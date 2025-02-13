package com.bcaf.tugasakhir.QUCA_Spring.service;

import com.bcaf.tugasakhir.QUCA_Spring.model.FormPerubahanBobotScore;
import com.bcaf.tugasakhir.QUCA_Spring.model.Session;
import com.bcaf.tugasakhir.QUCA_Spring.repo.PerubahanBobotScoreRepo;
import com.bcaf.tugasakhir.QUCA_Spring.repo.ScoreRepo;
import com.bcaf.tugasakhir.QUCA_Spring.repo.SessionRepo;
import com.bcaf.tugasakhir.QUCA_Spring.repo.UserRepo;
import com.bcaf.tugasakhir.QUCA_Spring.util.GlobalFunction;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BobotScoreService {

    @Autowired
    private ScoreRepo scoreRepo;

    @Autowired
    private PerubahanBobotScoreRepo perubahanBobotScoreRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SessionRepo sessionRepo;

    public ResponseEntity<Object> getListFormPerubahanBobotScore(HttpServletRequest request) {
        try {
            String sessionId = request.getHeader("X-Session") != null ? request.getHeader("X-Session") : null;

            Optional<Session> existingSession = sessionRepo.findBySessionId(sessionId);
            if (sessionId == null || existingSession.isEmpty()) {
                return GlobalFunction.requestFailed(null, HttpStatus.FORBIDDEN, "Anda tidak terotorisasi");
            }

            Session session = existingSession.get();
            String idUser = session.getIdUser();

            List<FormPerubahanBobotScore> listPerubahanBobotScore = perubahanBobotScoreRepo.findByUserId(idUser);

            return GlobalFunction.requestSuccess(listPerubahanBobotScore, null, HttpStatus.OK, "Sukses mencari list form!");
        } catch (Exception e) {
            e.printStackTrace();
            return GlobalFunction.requestFailed(null, HttpStatus.BAD_REQUEST, "Gagal mencari list form");
        }
    }

    public ResponseEntity<Object> getDetailFormPerubahanBobotScore(Long idFormPerubahanBobotScore, HttpServletRequest request) {
        try {
            String sessionId = request.getHeader("X-Session") != null ? request.getHeader("X-Session") : null;

            Optional<Session> existingSession = sessionRepo.findBySessionId(sessionId);
            if (sessionId == null || existingSession.isEmpty()) {
                return GlobalFunction.requestFailed(null, HttpStatus.FORBIDDEN, "Anda tidak terotorisasi");
            }

            Optional<FormPerubahanBobotScore> optionalFormPerubahan = perubahanBobotScoreRepo.findById(idFormPerubahanBobotScore);
            FormPerubahanBobotScore existingForm = optionalFormPerubahan.get();

            return GlobalFunction.requestSuccess(existingForm, null, HttpStatus.OK, "Sukses mencari detail form!");
        } catch (Exception e) {
            e.printStackTrace();
            return GlobalFunction.requestFailed(null, HttpStatus.BAD_REQUEST, "Gagal mencari form");
        }
    }

    public ResponseEntity<Object> getScoreForBranch(Long idFormPerubahanBobotScore, HttpServletRequest request) {
        try {
            String sessionId = request.getHeader("X-Session") != null ? request.getHeader("X-Session") : null;

            Optional<Session> existingSession = sessionRepo.findBySessionId(sessionId);
            if (sessionId == null || existingSession.isEmpty()) {
                return GlobalFunction.requestFailed(null, HttpStatus.FORBIDDEN, "Anda tidak terotorisasi");
            }

            Optional<FormPerubahanBobotScore> optionalFormPerubahan = perubahanBobotScoreRepo.findById(idFormPerubahanBobotScore);
            FormPerubahanBobotScore existingForm = optionalFormPerubahan.get();

            return GlobalFunction.requestSuccess(existingForm, null, HttpStatus.OK, "Sukses mencari detail form!");
        } catch (Exception e) {
            e.printStackTrace();
            return GlobalFunction.requestFailed(null, HttpStatus.BAD_REQUEST, "Gagal mencari form");
        }
    }
}
