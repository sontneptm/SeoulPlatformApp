package com.example.seoulsharespaceproject.Login;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterUserCheck {
    //정규표현식을 사용하여 이메일,비밀번호,이름 등 사용자정보를 간단히 표기함.
    //정규표현식을 사용하려 Pattern클래스를 활용했고,
    //Pattern.compile 메소드를 사용하여 정규표현식을 안에 넣어주면됨.
    /*
        <정규 표현식>

        ^ :문자열의시작
        $ :문자열의 종료
        []:문자의 범위
        {}:횟수 또는 범위를 나타냄.
        . :임의의 한 문자(\는 넣을수 없음)
        \ :역슬래시 다음에 일반 문자가오면 특수문자로 취급하고 특수문자가오면 문자로 취급.
     */
    //matcher find메소드는 pattern에 일치하는 텍스트들이 발견되면 true를 반환.

    //이메일 검사하는 메소드
    public boolean UserEmailCheck(String val){
        Pattern pattern =Pattern.compile("^[a-z0-9._%+-]+@[A-Z0-9.-]+\\.[a-z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(val);
        return matcher.find();
    }

    //사용자 비밀번호 체크하는 함수 (소문자,숫자,특수문자 입력가능)
    public boolean UserPassWordCheck(String value)
    {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9!@.#$%^&*?_~]{4,16}$");
        Matcher matcher = pattern.matcher(value);
        return matcher.find();
    }
    //사용자 이름 체크하는 함수
    public boolean UserNameCheck(String value){
        Pattern pattern = Pattern.compile("^[a-zA-Z가-힣0-9]{2,15}$");
        Matcher matcher = pattern.matcher(value);
        return matcher.find();
    }

    //사용자 닉네임 체크하는 함수
    public boolean UserNickNameCheck(String value){
        Pattern pattern = Pattern.compile("^[a-zA-Z가-힣0-9]{2,15}$");
        Matcher matcher = pattern.matcher(value);
        return matcher.find();
    }

}
