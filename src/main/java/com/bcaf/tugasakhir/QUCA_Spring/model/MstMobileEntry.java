package com.bcaf.tugasakhir.QUCA_Spring.model;

import jakarta.persistence.*;

@Entity
@Table(name = "MstMobileEntry")
public class MstMobileEntry {

    @Id
    @Column(name = "MID", nullable = false)
    private Long MID;

    @Column(name = "NamaKonsumen", nullable = false)
    private String namaKonsumen;

    @ManyToOne
    @JoinColumn(name = "IdCabang", foreignKey = @ForeignKey(name = "fk-mid-to-cabang"), nullable = false)
    private MstCabang idCabang;

    @ManyToOne
    @JoinColumn(name = "IdShowroom", foreignKey = @ForeignKey(name = "fk-mid-to-showroom"), nullable = false)
    private MstShowroom idShowroom;

    @ManyToOne
    @JoinColumn(name = "IdMarketingOfficer", foreignKey = @ForeignKey(name = "fk-mid-to-user"), nullable = false)
    private MstUser idMarketingOfficer; // --> NIP CMO

    @Column(name = "Pendapatan", nullable = false)
    private Double pendapatan;

    @Column(name = "Angsuran", nullable = false)
    private Double angsuran;

    @Column(name = "Tabungan", nullable = false)
    private Double tabungan;

    @Column(name = "DomisiliKonsumen", nullable = false)
    private String domisiliKonsumen;

    @Column(name = "Zipcode", nullable = false)
    private Long zipcode;

    public Long getMID() {
        return MID;
    }

    public void setMID(Long MID) {
        this.MID = MID;
    }

    public Long getZipcode() {
        return zipcode;
    }

    public void setZipcode(Long zipcode) {
        this.zipcode = zipcode;
    }

    public String getDomisiliKonsumen() {
        return domisiliKonsumen;
    }

    public void setDomisiliKonsumen(String domisiliKonsumen) {
        this.domisiliKonsumen = domisiliKonsumen;
    }

    public String getNamaKonsumen() {
        return namaKonsumen;
    }

    public void setNamaKonsumen(String namaKonsumen) {
        this.namaKonsumen = namaKonsumen;
    }

    public MstCabang getIdCabang() {
        return idCabang;
    }

    public void setIdCabang(MstCabang idCabang) {
        this.idCabang = idCabang;
    }

    public MstShowroom getIdShowroom() {
        return idShowroom;
    }

    public void setIdShowroom(MstShowroom idShowroom) {
        this.idShowroom = idShowroom;
    }

    public MstUser getIdMarketingOfficer() {
        return idMarketingOfficer;
    }

    public void setIdMarketingOfficer(MstUser idMarketingOfficer) {
        this.idMarketingOfficer = idMarketingOfficer;
    }

    public Double getPendapatan() {
        return pendapatan;
    }

    public void setPendapatan(Double pendapatan) {
        this.pendapatan = pendapatan;
    }

    public Double getAngsuran() {
        return angsuran;
    }

    public void setAngsuran(Double angsuran) {
        this.angsuran = angsuran;
    }

    public Double getTabungan() {
        return tabungan;
    }

    public void setTabungan(Double tabungan) {
        this.tabungan = tabungan;
    }
}
