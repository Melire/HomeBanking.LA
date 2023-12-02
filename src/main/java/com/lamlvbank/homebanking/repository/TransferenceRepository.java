package com.lamlvbank.homebanking.repository;

import com.lamlvbank.homebanking.model.Transference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransferenceRepository extends JpaRepository<Transference,Long> {
    boolean existsByTransferenceN(Long transfN);
    List<Transference> findAllByOriginIdA(Long idO);
    List<Transference> findAllByDestinyIdA(Long idD);
}
