package com.example.termproj;

  import androidx.activity.result.ActivityResult;
  import androidx.activity.result.ActivityResultCallback;
  import androidx.activity.result.ActivityResultLauncher;
  import androidx.activity.result.contract.ActivityResultContracts;
  import androidx.appcompat.app.AppCompatActivity;

  import android.content.Intent;
  import android.database.Cursor;
  import android.os.Bundle;
  import android.util.Log;
  import android.view.View;
  import android.widget.AdapterView;
  import android.widget.Button;
  import android.widget.ListView;
  import android.widget.TextView;

  import java.util.ArrayList;
  import java.util.List;

public class PlayerList extends AppCompatActivity {
  private List<Contact> contactos;
  private ContactListAdapter adapter;

  private DBAdapter db;


  public void readList() {
    contactos.clear();
    db = new DBAdapter(this);

    db.open();

    Cursor c = db.getAllContacts();
    Log.d("TAG", c.getCount() + " adjsadak");
    if (c.moveToFirst())
    {
      do {
        String name = c.getString(1);
        String email = c.getString(2);
        String phone = c.getString(3);
        String user = c.getString(4);
        String pass = c.getString(5);
        String role = c.getString(6);
        Integer stats = c.getInt(7);
        Log.d("TAG", user + "userarao");
        contactos.add(new Contact(name,phone,email,user,pass,role,stats));
      } while (c.moveToNext());
    }
    db.close();
  }
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_player_list);
    contactos =  new ArrayList<>();

    adapter = new ContactListAdapter(this, contactos);

    ListView listViewContacts = findViewById(R.id.listViewContacts);
    listViewContacts.setAdapter(adapter);
    listViewContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Contact contact = (Contact) parent.getItemAtPosition(position);
        Intent intent = new Intent(PlayerList.this, ContactDetail.class);
        intent.putExtra("name", contact.getName());
        intent.putExtra("phone", contact.getPhone());
        intent.putExtra("email", contact.getEmail());
        intent.putExtra("stats", contact.getStats().toString());
        intent.putExtra("user", contact.getUser());
        startActivity(intent);
      }
    });

    readList();
  }

  @Override
  protected void onResume() {
    super.onResume();
    readList();
  }


}

