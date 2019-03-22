package com.example.studentportal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {

    //instance variables
    private EditText url;
    private EditText title;
    private Portal portal;
    Button addPortalButton;
    public static final String EXTRA_PORTAL = "Portal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_portal);

        url = findViewById(R.id.editUrlText);
        title = findViewById(R.id.editTitleText);
        addPortalButton = findViewById(R.id.addPortalButton);

        addPortalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                portal = new Portal(title.getText().toString(), url.getText().toString());
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                intent.putExtra("Portal", portal);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}
