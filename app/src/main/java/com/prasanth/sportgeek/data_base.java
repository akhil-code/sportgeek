package com.prasanth.sportgeek;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by bhargav on 6/25/2016.
 */
/*Here we are creating a class data_base whose parent is sqliteopenhelper*/
public class data_base extends SQLiteOpenHelper {
    public data_base(Context context) {     //constructor for superclass i.e sqliteopenhelper
        super(context,"signup2",null,1);
    }

    @Override
    /*Here we are creating a database*/
    public void onCreate(SQLiteDatabase db) {
        String tbl_query = "create table user1(id integer primary key autoincrement, name text, phone text, email text, password text,age text)";
        db.execSQL(tbl_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    void add_user(String name,String phone,String email,String password ,String age)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("phone",phone);
        contentValues.put("email",email);
        contentValues.put("password",password);
        contentValues.put("age",age);

        db.insert("user1", null, contentValues);
        db.close();
    }

    ArrayList<String> get_users()
    {
        ArrayList<String> arrayList = new ArrayList<String>();
       // StringBuffer stringBuffer = new StringBuffer();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from user1";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            while (cursor.moveToNext())
            {
                arrayList.add(cursor.getString(1));
                //stringBuffer.append(cursor.getString(1));
            }
        }
        db.close();
        return arrayList;
    }

    public Cursor Login_check(String username1, String password1)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select name,password from user1 where name='"+username1+"' and password='"+password1+"' ";
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }

    ArrayList<Integer> get_usersid()
    {
        ArrayList<Integer> arrayList1 = new ArrayList<Integer>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from user1";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            while (cursor.moveToNext())
            {
                arrayList1.add(cursor.getInt(0));
                //stringBuffer.append(cursor.getString(1));
            }
        }
        db.close();
        return arrayList1;
    }
    ArrayList<String> get_useremails()
    {
        ArrayList<String> arrayList = new ArrayList<String>();
        // StringBuffer stringBuffer = new StringBuffer();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from user1";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            while (cursor.moveToNext())
            {
                arrayList.add(cursor.getString(3));
                //stringBuffer.append(cursor.getString(1));
            }
        }
        db.close();
        return arrayList;
    }
    ArrayList<Integer> get_userage()
    {
        ArrayList<Integer> arrayList1 = new ArrayList<Integer>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from user1";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            while (cursor.moveToNext())
            {
                arrayList1.add(cursor.getInt(5));
                //stringBuffer.append(cursor.getString(1));
            }
        }
        db.close();
        return arrayList1;
    }


    void delete_user(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String delete = "delete from user1 where id="+id;
        db.execSQL(delete);
        db.close();
    }
}
