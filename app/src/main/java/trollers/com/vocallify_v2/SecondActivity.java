package trollers.com.vocallify_v2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;


/*Purpose of Activity: To display list of 5 random words on screen
  based on which session will ask question on clicking next button
 */
public class SecondActivity extends AppCompatActivity {

    WordsObject array[];
    private TextView t1;
    private TextView t2;
    private TextView t3;
    private TextView t4;
    private TextView t5;
    private Button starto;
    int j=-999;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        t1 = (TextView) findViewById(R.id.textView);
        t2 = (TextView) findViewById(R.id.textView2);
        t3 = (TextView) findViewById(R.id.textView3);
        t4 = (TextView) findViewById(R.id.textView4);
        t5 = (TextView) findViewById(R.id.textView5);
        starto = (Button) findViewById(R.id.button2);


        Log.i("", "Trying to open database");
        DatabaseHelper myDatabaseHelper = new DatabaseHelper(SecondActivity.this);
        try {
            myDatabaseHelper.openDataBase();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

        array = myDatabaseHelper.getRandom();
        myDatabaseHelper.close();

        t1.setText(array[0].word);
        t2.setText(array[1].word);
        t3.setText(array[2].word);
        t4.setText(array[3].word);
        t5.setText(array[4].word);

        starto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(j!=5) {
                    Intent i = new Intent(SecondActivity.this, MeaningActivity.class);
                    for (j = 0; j < 5; j++) {
                        i.putExtra("word", array[j].word);
                        i.putExtra("meaning", array[j].meaning);
                        startActivity(i);
                    }
                }
                else {
                    Intent i = new Intent(SecondActivity.this, MCQ_Mean_Activity.class);
                    i.putExtra("word_id", 0);
                    ArrayList<WordsObject> listtemp = new ArrayList<>();
                    listtemp.addAll(Arrays.asList(array).subList(0, 5));
                    i.putParcelableArrayListExtra("wordslist", listtemp);
                    startActivity(i);
                }

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
                            Intent intent = new Intent(SecondActivity.this,MainActivityforSessions.class);
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
