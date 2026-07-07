package pl.pja.mas.projectimpl_kilic_ferideceren_s27614.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Exhibiton")
@Builder
public class Exhibition implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @NotBlank(message = "exhibition name cannot be blank")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "theme cannot be blank")
    private String theme;

    @Column(nullable = false)
    @NotBlank(message = "time of year cannot be blank")
    private String timeOfYear;

    @Builder.Default
    @OneToMany(mappedBy = "exhibition", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<ArtWork> artworks = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "gallery_id", nullable = false)
    private Gallery gallery;

    public void addArtwork(ArtWork artwork) {
        if (artwork == null) {
            throw new IllegalArgumentException("Artwork cannot be null");

        }
        if (artworks.contains(artwork)) {
            throw new IllegalArgumentException("Artwork already exists");
        }

        if (artwork.getAuctionItems() != null && !artwork.getAuctionItems().isEmpty()) {
            throw new IllegalStateException("XOR Error: This artwork is placed in auction now, cannot be placed in exhibition at the same time!");
        }

        artworks.add(artwork);
        artwork.setExhibition(this);
    }


    public void removeArtwork(ArtWork artwork) {
        if (artwork == null) {
            throw new IllegalArgumentException("Artwork cannot be null");

        }
        if (!artworks.contains(artwork)) {
            throw new IllegalArgumentException("Artwork does not exist");
        }
        artworks.remove(artwork);
        artwork.setExhibition(null);
    }


}
