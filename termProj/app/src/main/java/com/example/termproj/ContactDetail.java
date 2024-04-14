package com.example.termproj;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ContactDetail extends AppCompatActivity {
  private TextView showname;
  private TextView showphone;
  private TextView showemail;

  private List<Contact> contactos;
  private ContactListAdapter adapter;

  private DBAdapter db;

  String user = "";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_contact_detail);

    db = new DBAdapter(this);
    contactos =  new ArrayList<>();
    adapter = new ContactListAdapter(this, contactos);

    TextView textstats = findViewById(R.id.textstats);

    showname = findViewById(R.id.showname);
    showphone = findViewById(R.id.showphone);
    showemail = findViewById(R.id.showemail);

    Intent intent = getIntent();
    String name = intent.getStringExtra("name");
    String phone = intent.getStringExtra("phone");
    String email = intent.getStringExtra("email");
    String st = intent.getStringExtra("stats");
    user = intent.getStringExtra("user");

    textstats.setText(st);

    showname.setText(name);
    showphone.setText(phone);
    showemail.setText(email);

    Button exitButton = findViewById(R.id.exitButton);
    exitButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });

  }

  ActivityResultLauncher<Intent> gotocallShoot= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
    new ActivityResultCallback<ActivityResult>() {
      @Override
      public void onActivityResult(ActivityResult result) {
        if (result.getResultCode() == RESULT_OK) {
          Intent data = result.getData();
          Double stats = data.getDoubleExtra("stats", 0.0);
          TextView textstats = findViewById(R.id.textstats);
          textstats.setText(String.valueOf(stats));
          db.open();
          db.updateStats(user,Double.valueOf(stats).intValue());
          db.close();
        }
      }
    });

  public void callShoot(View view) {
    Intent intent2 = new Intent(this, Shoot.class);
    gotocallShoot.launch(intent2);
  }

}


