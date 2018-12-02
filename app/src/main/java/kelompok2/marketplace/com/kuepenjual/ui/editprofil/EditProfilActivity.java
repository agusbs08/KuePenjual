package kelompok2.marketplace.com.kuepenjual.ui.editprofil;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import kelompok2.marketplace.com.kuepenjual.R;
import kelompok2.marketplace.com.kuepenjual.common.UserState;
import kelompok2.marketplace.com.kuepenjual.ui.home.HomeActivity;

public class EditProfilActivity extends AppCompatActivity implements EditProfilView {

    private ImageButton imgToko;
    private EditText namaPemilik;
    private EditText emailPemilik;
    private EditText nomerPemilik;
    private EditText namaToko;
    private EditText alamatPemilik;
    private Button btnSimpan;

    private int bitmap_size = 40; // image quality 1 - 100;
    private int max_resolution_image = 800;
    private Bitmap foto;

    private EditProfilPresenter presenter;
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
        presenter = new EditProfilPresenter(this);
        initBtn();
    }

    private void initData(){
        namaPemilik.setText(UserState.getInstance().getPenjual().getNama());
        emailPemilik.setText(UserState.getInstance().getPenjual().getEmail());
        nomerPemilik.setText(UserState.getInstance().getPenjual().getNoTelepon());
        namaToko.setText(UserState.getInstance().getPenjual().getNamatoko());
        alamatPemilik.setText(UserState.getInstance().getPenjual().getAlamat());
        loadImage();
    }

    private void loadImage(){
        Picasso.get().load(UserState.getInstance().getPenjual().getImagePenjual()).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                foto = bitmap;
                imgToko.setImageBitmap(foto);
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }

    private void initBtn(){
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String namaUser = namaPemilik.getText().toString();
               String namatoko = namaToko.getText().toString();
               String email = emailPemilik.getText().toString();
               String notlp = nomerPemilik.getText().toString();
               String alamat = alamatPemilik.getText().toString();
                Toast.makeText(getApplicationContext(), namaUser + namatoko, Toast.LENGTH_SHORT).show();
               presenter.updateUser(namaUser, namatoko, email, notlp, alamat, createImageFile());
            }
        });

        imgToko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                setToImageView(getResizedBitmap(bitmap, max_resolution_image));
                foto = bitmap;
            }
            catch (Exception e){

            }
        }
    }

    private File createImageFile(){
        try{
            File f = new File(getApplicationContext().getCacheDir(), "imageToko");
            f.createNewFile();

            Bitmap bitmap = foto;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100 /*ignored for PNG*/, bos);
            byte[] bitmapdata = bos.toByteArray();
            //write the bytes in file
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
            //return bitmapdata;
            return f;
        }
        catch (Exception e){
            Log.e("createFile",e.getMessage());
            return null;
        }
    }

    private Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    private void setToImageView(Bitmap bmp) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
        Bitmap decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));

        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        imgToko.setImageBitmap(decoded);
    }


    @Override
    public void showActionUpdateSuccess() {
        Toast.makeText(getApplicationContext(), "Update Profile Success", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        intent.putExtra("state", 3);
        startActivity(intent);
    }
}
