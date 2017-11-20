package trollers.com.vocallify_v2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;




public class DatabaseHelper extends SQLiteOpenHelper {
    //The Android's default system path of your application database.
    private static String DB_PATH = "data/data/trollers.com.vocallify_v2/databases/";

    private static String DB_NAME = "gredict.db";

    private SQLiteDatabase myDataBase;

    private final Context myContext;

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * @param context
     */
    public DatabaseHelper(Context context) {

        super(context, DB_NAME, null, 1);
        this.myContext = context;
        Log.i("","Done with constructor");
    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     * */
    public void createDataBase() throws IOException{

        Log.i("","In Create Database");

        boolean dbExist = checkDataBase();

        if(dbExist){
            Log.i("","This database exists!");
            //do nothing - database already exist
        }else{

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            Log.i("","this.getreadabledb");
            this.getReadableDatabase();

            try {
                Log.i("","Trying to copy database");

                copyDataBase();

            } catch (IOException e) {

                Log.i("","Error copying");

                throw new Error("Error copying database");

            }

        }

    }


    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */


    /*private boolean checkDataBase(){
        //  this.getReadableDatabase();

        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        }catch(SQLiteException e){

            //database does't exist yet.

        }

        if(checkDB != null){

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }*/

    private boolean checkDataBase() {
        Log.i("","In checking db function");
        File databasePath = myContext.getDatabasePath(DB_NAME);
        return databasePath.exists();
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException{

        //Open your local db as the input stream
        Log.i("","In copy db method");
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        Log.i("","asset accessed");

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);
        Log.i("","Opened empty db");

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){

            myOutput.write(buffer, 0, length);
        }
        Log.i("","Writing done");

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException{

        //Open the database
        if(!(checkDataBase()))
        {
            try {
                createDataBase();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        String myPath = DB_PATH +DB_NAME;
        Log.i("","In open database method");
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {

        Log.i("","Closing db");

        if(myDataBase != null)
            myDataBase.close();

        super.close();

    }

    public WordsObject[] getRandom()
    {
        WordsObject [] temp1 = new WordsObject[5];
        Log.i("","In random function");
        SQLiteDatabase d1 = this.getReadableDatabase();
        final String TABLE_NAME ="word_list";
        String query = "SELECT * FROM "+TABLE_NAME+" ORDER BY random() LIMIT 5";
        Cursor cursor = d1.rawQuery(query,null);
        int k=0;

        if(cursor.moveToFirst())
        {
            do {

                temp1[k] = new WordsObject(cursor.getString(cursor.getColumnIndex("word")),cursor.getString(cursor.getColumnIndex("meaning")));
                k++;

            }while(cursor.moveToNext());
        }
        cursor.close();
        return temp1;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Add your public helper methods to access and get content from the database.
    // You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
    // to you to create adapters for your views.


    //add your public methods for insert, get, delete and update data in database.


}
