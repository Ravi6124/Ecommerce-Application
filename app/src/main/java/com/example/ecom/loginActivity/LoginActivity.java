package com.example.ecom.loginActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ecom.R;
import com.example.ecom.RetrofitClass;
import com.example.ecom.homeActivity.MainActivity;
import com.example.ecom.loginActivity.apiInterface.LoginInterface;
import com.example.ecom.loginActivity.modules.AccessTokenDTO;
import com.example.ecom.loginActivity.modules.LoginResponse;
import com.example.ecom.loginActivity.modules.login.LoginRequest;
import com.example.ecom.loginActivity.modules.signup.SignUpRequest;
import com.example.ecom.loginActivity.modules.signup.SignUpResponse;
import com.example.ecom.loginhistoryActivity.LoginHistoryActivity;
import com.example.ecom.orderHistory.OrderHistoryActivity;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
//import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

//import java.net.Inet4Address;

//import static android.app.PendingIntent.getActivity;

public class LoginActivity extends AppCompatActivity {

    private ImageButton imageButton;
    public GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInOptions gso;
    private int RC_SIGN_IN;
    private Context context;
    private SharedPreferences sharedPreferences;
    private String userName;
    private String password;
    private int loginStatus;
    CallbackManager callbackManager;
    private String userId;
    Retrofit retrofit;
    private Button logHistory;
    private Button orderHistory;

