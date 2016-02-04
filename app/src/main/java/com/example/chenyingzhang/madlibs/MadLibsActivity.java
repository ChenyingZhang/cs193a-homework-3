package com.example.chenyingzhang.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MadLibsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mad_libs);
    }

    public void fillWords(View view) {
        Intent intent = new Intent(this, FillWordsActivity.class);
        startActivity(intent);
    }
}
