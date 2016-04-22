package com.example.yurab.converterlab.fragments.recycler_view;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.yurab.converterlab.R;

/**
 * Created by Yura Breza
 * Date  22.04.2016.
 */
public class RVOrgzHolder extends RecyclerView.ViewHolder {
    public TextView tvTitle,tvRegion,tvCity,tvPhone,tvAddress;
    public TabLayout tabLayout;
    public RVOrgzHolder(View itemView) {
        super(itemView);
        tvTitle = (TextView) itemView.findViewById(R.id.tw_card_title_OLC);
        tvRegion = (TextView) itemView.findViewById(R.id.tw_card_region_OLC);
        tvCity =(TextView)  itemView.findViewById(R.id.tw_card_city_OLC);
        tvPhone = (TextView) itemView.findViewById(R.id.tw_card_phone_OLC);
        tvAddress =(TextView)  itemView.findViewById(R.id.tw_card_address_OLC);
        tabLayout = (TabLayout) itemView.findViewById(R.id.tab_layout_OLC);

    }
}
