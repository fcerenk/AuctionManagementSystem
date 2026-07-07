package pl.pja.mas.projectimpl_kilic_ferideceren_s27614.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Auction")
public class Auction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Auction name cannot be null")
    private String name;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime startTime;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime endTime;


    @Builder.Default
    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AuctionItem> auctionItems = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "gallery_id", nullable = true)
    private Gallery gallery;


    public void addAuctionItem(AuctionItem auctionItem) {

        if (auctionItem == null) {
            throw new IllegalArgumentException("Recording is null");
        }
        if (auctionItems.contains(auctionItem)) {
            throw new IllegalArgumentException("Auction item already exists");

        }
        auctionItems.add(auctionItem);

        auctionItem.setAuction(this);
    }

    public void removeAuctionItem(AuctionItem auctionItem) {

        if (auctionItem == null) {
            throw new IllegalArgumentException("Recording is null");
        }
        if (!auctionItems.contains(auctionItem)) {
            throw new IllegalArgumentException("Auction item already exists");

        }
        auctionItems.remove(auctionItem);
        auctionItem.setAuction(null);
    }


}
