package com.example.sharespaceproject.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.sharespaceproject.Home.HomeActivity;
import com.example.sharespaceproject.R;
import com.example.sharespaceproject.Register.RegisterActivity;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    TextView idText;
    TextView passwordText;
    String userpassword;
    String userID;
    AlertDialog dialog;
    Response.Listener<String> responseListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton =(Button)findViewById(R.id.loginButton);
        Button registerButton =(Button)findViewById(R.id.registerButton);
        idText =(TextView) findViewById(R.id.idText);
        passwordText =(TextView)findViewById(R.id.passwordText);

        userpassword =passwordText.getText().toString();
        userID =idText.getText().toString();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Response.Listener<String> responseListener =new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            Log.e("success", "first");
                            JSONObject jsonResponse =new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            Log.e("success", "val :"+success);

                            if(success){//로그인 성공했으면

                                userID =jsonResponse.getString("userID");
                                userpassword=jsonResponse.getString("userPassword");

                                AlertDialog.Builder builder =new AlertDialog.Builder(LoginActivity.this);
                                dialog =builder.setMessage("로그인에 성공했습니다.")
                                        .setPositiveButton("확인",null)
                                        .create();
                                dialog.show();

                                //로그인 성공했으면 HomeActivity로 전환되게!
                                Intent intent =new Intent(LoginActivity.this, HomeActivity.class);
                                intent.putExtra("userID",userID);
                                intent.putExtra("userPassword",userpassword);
                                LoginActivity.this.startActivity(intent);

                            }else { //로그인 실패했으면

                                AlertDialog.Builder builder =new AlertDialog.Builder(LoginActivity.this);
                                dialog =builder.setMessage("계정을 다시 확인하세요.")
                                        .setNegativeButton("다시시도",null)
                                        .create();
                                dialog.show();
                            }

                        }catch (Exception e){
                            Log.e("loginButton","");
                            e.printStackTrace();
                        }
                    }
                };
                //로그인 요청하는 클래스 객체를 만들어서 아이디와 비밀번호를 전달
                LoginRequest loginRequest =new LoginRequest(userID,userpassword,responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent를 만들어서 변수는 (현재액티비티LoginActivity.this 해도됨,넘어갈액티비티이름)
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                //LoginActivity.this.setIntent(registerIntent);
                startActivity(registerIntent);
                //정상적으로 보내지게되면 결과로나온 response가 jsonResponse를 통해 다뤄지게됨.
            }
        });
    }
    //현재 엑티비티가 종료됬으면
    protected void onStop(){
        super.onStop();
        if(dialog != null){ //현재 다이얼로그가 켜져있으면 한번에 꺼지지 않게 해주는 센스
            dialog.dismiss();
            finish();
        }
    }
}
