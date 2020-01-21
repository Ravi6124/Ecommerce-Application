package com.example.ecommerceapplication.loginActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ecommerceapplication.R;
import com.example.ecommerceapplication.homeActivity.MainActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
//import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

//import java.net.Inet4Address;

//import static android.app.PendingIntent.getActivity;

public class LoginActivity extends AppCompatActivity {

    public GoogleSignInClient mGoogleSignInClient;
    private int RC_SIGN_IN;
    private Context context;
    private SharedPreferences sharedPreferences;
    private String userName;
    private String password;

    {
        RC_SIGN_IN = 1;
        context = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Custom Login
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText =findViewById(R.id.email);
                userName = editText.getText().toString();
                editText = findViewById(R.id.password);
                password = editText.getText().toString();

                //Using shared Preference to store login information of user
                sharedPreferences = context.getSharedPreferences(
                        getString(R.string.preference_file_key), Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("userName",userName);
                editor.putString("password",password);
                //editor.commit will write to persistence data and apply will handle in the background
                editor.apply();
            }
        });



        //Google OAuth
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

//        SignInButton signInButton = findViewById(R.id.sign_in_button);
//        signInButton.setSize(SignInButton.SIZE_STANDARD);

        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.sign_in_button){
                    signIn();
                }
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(null != account){
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            account.getIdToken();
            // Signed in successfully, show authenticated UI.
            //updateUI(account);
  //          TextView textView =  findViewById(R.id.user_info);
//            textView.setText(account.getDisplayName());
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Sign_In_Result", "signInResult:failed code=" + e.getStatusCode());
           // updateUI(null);
        }
    }
}
