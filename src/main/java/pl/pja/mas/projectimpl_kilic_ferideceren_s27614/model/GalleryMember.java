package pl.pja.mas.projectimpl_kilic_ferideceren_s27614.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;


@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "GalleryMember")
public class GalleryMember extends Person implements Serializable {

    @Column(nullable = false, unique = true)
    private Long memberId;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collector collector;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Artist artist;

    public void addCollector(Collector collector) {
        this.collector = collector;
        collector.setMember(this);
    }

    public void addArtist(Artist artist) {
        this.artist = artist;
        artist.setMember(this);
    }


    public void removeCollector(Collector collector) {
        if (this.collector != null) {
            this.collector.setMember(null);
            this.collector = null;
        }
    }


    public void removeArtist(Artist artist) {
        if (this.artist != null) {
            this.artist.setMember(null);
            this.artist = null;
        }
    }

    public void browseExhibition() {
    }

    public void browseGallery() {
    }


    @Override
    public void registerProfile() {
        System.out.println("GalleryMember registered profile");
    }
}
