package gr.komic.arnold.Services;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;

import gr.komic.arnold.Models.UserBodyInfo;
import gr.komic.arnold.helpers.Constants;

import static android.content.Context.MODE_PRIVATE;

public class UserService {

    SharedPreferences mSharedPrefs;
    SharedPreferences.Editor mEditor;
    Gson gson = new Gson();

    private static final UserService ourInstance = new UserService();

    public static UserService getInstance() {
        return ourInstance;
    }

    private UserService() {
    }

    public UserBodyInfo restoreUserInfo(AppCompatActivity activity) {
        this.mSharedPrefs = activity.getSharedPreferences(Constants.BODY_INFO, MODE_PRIVATE);
        this.mEditor = mSharedPrefs.edit();

        String storedUserInfoJson = this.mSharedPrefs.getString(Constants.USER_INFO_OBJECT, null);
        if(storedUserInfoJson != null) {
            return gson.fromJson(storedUserInfoJson, UserBodyInfo.class);
        }else {
            return new UserBodyInfo();
        }
    }
}
