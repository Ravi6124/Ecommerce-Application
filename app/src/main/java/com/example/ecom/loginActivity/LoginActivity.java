package com.example.ecom.loginActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecom.R;
import com.example.ecom.RetrofitLogin;
import com.example.ecom.homeActivity.MainActivity;
import com.example.ecom.loginActivity.apiInterface.LoginInterface;
import com.example.ecom.loginActivity.modules.AccessTokenDTO;
import com.example.ecom.loginActivity.modules.LoginResponse;
import com.example.ecom.loginActivity.modules.signup.Data;
import com.example.ecom.loginActivity.modules.signup.SignUpRequest;
import com.example.ecom.loginActivity.modules.signup.SignUpResponse;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
//import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

//import java.net.Inet4Address;

//import static android.app.PendingIntent.getActivity;

public class LoginActivity extends AppCompatActivity {

    public GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInOptions gso;
    private int RC_SIGN_IN;
    private Context context;
    private SharedPreferences sharedPreferences;
    private String userName;
    private String password;
    private int loginStatus;

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
                EditText editTextEmail =findViewById(R.id.email);
                userName = editTextEmail.getText().toString();
                EditText editTextPass = findViewById(R.id.password);
                password = editTextPass.getText().toString();

                // todo send data to backend for authentication

                //Using shared Preference to store login information of user
                //context = getApplicationContext();
                sharedPreferences = context.getSharedPreferences(
                        getString(R.string.preference_file_key), Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("userName",userName);
                editor.putString("password",password);
                //editor.commit will write to persistence data and apply will handle in the background
                editor.apply();
            }
        });

        //Custom SignUp
        findViewById(R.id.signUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextEmail =findViewById(R.id.email);
                userName = editTextEmail.getText().toString();
                EditText editTextPass = findViewById(R.id.password);
                password = editTextPass.getText().toString();

                //send data to backend for registration
                SignUpRequest request = new SignUpRequest(userName,password,"customer");
                RetrofitLogin retrofitLogin = new RetrofitLogin();
                Retrofit retrofit = retrofitLogin.getRetrofit();
                LoginInterface loginInterface = retrofit.create(LoginInterface.class);
                Call<SignUpResponse> call = loginInterface.register(request);
                call.enqueue(new Callback<SignUpResponse>() {
                    @Override
                    public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {

                        int status = response.body().getStatusCode();
                        if(status == 1000){
                            updateUI(true);
                        Data data = response.body().getData();
                        sharedPreferences = context.getSharedPreferences(
                                    getString(R.string.preference_file_key_signup), Context.MODE_PRIVATE);

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("emailAddress",data.getEmailAddress());
                            editor.putString("userId",String.valueOf(data.getUserId()));
                            //editor.commit will write to persistence data and apply will handle in the background
                            editor.apply();
                            //String message = response.body().getMessage();
                            Toast.makeText(LoginActivity.this, "Registration Success", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Registration Failed : Try other methods", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<SignUpResponse> call, Throwable t) {
                        Log.d("SignUp Service","Callback failure");
                    }
                });
            }
        });



        //Google OAuth
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .requestIdToken("82806335202-ij3bqro9rstgi5ad5el56n5tmrovlaiv.apps.googleusercontent.com")
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
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
            updateUI(true);
            Toast.makeText(context, "Already singed in", Toast.LENGTH_LONG).show();
//        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
//        startActivity(intent);
        }
        else {
            updateUI(false);
        }

        findViewById(R.id.sign_out_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUI(false);
                signOut();
//                SharedPreferences sharedPreferences = context.getSharedPreferences("sign_out",Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("name","Guest");
//                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
//                startActivity(intent);
                //finish();
            }
        });

        findViewById(R.id.imageButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(context, "Signed Out", Toast.LENGTH_SHORT).show();
                    }
                });
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
            // TODO: 2020-01-22 send the token to backend
            String token = account.getIdToken();
            AccessTokenDTO accessTokenDTO = new AccessTokenDTO(account.getIdToken());
            RetrofitLogin retrofitLogin = new RetrofitLogin();
            Retrofit retrofit = retrofitLogin.getRetrofit();
            LoginInterface loginInterface = retrofit.create(LoginInterface.class);
            Call<LoginResponse> call = loginInterface.googleLogIn(accessTokenDTO);

            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    loginStatus = response.body().getStatusCode();
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Log.d("Authorization","not responding");
                }
            });

            // Signed in successfully, show authenticated UI.
            Log.d("loginStatus",String.valueOf(loginStatus));
            Log.d("token",token);
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//            sharedPreferences = context.getSharedPreferences(
//                    getString(R.string.preference_file_key_login), Context.MODE_PRIVATE);
//
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString("name",account.getDisplayName());
            //editor.commit will write to persistence data and apply will handle in the background
//            editor.apply();
            startActivity(intent);
            finish();

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Sign_In_Result", "signInResult:failed code=" + e.getStatusCode());
            updateUI(false);
        }
    }

    private void updateUI(boolean signedIn) {
        if (signedIn) {
            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
            findViewById(R.id.login).setVisibility(View.GONE);
            findViewById(R.id.signUp).setVisibility(View.GONE);
            findViewById(R.id.sign_out_button).setVisibility(View.VISIBLE);
            findViewById(R.id.textView).setVisibility(View.GONE);
            findViewById(R.id.textView2).setVisibility(View.GONE);
            findViewById(R.id.email).setVisibility(View.GONE);
            findViewById(R.id.password).setVisibility(View.GONE);
            //findViewById(R.id.sign_out_and_disconnect).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            findViewById(R.id.login).setVisibility(View.VISIBLE);
            findViewById(R.id.signUp).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_out_button).setVisibility(View.GONE);

            findViewById(R.id.textView).setVisibility(View.VISIBLE);
            findViewById(R.id.textView2).setVisibility(View.VISIBLE);
            findViewById(R.id.email).setVisibility(View.VISIBLE);
            findViewById(R.id.password).setVisibility(View.VISIBLE);

            //findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);
        }
    }
}
