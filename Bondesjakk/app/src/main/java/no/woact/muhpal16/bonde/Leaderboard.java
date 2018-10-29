package no.woact.muhpal16.bonde;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class Leaderboard extends AppCompatActivity {

    DatabaseHelper myDb;

    ListView leaderboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        myDb = new DatabaseHelper(this);

        leaderboard = findViewById(R.id.leaderboardLW);

        ArrayList<String> leaderboardArray = new ArrayList<>();

        Cursor dbcursor = myDb.getData();

        if(dbcursor.getCount() == 0){
            Toast.makeText(this, "Database er tom", Toast.LENGTH_SHORT).show();
        } else {
            while (dbcursor.moveToNext()) {
                leaderboardArray.add(dbcursor.getString(dbcursor.getColumnIndex("name")));
                leaderboardArray.add(dbcursor.getString(dbcursor.getColumnIndex("wins")));


                String[] collums = {"name", "wins"};
                int[] textType = {android.R.id.text1, android.R.id.text2};

                ListAdapter listAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, dbcursor, collums, textType);
                leaderboard.setAdapter(listAdapter);

            }

        }

    }
}
