package com.example.letsgo.dao;

import android.os.AsyncTask;

import com.example.letsgo.model.Annonce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AllAnnoncesDao extends AsyncTask<Void, Void, List<Annonce>> {


    @Override
    protected List<Annonce> doInBackground(Void... params) {
        Annonce annonce = null;
        List<Annonce> annonces = new ArrayList<Annonce>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6458663","sql6458663","crV5tnb9Pn");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM annonce");
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                annonce = new Annonce();
                annonce.setTitre(rs.getString("titre"));
                annonce.setDescription(rs.getString("description"));
                annonce.setDuree(rs.getString("duree"));
                annonce.setFrais(rs.getFloat("frais"));
                annonce.setDuree(rs.getString("duree"));
                annonce.setMax_participants(rs.getInt("max_participants"));
                annonce.setContact(rs.getString("contact"));
//                annonce.setAdresse(rs.getString("adresse"));
                annonces.add(annonce);
            }

            System.out.println(annonces.size());

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


        return annonces ;
    }
}
