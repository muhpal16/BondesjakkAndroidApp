package no.woact.muhpal16.bonde;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartScreenActivity extends AppCompatActivity {

    private Button singleplayer;
    private Button multiplayer;
    private Button leaderboard;
    private Button logout;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        multiplayer = findViewById(R.id.multiplayerButton);
        multiplayer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openPlayerSetupMP();
            }

        });

        singleplayer = findViewById(R.id.singleplayerButton);
        singleplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSingleplayer();
            }
        });

        leaderboard = findViewById(R.id.leaderboardButton);
        leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLeaderboardActivity();
            }
        });



        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(firebaseAuth.getCurrentUser() != null){
            Toast.makeText(this, firebaseAuth.getCurrentUser().getEmail()+" has signed in.", Toast.LENGTH_SHORT).show();
        } else {
            View v = findViewById(R.id.logoutbtn);
            v.setVisibility(View.GONE);
        }


    }

    public void clickLogout(View v){
        firebaseAuth.signOut();

        if(firebaseAuth.getCurrentUser() == null){
            View b = findViewById(R.id.logoutbtn);
            b.setVisibility(View.GONE);
        }
    }

    public void openPlayerSetupMP() {
        Intent intent = new Intent(this, PlayerSetupMP.class);
        startActivity(intent);

    }

    public void openSingleplayer() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


    public void openLeaderboardActivity() {
      Intent intent = new Intent(this, Leaderboard.class);
      startActivity(intent);
    }

    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}