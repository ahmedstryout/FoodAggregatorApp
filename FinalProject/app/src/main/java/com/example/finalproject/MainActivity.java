package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.listener.multi.BaseMultiplePermissionsListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.activity_main)
    public class MainActivity extends AppCompatActivity{
        @ViewById
        TextView loginText;

        @ViewById
        EditText usernameText;

        @ViewById
        EditText passwordText;

        @ViewById
        Button loginButton;

        @ViewById
        Button registerButton;

        @Bean
        UserManager realm;


    @AfterViews
    public void checkPermissions()
    {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.CAMERA

                )

                .withListener(new BaseMultiplePermissionsListener()
                {
                    public void onPermissionsChecked(MultiplePermissionsReport report)
                    {
                        if (report.areAllPermissionsGranted())
                        {
                            init();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "You must provide permissions for app to run", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }
                })
                .check();
    }

        public void init(){
            loginText.setText("LOGIN");
        }

        @Click(R.id.loginButton)
        public void login(){
            String username = usernameText.getText().toString();
            String password = passwordText.getText().toString();


            User userLoggingIn = realm.checkUsers(username);

            if(username.matches("admin") && password.matches("admin")){
                UserList_.intent(this).start();
            }

            else if(username.matches("") || password.matches(""))
            {
                Toast toast = Toast.makeText(this, "Please fill up all fields", Toast.LENGTH_SHORT);
                toast.show();
            }

            else if (userLoggingIn != null && userLoggingIn.getPassword().equals(password))
                {

                    finish();
                    Home_.intent(this).start();
                }


            else{
                Toast toast = Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT);
                toast.show();
            }
        }

        @Click(R.id.registerButton)
        public void register(){
        Register_.intent(this).start();
    }
    }

