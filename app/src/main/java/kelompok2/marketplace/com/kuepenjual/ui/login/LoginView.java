package kelompok2.marketplace.com.kuepenjual.ui.login;

import com.google.firebase.auth.FirebaseUser;

public interface LoginView {
    void updateUI(FirebaseUser user);
    void showError();
    void actionLoginSuccess(Integer id);
}
