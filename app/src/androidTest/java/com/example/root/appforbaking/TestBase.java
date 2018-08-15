package com.example.root.appforbaking;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

public abstract class TestBase {
    protected App globalApplication;
    protected IdlingResource mIdlingResource;

    @Rule
    public ActivityTestRule<ActivityMain> activityTestRule = new ActivityTestRule<>(ActivityMain.class);

    @Before
    public void registerIdlingResource() {
        globalApplication = (App) activityTestRule.getActivity().getApplicationContext();
        mIdlingResource = globalApplication.getIdlingResource();
        // Register Idling Resources
        IdlingRegistry.getInstance().register(mIdlingResource);
    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }
    }
}
