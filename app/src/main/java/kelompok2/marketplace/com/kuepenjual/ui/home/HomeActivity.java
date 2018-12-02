package kelompok2.marketplace.com.kuepenjual.ui.home;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.messaging.FirebaseMessaging;

import kelompok2.marketplace.com.kuepenjual.R;
import kelompok2.marketplace.com.kuepenjual.ui.home.beranda.BerandaFragment;
import kelompok2.marketplace.com.kuepenjual.ui.home.notif.NotifikasiFragment;
import kelompok2.marketplace.com.kuepenjual.ui.home.profile.ProfileFragment;
import kelompok2.marketplace.com.kuepenjual.ui.home.statistik.StatistikFragment;
import kelompok2.marketplace.com.kuepenjual.ui.home.tambahbarang.TambahBarangFragment;
import kelompok2.marketplace.com.kuepenjual.util.BottomNavigationViewHelper;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView btn;

    private String topicToSubscribe = "frompembeli";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        subscribeToTopic();
        btn = findViewById(R.id.bottom_navigation_menu);
        BottomNavigationViewHelper.disableShiftMode(btn);
        initBtn();
        btn.setSelectedItemId(R.id.menu_beranda);

    }

    private void subscribeToTopic(){
        FirebaseMessaging.getInstance().subscribeToTopic(topicToSubscribe);
    }

    private void initBtn(){
        btn.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_beranda :
                        setFragment(new BerandaFragment());
                        break;

                    case R.id.menu_statistik :
                        setFragment(new StatistikFragment());
                        break;

                    case R.id.menu_tambah_barang :
                        setFragment(new TambahBarangFragment());
                        break;

                    case R.id.menu_notif :
                        setFragment(new NotifikasiFragment());
                        break;

                    case R.id.menu_profil :
                        setFragment(new ProfileFragment());
                        break;
                }
                return true;
            }
        });
    }

    private void setFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, fragment, "Home")
                .commit();
    }
}
