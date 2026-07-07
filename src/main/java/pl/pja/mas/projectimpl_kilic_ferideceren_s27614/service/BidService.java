package pl.pja.mas.projectimpl_kilic_ferideceren_s27614.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pja.mas.projectimpl_kilic_ferideceren_s27614.enums.AuctionItemStatus;
import pl.pja.mas.projectimpl_kilic_ferideceren_s27614.model.AuctionItem;
import pl.pja.mas.projectimpl_kilic_ferideceren_s27614.model.Bid;
import pl.pja.mas.projectimpl_kilic_ferideceren_s27614.model.Collector;
import pl.pja.mas.projectimpl_kilic_ferideceren_s27614.model.GalleryMember;
import pl.pja.mas.projectimpl_kilic_ferideceren_s27614.repository.AuctionItemRepository;
import pl.pja.mas.projectimpl_kilic_ferideceren_s27614.repository.BidRepository;
import pl.pja.mas.projectimpl_kilic_ferideceren_s27614.repository.CollectorRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BidService {
    private final AuctionItemRepository auctionItemRepository;
    private final CollectorRepository collectorRepository;
    private final BidRepository bidRepository;

    @Transactional
    public void placeBid(Long collectorId, Long auctionItemId, double bidAmount) {


        if (collectorId == null) {
            throw new IllegalStateException("Please select a valid Collector from the top menu first.");
        }


        Collector collector = collectorRepository.findById(collectorId)
                .orElseThrow(() -> new IllegalArgumentException("Only registered Collectors can place bids."));


        AuctionItem item = auctionItemRepository.findById(auctionItemId)
                .orElseThrow(() -> new IllegalArgumentException("AuctionItem not found"));

        if (item.getStatus() != AuctionItemStatus.ACTIVE_BIDDING && item.getStatus() != AuctionItemStatus.EXTENDED) {
            throw new IllegalStateException("AuctionItem is not in active bidding phase");
        }

        if (item.getArtWork().getArtist().getMember().getId().equals(collector.getMember().getId())) {
            throw new IllegalStateException("Conflict of interest: Artists cannot bid on their own artworks");
        }

        item.calculateMinIncrement();

        item.validateBid(bidAmount);

        if (!collector.checkBalance(bidAmount)) {
            throw new IllegalStateException("Insufficient  balance, please check your balance!");
        }

        item.updateBidPrice(bidAmount);


        item.checkTimeAndExtend(LocalDateTime.now());

        Bid bid = Bid.builder()
                .bidAmount(bidAmount)
                .bidTime(LocalDateTime.now())
                .collector(collector)
                .auctionItem(item)
                .build();

        item.addBid(bid);
        bidRepository.save(bid);
        auctionItemRepository.save(item);


    }


}
