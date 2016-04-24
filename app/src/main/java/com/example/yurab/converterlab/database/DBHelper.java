package com.example.yurab.converterlab.database;

import android.os.Handler;
import android.util.Log;

import com.activeandroid.query.Select;
import com.example.yurab.converterlab.Constants;
import com.example.yurab.converterlab.model.CurrencyOrg;
import com.example.yurab.converterlab.model.Organization;
import com.example.yurab.converterlab.model.PublicCurrency;
import com.example.yurab.converterlab.model.PublicOrganization;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Yura Breza
 * Date  24.04.2016.
 */
public final class DBHelper {
    private List<Organization> organizationList;
    private boolean isIn;
    private final String st = "------------";
    private final String TAG = "DBHelper";

    public void writeDB(PublicCurrency body, Handler.Callback callback) {
        organizationList = getOrgzList();
        showStatus();

        for (PublicOrganization pOrganization : body.getOrganizations()) {

            isIn = false;

            if (organizationList.size() > 0) {
                for (Organization organization : organizationList)
                    if (pOrganization.getId().equals(organization.getIdString())) {
                        //equal Bank found in DataBase updating only currency table
                        isIn = true;
                        updateCurOrgzList(pOrganization);
                    }
            }


            //creating new Organization and saving it
            if (!isIn) {
                saveNewOrg(body, pOrganization);
                saveNewCurrencyOrg(pOrganization, body);
            }


        }
        showStatus();
        callback.handleMessage(null);
    }

    private void showStatus() {
        Log.d(TAG, st + st + "showStatus: " + st + getCurrzAll().size());
        Log.d(TAG, st + st + "showStatus: " + st + getOrgzList().size());
    }

    private void updateCurOrgzList(PublicOrganization pOrganization) {
        List<CurrencyOrg> currencyOrgList;
        //getting all currencies of current Organization
        HashMap<String, HashMap<String, String>> hMap = pOrganization.getCurrencies();
        currencyOrgList = getCurrencyOrgList(pOrganization.getId());
        Log.d(TAG, "currencyOrgListSize: " + st + currencyOrgList.size());
        //going on each currency id string(s)
        for (String s : hMap.keySet()) {
            HashMap<String, String> hashMap = hMap.get(s);

            for (CurrencyOrg currencyOrg : currencyOrgList) {

                if (currencyOrg.getCurrencyId().equals(s)) {

                    currencyOrg.setOldAsk(currencyOrg.getAsk());
                    currencyOrg.setOldBid(currencyOrg.getBid());
                    currencyOrg.setAsk(hashMap.get(Constants.ASK));

                    currencyOrg.setBid(hashMap.get(Constants.BID));
                    currencyOrg.save();
                    Log.d(TAG, "update currenncy: " + st + currencyOrg.getStringData());
                }

            }


        }

    }

    private void saveNewCurrencyOrg(PublicOrganization pOrganization, PublicCurrency body) {

        HashMap<String, HashMap<String, String>> hMap = pOrganization.getCurrencies();

        for (String s : hMap.keySet()) {
            HashMap<String, String> hashMap = hMap.get(s);
            CurrencyOrg cOrg = new CurrencyOrg();
            cOrg.setAsk(hashMap.get(Constants.ASK));
            cOrg.setBid(hashMap.get(Constants.BID));
            cOrg.setOrganizationId(pOrganization.getId());
            cOrg.setCurrencyId(s);
            cOrg.setCurrencyName(body.getCurrencies().get(s));
            cOrg.save();
        }

    }

    private void saveNewOrg(PublicCurrency body, PublicOrganization pOrganization) {
        Organization o = new Organization();
        o.setAddress(pOrganization.getAddress());
        o.setPhone(pOrganization.getPhone());
        o.setIdString(pOrganization.getId());
        o.setTitle(pOrganization.getTitle());
        o.setLink(pOrganization.getLink());
        o.setCity(body.getCities().get(pOrganization.getCityId()));
        o.setOrganizationType(body.getOrgTypes().get(pOrganization.getOrgType()));
        o.setRegion(body.getRegions().get(pOrganization.getRegionId()));
        o.save();
    }

    public List<CurrencyOrg> getCurrencyOrgList(String id) {
        return new Select()
                .from(CurrencyOrg.class)
                .where(Constants.KEY_ORG_ID, id)
                .execute();
    }

    public List<Organization> getOrgzList() {
        return new Select()
                .from(Organization.class)
                .execute();
    }

    public List<CurrencyOrg> getCurrzAll() {
        List<CurrencyOrg> list = new Select()
                .from(CurrencyOrg.class)
                .execute();
        return list;

    }
}
