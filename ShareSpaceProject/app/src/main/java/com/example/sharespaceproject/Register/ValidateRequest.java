package com.example.sharespaceproject.Register;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ValidateRequest extends StringRequest {

    //UserValidate.php파일에 파라메터들을 보내서 회원가입시켜라 라는 요청을 보내는 클래스
    final static private String URL ="https://minsun1269.cafe24.com/UserValidate.php";
    private Map<String,String> parameters;

    public ValidateRequest(String userID,Response.Listener<String> listener) {
        // userID를 사이트로 보내 회원가입이 가능한 아이디인지 확인
        //userID는 PRIMARY KEY이기 때문에 중복될 수 없음.
        super(Method.POST,URL,listener,null);
        parameters =new HashMap<>();
        parameters.put("userID",userID);
    }

    public Map<String, String> getParameters() {
        return parameters;
    }
}
