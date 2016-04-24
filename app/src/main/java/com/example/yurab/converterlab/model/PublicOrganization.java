
package com.example.yurab.converterlab.model;


import com.google.gson.annotations.Expose;

import java.util.HashMap;


public final class PublicOrganization {

    @Expose
    private String id;
    @Expose
    private String oldId;
    @Expose
    private String orgType;
    @Expose
    private String title;
    @Expose
    private String regionId;
    @Expose
    private String cityId;
    @Expose
    private String phone;
    @Expose
    private String address;
    @Expose
    private String link;
    @Expose
    private HashMap<String,HashMap<String,String>> currencies;

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The oldId
     */
    public String getOldId() {
        return oldId;
    }

    /**
     * 
     * @param oldId
     *     The oldId
     */
    public void setOldId(String oldId) {
        this.oldId = oldId;
    }

    /**
     * 
     * @return
     *     The orgType
     */
    public String getOrgType() {
        return orgType;
    }

    /**
     * 
     * @param orgType
     *     The orgType
     */
    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The regionId
     */
    public String getRegionId() {
        return regionId;
    }

    /**
     * 
     * @param regionId
     *     The regionId
     */
    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    /**
     * 
     * @return
     *     The cityId
     */
    public String getCityId() {
        return cityId;
    }

    /**
     * 
     * @param cityId
     *     The cityId
     */
    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    /**
     * 
     * @return
     *     The phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 
     * @param phone
     *     The phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 
     * @return
     *     The address
     */
    public String getAddress() {
        return address;
    }

    /**
     * 
     * @param address
     *     The address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 
     * @return
     *     The link
     */
    public String getLink() {
        return link;
    }

    /**
     * 
     * @param link
     *     The link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * 
     * @return
     *     The currencies
     */
    public HashMap<String,HashMap<String,String>> getCurrencies() {
        return currencies;
    }

    /**
     * 
     * @param currencies
     *     The currencies
     */
    public void setCurrencies(HashMap<String,HashMap<String,String>> currencies) {
        this.currencies = currencies;
    }

}
