package kelompok2.marketplace.com.kuepenjual.ui.home.search;

import android.util.Log;

import kelompok2.marketplace.com.kuepenjual.base.BasePresenterNetwork;
import kelompok2.marketplace.com.kuepenjual.model.Barang;
import kelompok2.marketplace.com.kuepenjual.model.response.ModelResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ridho on 05/12/18.
 */

public class SearchRecyclerViewPresenter extends BasePresenterNetwork {
    private SearchRecyclerViewView view;

    public SearchRecyclerViewPresenter(SearchRecyclerViewView view){
        super();
        this.view = view;
    }

    public void deletePenjualan(Integer idPenjualan, final Integer id) {
        Call<ModelResponse<Barang>> result = service.deletePenjualan(idPenjualan);
        result.enqueue(new Callback<ModelResponse<Barang>>() {
            @Override
            public void onResponse(Call<ModelResponse<Barang>> call, Response<ModelResponse<Barang>> response) {
                if(response.isSuccessful()){
                    Log.d("deletePenjualan", "Sukses hapus penjualan");
                    deleteBarang(id);
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

    private void deleteBarang(Integer id){
        Call<ModelResponse<Barang>> result = service.deleteBarang(id);

        result.enqueue(new Callback<ModelResponse<Barang>>() {
            @Override
            public void onResponse(Call<ModelResponse<Barang>> call, Response<ModelResponse<Barang>> response) {
                if (response.isSuccessful()) {
                    Log.d("deleteBarang", "sukses hapus barang");
                    view.refreshLayout();
                }
                else{
                    Log.e("deleteBarang", response.message());
                }
            }

            @Override
            public void onFailure(Call<ModelResponse<Barang>> call, Throwable t) {
                Log.e("deletebarang", t.getMessage());
            }
        });
    }
}
