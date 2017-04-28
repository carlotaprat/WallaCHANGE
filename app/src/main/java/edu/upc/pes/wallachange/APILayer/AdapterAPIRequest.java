package edu.upc.pes.wallachange.APILayer;

/**
 * Created by sejo on 21/04/17.
 */


import android.util.Log;
import android.util.Pair;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONArray;
import org.json.JSONObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static com.android.volley.VolleyLog.TAG;
import static com.facebook.FacebookSdk.getApplicationContext;

public class AdapterAPIRequest  {



    // GETERS
    public void GETStringRequestAPI(String url, Response.Listener responseListener, Response.ErrorListener errorListener){

        String  REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";

        StringRequest strReq = new StringRequest(url, responseListener, errorListener);
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq, REQUEST_TAG);
    }


    public void GETJsonObjectRequestAPI(String url, Response.Listener responseListener, Response.ErrorListener errorListener){

        String  REQUEST_TAG = "com.androidtutorialpoint.volleyJsonObjectRequest";

        JsonObjectRequest jsonObjectReq = new JsonObjectRequest(url, null, responseListener, errorListener);
        // Adding JsonObject request to request queue
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectReq,REQUEST_TAG);

    }


    public void GETJsonArrayRequestAPI(String url, Response.Listener responseListener, Response.ErrorListener errorListener){

        String  REQUEST_TAG = "com.androidtutorialpoint.volleyJsonArrayRequest";

        JsonArrayRequest jsonArrayReq = new JsonArrayRequest(url, responseListener, errorListener);

        // Adding JsonObject request to request queue
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonArrayReq, REQUEST_TAG);
    }



    public void GETImageLoaderAPI(String url, ImageLoader.ImageListener imageListener){
        ImageLoader imageLoader = AppSingleton.getInstance(getApplicationContext()).getImageLoader();

        imageLoader.get(url, imageListener);
        /*new ImageLoader.ImageListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Image Load Error: " + error.getMessage());
            }

            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                if (response.getBitmap() != null) {

                }
            }

        });*/
    }



    //POST
    public void POSTSJsonObjectRequestAPI(String url, Response.Listener responseListener, Response.ErrorListener errorListener) {
        String  REQUEST_TAG = "com.androidtutorialpoint.volleyPOSTRequest";

        JsonObjectRequest postRequest = new JsonObjectRequest(url, null, responseListener, errorListener) {
            /*@Override

            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                //TODO:Parametrizar
                params.put("name", "Alif");
                params.put("domain", "http://itsalif.info");

                return params;
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<String, String>();
                //TODO:Parametrizar
                headers.put("name", "Alif");
                headers.put("domain", "http://itsalif.info");

                return headers;
            }*/

        };
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(postRequest, REQUEST_TAG);
    }


    public void POSTStringRequestAPI(String url, Response.Listener responseListener, Response.ErrorListener errorListener, final Map<String,String> parametres, final Map<String, String> capceleres) {
        String  REQUEST_TAG = "com.androidtutorialpoint.volleyPOSTRequest";
        StringRequest postRequest = new StringRequest(url, responseListener, errorListener) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = parametres;
                return params;
            }
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = capceleres;
                return headers;
            }
        };
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(postRequest, REQUEST_TAG);
    }

    //PUT
    public void PUTStringRequestAPI(String url, Response.Listener responseListener, Response.ErrorListener errorListener){
        String  REQUEST_TAG = "com.androidtutorialpoint.putRequest";
        StringRequest putRequest = new StringRequest(Request.Method.PUT, url, responseListener, errorListener) {
           /*@Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                //TODO:Parametrizar
                params.put("name", "Alif");
                params.put("domain", "http://itsalif.info");

                return params;
            }
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<String, String>();
                //TODO:Parametrizar
                headers.put("name", "Alif");
                headers.put("domain", "http://itsalif.info");

                return headers;
            }*/
        };
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(putRequest, REQUEST_TAG);
    }

    public void DELETERequestAPI(String url, Response.Listener responseListener, Response.ErrorListener errorListener){
        String  REQUEST_TAG = "com.androidtutorialpoint.Delete";

        StringRequest dr = new StringRequest(Request.Method.DELETE, url, responseListener, errorListener);
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(dr, REQUEST_TAG);
    }



    public void volleyCacheRequest(String url){
        Cache cache = AppSingleton.getInstance(getApplicationContext()).getRequestQueue().getCache();
        Cache.Entry reqEntry = cache.get(url);
        if(reqEntry != null){
            try {
                String data = new String(reqEntry.data, "UTF-8");
                //Handle the Data here.
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        else{

            //Request Not present in cache, launch a network request instead.
        }
    }

    public void volleyInvalidateCache(String url){
        AppSingleton.getInstance(getApplicationContext()).getRequestQueue().getCache().invalidate(url, true);
    }

    public void volleyDeleteCache(String url){
        AppSingleton.getInstance(getApplicationContext()).getRequestQueue().getCache().remove(url);
    }

    public void volleyClearCache(){
        AppSingleton.getInstance(getApplicationContext()).getRequestQueue().getCache().clear();
    }
}


