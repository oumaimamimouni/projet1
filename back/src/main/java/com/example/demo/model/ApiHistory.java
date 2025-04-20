package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ACM_TRANSVERS_HISTORY")
public class ApiHistory {

    @Id
    @JsonProperty("ID_ACM_TRANSVERS_HISTORY")
    @Column(name = "ID_ACM_TRANSVERS_HISTORY")
    private Long ID_ACM_TRANSVERS_HISTORY;

    @JsonProperty("OBJECT_VALUE")
    @Column(name = "OBJECT_VALUE")
    private String OBJECT_VALUE;

    @JsonProperty("METHODE")
    @Column(name = "METHODE")
    private String METHODE;

    @JsonProperty("URI")
    @Column(name = "URI")
    private String URI;

    @JsonProperty("REPONSE_STATUS")
    @Column(name = "REPONSE_STATUS")
    private String REPONSE_STATUS;

    @JsonProperty("REQUEST_VALUE")
    @Column(name = "REQUEST_VALUE", columnDefinition = "CLOB")
    private String REQUEST_VALUE;

    @JsonProperty("RESPONSE_VALUE")
    @Column(name = "RESPONSE_VALUE", columnDefinition = "CLOB")
    private String RESPONSE_VALUE;

    @JsonProperty("ACM_ENABLED")
    @Column(name = "ACM_ENABLED")
    private boolean ACM_ENABLED;

    @JsonProperty("DATE_INSERTION")
    @Column(name = "DATE_INSERTION")
    private Date DATE_INSERTION;

    @JsonProperty("INSERT_BY")
    @Column(name = "INSERT_BY")
    private String INSERT_BY;

    @JsonProperty("DATE_LAST_UPDATE")
    @Column(name = "DATE_LAST_UPDATE")
    private Date DATE_LAST_UPDATE;

    @JsonProperty("UPDATED_BY")
    @Column(name = "UPDATED_BY")
    private String UPDATED_BY;

    @JsonProperty("ACM_VERSION")
    @Column(name = "ACM_VERSION")
    private Integer ACM_VERSION;

    // --- Getters et Setters ---

    public Long getID_ACM_TRANSVERS_HISTORY() {
        return ID_ACM_TRANSVERS_HISTORY;
    }

    public void setID_ACM_TRANSVERS_HISTORY(Long ID_ACM_TRANSVERS_HISTORY) {
        this.ID_ACM_TRANSVERS_HISTORY = ID_ACM_TRANSVERS_HISTORY;
    }

    public String getOBJECT_VALUE() {
        return OBJECT_VALUE;
    }

    public void setOBJECT_VALUE(String OBJECT_VALUE) {
        this.OBJECT_VALUE = OBJECT_VALUE;
    }

    public String getMETHODE() {
        return METHODE;
    }

    public void setMETHODE(String METHODE) {
        this.METHODE = METHODE;
    }

    public String getURI() {
        return URI;
    }

    public void setURI(String URI) {
        this.URI = URI;
    }

    public String getREPONSE_STATUS() {
        return REPONSE_STATUS;
    }

    public void setREPONSE_STATUS(String REPONSE_STATUS) {
        this.REPONSE_STATUS = REPONSE_STATUS;
    }

    public String getREQUEST_VALUE() {
        return REQUEST_VALUE;
    }

    public void setREQUEST_VALUE(String REQUEST_VALUE) {
        this.REQUEST_VALUE = REQUEST_VALUE;
    }

    public String getRESPONSE_VALUE() {
        return RESPONSE_VALUE;
    }

    public void setRESPONSE_VALUE(String RESPONSE_VALUE) {
        this.RESPONSE_VALUE = RESPONSE_VALUE;
    }

    public boolean isACM_ENABLED() {
        return ACM_ENABLED;
    }

    public void setACM_ENABLED(boolean ACM_ENABLED) {
        this.ACM_ENABLED = ACM_ENABLED;
    }

    public Date getDATE_INSERTION() {
        return DATE_INSERTION;
    }

    public void setDATE_INSERTION(Date DATE_INSERTION) {
        this.DATE_INSERTION = DATE_INSERTION;
    }

    public String getINSERT_BY() {
        return INSERT_BY;
    }

    public void setINSERT_BY(String INSERT_BY) {
        this.INSERT_BY = INSERT_BY;
    }

    public Date getDATE_LAST_UPDATE() {
        return DATE_LAST_UPDATE;
    }

    public void setDATE_LAST_UPDATE(Date DATE_LAST_UPDATE) {
        this.DATE_LAST_UPDATE = DATE_LAST_UPDATE;
    }

    public String getUPDATED_BY() {
        return UPDATED_BY;
    }

    public void setUPDATED_BY(String UPDATED_BY) {
        this.UPDATED_BY = UPDATED_BY;
    }

    public Integer getACM_VERSION() {
        return ACM_VERSION;
    }

    public void setACM_VERSION(Integer ACM_VERSION) {
        this.ACM_VERSION = ACM_VERSION;
    }
}
