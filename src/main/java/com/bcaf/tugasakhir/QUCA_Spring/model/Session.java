package com.bcaf.tugasakhir.QUCA_Spring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "Session")
public class Session {

    @Id
    @Column(name = "IdUser", nullable = false)
    private String idUser;

    @Column(name = "SessionId", length = 255, nullable = false)
    private String sessionId;

    @CreationTimestamp
    @Column(name = "CreatedAt", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "ModifiedAt")
    private Date modifiedAt;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
