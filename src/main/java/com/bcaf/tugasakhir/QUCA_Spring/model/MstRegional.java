package com.bcaf.tugasakhir.QUCA_Spring.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "MstRegional")
public class MstRegional {

    @Id
    @Column(name = "IdRegional", length = 8, nullable = false)
    private Short idRegional;

    @Column(name = "NamaRegional", unique = true, nullable = false)
    private String namaRegional;

    public Short getIdRegional() {
        return idRegional;
    }

    public void setIdRegional(Short idRegional) {
        this.idRegional = idRegional;
    }

    public String getNamaRegional() {
        return namaRegional;
    }

    public void setNamaRegional(String namaRegional) {
        this.namaRegional = namaRegional;
    }
}
