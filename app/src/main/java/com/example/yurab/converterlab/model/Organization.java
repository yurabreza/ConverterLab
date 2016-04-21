
package com.example.yurab.converterlab.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;

import java.util.List;

@Table(name = "Organizations")
public class Organization extends Model {
    @Expose
    @Column(name = "idString")
    private String idString;
    @Expose
    @Column(name = "Title")
    private String title;
    //    @Expose
//    @Column(name = "Region")
//    private Region region;
//    @Expose
//    @Column(name = "OrganizationType")
//    private OrganizationType organizationType;
//    @Expose
//    @Column(name = "City")
//    private City city;
    @Expose
    @Column(name = "Phone")
    private String phone;
    @Expose
    @Column(name = "Address")
    private String address;
    @Expose
    @Column(name = "Link")
    private String link;
//    @Column(name = "Id")
//    private int id;

    public Organization() {
        super();
    }

    private List<OrganizationCurrency> mCurrencies;


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

//    public Region getRegion() {
//        return this.region;
//    }
//
//    public void setRegion(Region _region) {
//        this.region = _region;
//    }
//
//    public City getCity() {
//        return this.city;
//    }
//
//    public void setCity(City _city) {
//        this.city = _city;
//    }
//
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


//    public OrganizationType getOrganizationType() {
//        return this.organizationType;
//    }
//
//    public void setOrganizationType(OrganizationType organizationType) {
//        this.organizationType = organizationType;
//    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String _link) {
        this.link = _link;
    }

    public List<OrganizationCurrency> getCurrencies() {
        return this.mCurrencies;
    }

    public void setCurrencies(List<OrganizationCurrency> _currencies) {
        this.mCurrencies = _currencies;
    }


//    public void setId(int id) {
//        this.id = id;
//    }
}
