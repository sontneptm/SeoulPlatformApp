package com.example.sharespaceproject.Login;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    //UserLogin.php파일에 파라메터들을 보내서 회원가입시켜라 라는 요청을 보내는 클래스
    final static private String URL ="https://minsun1269.cafe24.com/UserLogin.php";
    private Map<String,String> parameters;

    public LoginRequest(String userID,String userPassword, Response.Listener<String> listener){
        //해당 URL의 파라메터들을 POST방식으로 보내줘라
        super(Method.POST,URL,listener,null);
        parameters =new HashMap<>();
        parameters.put("userID",userID);
        parameters.put("userPassword",userPassword);
    }
    public String getUrl(){
        return URL;
    }
    public Map<String, String> getParameters() {
        return parameters;
    }
}
