package com.example.acer.iicpmobile;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.acer.iicpmobile.database.IICPBaseHelper;
import com.example.acer.iicpmobile.database.IICPDataBaseAdapter;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText txtUsername;
    EditText txtPassword;
    IICPDataBaseAdapter mIICPDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setEnabled(false); // Disable Login Button
        btnLogin.setBackgroundColor(getResources().getColor(R.color.iicpDarkGrey));
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtUsername.addTextChangedListener(new LoginTextWatcher());

        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtPassword.addTextChangedListener(new LoginTextWatcher());
    }

    // Custom TextWatcher - For Multiple EditText Usage
    private class LoginTextWatcher implements TextWatcher{
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        public void afterTextChanged(Editable s) {
            if (txtUsername.getText().toString().isEmpty() || txtPassword.getText().toString().isEmpty()) {
                btnLogin.setEnabled(false);
                btnLogin.setBackgroundColor(getResources().getColor(R.color.iicpDarkGrey));
            } else {
                btnLogin.setEnabled(true);
                btnLogin.setBackgroundColor(getResources().getColor(R.color.iicpRed));
            }
        }
    }
    private void login() {
        final ProgressDialog mProgressDialog = new ProgressDialog(LoginActivity.this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Authenticating...");
        mProgressDialog.show();

        mIICPDataBaseAdapter = new IICPDataBaseAdapter(this);
        mIICPDataBaseAdapter = mIICPDataBaseAdapter.Open();
        String studentPassword = mIICPDataBaseAdapter.SelectOne("student", "password", " student_id=?", new String[]{txtUsername.getText().toString()});
        mIICPDataBaseAdapter.Close();

        if(studentPassword.isEmpty() || !txtPassword.getText().toString().equals(studentPassword)) {
            Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
            mProgressDialog.dismiss();
            return;
        }
        else SaveSharedPreference.setStudentId(this, txtUsername.getText().toString());

        new android.os.Handler().postDelayed(new Runnable() {
             public void run() {
                 finish();
                 mProgressDialog.dismiss();
             }
         }, 1500); // Delay 1.5 seconds for dialog
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true); // disable going back to the MainActivity
    }
}
