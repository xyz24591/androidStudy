package example.web;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;

import com.style.base.BaseToolBarActivity;
import com.style.framework.R;
import com.style.framework.databinding.ActivityWebViewBinding;


public class WebViewAndJSActivity extends BaseToolBarActivity {

    ActivityWebViewBinding bd;
    private String url = "file:///android_asset/interact.html";

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        bd = DataBindingUtil.setContentView(this, R.layout.activity_web_view);
        super.setContentView(bd.getRoot());
        initData();
    }

    @Override
    public void initData() {
        setToolbarTitle("与js交互测试");

        bd.webView.getSettings().setJavaScriptEnabled(true);
        bd.webView.addJavascriptInterface(new JsInterface(), "control");

        bd.webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {
                // Activity和Webview根据加载程度决定进度条的进度大小

            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                showToast("onJsAlert");
                result.confirm();
                return true;
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                return super.onJsConfirm(view, url, message, result);
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }

            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                showToast(consoleMessage.message());
                return true;//拦截log
            }
        });

        bd.btnJavaToJs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bd.webView.loadUrl("javascript:javaCallJavascript('javacalljs参数')");
            }
        });
        bd.webView.loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        if (bd.webView.canGoBack()) {
            bd.webView.goBack();
            return;
        }
        super.onBackPressed();
    }

    public class JsInterface {

        @JavascriptInterface
        public void show(String toast) {
            showToast(toast);
        }

    }
}
