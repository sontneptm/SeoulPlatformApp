package com.example.seoulsharespaceproject.Login;

import com.google.firebase.firestore.Exclude;

import java.util.HashMap;
import java.util.Map;

public class FirebasePostUser {
    //documentId는 database에서의 PRIMARY-KEY역할
    String documentId;
    String email;
    String password;
    String gender;
    String name;
    String nickname;

    public FirebasePostUser(){}

    public FirebasePostUser(String documentId,String email,String password,String gender,String name,String nickname){
        this.documentId = documentId;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.name = name;
        this.nickname = nickname;
    }

    @Exclude
    public Map<String , Object> toMap(){
        HashMap<String,Object> userMap = new HashMap<>();
        userMap.put(FirebaseKey.documentId,documentId);
        userMap.put(FirebaseKey.email,email);
        userMap.put(FirebaseKey.password,password);
        userMap.put(FirebaseKey.gender,gender);
        userMap.put(FirebaseKey.name,name);
        userMap.put(FirebaseKey.nickname,nickname);

        return userMap;
    }
    public String getNickname() {
        return nickname;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDocumentId() {
        return documentId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }
}
