package gr.komic.arnold.Services;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import gr.komic.arnold.Models.Exercise;

public class ExerciseDBDataSource {
    private static final String TAG = "ExerciseDataSource";

    DBOpenHelper dbOpenHelper;
    SQLiteDatabase database;

    public ExerciseDBDataSource(Context context) { dbOpenHelper = new DBOpenHelper(context); }

    public void open() {
        Log.i(TAG, "ExerciseDB opened");
        database = dbOpenHelper.getReadableDatabase();
    }

    public void close() {
        Log.i(TAG, "ExerciseDB closed");
        database.close();
    }

    public Exercise insert(Exercise exercise) {
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.EXC_COLUMN_NAME, exercise.getName());
        values.put(DBOpenHelper.EXC_COLUMN_IMG_URL, exercise.getImageUrl());
        values.put(DBOpenHelper.EXC_COLUMN_MUSCLE_GROUP, exercise.getMuscleGroup());
        values.put(DBOpenHelper.EXC_COLUMN_PROGRAM_ID, exercise.getProgramId());
        long id = database.insert(DBOpenHelper.EXC_TABLE_NAME, null, values);
        exercise.setId(id);

        return exercise;
    }
}
