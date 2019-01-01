package kelompok2.marketplace.com.kuepenjual.ui.register;

import android.util.Log;

import kelompok2.marketplace.com.kuepenjual.base.BasePresenterNetwork;
import kelompok2.marketplace.com.kuepenjual.model.Penjual;
import kelompok2.marketplace.com.kuepenjual.model.response.ModelResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter extends BasePresenterNetwork{
    private RegisterView view;
    private Call<ModelResponse<Penjual>> result;
    private final String TAG = RegisterPresenter.class.getSimpleName();

    public RegisterPresenter(RegisterView view){
        super();
        this.view = view;
    }

    public void register(String nama, String email, String password, String nohp, String namaToko, String gender){
        view.showLoading();
        result = service.registerPenjual(nama, password, email, nohp);
        result.enqueue(new Callback<ModelResponse<Penjual>>() {
            @Override
            public void onResponse(Call<ModelResponse<Penjual>> call, Response<ModelResponse<Penjual>> response) {
                view.hideLoading();
                if(response.isSuccessful()){
                    Penjual penjual = response.body().getModel();
                    view.actionRegisterSuccess(penjual.getId());
                }
                else{
                    Log.e(TAG, response.message());
                }
            }

            @Override
            public void onFailure(Call<ModelResponse<Penjual>> call, Throwable t) {
                view.hideLoading();
                Log.e(TAG, t.getMessage());
                view.actionRegisterFailed();
            }
        });
    }
}
