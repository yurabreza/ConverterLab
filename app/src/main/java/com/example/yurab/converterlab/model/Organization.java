
package com.example.yurab.converterlab.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;

import java.util.List;

@Table(name = "Organizations")
public final class Organization extends Model {
    @Expose
    @Column(name = "idString")
    private String idString;
    @Expose
    @Column(name = "Title")
    private String title;
    @Expose
    @Column(name = "Region")
    private String region;
    @Expose
    @Column(name = "OrganizationType")
    private String organizationType;
    @Expose
    @Column(name = "City")
    private String city;
    @Expose
    @Column(name = "Phone")
    private String phone;
    @Expose
    @Column(name = "Address")
    private String address;
    @Expose
    @Column(name = "Link")
    private String link;


    public Organization() {
        super();
    }

    private List<CurrencyOrg> mCurrencies;


    public void setIdString(String id) {
        this.idString = id;
    }

    public String getIdString() {
        return this.idString;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;

    }



    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String _phone) {
        this.phone = _phone;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String _address) {
        this.address = _address;
    }



    public String getLink() {
        return this.link;
    }

    public void setLink(String _link) {
        this.link = _link;
    }

    public List<CurrencyOrg> getCurrencies() {
        return this.mCurrencies;
    }

    public void setCurrencies(List<CurrencyOrg> _currencies) {
        this.mCurrencies = _currencies;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(String organizationType) {
        this.organizationType = organizationType;
    }


//    public void setId(int id) {
//        this.id = id;
//    }
}
