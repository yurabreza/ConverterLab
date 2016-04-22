package com.example.yurab.converterlab.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yurab.converterlab.MainActivity;
import com.example.yurab.converterlab.R;
import com.example.yurab.converterlab.fragments.recycler_view.RVOrgzAdapter;
import com.example.yurab.converterlab.model.Organization;

import java.util.List;


/**
 * Created by Yura Breza
 * Date  22.04.2016.
 */
public class OrgzListFragment extends Fragment {
    private final static String TAG =OrgzListFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    private List<Organization> organizations;
    private ViewGroup container;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.container = container;
        return inflater.inflate(R.layout.orgz_list_fragment,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = (RecyclerView) container.findViewById(R.id.recycler_view_OLF);
        MainActivity mainActivity = (MainActivity) getContext();
        organizations = mainActivity.getOrgz();
        RVOrgzAdapter rvOrgzAdapter = new RVOrgzAdapter(getContext(),organizations);
        recyclerView.setAdapter(rvOrgzAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


    }
}
