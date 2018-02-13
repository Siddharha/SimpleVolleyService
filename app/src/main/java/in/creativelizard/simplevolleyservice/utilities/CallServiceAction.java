package in.creativelizard.simplevolleyservice.utilities;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import in.creativelizard.simplevolleyservice.interfaces.NetworkResponseInterface;

/**
 * Created by siddhartha on 13/2/18.
 */

public class CallServiceAction {
    private Context context;
    public NetworkResponseInterface networkResponseInterface;
    private JSONObject errorNodeMain;

    public CallServiceAction(Context context) {
        this.context = context;
    }

    public void requestVersionApi(String apiUrl) {
        RequestQueue queue = Volley.newRequestQueue(context);
        //---------------------------------New Req. -------------------------------//

        //--------------------------------------------------------------------------//
        StringRequest myRequest = new StringRequest(
                Request.Method.POST,
                ConstantClass.BASE_URL+apiUrl,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        networkResponseInterface.onComplete(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            //Toast.makeText(context, "Error in server connection.", Toast.LENGTH_LONG).show();
                            networkResponseInterface.onComplete(error.getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("User-agent", "Custom-Agent 1.0");
                networkResponseInterface.onInitialize();
                return headers;
            }
        };

        myRequest.setRetryPolicy(new DefaultRetryPolicy(30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(myRequest);
    }
}
