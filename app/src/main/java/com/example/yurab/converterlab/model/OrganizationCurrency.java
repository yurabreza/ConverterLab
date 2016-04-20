package com.example.yurab.converterlab.model;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;


@Table(name = "OrganizationCurrencies",id ="_id")
public class OrganizationCurrency  extends Model {
   @Column(name = "id")
    private long id;
    @Column(name = "Organization")
    private Organization organization;
    @Column(name = "Currency")
    private Currency currency;
    @Column(name = "Ask")
    private String ask;
    @Column(name = "Bid")
    private String bid;
    @Column(name = "OldAsk")
    private String oldAsk;
    @Column(name = "OldBid")
    private String oldBid;

    public OrganizationCurrency(){
        super();
    }


    public void setId(long _id) {
        this.id = _id;
    }

    public Organization getOrganization() {
        return this.organization;
    }

    public void setOrganization(Organization _organization) {
        this.organization = _organization;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public void setCurrency(Currency _currency) {
        this.currency = _currency;
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
}
