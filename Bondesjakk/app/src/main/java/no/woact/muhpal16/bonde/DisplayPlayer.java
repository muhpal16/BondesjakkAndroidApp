package no.woact.muhpal16.bonde;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class DisplayPlayer extends Fragment {


    private TextView textView;
    private TextView textView2;

    public DisplayPlayer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_display_player, container, false);

        textView = view.findViewById(R.id.txt_message_display);
        textView2 = view.findViewById(R.id.txt_player2display);

        Bundle bundle = getArguments();
        String playername = bundle.getString("playername");
        String playername2 = bundle.getString("playername2");


        textView.setText(playername);
        textView2.setText(playername2);
        return view;
    }


}
