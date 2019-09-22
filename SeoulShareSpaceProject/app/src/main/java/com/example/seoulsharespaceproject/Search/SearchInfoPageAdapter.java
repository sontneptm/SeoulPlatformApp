package com.example.seoulsharespaceproject.Search;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.seoulsharespaceproject.ReviewFragment;
import com.example.seoulsharespaceproject.TogoFragment;

public class SearchInfoPageAdapter extends FragmentStatePagerAdapter {

    int countTab;

    public SearchInfoPageAdapter(FragmentManager fm, int countTab) {
        super(fm);
        this.countTab = countTab;
    }

    public Fragment getItem(int i) {
        switch (i){
            case 0:
                SearchInfoFragment searchInfoFragment = new SearchInfoFragment();
                return searchInfoFragment;
            case 1:
                TogoFragment togoFragment = new TogoFragment();
                return togoFragment;
            case 2:
                ReviewFragment reviewFragment = new ReviewFragment();
                return reviewFragment;
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return countTab;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }
}
