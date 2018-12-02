package kelompok2.marketplace.com.kuepenjual.ui.home.beranda;

import android.util.Log;

import kelompok2.marketplace.com.kuepenjual.base.BasePresenterNetwork;
import kelompok2.marketplace.com.kuepenjual.model.Barang;
import kelompok2.marketplace.com.kuepenjual.model.response.ModelResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BerandaRecyclerViewPresenter extends BasePresenterNetwork{
    private BerandaRecyclerViewView view;

    public BerandaRecyclerViewPresenter(BerandaRecyclerViewView view){
        super();
        this.view = view;
    }

    public void deletePenjualan(Integer idPenjualan, final Integer idBarang){
        Call<ModelResponse<Barang>> result = service.deletePenjualan(idPenjualan);
        result.enqueue(new Callback<ModelResponse<Barang>>() {
            @Override
            public void onResponse(Call<ModelResponse<Barang>> call, Response<ModelResponse<Barang>> response) {
                if(response.isSuccessful()){
                    Log.e("deletePenjualan", response.body().getMessage());
                    deleteBarang(idBarang);
                }
                else{
                    Log.e("deletePenjualanr", response.message());
                }
            }

            @Override
            public void onFailure(Call<ModelResponse<Barang>> call, Throwable t) {
                Log.e("deletePenjualanf", t.getMessage());
            }
        });
    }

    private void deleteBarang(Integer idBarang){
        Call<ModelResponse<Barang>> result = service.deleteBarang(idBarang);

        result.enqueue(new Callback<ModelResponse<Barang>>() {
            @Override
            public void onResponse(Call<ModelResponse<Barang>> call, Response<ModelResponse<Barang>> response) {
                if(response.isSuccessful()){
                    Log.e("deleteBarang", response.body().getMessage());
                    view.refreshLayout();
                }
            }

            @Override
            public void onFailure(Call<ModelResponse<Barang>> call, Throwable t) {

            }
        });
    }
}
