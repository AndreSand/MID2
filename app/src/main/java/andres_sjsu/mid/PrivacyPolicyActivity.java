package andres_sjsu.mid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class PrivacyPolicyActivity extends AppCompatActivity {
    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);


        web =(WebView)findViewById(R.id.webView);
        web.loadUrl("file:///android_asset/privacy_policy.html");
    }
}


