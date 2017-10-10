package com.anand.dailyaccount.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.anand.dailyaccount.R;
import com.anand.dailyaccount.commonJavaClasses.ApplicationMainClass;
import com.anand.dailyaccount.entity.User;
import com.anand.dailyaccount.services.UserService;

import java.io.Serializable;

/**
 * Created by Anand Jakhaniya on 08-10-2017.
 * @author Anand Jakhaniya
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = ApplicationMainClass.sharedPreferences;
        Boolean loginStatus = sharedPreferences.getBoolean("loginStatus",false);
        if (loginStatus){
            String userEmailId = sharedPreferences.getString("userEmailId",null);
            UserService userService = new UserService();
            User user = userService.getUser(userEmailId);
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("user", (Serializable) user);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
