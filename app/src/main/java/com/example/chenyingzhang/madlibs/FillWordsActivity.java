package com.example.chenyingzhang.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FillWordsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ArrayList<String> placeholder;
    private String story;
    private int needToFill;
    private int finishFilling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_words);

        ListView files = (ListView) findViewById(R.id.files);
        files.setOnItemClickListener(this);

    }

    private ArrayList<String> readStory(String fileName) {
        String text = "";
        ArrayList<String> placeholder = new ArrayList<String>();
        int blanks = 0;
        Scanner scan = new Scanner(getResources().openRawResource(R.raw.madlib4_dance));
        if (fileName.equalsIgnoreCase("madlib0_simple")) {
            scan = new Scanner(getResources().openRawResource(R.raw.madlib0_simple));
        } else if(fileName.equalsIgnoreCase("madlib1_tarzan")) {
            scan = new Scanner(getResources().openRawResource(R.raw.madlib1_tarzan));
        } else if(fileName.equalsIgnoreCase("madlib2_university")) {
            scan = new Scanner(getResources().openRawResource(R.raw.madlib2_university));
        } else if(fileName.equalsIgnoreCase("madlib3_clothes")) {
            scan = new Scanner(getResources().openRawResource(R.raw.madlib3_clothes));
        }
        while (scan.hasNext()) {
            String word = scan.next();
            if (word.startsWith("<") && word.endsWith(">")) {
                text += "<" + blanks + "> ";
                blanks++;
                placeholder.add(word.substring(1,word.length()-1));
            } else {
                text += word + " ";
            }
        }
        placeholder.add(text);
        return placeholder;
    }

    public void replaceWord(View view) {
        EditText blank = (EditText) findViewById(R.id.blank);
        String word = blank.getText().toString();
        word = "<b>" + word + "</b>";
        story = story.replace("<" + finishFilling + ">", word);
        Toast.makeText(FillWordsActivity.this, "Great! Keep going!", Toast.LENGTH_SHORT).show();
        needToFill--;
        finishFilling++;
        if (needToFill == 0) {
            Intent intent = new Intent(this, ViewStoryActivity.class);
            intent.putExtra("Story Content", story);
            startActivity(intent);
            finish();
        } else {
            blank.setText("");
            blank.setHint(placeholder.get(finishFilling));
            TextView numberOfWordLeft = (TextView) findViewById(R.id.numberOfWordLeft);
            numberOfWordLeft.setText(needToFill + "word(s) left");
            TextView typeHint = (TextView) findViewById(R.id.typeHint);
            typeHint.setText("Please type a/an " + placeholder.get(finishFilling));
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ListView files = (ListView) findViewById(R.id.files);
        String fileName = files.getItemAtPosition(position).toString();
        placeholder = readStory(fileName);
        story = placeholder.get(placeholder.size() - 1);
        placeholder.remove(placeholder.size() - 1);
        needToFill = placeholder.size();
        finishFilling = 0;
        EditText blank = (EditText) findViewById(R.id.blank);
        blank.setHint(placeholder.get(finishFilling));
        TextView numberOfWordLeft = (TextView) findViewById(R.id.numberOfWordLeft);
        numberOfWordLeft.setText(needToFill + "word(s) left");
        TextView typeHint = (TextView) findViewById(R.id.typeHint);
        typeHint.setText("Please type a/an " + placeholder.get(finishFilling));
    }
}
