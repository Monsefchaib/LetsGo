package com.example.letsgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.letsgo.dao.AllAnnoncesDao;
import com.example.letsgo.model.Annonce;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ShowAllAnnonces extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_annonces);

        List<Annonce> annonces = getAnnoncesData();

        final ListView listView = (ListView) findViewById(R.id.annonceListView);
        listView.setAdapter(new CustomListAdapter(this, annonces));

        // When the user clicks on the ListItem
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Log.d("test","test ana hnaa");
                Object o = listView.getItemAtPosition(position);
                Annonce annonce = (Annonce) o;
                Intent i = new Intent(getApplicationContext(), DetailsAnnonce.class);
//                Toast.makeText(ShowAllAnnonces.this, "Selected :" + " " + annonce, Toast.LENGTH_LONG).show();
                i.putExtra("Annonce",annonce);
                startActivity(i);
            }
        });
    }

    private  List<Annonce> getAnnoncesData() {
        Log.d("get annonce data","im there");

        List<Annonce> listAnnonces = new ArrayList<Annonce>();
        try {
            listAnnonces =new AllAnnoncesDao().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return listAnnonces;
    }

}