package gr.komic.arnold.Services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import gr.komic.arnold.Models.Progress;

public class ProgressDataSource {

    private static final String TAG = "ProgressDataSource";

    private static final String[] columns = {
        DBOpenHelper.PROGRESS_COLUMN_ID,
        DBOpenHelper.PROGRESS_COLUMN_CREATED_AT,
        DBOpenHelper.PROGRESS_COLUMN_NECK,
        DBOpenHelper.PROGRESS_COLUMN_WAIST,
        DBOpenHelper.PROGRESS_COLUMN_HIPS
    };

    DBOpenHelper dbOpenHelper;
    SQLiteDatabase database;

    public ProgressDataSource(Context context) {
        dbOpenHelper = new DBOpenHelper(context);
    }

    public void open() {
        Log.i(TAG, "Database opened");
        database = this.dbOpenHelper.getReadableDatabase();
    }

    public void close() {
        Log.i(TAG, "Database closed");
        database.close();
    }

    public Progress insert(Progress progress) {
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.PROGRESS_COLUMN_CREATED_AT, progress.getCreatedAt());
        values.put(DBOpenHelper.PROGRESS_COLUMN_NECK, progress.getNeck());
        values.put(DBOpenHelper.PROGRESS_COLUMN_WAIST, progress.getWaist());
        values.put(DBOpenHelper.PROGRESS_COLUMN_HIPS, progress.getHips());
        long insertId = database.insert(DBOpenHelper.PROGRESS_TABLE_NAME, null, values);
        progress.setId(insertId);

        return progress;
    }

    public ArrayList<Progress> findAllProgresses() {
        ArrayList<Progress> progresses = new ArrayList<>();
        Cursor cursor = database.query(DBOpenHelper.PROGRESS_TABLE_NAME, columns, null, null, null, null, null);

        Log.i(TAG, "Returned " + cursor.getCount() + " rows");
        while(cursor.moveToNext()) {
            String createdAt = cursor.getString(cursor.getColumnIndex(DBOpenHelper.PROGRESS_COLUMN_CREATED_AT));
            float neck = cursor.getFloat(cursor.getColumnIndex(DBOpenHelper.PROGRESS_COLUMN_NECK));
            float waist = cursor.getFloat(cursor.getColumnIndex(DBOpenHelper.PROGRESS_COLUMN_WAIST));
            float hips = cursor.getFloat(cursor.getColumnIndex(DBOpenHelper.PROGRESS_COLUMN_HIPS));
            Progress progress = new Progress(createdAt, neck, waist, hips);
            progress.setId(cursor.getColumnIndex(DBOpenHelper.PROGRESS_COLUMN_ID));

            progresses.add(progress);
        }

        return progresses;
    }
}
