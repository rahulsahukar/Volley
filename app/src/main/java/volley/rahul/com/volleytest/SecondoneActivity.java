package volley.rahul.com.volleytest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

import volley.rahul.com.volley.VolleyJsonPostRequest;

public class SecondoneActivity extends AppCompatActivity implements VolleyJsonPostRequest.VolleyJsonPostResponse{

    private VolleyJsonPostRequest volleyRequest;
    private Button click;
    private Button prev;
    private TextView textView;
    private String url = "https://httpbin.org/post";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondone);

        JSONObject obj;

        click = (Button)findViewById(R.id.click1);
        prev = (Button)findViewById(R.id.prev);
        textView = (TextView)findViewById(R.id.response1);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volleyRequest = new VolleyJsonPostRequest(SecondoneActivity.this, url);
                volleyRequest.setParam("name", "Rahul");
                volleyRequest.setParam("title", "Something");
                volleyRequest.setParam("salary", "1000");
                volleyRequest.submitPostRequest();
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void postResponse(String response, int status) {
        if( response != null) {
            textView.setText(response);
        }
    }

}
