package com.example.yurab.converterlab.fragments.recycler_view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yurab.converterlab.R;
import com.example.yurab.converterlab.model.Organization;

import java.util.List;

/**
 * Created by Yura Breza
 * Date  22.04.2016.
 */
public final class RVOrgzAdapter extends RecyclerView.Adapter<RVOrgzHolder> implements TabLayout.OnTabSelectedListener {
    private final static String TAG = RVOrgzAdapter.class.getSimpleName();
    private LayoutInflater inflater;
    private List<Organization> organizations;
    private Context context;
    private String prePhone;
    private String preAddress;


    public RVOrgzAdapter(Context _context, List<Organization> _organizations) {
        inflater = LayoutInflater.from(_context);
        this.organizations = _organizations;
        this.context = _context;

        prePhone = context.getResources().getString(R.string.pre_phone);
        preAddress = context.getResources().getString(R.string.pre_address);
        Log.d(TAG, "RVOrgzAdapter: " + this.organizations.get(6).getTitle().toString());
    }

    @Override
    public RVOrgzHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.orgz_list_card, parent, false);
        return new RVOrgzHolder(view, context);
    }

    @Override
    public void onBindViewHolder(RVOrgzHolder holder, int position) {
        holder.tvTitle.setText(organizations.get(position).getTitle());
        holder.tvRegion.setText(organizations.get(position).getRegion());
        holder.tvCity.setText(organizations.get(position).getCity());
        holder.tvPhone.setText(prePhone + organizations.get(position).getPhone());
        holder.tvAddress.setText(preAddress + organizations.get(position).getAddress());
        Log.d(TAG, "onBindViewHolder: " + organizations.get(position).getTitle().toString());

        holder.tabLayout.setTag(position);

        holder.tabLayout.getTabAt(0).setTag(position);
        holder.tabLayout.getTabAt(1).setTag(position);
        holder.tabLayout.getTabAt(2).setTag(position);
        holder.tabLayout.getTabAt(3).setTag(position);
        holder.tabLayout.setOnTabSelectedListener(this);

    }


    @Override
    public int getItemCount() {
        return organizations.size();
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case (0):

                openUrl(organizations.get((int) tab.getTag()).getLink());
                break;
            case (1):

                openMap(organizations.get((int) tab.getTag()));
                break;
            case (2):

                makeCall(organizations.get((int) tab.getTag()).getPhone());
                break;
            case (3):
                commitDetailFragment(organizations.get((int) tab.getTag()));

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

                openUrl(organizations.get((int) tab.getTag()).getLink());
                break;
            case (1):
                openMap(organizations.get((int) tab.getTag()));
                break;
            case (2):

                makeCall(organizations.get((int) tab.getTag()).getPhone());
                break;
            case (3):
                commitDetailFragment(organizations.get((int) tab.getTag()));
                break;
        }

    }

    private void commitDetailFragment(Organization organization) {

    }

    private void openMap(Organization organization) {

    }

    private void openUrl(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

        try {
            context.startActivity(browserIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(context, "Web browser Activity not found", Toast.LENGTH_SHORT).show();
        }

    }

    private void makeCall(String phone) {
        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse(PhoneNumberUtils.formatNumber(phone, "UA")));
        try {
            context.startActivity(phoneIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(context, "ACTION_CALL Activity not found"
                    + Uri.parse(PhoneNumberUtils.formatNumber(phone, "UA")).toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
