package com.jakester.weekthreelab.application;

import android.app.Application;

import com.jakester.weekthreelab.model.Message;
import com.jakester.weekthreelab.util.LabConstants;
import com.parse.Parse;
import com.parse.ParseLiveQueryClient;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SubscriptionHandling;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import static com.jakester.weekthreelab.R.id.message;
import static com.jakester.weekthreelab.R.id.rvChat;

/**
 * Created by Jake on 9/27/2017.
 */

public class ChatApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Message.class);

        // Use for monitoring Parse network traffic
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        // Can be Level.BASIC, Level.HEADERS, or Level.BODY
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.networkInterceptors().add(httpLoggingInterceptor);

        // set applicationId and server based on the values in the Heroku settings.
        // any network interceptors must be added with the Configuration Builder given this syntax
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(LabConstants.APPLICATION_ID) // should correspond to APP_ID env variable
                .clientBuilder(builder)
                .server(LabConstants.SERVER_STRING).build());


    }

}
