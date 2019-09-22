package com.example.seoulsharespaceproject.Search;

import android.graphics.Bitmap;


public class ShareSpace {
    String adress;  // 주소
    String personnel; // 개인,단체
    String purpose ;//대여하려는 목적
    int cost; //비용

    int review ;//리뷰수
    int popularity; //인기수
    int distance; //거리

    String distinguish;//서비스 구분 (연계,자체)
    String minclassnm;//소분류명
    String title; //장소 명
    String pay;//결제방법 (유료&무료)
    String quickUrl ; //바로가기 URL
    String nx,ny; //경도,위도
    String serviceStart,serviceEnd;//서비스 시작,서비스 종료일
    String receiptStart,receiptEnd;//접수 시작,접수 종료일
    String city; //지역 명
    String imgURL; // 이미지경로
    String document;//상세정보
    String phoneNumber ;//전화번호
    String timeStart,timeEnd;//서비스 이용시간, 종료시간
    String cancleterm;//취소기간

    //json에서 받아온 정보 한 객체로 만들기
    Bitmap bitmap ;
    public ShareSpace(){}

    public ShareSpace(String imgUrl, String adress, String personnel, String purpose,int cost) {
        this.imgURL =imgUrl;
        this.adress = adress;
        this.personnel = personnel;
        this.purpose = purpose;
        this.cost = cost;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPersonnel() {
        return personnel;
    }

    public void setPersonnel(String personnel) {
        this.personnel = personnel;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getDistinguish() {
        return distinguish;
    }

    public void setDistinguish(String distinguish) {
        this.distinguish = distinguish;
    }



    public String getMinclassnm() {
        return minclassnm;
    }

    public void setMinclassnm(String minclassnm) {
        this.minclassnm = minclassnm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getQuickUrl() {
        return quickUrl;
    }

    public void setQuickUrl(String quickUrl) {
        this.quickUrl = quickUrl;
    }

    public String getNx() {
        return nx;
    }

    public void setNx(String nx) {
        this.nx = nx;
    }

    public String getNy() {
        return ny;
    }

    public void setNy(String ny) {
        this.ny = ny;
    }

    public String getServiceStart() {
        return serviceStart;
    }

    public void setServiceStart(String serviceStart) {
        this.serviceStart = serviceStart;
    }

    public String getServiceEnd() {
        return serviceEnd;
    }

    public void setServiceEnd(String serviceEnd) {
        this.serviceEnd = serviceEnd;
    }

    public String getReceiptStart() {
        return receiptStart;
    }

    public void setReceiptStart(String receiptStart) {
        this.receiptStart = receiptStart;
    }

    public String getReceiptEnd() {
        return receiptEnd;
    }

    public void setReceiptEnd(String receiptEnd) {
        this.receiptEnd = receiptEnd;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getCancleterm() {
        return cancleterm;
    }

    public void setCancleterm(String cancleterm) {
        this.cancleterm = cancleterm;
    }
    public Bitmap getBitmap(){
        return bitmap;
    }
    public void setBitmap(Bitmap bitmap){
        this.bitmap =bitmap;
    }
}
