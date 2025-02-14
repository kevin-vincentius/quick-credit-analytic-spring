package com.bcaf.tugasakhir.QUCA_Spring.repo;

import com.bcaf.tugasakhir.QUCA_Spring.model.FormPerubahanBobotScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PerubahanBobotScoreRepo extends JpaRepository<FormPerubahanBobotScore, Long> {
    @Query(value = """
            SELECT f FROM FormPerubahanBobotScore f
            WHERE f.idCabang IN (
                SELECT c FROM MstCabang c
                JOIN c.users u
                WHERE u.idUser = :idUser
            )
            """)
    List<FormPerubahanBobotScore> findByUserId(String idUser);

    @Query("SELECT f FROM FormPerubahanBobotScore f WHERE f.idCabang.idCabang = :idCabang AND (f.statusFormPerubahan = 'ON REVIEW RM' OR f.statusFormPerubahan = 'ON REVIEW DD')")
    List<FormPerubahanBobotScore> findExistingForm(Short idCabang);

}