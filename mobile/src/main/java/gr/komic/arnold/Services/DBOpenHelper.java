package gr.komic.arnold.Services;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DBOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "arnold.db";

    // PROGRESS TABLE
    public static final String PROGRESS_TABLE_NAME = "Progress";
    public static final String PROGRESS_COLUMN_ID = "Id";
    public static final String PROGRESS_COLUMN_CREATED_AT = "CreatedAt";
    public static final String PROGRESS_COLUMN_WAIST = "Waist";
    public static final String PROGRESS_COLUMN_NECK = "Neck";
    public static final String PROGRESS_COLUMN_HIPS = "Hips";
    public static final String PROGRESS_COLUMN_PROGRAM_ID = "Program_Id";
    public static final String CREATE_TABLE_PROGRESS = "CREATE TABLE IF NOT EXISTS "
                                                        + PROGRESS_TABLE_NAME + " ("
                                                        + PROGRESS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                        + PROGRESS_COLUMN_CREATED_AT + " VARCHAR,"
                                                        + PROGRESS_COLUMN_WAIST + " INTEGER(3),"
                                                        + PROGRESS_COLUMN_NECK + " INTEGER(3),"
                                                        + PROGRESS_COLUMN_HIPS + " INTEGER(3),"
                                                        + PROGRESS_COLUMN_PROGRAM_ID + " INTEGER)";

    //PROGRAM TABLE
    public static final String PROGRAM_TABLE_NAME = "Program";
    public static final String PROGRAM_COLUMN_ID = "Id";
    public static final String PROGRAM_COLUMN_TITLE = "Title";
    public static final String PROGRAM_COLUMN_CREATED_AT = "CreatedAt";
    public static final String PROGRAM_COLUMN_IS_CURRENT_PROGRAM = "isCurrent";
    public static final String CREATE_TABLE_PROGRAM = "CREATE TABLE IF NOT EXISTS "
                                                        + PROGRAM_TABLE_NAME + " ("
                                                        + PROGRAM_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                        + PROGRAM_COLUMN_CREATED_AT + " VARCHAR,"
                                                        + PROGRAM_COLUMN_TITLE + " VARCHAR,"
                                                        + PROGRAM_COLUMN_IS_CURRENT_PROGRAM + " BOOLEAN(0))";

    //EXERCISE TABLE
    public static final String EXC_TABLE_NAME = "Exercise";
    public static final String EXC_COLUMN_ID = "Id";
    public static final String EXC_COLUMN_NAME = "Name";
    public static final String EXC_COLUMN_IMG_URL = "ImageUrl";
    public static final String EXC_COLUMN_PROGRAM_ID =  "Program_Id";
    public static final String CREATE_TABLE_EXC = "CREATE TABLE IF NOT EXISTS "
                                                    + EXC_TABLE_NAME + " ("
                                                    + EXC_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                    + EXC_COLUMN_NAME + " VARCHAR,"
                                                    + EXC_COLUMN_IMG_URL + " VARCHAR,"
                                                    + EXC_COLUMN_PROGRAM_ID + " INTEGER)";

    //EXCERSICE SET TABLE
    public static final String EXC_SET_TABLE_NAME = "ExerciseSet";
    public static final String EXC_SET_COLUMN_SEQUENCE = "Sequence";
    public static final String EXC_SET_COLUMN_REPS = "Reps";
    public static final String EXC_SET_COLUMN_LAST_WEIGHT = "Last_Weight";
    public static final String EXC_SET_COLUMN_EXERCISE_ID = "Exercise_Id";
    public static final String CREATE_TABLE_EXC_SET = "CREATE TABLE IF NOT EXISTS "
                                                        + EXC_SET_TABLE_NAME + " ("
                                                        + EXC_SET_COLUMN_SEQUENCE + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                        + EXC_SET_COLUMN_REPS + " VARCHAR,"
                                                        + EXC_SET_COLUMN_LAST_WEIGHT + " VARCHAR,"
                                                        + EXC_SET_COLUMN_EXERCISE_ID + " INTEGER)";

    public DBOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_PROGRESS);
        sqLiteDatabase.execSQL(CREATE_TABLE_PROGRAM);
        sqLiteDatabase.execSQL(CREATE_TABLE_EXC);
        sqLiteDatabase.execSQL(CREATE_TABLE_EXC_SET);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PROGRESS_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PROGRAM_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EXC_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EXC_SET_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
