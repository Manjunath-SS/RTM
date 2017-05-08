package com.example.manjunath.finalchatdemo;

import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText messageInput;
    Button sendButton;
    String MESSAGES_ENDPOINT="http://192.168.43.56:8000/messages/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get our input field by its ID
        messageInput = (EditText) findViewById(R.id.message_input);


        // get our button by its ID
        sendButton = (Button) findViewById(R.id.send_button);

        // set its click listener
        sendButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        postMessage();

    }

    private void postMessage() {
        String text = messageInput.getText().toString();

        // return if the text is blank
        if (text.equals("")) {
            return;
        }


        RequestParams params = new RequestParams();

        // set our JSON object
        params.put("text", text);
        params.put("name", "Newuser");
        params.put("time", new Date().getTime());

        // create our HTTP client
        AsyncHttpClient client = new AsyncHttpClient();


        client.post(MESSAGES_ENDPOINT + "/messages", params, new JsonHttpResponseHandler() {

            //@Override
            public void onSuccess(int statusCode, PreferenceActivity.Header[] headers, JSONObject response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        messageInput.setText("");
                    }
                });
            }



            //@Override
            public void onFailure(int statusCode, PreferenceActivity.Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getApplicationContext(), "Something went wrong :(", Toast.LENGTH_LONG).show();
            }
        });


    }
}
