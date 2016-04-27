package com.example.yurab.converterlab.fragments.orgz_list;


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
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yurab.converterlab.MainActivity;
import com.example.yurab.converterlab.R;
import com.example.yurab.converterlab.constants.Constants;
import com.example.yurab.converterlab.database.DBHelper;
import com.example.yurab.converterlab.fragments.orgz_list.recycler_view.RVOrgzAdapter;
import com.example.yurab.converterlab.model.Organization;
import com.example.yurab.converterlab.service.UpdateService;

import java.util.List;


/**
 * Created by Yura Breza
 * Date  22.04.2016.
 */
public final class OrgzListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, SearchView.OnQueryTextListener {
    private final static String TAG = OrgzListFragment.class.getSimpleName();
    private String searchToken;
    private List<Organization> organizations;
    private ViewGroup container;
    private SwipeRefreshLayout swipeRefreshLayout;
    private DBHelper dbHelper;
    private RecyclerView recyclerView;
    private RVOrgzAdapter rvOrgzAdapter;
    private boolean isFirstTime = false;
    private static boolean updateFirst = false;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.container = container;
        setRetainInstance(true);
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

        if (savedInstanceState != null) {
            searchToken = savedInstanceState.getString(Constants.UPDATE_KEY);
            MainActivity m = (MainActivity) getActivity();
            Log.d(TAG, "onActivityCreated:   " + searchToken);

        }


        //this is alpha version of logic
        if (!updateFirst) {
            updateFirst = true;
            //updateFirst  is false if this fragment is created first time
            if (isNetworkAvaAvailable()) {
                //alpha version of checking internet connection
                //if there is connection and list in db is empty
                if (organizations.size() == 0) {
                    //updating db  and setting swipe refresh to spin
                    onRefresh();
                    //setting first time flag  to get data from db after updating
                    isFirstTime = true;

                } else {
                    //else creating adapter with existing data
                    createAdapter();
                    //and then starting update
                    //this is for not stop UI thread
                    update();
                }
            } else {
                if (!(organizations.size() == 0)) {
                    //if no internet but db is not empty creating adapter
                    createAdapter();
                } else
                    //showing toast that htere`s no connection and DB is empty
                    Toast.makeText(getContext(), "NO internet and db is empty", Toast.LENGTH_LONG).show();
            }
        } else {
            createAdapter();
        }
    }

    @Override
    public void onRefresh() {
        Log.d(TAG, "onRefresh: ");
        //refresh end when fragment receives message from service that update is completed
        swipeRefreshLayout.setRefreshing(true);
        update();


    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //receiving message from service
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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Constants.UPDATE_KEY, searchToken);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
//        Toast.makeText(getContext(), "query : " + query, Toast.LENGTH_SHORT).show();

        return false;
    }


    @Override
    public boolean onQueryTextChange(String newText) {
        searchToken = newText;
        rvOrgzAdapter.getFilter().filter(newText);
        return false;
    }
}
