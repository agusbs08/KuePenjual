package kelompok2.marketplace.com.kuepenjual.ui.edit;

import android.util.Log;
import android.widget.Toast;

import java.io.File;

import kelompok2.marketplace.com.kuepenjual.base.BasePresenterNetwork;
import kelompok2.marketplace.com.kuepenjual.model.Barang;
import kelompok2.marketplace.com.kuepenjual.model.response.ModelResponse;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;

public class UpdatePresenter extends BasePresenterNetwork {
    private UpdateView view;
    private Call<ModelResponse<Barang>> result;
    private Integer defaultKategori ;
    private Integer rating = 3;
    private Integer jumlah = 0;
    private String method;

    public UpdatePresenter(UpdateView view){
        this.view = view;
        defaultKategori = 1;
        this.method = "PATCH";
    }

    public void updateBarang(String nama, Integer harga, Integer diskon, Integer stok, String merk, File image, Integer idBarang){
        Log.e("image",idBarang.toString());
        Log.e("create","masuk");
        RequestBody reqNama = RequestBody.create(MultipartBody.FORM, nama);
        RequestBody reqHarga = RequestBody.create(MultipartBody.FORM, harga.toString());
        RequestBody reqDiskon = RequestBody.create(MultipartBody.FORM, diskon.toString());
        RequestBody reqStok = RequestBody.create(MultipartBody.FORM, stok.toString());
        RequestBody reqMerk = RequestBody.create(MultipartBody.FORM, merk);
        RequestBody reqKategori = RequestBody.create(MultipartBody.FORM, defaultKategori.toString());
        RequestBody reqRating = RequestBody.create(MultipartBody.FORM, rating.toString());
        RequestBody jml = RequestBody.create(MultipartBody.FORM, jumlah.toString());
        RequestBody requestBody = RequestBody.create(MultipartBody.FORM, image);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image_barang", image.getName(), requestBody);

        RequestBody reqMethod = RequestBody.create(MultipartBody.FORM, this.method);

        Call<Void> results = service.updateBarangVoid(reqMethod,reqKategori,reqHarga,reqDiskon,reqStok,reqNama,reqMerk,body,reqRating,jml,idBarang);
        results.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Integer code = response.code();
                    Log.e("updateBarang", "success " + code.toString());
                    view.actionUpdateSuccess();
                }
                else{
                    Log.e("updateBarangr", response.message() + response.code() +response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("updateBarangf", t.getMessage().toString());
            }
        });
    }
}
