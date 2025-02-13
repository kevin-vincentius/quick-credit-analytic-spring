package com.bcaf.tugasakhir.QUCA_Spring.repo;

import com.bcaf.tugasakhir.QUCA_Spring.model.MstUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<MstUser, String> {

}
