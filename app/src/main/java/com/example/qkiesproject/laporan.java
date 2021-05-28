package com.example.qkiesproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.Map;

public class laporan extends AppCompatActivity {

    public class menu extends AppCompatActivity {
        private long backPressedTime = 0;    // used by onBackPressed()
        SharedPreferences pref;
        private FirebaseFirestore fStore;
        private User user;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_menu);
            pref = this.getSharedPreferences("AndroidHivePref", 0);
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

        public void tobutton1(View view) {
            Intent intent = new Intent(com.example.qkiesproject.laporan.this, laporan2.class);
            startActivity(intent);
        }

        public void tobutton2(View view) {
            Intent intent = new Intent(com.example.qkiesproject.laporan.this, laporan3.class);
            startActivity(intent);
        }

        public void tobutton3(View view) {
            Intent intent = new Intent(com.example.qkiesproject.laporan.this, laporan6.class);
            startActivity(intent);
        }
    }}