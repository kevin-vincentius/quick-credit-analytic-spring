package com.bcaf.tugasakhir.QUCA_Spring.service;

import com.bcaf.tugasakhir.QUCA_Spring.dto.req.ReqFormPerubahan;
import com.bcaf.tugasakhir.QUCA_Spring.model.FormPerubahanBobotScore;
import com.bcaf.tugasakhir.QUCA_Spring.model.MstScore;
import com.bcaf.tugasakhir.QUCA_Spring.model.MstUser;
import com.bcaf.tugasakhir.QUCA_Spring.model.Session;
import com.bcaf.tugasakhir.QUCA_Spring.repo.PerubahanBobotScoreRepo;
import com.bcaf.tugasakhir.QUCA_Spring.repo.ScoreRepo;
import com.bcaf.tugasakhir.QUCA_Spring.repo.SessionRepo;
import com.bcaf.tugasakhir.QUCA_Spring.repo.UserRepo;
import com.bcaf.tugasakhir.QUCA_Spring.util.GlobalFunction;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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

    private ModelMapper modelMapper = new ModelMapper();

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

    public ResponseEntity<Object> getScoreForBranch(Short idCabang, HttpServletRequest request) {
        try {
            String sessionId = request.getHeader("X-Session") != null ? request.getHeader("X-Session") : null;

            Optional<Session> existingSession = sessionRepo.findBySessionId(sessionId);
            if (sessionId == null || existingSession.isEmpty()) {
                return GlobalFunction.requestFailed(null, HttpStatus.FORBIDDEN, "Anda tidak terotorisasi");
            }

            Optional<MstScore> optionalMstScore = scoreRepo.findByIdCabang_IdCabang(idCabang);
            MstScore existingScoreForBranch = optionalMstScore.get();

            return GlobalFunction.requestSuccess(existingScoreForBranch, null, HttpStatus.OK, "Sukses mencari detail score untuk cabang!");
        } catch (Exception e) {
            e.printStackTrace();
            return GlobalFunction.requestFailed(null, HttpStatus.BAD_REQUEST, "Gagal mencari detail score!");
        }
    }

    public ResponseEntity<Object> addNewFormPerubahan(ReqFormPerubahan reqFormPerubahanBobotScoreDTO, HttpServletRequest request) {
        try {
            String sessionId = request.getHeader("X-Session") != null ? request.getHeader("X-Session") : null;

            Optional<Session> existingSession = sessionRepo.findBySessionId(sessionId);
            if (sessionId == null || existingSession.isEmpty()) {
                return GlobalFunction.requestFailed(null, HttpStatus.FORBIDDEN, "Anda tidak terotorisasi");
            }

            List<FormPerubahanBobotScore> formPerubahanExists = perubahanBobotScoreRepo.findExistingForm(reqFormPerubahanBobotScoreDTO.getIdCabang());
            if (!formPerubahanExists.isEmpty()) {
                return GlobalFunction.requestFailed(null, HttpStatus.CONFLICT, "Ada form perubahan yang sedang pending!");
            }

            Float totalBobotScore = reqFormPerubahanBobotScoreDTO.getNewBobotAngsThdPdpt() + reqFormPerubahanBobotScoreDTO.getNewbobotTabThdAngs()
                    + reqFormPerubahanBobotScoreDTO.getNewBobotKarakter() + reqFormPerubahanBobotScoreDTO.getNewBobotHasilSLIK()
                    + reqFormPerubahanBobotScoreDTO.getNewBobotHasilGetContact() + reqFormPerubahanBobotScoreDTO.getNewBobotKondisiTempatTinggal()
                    + reqFormPerubahanBobotScoreDTO.getNewBobotStatusPekerjaan() + reqFormPerubahanBobotScoreDTO.getNewBobotStatusTempatTinggal();

            if (totalBobotScore != 1) {
                return GlobalFunction.requestFailed(null, HttpStatus.BAD_REQUEST, "Total bobot score belum 100%!");
            }

            FormPerubahanBobotScore newFormPerubahan = modelMapper.map(reqFormPerubahanBobotScoreDTO, FormPerubahanBobotScore.class);
            newFormPerubahan.setCreatedBy(existingSession.get().getIdUser());
            newFormPerubahan.setStatusFormPerubahan("ON REVIEW RM");
            perubahanBobotScoreRepo.save(newFormPerubahan);

            return GlobalFunction.requestSuccess(null, null, HttpStatus.CREATED, "Sukses membuat form perubahan baru!");
        } catch (Exception e) {
            e.printStackTrace();
            return GlobalFunction.requestFailed(null, HttpStatus.BAD_REQUEST, "Gagal membuat form!");
        }
    }

    public ResponseEntity<Object> rejectFormPerubahan(Long idFormPerubahan, HttpServletRequest request) {
        try {
            String sessionId = request.getHeader("X-Session") != null ? request.getHeader("X-Session") : null;

            Optional<Session> existingSession = sessionRepo.findBySessionId(sessionId);
            if (sessionId == null || existingSession.isEmpty()) {
                return GlobalFunction.requestFailed(null, HttpStatus.FORBIDDEN, "Anda tidak terotorisasi");
            }

            Optional<FormPerubahanBobotScore> optionalFormPerubahan = perubahanBobotScoreRepo.findById(idFormPerubahan);
            FormPerubahanBobotScore existingForm = optionalFormPerubahan.get();

            existingForm.setStatusFormPerubahan("REJECTED");
            existingForm.setModifiedBy(existingSession.get().getIdUser());
            perubahanBobotScoreRepo.save(existingForm);

            return GlobalFunction.requestSuccess(null, null, HttpStatus.OK, "Sukses reject form perubahan!");
        } catch (Exception e) {
            e.printStackTrace();
            return GlobalFunction.requestFailed(null, HttpStatus.BAD_REQUEST, "Gagal reject form!");
        }
    }

    public ResponseEntity<Object> approveFormPerubahan(Long idFormPerubahan, HttpServletRequest request) {
        try {
            String sessionId = request.getHeader("X-Session") != null ? request.getHeader("X-Session") : null;

            Optional<Session> existingSession = sessionRepo.findBySessionId(sessionId);
            if (sessionId == null || existingSession.isEmpty()) {
                return GlobalFunction.requestFailed(null, HttpStatus.FORBIDDEN, "Anda tidak terotorisasi");
            }

            Optional<MstUser> optionalUser = userRepo.findById(existingSession.get().getIdUser());
            MstUser user = optionalUser.get();
            Short aksesId = user.getAkses().getAksesId();

            Optional<FormPerubahanBobotScore> optionalFormPerubahan = perubahanBobotScoreRepo.findById(idFormPerubahan);
            FormPerubahanBobotScore existingForm = optionalFormPerubahan.get();

            if(Objects.equals(existingForm.getStatusFormPerubahan(), "REJECTED")) {
                return GlobalFunction.requestFailed(null, HttpStatus.BAD_REQUEST, "Form sudah di reject!");
            } else if(Objects.equals(existingForm.getStatusFormPerubahan(), "APPROVED")){
                return GlobalFunction.requestFailed(null, HttpStatus.BAD_REQUEST, "Form sudah di approve!");
            } else if(Objects.equals(existingForm.getStatusFormPerubahan(), "ON REVIEW DD")){
                if(aksesId != 3){
                    return GlobalFunction.requestFailed(null, HttpStatus.UNAUTHORIZED, "Anda bukan DD!");
                }
                existingForm.setStatusFormPerubahan("APPROVED");
            } else if(Objects.equals(existingForm.getStatusFormPerubahan(), "ON REVIEW RM")){
                if(aksesId != 2){
                    return GlobalFunction.requestFailed(null, HttpStatus.UNAUTHORIZED, "Anda bukan RM!");
                }
                existingForm.setStatusFormPerubahan("ON REVIEW DD");
            }

            existingForm.setModifiedBy(existingSession.get().getIdUser());
            perubahanBobotScoreRepo.save(existingForm);

            return GlobalFunction.requestSuccess(null, null, HttpStatus.OK, "Sukses approve form perubahan!");
        } catch (Exception e) {
            e.printStackTrace();
            return GlobalFunction.requestFailed(null, HttpStatus.BAD_REQUEST, "Gagal approve form!");
        }
    }


}