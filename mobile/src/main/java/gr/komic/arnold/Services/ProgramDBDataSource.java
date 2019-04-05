package gr.komic.arnold.Services;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import gr.komic.arnold.Models.Program;

public class ProgramDBDataSource {
    private static final String TAG = "ProgramDBDataSource";
    private static final String[] columns = {
            DBOpenHelper.PROGRESS_COLUMN_ID,
            DBOpenHelper.PROGRAM_COLUMN_CREATED_AT,
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
}
