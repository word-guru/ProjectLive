package com.example.projectlive;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Camera;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;


import java.util.List;
import java.util.Locale;

//public class MainActivity extends AppCompatActivity {
//
//    Button button;
//    private Camera camera;
//    private boolean isFlashOn;
//    private boolean hasFlash;
//    android.hardware.Camera.Parameters params;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        button= findViewById(R.id.btn_flash);
//
//
//
//        Dexter.withActivity(this)
//                .withPermissions(Manifest.permission.CAMERA)
//                .withListener(new MultiplePermissionsListener(){
//
//            @Override
//            public void onPermissionsChecked(MultiplePermissionsReport report) {
//
//            }
//
//            @Override
//            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
//
//            }
//
//        }).check();
//
//
//        hasFlash = getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
//        GetCamera();
//        if(!hasFlash){
//            getSupportActionBar().setTitle("No Flash");
//            //Toast.makeText(this,"No flash",Toast.LENGTH_LONG).show();
//        }else{
//            GetCamera();
//        }
//
//    }
//
//    private void GetCamera(){
//        if(camera == null){
//            try{
//                camera = Camera.open();
//            }catch(Exception ex){
//                getSupportActionBar().setTitle("Camera Error");
//            }
//        }
//
//        button.setOnClickListener(view -> {
//            if(!isFlashOn){
//                getSupportActionBar().setTitle("Flash on");
//                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xff00dded));
//                turnOnFlash();
//            }else{
//                getSupportActionBar().setTitle("Flash off");
//                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xffddee00));
//                trunOffFlash();
//            }
//        });
//    }
//
//    private void turnOnFlash(){
//        if(!isFlashOn){
//            if(camera==null || !hasFlash){
//                return;
//            }
//            params = camera.getParameters();
//            params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
//            camera.setParameters(params);
//            camera.startPreview();
//            isFlashOn = true;
//        }
//    }
//
//    private void trunOffFlash(){
//        if(!isFlashOn){
//            if(camera==null || hasFlash){
//                return;
//            }
//            params = camera.getParameters();
//            params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
//            camera.setParameters(params);
//            camera.stopPreview();
//            isFlashOn = false;
//        }
//    }
//}

public class MainActivity extends AppCompatActivity {
    Button button;
    private Camera camera;
    private boolean isFlashOn;
    private boolean hasFlash;
    android.hardware.Camera.Parameters params;
    int count = 0;
    EditText editSub;
    EditText editContent;

    //@SuppressLint("MissingInflatedId")
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // button = findViewById(R.id.btn_flash);

       // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//поворот экрана
//        Intent returnInt = getIntent();
//        if(returnInt!=null){
//            count = getIntent().getIntExtra("count",count);
//        }

        findViewById(R.id.btn_one).setOnClickListener(view->{
            count++;
            getSupportActionBar().setTitle("Count: " + count);
           // Toast.makeText(this, Integer.toString(count), Toast.LENGTH_SHORT).show();
        });

        findViewById(R.id.btn_two).setOnClickListener(view -> {
            Intent intent = new Intent(this,SecondActivity.class);
            intent.putExtra("count",count);
            //startActivity(intent);
            startActivityForResult(intent,200);
        });

        editSub = findViewById(R.id.edit_sub);
        editContent = findViewById(R.id.edit_content);

        findViewById(R.id.btn_three).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT,editSub.getText().toString());
            intent.putExtra(Intent.EXTRA_TEXT,editContent.getText().toString());
            startActivity(intent);
        });

        findViewById(R.id.btn_fpur).setOnClickListener(v -> {
            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,editContent.getText().toString());
            sendIntent.setType("text/plain");

            List<ResolveInfo> resolveInfos = getPackageManager().queryIntentActivities(sendIntent,0);
            StringBuilder str =new StringBuilder(200);

            for (ResolveInfo resolve:resolveInfos){
                String currentApp = resolve.activityInfo.packageName;
                str.append(currentApp+"\n");

                if(currentApp.contains("telegram")){
                    PackageManager pm = getApplicationContext().getPackageManager();
                    Intent launchIntent = pm.getLaunchIntentForPackage(currentApp);
                    startActivity(launchIntent);
                }
            }
            Log.i("intent_list",str.toString());
        });

        findViewById(R.id.btn_rec).setOnClickListener(v -> {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Скажите что-нибудь");
            startActivityForResult(intent,2);
        });

        Log.i("life_cycle","MainActivity -> onCreate");
