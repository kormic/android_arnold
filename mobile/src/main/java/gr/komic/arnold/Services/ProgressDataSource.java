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
    private static final String[] collumns = {
      ProgressDBOpenHelper.COLUMN_ID,
      ProgressDBOpenHelper.COLUMN_CREATED_AT,
      ProgressDBOpenHelper.COLUMN_NECK,
      ProgressDBOpenHelper.COLUMN_WAIST,
      ProgressDBOpenHelper.COLUMN_HIPS
    };

    ProgressDBOpenHelper progressDBOpenHelper;
    SQLiteDatabase database;

    public ProgressDataSource(Context context) {
        this.progressDBOpenHelper = new ProgressDBOpenHelper(context);
    }

    public void open() {
        Log.i(TAG, "Database opened");
        this.database = this.progressDBOpenHelper.getReadableDatabase();
    }

    public void close() {
        Log.i(TAG, "Database closed");
        this.database.close();
    }

    public Progress insert(Progress progress) {
        ContentValues values = new ContentValues();
        values.put(ProgressDBOpenHelper.COLUMN_CREATED_AT, progress.getCreatedAt());
        values.put(ProgressDBOpenHelper.COLUMN_NECK, progress.getNeck());
        values.put(ProgressDBOpenHelper.COLUMN_WAIST, progress.getWaist());
        values.put(ProgressDBOpenHelper.COLUMN_HIPS, progress.getHips());
        long insertId = database.insert(ProgressDBOpenHelper.TABLE_NAME, null, values);
        progress.setId(insertId);

        return progress;
    }

    public ArrayList<Progress> findAll() {
        ArrayList<Progress> progresses = new ArrayList<>();
        Cursor cursor = database.query(ProgressDBOpenHelper.TABLE_NAME, collumns, null, null, null, null, null);

        Log.i(TAG, "Returned " + cursor.getCount() + " rows");
        while(cursor.moveToNext()) {
            String createdAt = cursor.getString(cursor.getColumnIndex(ProgressDBOpenHelper.COLUMN_CREATED_AT));
            float neck = cursor.getFloat(cursor.getColumnIndex(ProgressDBOpenHelper.COLUMN_NECK));
            float waist = cursor.getFloat(cursor.getColumnIndex(ProgressDBOpenHelper.COLUMN_WAIST));
            float hips = cursor.getFloat(cursor.getColumnIndex(ProgressDBOpenHelper.COLUMN_HIPS));
            Progress progress = new Progress(createdAt, neck, waist, hips);
            progress.setId(cursor.getColumnIndex(ProgressDBOpenHelper.COLUMN_ID));

            progresses.add(progress);
        }

        return progresses;
    }
}
