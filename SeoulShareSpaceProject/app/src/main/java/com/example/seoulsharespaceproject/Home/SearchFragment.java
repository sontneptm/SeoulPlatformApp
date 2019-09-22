package com.example.seoulsharespaceproject.Home;


import android.app.ProgressDialog;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.seoulsharespaceproject.R;
import com.example.seoulsharespaceproject.Search.ShareSpace;
import com.example.seoulsharespaceproject.Search.SpaceAdapter;
import com.google.api.Context;
import com.google.api.Http;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment implements View.OnClickListener {
    private RecyclerView.LayoutManager layoutManager;
    private SpaceAdapter adapter;
    private RecyclerView recyclerView;
   List<ShareSpace> datas;
    private View view;
    ShareSpace space;

    public SearchFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        datas =new ArrayList<>();
       // datas.add(new ShareSpace(R.drawable.kongyoo,"kongyoo","kongyoo","kongyoo",2));
        view= inflater.inflate(R.layout.fragment_search, container, false);
        view.findViewById(R.id.searchButton).setOnClickListener(this);
        ParseFetch parseFetch =new ParseFetch();
        parseFetch.execute();
        //setup RecycerView
        recyclerView =(RecyclerView)view.findViewById(R.id.search_recyclerView);
        adapter = new SpaceAdapter(datas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return view;

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onStart() {
        ProgressDialog Loading = new ProgressDialog(getContext());
        super.onStart();
        Loading.setMessage("\tLoading");
        Loading.setCancelable(false);
        Loading.show();
    }
}

 class ParseFetch extends AsyncTask<String,String,String> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        String json = "";
        try {
            //json파일 열어 읽어오기
            InputStream is = getAssets().open("place1.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    protected void onPostExecute(String json) {
          super.onPostExecute(json);

        try {
            //json파일에서 object,array분리
            JSONObject jsonObject = new JSONObject(json);
            JSONObject ListPublicReservationInstitution = (JSONObject) jsonObject.getJSONObject("ListPublicReservationInstitution");
            JSONArray row = (JSONArray) ListPublicReservationInstitution.getJSONArray("row");

            for (int i = 0; i < row.length(); i++) {

                JSONObject placeInfo = row.getJSONObject(i);
                ShareSpace shareSpace = new ShareSpace();

                //json 받아 객체로 만들기

                shareSpace.setImgURL(placeInfo.getString("IMGURL"));
                shareSpace.setDistinguish(placeInfo.getString("GUBUN"));
                shareSpace.setMinclassnm(placeInfo.getString("MINCLASSNM"));
                shareSpace.setTitle(placeInfo.getString("SVCNM"));
                shareSpace.setPay(placeInfo.getString("PAYATNM"));
                shareSpace.setQuickUrl(placeInfo.getString("SVCURL"));
                shareSpace.setServiceStart(placeInfo.getString("SVCOPNBGNDT"));
                shareSpace.setServiceEnd(placeInfo.getString("SVCOPNENDDT"));
                shareSpace.setReceiptStart(placeInfo.getString("RCPTBGNDT"));
                shareSpace.setReceiptEnd(placeInfo.getString("RCPTENDDT"));
                shareSpace.setCity(placeInfo.getString("AREANM"));
                shareSpace.setDocument(placeInfo.getString("DTLCONT"));

                //x,y정보가있으면
                if (placeInfo.has("X") && placeInfo.has("Y")) {
                    shareSpace.setNx(placeInfo.getString("X"));
                    shareSpace.setNy(placeInfo.getString("Y"));
                }
                //영업 시작,종료시간이 있으면
                if (placeInfo.has("V_MIN") && placeInfo.has("V_MAX")) {
                    shareSpace.setTimeStart(placeInfo.getString("V_MIN"));
                    shareSpace.setTimeEnd(placeInfo.getString("V_MAX"));
                }
                if (placeInfo.has("REVSTDDAY")) {
                    shareSpace.setCancleterm(placeInfo.getString("REVSTDDAY"));
                }
                if (placeInfo.has("TELNO")) {
                    shareSpace.setPhoneNumber(placeInfo.getString("TELNO"));
                }
                Log.e("JSONObject", String.valueOf(placeInfo));
                //IMGURL로 Bitmap생성

                Bitmap bitmap = null;

//                    Uri uri = Uri.parse(shareSpace.getImgURL());
//
//                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                try {
                    URL url = new URL(shareSpace.getImgURL());
                    URLConnection conn = url.openConnection();
                    conn.connect();
                    int nSize = conn.getContentLength();
                    BufferedInputStream bis = new BufferedInputStream(conn.getInputStream(), nSize);
                    bitmap = BitmapFactory.decodeStream(bis);
                    bis.close();
                    shareSpace.setBitmap(bitmap);
                }catch (Exception e){
                    e.printStackTrace();
                }
                datas.add(shareSpace);
            }
            Log.e("length", String.valueOf(row.length()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

