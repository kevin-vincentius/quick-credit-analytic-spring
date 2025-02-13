package com.bcaf.tugasakhir.QUCA_Spring.repo;

import com.bcaf.tugasakhir.QUCA_Spring.model.MstScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScoreRepo extends JpaRepository<MstScore, Long> {
    Optional<MstScore> findByIdCabang_IdCabang(Short IdCabang);
}
