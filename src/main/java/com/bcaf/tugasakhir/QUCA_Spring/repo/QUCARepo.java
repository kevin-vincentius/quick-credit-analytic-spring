package com.bcaf.tugasakhir.QUCA_Spring.repo;

import com.bcaf.tugasakhir.QUCA_Spring.model.FormQUCA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QUCARepo extends JpaRepository<FormQUCA, Long> {
//    @Query("SELECT f FROM FormQUCA f WHERE f.MID.MID = :mid")
//    Optional<FormQUCA> findFormByMID(@Param("mid") Long mid);
    Optional<FormQUCA> findByMID_MID(Long MID);
    List<FormQUCA> findByIdMarketingOfficer(String idMarketingOfficer);

}
