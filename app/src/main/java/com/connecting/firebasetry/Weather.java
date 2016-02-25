package com.connecting.firebasetry;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by user on 2/25/2016.
 */
public class Weather extends AppCompatActivity implements View.OnClickListener{

    Button refreshID_java;
    TextView placeID_java;
    TextView temperatureID_java;
    Firebase firebase_url1,firebase_url2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);

        Firebase.setAndroidContext(this);

        placeID_java = (TextView) findViewById(R.id.placeID);
        temperatureID_java = (TextView) findViewById(R.id.temperatureID);
        refreshID_java = (Button) findViewById(R.id.refreshButtonID);
        firebase_url1 = new Firebase("https://radiant-heat-4494.firebaseio.com/weather_temperature");
        firebase_url2 = new Firebase("https://radiant-heat-4494.firebaseio.com/weather_place");


        refreshID_java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Refreshing...", Toast.LENGTH_LONG).show();

               refresh_temperature();
                refresh_place();

                Toast.makeText(getBaseContext(), "Refreshed", Toast.LENGTH_LONG).show();
            }
        });


    }


    private void refresh_place() {
//        firebase_url = new Firebase("https://radiant-heat-4494.firebaseio.com/weather_place");
        firebase_url2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String SuperData1 = (String) dataSnapshot.getValue();
                placeID_java.setText(SuperData1);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void refresh_temperature() {


   //     firebase_url = new Firebase("https://radiant-heat-4494.firebaseio.com/weather_temperature");
        firebase_url1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String SuperData2 = (String) dataSnapshot.getValue();
                temperatureID_java.setText(SuperData2);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }


    @Override
    public void onClick(View v) {

    }
}
