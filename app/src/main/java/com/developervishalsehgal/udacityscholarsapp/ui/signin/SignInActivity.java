package com.developervishalsehgal.udacityscholarsapp.ui.signin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.developervishalsehgal.udacityscholarsapp.R;
import com.developervishalsehgal.udacityscholarsapp.ui.PresenterInjector;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class SignInActivity extends AppCompatActivity implements SignInContract.View {

    private SignInContract.Presenter mPresenter;

    private static final int RC_SIGN_IN = 101;

    private GoogleSignInClient mGoogleSignInClient;

    private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            // User has already signed in, navigate to home
            navigateToHome();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions mGso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.google_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, mGso);

        PresenterInjector.injectSignInPresenter(this);

        findViewById(R.id.google_signin_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.handleLoginRequest();
            }
        });
    }

    @Override
    public void loginSuccess() {
        // TODO Login has succeeded, notify user and navigate to home activity
    }

    @Override
    public void loginFailure(int statusCode, String message) {
        // TODO Login failed, notify user
    }

    @Override
    public void setPresenter(SignInContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showLoading() {
        // TODO show progress bar / dialog here
        Toast.makeText(this, getString(R.string.text_please_wait), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
        // TODO hide progress bar / dialog here
    }

    @Override
    public void startSignIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void navigateToHome() {
        // TODO - Intent homeIntent = new Intent(this, Home)
    }

    @Override
    public void navigateToProfile() {
        // TODO - First time sign in navigate to profile
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.mPresenter.destroy();
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            firebaseAuthWithGoogle(account);

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            e.printStackTrace();
            mPresenter.handleLoginFailure(e.getStatusCode(), e.getMessage());
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                mPresenter.handleLoginSuccess(user.getEmail(), user.getDisplayName()
                                        , user.getPhotoUrl());
                            } else {
                                mPresenter.handleLoginFailure(0, getString(R.string.unable_to_login));
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            mPresenter.handleLoginFailure(0, getString(R.string.something_went_wrong));
                        }
                    }
                });

    }
}
