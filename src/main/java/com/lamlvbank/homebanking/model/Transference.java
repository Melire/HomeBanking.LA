package com.lamlvbank.homebanking.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idT")
public class Transference extends Transaction {

    @NotNull @Min(1) @Max(999999999)
    @Column(nullable = false, unique = true)
    private Long transferenceN;
    private String reference;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Account destiny;

    public void addDestinyAcc(Long idD){
        Account destiny = new Account();
        destiny.setIdA(idD);
        this.setDestiny(destiny);
        this.getDestiny().getTransactions().add(this);
    }
}
