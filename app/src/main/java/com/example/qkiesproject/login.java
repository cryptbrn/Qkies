package com.example.qkiesproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        pref = this.getSharedPreferences("AndroidHivePref",0);
        Button button = findViewById(R.id.btnLOGIN);
        TextView daftar = findViewById(R.id.tvDaftar);
        EditText etUsername = findViewById(R.id.etUsername);
        EditText etPassword = findViewById(R.id.etPassword);

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, register.class));
            }
        });
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // dapatin value dari inputan user
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                // cek apakah username dan password terisi
                if (username.length() > 0 && password.length() > 0) {
                    loginWithEmailAndPassword(username, password);
                } else {
                    Toast.makeText(login.this, "Fill all field!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loginWithEmailAndPassword (String username, String password) {
        mAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("login", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            // Pindah halaman
                            Intent intent = new Intent(login.this,menu.class);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putBoolean("logged",true);
                            editor.commit();
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("login", "signInWithEmail:failure", task.getException());
                            Toast.makeText(login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
