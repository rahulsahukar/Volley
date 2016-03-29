package volley.rahul.com.volleytest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.io.File;

import volley.rahul.com.volley.VolleyUploadFileRequest;

/**
 * Created by rsahukar on 3/28/2016.
 */
public class ThirdActivity extends Activity {

    private Button button;
    private VolleyUploadFileRequest uploadRequest;
    private String mimeType = "multipart/form-data";
    private String url = "http://localhost:8080/messenger/webapi/hello/post";

    private VolleyUploadFileRequest uploadFileRequest;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.third);

        button = (Button) findViewById(R.id.upload);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                uploadFileRequest = new VolleyUploadFileRequest(url, new File("/sdcard/test.txt"), "test.txt", new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), "response", Toast.LENGTH_SHORT).show();
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Upload failed!\r\n" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }, new VolleyUploadFileRequest.MultipartProgressListener() {

                    @Override
                    public void transferred(long transfered, int progress) {
                        Log.i("PROGRESS", progress + "");
                    }
                });
                queue.add(uploadFileRequest);
            }
        });
    }
}