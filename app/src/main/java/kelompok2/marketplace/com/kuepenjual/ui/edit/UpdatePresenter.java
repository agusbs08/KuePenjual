package kelompok2.marketplace.com.kuepenjual.ui.edit;

import android.util.Log;
import android.widget.Toast;

import kelompok2.marketplace.com.kuepenjual.base.BasePresenterNetwork;
import kelompok2.marketplace.com.kuepenjual.model.Barang;
import kelompok2.marketplace.com.kuepenjual.model.response.ModelResponse;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    public void updateBarang(String nama, Float harga, Float diskon, Float stok, String merk, byte[] image, Integer idBarang){
        Integer s = image.length;
        Log.e("image",idBarang.toString());
        Log.e("create","masuk");
        RequestBody reqNama = RequestBody.create(MediaType.parse("text/plain"), nama);
        RequestBody reqHarga = RequestBody.create(MediaType.parse("text/plain"), harga.toString());
        RequestBody reqDiskon = RequestBody.create(MediaType.parse("text/plain"), diskon.toString());
        RequestBody reqStok = RequestBody.create(MediaType.parse("text/plain"), stok.toString());
        RequestBody reqMerk = RequestBody.create(MediaType.parse("text/plain"), merk);
        RequestBody reqKategori = RequestBody.create(MediaType.parse("text/plain"), defaultKategori.toString());
        RequestBody reqRating = RequestBody.create(MediaType.parse("text/plain"), rating.toString());
        RequestBody jml = RequestBody.create(MediaType.parse("text/plain"), jumlah.toString());
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), image);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image_barang", "image.jpeg", requestBody);

        RequestBody reqMethod = RequestBody.create(MediaType.parse("text/plain"), "PATCH");
        //RequestBody reqMethod = RequestBody.create(MediaType.parse("text/plain"), "PATCH");
        result = service.updateBarang(reqMethod,reqKategori,reqHarga,reqDiskon,reqStok,reqNama,reqMerk,body,reqRating,jml,idBarang.toString());
        //result = service.updatedBarang(method,defaultKategori,harga,diskon,stok.intValue(),nama,merk,body,rating.floatValue(),jumlah,idBarang.toString() );
        //result = service.updateBarang(reqMethod,reqKategori,reqHarga,reqDiskon,reqStok,reqNama,reqMerk,body,reqRating,jml, idBarang.toString());
        result.enqueue(new Callback<ModelResponse<Barang>>() {
            @Override
            public void onResponse(Call<ModelResponse<Barang>> call, Response<ModelResponse<Barang>> response) {
                if(response.isSuccessful()){
                    Log.e("updateBarang", "success");
                    view.actionUpdateSuccess();
                }
                else{
                    Log.e("updateBarangr", response.message() + response.code() +response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ModelResponse<Barang>> call, Throwable t) {
                Log.e("updateBarangf", t.getMessage());
            }
        });
    }
}
