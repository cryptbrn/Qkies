package com.example.qkiesproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.Map;

public class laporan extends AppCompatActivity {

    EditText etTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan);

        etTitle = findViewById(R.id.etTitle);
    }

    public void addReport(View view) {
        String title = etTitle.getText().toString();

        addReportToDatabase(title);
    }

    private void addReportToDatabase (String title) {
        Map<String, Object> report = new HashMap<>();
        report.put("title", title);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("reports")
                .add(report)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(laporan.this, "Data berhasil disimpan!",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(laporan.this, "Data gagal disimpan!",
                                Toast.LENGTH_SHORT).show();
                        Log.w("Nambah data", "Error adding document", e);
                    }
                });
    }
}