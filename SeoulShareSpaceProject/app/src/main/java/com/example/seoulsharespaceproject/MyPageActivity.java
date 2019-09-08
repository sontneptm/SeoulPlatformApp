package com.example.seoulsharespaceproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Camera;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.seoulsharespaceproject.Login.FirebaseKey;
import com.example.seoulsharespaceproject.Login.RegisterUserCheck;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyPageActivity extends AppCompatActivity implements View.OnClickListener {

    Camera camera;
    CircleImageView profileImage;
    EditText nickName, email, name, password, passwordCheck;
    ImageView checkNickname, checkPassword, checkConfirmPassword;
    RegisterUserCheck check;

    //현재 사용자의 정보가져오기
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance(); //데이터베이스


    //데이터베이스에 저장되있는 user컬랙션에서 현재 유저 정보를
    String tempnick, tempname, tempemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        findViewById(R.id.mypage_confirmButton).setOnClickListener(this);
        //유저 정보 설정
        profileImage = findViewById(R.id.mypageProfileImage);
        nickName = findViewById(R.id.mypageNickname);
        email = findViewById(R.id.mypage_email);
        name = findViewById(R.id.mypage_name);
        password = findViewById(R.id.mypage_password);
        passwordCheck = findViewById(R.id.mypagePasswordCheck);
        checkNickname = findViewById(R.id.checkNickname);
        checkPassword = findViewById(R.id.checkPassword);
        checkConfirmPassword = findViewById(R.id.checkPasswordConfirm);

        //가져온 유저 데이터 가지고 text 채워넣기
        nickName.setText(tempnick);
        email.setText(user.getEmail());
        name.setText(user.getDisplayName());

        //패스워드 리스너
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String edit = editable.toString();

                if (check.UserPassWordCheck(edit)) {
                    checkPassword.setVisibility(View.VISIBLE);
                } else {
                    checkPassword.setVisibility(View.INVISIBLE);
                }
            }
        });
        passwordCheck.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String edit = editable.toString();
                if (check.UserPassWordCheck(edit)) {
                    checkConfirmPassword.setVisibility(View.VISIBLE);
                } else {
                    checkConfirmPassword.setVisibility(View.INVISIBLE);
                }
            }
        });

        //카메라 권한 요청하기 코드
        //선택한 사진 데이터 처리코드


    }
    @Override
    public void onClick(View view) {
        //마이페이지에서 confirm버튼이 눌리면
        if (view.getId() == R.id.mypage_confirmButton) {
            if (password.getText().toString().equals(passwordCheck.getText().toString())) {
                //데이터 수정
                HashMap<String,Object> mPassword =new HashMap<String, Object>() {};
                mPassword.put(FirebaseKey.password,password.getText().toString());
                //같은 Uid에 수정된 정보 덮어씌우기
                mStore.collection(FirebaseKey.user).document(user.getUid()).update(mPassword);

            } else {
                Toast.makeText(this, "패스워드가 다릅니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
