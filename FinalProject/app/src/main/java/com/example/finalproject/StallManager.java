package com.example.finalproject;


import android.content.Context;

import org.androidannotations.annotations.EBean;

import io.realm.Realm;
import io.realm.RealmResults;

@EBean
public class StallManager {
    Realm realm = Realm.getDefaultInstance();

    Context context;

    public StallManager(Context context)
    {
        this.context = context;
    }

    public void save(String stallName){
        // Instantiate Plain-Old-Java-Object
        Stall newStall = new Stall();

        // Set parameters of object
        newStall.setStallName(stallName);

        // Save to realm
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(newStall);
        realm.commitTransaction();

    }

    public void delete(Stall stall)
    {
        realm.beginTransaction();

        try{
            stall.deleteFromRealm();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        realm.commitTransaction();
    }

    public RealmResults<Stall> findAll(){
        RealmResults<Stall> stalls = realm.where(Stall.class).findAll();
        return stalls;
    }

    public Stall checkStalls(String stallName)
    {
        Stall stall = realm.where(Stall.class).equalTo("stallName", stallName)
                .findFirst();
        return stall;
    }

}
