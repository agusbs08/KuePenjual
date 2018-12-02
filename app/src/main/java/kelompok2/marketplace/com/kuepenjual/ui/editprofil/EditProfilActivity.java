package kelompok2.marketplace.com.kuepenjual.ui.editprofil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.squareup.picasso.Picasso;

import kelompok2.marketplace.com.kuepenjual.R;
import kelompok2.marketplace.com.kuepenjual.common.UserState;

public class EditProfilActivity extends AppCompatActivity {

    private ImageButton imgToko;
    private EditText namaPemilik;
    private EditText emailPemilik;
    private EditText nomerPemilik;
    private EditText namaToko;
    private EditText alamatPemilik;
    private Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);
        initView();
        initData();
    }

    private void initView(){
        imgToko = findViewById(R.id.ib_photoitem_tambah);
        namaPemilik = findViewById(R.id.et_namapemilik_editprofile);
        emailPemilik = findViewById(R.id.et_email_editprofile);
        nomerPemilik = findViewById(R.id.et_nomor_editprofile);
        namaToko = findViewById(R.id.et_namatoko_editprofile);
        alamatPemilik = findViewById(R.id.et_alamat_editprofile);
        btnSimpan = findViewById(R.id.btn_simpan_editprofile);
        initBtn();
    }

    private void initData(){
        Picasso.get().load(UserState.getInstance().getPenjual().getImagePenjual()).into(imgToko);
        namaPemilik.setText(UserState.getInstance().getPenjual().getNama());
        emailPemilik.setText(UserState.getInstance().getPenjual().getEmail());
        nomerPemilik.setText(UserState.getInstance().getPenjual().getNoTelepon());
        namaToko.setText(UserState.getInstance().getPenjual().getNamatoko());
        alamatPemilik.setText(UserState.getInstance().getPenjual().getAlamat());
    }

    private void initBtn(){
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
