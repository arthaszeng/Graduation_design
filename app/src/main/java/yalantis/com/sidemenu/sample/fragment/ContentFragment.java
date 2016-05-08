package yalantis.com.sidemenu.sample.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yalantis.com.sidemenu.interfaces.ScreenShotable;
import yalantis.com.sidemenu.sample.R;

public class ContentFragment extends Fragment implements ScreenShotable {
    public static final String CLOSE = "Close";
    public static final String NEWS = "News";
    public static final String COURSE = "Course";
    public static final String CREDITHOUT = "Credithout";
    public static final String EXAMINATION = "Examination";
    public static final String EMPLOYMENT = "Employment";
    public static final String COMMUNITY = "Community";
    public static final String OTHERS = "Others";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_main, container, false);           //设置初始界面
    }

    @Override
    public void takeScreenShot() {

    }

    @Override
    public Bitmap getBitmap() {
        return null;
    }
}

