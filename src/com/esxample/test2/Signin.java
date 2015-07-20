package com.esxample.test2;

import com.FRANK.kcg_app.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Signin extends Activity {
   /* WebView webView;
    WebSettings settings;
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signin);
	    webView=(WebView)findViewById(R.id.signIn);
	    settings=webView.getSettings();
	    settings.setWebChromeClient(WebChromeClient client)
	    webView.loadUrl("http://www.scaukcg.com/KCGS/activity/");
	    
	}*/
	WebView webview=null;

	 @SuppressLint("SetJavaScriptEnabled")
	@Override  
	    public void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState);  
	  
	        // 在标题栏上显示进度  
	        getWindow().requestFeature(Window.FEATURE_PROGRESS);  
	  
	        // 定义WebView  
	      //  webview = new WebView(this);  
	        setContentView(R.layout.signin);  
	        webview=(WebView)findViewById(R.id.signIn);
	      webview.setWebViewClient(new WebViewClients()); 
	        
	        
	        
	        // 取消滚动条
	       	webview.setScrollBarStyle(0); 
	        //触摸焦点起作用
	        webview.requestFocus();
	        // 设置JS可用  
	        webview.getSettings().setJavaScriptEnabled(true);  
	        //显示网络图像
	        webview.getSettings().setBlockNetworkImage(true);
	        //启用WebView访问文件数据
	        webview.getSettings().setAllowFileAccess(true);
	       // 设置是否支持变焦
	        webview.getSettings().setSupportZoom(true);
	        //设置取消竖直,水平滚动条
	        webview.setVerticalScrollBarEnabled(false);
	        webview.setHorizontalScrollBarEnabled(false);
	        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
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

				@Override
				public boolean onJsConfirm(WebView view, String url,
						String message, JsResult result) {
					// TODO Auto-generated method stub
					return super.onJsConfirm(view, url, message, result);
				}

				@Override
				public boolean onJsPrompt(WebView view, String url,
						String message, String defaultValue,
						JsPromptResult result) {
					// TODO Auto-generated method stub
					return super.onJsPrompt(view, url, message, defaultValue, result);
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

				@Override
				public boolean shouldOverrideUrlLoading(WebView view, String url) {
					// TODO Auto-generated method stub
					if(url.indexOf("http://weibo.com/")<0){//断绝连接两位大人物的微博。。因为连接之后，返不回来。 
						view.loadUrl(url); 
						} 
						return true; 
						} 
	  
	        });  
	        webview.loadUrl("http://www.scaukcg.com/KCGS/activity/");  
	  
	    }  
	  
	    /* 
	     * 通过WebView的goBack(),goForward()方法设置其前进和后退 
	     */  
	    @Override  
	    public boolean onKeyDown(int keyCode, KeyEvent event) {  
	        // TODO Auto-generated method stub  
	        if (webview.canGoBack() && event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {  
	            // 返回前一个页面  
	            webview.goBack();  
	            return true;  
	        }  
	        return super.onKeyDown(keyCode, event);  
	    }  
	    private class WebViewClients extends WebViewClient { 
	    	@Override 
	    	public boolean shouldOverrideUrlLoading(WebView view, String url) { 
	    	view.loadUrl(url); 
	    	return true; 
	    	} 
	    	} 

	    
	    
	    
}
