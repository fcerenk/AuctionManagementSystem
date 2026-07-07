package pl.pja.mas.projectimpl_kilic_ferideceren_s27614.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;
import pl.pja.mas.projectimpl_kilic_ferideceren_s27614.enums.AuctionItemStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "AuctionItem")
public class AuctionItem implements Serializable {

    public static final int EXTENSION_TIME_MINUTES = 5;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auctionItemId;

    @Positive
    @Column(nullable = false)
    private double openingPrice;

    @Positive
    @Column(nullable = false)
    private double reservePrice;

    @Column(nullable = false)
    @Positive
    private double currentHighestBid;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AuctionItemStatus status;

    @Column(nullable = false)
    @Positive
    private double minimumIncrement;


    @ManyToOne
    @JoinColumn(name = "artwork_id", nullable = false)
    private ArtWork artWork;


    @ManyToOne
    @JoinColumn(name = "auction_id", nullable = false)
    private Auction auction;


    @Builder.Default
    @OneToMany(mappedBy = "auctionItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bid> bids = new ArrayList<>();


    public void calculateMinIncrement() {
        if (currentHighestBid < 1000) {
            minimumIncrement = 50.0;
        } else if (currentHighestBid < 5000) {
            minimumIncrement = 100.0;
        } else if (currentHighestBid < 10000) {
            minimumIncrement = 500.0;
        } else {
            minimumIncrement = currentHighestBid * 0.10;
        }
    }


    public void validateBid(double bidAmount) {
        double requiredAmount = currentHighestBid + minimumIncrement;
        if (bidAmount < requiredAmount) {
            throw new IllegalArgumentException("Bid is so low");

        }
    }

    public void updateBidPrice(double newBidAmount) {
        currentHighestBid = newBidAmount;
    }


    public void checkTimeAndExtend(LocalDateTime bidTime) {
        LocalDateTime endTime = auction.getEndTime();
        if (bidTime.plusMinutes(EXTENSION_TIME_MINUTES).isAfter(endTime)) {
            status = AuctionItemStatus.EXTENDED;
            auction.setEndTime(auction.getEndTime().plusMinutes(EXTENSION_TIME_MINUTES));
        }
    }


    public void assignToAuction() {
        if (status != AuctionItemStatus.STORED) {
            throw new IllegalArgumentException("Just can be assigned to status STORED for auction item!");
        }
        status = AuctionItemStatus.PENDING;
    }


    public void onStartTime() {
        if (status != AuctionItemStatus.PENDING) {
            throw new IllegalArgumentException("Just can be started from to status PENDING for auction item!");
        }
        status = AuctionItemStatus.ACTIVE_BIDDING;
    }

    public void timeExpired() {
        if (status == AuctionItemStatus.ACTIVE_BIDDING || status == AuctionItemStatus.EXTENDED) {
            if (currentHighestBid >= reservePrice) {
                status = AuctionItemStatus.SOLD;
            } else {
                status = AuctionItemStatus.WITHDRAWN;
            }

        } else {
            throw new IllegalStateException("Time expired rule can be only used for active auction!");
        }
    }


    public void addBid(Bid bid) {
        if (bids.contains(bid)) {
            throw new IllegalArgumentException("bid already exists");
        }
        if (bid == null) {
            throw new IllegalArgumentException("bid cannot be null");
        }
        bids.add(bid);
        bid.setAuctionItem(this);
    }


    public void removeBid(Bid bid) {
        if (!bids.contains(bid)) {
            throw new IllegalArgumentException("bid not exists");
        }
        if (bid == null) {
            throw new IllegalArgumentException("bid cannot be null");
        }

        bids.remove(bid);
        bid.setAuctionItem(null);
    }

    public void setArtWork(ArtWork artWork) {
        if (artWork == null) {
            throw new IllegalArgumentException("Added artwork cannot be null!");
        }

        if (artWork.getExhibition() != null) {
            throw new IllegalStateException("XOR Error: This artwork is placed in exhibition, cannot be placed in the auction at the same time!");
        }

        this.artWork = artWork;

        if (!artWork.getAuctionItems().contains(this)) {
            artWork.getAuctionItems().add(this);
        }
    }


}
