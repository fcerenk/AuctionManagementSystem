package pl.pja.mas.projectimpl_kilic_ferideceren_s27614.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Employee")
public class Employee extends Person implements Serializable {

    @ElementCollection
    private List<String> knownLanguages = new ArrayList<>();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recording> recordings = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "gallery_id", nullable = false)
    private Gallery gallery;


    public void addLanguage(String language) {
        if (!knownLanguages.contains(language)) {
            knownLanguages.add(language);
        }
    }

    public void removeLanguage(String language) {
        if (knownLanguages.size() <= 1) {

            throw new IllegalStateException("There are no languages available");
        }
        knownLanguages.remove(language);

    }

    @Override
    public void registerProfile() {
        System.out.println("Employee registered profile");
    }

    public void recordArtWork() {
        System.out.println("Employee record work");
    }

    public void manageExhibition() {
        System.out.println("Employee manageExhibition");
    }

    public void manageAuction() {
        System.out.println("Employee manageAuction");
    }

    public void addRecording(Recording recording) {
        if (recordings.contains(recording)) {

            throw new IllegalArgumentException("Recording already exists");
        }
        if (recording == null) {
            throw new IllegalArgumentException("Recording is null");
        }
        recordings.add(recording);
        recording.setEmployee(this);
    }


    public void removeRecording(Recording recording) {
        if (recording == null) {
            throw new IllegalArgumentException("Recording is null");
        }
        if (!recordings.contains(recording)) {
            throw new IllegalArgumentException("Recording does not exist");
        }

        recordings.remove(recording);
        recording.setEmployee(null);
    }
}
