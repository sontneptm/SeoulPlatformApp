package com.example.sharespaceproject.Register;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.sharespaceproject.Login.LoginActivity;
import com.example.sharespaceproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    //안드로이드에서 데이터를 받아서 데이터베이스로 넘겨줄 userID,userPassword,userGender,userEmail변수
    String userID="";
    String userPassword="";
    String userGender="";
    String userEmail="";
    AlertDialog dialog; //알림창 보여주는 dialog
    AlertDialog.Builder builder;

    //로그인 성공인지 여부를 알려주는 논리형 변수 success
    boolean validate=false;//아이디가 중복인지 체크해주는 논리형변수
    int genderGroupID;
    RadioButton genderButton;

    Button registerButton;//회원가입 버튼
    Button validateButton; //중복체크 버튼
    EditText emailText;
    EditText passwordText;
    EditText idText;
    RadioGroup genderGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        idText =(EditText)findViewById(R.id.idText);
        passwordText =(EditText)findViewById(R.id.passwordText);
        emailText =(EditText)findViewById(R.id.emailText);
        validateButton =(Button)findViewById(R.id.validateButton);
        genderGroup =(RadioGroup)findViewById(R.id.genderGroup);
        registerButton=(Button)findViewById(R.id.registerButton);
        /*genderGroupID =genderGroup.getCheckedRadioButtonId();
        userGender=((RadioButton)findViewById(genderGroupID)).getText().toString();
        */

        genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                //genderGroup버튼 누르면 버튼이 무엇인지 찾고 gender에 넘겨줌
                genderButton =(RadioButton)findViewById(i);//성별 뭐 선택했는지 받아서
                userGender =genderButton.getText().toString();//문자열로 바꿔서 userGender에 저장
            }
        });


        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userID =idText.getText().toString(); //idText에 입력된 id를 받아서 userID에 저장

               /* if(validate) { //아이디가 중복이면
                    builder =new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("중복 된 아이디입니다.")
                            .setNegativeButton("확인",null)
                            .create();
                    dialog.show();
                    return;
                }*/
                if(userID.equals("")){ //아이디가 빈칸이면
                    builder= new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("아이디는 빈 칸일수 없습니다.")
                            .setPositiveButton("확인",null)
                            .create(); // dialog생성
                    dialog.show(); //생성된 dialog보여주고 끝내기
                    return;
                }

                //아이디가 중복도아니고 빈칸도아니면 responseListener를 통해 JSONObject를 만들어서
                //상황에 맞는 dialog를 띄워줌
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonresponse = new JSONObject(response);
                            //JSONObject jsonresponse1 = new JSONObject(statement);
                            boolean success =jsonresponse.getBoolean("success");
                            Log.e("중복",""+jsonresponse);
                            if(success) {
                                //사용할 수 있는 아이디이면
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("사용할 수 있는 아이디입니다.")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();
                                idText.setEnabled(false); //아이디 못바꾸게
                                validate = true; //아이디가 만들어졌으므로 validate는 true
                                idText.setBackgroundColor(getResources().getColor(R.color.colorGray));
                                validateButton.setBackgroundColor(getResources().getColor(R.color.colorGray));
                            }else{ //사용할 수 없는 아이디이면
                                builder =new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("중복 된 아이디입니다.")
                                        .setNegativeButton("확인",null)
                                        .create();
                                dialog.show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                //실제로 중복요청하기위해 객체를 만들어 생성된 userID를 보내 다음부터는 중복이 뜨게함.
                ValidateRequest validateRequest =new ValidateRequest(userID,responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(validateRequest);
            }
        });
        //회원가입 버튼이 눌렸을때
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //변수 값을 받아와서
                userID =idText.getText().toString();
                userPassword =passwordText.getText().toString();
                userEmail =emailText.getText().toString();

                if(!validate) {//중복 체크가 되어있지않다면
                    AlertDialog.Builder builder =new AlertDialog.Builder(RegisterActivity.this);
                    dialog =builder.setMessage("먼저 중복체크를 해주세요")
                            .setNegativeButton("확인",null)
                            .create();
                    dialog.show();
                    return ;
                }
                //하나라도 빈공간이있으면 회원가입 불가이므로 체크해주는 if문
                if(userID.equals("")|| userPassword.equals("")||genderGroup.equals("")||userEmail.equals("")){
                    AlertDialog.Builder builder =new AlertDialog.Builder(RegisterActivity.this);
                    dialog =builder.setMessage("빈 칸 없이 입력해주세요")
                            .setNegativeButton("확인",null)
                            .create();
                    dialog.show();
                    return;
                }
                //모든 조건을 만족했으면 회원등록 성공 dialog를 띄움
                Response.Listener<String> responseListener =new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                           boolean success = jsonResponse.getBoolean("success");
                           Log.e("register", ""+success);

                            if(success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("회원 등록에 성공했습니다.")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();
                                Log.e("성공","");

                               //finish(); //액티비티를 종료시켜(회원등록창을 닫아) 로그인화면으로 가게함.
                            }
                            else {

                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog= builder.setMessage("회원가입에 실패하였습니다.")
                                        .setNegativeButton("확인", null)
                                        .create();
                                dialog.show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                //회원등록 진행
                RegisterRequest registerRequest =new RegisterRequest(userID,userPassword,userGender,userEmail,responseListener);
                RequestQueue queue =Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });
    }

    @Override
    protected void onStop(){
        //회원등록이뤄진 이후에 회원등록창이꺼지면 실행되는 메소드
        super.onStop();
        if(dialog!=null){
            dialog.dismiss();
            dialog=null;
        }
    }

}