    {
        RC_SIGN_IN = 1;
        context = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //home button on click
        imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        logHistory = findViewById(R.id.login_history);
        logHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, LoginHistoryActivity.class);
                startActivity(intent);
            }
        });

        orderHistory = findViewById(R.id.order_history);
        orderHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, OrderHistoryActivity.class);
                startActivity(intent);
            }
        });


        //check for already logged user
        sharedPreferences = getSharedPreferences("UserInfo",MODE_PRIVATE);
        userId = sharedPreferences.getString("userId","");
        String email = sharedPreferences.getString("email","");
        if(email=="")
        {
            Toast.makeText(context, "null email", Toast.LENGTH_SHORT).show();
            updateUI(false);
        }
        else
            {
                updateUI(true);
                Toast.makeText(context, "already logged in ", Toast.LENGTH_SHORT).show();
                //loginStatus = true;
            }


        //facebook Login
        callbackManager = CallbackManager.Factory.create();

        final String EMAIL = "email";

        LoginButton loginButton = (LoginButton) findViewById(R.id.facebook_login_button);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code loginResult has the access token
                sharedPreferences = getSharedPreferences("UserInfo",MODE_PRIVATE);
                final SharedPreferences.Editor editor = sharedPreferences.edit();
                //editor.putString("email",EMAIL);

                String accessToken = loginResult.getAccessToken().getToken();
                AccessTokenDTO accessTokenDTO = new AccessTokenDTO(accessToken,"customer",userId, "facebook");
                RetrofitClass retrofitLogin = new RetrofitClass();
                Retrofit retrofit = retrofitLogin.getRetrofit();
                LoginInterface loginInterface = retrofit.create(LoginInterface.class);
                Call<LoginResponse> call = loginInterface.facebooklogin(accessTokenDTO);
                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        String userId = response.body().getUserId();
                        editor.putString("userId",userId);
                        editor.putString("email",EMAIL);
                        // TODO: 2020-01-25  do commit here and update UI here only
                        editor.commit();
                        updateUI(true);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        setResult(RESULT_OK,intent);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Authorization not responding", Toast.LENGTH_SHORT).show();
                        //Log.d("Authorization","not responding");
                    }
                });

                //AccessTokenDTO
                //updateUI(true);
                Toast.makeText(LoginActivity.this, "facebook logged in", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                // App code
                Toast.makeText(LoginActivity.this, "login cancelled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Toast.makeText(LoginActivity.this, "facebook exception", Toast.LENGTH_SHORT).show();
            }


        });

        //facebook is logged in

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if(isLoggedIn){
            updateUI(true);
            Toast.makeText(this, "Already logged in via facebook", Toast.LENGTH_SHORT).show();
        }

        //Custom Login
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextEmail =findViewById(R.id.email);
                userName = editTextEmail.getText().toString();
                EditText editTextPass = findViewById(R.id.password);
                password = editTextPass.getText().toString();

                if(userName.equals("")){
                    if(password.equals("")){
                        editTextEmail.requestFocus();
                        editTextPass.requestFocus();
                    }
                    else
                        editTextEmail.requestFocus();
                }
                else if(password.equals("")){
                    editTextPass.requestFocus();
                }
                else {

                    // todo send data to backend for authentication

                    LoginRequest loginRequest = new LoginRequest(userName, password, "customer", userId);
                    RetrofitClass retrofitLogin = new RetrofitClass();
                    retrofit = retrofitLogin.getRetrofit();
                    LoginInterface loginInterface = retrofit.create(LoginInterface.class);
                    Call<LoginResponse> call = loginInterface.customLogin(loginRequest);
                    call.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            //String user = response.body().getUserId();
                            int status = response.body().getStatusCode();
                            if (status == 1000) {
                                SharedPreferences sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("userId", response.body().getUserId());
                                editor.putString("email", response.body().getEmailAddress());
                                editor.commit();
                                Log.d("login", "true");
                                updateUI(true);

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                setResult(RESULT_OK, intent);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "invalid credentials", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {

                        }
                    });
                }
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

                if(userName.equals("")){
                    if(password.equals("")){
                        editTextEmail.requestFocus();
                        editTextPass.requestFocus();
                    }
                    else
                        editTextEmail.requestFocus();
                }
                else if(password.equals("")){
                    editTextPass.requestFocus();
                }else {

                    //send data to backend for registration
                    SignUpRequest request = new SignUpRequest(userName, password, "customer");
                    RetrofitClass retrofitLogin = new RetrofitClass();
                    retrofit = retrofitLogin.getRetrofit();
                    LoginInterface loginInterface = retrofit.create(LoginInterface.class);
                    Call<SignUpResponse> call = loginInterface.register(request);
                    call.enqueue(new Callback<SignUpResponse>() {
                        @Override
                        public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {

                            //Toast.makeText(LoginActivity.this, "", Toast.LENGTH_SHORT).show();
                            int status = response.body().getStatusCode();
                            if (status == 1000) {
                                // updateUI(true);
                                //Data data = response.body().getData();
//                        sharedPreferences = context.getSharedPreferences(
//                                    getString(R.string.preference_file_key_signup), Context.MODE_PRIVATE);
//
//                            SharedPreferences.Editor editor = sharedPreferences.edit();
//                            editor.putString("emailAddress",data.getEmailAddress());
//                            editor.putString("userId",String.valueOf(data.getUserId()));
//                            //editor.commit will write to persistence data and apply will handle in the background
//                            editor.apply();
                                //String message = response.body().getMessage();
                                Toast.makeText(LoginActivity.this, "Registration Success : You can login now", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(LoginActivity.this, "Registration Failed : Try other methods", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<SignUpResponse> call, Throwable t) {
                            Log.d("SignUp Service", "Callback failure");
                        }
                    });
                }
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

        findViewById(R.id.sign_out_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
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
        sharedPreferences = getSharedPreferences("UserInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        sharedPreferences = getSharedPreferences("merchant_product_info",MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedPreferences.edit();
        editor1.clear();
        editor1.commit();

        sharedPreferences = getSharedPreferences("productInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sharedPreferences.edit();
        editor2.clear();
        editor2.commit();
        updateUI(false);
        //setResult(RESULT_OK,);

       // LoginManager.getInstance().logOut();
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

        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            String userName = account.getDisplayName();
            sharedPreferences = getSharedPreferences("UserInfo",MODE_PRIVATE);
            final SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("email",userName);
            //editor.commit();
            // TODO: 2020-01-22 send the token to backend
            String token = account.getIdToken();
            AccessTokenDTO accessTokenDTO = new AccessTokenDTO(account.getIdToken(),"customer",userId,"google");
            RetrofitClass retrofitLogin = new RetrofitClass();
            Retrofit retrofit = retrofitLogin.getRetrofit();
            LoginInterface loginInterface = retrofit.create(LoginInterface.class);
            Call<LoginResponse> call = loginInterface.googleLogIn(accessTokenDTO);

            //progress Dialog
//            final ProgressDialog progressDialog;
//            progressDialog = new ProgressDialog(LoginActivity.this);
//            progressDialog.setMax(100);
//            progressDialog.setMessage("Its loading....");
//            progressDialog.setTitle("ProgressDialog bar example");
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            // show it
            //progressDialog.show();

            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                   // progressDoalog.dismiss();

                    loginStatus = response.body().getStatusCode();
                    String userId = response.body().getUserId();
                    editor.putString("userId",userId);
                    // TODO: 2020-01-25  do commit here and update UI here only
                    editor.commit();
                    updateUI(true);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    setResult(RESULT_OK,intent);
                    startActivity(intent);
                    finish();
                   // setResult(RESULT_OK,);
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                  //  progressDoalog.dismiss();
                    Log.d("Authorization","not responding");
                }
            });
            //editor.commit();
            // Signed in successfully, show authenticated UI.
            //updateUI(true);
            Log.d("loginStatus",String.valueOf(loginStatus));
            Log.d("token",token);

//            sharedPreferences = context.getSharedPreferences(
//                    getString(R.string.preference_file_key_login), Context.MODE_PRIVATE);
//
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString("name",account.getDisplayName());
            //editor.commit will write to persistence data and apply will handle in the background
//            editor.apply();                   s


//            ((Activity)context).finish();
//            context.startActivity(new Intent(context, MainActivity.class));

//            Intent refresh = new Intent(this, MainActivity.class); //inboxlist is activity which list the read and unread messages
//            startActivity(refresh);
//            this.finish();



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
            findViewById(R.id.facebook_login_button).setVisibility(View.GONE);
            findViewById(R.id.login_history).setVisibility(View.VISIBLE);
            findViewById(R.id.order_history).setVisibility(View.VISIBLE);
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
            findViewById(R.id.facebook_login_button).setVisibility(View.VISIBLE);

            findViewById(R.id.login_history).setVisibility(View.GONE);
            findViewById(R.id.order_history).setVisibility(View.GONE);

            //findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);
        }
    }
}
