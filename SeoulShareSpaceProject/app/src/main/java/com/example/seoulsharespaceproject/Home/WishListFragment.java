package com.example.seoulsharespaceproject.Home;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.seoulsharespaceproject.R;


public class WishListFragment extends Fragment {


    public WishListFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wish_list, container, false);
    }

}
