package com.cordova.example;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;

import org.apache.cordova.CordovaWebViewEngine;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

public class Form extends CordovaPlugin {
    public static final String TAG = Form.class.getSimpleName();
    private Button button;
    private  TextView textView;
    private  TextView textView1;


    public Form() {

    }

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }


    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("show".equals(action)) {
            loadPage();
        } else {
            return false;
        }
        return true;
    }

    public void loadPage(){

            LinearLayout mParent = new LinearLayout(webView.getContext());
            cordova.getActivity().runOnUiThread(() -> {
            ViewGroup.LayoutParams params =new ViewGroup.LayoutParams(900, 1000);
            button =new Button(webView.getContext());
            button.setLayoutParams(new ViewGroup.LayoutParams(400,400));
            textView = new TextView(webView.getContext());
            textView1 = new TextView(webView.getContext());
                textView.setLayoutParams(new ViewGroup.LayoutParams(200,400));
                textView1.setLayoutParams(new ViewGroup.LayoutParams(200,400));
            textView1.setText("deneme");
            textView1.setTextColor(Color.BLACK);
            textView.setText("deneme");
                textView.setTextColor(Color.BLACK);
            button.setText("Button");
            mParent.setLayoutParams(params);
            mParent.addView(textView);
            mParent.addView(textView1);
            mParent.addView(button);
            getLayout().addView(mParent);
            buttonListener();


        });
    }

    private void buttonListener(){
        cordova.getActivity().runOnUiThread(() ->
            button.setOnClickListener(v -> {
            CordovaWebViewEngine engine = webView.getEngine();
            if (engine == null) {
                webView.loadUrl("javascript:" + "deneme()");

            } else {
                engine.evaluateJavascript("deneme()", (result) -> {

                });
            }
        }));
    }


    private ViewGroup getLayout() {

        return (ViewGroup) webView.getView().getParent();
    }


    private void show(String msg, CallbackContext callbackContext) {
        if (msg == null || msg.length() == 0) {
            callbackContext.error("Empty message!");
            return;
        }

        Toast.makeText(webView.getContext(), msg, Toast.LENGTH_LONG).show();
        callbackContext.success(msg);
    }
}
