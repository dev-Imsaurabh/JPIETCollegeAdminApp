package com.example.jpietcollegeadminapp.DeleteNotice;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.example.jpietcollegeadminapp.R;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewAdapter> {
    private Context context;
    private ArrayList<NoticeData> list;

    public NoticeAdapter(Context context, ArrayList<NoticeData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NoticeViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.newsfeed_item_layout,parent,false);
        return new NoticeViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewAdapter holder, int position) {

        NoticeData currentItem= list.get(position);
        holder.deleteNoticeTitle.setText(currentItem.getTitle());


        try {
            if(currentItem.getImage() !=null){
               //Glide.with(context).load("https://firebasestorage.googleapis.com/v0/b/jpiet-admin-app.appspot.com/o/gallery%2F%5BB%40f00fc8fjpg?alt=media&token=777c1c50-c12a-4226-99c7-090f2907ba4d").into(holder.deleteNoticeImage);

                Picasso.get().load(currentItem.getImage()).into(holder.deleteNoticeImage);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.deleteNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(context);
                builder.setMessage("Are you sure want to delete this Notice");
                builder.setCancelable(true);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("Notice");
                        reference.child(currentItem.getKey()).removeValue()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();

                            }
                        });
                        notifyItemRemoved(position);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                   AlertDialog dialog=null;
                try {
                    dialog= builder.create();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(dialog!=null) {
                    dialog.show();

                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NoticeViewAdapter extends RecyclerView.ViewHolder {
        private Button deleteNotice;
        private TextView deleteNoticeTitle;
        private ImageView deleteNoticeImage;

        public NoticeViewAdapter(@NonNull View itemView) {
            super(itemView);
            deleteNotice=itemView.findViewById(R.id.deleteNotice);
            deleteNoticeTitle=itemView.findViewById(R.id.deleteNoticeTitle);
            deleteNoticeImage=itemView.findViewById(R.id.deleteNoticeImage);

        }
    }
}
