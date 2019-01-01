package kelompok2.marketplace.com.kuepenjual.ui.login;

import com.google.firebase.auth.FirebaseUser;

import kelompok2.marketplace.com.kuepenjual.base.BaseView;
import kelompok2.marketplace.com.kuepenjual.model.Penjual;

public interface LoginView extends BaseView{
    void updateUI(FirebaseUser user);
    void showError(String message);
    void actionLoginSuccess(Penjual penjual);
}
