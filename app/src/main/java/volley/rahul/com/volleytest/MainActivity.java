package volley.rahul.com.volleytest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import volley.rahul.com.volley.VolleyJsonGetRequest;

public class MainActivity extends AppCompatActivity implements VolleyJsonGetRequest.VolleyJsonGetResponse{

    private VolleyJsonGetRequest volleyRequest;
    private Button click;
    private Button next;
    private TextView textView;
    private String url = "http://suggestqueries.google.com/complete/search?output=firefox&q=rahul";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        click = (Button)findViewById(R.id.click);
        next = (Button)findViewById(R.id.next);
        textView = (TextView)findViewById(R.id.response);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volleyRequest = new VolleyJsonGetRequest(MainActivity.this, url);
                volleyRequest.submitGetRequest();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SecondoneActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getResponse(String response, int status) {
        textView.setText(response);
    }
}
