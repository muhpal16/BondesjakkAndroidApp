package no.woact.muhpal16.bonde;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button[][] fields = new Button[3][3];

    private boolean player1Turn = true;


    private int roundCounter;

    private int player1Score;
    private int player2Score;
    private int player1draws;
    private int player2draws;
    private int player1losses;
    private int player2losses;



    DatabaseHelper myDb;

    private Chronometer mChronometer;


    private TextView player1TextView;
    private TextView player2TextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        player1TextView = findViewById(R.id.player1_text_view);
        player2TextView = findViewById(R.id.player2_text_view);

        updatePlayerStats();

        mChronometer = findViewById(R.id.timerMP);

        mChronometer.start();





        for (int i = 0; i < 3; i++) { // looper igjennom alle knappne.
            for (int j = 0; j < 3; j++) {
                String fieldID = "field_" + i + j;
                int resID = getResources().getIdentifier(fieldID, "id", getPackageName());
                fields[i][j] = findViewById(resID);
                fields[i][j].setOnClickListener(this);
            }
        }

        Button nextroundButton = findViewById(R.id.next_round_button);
        nextroundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartGame();
            }
        });

        Button addtohighscore = findViewById(R.id.addtohighscore);
        addtohighscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                dbInsertPlayer();
            }

        });


    }

    @Override
    public void onClick(View view) {
        if (!((Button) view).getText().toString().equals("")) { //Sjekker om stringene er tomme.
            return;
        }

        if (player1Turn) {
            ((Button) view).setText("X");
        } else {
            ((Button) view).setText("O");
        }

        roundCounter++;

        if (checkForWin()) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (roundCounter == 9) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }

    }

    private void restartGame() {
        //player1Turn = true;
        player1Score = 0;
        player2Score = 0;
        updatePlayerStats();
        mChronometer.setBase(SystemClock.elapsedRealtime());
    }

    private boolean checkForWin() {

        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = fields[i][j].getText().toString();

            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1]) // *
                    && field[i][0].equals(field[i][2])// * Sjekker om det er 3 på rad vannrett.
                    && !field[i][0].equals("")) { // sjekker at de ikke bare er tomme
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i]) // *
                    && field[0][i].equals(field[2][i])// * Sjekker om det er 3 på rad loddrett
                    && !field[0][i].equals("")) { // sjekker at de ikke bare er tomme
                return true;
            }
        }

        if (field[0][0].equals(field[1][1]) // *
                && field[1][1].equals(field[2][2])// * Sjekker om det er 3 på rad på diagonalen
                && !field[0][0].equals("")) { // sjekker at de ikke bare er tomme
            return true;
        }

        if (field[0][2].equals(field[1][1]) // *
                && field[1][1].equals(field[2][0])// * Sjekker om det er 3 på rad på diagonalen
                && !field[0][2].equals("")) { // sjekker at de ikke bare er tomme
            return true;
        }

        return false;

    }

    private void player1Wins() {
        Toast.makeText(this, "PLayer 1 wins!", Toast.LENGTH_SHORT).show();
        player1Score++;
        player2losses++;

        updatePlayerStats();
        resetBoard();

    }

    private void player2Wins() {
        Toast.makeText(this, "PLayer 2 wins!", Toast.LENGTH_SHORT).show();
        player2Score++;
        player1losses++;

        updatePlayerStats();
        resetBoard();
    }

    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        player1draws++;
        player2draws++;

        updatePlayerStats();
        resetBoard();
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fields[i][j].setText("");
            }
        }
        roundCounter = 0;


        //player1Turn = true;


    }


    private void updatePlayerStats() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Multiplayer");
        String playername = bundle.getString("playername");
        String playername2 = bundle.getString("playername2");


        player1TextView = findViewById(R.id.player1_text_view);
        player2TextView = findViewById(R.id.player2_text_view);

        player1TextView.setText(playername + ": " + player1Score);
        player2TextView.setText(playername2 + ": " + player2Score);



    }

    private void dbInsertPlayer() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Multiplayer");

        String player1 = bundle.getString("playername");
        String player2 = bundle.getString("playername2");

        boolean dbsjekk = myDb.insertPlayer(player1.toString(), player1Score, player1draws, player1losses);
        myDb.insertPlayer(player2.toString(), player2Score, player2draws, player2losses);


        System.out.println("dbsjekken viser " + dbsjekk);
        System.out.println("dbInsertplayer blir kalt på!");

        if(dbsjekk == true) {
            Toast.makeText(this, "Resultater har blitt lagt til i toppliste.", Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("roundCounter", roundCounter);
        outState.putInt("player1Score", player1Score);
        outState.putInt("player2Score", player2Score);
        outState.putBoolean("player1Turn", player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCounter = savedInstanceState.getInt("roundCounter");
        player1Score = savedInstanceState.getInt("player1Score");
        player2Score = savedInstanceState.getInt("player2Score");
        player1Turn = savedInstanceState.getBoolean("player1Turn");
        updatePlayerStats();
    }


}
