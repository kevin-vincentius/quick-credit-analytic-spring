package com.bcaf.tugasakhir.QUCA_Spring.dto.req;

public class ReqUpdateFormQUCADTO {
    private Long MID;
    private Short tabThdAngs;
    private Short angsThdPdpt;
    private Short statusPekerjaan;
    private Short statusTempatTinggal;
    private Short kondisiTempatTinggal;
    private Short karakterDiLingkungan;
    private Short hasilGetContact;
    private Short hasilSLIK;

    public Long getMID() {
        return MID;
    }

    public void setMID(Long MID) {
        this.MID = MID;
    }

    public Short getTabThdAngs() {
        return tabThdAngs;
    }

    public void setTabThdAngs(Short tabThdAngs) {
        this.tabThdAngs = tabThdAngs;
    }

    public Short getAngsThdPdpt() {
        return angsThdPdpt;
    }

    public void setAngsThdPdpt(Short angsThdPdpt) {
        this.angsThdPdpt = angsThdPdpt;
    }

    public Short getStatusPekerjaan() {
        return statusPekerjaan;
    }

    public void setStatusPekerjaan(Short statusPekerjaan) {
        this.statusPekerjaan = statusPekerjaan;
    }

    public Short getStatusTempatTinggal() {
        return statusTempatTinggal;
    }

    public void setStatusTempatTinggal(Short statusTempatTinggal) {
        this.statusTempatTinggal = statusTempatTinggal;
    }

    public Short getKondisiTempatTinggal() {
        return kondisiTempatTinggal;
    }

    public void setKondisiTempatTinggal(Short kondisiTempatTinggal) {
        this.kondisiTempatTinggal = kondisiTempatTinggal;
    }

    public Short getKarakterDiLingkungan() {
        return karakterDiLingkungan;
    }

    public void setKarakterDiLingkungan(Short karakterDiLingkungan) {
        this.karakterDiLingkungan = karakterDiLingkungan;
    }

    public Short getHasilGetContact() {
        return hasilGetContact;
    }

    public void setHasilGetContact(Short hasilGetContact) {
        this.hasilGetContact = hasilGetContact;
    }

    public Short getHasilSLIK() {
        return hasilSLIK;
    }

    public void setHasilSLIK(Short hasilSLIK) {
        this.hasilSLIK = hasilSLIK;
    }
}
