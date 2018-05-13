package com.developervishalsehgal.udacityscholarsapp.ui.notification;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.developervishalsehgal.udacityscholarsapp.R;
import com.developervishalsehgal.udacityscholarsapp.data.models.Notification;
import com.developervishalsehgal.udacityscholarsapp.ui.PresenterInjector;

import java.util.List;

public class NotificationActivity extends AppCompatActivity implements NotificationContract.View {

    private static final int TAB_COUNT = 3;
    private static final int INDEX_NEW_QUIZ = 0;
    private static final int INDEX_DEADLINE_QUIZ = 1;
    private static final int INDEX_RESOURCES = 2;

    private NotificationTabFragment mNewQuizTab;
    private NotificationTabFragment mDeadlineQuizTab;
    private NotificationTabFragment mResourcesTab;

    private NotificationContract.Presenter mPresenter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        initializeTabs();

        // Injecting Presenter here
        PresenterInjector.injectNotificationPresenter(this);

        mPresenter.start(getIntent().getExtras());
    }

    private void initializeTabs() {
        mNewQuizTab = NotificationTabFragment.getInstance(NotificationsAdapter.TYPE_NEW_QUIZ);
        mDeadlineQuizTab = NotificationTabFragment.getInstance(NotificationsAdapter.TYPE_DEADLINE);
        mResourcesTab = NotificationTabFragment.getInstance(NotificationsAdapter.TYPE_RESOURCES);
    }

    @Override
    public void loadNewQuizNotifications(List<Notification> newQuizNotifications) {
        mNewQuizTab.addItems(newQuizNotifications);
    }

    @Override
    public void loadDeadlineNotifications(List<Notification> deadlineNotifications) {
        mDeadlineQuizTab.addItems(deadlineNotifications);
    }

    @Override
    public void loadResourceNotifications(List<Notification> resourceNotifications) {
        mResourcesTab.addItems(resourceNotifications);
    }

    @Override
    public void showError() {
        Toast.makeText(this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showResourcesTab() {
        mViewPager.setCurrentItem(INDEX_RESOURCES);
    }

    @Override
    public void setPresenter(NotificationContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showLoading() {
        // TODO(1): Show loading here
    }

    @Override
    public void hideLoading() {
        // TODO(2): Hide loading here
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case INDEX_NEW_QUIZ:
                    mPresenter.fetchNewQuizNotifications();
                    return mNewQuizTab;
                case INDEX_DEADLINE_QUIZ:
                    mPresenter.fetchDeadlineNotifications();
                    return mDeadlineQuizTab;
                case INDEX_RESOURCES:
                    mPresenter.fetchResources();
                    return mResourcesTab;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return TAB_COUNT;
        }
    }
}
