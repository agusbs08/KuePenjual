package kelompok2.marketplace.com.kuepenjual.ui.home.tambahbarang;

import android.util.Log;

import java.io.File;

import kelompok2.marketplace.com.kuepenjual.base.BasePresenterNetwork;
import kelompok2.marketplace.com.kuepenjual.common.UserState;
import kelompok2.marketplace.com.kuepenjual.model.Barang;
import kelompok2.marketplace.com.kuepenjual.model.Penjualan;
import kelompok2.marketplace.com.kuepenjual.model.response.ModelResponse;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahBarangPresenter extends BasePresenterNetwork{
    private Call<ModelResponse<Barang>> result;
    private Integer idKategori = 1;
    private Integer rating = 1;
    private Integer jumlahTerjual = 0;

    private TambahBarangView view;
    public TambahBarangPresenter(TambahBarangView view){
        this.view = view;
    }

    public void createBarang(String nama, Integer harga, Integer diskon, Integer stok, String merk, byte[] image){
        Log.e("create","masuk");
        RequestBody reqNama = RequestBody.create(MediaType.parse("text/plain"), nama);
        RequestBody reqHarga = RequestBody.create(MediaType.parse("text/plain"), harga.toString());
        RequestBody reqDiskon = RequestBody.create(MediaType.parse("text/plain"), diskon.toString());
        RequestBody reqStok = RequestBody.create(MediaType.parse("text/plain"), stok.toString());
        RequestBody reqMerk = RequestBody.create(MediaType.parse("text/plain"), merk);
        RequestBody reqKategori = RequestBody.create(MediaType.parse("text/plain"), idKategori.toString());
        RequestBody reqRating = RequestBody.create(MediaType.parse("text/plain"), rating.toString());
        RequestBody jml = RequestBody.create(MediaType.parse("text/plain"), jumlahTerjual.toString());
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), image);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image_barang", "image.jpeg", requestBody);
        result = service.createBarang(reqKategori,reqHarga,reqDiskon,reqStok,reqNama,reqMerk,body,reqRating,jml);

        result.enqueue(new Callback<ModelResponse<Barang>>() {
            @Override
            public void onResponse(Call<ModelResponse<Barang>> call, Response<ModelResponse<Barang>> response) {
                if(response.isSuccessful()){
                    Log.e("createBarangr", "success");
                    addBarangtoPenjual(response.body().getModel().getId());
                }
                else{
                    Log.e("createBarangr", response.message());
                }
            }

            @Override
            public void onFailure(Call<ModelResponse<Barang>> call, Throwable t) {
                Log.e("createBarangf", t.getMessage());
            }
        });
    }

    private void addBarangtoPenjual(Integer idBarang){
        Call<ModelResponse<Penjualan>> penjualan = service.createPenjualan(UserState.getInstance().getIdUser(), idBarang);

        penjualan.enqueue(new Callback<ModelResponse<Penjualan>>() {
            @Override
            public void onResponse(Call<ModelResponse<Penjualan>> call, Response<ModelResponse<Penjualan>> response) {
                if(response.isSuccessful()){
                    Log.e("addPenjualan", "success");
                    view.actionCreateSuccess();
                }
                else {
                    Log.e("addPenjualan", response.message());
                }
            }

            @Override
            public void onFailure(Call<ModelResponse<Penjualan>> call, Throwable t) {
                Log.e("addPenjualan", t.getMessage());
            }
        });
    }
}
