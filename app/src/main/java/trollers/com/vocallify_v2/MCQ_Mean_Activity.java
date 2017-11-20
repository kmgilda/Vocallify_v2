package trollers.com.vocallify_v2;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.util.Log;
import java.util.ArrayList;

public class MCQ_Mean_Activity extends AppCompatActivity {

    private RadioGroup radiogrp;
    private Button button;
    private TextView txt1;
    private RadioButton selected;
    private RadioButton btnA;
    private RadioButton btnB;
    private RadioButton btnC;
    private RadioButton btnD;
    boolean trial = false;
    QuestionRadio temp;
    int answerid;

    private static final String TAG ="FirstTimetag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcq__mean_);

        radiogrp = (RadioGroup)findViewById(R.id.radioGroup1);
        button = (Button)findViewById(R.id.button1);
        txt1 = (TextView)findViewById(R.id.textView1);
        btnA = (RadioButton)findViewById(R.id.radio0);
        btnB = (RadioButton)findViewById(R.id.radio1);
        btnC = (RadioButton)findViewById(R.id.radio2);
        btnD = (RadioButton)findViewById(R.id.radio3);


        ArrayList<WordsObject> recList;
        Intent i = getIntent();
        recList = i.getParcelableArrayListExtra("wordslist");
        int WORD_ID = i.getIntExtra("word_id",0);
        WordsObject array[] = recList.toArray(new WordsObject[recList.size()]);

        temp = new QuestionRadio(array,WORD_ID);
        temp = temp.getQues();
        Log.i(TAG,temp.ANSWER);

        txt1.setText("What is the meaning of "+array[WORD_ID].word+"?");
        btnA.setText(temp.OptA);
        btnB.setText(temp.OptB);
        btnC.setText(temp.OptC);
        btnD.setText(temp.OptD);

        if(btnA.getText().equals(temp.ANSWER))
            answerid = btnA.getId();
        else if(btnB.getText().equals(temp.ANSWER))
            answerid = btnB.getId();
        else if(btnC.getText().equals(temp.ANSWER))
            answerid = btnC.getId();
        else if(btnD.getText().equals(temp.ANSWER))
            answerid = btnD.getId();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(trial==false)
                {
                    if(radiogrp.getCheckedRadioButtonId()==-1)
                    {
                        selected = (RadioButton)findViewById(answerid);
                        selected.setChecked(true);
                        selected.setTextColor(Color.RED);
                        trial=true;
                    }
                    else
                    {
                        selected = (RadioButton)findViewById(radiogrp.getCheckedRadioButtonId());
                        if(selected.getText().equals(temp.ANSWER))
                            selected.setTextColor(Color.GREEN);
                        else
                        {
                            selected.setTextColor(Color.RED);
                            selected = (RadioButton)findViewById(answerid);
                            selected.setTextColor(Color.GREEN);
                        }
                    }
                }
                else
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
                        Intent intent = new Intent(MCQ_Mean_Activity.this,MainActivityforSessions.class);
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
