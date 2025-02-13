package com.bcaf.tugasakhir.QUCA_Spring.repo;

import com.bcaf.tugasakhir.QUCA_Spring.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepo extends JpaRepository<Session, String> {
    Optional<Session> findBySessionId(String sessionId);
}
