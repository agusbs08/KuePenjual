package kelompok2.marketplace.com.kuepenjual.ui.login;

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

public class LoginPresenter {
    private LoginView view;
    private FirebaseAuth mAuth;
    private AppCompatActivity activity;
   // private Call<ModelResponse<Pembeli>> result;

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
                           // view.showError();
                        }
                    }
                });
    }

}
