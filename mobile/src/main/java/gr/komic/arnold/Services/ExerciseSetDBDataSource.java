package gr.komic.arnold.Services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import gr.komic.arnold.Models.ExerciseSet;

public class ExerciseSetDBDataSource {
    private static final String TAG = "ExerciseSetDBDataSource";
    private static final String[] columns = {
            DBOpenHelper.EXC_SET_COLUMN_SEQUENCE,
            DBOpenHelper.EXC_SET_COLUMN_REPS,
            DBOpenHelper.EXC_SET_COLUMN_LAST_WEIGHT,
            DBOpenHelper.EXC_SET_COLUMN_EXERCISE_ID,
            DBOpenHelper.EXC_SET_COLUMN_PROGRAM_ID
    };

    DBOpenHelper dbOpenHelper;
    SQLiteDatabase database;

    public ExerciseSetDBDataSource(Context context) {
        dbOpenHelper = new DBOpenHelper(context);
    }

    public void open() {
        Log.i(TAG, "ExerciseSetDB opened");
        database = dbOpenHelper.getReadableDatabase();
    }

    public void close() {
        Log.i(TAG, "ExerciseSetDB closed");
        database.close();
    }

    public ExerciseSet insert(ExerciseSet exerciseSet) {
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.EXC_SET_COLUMN_SEQUENCE, exerciseSet.getSequence());
        values.put(DBOpenHelper.EXC_SET_COLUMN_REPS, exerciseSet.getReps());
        values.put(DBOpenHelper.EXC_SET_COLUMN_LAST_WEIGHT, exerciseSet.getLastWeight());
        values.put(DBOpenHelper.EXC_SET_COLUMN_EXERCISE_ID, exerciseSet.getExerciseId());
        values.put(DBOpenHelper.EXC_SET_COLUMN_PROGRAM_ID, exerciseSet.getProgramId());
        database.insert(DBOpenHelper.EXC_SET_TABLE_NAME, null, values);

        return exerciseSet;
    }

    public ArrayList<ExerciseSet> getByProgramAndExerciseId(long programId, long exerciseId) {
        ArrayList<ExerciseSet> exerciseSets = new ArrayList<>();

        Cursor cursor = database.query(
                DBOpenHelper.EXC_SET_TABLE_NAME,
                columns,
                DBOpenHelper.EXC_COLUMN_PROGRAM_ID + "=?" + " AND " + DBOpenHelper.EXC_SET_COLUMN_EXERCISE_ID + "=?",
                new String[] {String.valueOf(programId), String.valueOf(exerciseId)},
                null,
                null,
                null
        );

        Log.i(TAG, "Returned " + cursor.getCount() + " rows");
        while(cursor.moveToNext()) {
            int sequence = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.EXC_SET_COLUMN_SEQUENCE));
            int reps = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.EXC_SET_COLUMN_REPS));
            float lastWeight = cursor.getFloat(cursor.getColumnIndex(DBOpenHelper.EXC_SET_COLUMN_LAST_WEIGHT));
            long program_Id = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.EXC_COLUMN_PROGRAM_ID));
            long exercise_Id = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.EXC_SET_COLUMN_EXERCISE_ID));

            ExerciseSet exerciseSet = new ExerciseSet(reps, lastWeight);
            exerciseSet.setSequence(sequence);
            exerciseSet.setExerciseId(exercise_Id);
            exerciseSet.setProgramId(program_Id);

            exerciseSets.add(exerciseSet);
        }

        return exerciseSets;
    }
}
