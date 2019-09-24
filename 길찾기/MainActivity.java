package com.example.myroadfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import net.daum.mf.map.api.MapPoint;

public class MainActivity extends AppCompatActivity {
    EditText start;
    EditText last;
    private LocationManager locationManager;
    GpsLocationListener gpsLocationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //위치 변화를 감지할 사용자 정의 locationListener 객체
        gpsLocationListener = new GpsLocationListener();
        //현 위치 탐색을 요청할 LocationManager 객체
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
    }

    //버튼을 클릭하면
    public void onClick(View view){
        //위치 권한이 있는지 확인하고 없다면 권한 요청
        if ( Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission( getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions( MainActivity.this, new String[] {  android.Manifest.permission.ACCESS_FINE_LOCATION  },
                    0 );
        }else{
            /* 권한이 있다면
            *사용자의 현재 위치 탐색 요청
            */
            //gps로 1초마다 현 위치 요청
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    1000,
                    1,
                    gpsLocationListener);
            //gps로 위치를 찾아내지 못한 경우를 위해 네트워크로 1초마다 현 위치 요청
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    1000,
                    1,
                    gpsLocationListener);
        }
    }
    //다음 지도 길찾기 기능 표시 메소드
    public void findMap(double latitude, double longitude){
        //1초마다 요청하던 현 위치 정보를 요청 중단합니다.
        locationManager.removeUpdates(gpsLocationListener);

        String start_name, last_name;
        //시작점 좌표 x, y(위도, 경도)
        start_name = latitude+","+longitude;
        //도착점 좌표 x, y(위도, 경도)
        last_name = "37.61175701312581"+","+"126.91035353482715";

        //url에 시작점 좌표와 도착점 좌표를 전달합니다.
        String url = "daummaps://route?sp=" + start_name +"&ep="+ last_name +"&by=PUBLICTRANSIT";
        Log.e("url", url);

        //해당 url로 Intent 객체 생성
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        //액티비티 시작
        startActivity(intent);
    }

    //사용자 정의 LocationListener 객체
    public class GpsLocationListener implements LocationListener {
        public void onLocationChanged(Location location) {
            //현 위치를 감지하여 위도, 경도, 고도를
            String provider = location.getProvider();
            double latitude = location.getLatitude(); //위도
            double longitude = location.getLongitude(); //경도
            double altitude = location.getAltitude(); //고도

            Log.e("위도", ""+longitude);
            Log.e("경도", ""+latitude);
            Log.e("고도", ""+altitude);

            findMap(latitude, longitude); //위도, 경도 전달
        }
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
        public void onProviderEnabled(String provider) {
        }
        public void onProviderDisabled(String provider) {
        }
    }

}
