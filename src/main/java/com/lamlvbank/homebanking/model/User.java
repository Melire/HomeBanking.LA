package com.lamlvbank.homebanking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "idU")
public class User {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long idU;

 @NonNull @NotBlank @Size(min=3, max=40)
 //@Length(min=3, max=40) combina funciones de @NotBlank y @Size
 @Column(nullable = false)
 private String name;

 @NonNull @NotBlank @Size(min=3, max=40)
 @Column(nullable = false)
 private String surname;

 @NonNull @NotBlank @Size(min=8, max=10)
 @Column(nullable = false, unique = true)
 private String dni;

 @NonNull
 @Column(nullable = false)
 private LocalDate birthdate;

 @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
 //@JsonBackReference
 private List<Account> accounts;

  public User (){
   this.accounts = new ArrayList<>();
  }

}