package gr.komic.arnold.Services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import gr.komic.arnold.Models.Program;

public class ProgramDBDataSource {
    private static final String TAG = "ProgramDBDataSource";
    private static final String[] columns = {
            DBOpenHelper.PROGRAM_COLUMN_ID,
            DBOpenHelper.PROGRAM_COLUMN_IS_CURRENT_PROGRAM,
            DBOpenHelper.PROGRAM_COLUMN_TITLE,
            DBOpenHelper.PROGRAM_COLUMN_CREATED_AT
    };

    DBOpenHelper dbOpenHelper;
    SQLiteDatabase database;

    public ProgramDBDataSource(Context context) {
        dbOpenHelper = new DBOpenHelper(context);
    }

    public void open() {
        Log.i(TAG, "ProgramDB opened");
        database = dbOpenHelper.getReadableDatabase();
    }

    public void close() {
        Log.i(TAG, "ProgramDB closed");
        database.close();
    }

    public Program insert(Program program) {
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.PROGRAM_COLUMN_CREATED_AT, program.getCreatedAt().toString());
        values.put(DBOpenHelper.PROGRAM_COLUMN_TITLE, program.getTitle());
        values.put(DBOpenHelper.PROGRAM_COLUMN_IS_CURRENT_PROGRAM, program.getIsCurrentProgram());
        long insertId = database.insert(DBOpenHelper.PROGRAM_TABLE_NAME, null, values);
        program.setId(insertId);

        return program;
    }

    public ArrayList<Program> findAll() {
        ArrayList<Program> programs = new ArrayList<>();
        Cursor cursor = database.query(DBOpenHelper.PROGRAM_TABLE_NAME, columns, null, null, null, null, null);

        Log.i(TAG, "Returned " + cursor.getCount() + " rows");
        while(cursor.moveToNext()) {
            String createdAt = cursor.getString(cursor.getColumnIndex(DBOpenHelper.PROGRAM_COLUMN_CREATED_AT));
            long id = cursor.getLong(cursor.getColumnIndex(DBOpenHelper.PROGRAM_COLUMN_ID));
            int isCurrent = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.PROGRAM_COLUMN_IS_CURRENT_PROGRAM));
            String title = cursor.getString(cursor.getColumnIndex(DBOpenHelper.PROGRAM_COLUMN_TITLE));
            Program program = new Program();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            try {
                Date date = format.parse(createdAt);
                program.setCreatedAt(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            program.setIsCurrentProgram(isCurrent == 1);
            program.setTitle(title);
            program.setId(id);

            programs.add(program);
        }

        return programs;
    }
}
