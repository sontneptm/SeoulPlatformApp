package com.example.seoulsharespaceproject.Login;

import android.os.Bundle;

import com.example.seoulsharespaceproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Map;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText emailText, passwordText, passwordcheckText, nameText, nickNameText;
    private RadioGroup genderGroup;
    AlertDialog dialog;
    AlertDialog.Builder builder;

    //firebaseAuth 사용
    private FirebaseAuth mAuth = FirebaseAuth.getInstance(); // null 에러 안나게 getInstance()해줌.
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private FirebaseUser user = mAuth.getCurrentUser();
    private Map<String, Object> userMap;

    private String email,name,nickname;
    //유저 정보가 정해진 형식에 맞게 작성됬나를 판단해주는 usercheck
    RegisterUserCheck userCheck = new RegisterUserCheck();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailText = (EditText)findViewById(R.id.emailText);
        passwordText = (EditText)findViewById(R.id.passwordText);
        passwordcheckText =(EditText) findViewById(R.id.passwordCheckText);
        nameText = (EditText)findViewById(R.id.nameText);
        nickNameText =(EditText) findViewById(R.id.nickNameText);
        genderGroup = (RadioGroup)findViewById(R.id.genderGroup);

        emailText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                String edit =editable.toString();
                if(userCheck.UserEmailCheck(edit)){
                    if(emailText.getText().toString().equals(email)){
                        Toast.makeText(RegisterActivity.this,"중복된 이메일",Toast.LENGTH_SHORT).show();
                        emailText.setBackgroundResource(R.drawable.storkebutton_red);
                    }
                    else
                        emailText.setBackgroundResource(R.drawable.strokebutton_primary);
                }
            }
        });
        //패스워드 리스너
        passwordText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String edit = editable.toString();
                if (userCheck.UserPassWordCheck(edit)) {
                    passwordText.setBackgroundResource(R.drawable.strokebutton_primary);
                } else {
                    if (edit.length() != 0)
                        passwordText.setBackgroundResource(R.drawable.storkebutton_red);
                    else
                        passwordText.setBackgroundResource(R.drawable.strokebutton_width);
                }
                //패스워드와 패스워드 중복확인이 같나 확인
                if (passwordText.getText().toString().equals(passwordcheckText.getText().toString())) {
                    passwordcheckText.setBackgroundResource(R.drawable.storkebutton_red);
                } else {
                    passwordcheckText.setBackgroundResource(R.drawable.strokebutton_primary);
                }
            }
        });
        //이름 리스너
        nameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String edit = editable.toString();
                if (userCheck.UserNameCheck(edit)) {
                    if(nameText.getText().toString().equals(name)){
                        nameText.setBackgroundResource(R.drawable.storkebutton_red);
                        Toast.makeText(RegisterActivity.this,"중복된 이름",Toast.LENGTH_SHORT).show();
                    }
                    nameText.setBackgroundResource(R.drawable.strokebutton_primary);
                } else {
                    if (edit.length() != 0)
                        nameText.setBackgroundResource(R.drawable.storkebutton_red);
                    else
                        nameText.setBackgroundResource(R.drawable.strokebutton_width);
                }
            }
        });



       nickNameText.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

           @Override
           public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

           @Override
           public void afterTextChanged(Editable editable) {
               String edit = editable.toString();
               if(userCheck.UserNickNameCheck(nickNameText.getText().toString())){
                   if(nickNameText.getText().toString().equals(nickname)){
                       Toast.makeText(RegisterActivity.this, "중복된 닉네임 입니다.", Toast.LENGTH_SHORT).show();
                       nickNameText.setBackgroundResource(R.drawable.storkebutton_red);
                   }else{
                       nickNameText.setBackgroundResource(R.drawable.strokebutton_primary);
                   }
               }
           }
       });
        genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                FirebasePostUser newUser=null; //유저 정보를 가지고있는 FirebasePostUser클래스로 유저생성
                if (i == R.id.women) {
                    newUser = new FirebasePostUser(user.getUid(), emailText.getText().toString(), passwordText.getText().toString(),
                            "여성", nameText.getText().toString(), nickNameText.getText().toString());
                } else if (i == R.id.men) {
                    newUser = new FirebasePostUser(user.getUid(), emailText.getText().toString(), passwordText.getText().toString(),
                            "남성", nameText.getText().toString(), nickNameText.getText().toString());
                }
                userMap = newUser.toMap();
            }
        });
       findViewById(R.id.registerButton).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //앱이 실행되면 데이터베이스에있는 정보를 가져옴
       /* mStore.collection(FirebaseKey.user)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult() != null) {
                                for (DocumentSnapshot snap : task.getResult()) {
                                    Map<String, Object> temp = snap.getData();
                                    email = String.valueOf(temp.get(FirebaseKey.email));
                                    nickname = String.valueOf(temp.get(FirebaseKey.nickname));
                                    Log.d("email:",email);
                                    Log.d("nickname:",nickname);
                                }
                            }
                        }
                    }
                });*/

        mStore.collection(FirebaseKey.user)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult() != null) {
                                for (DocumentSnapshot snap : task.getResult()) {

                                   /* Map<String, Object> temp = snap.getData();
                                    tempname = String.valueOf(temp.get(FirebaseKey.name));
                                    tempnick = String.valueOf(temp.get(FirebaseKey.nickname));
                                    tempemail = String.valueOf(temp.get(FirebaseKey.email));*/
                                    FirebasePostUser temp =snap.toObject(FirebasePostUser.class);
                                    name = temp.getName();
                                    email =temp.getNickname();
                                    nickname =temp.getEmail();

                                }
                            }
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        mAuth.createUserWithEmailAndPassword(emailText.getText().toString().trim(), passwordText.getText().toString().trim())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if (user != null) {
                                //FirebaseStore db에 정보 넣기

                                mStore.collection(FirebaseKey.user).document(user.getUid()).set(userMap, SetOptions.merge()); //데이터 더 들어왔을때 덮어쓰기
                                finish();
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "가입 실패", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}


