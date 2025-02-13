package com.bcaf.tugasakhir.QUCA_Spring.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name =  "FormQUCA")
public class FormQUCA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdFormQUCA")
    private Long idFormQUCA;

    @OneToOne
    @JoinColumn(name = "MID", foreignKey = @ForeignKey(name = "fk-quca-to-mid"), nullable = false)
    private MstMobileEntry MID;

    @ManyToOne
    @JoinColumn(name = "IdScore", foreignKey = @ForeignKey(name = "fk-quca-to-score"), nullable = false)
    private MstScore idScore;

    @Column(name = "IdMarketingOfficer", nullable = false)
    private String idMarketingOfficer;

    @Column(name = "StatusQUCA", nullable = false)
    private String statusQUCA;
    // ON REVIEW, NEED REVISION, APPROVED
    // kalau direject BM, BM tambah notes dan reject form, status -> need revision (sama aja kek reject), dan cmo perlu perlu edit lagi
    // kalau diapprove BM, status -> approved

    @Column(name = "TabThdAngs", nullable = false)
    private Short tabThdAngs;

    @Column(name = "AngsThdPdpt", nullable = false)
    private Short angsThdPdpt;

    @Column(name = "StatusPekerjaan", nullable = false)
    private Short statusPekerjaan;

    @Column(name = "StatusTempatTinggal", nullable = false)
    private Short statusTempatTinggal;

    @Column(name = "KondisiTempatTinggal", nullable = false)
    private Short kondisiTempatTinggal;

    @Column(name = "KarakterDiLingkungan", nullable = false)
    private Short karakterDiLingkungan;

    @Column(name = "HasilGetContact", nullable = false)
    private Short hasilGetContact;

    @Column(name = "HasilSLIK", nullable = false)
    private Short hasilSLIK;

    @Column(name = "TotalScore")
    private Float totalScore;

    @Column(name = "Notes")
    private String notes;

    @Column(name = "ApprovedBy")
    private String approvedBy;

    @Column(name = "CreatedAt")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "ModifiedAt")
    @UpdateTimestamp
    private Date modifiedAt;


    //getter setter
    public Long getIdFormQUCA() {
        return idFormQUCA;
    }

    public void setIdFormQUCA(Long idFormQUCA) {
        this.idFormQUCA = idFormQUCA;
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

    public MstMobileEntry getMID() {
        return MID;
    }

    public void setMID(MstMobileEntry MID) {
        this.MID = MID;
    }

    public String getStatusQUCA() {
        return statusQUCA;
    }

    public void setStatusQUCA(String statusQUCA) {
        this.statusQUCA = statusQUCA;
    }

    public String getNotes() {

        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

    public Float getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Float totalScore) {
        this.totalScore = totalScore;
    }

    public MstScore getIdScore() {
        return idScore;
    }

    public void setIdScore(MstScore idScore) {
        this.idScore = idScore;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getIdMarketingOfficer() {
        return idMarketingOfficer;
    }

    public void setIdMarketingOfficer(String idMarketingOfficer) {
        this.idMarketingOfficer = idMarketingOfficer;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }


}
