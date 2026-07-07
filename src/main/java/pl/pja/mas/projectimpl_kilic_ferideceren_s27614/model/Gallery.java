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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Gallery implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @NotBlank(message = "Gallery name cannot be blank")
    private String galleryName;


    @Column(nullable = false)
    @NotBlank(message = "Establish year cannot be blank")
    private String establishYear;

    @Column(nullable = false)
    @NotBlank(message = "contact number year cannot be blank")
    private String contactNumber;

    @Column(nullable = false)
    @NotBlank(message = "location cannot be blank")
    private String location;

    @Builder.Default

    @OneToMany(mappedBy = "gallery", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees = new ArrayList<>();


    @Builder.Default

    @OneToMany(mappedBy = "gallery", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<ArtWork> artWorks = new ArrayList<>();


    @Builder.Default

    @OneToMany(mappedBy = "gallery", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Auction> auctions = new ArrayList<>();


    @Builder.Default

    @OneToMany(mappedBy = "gallery", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exhibition> exhibitions = new ArrayList<>();


    public void addEmployee(Employee emp) {
        if (employees.contains(emp)) {

            throw new IllegalArgumentException("Recording already exists");
        }
        if (emp == null) {
            throw new IllegalArgumentException("Recording is null");
        }
        employees.add(emp);
        emp.setGallery(this);
    }


    public void removeEmployee(Employee emp) {
        if (emp == null) {
            throw new IllegalArgumentException("Recording is null");
        }
        if (!employees.contains(emp)) {
            throw new IllegalArgumentException("Recording does not exist");
        }

        employees.remove(emp);
        emp.setGallery(null);
    }


    public void addArtWork(ArtWork artWork) {
        if (artWorks.contains(artWork)) {

            throw new IllegalArgumentException("Recording already exists");
        }
        if (artWork == null) {
            throw new IllegalArgumentException("Recording is null");
        }
        artWorks.add(artWork);
        artWork.setGallery(this);
    }


    public void removeArtWork(ArtWork artWork) {
        if (artWork == null) {
            throw new IllegalArgumentException("Recording is null");
        }
        if (!artWorks.contains(artWork)) {
            throw new IllegalArgumentException("Recording does not exist");
        }

        artWorks.remove(artWork);
        artWork.setGallery(null);
    }


    public void addAuction(Auction auction) {
        if (auctions.contains(auction)) {

            throw new IllegalArgumentException("Recording already exists");
        }
        if (auction == null) {
            throw new IllegalArgumentException("Recording is null");
        }
        auctions.add(auction);
        auction.setGallery(this);
    }


    public void removeAuction(Auction auction) {
        if (auction == null) {
            throw new IllegalArgumentException("Recording is null");
        }
        if (!auctions.contains(auction)) {
            throw new IllegalArgumentException("Recording does not exist");
        }

        auctions.remove(auction);
        auction.setGallery(null);
    }


    public void addExhibition(Exhibition exhibition) {
        if (exhibitions.contains(exhibition)) {

            throw new IllegalArgumentException("Recording already exists");
        }
        if (exhibition == null) {
            throw new IllegalArgumentException("Recording is null");
        }
        exhibitions.add(exhibition);
        exhibition.setGallery(this);
    }


    public void removeExhibition(Exhibition exhibition) {
        if (exhibition == null) {
            throw new IllegalArgumentException("Recording is null");
        }
        if (!exhibitions.contains(exhibition)) {
            throw new IllegalArgumentException("Recording does not exist");
        }

        exhibitions.remove(exhibition);
        exhibition.setGallery(null);
    }


    public int getArtWorkCount() {
        return this.artWorks != null ? this.artWorks.size() : 0;
    }


}
