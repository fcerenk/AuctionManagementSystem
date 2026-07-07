package pl.pja.mas.projectimpl_kilic_ferideceren_s27614.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "recording")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Recording implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @NotNull
    private LocalDateTime recordByDate;

    @Column(nullable = false)
    @NotBlank(message = " description cannot be blank")
    private String description;

    @ManyToOne
    @JoinColumn(name = "artwork_id", nullable = false)
    private ArtWork artWork;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    public void updateHistoricalData() {
    }


}
