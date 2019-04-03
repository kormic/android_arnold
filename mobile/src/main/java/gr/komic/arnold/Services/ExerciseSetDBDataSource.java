package gr.komic.arnold.Services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.jetbrains.annotations.Nullable;

import gr.komic.arnold.Models.ExerciseSet;

public class ExerciseSetDBDataSource {
    private static final String TAG = "ExerciseSetDBDataSource";
    private static final String[] columns = {
            DBOpenHelper.EXC_SET_COLUMN_SEQUENCE,
            DBOpenHelper.EXC_SET_COLUMN_REPS,
            DBOpenHelper.EXC_SET_COLUMN_LAST_WEIGHT,
            DBOpenHelper.EXC_SET_COLUMN_EXERCISE_ID
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
        database.insert(DBOpenHelper.EXC_SET_TABLE_NAME, null, values);

        return exerciseSet;
    }

    public @Nullable ExerciseSet getById(long id) {
        Cursor cursor = database.rawQuery("SELECT * FROM " + DBOpenHelper.EXC_SET_TABLE_NAME + " WHERE ID = ?", new String[]{String.valueOf(id)}, null);
        ExerciseSet exerciseSet = null;

        Log.i(TAG, "Returned " + cursor.getCount() + " rows");
        if (cursor.moveToFirst()) {
            int repsColumnIndex = cursor.getColumnIndex(DBOpenHelper.EXC_SET_COLUMN_REPS);
            int lastWeight = cursor.getColumnIndex(DBOpenHelper.EXC_SET_COLUMN_LAST_WEIGHT);
            exerciseSet = new ExerciseSet(cursor.getInt(repsColumnIndex), cursor.getFloat(lastWeight));
            int sequenceColumnIndex = cursor.getColumnIndex(DBOpenHelper.EXC_SET_COLUMN_SEQUENCE);
            int exerciseIdColumnIndex = cursor.getColumnIndex(DBOpenHelper.EXC_SET_COLUMN_EXERCISE_ID);
            exerciseSet.setSequence(cursor.getInt(sequenceColumnIndex));
            exerciseSet.setExerciseId(cursor.getLong(exerciseIdColumnIndex));
        }

        return exerciseSet;
    }
}
