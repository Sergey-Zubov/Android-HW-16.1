package com.szubov.android_hw_161;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "My app";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

    }

    private void initViews() {
        final EditText editText = findViewById(R.id.editText);

        findViewById(R.id.btnSearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = editText.getText().toString().trim();
                if (string.length() > 0) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    Uri uri;
                    if (!string.contains("\\W")) {
                        uri = Uri.parse("geo:?q=" + string);
                    } else {
                        uri = Uri.parse("geo:" + string);
                    }
                    intent.setData(uri);
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    } else {
                        Log.d(LOG_TAG, "The app for searching for an object was not found");
                    };
                } else {
                    Toast.makeText(MainActivity.this, R.string.edit_text_is_empty,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}