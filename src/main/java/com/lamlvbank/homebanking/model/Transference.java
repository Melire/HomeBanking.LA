//correccion de usuario github

package com.lamlvbank.homebanking.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import lombok.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Transference{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTF;

    @NotNull @Min(1) @Max(999999999)
    @Column(nullable = false, unique = true)
    private Long transferenceN;

    @NotNull @Length(min=10, max=40)
    private String reference;

    @NotNull @DecimalMin("1.0") @DecimalMax("1000000.0")
    @Column(nullable = false)
    private float amount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime creationDate;

    @ManyToOne
    @JsonIgnoreProperties("transferences")
    private Account origin;

    @ManyToOne
    @JsonIgnoreProperties("transferences")
    private Account destiny;

    public void addOriginAcc(Long idO){
        Account origin = new Account();
        origin.setIdA(idO);
        this.setOrigin(origin);
        this.getOrigin().getTransferences().add(this);
    }

    public void addDestinyAcc(Long idD){
        Account destiny = new Account();
        destiny.setIdA(idD);
        this.setDestiny(destiny);
        this.getDestiny().getTransferences().add(this);
    }
}
