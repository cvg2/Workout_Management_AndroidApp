package com.example.termproj;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
  private List<Contact> contactos;
  private ContactListAdapter adapter;

  private DBAdapter db;

  public SecondActivity() {
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_second);
    db = new DBAdapter(this);
    contactos =  new ArrayList<>();
    adapter = new ContactListAdapter(this, contactos);
    EditText textname = (EditText) findViewById(R.id.varname2);
    textname.requestFocus();
  }

  public void goMainAct(View view1) {
    EditText textuser = (EditText) findViewById(R.id.username2);
    EditText textpass = (EditText) findViewById(R.id.passwordedit2);
    EditText textname = (EditText) findViewById(R.id.varname2);
    EditText textphone = (EditText) findViewById(R.id.varphone2);
    EditText textemail = (EditText) findViewById(R.id.varemail2);
    EditText textrole = (EditText) findViewById(R.id.varrole2);

    String name = textname.getText().toString();
    String phone = textphone.getText().toString();
    String email = textemail.getText().toString();
    String user = textuser.getText().toString();
    String pass = textpass.getText().toString();
    String role = textrole.getText().toString();



    contactos.add(new Contact(name,phone,email,user,pass,role,0));
    db.open();
    db.insertContact(name,email,phone,user,pass,role,0);
    db.close();
    adapter.notifyDataSetChanged();

    finish();
  }
}
