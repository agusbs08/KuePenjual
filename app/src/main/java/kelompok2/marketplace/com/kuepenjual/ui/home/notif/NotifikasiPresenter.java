package kelompok2.marketplace.com.kuepenjual.ui.home.notif;

import android.util.Log;

import java.util.ArrayList;

import kelompok2.marketplace.com.kuepenjual.base.BasePresenterNetwork;
import kelompok2.marketplace.com.kuepenjual.model.list.BarangTransaksiList;
import kelompok2.marketplace.com.kuepenjual.model.response.DataResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotifikasiPresenter extends BasePresenterNetwork{

    private NotifikasiView view;

    public NotifikasiPresenter(NotifikasiView view){
        super();
        this.view = view;
    }

    public void getListTransaksi(Integer idPenjual){
        view.showLoading();
        Call<DataResponse<BarangTransaksiList>> result = service.getAllTransaksi(idPenjual);

        result.enqueue(new Callback<DataResponse<BarangTransaksiList>>() {
            @Override
            public void onResponse(Call<DataResponse<BarangTransaksiList>> call, Response<DataResponse<BarangTransaksiList>> response) {
                ArrayList<BarangTransaksiList> barangTransaksiLists = response.body().getListData();
                view.showListTransaksi(barangTransaksiLists);
                view.hideLoading();
            }

            @Override
            public void onFailure(Call<DataResponse<BarangTransaksiList>> call, Throwable t) {
                Log.d("getNotif", t.getMessage());
            }
        });
    }
}
