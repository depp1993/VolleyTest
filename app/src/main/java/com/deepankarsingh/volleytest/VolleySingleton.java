package com.deepankarsingh.volleytest;

/**
 * Created by Deepankar on 04-11-2015.
 */
import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import utils.LruBitmapCache;

public class VolleySingleton {

    public static final String TAG = VolleySingleton.class
            .getSimpleName();

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static Context mContext;
    private static VolleySingleton mInstance;

    private VolleySingleton(Context context)
    {
        mContext = context;
        mRequestQueue =getRequestQueue();
    }

    public static synchronized VolleySingleton getInstance(Context context) {
        if(mInstance == null)
            mInstance = new VolleySingleton(context);
        return  mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null)
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        return  mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}