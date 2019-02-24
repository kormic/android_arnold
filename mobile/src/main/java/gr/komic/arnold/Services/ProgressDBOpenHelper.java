package gr.komic.arnold.Services;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProgressDBOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "arnold.db";
    public static final String TABLE_NAME = "Progress";
    public static final String COLUMN_ID = "Id";
    public static final String COLUMN_CREATED_AT = "CreatedAt";
    public static final String COLUMN_WAIST = "Waist";
    public static final String COLUMN_NECK = "Neck";
    public static final String COLUMN_HIPS = "Hips";


    public ProgressDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS "
                + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_CREATED_AT + " VARCHAR,"
                + COLUMN_WAIST + " INTEGER(3),"
                + COLUMN_NECK + " INTEGER(3),"
                + COLUMN_HIPS + " INTEGER(3))"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
