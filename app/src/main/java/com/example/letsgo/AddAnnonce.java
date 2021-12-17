package com.example.letsgo;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.letsgo.model.Annonce;

public class AddAnnonce extends AppCompatActivity {
    public static final int PICK_IMAGE = 100;
    // Connection connection;
    EditText titre,description,dateevenement,duree,frais,contact,maxParticipant;
    String titreString,descriptionString,dateevenementString,dureeString,contactString;
    Integer maxParticipantString;
    Double fraisString;
    Button saveButton,imageButton;
    ImageView image;
    Uri imageUri;
    private void createAnnonce(Annonce a){
        String query = "INSERT INTO annonce VALUES (" + a.getTitre() + "," + a.getDescription() + ",'" + a.getDate_evenement() + "',"
                + a.getDuree() + "," + a.getMax_participants() + ",'" + a.getFrais() + "'," + a.getContact()+")";
        DBConnection.executeQuery(query);
    }
    private void updateAnnonce(Integer id){
           /* String query = "UPDATE  annonce SET titre  = '" + tfclient.getText() + "', prixVente = '" + tfprixdevente.getText() + "', quantite = " +
                    tfquantite.getText() + " WHERE id = " + tfidproduit.getText() + "";
            DBConnection.executeQuery(query);*/
    }
    private void deleteVente(Integer id){
        String query = "DELETE FROM annonce WHERE id = " + id + "";
        DBConnection.executeQuery(query);
    }
    public void pickImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            image.setImageURI(imageUri);
            Log.d("imaage", imageUri.toString());
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.addannonce);
        saveButton = (Button) findViewById(R.id.buttonSave);
        imageButton = (Button) findViewById(R.id.buttonImage);
        image =(ImageView) findViewById(R.id.imageView2);



        titre =(EditText) findViewById(R.id.editTextTextPersonName2);
        description =(EditText) findViewById(R.id.discription_et);
        duree =(EditText) findViewById(R.id.editTextTextPersonName3);
        frais =(EditText) findViewById(R.id.editTextTextPersonName4);
        contact =(EditText) findViewById(R.id.editTextPhone);
        maxParticipant =(EditText) findViewById(R.id.editTextNumber);
        dateevenement =(EditText) findViewById(R.id.editTextDate);
        imageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pickImage();
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Task().execute();
            }
        });

    }

    class Task extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            titreString = titre.getText().toString();
            descriptionString = description.getText().toString();
            dureeString = duree.getText().toString();
            contactString = contact.getText().toString();
            maxParticipantString = Integer.parseInt(maxParticipant.getText().toString());
            fraisString = Double.parseDouble(frais.getText().toString());

            java.util.Date date=new java.util.Date();
            java.sql.Date sqlDate=new java.sql.Date(date.getTime());

            Annonce a = new Annonce(6,titreString,descriptionString,sqlDate,dureeString,maxParticipantString,null,fraisString,"",contactString);
            String query = "INSERT INTO annonce VALUES (null,'" + a.getTitre() + "','" + a.getDescription() + "','" + a.getDate_evenement() + "','"
                    + a.getDuree() + "'," + a.getMax_participants() + "," + a.getFrais() + ",'" + a.getContact()+"','"+ imageUri.toString() +"',2)";
            DBConnection.executeQuery(query);
            /*
            Connection connection = DBConnection.getConnection();
            try {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM annonce");
                Annonce annonce;
                ArrayList<Annonce> listAnnonce = new ArrayList<>();
                while (rs.next()) {
                    annonce = new Annonce(rs.getInt("id_annonce"),rs.getString("titre"),
                            rs.getString("description"),rs.getDate("date_evenement"),
                            rs.getString("duree"),rs.getInt("max_participants"),rs.getDouble("frais"),rs.getString("contact"));
                    listAnnonce.add(annonce);
                    /*for (Annonce a:listAnnonce) {
                        records+= a.getTitre();
                    }
                    records += rs.getString(2) + " " + rs.getString(3) + "\n";
                }
            } catch (Exception e) {
                error = e.toString();
            }*/
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
         /*   test.setText(records);
            if (error != "")
                Log.d("STATER", error);
                errorText.setText(error);*/
            super.onPostExecute(aVoid);
        }
    }
}