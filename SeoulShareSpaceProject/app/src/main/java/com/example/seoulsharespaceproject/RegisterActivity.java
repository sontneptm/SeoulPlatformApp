package com.example.seoulsharespaceproject;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText emailText, passwordText;
    AlertDialog dialog;
    RadioGroup genderGroup;
    String userGender="";
    AlertDialog.Builder builder;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance(); // null 에러 안나게 getInstance()해줌.
    private FirebaseFirestore db =FirebaseFirestore.getInstance();
    private FirebaseUser user = mAuth.getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);
      /*  int genderGroupID =genderGroup.getCheckedRadioButtonId();
        userGender=((RadioButton)findViewById(genderGroupID)).getText().toString();
*/
        findViewById(R.id.registerButton).setOnClickListener(this);
        findViewById(R.id.validateButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.registerButton :
                mAuth.createUserWithEmailAndPassword(emailText.getText().toString().trim(), passwordText.getText().toString().trim())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.e("user?",String.valueOf(user));
                                    if(user!=null) {
                                        Map<String, Object> userMap = new HashMap<>(); //Firestore에 넣을 HashMap
                                        userMap.put(FirebaseKey.documentId, user.getUid());
                                        userMap.put(FirebaseKey.email, emailText.getText().toString());
                                        userMap.put(FirebaseKey.password, passwordText.getText().toString());
                                        // userMap.put(FirebaseKey.gender, userGender);
                                        db.collection(FirebaseKey.user).document(user.getUid()).set(userMap, SetOptions.merge()); //데이터 더 들어왔을때 덮어쓰기
                                        finish();
                                    }
                                } else {
                                    Log.d("exception? : ",task.getException()+"");
                                    Toast.makeText(RegisterActivity.this, "가입 실패", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                break;
            case R.id.validateButton :
                db.collection("user")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        if(document.getId() == user.getUid()){
                    //내가 보낼 userId와 db에 담긴 user의 Id와 같은게있으면 ->중복
                                            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                            dialog= builder.setMessage("중복된 사용자가 있습니다.")
                                                    .setNegativeButton("확인", null)
                                                    .create();
                                            dialog.show();
                                        }
                                    }
                                }
                            }
                        });
                break;

        }
    }
}
