package com.example.bonvoyaide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class SentimentReview extends AppCompatActivity {

    private TextView txvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentiment_review);

        final EditText input = findViewById(R.id.input);
        final Button postButton =  findViewById(R.id.post);
        final TextView output =  findViewById(R.id.output);
        final TextView text = findViewById(R.id.text);
        txvResult = (TextView) findViewById(R.id.txvResult);

        postButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                RequestQueue queue = Volley.newRequestQueue(SentimentReview.this);
                String url = "https://sentiment-analysis-api.herokuapp.com/sentiment";

                StringRequest sr = new StringRequest(Request.Method.POST, url , new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        text.setText(String.format("%s : ", input.getText().toString().toUpperCase()));
                        output.setText(response.toUpperCase());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        output.setText(error.getMessage());
                    }
                }) {
                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        HashMap<String, String> params2 = new HashMap<String, String>();
                        params2.put("text", input.getText().toString() );
                        return new JSONObject(params2).toString().getBytes();
                    }

                    @Override
                    public String getBodyContentType() {
                        return "application/json";
                    }
                };

                queue.add(sr);
            }

        });
    }

    public void getSpeechInput(View view) {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        } else {
            Toast.makeText(this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txvResult.setText(result.get(0));
                }
                break;
        }
    }
}
