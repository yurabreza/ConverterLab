package com.example.yurab.converterlab.fragments.share_dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yurab.converterlab.R;
import com.example.yurab.converterlab.constants.Constants;
import com.example.yurab.converterlab.database.DBHelper;
import com.example.yurab.converterlab.model.CurrencyOrg;
import com.example.yurab.converterlab.model.Organization;

import java.util.List;

/**
 * Created by Yura Breza
 * Date  26.04.2016.
 */
public class ShareDialogFragment extends android.support.v4.app.DialogFragment implements View.OnClickListener {
    private long id;
    private Organization organization;
    private List<CurrencyOrg> data;
    private ViewGroup container;
    private TextView tvTitle, tvRegion, tvCity;
    private View root;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        id = getArguments().getLong(Constants.ID_KEY);
        Log.d("ShareDialogFragment", "onCreateView: " + id);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.container = container;
        root = inflater.inflate(R.layout.share_content, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DBHelper dbHelper = new DBHelper();
        organization = dbHelper.getOrgById(id);
        data = dbHelper.getCurrencyOrgList(organization.getIdString());
        init();


    }

    private void init() {
        tvTitle = (TextView) root.findViewById(R.id.tv_title_SC);
        tvRegion = (TextView) root.findViewById(R.id.tv_region_SC);
        tvCity = (TextView) root.findViewById(R.id.tv_city_SC);
        tvTitle.setText(organization.getTitle());
        tvRegion.setText(organization.getRegion());
        tvCity.setText(organization.getCity());
        root.findViewById(R.id.btn_share_SC).setOnClickListener(this);

        CurrencyAdapter currencyAdapter  = new CurrencyAdapter(getContext(),data );

        // настраиваем список
        ListView lvMain = (ListView) root.findViewById(R.id.list_view_SC);
        lvMain.setAdapter(currencyAdapter);
    }


    @Override
    public void onClick(View v) {

    }
}
