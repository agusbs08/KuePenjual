package kelompok2.marketplace.com.kuepenjual.ui.home.search;

import java.util.ArrayList;

import kelompok2.marketplace.com.kuepenjual.base.BasePresenterNetwork;
import kelompok2.marketplace.com.kuepenjual.common.UserState;
import kelompok2.marketplace.com.kuepenjual.model.Penjualan;
import kelompok2.marketplace.com.kuepenjual.model.PenjualanBarangList;
import kelompok2.marketplace.com.kuepenjual.model.response.ModelResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresenter extends BasePresenterNetwork{
    private SearchView view;

    public SearchPresenter(SearchView view){
        super();
        this.view = view;
    }

    public void getAllBarang(){
        view.showLoading();
        Call<ModelResponse<Penjualan>> result =service.getBarangsBeranda(UserState.getInstance().getIdUser());
        result.enqueue(new Callback<ModelResponse<Penjualan>>() {
            @Override
            public void onResponse(Call<ModelResponse<Penjualan>> call, Response<ModelResponse<Penjualan>> response) {
                ArrayList<PenjualanBarangList> listBarang = response.body().getModel().getListPenjualanBarang();
                view.hideLoading();
                view.showListBarang(listBarang);
            }

            @Override
            public void onFailure(Call<ModelResponse<Penjualan>> call, Throwable t) {

            }
        });
    }
}
