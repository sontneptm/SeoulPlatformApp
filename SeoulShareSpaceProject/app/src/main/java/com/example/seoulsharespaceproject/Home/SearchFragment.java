package com.example.seoulsharespaceproject.Home;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.seoulsharespaceproject.R;
import com.example.seoulsharespaceproject.Search.ShareSpace;
import com.example.seoulsharespaceproject.Search.SpaceAdapter;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment implements View.OnClickListener {
    private RecyclerView.LayoutManager layoutManager;
    private SpaceAdapter adapter;
    private RecyclerView recyclerView;
    private List<ShareSpace> datas;
    private View view;


    public SearchFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        datas =new ArrayList<>();
        view= inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView =(RecyclerView)view.findViewById(R.id.search_recyclerView);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        view.findViewById(R.id.searchButton).setOnClickListener(this);

        datas.add(new ShareSpace(getResources().getDrawable(R.drawable.sample),"순천향대","개인","창업",1000));
        datas.add(new ShareSpace(getResources().getDrawable(R.drawable.sample),"순천향대","개인","창업",1000));
        datas.add(new ShareSpace(getResources().getDrawable(R.drawable.sample),"순천향대","개인","창업",1000));
        datas.add(new ShareSpace(getResources().getDrawable(R.drawable.sample),"순천향대","개인","창업",1000));
        datas.add(new ShareSpace(getResources().getDrawable(R.drawable.sample),"순천향대","개인","창업",1000));


        adapter = new SpaceAdapter(datas);
        recyclerView.setAdapter(adapter);

        return view;

    }

    @Override
    public void onClick(View view) {

    }
}