//        Dexter.withActivity(this)
//                .withPermissions(Manifest.permission.CAMERA)
//                .withListener(new MultiplePermissionsListener() {
//                    @Override
//                    public void onPermissionsChecked(MultiplePermissionsReport report) {
//
//                    }
//
//                    @Override
//                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
//
//                    }
//                }).check();
//        hasFlash = getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
//        if (!hasFlash) {
//            getSupportActionBar().setTitle("No Flash");
//            //Toast.makeText(this, "No Flash!", Toast.LENGTH_LONG).show();
//        } else {
//            GetCamera();
//        }
//
//        button.setOnClickListener(view -> {
//            if (!isFlashOn) {
//                getSupportActionBar().setTitle("Flash On!");
//                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xff00dded));
//                TurnOnFlash();
//            } else {
//                getSupportActionBar().setTitle("Flash Off");
//                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xffddee00));
//                TurnOffFlash();
//            }
//        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 200 && data != null){
            Intent returnInt = getIntent();
       if(data!=null){
            count = getIntent().getIntExtra("count",count);
           getSupportActionBar().setTitle("Count: " + count);
       }else if(resultCode == 2 && resultCode == 2 && data != null){


       }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        Log.i("life_cycle","MainActivity -> onSaveInstanceState");
        outState.putInt("count",count);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        Log.i("life_cycle","MainActivity -> onRestoreInstanceState");
        count = savedInstanceState.getInt("count",0);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onStart() {
        Log.i("life_cycle","MainActivity -> onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.i("life_cycle","MainActivity -> onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i("life_cycle","MainActivity -> onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i("life_cycle","MainActivity -> onStop");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.i("life_cycle","MainActivity -> onRestart");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.i("life_cycle","MainActivity -> onDestroy");
        super.onDestroy();
    }

    //    private void GetCamera() {
//        if (camera == null) {
//            try {
//                camera = Camera.open();
//            } catch (Exception ex) {
//                getSupportActionBar().setTitle("CameraError!");
//            }
//        }
//    }
//
//    private void TurnOnFlash() {
//        if (!isFlashOn) {
//            if (camera == null || !hasFlash) {
//                return;
//            }
//            params = camera.getParameters();
//            params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
//            camera.setParameters(params);
//            camera.startPreview();
//            isFlashOn = true;
//        }
//    }
//
//    private void TurnOffFlash() {
//        if (isFlashOn) {
//            if (camera == null || !hasFlash) {
//                return;
//            }
//            params = camera.getParameters();
//            params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
//            camera.setParameters(params);
//            camera.startPreview();
//            isFlashOn = false;
//        }
//
//    }
}

//    public void ImageClick(View view)
//    {
//        ImageButton imageButton = findViewById(R.id.btn_two);
//        Toast.makeText(this, "Click on image button!", Toast.LENGTH_SHORT).show();
//    }
//    public void onMyClick(View view) {
//        // R.layout.activity_main;
//        //R.color
//        //R.string.
//        //getApplicationContext()
//        //Toast.makeText(this, 20+1)
//        Toast toast = Toast.makeText(this, R.string.Text_toast, Toast.LENGTH_SHORT);
//        //toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT, 0 , 100); центровка вывода
//        LinearLayout toastContainer = (LinearLayout) toast.getView();
//        TextView textView = (TextView) toastContainer.getChildAt(0);
//        textView.setTextSize(24);
//
//        ImageView imageView = new ImageView(getApplicationContext());
//        imageView.setPadding(10,20,10,20);
//        imageView.setImageResource(R.drawable.ic_baseline_anchor_24);
//
//        toastContainer.addView(imageView, 1);
//        toast.setGravity(Gravity.FILL_HORIZONTAL|Gravity.BOTTOM, 0, 0);
//        toast.show();
//        Log.i("main_activity", "call on MyClick!");
//        //Snackbar.make(view, "Hello Men!", Snackbar.LENGTH_SHORT).show();
//    }
//}