package com.example.qkiesproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class menu extends AppCompatActivity {
    private long backPressedTime = 0;    // used by onBackPressed()
    SharedPreferences pref;
    private FirebaseFirestore fStore;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        pref = this.getSharedPreferences("AndroidHivePref",0);
        fStore = FirebaseFirestore.getInstance();
        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference document = fStore.collection("users").document(id);
        document.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                user = documentSnapshot.toObject(User.class);
            }
        });
    }

    public void toSensor(View view) {
        Intent intent = new Intent(menu.this, sensor.class);
        startActivity(intent);
    }

    public void toKalkulator(View view) {
        Intent intent = new Intent(menu.this, kalkulator.class);
        startActivity(intent);
    }

    public void toLaporan(View view) {
        Intent intent = new Intent(menu.this,laporan.class);
        startActivity(intent);
    }

    public void toProfil(View view) {
        Intent intent = new Intent(menu.this, profil.class);
        intent.putExtra("profile",user);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        long t = System.currentTimeMillis();
        if (t - backPressedTime > 2000) {
            backPressedTime = t;
            Toast.makeText(this, "Press back again to logout",
                    Toast.LENGTH_SHORT).show();
        } else {
            super.onBackPressed();
            return;
        }
    }
}

