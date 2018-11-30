package kelompok2.marketplace.com.kuepenjual.ui.home.tambahbarang;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import kelompok2.marketplace.com.kuepenjual.R;

public class TambahBarangFragment extends Fragment implements TambahBarangView{

    private ImageButton imageButton;
    private ImageView imageView;
    private EditText namaBarang;
    private EditText hargaBarang;
    private EditText stokBarang;
    private EditText diskonBarang;
    private EditText merkBarang;
    private Button btnTambah;
    private Bitmap foto;
    private int bitmap_size = 40; // image quality 1 - 100;
    private int max_resolution_image = 800;
    private TambahBarangPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tambah, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View view){
        imageButton = view.findViewById(R.id.ib_photoitem_tambah);
        imageView = view.findViewById(R.id.iv_photoitem_tambah);
        namaBarang = view.findViewById(R.id.et_namabarang_tambah);
        hargaBarang = view.findViewById(R.id.et_hargabarang_tambah);
        stokBarang = view.findViewById(R.id.et_stokbarang_tambah);
        diskonBarang = view.findViewById(R.id.et_diskonharga_tambah);
        merkBarang = view.findViewById(R.id.et_merkbarang_tambah);
        btnTambah = view.findViewById(R.id.btn_tambahbarang_tambah);
        presenter = new TambahBarangPresenter(this);
        imageView.setVisibility(View.GONE);
        setActionImageButton();
        initBtnTambah();
    }

    private void setActionImageButton(){
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

    private void initBtnTambah(){
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = namaBarang.getText().toString();
                Integer harga = Integer.parseInt(hargaBarang.getText().toString());
                Integer stok = Integer.parseInt(stokBarang.getText().toString());
                Integer diskon = Integer.parseInt(diskonBarang.getText().toString());
                String merk = merkBarang.getText().toString();
                byte[] image = createImageFile();
                presenter.createBarang(nama,harga,diskon,stok,merk,image);

            }
        });
    }

    private byte[] createImageFile(){
        try{
            File f = new File(getContext().getCacheDir(), "imageBarang");
            f.createNewFile();

            Bitmap bitmap = foto;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100 /*ignored for PNG*/, bos);
            byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
//            FileOutputStream fos = new FileOutputStream(f);
//            fos.write(bitmapdata);
//            fos.flush();
//            fos.close();
            return bitmapdata;
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
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), data.getData());
                setToImageView(getResizedBitmap(bitmap, max_resolution_image));
                foto = bitmap;
                imageView.setVisibility(View.VISIBLE);
                imageButton.setVisibility(View.GONE);
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
        imageView.setImageBitmap(decoded);
        imageView.setVisibility(View.VISIBLE);
        imageButton.setVisibility(View.GONE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void actionCreateSuccess() {
        Toast.makeText(getContext(), "Berhasil", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
