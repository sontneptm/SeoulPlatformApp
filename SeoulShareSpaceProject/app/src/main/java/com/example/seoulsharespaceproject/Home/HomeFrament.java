package com.example.seoulsharespaceproject.Home;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.seoulsharespaceproject.R;
import kr.go.seoul.airquality.AirQualityTypeMini;


public class HomeFrament extends Fragment {

    private String key ="59547a4a586c656537336872536573";
    private AirQualityTypeMini mini;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        mini =(AirQualityTypeMini)view.findViewById(R.id.Airmini);
        mini.setOpenAPIKey(key);

        //배경이미지 설정 this는 현재 액티비티 혹은 프래그먼트
        //load 뒤의 R.drawable은 드로어블 이미지를
        //into 다음에들어오는것은 imageView 또는 Button 같은 컨텐츠 적용.
        return view;
    }

}
