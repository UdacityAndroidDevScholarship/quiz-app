package com.developervishalsehgal.udacityscholarsapp.ui.signin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.developervishalsehgal.udacityscholarsapp.R;
import com.developervishalsehgal.udacityscholarsapp.ui.PresenterInjector;
import com.developervishalsehgal.udacityscholarsapp.ui.home.HomeActivity;
import com.developervishalsehgal.udacityscholarsapp.ui.profile.UserProfileActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class SignInActivity extends AppCompatActivity implements SignInContract.View {

    private SignInContract.Presenter mPresenter;

    private static final int RC_SIGN_IN = 101;

    private GoogleSignInClient mGoogleSignInClient;

    private FirebaseAuth mAuth;

    private Bundle extras;

    private ProgressBar mProgressBar;

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

        mProgressBar = findViewById(R.id.pb_signin);

        PresenterInjector.injectSignInPresenter(this);

        findViewById(R.id.google_signin_btn).setOnClickListener(v -> mPresenter.handleLoginRequest());

        extras = getIntent().getExtras();
        mPresenter.start(extras);
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(this, getString(R.string.msg_login_successful), Toast.LENGTH_SHORT).show();
        navigateToProfile();
    }

    @Override
    public void loginFailure(int statusCode, String message) {
        Toast.makeText(this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(SignInContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showLoading() {
        Toast.makeText(this, getString(R.string.text_please_wait), Toast.LENGTH_SHORT).show();
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void startSignIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void navigateToHome() {
        Intent homeIntent = new Intent(this, HomeActivity.class);
        if (extras != null) {
            homeIntent.putExtras(extras);
        }
        startActivity(homeIntent);
        this.finish();
    }

    @Override
    public void navigateToProfile() {
        Intent signInIntent = new Intent(this, UserProfileActivity.class);
        startActivity(signInIntent);
        this.finish();
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
                task -> {
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
                });

    }
}
