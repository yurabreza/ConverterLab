package com.example.yurab.converterlab.fragments.DetailView.recycler_view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yurab.converterlab.R;

/**
 * Created by Yura Breza
 * Date  25.04.2016.
 */
public final class RVDetailHolder extends RecyclerView.ViewHolder {
    public TextView tvTitle,tvAsk,tvBid;
    public ImageView ivAskArrow, ivBidArrow;
    public RVDetailHolder(View itemView) {
        super(itemView);
        tvTitle =(TextView) itemView.findViewById(R.id.tv_title_CC);
        tvAsk =(TextView) itemView.findViewById(R.id.tw_ask_CC);
        tvBid =(TextView) itemView.findViewById(R.id.tv_bid_CC);
        ivAskArrow =(ImageView) itemView.findViewById(R.id.iv_ask_arrow_CC);
        ivBidArrow =(ImageView) itemView.findViewById(R.id.iv_bid_arrow_CC);

    }
}
