package com.example.android.news;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;


import java.util.List;

/**
 * Created by Saksham on 04-01-2018.
 */

public class newsloader extends AsyncTaskLoader<List<word>> {

     private String murl;
    newsloader(Context context, String url)
    {
        super(context);
        murl=url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<word> loadInBackground() {
        if(murl==null)
        {
            return null;
        }
        List<word> result = utils.fetchdata(murl);
        return result;
    }
}
