package com.planetinnovative.pi_database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        names = new ArrayList<>();

        //Initialize Realm Database
        Realm.init(getApplicationContext());
        RealmConfiguration config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm mReal = Realm.getInstance(config);

        //Begin Transaction to store
        mReal.beginTransaction();
        UserDb db = mReal.createObject(UserDb.class, new Random().nextInt());
        db.setter("ramesh", "Ramesh 2");
        mReal.commitTransaction();



        //Deleting Database
        /*
        mReal = Realm.getInstance(config);
        mReal.beginTransaction();
        mReal.delete(UserDb.class);
        mReal.commitTransaction();
        mReal.close();
        */

        //Update Database
        UserDb userDb = new UserDb();
        userDb.setterWithPK(1,"Is","asdfasdf");
        mReal.beginTransaction();
        mReal.insertOrUpdate(userDb);
        mReal.commitTransaction();


        //Reading from database
        RealmResults<UserDb> results = mReal.where(UserDb.class).findAll();
        for (int i = 0; i < results.size(); i++) {
            names.add(results.get(i).getName());
            System.out.println(results.get(i).getId());
        }

        ListView listView = findViewById(R.id.listView);
        ArrayAdapter<UserDb> adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, names);
        listView.setAdapter(adapter);
    }
}
