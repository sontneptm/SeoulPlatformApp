package com.example.seoulsharespaceproject.Home;

import android.content.Intent;
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

import com.example.seoulsharespaceproject.Login.LoginActivity;
import com.example.seoulsharespaceproject.MyPageActivity;
import com.example.seoulsharespaceproject.R;
import com.example.seoulsharespaceproject.ReservationActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView; //하단 네비게이션 바
    private FragmentManager manager; //fragment를 관리해주는 FragmentManager
    private FragmentTransaction transaction; //fragment간 변환을 위한 FragmentTransaction

    //fragment모음
    private HomeFrament fragmentHome;
    private WishListFragment fragmentWish;
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
        manager = getSupportFragmentManager();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                transaction = manager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.Bottomnavigation_wishlist:
                        fragmentWish = new WishListFragment();
                        transaction.replace(R.id.home_main, fragmentWish).commit();
                        break;
                    case R.id.Bottomnavigation_home:
                        fragmentHome = new HomeFrament();
                        transaction.replace(R.id.home_main, fragmentHome).commit();
                        break;
                    case R.id.Bottomnavigation_search:
                        fragmentSearch = new SearchFragment();
                        transaction.replace(R.id.home_main, fragmentSearch).commit();
                        break;

                }
                return true;
            }

        });


        //디폴트 프래그먼트 홈화면 지정.
        fragmentHome = new HomeFrament();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.home_main, fragmentHome).commit();

        // Navigation Drawer (옆구리 네비게이션 바)
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, 0, 0);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // 상단에 토글 아이콘 색상 변경
        int color = ContextCompat.getColor(getBaseContext(), R.color.colorPrimary);
        toggle.getDrawerArrowDrawable().setColor(color);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = LayoutInflater.from(this).inflate(R.layout.navigationbar_left_drawer, navigationView, false);
        navigationView.addHeaderView(headerView);

        //아이템이 선택된 때 호출될 리스너 등록
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        // 내 정보 이동
        if (id == R.id.nav_mypage) {
            startActivity(new Intent(this, MyPageActivity.class));
        }
        // 예약 정보 이동
        else if (id == R.id.nav_reservation_information) {
            startActivity(new Intent(this, ReservationActivity.class));
        }
        // 로그아웃, 로그인페이지로 이동
        else if (id == R.id.nav_logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, LoginActivity.class));

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}




