package pl.pja.mas.projectimpl_kilic_ferideceren_s27614.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dimension implements Serializable {

    @Positive(message = "width must be greater than 0")
    @Column(nullable = false)
    private double width;


    @Positive(message = "height must be greater than 0")
    @Column(nullable = false)
    private double height;


    @Positive(message = "depth must be greater than 0 if provided")
    @Column(nullable = true)
    private double depth;


}
