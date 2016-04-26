package com.example.yurab.converterlab.fragments.OrgzList.recycler_view;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.yurab.converterlab.MainActivity;
import com.example.yurab.converterlab.R;
import com.example.yurab.converterlab.constants.Constants;
import com.example.yurab.converterlab.database.DBHelper;
import com.example.yurab.converterlab.fragments.DetailView.DetailFragment;
import com.example.yurab.converterlab.model.Organization;
import com.example.yurab.converterlab.utils.MenuActions;

import java.util.List;

/**
 * Created by Yura Breza
 * Date  22.04.2016.
 */
public final class RVOrgzAdapter extends RecyclerView.Adapter<RVOrgzHolder> implements TabLayout.OnTabSelectedListener, Filterable {
    private final static String TAG = RVOrgzAdapter.class.getSimpleName();
    private LayoutInflater inflater;
    public List<Organization> organizations;
    public List<Organization> organizationsAll;
    private Context context;
    private String prePhone;
    private String preAddress;
    private MenuActions menuActions;
    private DBHelper dbHelper;


    public RVOrgzAdapter(Context _context, List<Organization> _organizations) {
        inflater = LayoutInflater.from(_context);
        this.organizations = _organizations;
        this.organizationsAll = _organizations;
        this.context = _context;
        menuActions = new MenuActions();
        dbHelper = new DBHelper();

        prePhone = context.getResources().getString(R.string.pre_phone);
        preAddress = context.getResources().getString(R.string.pre_address);

    }

    @Override
    public RVOrgzHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.orgz_list_card, parent, false);
        return new RVOrgzHolder(view, context);
    }

    @Override
    public void onBindViewHolder(RVOrgzHolder holder, int position) {
        holder.tvTitle.setText(organizationsAll.get(position).getTitle());
        holder.tvRegion.setText(organizationsAll.get(position).getRegion());
        holder.tvCity.setText(organizationsAll.get(position).getCity());
        holder.tvPhone.setText(prePhone + organizationsAll.get(position).getPhone());
        holder.tvAddress.setText(preAddress + organizationsAll.get(position).getAddress());
        Log.d(TAG, "onBindViewHolder: " + organizationsAll.get(position).getTitle().toString());

        holder.tabLayout.setTag(position);

        holder.tabLayout.getTabAt(0).setTag(position);
        holder.tabLayout.getTabAt(1).setTag(position);
        holder.tabLayout.getTabAt(2).setTag(position);
        holder.tabLayout.getTabAt(3).setTag(position);
        holder.tabLayout.setOnTabSelectedListener(this);

    }


    @Override
    public int getItemCount() {
        return organizationsAll.size();
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case (0):

                menuActions.openUrl(context, organizationsAll.get((int) tab.getTag()).getLink());
                break;
            case (1):

                menuActions.openMap(context, organizationsAll.get((int) tab.getTag()));
                break;
            case (2):

                menuActions.makeCall(context, organizationsAll.get((int) tab.getTag()).getPhone());
                break;
            case (3):
                commitDetailFragment(organizationsAll.get((int) tab.getTag()));

                break;
        }

    }


    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case (0):

                menuActions.openUrl(context, organizationsAll.get((int) tab.getTag()).getLink());
                break;
            case (1):
                menuActions.openMap(context, organizationsAll.get((int) tab.getTag()));
                break;
            case (2):

                menuActions.makeCall(context, organizationsAll.get((int) tab.getTag()).getPhone());
                break;
            case (3):
                commitDetailFragment(organizationsAll.get((int) tab.getTag()));
                break;
        }

    }

    public void commitDetailFragment(Organization organization) {
        MainActivity m = (MainActivity) context;
        DetailFragment dF = new DetailFragment();
        Bundle b = new Bundle();
        b.putLong(Constants.ID_KEY, organization.getId());
        dF.setArguments(b);
        m.commitFragmnet(dF);


    }


    @Override
    public Filter getFilter() {

        return new OrganizationFilter(this, dbHelper.getOrgzList());
    }


}
