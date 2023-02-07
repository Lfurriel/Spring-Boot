package com.furriel.springboot.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "log")
public class Log implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idCore;
    private Instant moment;
    private String endpoint;
    private String integracao;
    private boolean erro;
    private String message;
    private String status;
    private String json;

    public Log(){
    }

    public Log(Long id, Long idCore, Instant moment, String endpoint, String integracao, boolean erro, String message, String status, String json) {
        this.id = id;
        this.idCore = idCore;
        this.moment = moment;
        this.endpoint = endpoint;
        this.integracao = integracao;
        this.erro = erro;
        this.message = message;
        this.message = message;
        this.json = json;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCore() {
        return idCore;
    }

    public void setIdCore(Long idCore) {
        this.idCore = idCore;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getIntegracao() {
        return integracao;
    }

    public void setIntegracao(String integracao) {
        this.integracao = integracao;
    }

    public boolean isErro() {
        return erro;
    }

    public void setErro(boolean erro) {
        this.erro = erro;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Log log = (Log) o;
        return id.equals(log.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
