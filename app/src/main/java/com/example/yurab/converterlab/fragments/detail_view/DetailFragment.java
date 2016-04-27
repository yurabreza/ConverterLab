package com.example.yurab.converterlab.fragments.detail_view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yurab.converterlab.R;
import com.example.yurab.converterlab.constants.Constants;
import com.example.yurab.converterlab.database.DBHelper;
import com.example.yurab.converterlab.fragments.detail_view.recycler_view.RVDetailAdapter;
import com.example.yurab.converterlab.fragments.share_dialog.ShareDialogFragment;
import com.example.yurab.converterlab.model.CurrencyOrg;
import com.example.yurab.converterlab.model.Organization;
import com.example.yurab.converterlab.utils.MenuActions;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.List;

/**
 * Created by Yura Breza
 * Date  25.04.2016.
 */
public final class DetailFragment extends Fragment implements MenuItem.OnMenuItemClickListener, View.OnClickListener {
    private TextView tvTitle, tvLink, tvPhone, tvRegion, tvCity, tvAddress, tvOrgType;
    private ViewGroup container;
    private long id;
    private Organization organization;
    private List<CurrencyOrg> dataList;
    private String link;
    private String phone;
    private String address;
    private String city;
    private String region;
    private String orgType;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        id = getArguments().getLong(Constants.ID_KEY);
        this.container = container;
        setRetainInstance(true);
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.detail_content, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {

        loadRes();
        DBHelper dbHelper = new DBHelper();
        organization = dbHelper.getOrgById(id);

        tvTitle = (TextView) container.findViewById(R.id.tv_title_DC);
        tvLink = (TextView) container.findViewById(R.id.tv_link_DC);
        tvPhone = (TextView) container.findViewById(R.id.tv_phone_DC);
        tvRegion = (TextView) container.findViewById(R.id.tv_region_DC);
        tvCity = (TextView) container.findViewById(R.id.tv_city_DC);
        tvAddress = (TextView) container.findViewById(R.id.tv_address_DC);
        tvOrgType = (TextView) container.findViewById(R.id.tv_org_type_DC);


        tvTitle.setText(organization.getTitle());
        tvLink.setText(link + organization.getLink());
        tvPhone.setText(phone + organization.getPhone());
        tvRegion.setText(region + organization.getRegion());
        tvCity.setText(city + organization.getCity());
        tvAddress.setText(address + organization.getAddress());
        tvOrgType.setText(orgType + organization.getOrganizationType());


        FloatingActionsMenu floatingActionsMenu= (FloatingActionsMenu) container.findViewById(R.id.floating_menu);
        floatingActionsMenu.getChildAt(0).setOnClickListener(this);
        floatingActionsMenu.getChildAt(1).setOnClickListener(this);
        floatingActionsMenu.getChildAt(2).setOnClickListener(this);



        dataList = dbHelper.getCurrencyOrgList(organization.getIdString());

        RecyclerView rv = (RecyclerView) container.findViewById(R.id.recycler_DC);

        RVDetailAdapter rvDetailAdapter = new RVDetailAdapter(getContext(), dataList);
        rv.setAdapter(rvDetailAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(R.id.menu_search).setVisible(false);
        inflater.inflate(R.menu.detail_menu, menu);
        menu.findItem(R.id.menu_share).setOnMenuItemClickListener(this);
    }

    private void loadRes() {
        link = getActivity().getResources().getString(R.string.link_rus);
        phone = getActivity().getResources().getString(R.string.pre_phone);
        address = getActivity().getResources().getString(R.string.pre_address);
        city = getActivity().getResources().getString(R.string.pre_city);
        region = getActivity().getResources().getString(R.string.pre_region);
        orgType = getActivity().getResources().getString(R.string.pre_org_type);
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        ShareDialogFragment shareDialogFragment = new ShareDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(Constants.ID_KEY,id);
        shareDialogFragment.setArguments(bundle);
        shareDialogFragment.show(getActivity().getSupportFragmentManager(),Constants.SHARE_DIALOG);
        return false;
    }

    @Override
    public void onClick(View v) {
        MenuActions menuActions = new MenuActions();
        switch (v.getId()){
            case (R.id.action_map):
                menuActions.openMap(getContext(),organization);
                break;
            case (R.id.action_phone):
                menuActions.makeCall(getContext(),organization.getPhone());
                break;
            case (R.id.action_site):
                menuActions.openUrl(getContext(),organization.getLink());
                break;
        }

    }
}
