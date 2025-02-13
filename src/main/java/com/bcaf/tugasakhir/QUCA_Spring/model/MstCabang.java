package com.bcaf.tugasakhir.QUCA_Spring.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "MstCabang")
public class MstCabang {

    @Id
    @Column(name = "IdCabang", nullable = false)
    private Short idCabang;

    @Column(name = "NamaCabang", nullable = false, length = 50)
    private String namaCabang;

    @ManyToOne
    @JoinColumn(name = "IdRegional", foreignKey = @ForeignKey(name = "fk-cabang-to-regional"), nullable = false)
    private MstRegional idRegional;

    @ManyToMany(mappedBy = "listCabang")
    @JsonBackReference
    private Set<MstUser> users;

    public Short getIdCabang() {
        return idCabang;
    }

    public void setIdCabang(Short idCabang) {
        this.idCabang = idCabang;
    }

    public String getNamaCabang() {
        return namaCabang;
    }

    public void setNamaCabang(String namaCabang) {
        this.namaCabang = namaCabang;
    }

    public MstRegional getIdRegional() {
        return idRegional;
    }

    public void setIdRegional(MstRegional idRegional) {
        this.idRegional = idRegional;
    }

    public Set<MstUser> getUsers() {
        return users;
    }

    public void setUsers(Set<MstUser> users) {
        this.users = users;
    }
}
