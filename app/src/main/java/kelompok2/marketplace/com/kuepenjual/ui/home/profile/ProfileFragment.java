package kelompok2.marketplace.com.kuepenjual.ui.home.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import kelompok2.marketplace.com.kuepenjual.BuildConfig;
import kelompok2.marketplace.com.kuepenjual.R;
import kelompok2.marketplace.com.kuepenjual.common.UserState;
import kelompok2.marketplace.com.kuepenjual.ui.editprofil.EditProfilActivity;

public class ProfileFragment extends Fragment {

    private ImageView imgToko;
    private TextView namaToko;
    private TextView alamatToko;
    private TextView namaPemilik;
    private TextView emailPemilik;
    private TextView nomerPemilik;
    private Button btnEdit;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initBtn();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profil, container, false);
        initView(rootView);
        return  rootView;
    }

    private void initView(View view){
        imgToko = view.findViewById(R.id.img_toko_fragment_profil);
        namaToko = view.findViewById(R.id.tv_namatoko_fragment_profil);
        alamatToko = view.findViewById(R.id.tv_alamat_fragment_profil);
        namaPemilik = view.findViewById(R.id.tv_namapemilik_fragment_profil);
        emailPemilik = view.findViewById(R.id.tv_emailpemilik_fragment_profil);
        nomerPemilik = view.findViewById(R.id.tv_nomerpemilik_fragment_profil);
        btnEdit = view.findViewById(R.id.btn_editprofil_fragment_profil);
    }

    private void initData(){
        Picasso.get().load(UserState.getInstance().getPenjual().getImagePenjual()).into(imgToko);
        namaToko.setText(UserState.getInstance().getPenjual().getNamatoko());
        alamatToko.setText(UserState.getInstance().getPenjual().getAlamat());
        namaPemilik.setText(UserState.getInstance().getPenjual().getNama());
        emailPemilik.setText(UserState.getInstance().getPenjual().getEmail());
        nomerPemilik.setText(UserState.getInstance().getPenjual().getNoTelepon());
    }

    private void initBtn(){
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditProfilActivity.class);
                startActivity(intent);
            }
        });
    }
}
