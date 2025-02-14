package com.bcaf.tugasakhir.QUCA_Spring.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "FormPerubahanBobotScore")
public class FormPerubahanBobotScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdFormPerubahanBobotScore")
    private Long idFormPerubahanBobotScore;

    @ManyToOne
    @JoinColumn(name = "IdCabang", foreignKey = @ForeignKey(name = "fk-formperubahan-to-cabang"), nullable = false)
    private MstCabang idCabang;

    @Column(name = "NewBobotTabThdAngs", nullable = false)
    private Float newbobotTabThdAngs;

    @Column(name = "NewBobotAngsThdPdpt", nullable = false)
    private Float newBobotAngsThdPdpt;

    @Column(name = "NewBobotStatusPekerjaan", nullable = false)
    private Float newBobotStatusPekerjaan;

    @Column(name = "NewBobotStatusTempatTinggal", nullable = false)
    private Float newBobotStatusTempatTinggal;

    @Column(name = "NewBobotKondisiTempatTinggal", nullable = false)
    private Float newBobotKondisiTempatTinggal;

    @Column(name = "NewBobotKarakter", nullable = false)
    private Float newBobotKarakter;

    @Column(name = "NewBobotHasilGetContact", nullable = false)
    private Float newBobotHasilGetContact;

    @Column(name = "NewBobotHasilSLIK", nullable = false)
    private Float newBobotHasilSLIK;

    @Column(name = "CreatedAt", nullable = false)
    @CreationTimestamp
    private LocalDate createdAt;

    @Column(name = "CreatedBy", nullable = false)
    private String createdBy;

    @Column(name = "ModifiedBy")
    private String modifiedBy;

    @Column(name = "StatusFormPerubahan", nullable = false)
    private String statusFormPerubahan; // ON REVIEW RM, ON REVIEW DD, APPROVED, REJECTED

    //getter setter
    public Long getIdFormPerubahanBobotScore() {
        return idFormPerubahanBobotScore;
    }

    public void setIdFormPerubahanBobotScore(Long idFormPerubahanBobotScore) {
        this.idFormPerubahanBobotScore = idFormPerubahanBobotScore;
    }

    public MstCabang getIdCabang() {
        return idCabang;
    }

    public void setIdCabang(MstCabang idCabang) {
        this.idCabang = idCabang;
    }

    public Float getNewbobotTabThdAngs() {
        return newbobotTabThdAngs;
    }

    public void setNewbobotTabThdAngs(Float newbobotTabThdAngs) {
        this.newbobotTabThdAngs = newbobotTabThdAngs;
    }

    public Float getNewBobotAngsThdPdpt() {
        return newBobotAngsThdPdpt;
    }

    public void setNewBobotAngsThdPdpt(Float newBobotAngsThdPdpt) {
        this.newBobotAngsThdPdpt = newBobotAngsThdPdpt;
    }

    public Float getNewBobotStatusPekerjaan() {
        return newBobotStatusPekerjaan;
    }

    public void setNewBobotStatusPekerjaan(Float newBobotStatusPekerjaan) {
        this.newBobotStatusPekerjaan = newBobotStatusPekerjaan;
    }

    public Float getNewBobotStatusTempatTinggal() {
        return newBobotStatusTempatTinggal;
    }

    public void setNewBobotStatusTempatTinggal(Float newBobotStatusTempatTinggal) {
        this.newBobotStatusTempatTinggal = newBobotStatusTempatTinggal;
    }

    public Float getNewBobotKondisiTempatTinggal() {
        return newBobotKondisiTempatTinggal;
    }

    public void setNewBobotKondisiTempatTinggal(Float newBobotKondisiTempatTinggal) {
        this.newBobotKondisiTempatTinggal = newBobotKondisiTempatTinggal;
    }

    public Float getNewBobotKarakter() {
        return newBobotKarakter;
    }

    public void setNewBobotKarakter(Float newBobotKarakter) {
        this.newBobotKarakter = newBobotKarakter;
    }

    public Float getNewBobotHasilGetContact() {
        return newBobotHasilGetContact;
    }

    public void setNewBobotHasilGetContact(Float newBobotHasilGetContact) {
        this.newBobotHasilGetContact = newBobotHasilGetContact;
    }

    public Float getNewBobotHasilSLIK() {
        return newBobotHasilSLIK;
    }

    public void setNewBobotHasilSLIK(Float newBobotHasilSLIK) {
        this.newBobotHasilSLIK = newBobotHasilSLIK;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getStatusFormPerubahan() {
        return statusFormPerubahan;
    }

    public void setStatusFormPerubahan(String statusFormPerubahan) {
        this.statusFormPerubahan = statusFormPerubahan;
    }
}