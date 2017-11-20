package trollers.com.vocallify_v2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MeaningActivity extends AppCompatActivity {

    private TextView WordCur;
    private Button clickone;
    private Button clicktwo;
    private TextView MeanCur;
    public String meaning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meaning);

        WordCur = (TextView)findViewById(R.id.textView6);
        clickone = (Button)findViewById(R.id.button3);
        clicktwo = (Button)findViewById(R.id.button5);
        MeanCur = (TextView)findViewById(R.id.textView7);

        Intent i = getIntent();

        String word = i.getStringExtra("word");
        meaning = i.getStringExtra("meaning");

        WordCur.setText(word);

        clickone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.GONE);
                MeanCur.setText(meaning);
            }
        });

        clicktwo.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
               finish();
           }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to end this session?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(MeaningActivity.this,MainActivityforSessions.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
