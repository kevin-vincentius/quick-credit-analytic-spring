package com.bcaf.tugasakhir.QUCA_Spring.service;

import com.bcaf.tugasakhir.QUCA_Spring.dto.req.ReqNewFormQUCADTO;
import com.bcaf.tugasakhir.QUCA_Spring.dto.req.ReqRejectFormQUCADTO;
import com.bcaf.tugasakhir.QUCA_Spring.dto.req.ReqUpdateFormQUCADTO;
import com.bcaf.tugasakhir.QUCA_Spring.model.*;
import com.bcaf.tugasakhir.QUCA_Spring.repo.*;
import com.bcaf.tugasakhir.QUCA_Spring.util.GlobalFunction;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class QUCAService {

    @Autowired
    private QUCARepo qucaRepo;

    @Autowired
    private MobileEntryRepo midRepo;

    @Autowired
    private ScoreRepo scoreRepo;

    @Autowired
    private SessionRepo sessionRepo;

    @Autowired
    private UserRepo userRepo;

    private ModelMapper modelMapper = new ModelMapper();

    public ResponseEntity<Object> getListFormQUCA(HttpServletRequest request) {
        // list form sesuai dengan cabang yang ditempati user
        //
        try {
            String sessionId = request.getHeader("X-Session") != null ? request.getHeader("X-Session") : null;

            Optional<Session> existingSession = sessionRepo.findBySessionId(sessionId);
            if (sessionId == null || existingSession.isEmpty()) {
                return GlobalFunction.requestFailed(null, HttpStatus.FORBIDDEN, "Anda tidak terotorisasi");
            }

            Session session = existingSession.get();
            Optional<MstUser> optionalUser = userRepo.findById(session.getIdUser());
            MstUser currentUser = optionalUser.get();

            Set<Short> supervisedBranchIds = currentUser.getListCabang().stream()
                    .map(MstCabang::getIdCabang)
                    .collect(Collectors.toSet());            List<FormQUCA> listFormQUCA = qucaRepo.findByIdCabangIn(supervisedBranchIds);

            return GlobalFunction.requestSuccess(listFormQUCA, null, HttpStatus.OK, "Sukses mengambil list form!");
        } catch (Exception e) {
            e.printStackTrace(); // Log the root cause
            return GlobalFunction.requestFailed(null, HttpStatus.BAD_REQUEST, "Gagal submit form");
        }
    }

    public ResponseEntity<Object> getFormDetail(Long MID, HttpServletRequest request) {
        try {
            String sessionId = request.getHeader("X-Session") != null ? request.getHeader("X-Session") : null;

            Optional<Session> existingSession = sessionRepo.findBySessionId(sessionId);
            if (sessionId == null || existingSession.isEmpty()) {
                return GlobalFunction.requestFailed(null, HttpStatus.FORBIDDEN, "Anda tidak terotorisasi");
            }

            Session session = existingSession.get();
            Optional<FormQUCA> optionalFormQUCA = qucaRepo.findByMID_MID(MID);

            FormQUCA formDetail = optionalFormQUCA.get();

            return GlobalFunction.requestSuccess(formDetail, null, HttpStatus.OK, "Sukses mencari detail form!");
        } catch (Exception e) {
            e.printStackTrace(); // Log the root cause
            return GlobalFunction.requestFailed(null, HttpStatus.BAD_REQUEST, "Gagal submit form");
        }
    }

    public ResponseEntity<Object> getKonsumenByMID(Long MID, HttpServletRequest request) {

        // gunanya untuk tarik data mid di form quca.
        // TODO 2 validasi:
        // kalau mid ga terdaftar di table mid -> not found
        // kalau mid udah permah dipake di formquca -> conflict

        try {
            String sessionId = request.getHeader("X-Session") != null ? request.getHeader("X-Session") : null;

            Optional<Session> existingSession = sessionRepo.findBySessionId(sessionId);
            if (sessionId == null || existingSession.isEmpty()) {
                return GlobalFunction.requestFailed(null, HttpStatus.FORBIDDEN, "Anda tidak terotorisasi");
            }

            Optional<MstMobileEntry> midOptional = midRepo.findById(MID);
            if (midOptional.isEmpty()) {
                return GlobalFunction.requestFailed(null, HttpStatus.NOT_FOUND, "MID tidak ditemukan");
            }

            MstMobileEntry midObject = midOptional.get();

            Optional<FormQUCA> formQUCAOptional = qucaRepo.findByMID_MID(MID);
            if (formQUCAOptional.isPresent()) {
                return GlobalFunction.requestFailed(null, HttpStatus.CONFLICT, "MID sudah dipakai di form lain");
            }

            return GlobalFunction.requestSuccess(midObject, null, HttpStatus.OK, "Sukses mencari MID!");

        } catch (Exception e) {
            e.printStackTrace();
            return GlobalFunction.requestFailed(null, HttpStatus.BAD_REQUEST, "Gagal mengambil MID");
        }
    }

    public ResponseEntity<Object> submitFormQUCA(ReqNewFormQUCADTO reqNewFormQUCA, HttpServletRequest request) {
        try {
            String sessionId = request.getHeader("X-Session") != null ? request.getHeader("X-Session") : null;

            Optional<Session> existingSession = sessionRepo.findBySessionId(sessionId);
            if (sessionId == null || existingSession.isEmpty()) {
                return GlobalFunction.requestFailed(null, HttpStatus.FORBIDDEN, "Anda tidak terotorisasi");
            }

            // gada penjagaan karena udah ada di api get mid,
            // jadi mid yang diinput di api ini udah pasti exist di table mid dan belum pernah dipakai di form lain
            Optional<MstMobileEntry> midExists = midRepo.findById(reqNewFormQUCA.getMID());

            MstMobileEntry mid = midExists.get();

            Optional<MstUser> optionalUser = userRepo.findById(existingSession.get().getIdUser());
            MstUser currentUser = optionalUser.get();

            // get id score sesuai cabang
            Optional<MstScore> optionalMstScore = scoreRepo.findByIdCabang_IdCabang(mid.getIdCabang().getIdCabang());
            MstScore mstScore = optionalMstScore.get();

            FormQUCA formQUCA = modelMapper.map(reqNewFormQUCA, FormQUCA.class);
            formQUCA.setIdScore(mstScore);
            formQUCA.setMID(mid);
            formQUCA.setStatusQUCA("ON REVIEW");
            formQUCA.setIdMarketingOfficer(mid.getIdMarketingOfficer().getIdUser());
            formQUCA.setIdCabang(currentUser.getListCabang().iterator().next().getIdCabang());
            qucaRepo.save(formQUCA);

            return GlobalFunction.requestSuccess(null, null, HttpStatus.CREATED, "Sukses submit form!");
        } catch (Exception e) {
            e.printStackTrace(); // Log the root cause
            return GlobalFunction.requestFailed(null, HttpStatus.BAD_REQUEST, "Gagal submit form");
        }
    }

    public ResponseEntity<Object> updateFormQUCA(ReqUpdateFormQUCADTO reqUpdateFormQUCA, HttpServletRequest request) {
        try {
            String sessionId = request.getHeader("X-Session") != null ? request.getHeader("X-Session") : null;

            Optional<Session> existingSession = sessionRepo.findBySessionId(sessionId);
            if (sessionId == null || existingSession.isEmpty()) {
                return GlobalFunction.requestFailed(null, HttpStatus.FORBIDDEN, "Anda tidak terotorisasi");
            }

            Optional<FormQUCA> optionalFormQUCA = qucaRepo.findByMID_MID(reqUpdateFormQUCA.getMID());
            FormQUCA existingForm = optionalFormQUCA.orElse(null);

            existingForm.setTabThdAngs(reqUpdateFormQUCA.getTabThdAngs());
            existingForm.setAngsThdPdpt(reqUpdateFormQUCA.getAngsThdPdpt());
            existingForm.setStatusPekerjaan(reqUpdateFormQUCA.getStatusPekerjaan());
            existingForm.setStatusTempatTinggal(reqUpdateFormQUCA.getStatusTempatTinggal());
            existingForm.setKondisiTempatTinggal(reqUpdateFormQUCA.getKondisiTempatTinggal());
            existingForm.setKarakterDiLingkungan(reqUpdateFormQUCA.getKarakterDiLingkungan());
            existingForm.setHasilGetContact(reqUpdateFormQUCA.getHasilGetContact());
            existingForm.setHasilSLIK(reqUpdateFormQUCA.getHasilSLIK());
            existingForm.setStatusQUCA("ON REVIEW");

            qucaRepo.save(existingForm);

            return GlobalFunction.requestSuccess(existingForm, null, HttpStatus.CREATED, "Sukses update form!");
        } catch (Exception e) {
            e.printStackTrace(); // Log the root cause
            return GlobalFunction.requestFailed(null, HttpStatus.BAD_REQUEST, "Gagal update form");
        }
    }

    public ResponseEntity<Object> approveFormQUCA(Long MID, HttpServletRequest request) {
        try {
            String sessionId = request.getHeader("X-Session") != null ? request.getHeader("X-Session") : null;

            Optional<Session> existingSession = sessionRepo.findBySessionId(sessionId);
            if (sessionId == null || existingSession.isEmpty()) {
                return GlobalFunction.requestFailed(null, HttpStatus.FORBIDDEN, "Anda tidak terotorisasi");
            }

            Optional<FormQUCA> formQUCAOptional = qucaRepo.findByMID_MID(MID);

            FormQUCA existingForm = formQUCAOptional.orElse(null);

            MstScore mstScore = existingForm.getIdScore();

            Float hasilScore = (float) (mstScore.getBobotHasilSLIK() * existingForm.getHasilSLIK()) +
                    (mstScore.getBobotHasilGetContact() * existingForm.getHasilGetContact()) +
                    (mstScore.getBobotAngsThdPdpt() * existingForm.getAngsThdPdpt()) +
                    (mstScore.getBobotTabThdAngs() * existingForm.getTabThdAngs()) +
                    (mstScore.getBobotKarakter() * existingForm.getKarakterDiLingkungan()) +
                    (mstScore.getBobotKondisiTempatTinggal() * existingForm.getKondisiTempatTinggal()) +
                    (mstScore.getBobotStatusTempatTinggal() * existingForm.getStatusTempatTinggal()) +
                    (mstScore.getBobotStatusPekerjaan() * existingForm.getStatusPekerjaan());

            existingForm.setTotalScore(hasilScore / 100);
            existingForm.setStatusQUCA("APPROVED");
            existingForm.setApprovedBy(existingSession.get().getIdUser());
            qucaRepo.save(existingForm);

            return GlobalFunction.requestSuccess(null, null, HttpStatus.OK, "Sukses approve form!");
        } catch (Exception e) {
            e.printStackTrace(); // Log the root cause
            return GlobalFunction.requestFailed(null, HttpStatus.BAD_REQUEST, "Gagal approve form");
        }
    }


    public ResponseEntity<Object> rejectFormQUCA(ReqRejectFormQUCADTO reqRejectFormQUCA, HttpServletRequest request) {
        try {
            String sessionId = request.getHeader("X-Session") != null ? request.getHeader("X-Session") : null;

            Optional<Session> existingSession = sessionRepo.findBySessionId(sessionId);
            if (sessionId == null || existingSession.isEmpty()) {
                return GlobalFunction.requestFailed(null, HttpStatus.FORBIDDEN, "Anda tidak terotorisasi");
            }

            Optional<FormQUCA> formQUCAOptional = qucaRepo.findByMID_MID(reqRejectFormQUCA.getMID());
            FormQUCA existingForm = formQUCAOptional.orElse(null);

            existingForm.setStatusQUCA("NEED REVISION");
            existingForm.setNotes(reqRejectFormQUCA.getNotes());
            qucaRepo.save(existingForm);

            return GlobalFunction.requestSuccess(null, null, HttpStatus.OK, "Sukses reject form!");
        } catch (Exception e) {
            e.printStackTrace(); // Log the root cause
            return GlobalFunction.requestFailed(null, HttpStatus.BAD_REQUEST, "Gagal reject form");
        }
    }

}
