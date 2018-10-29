package no.woact.muhpal16.bonde;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;


public class PlayerSetupMP extends AppCompatActivity implements PlayernameFragment.OnPlayernameSendListener {

    EditText player1;
    EditText player2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_setup_mp);

        player1 = findViewById(R.id.txt_message);
        player2 = findViewById(R.id.txt_player2);


        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            PlayernameFragment playernameFragment = new PlayernameFragment();

            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, playernameFragment, null).commit(); // legger fragmentet i activityen

        }
    }


    @Override
    public void onPlayernameSend(String playername, String playername2) {



        Bundle bundle = new Bundle();
        bundle.putString("playername", playername);
        bundle.putString("playername2", playername2);



        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Multiplayer", bundle);
        startActivity(intent);


    }
}
