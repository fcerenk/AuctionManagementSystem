package pl.pja.mas.projectimpl_kilic_ferideceren_s27614.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "person")
public abstract class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;


    @Column(nullable = false)
    @NotBlank(message = "name cannot be null")
    protected String name;

    @NotBlank(message = "surname cannot be null")
    @Column(nullable = false)
    protected String surname;

    @Column(nullable = false)
    @NotBlank(message = "password cannot be null")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 char")
    protected String password;

    @Column(nullable = false)
    @Min(value = 18)
    protected int age;

    @Column(nullable = true)
    protected String telephoneNumber;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "email cannot be null")
    @Email(message = "please provide a valid email address")
    protected String email;


    public abstract void registerProfile();


}
