package volley.rahul.com.volley;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class VolleyJsonPostRequest {

    // Constants
    private final int SUCCESS = 0;
    private final int FAIL = -1;
    private final String VOLLEY_POST_ERROR = "Valley Post Error";

    private String url;
    private Context context;
    private VolleyJsonPostResponse volleyJsonPostResponse;
    private HashMap<String, String> postParams = null;

    public VolleyJsonPostRequest(Context context, String url) {
        this.context = context;
        volleyJsonPostResponse = (VolleyJsonPostResponse) context;
        this.url = url;
    }

    public interface VolleyJsonPostResponse {
        void postResponse(String response, int status);
    }

    public void setParam(String key, String value) {
        if(postParams == null){
            postParams = new HashMap<String,String>();
        }
        postParams.put(key, value);
    }

    public void setParams(HashMap<String, String> params) {
        postParams = params;
    }

    public void submitPostRequest() {
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest req = new JsonObjectRequest(url, new JSONObject(postParams),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            volleyJsonPostResponse.postResponse(response.toString(), SUCCESS);
                        } catch (Exception e) {
                            volleyJsonPostResponse.postResponse(null, FAIL);
                            //Log.e(VOLLEY_POST_ERROR, "Error converting Json response to string!");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyJsonPostResponse.postResponse(null, FAIL);
                //Log.e("Error: ", error.getMessage().toString());
            }
        });
        queue.add(req);
    }
}
