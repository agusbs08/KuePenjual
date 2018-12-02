package kelompok2.marketplace.com.kuepenjual.ui.editprofil;

import android.util.Log;

import java.io.File;

import kelompok2.marketplace.com.kuepenjual.BuildConfig;
import kelompok2.marketplace.com.kuepenjual.base.BasePresenterNetwork;
import kelompok2.marketplace.com.kuepenjual.common.UserState;
import kelompok2.marketplace.com.kuepenjual.model.Penjual;
import kelompok2.marketplace.com.kuepenjual.model.response.ModelResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfilPresenter extends BasePresenterNetwork{
    private EditProfilView view;
    private String method = "patch";
    private String TAG = EditProfilPresenter.class.getSimpleName();

    public EditProfilPresenter(EditProfilView view){
        super();
        this.view = view;
    }

    public void updateUser(String namaUser, String namaToko, String email, String notlp, String alamat, File image){

        RequestBody reqMethod = RequestBody.create(MultipartBody.FORM, this.method);
        RequestBody reqPassword = RequestBody.create(MultipartBody.FORM, UserState.getInstance().getPenjual().getPassword());
        RequestBody reqKelamin = RequestBody.create(MultipartBody.FORM, UserState.getInstance().getPenjual().getJenisKelamin());
        RequestBody reqNamaUser = RequestBody.create(MultipartBody.FORM, namaUser);
        RequestBody reqNamaToko = RequestBody.create(MultipartBody.FORM, namaToko);
        RequestBody reqNoTlp = RequestBody.create(MultipartBody.FORM, notlp);
        RequestBody reqAlamat = RequestBody.create(MultipartBody.FORM, alamat);
        RequestBody requestBody = RequestBody.create(MultipartBody.FORM, image);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image_pen", image.getName(), requestBody);
        Call<ModelResponse<Penjual>> result = service.updatePenjual(reqMethod,reqPassword, reqKelamin, reqNamaUser, reqNamaToko, reqNoTlp, reqAlamat, body,
                UserState.getInstance().getPenjual().getId());

        result.enqueue(new Callback<ModelResponse<Penjual>>() {
            @Override
            public void onResponse(Call<ModelResponse<Penjual>> call, Response<ModelResponse<Penjual>> response) {
                if(response.isSuccessful()){
                    Penjual penjual = response.body().getModel();
                    UserState.getInstance().setPenjual(penjual);
                    UserState.getInstance().getPenjual().setImagePenjual(BuildConfig.BASE_STORAGE + penjual.getImagePenjual());
                    view.showActionUpdateSuccess();
                }
                else{
                    Log.d(TAG, response.message() + " r");
                }
            }

            @Override
            public void onFailure(Call<ModelResponse<Penjual>> call, Throwable t) {
                Log.d(TAG, t.getMessage() + " f");
            }
        });
    }
}
