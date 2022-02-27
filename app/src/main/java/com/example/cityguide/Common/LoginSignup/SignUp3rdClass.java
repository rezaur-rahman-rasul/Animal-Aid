package com.example.cityguide.Common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.cityguide.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

import java.util.regex.Pattern;

public class SignUp3rdClass extends AppCompatActivity {


    Button loginButton;
    ScrollView scrollView;
    TextInputLayout signupPhoneNo;
    CountryCodePicker countryCodePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_retailer_sign_up3rd_class);

        //Hooks
        loginButton = findViewById(R.id.login_button_from_signup3);
        scrollView = findViewById(R.id.signup_3rd_screen_scroll_view);
        signupPhoneNo = findViewById(R.id.signup_phone_number);
        countryCodePicker = findViewById(R.id.country_code_picker);

    }

    //Back Pressed Button
    public void backPressed(View view) {
        SignUp3rdClass.super.onBackPressed();
    }

    //Call Login from Signup2
    public void callLoginScreenFromSignup3(View view) {
        Intent login = new Intent(getApplicationContext(), Login.class);

        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(loginButton, "transition_login");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp3rdClass.this, pairs);
            startActivity(login, options.toBundle());
        } else {
            startActivity(login);
        }
    }

    //Call Next for OTP from Signup3
    public void callOtpFromSignup3(View view) {
        if (!validatePhoneNumber()) {
            return;
        }

        //String all values passed from previous using Intent
        String _fullName = getIntent().getStringExtra("fullName");
        String _email = getIntent().getStringExtra("email");
        String _userName = getIntent().getStringExtra("userName");
        String _password = getIntent().getStringExtra("password");
        String _date = getIntent().getStringExtra("date");
        String _gender = getIntent().getStringExtra("gender");

        String _getUserEnteredPhoneNumber = signupPhoneNo.getEditText().getText().toString().trim();
        String _phoneNo = "+" + countryCodePicker.getFullNumber() + _getUserEnteredPhoneNumber;

        Intent otpScreen = new Intent(getApplicationContext(), VerifyOTP.class);

        //Pass all fields to the next Activity
        otpScreen.putExtra("fullName", _fullName);
        otpScreen.putExtra("email", _email);
        otpScreen.putExtra("userName", _userName);
        otpScreen.putExtra("password", _password);
        otpScreen.putExtra("date", _date);
        otpScreen.putExtra("gender", _gender);
        otpScreen.putExtra("phoneNo", _phoneNo);

        //Add Transition
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(scrollView, "transition_OTP_screen");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp3rdClass.this, pairs);
            startActivity(otpScreen, options.toBundle());
        } else {
            startActivity(otpScreen);
        }
    }

    private boolean validatePhoneNumber() {
        String val = signupPhoneNo.getEditText().getText().toString().trim();
        String checkSpaces = "Aw{1,20}z";
        if (val.isEmpty()) {
            signupPhoneNo.setError("Enter valid phone number");
            return false;
        } else if (val.matches(checkSpaces)) {
            signupPhoneNo.setError("No White spaces are allowed!");
            return false;
        } else {
            signupPhoneNo.setError(null);
            signupPhoneNo.setErrorEnabled(false);
            return true;
        }
    }
}