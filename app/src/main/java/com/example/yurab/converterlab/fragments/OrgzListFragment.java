package com.example.yurab.converterlab.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yurab.converterlab.R;
import com.example.yurab.converterlab.database.DBHelper;
import com.example.yurab.converterlab.fragments.recycler_view.RVOrgzAdapter;
import com.example.yurab.converterlab.model.Organization;

import java.util.List;


/**
 * Created by Yura Breza
 * Date  22.04.2016.
 */
public class OrgzListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private final static String TAG =OrgzListFragment.class.getSimpleName();

    private List<Organization> organizations;
    private ViewGroup container;
    private SwipeRefreshLayout swipeRefreshLayout;
    private DBHelper dbHelper;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.container = container;
        return inflater.inflate(R.layout.orgz_list_fragment,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) container.findViewById(R.id.recycler_view_OLF);
        dbHelper = new DBHelper();
        organizations = dbHelper.getOrgzList();

//        Log.d(TAG, "update: "+organizations.get(4).getTitle().toString());
        RVOrgzAdapter rvOrgzAdapter = new RVOrgzAdapter(getContext(),organizations);
        recyclerView.setAdapter(rvOrgzAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        rvOrgzAdapter.notifyDataSetChanged();
        swipeRefreshLayout = (SwipeRefreshLayout) container.findViewById(R.id.refresh);
        swipeRefreshLayout.setOnRefreshListener(this);
    }


    public void update(){

    }

    @Override
    public void onRefresh() {
        Log.d(TAG, "onRefresh: ");

       // swipeRefreshLayout.setRefreshing(true);
       // organizations = mainActivity.getOrgz();
//       rvOrgzAdapter.deleteList();
//        rvOrgzAdapter.notifyItemRangeRemoved(0,rvOrgzAdapter.getItemCount());
//        rvOrgzAdapter.setList(organizations);
//        rvOrgzAdapter.notifyDataSetChanged();
       swipeRefreshLayout.setRefreshing(false);




    }
}
