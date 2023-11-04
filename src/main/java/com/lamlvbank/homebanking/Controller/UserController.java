package com.lamlvbank.homebanking.Controller;

import com.lamlvbank.homebanking.Model.User;
import com.lamlvbank.homebanking.Service.UserService;
import jakarta.persistence.PostUpdate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apiHB/users")
public class UserController {
    @Autowired
    private UserService uS;
    @GetMapping
    ResponseEntity<List<User>> findAll() {
      return ResponseEntity.ok(uS.findAll());
    }
    @GetMapping("{idU}")
    ResponseEntity<User> findById(@PathVariable("idU")Long idU){
        Optional<User> optUser=uS.findById(idU);
          if (optUser.isPresent()){
            return ResponseEntity.ok(optUser.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("dni/{dniU}")
    ResponseEntity<User> findBydni(@PathVariable("dniU")String dniU){
        Optional<User> optUser=uS.getUserByDni(dniU);
        if (optUser.isPresent()){
            return ResponseEntity.ok(optUser.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    ResponseEntity<User> save(@Valid @RequestBody User user){
        User userSaved = uS.save(user);
        if (userSaved !=null){
            return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    /*
    @DeleteMapping("{idU}")
    ResponseEntity<User> deleteById(@PathVariable("idU")Long idU){
        Optional<User> optUser = uS.findById(idU);
        if (uS.deleteById(idU)){
            return ResponseEntity.ok(optUser.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    */

    @DeleteMapping("{idU}")
    ResponseEntity<User> deleteById(@PathVariable("idU")Long idU){
        Optional<User> optUser = uS.findById(idU);
        if (optUser.isPresent()){
            boolean userDeleted = uS.deleteById(idU);
            return ResponseEntity.ok(optUser.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("dni/{dniU}")
    ResponseEntity<User> deleteByDni(@PathVariable("dniU")String dniU){
        Optional<User> optUser = uS.getUserByDni(dniU);
        if (optUser.isPresent()){
            boolean userDeleted = uS.deleteById(optUser.get().getIdU());
            return ResponseEntity.ok(optUser.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("{idU}")
    ResponseEntity<User> updateById(@Valid @RequestBody  User user, @PathVariable("idU")Long idU){
         user.setIdU(idU);
         User userSaved = uS.save(user);
        if (userSaved !=null){
                return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);
            } else {
                return ResponseEntity.badRequest().build();
            }
        }
}