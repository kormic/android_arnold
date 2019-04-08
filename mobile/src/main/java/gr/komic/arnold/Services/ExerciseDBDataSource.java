package gr.komic.arnold.Services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.ParseException;
import java.util.ArrayList;

import gr.komic.arnold.Models.Exercise;

public class ExerciseDBDataSource {
    private static final String TAG = "ExerciseDataSource";
    private static final String[] columns = {
            DBOpenHelper.EXC_COLUMN_ID,
            DBOpenHelper.EXC_COLUMN_NAME,
            DBOpenHelper.EXC_COLUMN_IMG_URL,
            DBOpenHelper.EXC_COLUMN_MUSCLE_GROUP,
            DBOpenHelper.EXC_COLUMN_PROGRAM_ID
    };

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

    public ArrayList<Exercise> findExercisesByProgramId(long programId) {
        ArrayList<Exercise> exercises = new ArrayList<>();
        Cursor cursor = database.query(DBOpenHelper.EXC_TABLE_NAME, columns, DBOpenHelper.EXC_COLUMN_PROGRAM_ID + "=?", new String[] {String.valueOf(programId)}, null, null, null);

        Log.i(TAG, "Returned " + cursor.getCount() + " rows");
        while(cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(DBOpenHelper.EXC_COLUMN_NAME));
            long id = cursor.getLong(cursor.getColumnIndex(DBOpenHelper.EXC_COLUMN_ID));
            long program_Id = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.EXC_COLUMN_PROGRAM_ID));
            String muscleGroup = cursor.getString(cursor.getColumnIndex(DBOpenHelper.EXC_COLUMN_MUSCLE_GROUP));
            String imgUrl = cursor.getString(cursor.getColumnIndex(DBOpenHelper.EXC_COLUMN_IMG_URL));
            Exercise exercise = new Exercise(name);
            exercise.setMuscleGroup(muscleGroup);
            exercise.setImageUrl(imgUrl == null ? imgUrl : "");
            exercise.setId(id);
            exercise.setProgramId(program_Id);

            exercises.add(exercise);
        }

        return exercises;
    }

    public ArrayList<Exercise> findAll() {
        ArrayList<Exercise> exercises = new ArrayList<>();
        Cursor cursor = database.query(DBOpenHelper.EXC_TABLE_NAME, columns, null, null, null, null, null);

        Log.i(TAG, "Returned " + cursor.getCount() + " rows");
        while(cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(DBOpenHelper.EXC_COLUMN_NAME));
            long id = cursor.getLong(cursor.getColumnIndex(DBOpenHelper.EXC_COLUMN_ID));
            long program_Id = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.EXC_COLUMN_PROGRAM_ID));
            String muscleGroup = cursor.getString(cursor.getColumnIndex(DBOpenHelper.EXC_COLUMN_MUSCLE_GROUP));
            String imgUrl = cursor.getString(cursor.getColumnIndex(DBOpenHelper.EXC_COLUMN_IMG_URL));
            Exercise exercise = new Exercise(name);
            exercise.setMuscleGroup(muscleGroup);
            exercise.setImageUrl(imgUrl == null ? imgUrl : "");
            exercise.setId(id);
            exercise.setProgramId(program_Id);

            exercises.add(exercise);
        }

        return exercises;
    }
}
