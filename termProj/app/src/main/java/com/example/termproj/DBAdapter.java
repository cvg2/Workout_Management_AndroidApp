package com.example.termproj;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBAdapter {
  static final String KEY_ROWID = "_id";
  static final String KEY_NAME = "name";
  static final String KEY_EMAIL = "email";

  static final String KEY_PHONE = "phone";

  static final String KEY_USER = "user";

  static final String KEY_ROLE = "role";

  static final String KEY_STATS = "stats";
  static final String KEY_PASSWORD = "password";
  static final String TAG = "DBAdapter";
  static final String DATABASE_NAME = "MyDB";
  static final String DATABASE_TABLE = "players";

  static final int DATABASE_VERSION = 1;
  static final String TABLE_CREATE = "create table players (_id integer primary key autoincrement, "
    + "name text not null, email text not null,phone text not null,user text not null, password text not null, role text not null, stats integer not null);";
  static final String INDEX_CREATE = "CREATE UNIQUE INDEX idx_user ON players(user);";
  final Context context;
  DatabaseHelper DBHelper;
  SQLiteDatabase db;
  public DBAdapter(Context ctx) {
    //ctx.deleteDatabase(DATABASE_NAME);
    this.context = ctx;
    DBHelper = new DatabaseHelper(context);
  }


  private static class DatabaseHelper extends SQLiteOpenHelper
  {
    DatabaseHelper(Context context)
    {
      super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
      public void onCreate(SQLiteDatabase db)
      {
        try {
          db.execSQL(TABLE_CREATE);
          db.execSQL(INDEX_CREATE);
      } catch (SQLException e) { e.printStackTrace(); }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
      Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
        + newVersion + ", which will destroy all old data");
      onCreate(db);
    }
  }
  //---opens the database---
  public DBAdapter open() throws SQLException
  {
    db = DBHelper.getWritableDatabase();
    return this;
  }
  //---closes the database---
  public void close()
  {
    DBHelper.close();
  }

  public long insertContact(String name, String email, String phone, String user,
                            String password, String role, Integer stats)
  {
    ContentValues initialValues = new ContentValues();
    initialValues.put(KEY_NAME, name);
    initialValues.put(KEY_EMAIL, email);
    initialValues.put(KEY_PHONE, phone);
    initialValues.put(KEY_USER, user);
    initialValues.put(KEY_PASSWORD, password);
    initialValues.put(KEY_ROLE, role);
    initialValues.put(KEY_STATS, stats);
    return db.insert(DATABASE_TABLE, null, initialValues);
  }
  public boolean deleteContact(long rowId)
  {
    return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
  }
  public Cursor getAllContacts()
  {
    return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_NAME,
      KEY_EMAIL,KEY_PHONE,KEY_USER, KEY_PASSWORD, KEY_ROLE, KEY_STATS,KEY_STATS},
      null, null, null, null, null);
  }



  public Cursor getUser(String user1) throws SQLException
  {
    Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] {
      KEY_ROWID, KEY_NAME, KEY_EMAIL, KEY_PHONE, KEY_USER, KEY_PASSWORD, KEY_ROLE, KEY_STATS}, KEY_USER + " = "
      + "\'" + user1 + "\'",null, null, null, null, null);

    if (mCursor != null) { mCursor.moveToFirst(); }
    return mCursor;
  }

  public boolean updateStats(String user, Integer stats1)
  {
    ContentValues args = new ContentValues();
    args.put(KEY_STATS, stats1);
    return db.update(DATABASE_TABLE, args, KEY_USER + "=" + "\'" +user + "\'", null) > 0;
  }
}

