package kelompok2.marketplace.com.kuepenjual.ui.register;

import kelompok2.marketplace.com.kuepenjual.base.BasePresenterNetwork;
import kelompok2.marketplace.com.kuepenjual.model.Penjual;
import kelompok2.marketplace.com.kuepenjual.model.response.ModelResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter extends BasePresenterNetwork{
    private RegisterView view;
    private Call<ModelResponse<Penjual>> result;

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
                Penjual penjual = response.body().getModel();
                view.hideLoading();
                view.actionRegisterSuccess(penjual.getId());
            }

            @Override
            public void onFailure(Call<ModelResponse<Penjual>> call, Throwable t) {
                view.hideLoading();
                view.showError();
            }
        });
    }
}
