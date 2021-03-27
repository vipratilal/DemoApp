package com.app.demoapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.app.demoapp.R;
import com.app.demoapp.database.DatabaseClient;
import com.app.demoapp.databinding.ActivityLoginBinding;
import com.app.demoapp.model.User;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityLoginBinding binding;
    boolean removeEmailError = false, removePwdError = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);

        binding.buttonSignIn.setOnClickListener(this);

        List<User> userList = DatabaseClient.getInstance(this).getAppDatabase().userDao().getAll();
        if(userList.size()>0)
        {
            Log.e("Size",":" + userList.size());
        }

    }

    private boolean isValid() {

        if (binding.edEmail.getText().toString().trim().equals("") || !android.util.Patterns.EMAIL_ADDRESS.matcher(binding.edEmail.getText().toString().trim()).matches() ||
                binding.edPassword.getText().toString().trim().equals("")) {
            if (binding.edEmail.getText().toString().equals("")) {
                removeEmailError = true;
                binding.teEmail.setErrorEnabled(true);
                binding.teEmail.setError(getString(R.string.please_enter_email_address));
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(binding.edEmail.getText().toString().trim()).matches()) {
                removeEmailError = true;
                binding.teEmail.setErrorEnabled(true);
                binding.teEmail.setError(getString(R.string.please_enter_valid_email_address));
            } else {
                removeEmailError = false;
                if (binding.teEmail.getError() != null) {
                    binding.teEmail.setError(null);
                    binding.teEmail.setErrorEnabled(false);
                }
            }

            if (binding.edPassword.getText().toString().trim().equals("")) {
                removePwdError = true;
                binding.tePassword.setErrorEnabled(true);
                binding.tePassword.setError(getString(R.string.please_enter_password));

            } else {
                removePwdError = false;
                if (binding.tePassword.getError() != null) {
                    binding.tePassword.setError(null);
                    binding.tePassword.setErrorEnabled(false);
                }
            }
            return false;
        } else {

            removeEmailError = false;
            if (binding.teEmail.getError() != null) {
                binding.teEmail.setError(null);
                binding.teEmail.setErrorEnabled(false);
            }
            removePwdError = false;
            if (binding.tePassword.getError() != null) {
                binding.tePassword.setError(null);
                binding.tePassword.setErrorEnabled(false);
            }
            return true;
        }

    }

    @Override
    public void onClick(View view) {
        if(view == binding.buttonSignIn)
        {
            if(isValid())
            {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        }
    }
}