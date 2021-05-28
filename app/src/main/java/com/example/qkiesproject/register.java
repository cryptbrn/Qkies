package com.example.qkiesproject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class register extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private String mEmail;
    private String mFullname;
    private String mPassword;
    private String mPasswordConfirm;
    private String mPhoneNumber;
    private String mDivisi;
    private Boolean canRegist = true;
    private FirebaseFirestore fStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        Log.d("ojan",mAuth.toString());

        Button button = findViewById(R.id.btnRegister);
        EditText etFullName = findViewById(R.id.etFullName);
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etPassword = findViewById(R.id.etPassword);
        EditText etConfirmPassword = findViewById(R.id.etPasswordConfirmation);
        EditText etPhoneNumber = findViewById(R.id.etTelfon);
        EditText etDivisi = findViewById(R.id.etDivisi);
        fStore = FirebaseFirestore.getInstance();
        Log.d("ojan", String.valueOf(fStore.enableNetwork().isSuccessful()));
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // dapatin value dari inputan user
                mEmail = etEmail.getText().toString();
                mPassword = etPassword.getText().toString();
                mFullname = etFullName.getText().toString();
                mPasswordConfirm = etConfirmPassword.getText().toString();
                mPhoneNumber = etPhoneNumber.getText().toString();
                mDivisi = etDivisi.getText().toString();

                // cek apakah username dan password terisi
                if (mEmail.length() > 0 && mPassword.length() > 0 && mPasswordConfirm.length() > 0 && mFullname.length() > 0
                && mPhoneNumber.length() > 0 && mDivisi.length() > 0) {
                    if (!mPassword.equals(mPasswordConfirm)){
                        canRegist = false;
                        Toast.makeText(register.this, "Password tidak sesuai",
                                Toast.LENGTH_SHORT).show();
                    }
                    if (validate(mEmail)){
                        canRegist = false;
                           Toast.makeText(register.this, "Email tidak sesuai format",
                                Toast.LENGTH_SHORT).show();
                    }
                    if (canRegist){
                        loginWithEmailAndPassword(mEmail , mPassword);
                    }
                } else {
                    Toast.makeText(register.this, "Fill all field!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loginWithEmailAndPassword (String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("ojan", "createUserWithEmail:success");
                            String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            DocumentReference document = fStore.collection("users").document(id);
                            Map<String ,Object> _user = new HashMap<>();
                            _user.put("name",mFullname);
                            _user.put("email",mEmail);
                            _user.put("divisi",mDivisi);
                            _user.put("phone",mPhoneNumber);
                            Log.d("ojan", document.getFirestore().toString());
                            document.set(_user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("ojan","sukses");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("ojan",e.toString());
                                }
                            }).addOnCanceledListener(new OnCanceledListener() {
                                @Override
                                public void onCanceled() {
                                    Log.d("ojan","cancel");
                                }
                            });
                            finish();
                        } else {
                            Log.w("ojan", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(register.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$");

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
}
