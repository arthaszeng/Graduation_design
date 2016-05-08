package yalantis.com.sidemenu.sample;

import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;
import yalantis.com.sidemenu.interfaces.Resourceable;
import yalantis.com.sidemenu.interfaces.ScreenShotable;
import yalantis.com.sidemenu.model.SlideMenuItem;
import yalantis.com.sidemenu.sample.fragment.CommunityFragment;
import yalantis.com.sidemenu.sample.fragment.ContentFragment;
import yalantis.com.sidemenu.sample.fragment.CourseFragment;
import yalantis.com.sidemenu.sample.fragment.CredithoutFragment;
import yalantis.com.sidemenu.sample.fragment.EmploymentFragment;
import yalantis.com.sidemenu.sample.fragment.ExaminationFragment;
import yalantis.com.sidemenu.sample.fragment.OthersFragment;
import yalantis.com.sidemenu.sample.fragment.newsFragment;
import yalantis.com.sidemenu.util.ViewAnimator;

public class MainActivity extends ActionBarActivity implements ViewAnimator.ViewAnimatorListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private List<SlideMenuItem> list = new ArrayList<>();
    private List<WordMenuItem> wordList = new ArrayList<>();
    private ViewAnimator viewAnimator;
    private LinearLayout left_drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ContentFragment contentFragment = new ContentFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new ContentFragment())
                .commit();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        left_drawer = (LinearLayout) findViewById(R.id.left_drawer);
        left_drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });
        setActionBar();
        createMenuList();

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window window = getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//
//        }

        viewAnimator = new ViewAnimator<>(this, list, wordList, contentFragment, drawerLayout, this);
    }




    private void createMenuList() {
        SlideMenuItem menuItem0 = new SlideMenuItem(ContentFragment.CLOSE, R.drawable.icn_close);
        list.add(menuItem0);
        SlideMenuItem menuItem = new SlideMenuItem(ContentFragment.COMMUNITY, R.drawable.icn_1);
        list.add(menuItem);
        SlideMenuItem menuItem2 = new SlideMenuItem(ContentFragment.COURSE, R.drawable.icn_2);
        list.add(menuItem2);
        SlideMenuItem menuItem3 = new SlideMenuItem(ContentFragment.CREDITHOUT, R.drawable.icn_3);
        list.add(menuItem3);
        SlideMenuItem menuItem4 = new SlideMenuItem(ContentFragment.EMPLOYMENT, R.drawable.icn_4);
        list.add(menuItem4);
        SlideMenuItem menuItem5 = new SlideMenuItem(ContentFragment.EXAMINATION, R.drawable.icn_5);
        list.add(menuItem5);
        SlideMenuItem menuItem6 = new SlideMenuItem(ContentFragment.NEWS, R.drawable.icn_6);
        list.add(menuItem6);
        SlideMenuItem menuItem7 = new SlideMenuItem(ContentFragment.OTHERS, R.drawable.icn_7);
        list.add(menuItem7);
        WordMenuItem wordItem0 = new WordMenuItem(ContentFragment.CLOSE, R.string.closs);
        wordList.add(wordItem0);
        WordMenuItem wordItem1 = new WordMenuItem(ContentFragment.COMMUNITY, R.string.classroom);
        wordList.add(wordItem1);
        WordMenuItem wordItem2 = new WordMenuItem(ContentFragment.COURSE, R.string.courseTable);
        wordList.add(wordItem2);
        WordMenuItem wordItem3 = new WordMenuItem(ContentFragment.CREDITHOUT, R.string.credithour);
        wordList.add(wordItem3);
        WordMenuItem wordItem4 = new WordMenuItem(ContentFragment.EMPLOYMENT, R.string.employment);
        wordList.add(wordItem4);
        WordMenuItem wordItem5 = new WordMenuItem(ContentFragment.EXAMINATION, R.string.examination);
        wordList.add(wordItem5);
        WordMenuItem wordItem6 = new WordMenuItem(ContentFragment.NEWS, R.string.swpuNews);
        wordList.add(wordItem6);
        WordMenuItem wordItem7 = new WordMenuItem(ContentFragment.OTHERS, R.string.others);
        wordList.add(wordItem7);
    }


    private void setActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                toolbar,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                left_drawer.removeAllViews();
                left_drawer.invalidate();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if (slideOffset > 0.6 && left_drawer.getChildCount() == 0)
                    viewAnimator.showMenuContent();
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public ScreenShotable onSwitch(Resourceable slideMenuItem, ScreenShotable screenShotable, int position) {
        switch (slideMenuItem.getName()) {
            case ContentFragment.CLOSE:
                return screenShotable;
            default:
                return replaceFragment(slideMenuItem, screenShotable, position);
        }
    }

    @Override
    public void disableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(false);
    }

    @Override
    public void enableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerLayout.closeDrawers();
    }

    @Override
    public void addViewToContainer(View view) {
        left_drawer.addView(view);
    }


    private ScreenShotable replaceFragment(Resourceable slideMenuItem, ScreenShotable screenShotable, int topPosition) {
        ScreenShotable fragment;
        View view = findViewById(R.id.content_frame);
        int finalRadius = Math.max(view.getWidth(), view.getHeight());
        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0, finalRadius);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);
        findViewById(R.id.content_overlay).setBackgroundDrawable(new BitmapDrawable(getResources(), screenShotable.getBitmap()));
        animator.start();
        switch (slideMenuItem.getName()){
            case ContentFragment.NEWS:
                fragment = new newsFragment();
                break;
            case ContentFragment.COMMUNITY:
                fragment = new CommunityFragment();
                break;
            case ContentFragment.COURSE:
                fragment = new CourseFragment();
                break;
            case ContentFragment.CREDITHOUT:
                fragment = new CredithoutFragment();
                break;
            case ContentFragment.EMPLOYMENT:
                fragment = new EmploymentFragment();
                break;
            case ContentFragment.EXAMINATION:
                fragment = new ExaminationFragment();
                break;
            case ContentFragment.OTHERS:
                fragment = new OthersFragment();
                break;
            default:
                fragment = new newsFragment();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,(Fragment)fragment).commit();
        return fragment;
    }

}
