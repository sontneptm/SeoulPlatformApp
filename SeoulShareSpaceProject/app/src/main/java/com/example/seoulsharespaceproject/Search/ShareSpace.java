package com.example.seoulsharespaceproject.Search;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import java.net.URL;

public class ShareSpace {
    Drawable imageUrl;
    String adress;  // 주소
    String personnel; // 개인,단체
    String purpose ;//대여하려는 목적
    int cost; //비용

    int review ;//리뷰수
    int popularity; //인기수
    int distance; //거리

    public ShareSpace(){}

    public ShareSpace(Drawable imageUrl, String adress, String personnel, String purpose,int cost) {
        this.imageUrl = imageUrl;
        this.adress = adress;
        this.personnel = personnel;
        this.purpose = purpose;
        this.cost = cost;
    }

    public Drawable getImage() {
        return imageUrl;
    }

    public void setImage(Drawable imageUrl) {
        this.imageUrl = imageUrl;
    }
}
