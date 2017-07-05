package com.example.yamin.jsontest.aty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.yamin.jsontest.R;

public class SettingActivity extends AppCompatActivity {

    private Button backButton;
    private TextView title;
    private ImageButton rightButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        backButton = (Button)findViewById(R.id.left_button);
        backButton.setBackgroundResource(R.drawable.ic_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        title = (TextView)findViewById(R.id.title);
        title.setText("设置");

        rightButton = (ImageButton)findViewById(R.id.right_button);
        rightButton.setVisibility(View.GONE);
    }
}
