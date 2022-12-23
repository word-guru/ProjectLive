package com.example.firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

//class MyClickListener implements View.OnClickListener {
//    @Override
//    public void onClick() {
//
//    }
//}

public class MainActivity extends AppCompatActivity {

    @SuppressLint({"MissingInflatedId", "Range"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.btn_one);


//        btn.setOnClickListener(view -> {
//            Toast.makeText(this, R.string.hello_world, Toast.LENGTH_SHORT).show();
//        });
        btn.setText("My custom for click");
        btn.setTextColor(Color.rgb(140,200,300));


        ImageButton imageButton = findViewById(R.id.btn_two);
        imageButton.setOnClickListener(view -> {
            Toast.makeText(this,"Click on image",Toast.LENGTH_LONG).show();
        });
    }


    public void onMyClick(View view) {
        // R.layout.activity_main;
        // R.color.
        // R.string.
        //getApplicationContext()
        //Toast toast = Toast.makeText(this, "Hello world!", Toast.LENGTH_LONG);
        //toast.show();

        // Toast.makeText(this, "Hello world!", Toast.LENGTH_LONG).show();
        //Toast.makeText(this, R.string.hello_world, Toast.LENGTH_SHORT).show();
        //Snackbar.make(view, R.string.hello_world, Snackbar.LENGTH_LONG).show();
        //Toast.LENGTH_LONG = 3500ms;

        Toast toast = Toast.makeText(this,R.string.hello_world,Toast.LENGTH_LONG);
        //toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT,0,250);
        //toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.BOTTOM,0,0);
        LinearLayout toastConteiner = (LinearLayout)toast.getView();
        TextView textView = (TextView) toastConteiner.getChildAt(0);
        textView.setTextSize(35);

        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setPadding(10,30,10,10);
        imageView.setImageResource(R.drawable.ic_baseline_accessibility_24);

        toastConteiner.addView(imageView,0);
        toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.BOTTOM,0,0);

        toast.show();
        Log.i("main_activity", "MyLog");
    }

}