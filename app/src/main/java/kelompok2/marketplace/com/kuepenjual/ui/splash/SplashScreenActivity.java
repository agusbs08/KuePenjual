package kelompok2.marketplace.com.kuepenjual.ui.splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import kelompok2.marketplace.com.kuepenjual.R;
import kelompok2.marketplace.com.kuepenjual.common.UserState;
import kelompok2.marketplace.com.kuepenjual.ui.PrefManager;
import kelompok2.marketplace.com.kuepenjual.ui.intro.IntroActivity;
import kelompok2.marketplace.com.kuepenjual.ui.login.LoginActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        checkUserState();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),IntroActivity.class));
                finish();
            }
        }, 2000L);
    }

    private void checkUserState(){
        PrefManager pref = new PrefManager(this);
        if(pref.getUserId() != 0){
            UserState.getInstance().setIdUser(pref.getUserId());
            UserState.getInstance().setPenjual(pref.getObjPenjual());
        }
    }
}

