package pl.pja.mas.projectimpl_kilic_ferideceren_s27614.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import pl.pja.mas.projectimpl_kilic_ferideceren_s27614.enums.Category;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Artwork")

public class ArtWork implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Artwork title cannot be null")
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Min(value = 0, message = "price cannot be negative")
    private double price;

    @Column(nullable = false)
    @NotBlank(message = "Made of year  cannot be null")
    private String madeYear;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Category category;

    @Embedded
    private Dimension dimension;

    @Getter
    private static int totalArtworks = 0;

    @PostPersist
    private void incrementTotalArtworks() {
        totalArtworks++;
    }

    @Builder.Default
    @OneToMany(mappedBy = "artWork", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recording> recordings = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;

    @Builder.Default
    @OneToMany(mappedBy = "artWork", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AuctionItem> items = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "exhibition_id", nullable = true)
    private Exhibition exhibition;


    @ManyToOne
    @JoinColumn(name = "gallery_id", nullable = true)
    private Gallery gallery;


    @PrePersist
    @PreUpdate
    private void validateXOR() {
        boolean isInExhibition = (this.exhibition != null);

        boolean isInAuction = (this.items != null && !this.items.isEmpty());

        if (isInExhibition && isInAuction) {
            throw new IllegalStateException("XOR Error: This artwork is placed in exhibition, cannot be placed in the auction at the same time!");
        }
    }


    public int getAgeOfWork() {
        int currentYear = LocalDate.now().getYear();
        return currentYear - Integer.parseInt(this.madeYear);
    }

    public void setArtWorkDimensions(Dimension dimension) {
        if (dimension == null) {
            throw new IllegalArgumentException("Dimension cannot be null");
        }
        this.dimension = dimension;
    }


    public void addRecording(Recording recording) {
        if (recordings.contains(recording)) {
            throw new IllegalArgumentException("Recording already exists");
        }
        if (recording == null) {
            throw new IllegalArgumentException("Recording cannot be null");
        }
        recordings.add(recording);
        recording.setArtWork(this);
    }


    public void removeRecording(Recording recording) {
        if (recording == null) {
            throw new IllegalArgumentException("Recording cannot be null");

        }
        if (!recordings.contains(recording)) {
            throw new IllegalArgumentException("Recording already exists");
        }
        recordings.remove(recording);
        recording.setArtWork(null);
    }


    public void addAuctionItem(AuctionItem item) {
        if (items.contains(item)) {
            throw new IllegalArgumentException("AuctionItem already exists");
        }
        if (item == null) {
            throw new IllegalArgumentException("AuctionItem cannot be null");
        }
        items.add(item);
        item.setArtWork(this);
    }


    public void removeAuctionItem(AuctionItem item) {
        if (item == null) {
            throw new IllegalArgumentException("AuctionItem cannot be null");

        }
        if (!items.contains(item)) {
            throw new IllegalArgumentException("AuctionItem already exists");
        }
        items.remove(item);
        item.setArtWork(null);
    }


    public List<AuctionItem> getAuctionItems() {
        return items;
    }


}
