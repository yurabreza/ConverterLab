package com.example.yurab.converterlab.fragments.orgz_list.recycler_view;

import android.widget.Filter;

import com.example.yurab.converterlab.model.Organization;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yura Breza
 * Date  26.04.2016.
 */
public final class OrganizationFilter extends Filter {
    private List<Organization> originalList;
    private List<Organization> filteredList;
    private RVOrgzAdapter rvOrgzAdapter;

    OrganizationFilter(RVOrgzAdapter adapter, List<Organization> data) {
        super();
        rvOrgzAdapter = adapter;
        originalList = data;
        filteredList = new ArrayList<>();
    }


    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        filteredList.clear();
        FilterResults results = new FilterResults();

        if (constraint.length() == 0) {
            filteredList.addAll(originalList);
        } else {
            String filterPattern = constraint.toString().toLowerCase().trim();
            for (Organization organization : originalList) {
                if (organization.getTitle().toLowerCase().contains(filterPattern)
                        ||organization.getRegion().toLowerCase().contains(filterPattern)
                        ||organization.getAddress().toLowerCase().contains(filterPattern)
                        ){

                    filteredList.add(organization);
                }
            }
        }
        results.values = filteredList;
        results.count = filteredList.size();
        return results;

    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        rvOrgzAdapter.organizations.clear();
        rvOrgzAdapter.organizations.addAll((List<Organization>) results.values);
        rvOrgzAdapter.notifyDataSetChanged();
    }
}
