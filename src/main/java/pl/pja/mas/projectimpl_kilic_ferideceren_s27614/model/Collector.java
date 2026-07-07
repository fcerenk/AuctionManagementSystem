package pl.pja.mas.projectimpl_kilic_ferideceren_s27614.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "Collector")

public class Collector implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 0, message = "Balance cannot be negative")
    @Column(nullable = false)
    private double balance;

    @OneToOne(optional = false)
    @JoinColumn(name = "gallery_member_id", unique = true, nullable = false)
    private GalleryMember member;


    @Builder.Default
    @OneToMany(mappedBy = "collector", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bid> bids = new ArrayList<>();

    public boolean checkBalance(double requiredAmount) {
        return balance >= requiredAmount;
    }

    public void browseAuction() {
        System.out.println("Browsing available auction and its items");
    }

    public void viewBidHistory() {
        System.out.println("Viewing a bid history");
    }

    public void placeBid(double bidAmount) {
        System.out.println("Placing a bid......." + bidAmount);
    }


    public void addBid(Bid bid) {
        if (bids.contains(bid)) {
            throw new IllegalArgumentException("bid already exists");
        }
        if (bid == null) {
            throw new IllegalArgumentException("bid cannot be null");
        }
        bids.add(bid);
        bid.setCollector(this);
    }


    public void removeBid(Bid bid) {
        if (!bids.contains(bid)) {
            throw new IllegalArgumentException("bid not exists");
        }
        if (bid == null) {
            throw new IllegalArgumentException("bid cannot be null");
        }

        bids.remove(bid);
        bid.setCollector(null);
    }

}
