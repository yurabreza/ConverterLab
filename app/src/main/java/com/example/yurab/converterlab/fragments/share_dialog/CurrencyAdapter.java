package com.example.yurab.converterlab.fragments.share_dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.yurab.converterlab.R;
import com.example.yurab.converterlab.model.CurrencyOrg;

import java.util.List;

/**
 * Created by Yura Breza
 * Date  26.04.2016.
 */
public final class CurrencyAdapter extends BaseAdapter {
    private Context context;
    private List<CurrencyOrg> data;
    private LayoutInflater inflater;

    CurrencyAdapter(Context context, List<CurrencyOrg> data){
        this.context = context;
        this.data = data;
        inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.list_view_row, parent, false);
        }

        CurrencyOrg currencyOrg = data.get(position);

        TextView tvId = (TextView) view.findViewById(R.id.tv_currency_id_LVR);
        TextView tvAskBid = (TextView) view.findViewById(R.id.tv_ask_bid_LVR);

        tvId.setText(currencyOrg.getCurrencyId());
        tvAskBid.setText(currencyOrg.getAsk()+"/"+currencyOrg.getBid());


        return view;
    }
}
