package com.deepankarsingh.volleytest;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView response1 = (TextView)findViewById(R.id.repsponse1);
        final TextView response2 = (TextView)findViewById(R.id.repsponse2);
        final TextView response3 = (TextView) findViewById(R.id.repsponse3);
        final ImageView mImageView = (ImageView) findViewById(R.id.image);
        String tag_json_obj = "json_obj_req";
        String tag_json_arry = "json_array_req";
        String  tag_string_req = "string_req";

        // ******************JSON OBJECT REQUEST************************ //
        String url1 = "http://api.androidhive.info/volley/person_object.json";
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url1, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(VolleySingleton.TAG, response.toString());
                        response1.setText(response.toString());
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(VolleySingleton.TAG, "Error: " + error.getMessage());
            }
        });

        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjReq, tag_json_obj);

        // ******************JSON ARRAY REQUEST************************ //
        String url2 = "http://api.androidhive.info/volley/person_array.json";
        JsonArrayRequest req = new JsonArrayRequest(url2,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(VolleySingleton.TAG, response.toString());
                        response2.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(VolleySingleton.TAG, "Error: " + error.getMessage());
            }
        });

        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(req, tag_json_arry);

        // ******************STRING REQUEST************************ //
        String url3 = "http://teamdevops.github.io/fifth/post/2015/08/22/next-sem-project-breakage.html";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url3, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                response3.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                VolleyLog.d(VolleySingleton.TAG, "Error: " + volleyError.getMessage());
            }
        });

        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest,tag_string_req);


        // ******************IMAGE REQUEST************************ //


        ImageLoader imageLoader =VolleySingleton.getInstance(getApplicationContext()).getImageLoader();
        String imageURL = "http://s.quickmeme.com/img/cc/cc5adfb589672199f10f40d498d225de1292a2348976ea60938dee586109af97.jpg";
        ImageRequest imgRequest = new ImageRequest(imageURL, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                mImageView.setImageBitmap(bitmap);
            }
        }, 0, 0, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                mImageView.setImageResource(R.drawable.abc_popup_background_mtrl_mult);
            }
        });
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(imgRequest);
    }
}
