package com.lambdaschool.swapi;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Trace;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.view.View;
import android.view.Window;

public class MainActivity extends AppCompatActivity implements SwApiListFragment.OnListFragmentInteractionListener, CategoriesFragment.OnCategoryFragmentInteractionListener {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Trace.beginSection("SettingAnimations");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setExitTransition(new Explode());
            getWindow().setEnterTransition(new Explode());
        }
        Trace.endSection();

        setContentView(R.layout.activity_main);
        context = this;

        Trace.beginSection("Adding Fragment");
        Fragment fragment = new CategoriesFragment();
        /*Bundle bundle = new Bundle();
        bundle.putInt(SwApiListFragment.ARG_CATEGORY, SwApiListFragment.CATEGORY_TRANSPORT);
        fragment.setArguments(bundle);*/
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_holder, fragment)
                .commit();
        Trace.endSection();
    }

    @Override
    public void onSwApiObjectListFragmentInteraction(SwApiObject item, View sharedView) {
        if (getResources().getBoolean(R.bool.is_tablet)) {
            // fragment_holder_detail
            final DetailFragment detailFragment = DetailFragment.newInstance(item);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder_detail, detailFragment).commit();
        } else {
            Intent intent = new Intent(context, PhoneDetailActivity.class);
            intent.putExtra(SwApiObject.SWAPI_ITEM_INTENT_TAG, item);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setEnterTransition(new Fade());
                final ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(
                        (Activity) context,
                        sharedView,
                        ViewCompat.getTransitionName(sharedView));
//                        sharedView.getTransitionName());
                startActivity(intent, activityOptions.toBundle());
            } else {
                startActivity(intent);
            }
        }
    }

    @Override
    public void onFragmentInteraction(int category) {
        Fragment fragment = new SwApiListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(SwApiListFragment.ARG_CATEGORY, category);
        fragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_holder, fragment)
                .commit();
    }
}
