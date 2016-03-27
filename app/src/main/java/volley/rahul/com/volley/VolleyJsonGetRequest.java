package volley.rahul.com.volley;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by hadoop on 27/3/16.
 */
public class VolleyJsonGetRequest {

    // Constants
    private final int SUCCESS = 0;
    private final int FAIL = -1;

    private String url;
    private Context context;
    private VolleyJsonGetResponse volleyJsonGetResponse;

    public VolleyJsonGetRequest(Context context, String url){
        this.context = context;
        volleyJsonGetResponse = (VolleyJsonGetResponse)context;
        this.url = url;
    }

    public interface VolleyJsonGetResponse{
        void getResponse(String response, int status);
    }

    public void submitGetRequest(){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        volleyJsonGetResponse.getResponse(response, SUCCESS);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyJsonGetResponse.getResponse(null, FAIL);
            }
        });
        queue.add(stringRequest);
    }


}
