package com.nowwego.hakwonga.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nowwego.hakwonga.R;

/**
 * Login screen
 */
public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private Button signupStudentButton;
    private Button signupParentButton;
    private Button forgotPasswordButton;
    private EditText email;
    private EditText password;

    /**
     * Creates a starting intent to start this activity
     * @param context Parent context
     * @return Starting intent
     */
    public static Intent getStartIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindViews();
        initViews();
    }

    private void bindViews() {
        loginButton = findViewById(R.id.button_login);
        signupStudentButton = findViewById(R.id.button_signup_student);
        signupParentButton = findViewById(R.id.button_signup_parent);
        forgotPasswordButton = findViewById(R.id.button_forgot_password);
        email = findViewById(R.id.editText_email_address);
        password = findViewById(R.id.editText_password);
    }

    private void initViews() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: temporary code, remove later
                startActivity(MainActivity.getStartIntent(getApplicationContext()));
                finish();
            }
        });
    }
}
