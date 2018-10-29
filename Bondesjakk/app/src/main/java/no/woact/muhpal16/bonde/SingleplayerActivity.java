package no.woact.muhpal16.bonde;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

public class SingleplayerActivity extends AppCompatActivity implements View.OnClickListener {


    private Button[][] field = new Button[3][3];
    private String[][] compareFieldText = new String[3][3];

    private boolean player1Turn = true;


    private int roundCounter;

    private int player1Score;
    private int botScore;


    private TextView player1TextView;
    private TextView botTextView;

    private Chronometer mChronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleplayer);

        mChronometer = findViewById(R.id.timerSP);
        mChronometer.start();

        player1TextView = findViewById(R.id.player2_text_view);
        botTextView     = findViewById(R.id.bot_text_view);





        for (int i = 0; i < 3; i++) {  // looper igjennom alle knappene
            for (int j = 0; j < 3; j++) {
                String fieldID = "field_" + i + j;
                int resID = getResources().getIdentifier(fieldID, "id", getPackageName());
                field[i][j] = findViewById(resID);
                field[i][j].setOnClickListener(this);
            }
        }

        Button buttonReset = findViewById(R.id.reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                restartGame();
            }

        });




    }

    @Override
    public void onClick(View v) {
        clickBtn((Button) v);
    }


    public void clickBtn(Button btn) {
        if (!btn.getText().toString().equals("")) { // sjekker om knappen er trykt.
            return;
        }


        if (player1Turn) {
            (btn).setText("X");
            player1Turn = false;
            updateFields();

            if (player1Turn == false) {
                botWin();
            }

            if (player1Turn == false) {
                botDefend();
            }
            if (player1Turn == false) {

                botMove();
            }


        } else {

            (btn).setText("O");

        }


        roundCounter++;
        if (checkForWin()) {
            if (player1Turn) {
                player1Wins();
            } else {
                botWins();
            }
        } else if (roundCounter == 9) {
            draw();
        }

        System.out.println(roundCounter);

    }

    private Button[][] getButtons() {
        return field;
    }


    private void botWin() {
        System.out.println("botWin() blir kalt på....");

        for (int i = 0; i < 3; i++) {
            if (compareFieldText[i][0].equals("O") && compareFieldText[i][1].equals("O") && compareFieldText[i][2].equals("")) {        // vannrett.
                clickBtn(getButtons()[i][2]);
                System.out.println("botWin() blir kalt på 1");
                player1Turn = true;
            } else if (compareFieldText[i][1].equals("O") && compareFieldText[i][2].equals("O") && compareFieldText[i][0].equals("")) { // ^
                clickBtn(getButtons()[i][0]);
                player1Turn = true;
                System.out.println("botWin() blir kalt på 2");
            } else if (compareFieldText[i][2].equals("O") && compareFieldText[i][0].equals("O") && compareFieldText[i][1].equals("")) { // ^
                clickBtn(getButtons()[i][1]);
                System.out.println("botWin() blir kalt på 3");
                player1Turn = true;
            } else if (compareFieldText[1][i].equals("O") && compareFieldText[0][i].equals("O") && compareFieldText[2][i].equals("")) {     // Loddrett
                clickBtn(getButtons()[2][i]);
                System.out.println("botWin() blir kalt på 4");
                player1Turn = true;
            } else if (compareFieldText[1][i].equals("O") && compareFieldText[2][i].equals("O") && compareFieldText[0][i].equals("")) { // ^
                clickBtn(getButtons()[0][i]);
                System.out.println("botWin() blir kalt på 5");
                player1Turn = true;
            } else if (compareFieldText[0][i].equals("O") && compareFieldText[2][i].equals("O") && compareFieldText[1][i].equals("")) { // ^
                clickBtn(getButtons()[1][i]);
                System.out.println("botWin() blir kalt på 6");
                player1Turn = true;
            } else if (compareFieldText[0][0].equals("O") && compareFieldText[1][1].equals("O") && compareFieldText[2][2].equals("")) {   // Diagonalen venstre til høyre
                clickBtn(getButtons()[2][2]);
                System.out.println("botWin() blir kalt på 7");
                player1Turn = true;
            } else if (compareFieldText[1][1].equals("O") && compareFieldText[2][2].equals("O") && compareFieldText[0][0].equals("")) {  // ^
                clickBtn(getButtons()[0][0]);
                System.out.println("botWin() blir kalt på 8");
                player1Turn = true;
            } else if (compareFieldText[0][0].equals("O") && compareFieldText[2][2].equals("O") && compareFieldText[1][1].equals("")) {  // ^
                clickBtn(getButtons()[1][1]);
                System.out.println("botWin() blir kalt på 9 ");
                player1Turn = true;
            } else if (compareFieldText[1][1].equals("O") && compareFieldText[0][2].equals("O") && compareFieldText[2][0].equals("")) {   // Diagonalen høyre til venstre
                clickBtn(getButtons()[2][0]);
                System.out.println("botWin() blir kalt på10");
                player1Turn = true;
            } else if (compareFieldText[2][0].equals("O") && compareFieldText[1][1].equals("O") && compareFieldText[0][2].equals("")) {  // ^
                clickBtn(getButtons()[0][2]);
                System.out.println("botWin() blir kalt på 11 ");
                player1Turn = true;
            } else if (compareFieldText[2][0].equals("O") && compareFieldText[0][2].equals("O") && compareFieldText[1][1].equals("")) {  // ^
                clickBtn(getButtons()[1][1]);
                System.out.println("botWin() blir kalt på 1");
                player1Turn = true;
            }
        }
    }

    private void botDefend() {

        System.out.println("BotDefend() blir kalt på ......");

        for (int i = 0; i < 3; i++) {
            if (compareFieldText[i][0].equals("X") && compareFieldText[i][1].equals("X") && compareFieldText[i][2].equals("")) {        // vannrett.
                clickBtn(getButtons()[i][2]);
                System.out.println("botDefend() 1");
                player1Turn = true;
            } else if (compareFieldText[i][1].equals("X") && compareFieldText[i][2].equals("X") && compareFieldText[i][0].equals("")) { // ^
                clickBtn(getButtons()[i][0]);
                System.out.println("botDefend() blir kalt på 2");
                player1Turn = true;
            } else if (compareFieldText[i][0].equals("X") && compareFieldText[i][2].equals("X") && compareFieldText[i][1].equals("")) { // ^
                clickBtn(getButtons()[i][1]);
                System.out.println("botDefend() blir kalt på 3");
                player1Turn = true;
            } else if (compareFieldText[0][i].equals("X") && compareFieldText[1][i].equals("X") && compareFieldText[2][i].equals("")) {     // Loddrett
                clickBtn(getButtons()[2][i]);
                System.out.println("botDefend() blir kalt på 4 ");
                player1Turn = true;
            } else if (compareFieldText[1][i].equals("X") && compareFieldText[2][i].equals("X") && compareFieldText[0][i].equals("")) { // ^
                clickBtn(getButtons()[0][i]);
                System.out.println("botDefend() blir kalt på 5");
                player1Turn = true;
            } else if (compareFieldText[0][i].equals("X") && compareFieldText[2][i].equals("X") && compareFieldText[1][i].equals("")) { // ^
                clickBtn(getButtons()[1][i]);
                System.out.println("botDefend() blir kalt på 6");
                player1Turn = true;
            } else if (compareFieldText[0][0].equals("X") && compareFieldText[1][1].equals("X") && compareFieldText[2][2].equals("")) {   // Diagonalen venstre til høyre
                clickBtn(getButtons()[2][2]);
                System.out.println("botDefend() blir kalt på 7 ");
                player1Turn = true;
            } else if (compareFieldText[1][1].equals("X") && compareFieldText[2][2].equals("X") && compareFieldText[0][0].equals("")) {  // ^
                clickBtn(getButtons()[0][0]);
                System.out.println("botDefend() blir kalt på 8 ");
                player1Turn = true;
            } else if (compareFieldText[0][0].equals("X") && compareFieldText[2][2].equals("X") && compareFieldText[1][1].equals("")) {  // ^
                clickBtn(getButtons()[1][1]);
                System.out.println("botDefend() blir kalt på 9");
                player1Turn = true;
            } else if (compareFieldText[0][2].equals("X") && compareFieldText[1][1].equals("X") && compareFieldText[2][0].equals("")) {   // Diagonalen høyre til venstre
                clickBtn(getButtons()[2][0]);
                System.out.println("botDefend() blir kalt på 10");
                player1Turn = true;
            } else if (compareFieldText[2][0].equals("X") && compareFieldText[1][1].equals("X") && compareFieldText[0][2].equals("")) {  // ^
                clickBtn(getButtons()[0][2]);
                System.out.println("botDefend() blir kalt på 11");
                player1Turn = true;
            } else if (compareFieldText[2][0].equals("X") && compareFieldText[0][2].equals("X") && compareFieldText[1][1].equals("")) {  // ^
                clickBtn(getButtons()[1][1]);
                System.out.println("botDefend() blir kalt på  12");
                player1Turn = true;
            }
        }
        System.out.println("BotDefend() blir kalt ferdig på ......");
    }


    private void botMove() {

        System.out.println("BotMove blir kalt på.,.. ");

        if (compareFieldText[1][1].equals("X") && compareFieldText[0][2].equals("")) {
            clickBtn(getButtons()[0][2]);
            player1Turn = true;
            System.out.println("botMove() blir kalt på 1");
        } else if (compareFieldText[1][1].equals("") && compareFieldText[1][1].equals("")) {
            clickBtn(getButtons()[1][1]);
            player1Turn = true;
            System.out.println("Botmove() blir kalt på 2.5");
        } else if (compareFieldText[1][1].equals("X") && compareFieldText[0][0].equals("")) {
            clickBtn(getButtons()[0][0]);
            player1Turn = true;
            System.out.println("botMove() blir kalt på 2");
        } else if (compareFieldText[0][0].equals("X") && compareFieldText[2][2].equals("")) {
            clickBtn(getButtons()[2][2]);
            player1Turn = true;
            System.out.println("botMove() blir kalt på 3");
        } else if (compareFieldText[0][2].equals("X") && compareFieldText[2][1].equals("") && compareFieldText[0][1].equals("")) {
            clickBtn(getButtons()[0][1]);
            player1Turn = true;
            System.out.println("botMove() blir kalt på 4");
        } else if (compareFieldText[2][0].equals("X") && compareFieldText[0][0].equals("X") && compareFieldText[1][0].equals("")) {
            clickBtn(getButtons()[1][0]);
            player1Turn = true;
            System.out.println("BotMove() blir kalt på 5");
        } else if (compareFieldText[2][2].equals("X") && compareFieldText[0][0].equals("")) {
            clickBtn(getButtons()[0][0]);
            System.out.println("botMove() blir kalt på 6");
        } else if (compareFieldText[1][0].equals("X") && compareFieldText[1][2].equals("")) {
            clickBtn(getButtons()[1][2]);
            System.out.println("botMove() blir kalt på 7");
        } else if (compareFieldText[1][2].equals("X") && compareFieldText[1][0].equals("")) {
            clickBtn(getButtons()[1][0]);
            System.out.println("botMove() blir kalt på 8 ");
            player1Turn = true;
        } else if (compareFieldText[2][1].equals("X") && compareFieldText[1][2].equals("")) {
            clickBtn(getButtons()[1][2]);
            player1Turn = true;
            System.out.println("botMove() blir kalt på 9");
        } else if (compareFieldText[0][1].equals("X") && compareFieldText[0][0].equals("")) {
            clickBtn(getButtons()[0][0]);
            System.out.println("botMove() blir kalt på 10 ");
            player1Turn = true;
        } else if (compareFieldText[0][0].equals("X") && compareFieldText[2][2].equals("X") && compareFieldText[1][2].equals("")) {
            clickBtn(getButtons()[1][2]);
            player1Turn = true;
            System.out.println("botMove() blir kalt på 11");
        } else if (compareFieldText[2][0].equals("X") && compareFieldText[1][2].equals("")) {
            clickBtn(getButtons()[1][2]);
            System.out.println("botMove() blir kalt på  12");
            player1Turn = true;
        } else if (compareFieldText[2][2].equals("X") && compareFieldText[0][2].equals("")) {
            clickBtn(getButtons()[0][2]);
            System.out.println("botMove() blir kalt på  13");
        } else if (compareFieldText[0][2].equals("X") && compareFieldText[0][0].equals("")) {
            clickBtn(getButtons()[0][0]);
            System.out.println("botMove() blir kaldt på 14");
            player1Turn = true;
        } else if (compareFieldText[1][0].equals("")) {
            clickBtn(getButtons()[1][0]);
            System.out.println("Botmove() blir kalt på 15");
            player1Turn = true;
        } else if (compareFieldText[1][2].equals("")) {
            clickBtn(getButtons()[1][2]);
            System.out.println("Botmove() blir kalt på 15");
            player1Turn = true;
        } else {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (compareFieldText[j][i].equals("")) {

                        System.out.println("foooorfæn");
                        clickBtn(getButtons()[j][i]);
                        player1Turn = true;
                        break;
                    }
                    if (player1Turn == true) { break; }
                }
                if (player1Turn == true) { break; }
            }
        }
    }

    private void restartGame() {
        player1Turn = true;
        player1Score = 0;
        botScore = 0;
        updatePlayerStats();
    }

    private boolean checkForWin() {

        updateFields();

        for (int i = 0; i < 3; i++) {
            if (compareFieldText[i][0].equals(compareFieldText[i][1]) // *
                    && compareFieldText[i][0].equals(compareFieldText[i][2])// * Sjekker om det er 3 på rad vannrett.
                    && !compareFieldText[i][0].equals("")) { // sjekker at de ikke bare er tomme
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (compareFieldText[0][i].equals(compareFieldText[1][i]) // *
                    && compareFieldText[0][i].equals(compareFieldText[2][i])// * Sjekker om det er 3 på rad loddrett
                    && !compareFieldText[0][i].equals("")) { // sjekker at de ikke bare er tomme
                return true;
            }
        }

        if (compareFieldText[0][0].equals(compareFieldText[1][1]) // *
                && compareFieldText[1][1].equals(compareFieldText[2][2])// * Sjekker om det er 3 på rad på diagonalen
                && !compareFieldText[0][0].equals("")) { // sjekker at de ikke bare er tomme
            return true;
        }

        if (compareFieldText[0][2].equals(compareFieldText[1][1]) // *
                && compareFieldText[1][1].equals(compareFieldText[2][0])// * Sjekker om det er 3 på rad på diagonalen
                && !compareFieldText[0][2].equals("")) { // sjekker at de ikke bare er tomme
            return true;
        }

        return false;

    }

    private void botWins() {
        Toast.makeText(this, "TTTBot wins!", Toast.LENGTH_SHORT).show(); ///////////////////////// legg inn navnet her.
        botScore++;

        updatePlayerStats();
        resetBoard();
    }

    private void player1Wins() {
        Toast.makeText(this, "PLayer 1 wins!", Toast.LENGTH_SHORT).show(); ///////////////////////// legg inn navnet her.
        player1Score++;

        updatePlayerStats();
        resetBoard();

    }


    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }


    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j].setText("");
                compareFieldText[i][j] = ("");
            }
        }
        roundCounter = 0;


        player1Turn = true; ///////////////////////////////////---------------------******* fjerne denne kanskje


    }

    private void updateFields() {  // String field[][] = returnFields();


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                compareFieldText[i][j] = field[i][j].getText().toString();
            }
        }

    }


    private void updatePlayerStats() { /////////////om intent skal legges inn her?


        botTextView = findViewById(R.id.bot_text_view);
        player1TextView = findViewById(R.id.player2_text_view);

        botTextView.setText("TTTBot " + botScore);
        player1TextView.setText("player1: " + player1Score); /////**************************Legge inn navnet på spilleren her.

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCounter", roundCounter);
        outState.putInt("player1Score", player1Score);
        outState.putInt("botScore", botScore);
        outState.putBoolean("player1Turn", player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCounter = savedInstanceState.getInt("roundCounter");
        player1Score = savedInstanceState.getInt("player1Score");
        botScore = savedInstanceState.getInt("botScore");
        player1Turn = savedInstanceState.getBoolean("player1Turn");
        updatePlayerStats();
    }
}
