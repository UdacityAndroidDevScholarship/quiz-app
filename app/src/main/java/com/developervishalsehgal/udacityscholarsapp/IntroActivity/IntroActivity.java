package com.developervishalsehgal.udacityscholarsapp.IntroActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.developervishalsehgal.udacityscholarsapp.BaseActivity.BaseActivity;
import com.developervishalsehgal.udacityscholarsapp.Extras.Connectivity;
import com.developervishalsehgal.udacityscholarsapp.MainActivity;
import com.developervishalsehgal.udacityscholarsapp.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class IntroActivity extends BaseActivity {

    private ViewPager viewPager;
    private TextView[] dots;
    private LinearLayout dotsLayout;
    private int[] layouts;
    private ViewPagerAdapter viewPagerAdapter;
    Connectivity connection;

    //Google SignIn
    private FirebaseAuth mAuth;
    private final static int RC_SIGN_IN = 1;
    GoogleApiClient mGoogleApiClient;
    FirebaseAuth.AuthStateListener mAuthListener;
    GoogleSignInOptions gso;

    public ProgressBar progressBarr;
    public SignInButton button;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * Setting the Status Bar Transparent.
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setContentView(R.layout.activity_intro);

        circularReveal(R.id.activity_intro);

        viewPager = findViewById(R.id.viewPager);
        dotsLayout = findViewById(R.id.layoutDots);

        layouts = new int[]{R.layout.intro_screen_one, R.layout.intro_screen_two};

        addBottomDots(0);
        changeStatusBarColor();
        viewPagerAdapter = new ViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
//        viewPager.setPageTransformer(true,new ZoomOutTransformer());

//        ParallaxPageTransformer pageTransformer = new ParallaxPageTransformer()
//                .addViewToParallax(new ParallaxPageTransformer.ParallaxTransformInformation(R.id.viewPager, 2, 2))
//                .addViewToParallax(new ParallaxPageTransformer.ParallaxTransformInformation(R.id.img_back_one, 2, 2))
//                .addViewToParallax(new ParallaxPageTransformer.ParallaxTransformInformation(R.id.img_back_two, -0.25f, PARALLAX_EFFECT_DEFAULT)).
//                addViewToParallax(new ParallaxPageTransformer.ParallaxTransformInformation(R.id.title_screen_two, 0.45f, PARALLAX_EFFECT_DEFAULT)).
//                addViewToParallax(new ParallaxPageTransformer.ParallaxTransformInformation(R.id.subtitle_screen_two, 0.65f, PARALLAX_EFFECT_DEFAULT)).
//                        addViewToParallax(new ParallaxPageTransformer.ParallaxTransformInformation(R.id.img_back_three, -0.25f, PARALLAX_EFFECT_DEFAULT)).
//                        addViewToParallax(new ParallaxPageTransformer.ParallaxTransformInformation(R.id.title_screen_three, 0.45f, PARALLAX_EFFECT_DEFAULT)).
//                        addViewToParallax(new ParallaxPageTransformer.ParallaxTransformInformation(R.id.subtitle_screen_three, 0.65f, PARALLAX_EFFECT_DEFAULT)).
//                        addViewToParallax(new ParallaxPageTransformer.ParallaxTransformInformation(R.id.img_back_four, -0.25f, PARALLAX_EFFECT_DEFAULT)).
//                        addViewToParallax(new ParallaxPageTransformer.ParallaxTransformInformation(R.id.title_screen_four, 0.45f, PARALLAX_EFFECT_DEFAULT)).
//                        addViewToParallax(new ParallaxPageTransformer.ParallaxTransformInformation(R.id.subtitle_screen_four, 0.65f, PARALLAX_EFFECT_DEFAULT)).
//                        addViewToParallax(new ParallaxPageTransformer.ParallaxTransformInformation(R.id.img_back_five, -0.65f, PARALLAX_EFFECT_DEFAULT)).
//                        addViewToParallax(new ParallaxPageTransformer.ParallaxTransformInformation(R.id.google_signin_btn, 0.65f, PARALLAX_EFFECT_DEFAULT));
//        viewPager.setPageTransformer(true, pageTransformer);
        viewPager.setOnPageChangeListener(viewListeners);

        connection = new Connectivity(this);

        /**
         * Google SignIn Code.
         */
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    Toast.makeText(IntroActivity.this, user.getDisplayName() + user.getEmail() + user.getPhoneNumber() + "You're Successfully Signed In", Toast.LENGTH_SHORT).show();


                    Intent i = new Intent(IntroActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    setAppLaunch(true);
                    settingUserInfo(user.getDisplayName(), user.getEmail(), user.getPhotoUrl().toString());

                } else {
                    Snackbar.make(viewPager, "Sign-in to continue.", Snackbar.LENGTH_SHORT).show();
                }

            }
        };

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                } /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

    }

    public void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            }
            if (resultCode == RESULT_CANCELED) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(getApplicationContext(), "Cancelled!", Toast.LENGTH_SHORT).show();
                Snackbar.make(viewPager, "Sign-in to continue.", Snackbar.LENGTH_SHORT).show();
            }
        }

    }

    public void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        progressBarr.setVisibility(View.VISIBLE);
        button.setVisibility(View.GONE);


        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            progressBarr.setVisibility(View.GONE);
                            button.setVisibility(View.VISIBLE);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(IntroActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    public void setAppLaunch(boolean b) {
        SharedPreferences sharedPreferences = getSharedPreferences("AppLaunch", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("finish", b);
        editor.commit();
        editor.apply();
    }

    private void addBottomDots(int position) {

        dots = new TextView[layouts.length];
        int[] colorActive = getResources().getIntArray(R.array.dot_active);
        int[] colorInactive = getResources().getIntArray(R.array.dot_inactive);
        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8254"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorInactive[position]);
            dotsLayout.addView(dots[i]);

        }
        if (dots.length > 0) {
            dots[position].setTextColor(colorActive[position]);
        }

    }

    ViewPager.OnPageChangeListener viewListeners = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void changeStatusBarColor() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);

        }
    }

    public class ViewPagerAdapter extends PagerAdapter {

        private LayoutInflater layoutInflater;
        View v;

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            v = layoutInflater.inflate(layouts[position],container,false);
//            container.addView(v);
            v = null;
            switch (position) {
                case 0:
                    v = layoutInflater.inflate(R.layout.intro_screen_one, container, false);
                    break;
                case 1:
                    v = layoutInflater.inflate(R.layout.intro_screen_two, container, false);
                    SignInButton button = (SignInButton) v.findViewById(R.id.google_signin_btn);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            signInClick();
                        }
                    });
                    ProgressBar progressBar = (ProgressBar) v.findViewById(R.id.progressbar_google_signin);
                    IntroActivity.this.progressBarr = progressBar;
                    IntroActivity.this.button = button;
                    ImageView relativeLayout = (ImageView) v.findViewById(R.id.wizard_two_background);
                    Glide.with(getApplicationContext()).load("https://drive.google.com/uc?export=download&id=0B1R9AX00GI_ZQXQ1SmV3djFqNmM").into(relativeLayout);
                    break;

            }
            container.addView(v);

            return v;
        }

        public void signInClick() {
            if (connection.isNetworkAvailable()) {
                signIn();
            } else {
                Snackbar snackbar = Snackbar.make(viewPager, "No internet connection", Snackbar.LENGTH_INDEFINITE).setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (connection.isNetworkAvailable()) {
                            signIn();
                        } else {
                            signInClick();
                        }
                    }
                });
                snackbar.show();
            }
        }


        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View v = (View) object;
            container.removeView(v);
        }

    }

    @Override
    public void onBackPressed() {
        viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
    }

    public void settingUserInfo(String name, String emailId, String imageUrl) {
        SharedPreferences sharedPreferences = getSharedPreferences("AppLaunch", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_name", name);
        editor.putString("email_id", emailId);
        editor.putString("profile_img", imageUrl);
        editor.commit();
        editor.apply();
    }


    public String getUserName() {
        SharedPreferences sharedPreferences = getSharedPreferences("AppLaunch", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("user_name", "Android");
        return name;
    }

    public String getEmailId() {
        SharedPreferences sharedPreferences = getSharedPreferences("AppLaunch", Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("email_id", "abc@gmail.com");
        return email;
    }

    public String getPhotoUrl() {
        SharedPreferences sharedPreferences = getSharedPreferences("AppLaunch", Context.MODE_PRIVATE);
        String photoUrl = sharedPreferences.getString("profile_img", "https://drive.google.com/uc?export=download&id=0B1R9AX00GI_ZemZJQzNSbEVXckE");
        return photoUrl;
    }

}
