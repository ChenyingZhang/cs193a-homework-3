package com.example.chenyingzhang.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ViewStoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_story);
        Intent intent = getIntent();
        String story = intent.getStringExtra("Story Content");
        TextView storyContent = (TextView) findViewById(R.id.storyContent);
        storyContent.setText(story);
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, MadLibsActivity.class);
        startActivity(intent);
        finish();
    }
}
