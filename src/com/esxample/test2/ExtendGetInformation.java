package com.esxample.test2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class ExtendGetInformation extends Activity{
	WebView webview=null;
	 private String url;
	 @SuppressLint("SetJavaScriptEnabled")
	@Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
  try{
	  Bundle bundle=getIntent().getExtras();
	 url=bundle.getString("ExtendUrl");
	 Toast.makeText(ExtendGetInformation.this, url, Toast.LENGTH_LONG).show();
	  
  }catch(Exception e){
	  e.printStackTrace();	  
  }
        // 在标题栏上显示进度  
        getWindow().requestFeature(Window.FEATURE_PROGRESS);  
  
        // 定义WebView  
        webview = new WebView(this);  
        setContentView(webview);  
  
        // 滚动条风格  
        webview.setScrollBarStyle(0);  
  
        // 设置JS可用  
        webview.getSettings().setJavaScriptEnabled(true);  
  
        final Activity activity = this;  
  
        /* 
         * WebChromeClient类:用来辅助WebView处理JavaScript的对话框,网站图标,网站Title,加载进度等 
         * 通过setWebChromeClient调协WebChromeClient类 
         */  
        webview.setWebChromeClient(new WebChromeClient() {  
            // 加载进度中,100时停止  
            public void onProgressChanged(WebView view, int progress) {  
                activity.setProgress(progress * 100);  
            }  
  
            @Override  
            public void onReceivedTitle(WebView view, String title) {  
                activity.setTitle(title);  
            }  
  
        });  
  
        /* 
         * WebViewClient类: 用来辅助WebView处理各种通知,请求等事件的类 
         * 通过setWebViewClient设置WebViewClient类 
         */  
        webview.setWebViewClient(new WebViewClient() {  
            // 页面加载失败  
            public void onReceivedError(WebView view, int errorCode,  
                    String description, String failingUrl) {  
                Toast.makeText(activity, "异常:! " + description,  
                        Toast.LENGTH_LONG).show();  
            }  
  
        });  
        webview.loadUrl(url);  
  
    }  
  
    /* 
     * 通过WebView的goBack(),goForward()方法设置其前进和后退 
     */  
    @Override  
    public boolean onKeyDown(int keyCode, KeyEvent event) {  
        // TODO Auto-generated method stub  
        if (keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack()) {  
            // 返回前一个页面  
            webview.goBack();  
            return true;  
        }  
        return super.onKeyDown(keyCode, event);  
    }  


}
