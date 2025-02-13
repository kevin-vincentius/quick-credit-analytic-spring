package com.bcaf.tugasakhir.QUCA_Spring.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "MstShowroom")
public class MstShowroom {

    @Id
    @Column(name = "IdShowroom", nullable = false)
    private String idShowroom;

    @Column(name = "NamaShowroom", nullable = false, length = 50)
    private String namaShowroom;

    @ManyToOne
    @JoinColumn(name = "IdRegional", nullable = false)
    private MstRegional regional;

    //getter setter
    public String getIdShowroom() {
        return idShowroom;
    }

    public void setIdShowroom(String idShowroom) {
        this.idShowroom = idShowroom;
    }

    public String getNamaShowroom() {
        return namaShowroom;
    }

    public void setNamaShowroom(String namaShowroom) {
        this.namaShowroom = namaShowroom;
    }

    public MstRegional getRegional() {
        return regional;
    }

    public void setRegional(MstRegional regional) {
        this.regional = regional;
    }
}
