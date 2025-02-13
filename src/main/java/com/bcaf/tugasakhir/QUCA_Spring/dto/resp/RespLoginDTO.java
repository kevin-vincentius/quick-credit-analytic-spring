package com.bcaf.tugasakhir.QUCA_Spring.dto.resp;

import com.bcaf.tugasakhir.QUCA_Spring.model.MstAkses;

public class RespLoginDTO {
    private String idUser;
    private String namaLengkap;
    private MstAkses akses;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public MstAkses getAkses() {
        return akses;
    }

    public void setAkses(MstAkses akses) {
        this.akses = akses;
    }
}
