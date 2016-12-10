package bhf.commerce.connections;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import bhf.commerce.callbacks.OnListItemFetched;
import bhf.commerce.models.Item;

/**
 * Created by whdinata on 12/10/16.
 */
public class API {
    private static final String TAG = API.class.getName();
    public static final String HOST = "http://77e35035.ngrok.io";
    private static final String STORE_LISTING = "/api/store";
    private static final String QUEUE = "/api/queue";
    private static final String UPLOADS = "/api/uploads";
    private static final String SHOP = "/management/shop";
    private static ProgressDialog dialog;
    private static Activity context;

    public static void init(Activity ctx){
        context = ctx;
        dialog = new ProgressDialog(context);
        dialog.setTitle("Fetching Data");
        dialog.setMessage("Please wait...");
    }

    public static void uploadItem(final Item item){
        dialog.show();
        StringRequest request = new StringRequest(Request.Method.POST, HOST + QUEUE, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                dialog.dismiss();
                Log.d(TAG, "Response: " + response);
                context.finish();
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                item.getBitmap().compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream .toByteArray();
                String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);


                params.put("title", item.getTitle());
                params.put("description", item.getDescription());
                params.put("suggestedPrice", item.getSuggestedPrice());
                params.put("file", encoded);

                return params;
            }
        };

        BhfRequestQueue.getInstance(context).addToRequestQueue(request);
    }

    public static void getListItems(final OnListItemFetched callback){
        //dialog.show();
        String url = HOST + UPLOADS;
        Log.d(TAG, "Endpoint: " + url);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("items");
                    Item[] items = new Item[jsonArray.length()];

                    for(int i = 0; i < items.length; i++){
                        items[i] = new Item(jsonArray.getJSONObject(i));
                    }

                    callback.onItemFetched(items);
                    Log.d(TAG, "Response: " + response);
                } catch (JSONException e){
                    e.printStackTrace();
                }

//                dialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Error: " + error.toString());
                error.printStackTrace();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");

                return params;
            }
        };

        BhfRequestQueue.getInstance(context).addToRequestQueue(request);
    }
}
