package com.example.seoulsharespaceproject.Home;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.seoulsharespaceproject.LoginActivity;
import com.example.seoulsharespaceproject.MyPageActivity;
import com.example.seoulsharespaceproject.R;
import com.example.seoulsharespaceproject.ReservationActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView; //하단 네비게이션 바
    private FragmentManager manager; //fragment를 관리해주는 FragmentManager
    private FragmentTransaction transaction; //fragment간 변환을 위한 FragmentTransaction

    //fragment모음
    private WishListFragment fragmentWishList;
    private HomeFrament fragmentHome;
    private SearchFragment fragmentSearch;

    //Navigation Bar에 필요한 drawer들
    private ImageView drawerBackground;
    private CircleImageView drawerProfile;
    private TextView drawerPhone;
    private TextView drawerEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        manager=getSupportFragmentManager();



        //디폴트 프래그먼트 홈화면 지정.
        fragmentHome=new HomeFrament();
        transaction=manager.beginTransaction();
        transaction.replace(R.id.home_main,fragmentHome).commit();

        //아이템이 선택된 때 호출될 리스너 등록
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                transaction=manager.beginTransaction();
                switch (menuItem.getItemId()){
                    case R.id.Bottomnavigation_wishlist:
                        fragmentWishList=new WishListFragment();
                        fragmentHome =null;
                        fragmentSearch =null;
                        transaction.replace(R.id.home_main,fragmentWishList).commit();
                        break;
                    case R.id.Bottomnavigation_home:
                        fragmentHome=new HomeFrament();
                        fragmentSearch=null;
                        fragmentWishList=null;
                        transaction.replace(R.id.home_main,fragmentHome).commit();
                        break;
                    case R.id.Bottomnavigation_search:
                        fragmentSearch=new SearchFragment();
                        fragmentHome=null;
                        fragmentWishList=null;
                        transaction.replace(R.id.home_main,fragmentSearch).commit();
                        break;
                }
                return true;
            }
        });
    }
}



