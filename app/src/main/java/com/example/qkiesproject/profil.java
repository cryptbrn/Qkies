package com.example.qkiesproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.List;

public class profil extends AppCompatActivity {
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        getSupportActionBar().hide();

        pref = this.getSharedPreferences("AndroidHivePref",0);
        Intent intent = getIntent();
        User value = intent.getParcelableExtra("profile");
        TextView name = findViewById(R.id.etName);
        TextView email = findViewById(R.id.etEmail);
        TextView phone = findViewById(R.id.etPhone);
        TextView divisi = findViewById(R.id.etDivisi);

        name.setText(value.name);
        email.setText(value.email);
        phone.setText(value.phone);
        divisi.setText(value.divisi);

        TextView logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = pref.edit();
                editor.putBoolean("logged",false);
                editor.commit();
                Intent i = new Intent(profil.this, MainActivity.class); // Your list's Intent
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Adds the FLAG_ACTIVITY_NO_HISTORY flag
                startActivity(i);

            }
        });
    }
}