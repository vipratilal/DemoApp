package com.app.demoapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.app.demoapp.R;
import com.app.demoapp.database.DatabaseClient;
import com.app.demoapp.databinding.ActivityRegisterBinding;
import com.app.demoapp.databinding.ActivityRegisterBindingImpl;
import com.app.demoapp.model.User;

import java.util.List;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityRegisterBinding binding;
    boolean removeFirstnameError = false, removeEmailError = false, removePwdError = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);

        binding.buttonRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == binding.buttonRegister) {
            if (isValid()) {

                User user = new User();
                user.setFirstName(binding.edFirstname.getText().toString().trim());
                user.setUserEmail(binding.edEmail.getText().toString().trim());
                user.setUserPassword(binding.edPassword.getText().toString().trim());

                DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .userDao()
                        .insert(user);

                //DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().userDao().insert(user);

                List<User> taskList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .userDao()
                        .getAll();

                for (int i = 0; i < taskList.size(); i++) {
                    Log.e("1",""+ taskList.get(i).getUid());
                    Log.e("2",""+ taskList.get(i).getFirstName());
                    Log.e("3",""+ taskList.get(i).getUserEmail());
                    Log.e("4",""+ taskList.get(i).getUserPassword());
                }

                Log.e("Size",":"+taskList.size());


                /*Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);*/
            }
        }
    }

    private boolean isValid() {

        if (binding.edFirstname.getText().toString().trim().equals("") || binding.edEmail.getText().toString().trim().equals("") || !android.util.Patterns.EMAIL_ADDRESS.matcher(binding.edEmail.getText().toString().trim()).matches() ||
                binding.edPassword.getText().toString().trim().equals("")) {

            if (binding.edFirstname.getText().toString().trim().equals("")) {
                removeFirstnameError = true;
                binding.teFirstname.setErrorEnabled(true);
                binding.teFirstname.setError(getString(R.string.please_enter_firstname));

            } else {
                removeFirstnameError = false;
                if (binding.teFirstname.getError() != null) {
                    binding.teFirstname.setError(null);
                    binding.teFirstname.setErrorEnabled(false);
                }
            }

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
            removeFirstnameError = false;
            if (binding.teFirstname.getError() != null) {
                binding.teFirstname.setError(null);
                binding.teFirstname.setErrorEnabled(false);
            }
            return true;
        }

    }
}