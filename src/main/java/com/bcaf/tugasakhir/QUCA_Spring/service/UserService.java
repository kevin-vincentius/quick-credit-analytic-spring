package com.bcaf.tugasakhir.QUCA_Spring.service;

import com.bcaf.tugasakhir.QUCA_Spring.dto.req.ReqLoginDTO;
import com.bcaf.tugasakhir.QUCA_Spring.dto.resp.RespLoginDTO;
import com.bcaf.tugasakhir.QUCA_Spring.model.MstUser;
import com.bcaf.tugasakhir.QUCA_Spring.model.Session;
import com.bcaf.tugasakhir.QUCA_Spring.repo.SessionRepo;
import com.bcaf.tugasakhir.QUCA_Spring.repo.UserRepo;
import com.bcaf.tugasakhir.QUCA_Spring.security.BcryptImpl;
import com.bcaf.tugasakhir.QUCA_Spring.util.GlobalFunction;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SessionRepo sessionRepo;

    public ResponseEntity<Object> login(ReqLoginDTO loginDTO) {
        try {
            Optional<MstUser> userOptional = userRepo.findById(loginDTO.getIdUser());
            if(userOptional.isEmpty()){
                return GlobalFunction.requestFailed(null, HttpStatus.NOT_FOUND, "User ID tidak ditemukan");
            }

            MstUser user = userOptional.get();

            RespLoginDTO respLoginDTO = new RespLoginDTO();

            boolean passwordMatches = BcryptImpl.verifyHash(loginDTO.getPassword(), user.getPassword());

            if (!passwordMatches) {
                return GlobalFunction.requestFailed(null, HttpStatus.UNAUTHORIZED, "Password salah");
            }

            String sessionId = UUID.randomUUID().toString();
            Optional<Session> existingSession = sessionRepo.findById(user.getIdUser());

            if(existingSession.isPresent()){
                Session session = existingSession.get();
                session.setSessionId(sessionId);
                session.setModifiedAt(new Date());
                sessionRepo.save(session);
            } else {
                Session newSession = new Session();
                newSession.setSessionId(sessionId);
                newSession.setIdUser(user.getIdUser());
                newSession.setModifiedAt(new Date());
                sessionRepo.save(newSession);
            }

            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Session", sessionId);

            respLoginDTO.setIdUser(user.getIdUser());
            respLoginDTO.setAkses(user.getAkses());
            respLoginDTO.setNamaLengkap(user.getNamaLengkap());

            return GlobalFunction.requestSuccess(respLoginDTO, headers, HttpStatus.OK, "Login berhasil!");

        } catch(Exception e){
            return GlobalFunction.requestFailed(null, HttpStatus.BAD_REQUEST, "Login gagal");
        }

    }

    public ResponseEntity<Object> logout(HttpServletRequest request) {
        try {
            String sessionId = request.getHeader("X-Session") != null ? request.getHeader("X-Session") : null;

            Optional<Session> existingSession = sessionRepo.findBySessionId(sessionId);
            if (sessionId == null || existingSession.isEmpty()) {
                return GlobalFunction.requestFailed(null, HttpStatus.FORBIDDEN, "Anda tidak terotorisasi");
            }
            Session session = existingSession.get();

            sessionRepo.delete(session);
            return GlobalFunction.requestSuccess(null, null, HttpStatus.OK, "Logout berhasil!");

        } catch(Exception e){
            return GlobalFunction.requestFailed(null, HttpStatus.BAD_REQUEST, "Logout gagal");
        }

    }
}
