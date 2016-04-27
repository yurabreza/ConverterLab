package com.example.yurab.converterlab.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.widget.Toast;

import com.example.yurab.converterlab.MainActivity;
import com.example.yurab.converterlab.constants.Constants;
import com.example.yurab.converterlab.fragments.map.MapFragment;
import com.example.yurab.converterlab.model.Organization;

/**
 * Created by Yura Breza
 * Date  25.04.2016.
 */
public final class MenuActions {

    public void openMap(Context context,Organization organization) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.MAP_KEY_CITY,organization.getCity());
        bundle.putString(Constants.MAP_KEY_REGION,organization.getRegion());
        bundle.putString(Constants.MAP_KEY_ADDRESS,organization.getAddress());
        bundle.putString(Constants.MAP_KEY_TITLE,organization.getTitle());
        MainActivity m = (MainActivity) context;

        MapFragment mapFragment = new MapFragment();
        mapFragment.setArguments(bundle);
        m.commitFragmnet(mapFragment);

    }

    public void openUrl(Context context,String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

        try {
            context.startActivity(browserIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(context, "Web browser Activity not found", Toast.LENGTH_SHORT).show();
        }

    }

    public void makeCall(Context context,String phone) {
        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:"+phone));
        try {
            context.startActivity(phoneIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(context, "ACTION_CALL Activity not found"
                    + Uri.parse(PhoneNumberUtils.formatNumber(phone, "UA")).toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
