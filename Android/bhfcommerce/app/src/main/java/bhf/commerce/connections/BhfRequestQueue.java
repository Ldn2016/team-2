package bhf.commerce.connections;

/**
 * Created by whdinata on 12/10/16.
 */

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by whdinata on 10/8/16.
 */
public class BhfRequestQueue {
    private static BhfRequestQueue stRequestQueue;
    private static Context context;
    private RequestQueue requestQueue;

    private BhfRequestQueue(Context context){
        this.context = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized BhfRequestQueue getInstance(Context context){
        if(stRequestQueue == null){
            stRequestQueue = new BhfRequestQueue(context);
        }

        return stRequestQueue;
    }

    public RequestQueue getRequestQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }

        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req){
        getRequestQueue().add(req);
    }
}
