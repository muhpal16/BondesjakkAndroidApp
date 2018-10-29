package no.woact.muhpal16.bonde;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseRegistration extends AppCompatActivity implements View.OnClickListener {


    private Button buttonRegistrer;

    private TextView textViewSignIn;
    private EditText emailTxt;
    private EditText pwTxt;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    private DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_registration);


        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        if(firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), SingleplayerActivity.class));
        }


        databaseReference = FirebaseDatabase.getInstance().getReference();



        buttonRegistrer = (Button) findViewById(R.id.reg_btn);

        textViewSignIn = (TextView) findViewById(R.id.signIn_txt);

        emailTxt = (EditText) findViewById(R.id.email_txt);
        pwTxt = (EditText) findViewById(R.id.pw_txt);


        buttonRegistrer.setOnClickListener(this);
        textViewSignIn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v == buttonRegistrer){
            registrerUser();
        }

        if(v == textViewSignIn){
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));

        }
    }

    private void registrerUser() {

        String email = emailTxt.getText().toString().trim();
        String password = pwTxt.getText().toString().trim();


        FirebaseUser user = firebaseAuth.getCurrentUser();




        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }


        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if(task.isSuccessful()){
                            Toast.makeText(FirebaseRegistration.this, "Registration successfull!", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), SingleplayerActivity.class));
                        } else {
                            Toast.makeText(FirebaseRegistration.this, "Could not registrer.. please try again.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

}
