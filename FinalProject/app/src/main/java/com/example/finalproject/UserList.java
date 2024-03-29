package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import io.realm.RealmResults;

@EActivity(R.layout.activity_user_list)
public class UserList extends AppCompatActivity {

    @ViewById
    RecyclerView userRecyclerView;

    @Bean
    UserManager realm;

    @ViewById
    Button backList;




    @Click(R.id.backList)
    public void next()
    {
        MainActivity_.intent(this).start();
    }




    @AfterViews
    public void init(){
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        userRecyclerView.setLayoutManager(mLayoutManager);

        RealmResults<User> displayusers = realm.findAll();

        // set adapter
        UserAdapter ua = new UserAdapter(this,displayusers);
        userRecyclerView.setAdapter(ua);

    }

    public void delete(User object)
    {
        realm.delete(object);
    }

}

