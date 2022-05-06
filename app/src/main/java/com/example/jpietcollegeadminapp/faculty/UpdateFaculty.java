package com.example.jpietcollegeadminapp.faculty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.jpietcollegeadminapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public class UpdateFaculty extends AppCompatActivity {
    private FloatingActionButton fab;
    private RecyclerView csDepartment,mechDepartment,elecDepartment;
    private LinearLayout csNoData,mechNoData,elecNoData;
    private List<TeacherData> list1,list2,list3;
    private DatabaseReference reference ,dbRef;
    private TeacherAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_faculty);
        csDepartment=findViewById(R.id.csDepartment);
        mechDepartment=findViewById(R.id.mechDepartment);
        elecDepartment=findViewById(R.id.elecDepartment);

        csNoData=findViewById(R.id.csNoData);
        mechNoData=findViewById(R.id.mechNoData);
        elecNoData=findViewById(R.id.elceNoData);

        reference= FirebaseDatabase.getInstance().getReference().child("teachers");
        csDepartment();
        mechDepartment();
        elecDepartment();

        fab=findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateFaculty.this,AddTeacherActivity.class);
                startActivity(intent);
            }
        });
    }



    private void csDepartment () {
        dbRef=reference.child("CSE branch");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list1=new ArrayList<>();
                if(!snapshot.exists()){
                    csNoData.setVisibility(View.VISIBLE);
                    csDepartment.setVisibility(View.GONE);

                }else{
                    csNoData.setVisibility(View.GONE);
                    csDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1:snapshot.getChildren()){
                        TeacherData data =snapshot1.getValue(TeacherData.class);
                        list1.add(data);

                    }
                    csDepartment.setHasFixedSize(true);
                    csDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter=new TeacherAdapter(list1,UpdateFaculty.this,"CSE branch");
                    csDepartment.setAdapter(adapter);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this,error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void elecDepartment () {
        dbRef = reference.child("Electrical branch");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list2 = new ArrayList<>();
                if (!snapshot.exists()) {
                    elecNoData.setVisibility(View.VISIBLE);
                    elecDepartment.setVisibility(View.GONE);

                } else {
                    elecNoData.setVisibility(View.GONE);
                    elecDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        TeacherData data = snapshot1.getValue(TeacherData.class);
                        list2.add(data);

                    }
                    elecDepartment.setHasFixedSize(true);
                    elecDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list2, UpdateFaculty.this,"Electrical branch");
                    elecDepartment.setAdapter(adapter);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void mechDepartment() {
        dbRef=reference.child("Mechanical");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list3=new ArrayList<>();
                if(!snapshot.exists()){
                    mechNoData.setVisibility(View.VISIBLE);
                    mechDepartment.setVisibility(View.GONE);

                }else{
                    mechNoData.setVisibility(View.GONE);
                    mechDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1:snapshot.getChildren()){
                        TeacherData data =snapshot1.getValue(TeacherData.class);
                        list3.add(data);

                    }
                    mechDepartment.setHasFixedSize(true);
                    mechDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter=new TeacherAdapter(list3,UpdateFaculty.this,"Mechanical");
                    mechDepartment.setAdapter(adapter);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this,error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }







}