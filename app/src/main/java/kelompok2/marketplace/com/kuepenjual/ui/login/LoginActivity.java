package kelompok2.marketplace.com.kuepenjual.ui.login;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

import kelompok2.marketplace.com.kuepenjual.R;
import kelompok2.marketplace.com.kuepenjual.common.UserState;
import kelompok2.marketplace.com.kuepenjual.model.Pembeli;
import kelompok2.marketplace.com.kuepenjual.model.Penjual;
import kelompok2.marketplace.com.kuepenjual.service.NotifikasiService;
import kelompok2.marketplace.com.kuepenjual.ui.PrefManager;
import kelompok2.marketplace.com.kuepenjual.ui.home.HomeActivity;
import kelompok2.marketplace.com.kuepenjual.ui.register.RegisterActivity;
import kelompok2.marketplace.com.kuepenjual.util.FormHelper;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private final String TAG = LoginActivity.class.getSimpleName();
    public final int RC_SIGN_IN = 234;
    private GoogleSignInClient mGoogleSignInClient;
    GoogleSignInOptions gso;
    private LoginPresenter presenter;
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private TextView tvRegister;
    private AlertDialog.Builder progresBar;
    private AlertDialog builder;

    private Button btnPb;
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initComponent();
        if(prefManager.getUserId() != 0){
            UserState.getInstance().setIdUser(prefManager.getUserId());
            UserState.getInstance().setPenjual(prefManager.getObjPenjual());
            lauchHomeActivity();
        }
        else {
            initView();
            initBtnAction();
            findViewById(R.id.default_google_sign_in_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivityForResult(mGoogleSignInClient.getSignInIntent(), RC_SIGN_IN);
                }
            });
        }
    }

    private void initView(){
        etUsername = findViewById(R.id.et_username_login);
        etPassword = findViewById(R.id.et_password_login);
        btnLogin = findViewById(R.id.btn_masuk_login);
        tvRegister = findViewById(R.id.tv_daftar_register);
    }

    private void initComponent(){
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        presenter = new LoginPresenter(this, FirebaseAuth.getInstance(), this);
        prefManager = new PrefManager(getApplicationContext());
    }

    private void initBtnAction(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                if(!email.equals("") && !password.equals("")){

                    if(FormHelper.checkEmail(email)){
                        presenter.checkUser(email,password);
                    }
                    else{
                        showError("Masukkan email sesuai format");
                    }
                }
                else{
                    showError("Isi field yang kosong");
                }
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                presenter.firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Log.d(TAG,e.getMessage());
            }
        }
    }

    @Override
    public void updateUI(FirebaseUser user){
        Toast.makeText(this,user.getEmail().toString(), Toast.LENGTH_SHORT).show();
        if(user != null){
           presenter.checkUserFromEmail(user.getEmail());
        }
        else{
            showError("User tidak ditemukan");
        }
    }
    @Override
    public void showError(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void actionLoginSuccess(Penjual penjual) {
        prefManager.setUser(penjual.getId(), penjual);
        UserState.getInstance().setIdUser(penjual.getId());
        UserState.getInstance().setPenjual(penjual);
        lauchHomeActivity();
    }

    @Override
    public void showLoading() {
        //pb.setVisibility(View.VISIBLE);
        //progresBar.show();
//        View pb = LayoutInflater.from(getApplicationContext()).inflate(R.layout.progress_bar_layout, null);
//        btnPb = pb.findViewById(R.id.btn_pb);
//        progresBar = new AlertDialog.Builder(getApplicationContext());
//        progresBar.setView(pb);
//        builder = progresBar.create();
//        builder.show();
    }

    @Override
    public void hideLoading() {
        //pb.setVisibility(View.GONE);
        //builder.dismiss();
    }

    private void lauchHomeActivity(){
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        finish();
    }

}
