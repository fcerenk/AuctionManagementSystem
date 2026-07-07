package pl.pja.mas.projectimpl_kilic_ferideceren_s27614.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import pl.pja.mas.projectimpl_kilic_ferideceren_s27614.model.GalleryMember;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "Artist")

public class Artist implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 2000)
    @NotBlank(message = "Biography cannot be blank")
    @Size(max = 2000, message = "Biography can be max 2000 char")
    private String biography;


    @OneToOne(optional = false)
    @JoinColumn(name = "gallery_member_id", unique = true, nullable = false)
    private GalleryMember member;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArtWork> artworks = new ArrayList<>();


    public void submitArtwork() {
        System.out.println("Submitting artwork.....");
    }

    public void updateBiography(String newBio) {
        biography = newBio;
        System.out.println("Biography is updated");
    }


    public void addArtWork(ArtWork artWork) {
        if (artworks.contains(artWork)) {
            throw new IllegalArgumentException("Art work already exists");
        }
        if (artWork == null) {
            throw new IllegalArgumentException("Art work cannot be null");
        }
        artworks.add(artWork);
        artWork.setArtist(this);
    }


    public void removeArtWork(ArtWork artWork) {
        if (!artworks.contains(artWork)) {
            throw new IllegalArgumentException("Artwork not exists");
        }
        if (artWork == null) {
            throw new IllegalArgumentException("Artwork cannot be null");
        }

        artworks.remove(artWork);
        artWork.setArtist(null);
    }
}
