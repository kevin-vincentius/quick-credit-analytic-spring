package com.bcaf.tugasakhir.QUCA_Spring.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "MstScore")
public class MstScore {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IdScore", nullable = false)
    private Long idScore;

    @OneToOne
    @JoinColumn(name = "IdCabang", foreignKey = @ForeignKey(name = "fk-score-to-cabang"), nullable = false)
    private MstCabang idCabang;

    @Column(name = "BobotAngsThdPdpt", nullable = false)
    private Float bobotAngsThdPdpt;

    @Column(name = "BobotHasilGetContact", nullable = false)
    private Float bobotHasilGetContact;

    @Column(name = "BobotHasilSLIK", nullable = false)
    private Float bobotHasilSLIK;

    @Column(name = "BobotKarakter", nullable = false)
    private Float bobotKarakter;

    @Column(name = "BobotKondisiTempatTinggal", nullable = false)
    private Float bobotKondisiTempatTinggal;

    @Column(name = "BobotStatusPekerjaan", nullable = false)
    private Float bobotStatusPekerjaan;

    @Column(name = "BobotStatusTempatTinggal", nullable = false)
    private Float bobotStatusTempatTinggal;

    @Column(name = "BobotTabThdAngs", nullable = false)
    private Float bobotTabThdAngs;

    @Column(name = "CreatedAt", nullable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "ApprovedBy", nullable = false)
    private String approvedBy;

    @Column(name = "ModifiedAt")
    @UpdateTimestamp
    private Date modifiedAt;

    //getter setter


    public Long getIdScore() {
        return idScore;
    }

    public void setIdScore(Long idScore) {
        this.idScore = idScore;
    }

    public MstCabang getIdCabang() {
        return idCabang;
    }

    public void setIdCabang(MstCabang idCabang) {
        this.idCabang = idCabang;
    }

    public Float getBobotAngsThdPdpt() {
        return bobotAngsThdPdpt;
    }

    public void setBobotAngsThdPdpt(Float bobotAngsThdPdpt) {
        this.bobotAngsThdPdpt = bobotAngsThdPdpt;
    }

    public Float getBobotHasilGetContact() {
        return bobotHasilGetContact;
    }

    public void setBobotHasilGetContact(Float bobotHasilGetContact) {
        this.bobotHasilGetContact = bobotHasilGetContact;
    }

    public Float getBobotHasilSLIK() {
        return bobotHasilSLIK;
    }

    public void setBobotHasilSLIK(Float bobotHasilSLIK) {
        this.bobotHasilSLIK = bobotHasilSLIK;
    }

    public Float getBobotKarakter() {
        return bobotKarakter;
    }

    public void setBobotKarakter(Float bobotKarakter) {
        this.bobotKarakter = bobotKarakter;
    }

    public Float getBobotKondisiTempatTinggal() {
        return bobotKondisiTempatTinggal;
    }

    public void setBobotKondisiTempatTinggal(Float bobotKondisiTempatTinggal) {
        this.bobotKondisiTempatTinggal = bobotKondisiTempatTinggal;
    }

    public Float getBobotStatusPekerjaan() {
        return bobotStatusPekerjaan;
    }

    public void setBobotStatusPekerjaan(Float bobotStatusPekerjaan) {
        this.bobotStatusPekerjaan = bobotStatusPekerjaan;
    }

    public Float getBobotStatusTempatTinggal() {
        return bobotStatusTempatTinggal;
    }

    public void setBobotStatusTempatTinggal(Float bobotStatusTempatTinggal) {
        this.bobotStatusTempatTinggal = bobotStatusTempatTinggal;
    }

    public Float getBobotTabThdAngs() {
        return bobotTabThdAngs;
    }

    public void setBobotTabThdAngs(Float bobotTabThdAngs) {
        this.bobotTabThdAngs = bobotTabThdAngs;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
