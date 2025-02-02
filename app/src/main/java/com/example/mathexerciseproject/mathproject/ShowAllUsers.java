package com.example.mathexerciseproject.mathproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.mathexerciseproject.R;

import java.util.ArrayList;

public class ShowAllUsers extends AppCompatActivity {
    private RecyclerView rcShowFruits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_users);
        rcShowFruits = findViewById(R.id.rcShowFruit);


        ArrayList<Fruit> fruits = new ArrayList<>();
        fruits.add(new Fruit("Banana", R.drawable.img_1));
        fruits.add(new Fruit("Apple", R.drawable.img_2));
        fruits.add(new Fruit("Fruits", R.drawable.img_3));
        fruits.add(new Fruit("Grapes", R.drawable.img_4));
        fruits.add(new Fruit("Lemon", R.drawable.img_5));

        FruitAdapter fruitAdapter = new FruitAdapter(fruits, new FruitAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Fruit item) {
                Toast.makeText(ShowAllUsers.this, item.getName(), Toast.LENGTH_LONG).show();
            }
        });
        rcShowFruits.setLayoutManager(new LinearLayoutManager(this));
        rcShowFruits.setAdapter(fruitAdapter);
        rcShowFruits.setHasFixedSize(true);
    }


}