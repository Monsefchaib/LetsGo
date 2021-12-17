package com.example.letsgo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.letsgo.dao.AnnoncesDao;
import com.example.letsgo.model.Annonce;

import java.util.concurrent.ExecutionException;

public class DetailsAnnonce extends AppCompatActivity {
    TextView title,adresse,description,duree,contact,max_participants,frais;
    Annonce annonce;
    int annonce_id=1;
    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        title=findViewById(R.id.textView);
        adresse=findViewById(R.id.textView2);
        description = findViewById(R.id.textView4);
        duree=findViewById(R.id.textView);
        contact=findViewById(R.id.textView);
        max_participants=findViewById(R.id.textView5);
        frais=findViewById(R.id.textView9);
        contact=findViewById(R.id.textView12);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_annonce);

        Intent i = getIntent();
        final Annonce[] annonce = {(Annonce) i.getSerializableExtra("annonce")};

//        title.setText(annonce[0].getTitre());
//        description.setText(annonce[0].getDescription());
//        duree.setText(annonce[0].getDuree());
//        contact.setText(annonce[0].getContact());
//        max_participants.setText(annonce[0].getMax_participants());
//        frais.setText((int) annonce[0].getFrais());
//        adresse.setText(annonce[0].getAdresse());




    }


}