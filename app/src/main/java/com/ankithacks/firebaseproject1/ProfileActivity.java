package com.ankithacks.firebaseproject1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.ankithacks.firebaseproject1.R.id.buttonSave;
import static com.ankithacks.firebaseproject1.R.id.editTextDisplayName;


public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {


    private DatabaseReference databaseReference;
    private EditText editTextName,editTextAddress;
    private FirebaseAuth mAuth;

    private Button buttonSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        databaseReference= FirebaseDatabase.getInstance().getReference();
        editTextAddress=(EditText)findViewById(R.id.edit1);
        editTextName=(EditText)findViewById(R.id.editTextDisplayName);
        buttonSave=(Button)findViewById(R.id.buttonSave);
        mAuth = FirebaseAuth.getInstance();

        buttonSave.setOnClickListener(this);
    }


    private void saveUserInfo()
    {
        String add=editTextAddress.getText().toString().trim();
        String name=editTextName.getText().toString().trim();

        customDatabaseclass cd=new customDatabaseclass(name,add);
        FirebaseUser user=mAuth.getCurrentUser();
        databaseReference.child(user.getUid()).setValue(cd);
        Toast.makeText(this,"Information Saved",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onClick(View view) {
        if(view==buttonSave)
            saveUserInfo();
    }
}
