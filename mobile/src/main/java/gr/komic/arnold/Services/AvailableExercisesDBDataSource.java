package gr.komic.arnold.Services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import gr.komic.arnold.Models.AvailableExercise;
import gr.komic.arnold.Models.Exercise;

public class AvailableExercisesDBDataSource {
    private static final String TAG = "AvailableExercisesDB";
    private static final String[] columns = {
            DBOpenHelper.AVAILABLE_EXC_COLUMN_ID,
            DBOpenHelper.AVAILABLE_EXC_COLUMN_NAME,
            DBOpenHelper.AVAILABLE_EXC_COLUMN_MUSCLE_GROUP
    };

    DBOpenHelper dbOpenHelper;
    SQLiteDatabase database;

    public AvailableExercisesDBDataSource(Context context) { dbOpenHelper = new DBOpenHelper(context); }

    public void open() {
        Log.i(TAG, "ExerciseDB opened");
        database = dbOpenHelper.getReadableDatabase();
    }

    public void close() {
        Log.i(TAG, "ExerciseDB closed");
        database.close();
    }

    public ArrayList<AvailableExercise> findAllByGroup(String group) {
        ArrayList<AvailableExercise> availableExercises = new ArrayList<>();
        Cursor cursor = database.query(DBOpenHelper.AVAILABLE_EXC_TABLE_NAME, columns, DBOpenHelper.AVAILABLE_EXC_COLUMN_MUSCLE_GROUP + "=?", new String[] { group }, null, null, null);

        Log.i(TAG, "Returned " + cursor.getCount() + " rows");
        while(cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(DBOpenHelper.AVAILABLE_EXC_COLUMN_NAME));
            long id = cursor.getLong(cursor.getColumnIndex(DBOpenHelper.AVAILABLE_EXC_COLUMN_ID));
            String muscleGroup = cursor.getString(cursor.getColumnIndex(DBOpenHelper.AVAILABLE_EXC_COLUMN_MUSCLE_GROUP));
            AvailableExercise availableExercise = new AvailableExercise(name, muscleGroup);
            availableExercise.setId(id);

            availableExercises.add(availableExercise);
        }

        return availableExercises;
    }

    public ArrayList<AvailableExercise> findAll() {
        ArrayList<AvailableExercise> availableExercises = new ArrayList<>();
        Cursor cursor = database.query(DBOpenHelper.AVAILABLE_EXC_TABLE_NAME, columns, null, null, null, null, null);

        Log.i(TAG, "Returned " + cursor.getCount() + " rows");
        while(cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(DBOpenHelper.AVAILABLE_EXC_COLUMN_NAME));
            long id = cursor.getLong(cursor.getColumnIndex(DBOpenHelper.AVAILABLE_EXC_COLUMN_ID));
            String muscleGroup = cursor.getString(cursor.getColumnIndex(DBOpenHelper.AVAILABLE_EXC_COLUMN_MUSCLE_GROUP));
            AvailableExercise availableExercise = new AvailableExercise(name, muscleGroup);
            availableExercise.setId(id);

            availableExercises.add(availableExercise);
        }

        return availableExercises;
    }
}
