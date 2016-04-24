
package com.example.yurab.converterlab.model;



import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public final class PublicCurrency {

    @Expose
    private String sourceId;
    @Expose
    private String date;
    @Expose
    private List<PublicOrganization> organizations = new ArrayList<>();
    @Expose
    private HashMap<String,String> orgTypes;
    @Expose
    private HashMap<String,String> currencies;
    @Expose
    private HashMap<String,String> regions;
    @Expose
    private HashMap<String,String> cities;

    /**
     * @return The sourceId
     */
    public String getSourceId() {
        return sourceId;
    }

    /**
     * @param sourceId The sourceId
     */
    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    /**
     * @return The date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return The organizations
     */
    public List<PublicOrganization> getOrganizations() {
        return organizations;
    }

    /**
     * @param organizations The organizations
     */
    public void setOrganizations(List<PublicOrganization> organizations) {
        this.organizations = organizations;
    }

    /**
     * @return The orgTypes
     */
    public HashMap<String,String> getOrgTypes() {
        return orgTypes;
    }

    /**
     * @param orgTypes The orgTypes
     */
    public void setOrgTypes(HashMap<String,String> orgTypes) {
        this.orgTypes = orgTypes;
    }

    /**
     * @return The currencies
     */
    public HashMap<String,String> getCurrencies() {
        return currencies;
    }

    /**
     * @param currencies The currencies
     */
    public void setCurrencies(HashMap<String,String> currencies) {
        this.currencies = currencies;
    }

    /**
     * @return The regions
     */
    public HashMap<String,String> getRegions() {
        return regions;
    }

    /**
     * @param regions The regions
     */
    public void setRegions(HashMap<String,String> regions) {
        this.regions = regions;
    }

    /**
     * @return The cities
     */
    public HashMap<String,String> getCities() {
        return cities;
    }

    /**
     * @param cities The cities
     */
    public void setCities(HashMap<String,String> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return "PublicCurrency{" +
                "sourceId='" + sourceId + '\'' +
                ", date='" + date + '\'' +
                ", organizations=" + organizations +
                ", orgTypes=" + orgTypes +
                ", currencies=" + currencies +
                ", regions=" + regions +
                ", cities=" + cities +
                '}';
    }
}
