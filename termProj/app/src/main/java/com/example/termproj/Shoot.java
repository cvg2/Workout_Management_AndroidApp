package com.example.termproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Shoot extends AppCompatActivity {
    int madeshots = 0;
    int missshots = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoot);

      Button made = (Button) findViewById(R.id.made);
      made.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          madeshots++;
        }
      });

      Button miss = (Button) findViewById(R.id.miss);
      miss.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          missshots++;
        }
      });

    }

    public void goContactDetailAct(View view1) {
      double totalshots = madeshots+missshots;
      double stats = (double)(madeshots)/(totalshots);
      stats= stats*100;
      Intent intent2 = new Intent();
      intent2.putExtra("stats", stats);
      setResult(RESULT_OK, intent2);
      finish();
    }
}
