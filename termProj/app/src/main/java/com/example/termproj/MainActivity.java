package com.example.termproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  private List<Contact> contactos;
  private ContactListAdapter adapter;

  private DBAdapter db;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    db = new DBAdapter(this);
    contactos =  new ArrayList<>();
    adapter = new ContactListAdapter(this, contactos);

    Button loginbutton = findViewById(R.id.Login);
    loginbutton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        TextView user = findViewById(R.id.usernameedit);
        String text1 = user.getText().toString();
        TextView pass = findViewById(R.id.passwordedit);
        String text2 = pass.getText().toString();

        db.open();
        Cursor c = db.getUser(text1);
        Log.d("TAG", "çsdd" + c.getCount());
        boolean found = false;
        String passdb = "", role = "", name = "", email = "", phone = "";
        int stats= 0;
        if (c.moveToFirst()) {
          role =  c.getString(6).toString();
          passdb = c.getString(5).toString();
          name = c.getString(1);
          email = c.getString(2);
          phone = c.getString(3);
          stats = c.getInt(7);
        }
        db.close();
        if (text2.equals(passdb)) {
          Log.d("TAG","FOUND");
          Log.d("TAG",role + "role");
          String st = Integer.toString(stats);
          if (role.equals("p")) {
            Intent intent = new Intent(MainActivity.this, ContactDetail.class);
            intent.putExtra("name", name);
            intent.putExtra("phone", phone);
            intent.putExtra("email", email);
            intent.putExtra("role", role);
            intent.putExtra("user", text1);
            intent.putExtra("stats", st);
            Log.d("TAG", "stadis" + stats);

            startActivity(intent);
          }
          else if (role.equals("c")) {
            Log.d("TAG","COACH");
            Intent intent = new Intent(MainActivity.this, PlayerList.class);

            startActivity(intent);
          }

        }
        else Log.d("TAG","NOT FOUND");
        }
    });

    Button registerbutton = findViewById(R.id.signup);
    registerbutton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
      }
    });




  }
}
