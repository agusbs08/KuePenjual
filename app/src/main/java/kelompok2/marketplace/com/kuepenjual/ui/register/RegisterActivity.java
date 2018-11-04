package kelompok2.marketplace.com.kuepenjual.ui.register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import kelompok2.marketplace.com.kuepenjual.R;
import kelompok2.marketplace.com.kuepenjual.ui.home.HomeActivity;

public class RegisterActivity extends AppCompatActivity implements RegisterView{

    private EditText etUsername;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etRePassword;
    private EditText etNoHp;
    private Button btnDaftar;
    private ProgressBar pb;
    private RadioGroup radioGroup;
    private EditText etNamaToko;

    private RegisterPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initComponent();
        initActionView();
        hideLoading();
    }

    private void initView(){
        etUsername = findViewById(R.id.et_username_register);
        etPassword = findViewById(R.id.et_password_register);
        etRePassword = findViewById(R.id.et_repassword_register);
        etEmail = findViewById(R.id.et_email_register);
        etNoHp = findViewById(R.id.et_notelp_register);
        btnDaftar = findViewById(R.id.btn_daftar_register);
        pb = findViewById(R.id.pb_register);
        radioGroup = findViewById(R.id.rg_gender_register);
        etNamaToko = findViewById(R.id.et_namatoko_register);
        RadioButton radioGender = findViewById(R.id.rb_lakilaki_register);
        radioGender.setChecked(true);
    }

    private void initComponent(){
        presenter = new RegisterPresenter(this);
    }

    private void initActionView(){
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etUsername.getText().toString();
                String rePassword = etRePassword.getText().toString();
                String email = etEmail.getText().toString();
                String namaToko = etNamaToko.getText().toString();
                String noTlp = etNoHp.getText().toString();
                int idRadio = radioGroup.getCheckedRadioButtonId();
                RadioButton radioGender = findViewById(idRadio);
                String gender = radioGender.getText().toString();
                if(!username.equals("") && !password.equals("") && !rePassword.equals("")
                    && !email.equals("") && !namaToko.equals("") && !noTlp.equals("")
                    && checkEmail(email) && checkPhone(noTlp) && password.equals(rePassword)){

                    presenter.register(username, email, password, noTlp, namaToko, gender);
                }
                else{
                    showError();
                }
            }
        });
    }

    private boolean checkEmail(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean checkPhone(String phone){
        return Patterns.PHONE.matcher(phone).matches();
    }

    @Override
    public void showError() {
        Toast.makeText(this,"Isi Field yang kosong", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void actionRegisterSuccess(Integer id) {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        intent.putExtra("idUser", id);
        startActivity(intent);
        finish();
    }

    @Override
    public void actionRegisterFailed() {
        Toast.makeText(this,"Register Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        pb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pb.setVisibility(View.GONE);
    }
}
