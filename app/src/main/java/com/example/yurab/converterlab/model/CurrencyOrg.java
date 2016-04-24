package com.example.yurab.converterlab.model;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;


@Table(name = "OrganizationCurrencies")
public class CurrencyOrg extends Model {
    @Column(name = "CurrencyId")
    private String currencyId;
    @Column(name = "CurrencyName")
    private String currencyName;
    @Column(name = "OrganizationId")
    private String organizationId;

    @Column(name = "Ask")
    private String ask;
    @Column(name = "Bid")
    private String bid;
    @Column(name = "OldAsk")
    private String oldAsk;
    @Column(name = "OldBid")
    private String oldBid;



    public CurrencyOrg() {
        super();
    }

    public String getStringData(){
        String s = "---";
        return s+getCurrencyId()+" ask "+getAsk()+" oldAsk "+getOldAsk();
    }
    public String getOrganizationId() {
        return this.organizationId;
    }

    public void setOrganizationId(String _organization) {
        this.organizationId = _organization;
    }


    public void setAsk(String _ask) {
        this.ask = _ask;
    }

    public String getAsk() {
        return ask;
    }

    public void setBid(String _bid) {
        this.bid = _bid;
    }

    public String getBid() {
        return bid;
    }

    public void setOldAsk(String _oldAsk) {
        this.oldAsk = _oldAsk;
    }

    public String getOldAsk() {
        return oldAsk;
    }

    public void setOldBid(String _oldBid) {
        this.oldBid = _oldBid;
    }

    public String getOldBid() {
        return oldBid;
    }


    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }
}
