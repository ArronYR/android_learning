package com.helloarron.slidemenuqq50;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.helloarron.slidemenuqq50.view.SlideMenu;

public class MainActivity extends Activity {

    private SlideMenu mLeftMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        mLeftMenu = (SlideMenu) findViewById(R.id.id_menu);
    }

    public void toggleMenu(View view) {
        mLeftMenu.toggle();
    }
}
