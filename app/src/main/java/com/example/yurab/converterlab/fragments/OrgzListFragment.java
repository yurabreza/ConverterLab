package com.example.yurab.converterlab.fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yurab.converterlab.Constants;
import com.example.yurab.converterlab.R;
import com.example.yurab.converterlab.database.DBHelper;
import com.example.yurab.converterlab.fragments.recycler_view.RVOrgzAdapter;
import com.example.yurab.converterlab.model.Organization;
import com.example.yurab.converterlab.service.UpdateService;

import java.util.List;


/**
 * Created by Yura Breza
 * Date  22.04.2016.
 */
public final class OrgzListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private final static String TAG = OrgzListFragment.class.getSimpleName();

    private List<Organization> organizations;
    private ViewGroup container;
    private SwipeRefreshLayout swipeRefreshLayout;
    private DBHelper dbHelper;
    private RecyclerView recyclerView;
    private RVOrgzAdapter rvOrgzAdapter;
    private boolean isFirstTime = false;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.container = container;
        return inflater.inflate(R.layout.orgz_list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mMessageReceiver,
                new IntentFilter(Constants.BROADCAST_UPDATE_END));

        recyclerView = (RecyclerView) container.findViewById(R.id.recycler_view_OLF);
        dbHelper = new DBHelper();
        swipeRefreshLayout = (SwipeRefreshLayout) container.findViewById(R.id.refresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        organizations = dbHelper.getOrgzList();
        if (isNetworkAvaAvailable()) {
            if (organizations.size() == 0) {
                onRefresh();
                isFirstTime = true;

            } else {

                createAdapter();
                update();
            }
        } else {
            if (!(organizations.size() == 0)) {
                createAdapter();
            } else
                Toast.makeText(getContext(), "NO internet and db is empty", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRefresh() {
        Log.d(TAG, "onRefresh: ");

        swipeRefreshLayout.setRefreshing(true);
        update();


    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (isFirstTime) {
                isFirstTime = false;
                organizations = dbHelper.getOrgzList();
                createAdapter();
                swipeRefreshLayout.setRefreshing(false);
            } else {
                createAdapter();
                swipeRefreshLayout.setRefreshing(false);

            }
            Log.d("receiver", "Got message: ");
        }
    };

    private boolean isNetworkAvaAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void createAdapter() {

        rvOrgzAdapter = new RVOrgzAdapter(getContext(), organizations);
        recyclerView.setAdapter(rvOrgzAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        rvOrgzAdapter.notifyDataSetChanged();

    }


    public void update() {
        getActivity().startService(new Intent(getActivity(), UpdateService.class));
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }
}
