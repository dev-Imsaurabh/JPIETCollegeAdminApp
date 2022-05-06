 package com.example.jpietcollegeadminapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.jpietcollegeadminapp.DeleteNotice.DeleteNoticeActivity;
import com.example.jpietcollegeadminapp.DeleteNotice.UploadNotice;
import com.example.jpietcollegeadminapp.faculty.UpdateFaculty;


 public class MainActivity extends AppCompatActivity implements View.OnClickListener {
     CardView uploadNotice,uploadImage,addPDF,addFaculty,deleteNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uploadNotice = findViewById(R.id.addNotice);
        uploadNotice.setOnClickListener(this);

        uploadImage=findViewById(R.id.addGallery);
        uploadImage.setOnClickListener(this);

        addPDF=findViewById(R.id.upload);
        addPDF.setOnClickListener(this);

        addFaculty=findViewById(R.id.faculty);
        addFaculty.setOnClickListener(this);

        deleteNotice=findViewById(R.id.deleteNotice);
        deleteNotice.setOnClickListener(this);
    }

     @Override
     public void onClick(View v) {
        switch(v.getId()){

            case R.id.addNotice:
                Intent intent= new Intent(MainActivity.this, UploadNotice.class);
                startActivity(intent);
                break;
            case R.id.addGallery:
               Intent intent1= new Intent(MainActivity.this,UploadImage.class);
                startActivity(intent1);
                break;
            case R.id.upload:
                Intent intent2= new Intent(MainActivity.this,UploadPDF.class);
                startActivity(intent2);
                break;
            case R.id.faculty:
                Intent intent3= new Intent(MainActivity.this, UpdateFaculty.class);
                startActivity(intent3);
                break;

            case R.id.deleteNotice:
                Intent intent4= new Intent(MainActivity.this, DeleteNoticeActivity.class);
                startActivity(intent4);
                break;

        }

     }
 }