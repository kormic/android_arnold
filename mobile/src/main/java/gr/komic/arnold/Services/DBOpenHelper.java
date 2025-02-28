package gr.komic.arnold.Services;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DBOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "arnold.db";

    //PROGRAM TABLE
    public static final String PROGRAM_TABLE_NAME = "Program";
    public static final String PROGRAM_COLUMN_ID = "Id";
    public static final String PROGRAM_COLUMN_TITLE = "Title";
    public static final String PROGRAM_COLUMN_CREATED_AT = "CreatedAt";
    public static final String PROGRAM_COLUMN_IS_CURRENT_PROGRAM = "isCurrent";
    public static final String CREATE_TABLE_PROGRAM = "CREATE TABLE IF NOT EXISTS "
            + PROGRAM_TABLE_NAME + " ("
            + PROGRAM_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PROGRAM_COLUMN_CREATED_AT + " TEXT NOT NULL,"
            + PROGRAM_COLUMN_TITLE + " TEXT NOT NULL,"
            + PROGRAM_COLUMN_IS_CURRENT_PROGRAM + " INTEGER NOT NULL)";

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
                                                        + PROGRESS_COLUMN_CREATED_AT + " TEXT NOT NULL,"
                                                        + PROGRESS_COLUMN_WAIST + " INTEGER NOT NULL,"
                                                        + PROGRESS_COLUMN_NECK + " INTEGER NOT NULL,"
                                                        + PROGRESS_COLUMN_HIPS + " INTEGER NOT NULL,"
                                                        + PROGRESS_COLUMN_PROGRAM_ID + " INTEGER NOT NULL,"
                                                        + " FOREIGN KEY("
                                                        + PROGRESS_COLUMN_PROGRAM_ID
                                                        + ") REFERENCES " + PROGRAM_TABLE_NAME + "(" + PROGRAM_COLUMN_ID +")"
                                                        + ")";

    //EXERCISE TABLE
    public static final String EXC_TABLE_NAME = "Exercise";
    public static final String EXC_COLUMN_ID = "Id";
    public static final String EXC_COLUMN_NAME = "Name";
    public static final String EXC_COLUMN_MUSCLE_GROUP = "Muscle_Group";
    public static final String EXC_COLUMN_IMG_URL = "ImageUrl";
    public static final String EXC_COLUMN_PROGRAM_ID =  "Program_Id";
    public static final String CREATE_TABLE_EXC = "CREATE TABLE IF NOT EXISTS "
                                                    + EXC_TABLE_NAME + " ("
                                                    + EXC_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                    + EXC_COLUMN_NAME + " TEXT NOT NULL,"
                                                    + EXC_COLUMN_MUSCLE_GROUP + " TEXT NOT NULL,"
                                                    + EXC_COLUMN_IMG_URL + " TEXT,"
                                                    + EXC_COLUMN_PROGRAM_ID + " INTEGER,"
                                                    + " FOREIGN KEY("
                                                    + EXC_COLUMN_PROGRAM_ID
                                                    + ") REFERENCES " + PROGRAM_TABLE_NAME + "(" + PROGRAM_COLUMN_ID +")"
                                                    + ")";

    //EXCERSICE SET TABLE
    public static final String EXC_SET_TABLE_NAME = "ExerciseSet";
    public static final String EXC_SET_COLUMN_SEQUENCE = "Sequence";
    public static final String EXC_SET_COLUMN_REPS = "Reps";
    public static final String EXC_SET_COLUMN_LAST_WEIGHT = "Last_Weight";
    public static final String EXC_SET_COLUMN_EXERCISE_ID = "Exercise_Id";
    public static final String EXC_SET_COLUMN_PROGRAM_ID = "Program_Id";
    public static final String CREATE_TABLE_EXC_SET = "CREATE TABLE IF NOT EXISTS "
                                                        + EXC_SET_TABLE_NAME + " ("
                                                        + EXC_SET_COLUMN_SEQUENCE + " INTEGER NOT NULL, "
                                                        + EXC_SET_COLUMN_REPS + " INTEGER NOT NULL,"
                                                        + EXC_SET_COLUMN_LAST_WEIGHT + " REAL NOT NULL,"
                                                        + EXC_SET_COLUMN_EXERCISE_ID + " INTEGER NOT NULL,"
                                                        + EXC_SET_COLUMN_PROGRAM_ID + " INTEGER NOT NULL,"
                                                        + " FOREIGN KEY("
                                                        + EXC_SET_COLUMN_EXERCISE_ID
                                                        + ") REFERENCES " + EXC_TABLE_NAME + "(" + EXC_COLUMN_ID +"),"
                                                        + " FOREIGN KEY("
                                                        + EXC_SET_COLUMN_PROGRAM_ID
                                                        + ") REFERENCES " + PROGRAM_TABLE_NAME + "(" + PROGRESS_COLUMN_ID +")"
                                                        + ")";

    //AVAILABLE EXERCISES
    public static final String AVAILABLE_EXC_TABLE_NAME = "AvailableExcercises";
    public static final String AVAILABLE_EXC_COLUMN_ID = "AvailableExerciseId";
    public static final String AVAILABLE_EXC_COLUMN_NAME = "Name";
    public static final String AVAILABLE_EXC_COLUMN_MUSCLE_GROUP = "Muscle_Group";
    public static final String CREATE_AVAILABLE_EXC_TABLE = "CREATE TABLE IF NOT EXISTS "
                                                        + AVAILABLE_EXC_TABLE_NAME + " ("
                                                        + AVAILABLE_EXC_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                        + AVAILABLE_EXC_COLUMN_NAME + " TEXT NOT NULL, "
                                                        + AVAILABLE_EXC_COLUMN_MUSCLE_GROUP + " TEXT NOT NULL)";


    public DBOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_PROGRAM);
        sqLiteDatabase.execSQL(CREATE_TABLE_PROGRESS);
        sqLiteDatabase.execSQL(CREATE_TABLE_EXC);
        sqLiteDatabase.execSQL(CREATE_TABLE_EXC_SET);
        sqLiteDatabase.execSQL(CREATE_AVAILABLE_EXC_TABLE);

        addAvailableExercises(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PROGRESS_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PROGRAM_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EXC_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EXC_SET_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + AVAILABLE_EXC_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    private void addAvailableExercises(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("INSERT INTO " + AVAILABLE_EXC_TABLE_NAME + " ("
                + AVAILABLE_EXC_COLUMN_NAME + ", " + AVAILABLE_EXC_COLUMN_MUSCLE_GROUP + ")" + " VALUES('CHEST PRESS', 'CHEST')");
        sqLiteDatabase.execSQL("INSERT INTO " + AVAILABLE_EXC_TABLE_NAME + " ("
                + AVAILABLE_EXC_COLUMN_NAME + ", " + AVAILABLE_EXC_COLUMN_MUSCLE_GROUP + ")" + " VALUES('PECK DECK', 'CHEST')");
        sqLiteDatabase.execSQL("INSERT INTO " + AVAILABLE_EXC_TABLE_NAME + " ("
                + AVAILABLE_EXC_COLUMN_NAME + ", " + AVAILABLE_EXC_COLUMN_MUSCLE_GROUP + ")" + " VALUES('SQUATS', 'LEGS')");
        sqLiteDatabase.execSQL("INSERT INTO " + AVAILABLE_EXC_TABLE_NAME + " ("
                + AVAILABLE_EXC_COLUMN_NAME + ", " + AVAILABLE_EXC_COLUMN_MUSCLE_GROUP + ")" + " VALUES('BICEPS CURL', 'BICEPS')");
    }
}
