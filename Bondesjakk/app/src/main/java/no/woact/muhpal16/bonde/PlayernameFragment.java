package no.woact.muhpal16.bonde;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlayernameFragment extends Fragment {



    private Button button;
    private EditText editText;
    private EditText editText2;


    OnPlayernameSendListener playernameSendListener;

    public interface OnPlayernameSendListener {

        public void onPlayernameSend(String playername, String playername2); // 11:04
    }

    public PlayernameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_playername, container, false);




        button = view.findViewById(R.id.button);
        editText = view.findViewById(R.id.txt_message);
        editText2 = view.findViewById(R.id.txt_player2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String playername = editText.getText().toString();
                String playername2 = editText2.getText().toString();
                playernameSendListener.onPlayernameSend(playername, playername2);

            }
        });


        return view;

    }

    public void onAttach(Context context) {


        super.onAttach(context);
        Activity activity = (Activity) context;

        try {
            playernameSendListener = (OnPlayernameSendListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "Must implement onPLayernamesend");
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        editText.setText("");
        editText2.setText("");
    }

}
