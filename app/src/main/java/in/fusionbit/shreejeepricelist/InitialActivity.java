package in.fusionbit.shreejeepricelist;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import in.fusionbit.shreejeepricelist.api.Api;
import in.fusionbit.shreejeepricelist.apimodels.UserModel;
import in.fusionbit.shreejeepricelist.utils.SharedPreferences;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InitialActivity extends BaseActivity {


    @BindView(R.id.et_username)
    TextInputEditText etUsername;
    @BindView(R.id.et_password)
    TextInputEditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.cb_rememberMe)
    CheckBox cbRememberMe;
    @BindView(R.id.textInputLayout)
    TextInputLayout textInputLayout;
    @BindView(R.id.textInputLayout2)
    TextInputLayout textInputLayout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Login");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Map<String, String> savedUsernameAndPassword = new SharedPreferences(this).getSavedUsernameAndPassword();
        if (savedUsernameAndPassword != null) {
            cbRememberMe.setChecked(true);
            etUsername.setText(savedUsernameAndPassword.get("username"));
            etPassword.setText(savedUsernameAndPassword.get("password"));
        }
    }

    @Override
    int getLayoutResourceId() {
        return R.layout.activity_initial;
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {

        final String username = etUsername.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();

        if (username.isEmpty()) {
            etUsername.setError("Username is required");
            etUsername.findFocus();
            return;
        }

        if (password.isEmpty()) {
            etPassword.setError("Password is required");
            etPassword.findFocus();
            return;
        }

        tryLogin(this, username, password);

    }

    private void tryLogin(final Context context, final String username, final String password) {

        showProgressDialog("Please Wait", "Logging in...");

        Api.User.tryLogin(username, password, new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()) {

                    if (response.body() != null) {
                        if (cbRememberMe.isChecked()) {
                            new SharedPreferences(context).saveUsernameAndPassword(username, password);
                        }
                        ProductListActivity.start(context, response.body().getUser().getAdmin_id());
                    }

                } else {
                    Toast.makeText(context, "Something went wrong please try again later", Toast.LENGTH_SHORT).show();
                }
                hideProgressDialog();
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                hideProgressDialog();
            }
        });

    }
}
