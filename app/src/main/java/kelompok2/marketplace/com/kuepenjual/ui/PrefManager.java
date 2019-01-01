package kelompok2.marketplace.com.kuepenjual.ui;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import kelompok2.marketplace.com.kuepenjual.model.Penjual;


public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "kue-welcome";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    private static final String USER_ID = "user-id";
    private static final String OBJ_PENJUAL = "penjual";

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public void setUser(Integer idUser, Penjual penjual){
        editor.putInt(USER_ID, idUser);
        Gson gson = new Gson();
        String json = gson.toJson(penjual);
        editor.putString(OBJ_PENJUAL, json);
        editor.commit();
    }

    public Integer getUserId(){
        return pref.getInt(USER_ID,0);
    }

    public Penjual getObjPenjual(){
        Gson gson = new Gson();
        String json = pref.getString(OBJ_PENJUAL, "");
        Penjual penjual = gson.fromJson(json, Penjual.class);
        return penjual;
    }
}