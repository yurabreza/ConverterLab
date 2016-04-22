package com.example.yurab.converterlab.fragments.recycler_view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yurab.converterlab.R;
import com.example.yurab.converterlab.model.Organization;

import java.util.List;

/**
 * Created by Yura Breza
 * Date  22.04.2016.
 */
public class RVOrgzAdapter extends RecyclerView.Adapter<RVOrgzHolder> {
    private final static String TAG =RVOrgzAdapter.class.getSimpleName();
    private LayoutInflater inflater;
    private List<Organization> organizations;
    private Context context;

    public RVOrgzAdapter(Context _context,List<Organization> _organizations){
        inflater = LayoutInflater.from(_context);
        this.organizations = _organizations;
        this.context = _context;
        Log.d(TAG, "RVOrgzAdapter: "+ this.organizations.get(6).getTitle().toString());
    }

    @Override
    public RVOrgzHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.orgz_list_card,parent,false);
        return new RVOrgzHolder(view);
    }

    @Override
    public void onBindViewHolder(RVOrgzHolder holder, int position) {
        holder.tvTitle.setText(organizations.get(position).getTitle());
        holder.tvRegion.setText(organizations.get(position).getRegion());
        holder.tvCity.setText(organizations.get(position).getCity());
        holder.tvPhone.setText(organizations.get(position).getPhone());
        holder.tvAddress.setText(organizations.get(position).getAddress());
        Log.d(TAG, "onBindViewHolder: "+organizations.get(position).getTitle().toString());

    }



    @Override
    public int getItemCount() {
        return organizations.size();
    }
}
