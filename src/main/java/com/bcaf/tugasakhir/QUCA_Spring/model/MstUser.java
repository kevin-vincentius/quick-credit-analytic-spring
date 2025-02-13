package com.bcaf.tugasakhir.QUCA_Spring.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "MstUser")
public class MstUser {
    @Id // --> NIP
    @Column(name = "IdUser", length = 9, nullable = false)
    private String idUser;

    @ManyToOne
    @JoinColumn(name = "IdAkses", foreignKey = @ForeignKey(name = "fk-user-to-akses"), nullable = false)
    private MstAkses akses;

    @Column(name = "NamaLengkap", nullable = false)
    private String namaLengkap;

    @Column(name = "Password", nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(
            name = "MstUser_Cabang",
            joinColumns = @JoinColumn(name = "IdUser"),
            inverseJoinColumns = @JoinColumn(name = "IdCabang")
    )
    @JsonManagedReference
    private Set<MstCabang> listCabang;

    @Column(name = "CreatedAt", updatable = false)
    @CreationTimestamp
    private Date createdAt;

    //getter setter
    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public MstAkses getAkses() {
        return akses;
    }

    public void setAkses(MstAkses akses) {
        this.akses = akses;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Set<MstCabang> getListCabang() {
        return listCabang;
    }

    public void setListCabang(Set<MstCabang> listCabang) {
        this.listCabang = listCabang;
    }
}
