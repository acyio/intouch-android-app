package com.example.databasetutorial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.databasetutorial.DBHandler;
import com.example.databasetutorial.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updating__database);

        DBHandler db = new DBHandler(this);

// Inserting Shop/Rows
        Log.d("Insert: ", "Inserting ..");
        db.addShop(new Shop(1, "Dockers", " 475 Brannan St #330, San Francisco, CA 94107, United States"));
        db.addShop(new Shop(2, "Dunkin Donuts", "White Plains, NY 10601"));
        db.addShop(new Shop(3, "Pizza Porlar", "North West Avenue, Boston , USA"));
        db.addShop(new Shop(4, "Town Bakers", "Beverly Hills, CA 90210, USA"));

// Reading all shops
        Log.d("Reading: ", "Reading all shops..");
        List<Shop> shops = db.getAllShops();

        for (Shop shop : shops) {
            String log = "Id: " + shop.getId() + " ,Name: " + shop.getName() + " ,Address: " + shop.getAddress();
// Writing shops to log
            Log.d("Shop: : ", log);
        }
    }
}