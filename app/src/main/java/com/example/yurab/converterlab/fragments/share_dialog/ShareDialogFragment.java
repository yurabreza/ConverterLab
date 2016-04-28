package com.example.yurab.converterlab.fragments.share_dialog;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yurab.converterlab.R;
import com.example.yurab.converterlab.constants.Constants;
import com.example.yurab.converterlab.database.DBHelper;
import com.example.yurab.converterlab.model.CurrencyOrg;
import com.example.yurab.converterlab.model.Organization;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yura Breza
 * Date  26.04.2016.
 */
public final class ShareDialogFragment extends android.support.v4.app.DialogFragment implements View.OnClickListener {
    private long id;
    private Organization organization;
    private List<CurrencyOrg> data;
    private ViewGroup container;
    private TextView tvTitle, tvRegion, tvCity;
    private View view;
    private LinearLayout linearLayout;
    private ListView lvMain;
    private CurrencyAdapter currencyAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        id = getArguments().getLong(Constants.ID_KEY);
        Log.d("ShareDialogFragment", "onCreateView: " + id);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.container = container;
        view = inflater.inflate(R.layout.share_content, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DBHelper dbHelper = new DBHelper();
        organization = dbHelper.getOrgById(id);
        data = dbHelper.getCurrencyOrgList(organization.getIdString());
        init();


    }

    private void init() {
        tvTitle = (TextView) view.findViewById(R.id.tv_title_SC);
        tvRegion = (TextView) view.findViewById(R.id.tv_region_SC);
        tvCity = (TextView) view.findViewById(R.id.tv_city_SC);
        tvTitle.setText(organization.getTitle());
        tvRegion.setText(organization.getRegion());
        tvCity.setText(organization.getCity());
        view.findViewById(R.id.btn_share_SC).setOnClickListener(this);


        linearLayout = (LinearLayout) view.findViewById(R.id.linear_layout_SC);


        currencyAdapter = new CurrencyAdapter(getContext(), data);


        lvMain = (ListView) view.findViewById(R.id.list_view_SC);
        lvMain.setAdapter(currencyAdapter);

    }


    @Override
    public void onClick(View v) {

        Bitmap icon = getWholeListViewItemsToBitmap();

        shareBitmap(icon, organization.getTitle() + "-currencies");
    }

    public static Bitmap loadBitmapFromView(View v) {
        Bitmap b = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);

        v.layout(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
        v.draw(c);
        return b;
    }

    private void shareBitmap(Bitmap bitmap, String fileName) {
        try {
            File file = new File(getContext().getCacheDir(), fileName + ".jpeg");
            FileOutputStream fOut = new FileOutputStream(file);

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
            file.setReadable(true, false);
            final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            intent.setType("image/jpeg");
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public Bitmap getWholeListViewItemsToBitmap() {

        ListView listview = lvMain;
        ListAdapter adapter = listview.getAdapter();
        int itemscount = adapter.getCount();
        int allitemsheight = 0;
        List<Bitmap> bmps = new ArrayList<Bitmap>();

        Bitmap iconTop = loadBitmapFromView(linearLayout);

        bmps.add(iconTop);
        allitemsheight += iconTop.getHeight();

        for (int i = 0; i < itemscount; i++) {

            View childView = adapter.getView(i, null, listview);
            childView.measure(View.MeasureSpec.makeMeasureSpec(listview.getWidth(), View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

            childView.layout(0, 0, childView.getMeasuredWidth(), childView.getMeasuredHeight());
            childView.setDrawingCacheEnabled(true);
            childView.buildDrawingCache();
            bmps.add(childView.getDrawingCache());
            allitemsheight += childView.getMeasuredHeight();
        }

        Bitmap bigbitmap = Bitmap.createBitmap(listview.getMeasuredWidth(), allitemsheight, Bitmap.Config.ARGB_8888);
        Canvas bigcanvas = new Canvas(bigbitmap);

        Paint paint = new Paint();
        int iHeight = 0;

        for (int i = 0; i < bmps.size(); i++) {
            Bitmap bmp = bmps.get(i);
            bigcanvas.drawBitmap(bmp, 0, iHeight, paint);
            iHeight += bmp.getHeight();

            bmp.recycle();
            bmp = null;
        }


        return bigbitmap;
    }
}
