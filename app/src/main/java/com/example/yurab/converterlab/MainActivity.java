package com.example.yurab.converterlab;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.yurab.converterlab.fragments.OrgzList.OrgzListFragment;

public final class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private OrgzListFragment orgzListFragment;
    public SearchView searchView ;
    public String search ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        orgzListFragment = new OrgzListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_activity, orgzListFragment)
                .commit();


    }


    @Override
    public boolean onSearchRequested() {
        return super.onSearchRequested();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.menu_search));
        searchView.setInputType(1);
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(orgzListFragment);
        if(search!=null)
            searchView.setQuery(search,true);
        return true;
    }


    public void commitFragmnet(android.support.v4.app.Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_activity, fragment)
                .addToBackStack(null)
                .commit();
    }
}
