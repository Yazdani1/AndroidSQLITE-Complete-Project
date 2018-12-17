package yazdaniscodelab.sqlitegrocerycompleteapp.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;
import java.util.List;

import yazdaniscodelab.sqlitegrocerycompleteapp.Model.Grocery;
import yazdaniscodelab.sqlitegrocerycompleteapp.Utils.Constants;

/**
 * Created by Yazdani on 12/14/2018.
 */

public class DatabaseHandaler extends SQLiteOpenHelper{

    private Context ctx;

    public DatabaseHandaler(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        this.ctx=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_GROCERY_TABLE ="CREATE TABLE " + Constants.TABLE_NAME + "("
                + Constants.KEY_ID + " INTEGER PRIMARY KEY,"
                + Constants.KEY_GROCERY_ITEM + " TEXT,"
                + Constants.KEY_QTY_NUMBER + " TEXT,"
                + Constants.KEY_DATE_NAME + " LONG);";

        sqLiteDatabase.execSQL(CREATE_GROCERY_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    //CRUD OPERATION

    //add grocery

    public void addGrocery(Grocery grocery){

        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();

        values.put(Constants.KEY_GROCERY_ITEM,grocery.getName());
        values.put(Constants.KEY_QTY_NUMBER,grocery.getQuentity());
        values.put(Constants.KEY_DATE_NAME,java.lang.System.currentTimeMillis());

        db.insert(Constants.TABLE_NAME,null,values);

        Log.d("Saved:","Saved to db");

    }

    //get grocery
    public Grocery getGrocery(int id){

        SQLiteDatabase db=this.getWritableDatabase();

        Cursor cursor=db.query(Constants.TABLE_NAME,new String[]{Constants.KEY_ID,
        Constants.KEY_GROCERY_ITEM,Constants.KEY_QTY_NUMBER,Constants.KEY_DATE_NAME},
                Constants.KEY_ID+"?",new String[]{String.valueOf(id)},null,null,null,null);


        if (cursor!=null)
            cursor.moveToFirst();

            Grocery grocery=new Grocery();

            grocery.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
            grocery.setName(cursor.getString(cursor.getColumnIndex(Constants.KEY_GROCERY_ITEM)));
            grocery.setQuentity(cursor.getString(cursor.getColumnIndex(Constants.KEY_QTY_NUMBER)));

            //date formate
            java.text.DateFormat dateFormat=java.text.DateFormat.getDateInstance();
            String formateDate=dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_DATE_NAME))).getTime());
            grocery.setDateItemAdded(formateDate);

        return grocery;
    }

    //Get all groceries

    public List<Grocery>getAllGrocery(){
        return null;
    }

    //update grocery

    public int updateGrocery(Grocery grocery){

        return 0;
    }

    //delete grocery

    public void deleteGrocery(int id){

    }

    //get count

    public int getGroceryCount(){
        return 0;
    }


}
