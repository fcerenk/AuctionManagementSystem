package pl.pja.mas.projectimpl_kilic_ferideceren_s27614.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pja.mas.projectimpl_kilic_ferideceren_s27614.enums.AuctionItemStatus;
import pl.pja.mas.projectimpl_kilic_ferideceren_s27614.model.Auction;
import pl.pja.mas.projectimpl_kilic_ferideceren_s27614.model.AuctionItem;
import pl.pja.mas.projectimpl_kilic_ferideceren_s27614.repository.AuctionItemRepository;
import pl.pja.mas.projectimpl_kilic_ferideceren_s27614.repository.AuctionRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuctionService {

    private final AuctionRepository auctionRepository;
    private final AuctionItemRepository auctionItemRepository;


    public Auction getAuctionById(Long id) {
        return auctionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Auction not found"));
    }


    @Scheduled(fixedRate = 60000)
    public void closeExpiredAuctions() {
        List<AuctionItem> items = auctionItemRepository.findByStatusIn(
                List.of(AuctionItemStatus.ACTIVE_BIDDING, AuctionItemStatus.EXTENDED));

        for (AuctionItem item : items) {
            if (item.getAuction().getEndTime().isBefore(LocalDateTime.now())) {
                item.timeExpired();
                auctionItemRepository.save(item);
            }
        }
    }


}
