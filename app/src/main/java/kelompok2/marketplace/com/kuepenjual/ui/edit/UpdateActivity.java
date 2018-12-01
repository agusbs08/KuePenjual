package kelompok2.marketplace.com.kuepenjual.ui.edit;

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
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import kelompok2.marketplace.com.kuepenjual.BuildConfig;
import kelompok2.marketplace.com.kuepenjual.R;
import kelompok2.marketplace.com.kuepenjual.model.Barang;
import kelompok2.marketplace.com.kuepenjual.ui.home.HomeActivity;

public class UpdateActivity extends AppCompatActivity implements UpdateView{

    private Barang barang;
    private ImageButton imageButton;
    private EditText namaBarang;
    private EditText hargaBarang;
    private EditText stokBarang;
    private EditText diskonBarang;
    private EditText merkBarang;
    private Button btnTambah;
    private Bitmap foto;
    private int bitmap_size = 40; // image quality 1 - 100;
    private int max_resolution_image = 800;
    private UpdatePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_editbarang);
        initData();
        initView();
        setView();
    }

    private void setView(){
        presenter = new UpdatePresenter(this);
        namaBarang.setText(barang.getNama());
        hargaBarang.setText(barang.getHarga().toString());
        stokBarang.setText(barang.getStok().toString());
        diskonBarang.setText(barang.getPotonganHarga().toString());
        merkBarang.setText(barang.getMerk());
        loadImage();
        setImageButton();
        setBtnUpdate();
    }

    private void loadImage(){
        Picasso.get().load(BuildConfig.BASE_STORAGE + barang.getGambar()).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                foto = bitmap;
                imageButton.setImageBitmap(foto);
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }

    private void initView(){
        imageButton = findViewById(R.id.ib_photoitem_tambah);
        namaBarang = findViewById(R.id.et_namabarang_tambah);
        hargaBarang = findViewById(R.id.et_hargabarang_tambah);
        stokBarang = findViewById(R.id.et_stokbarang_tambah);
        diskonBarang = findViewById(R.id.et_diskonharga_tambah);
        merkBarang = findViewById(R.id.et_merkbarang_tambah);
        btnTambah = findViewById(R.id.btn_tambahbarang_tambah);
    }

    private void setBtnUpdate(){
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = namaBarang.getText().toString();
                Float harga = Float.parseFloat(hargaBarang.getText().toString());

                Float stok = Float.parseFloat(stokBarang.getText().toString());
                Float diskon = Float.parseFloat(diskonBarang.getText().toString());
                String merk = merkBarang.getText().toString();
               // byte[] image = createImageFile();
                File image = createImageFile();
                Toast.makeText(getApplicationContext(), nama, Toast.LENGTH_SHORT).show();
                presenter.updateBarang(nama,harga.intValue(),diskon.intValue(),stok.intValue(),merk,image, barang.getId());
            }
        });
    }

    private void setImageButton(){
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
            }
        });

    }

    private void initData(){
        Intent intent = getIntent();
        barang = (Barang) intent.getSerializableExtra("barang");
    }

    private File createImageFile(){
        try{
            File f = new File(getApplicationContext().getCacheDir(), "imageBarang");
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
        imageButton.setImageBitmap(decoded);
    }

    @Override
    public void actionUpdateSuccess() {
        Toast.makeText(getApplicationContext(), "Update Success", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
    }
}
