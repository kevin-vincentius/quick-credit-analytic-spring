package com.bcaf.tugasakhir.QUCA_Spring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "MstAkses")
public class MstAkses {

    @Id
    @Column(name = "IdAkses", nullable = false)
    private Short idAkses;

    @Column(name = "LevelAkses", nullable = false)
    private String levelAkses;

    //getter setter
    public Short getAksesId() {
        return idAkses;
    }

    public void setAksesId(Short aksesId) {
        this.idAkses = aksesId;
    }

    public String getLevelAkses() {
        return levelAkses;
    }

    public void setLevelAkses(String levelAkses) {
        this.levelAkses = levelAkses;
    }
}
