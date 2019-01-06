package kelompok2.marketplace.com.kuepenjual.ui.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import kelompok2.marketplace.com.kuepenjual.base.BasePresenterNetwork;
import kelompok2.marketplace.com.kuepenjual.common.UserState;
import kelompok2.marketplace.com.kuepenjual.model.Penjual;
import kelompok2.marketplace.com.kuepenjual.model.response.ModelResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter extends BasePresenterNetwork{
    private LoginView view;
    private FirebaseAuth mAuth;
    private AppCompatActivity activity;
    private Call<ModelResponse<Penjual>> result;

    public LoginPresenter(LoginView view, FirebaseAuth mAuth, AppCompatActivity activity){
        super();
        this.view = view;
        this.mAuth = mAuth;
        this.activity = activity;
    }

    public void firebaseAuthWithGoogle(GoogleSignInAccount acct){
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("sign in", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            view.updateUI(user);
                        }
                        else{
                            view.showError("User tidak ditemukan");
                        }
                    }
                });
    }

    public void checkUser(String email, String password){
        view.showLoading();
        result = service.getUser(email, password);

        result.enqueue(new Callback<ModelResponse<Penjual>>() {
            @Override
            public void onResponse(Call<ModelResponse<Penjual>> call, Response<ModelResponse<Penjual>> response) {
                view.hideLoading();
                view.actionLoginSuccess(response.body().getModel());
            }

            @Override
            public void onFailure(Call<ModelResponse<Penjual>> call, Throwable t) {
                view.hideLoading();
                view.showError("User tidak ditemukan");
            }
        });
    }

    public void checkUserFromEmail(String email){
        view.showLoading();
        result = service.getUserFromEmail(email);

        result.enqueue(new Callback<ModelResponse<Penjual>>() {
            @Override
            public void onResponse(Call<ModelResponse<Penjual>> call, Response<ModelResponse<Penjual>> response) {
                view.hideLoading();
                view.actionLoginSuccess(response.body().getModel());
            }

            @Override
            public void onFailure(Call<ModelResponse<Penjual>> call, Throwable t) {
                view.hideLoading();
                view.showError("User tidak ditemukan");
            }
        });
    }
}

