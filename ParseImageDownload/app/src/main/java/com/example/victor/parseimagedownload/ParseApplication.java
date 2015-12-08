package com.example.victor.parseimagedownload;

/**
 * Created by victor on 12/8/15.
 */
import com.parse.Parse;
import com.parse.ParseACL;

import com.parse.ParseUser;

import android.app.Application;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Add your initialization code here
        Parse.initialize(this, "yQVkr1EyPiHlf6ssCl6c6Zp6qqI4sPQRswM8owSE", "0lrCxBl4efiPFHCxH1V7TDhXK77U9pZYjkJgj4px");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
    }

}