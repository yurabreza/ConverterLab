package com.example.yurab.converterlab.fragments.DetailView.recycler_view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yurab.converterlab.R;
import com.example.yurab.converterlab.model.CurrencyOrg;

import java.util.List;

/**
 * Created by Yura Breza
 * Date  25.04.2016.
 */
public final class RVDetailAdapter extends RecyclerView.Adapter<RVDetailHolder> {
    private List<CurrencyOrg> data;
    private LayoutInflater inflater;
    private Drawable arrowGreen;
    private Drawable arrowRed;


    public RVDetailAdapter(Context _context, List<CurrencyOrg> _data) {
        inflater = LayoutInflater.from(_context);
        data = _data;
        arrowGreen = ContextCompat.getDrawable(_context, R.drawable.ic_green_arrow_up);
        arrowRed = ContextCompat.getDrawable(_context, R.drawable.ic_red_arrow_down);
    }

    @Override
    public RVDetailHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.currency_card, parent, false);
        return new RVDetailHolder(v);
    }

    @Override
    public void onBindViewHolder(RVDetailHolder holder, int position) {
        holder.tvTitle.setText(data.get(position).getCurrencyName());
        holder.tvAsk.setText(data.get(position).getAsk());
        holder.tvBid.setText(data.get(position).getBid());
        if (data.get(position).getOldAsk() != null) {
            if (Float.valueOf(data.get(position).getAsk()) < Float.valueOf(data.get(position).getOldAsk()))
                holder.ivAskArrow.setImageDrawable(arrowRed);
            else holder.ivAskArrow.setImageDrawable(arrowGreen);
        } else
            holder.ivAskArrow.setImageDrawable(arrowGreen);
        if (data.get(position).getOldBid() != null) {
            if (Float.valueOf(data.get(position).getBid()) < Float.valueOf(data.get(position).getOldBid()))
                holder.ivBidArrow.setImageDrawable(arrowRed);
            else holder.ivBidArrow.setImageDrawable(arrowGreen);
        } else
            holder.ivBidArrow.setImageDrawable(arrowGreen);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
