package com.mycodesamples.cordinatorlayoutexample;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton mFab;
    Button mBTNBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFab = findViewById(R.id.fab);
        mBTNBottom = findViewById(R.id.BTNBottom);
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBTNBottom.getVisibility() == View.VISIBLE) {
//                    ((CoordinatorLayout)mBTNBottom.getParent()).removeView(mBTNBottom);
//                    ((CoordinatorLayout)mFab.getParent()).addView(mBTNBottom);
                    findViewById(R.id.BTNBottom).setVisibility(View.GONE);
                } else {
                    findViewById(R.id.BTNBottom).setVisibility(View.VISIBLE);
                }
            }
        });
        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) mFab.getLayoutParams();
        lp.setBehavior(new FloatingActionButtonBehavior(this, null));
    }

    public static class FloatingActionButtonBehavior extends CoordinatorLayout.Behavior<FloatingActionButton> {

        public FloatingActionButtonBehavior(Context context, AttributeSet atts) {
            super(context, atts);
        }

        @Override
        public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
            return dependency instanceof Button;
        }

        @Override
        public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
            float translationy = Math.min(0, dependency.getTranslationY() - dependency.getHeight());
            Log.d("Prashant", " translationy getTranslationY=" + dependency.getTranslationY());
            Log.d("Prashant", " translationy getHeight=" + dependency.getHeight());
            Log.d("Prashant", " translationy=" + translationy);
            child.setTranslationY(translationy);
            return true;
        }

        @Override
        public void onDependentViewRemoved(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
            Log.d("Prashant", " after translationy=" + dependency.getTranslationY());
            child.setTranslationY(dependency.getTranslationY());
        }
    }
}
