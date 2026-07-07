package pl.pja.mas.projectimpl_kilic_ferideceren_s27614.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
@Table(name = "Bid")
public class Bid implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Positive
    private double bidAmount;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime bidTime;

    @ManyToOne
    @JoinColumn(name = "collector_id", nullable = false)
    private Collector collector;

    @ManyToOne
    @JoinColumn(name = "auction_item_id", nullable = false)
    private AuctionItem auctionItem;


}
