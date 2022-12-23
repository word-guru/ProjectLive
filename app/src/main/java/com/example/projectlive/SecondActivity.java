package com.example.projectlive;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {
    int count = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Log.i("life_cycle","SecondActivity -> onCreate");

        count = getIntent().getIntExtra("count",0);
        getSupportActionBar().setTitle("Count: " + count);

        findViewById(R.id.btn_one).setOnClickListener(v -> {
//            Intent intent =new Intent(this,MainActivity.class);
//            intent.putExtra("count",count*10);
//            startActivity(intent);
            Intent intent =new Intent();
            intent.putExtra("count",count*10);
            setResult(200,intent);
            finish();
        });
    }
    @Override
    protected void onStart() {
        Log.i("life_cycle","SecondActivity -> onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.i("life_cycle","SecondActivity -> onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i("life_cycle","SecondActivity -> onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i("life_cycle","SecondActivity -> onStop");
        super.onStop();
    }
    @Override
    protected void onRestart() {
        Log.i("life_cycle","SecondActivity -> onRestart");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.i("life_cycle","SecondActivity -> onDestroy");
        super.onDestroy();
    }
}