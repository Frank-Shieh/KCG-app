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
	  
	        // �ڱ���������ʾ����  
	        getWindow().requestFeature(Window.FEATURE_PROGRESS);  
	  
	        // ����WebView  
	      //  webview = new WebView(this);  
	        setContentView(R.layout.signin);  
	        webview=(WebView)findViewById(R.id.signIn);
	      webview.setWebViewClient(new WebViewClients()); 
	        
	        
	        
	        // ȡ��������
	       	webview.setScrollBarStyle(0); 
	        //��������������
	        webview.requestFocus();
	        // ����JS����  
	        webview.getSettings().setJavaScriptEnabled(true);  
	        //��ʾ����ͼ��
	        webview.getSettings().setBlockNetworkImage(true);
	        //����WebView�����ļ�����
	        webview.getSettings().setAllowFileAccess(true);
	       // �����Ƿ�֧�ֱ佹
	        webview.getSettings().setSupportZoom(true);
	        //����ȡ����ֱ,ˮƽ������
	        webview.setVerticalScrollBarEnabled(false);
	        webview.setHorizontalScrollBarEnabled(false);
	        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
	        final Activity activity = this;  
	       
	       
	        /* 
	         * WebChromeClient��:��������WebView����JavaScript�ĶԻ���,��վͼ��,��վTitle,���ؽ��ȵ� 
	         * ͨ��setWebChromeClient��ЭWebChromeClient�� 
	         */  
	        webview.setWebChromeClient(new WebChromeClient() {  
	            // ���ؽ�����,100ʱֹͣ  
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
	         * WebViewClient��: ��������WebView�������֪ͨ,������¼����� 
	         * ͨ��setWebViewClient����WebViewClient�� 
	         */  
	        webview.setWebViewClient(new WebViewClient() {  
	            // ҳ�����ʧ��  
	            public void onReceivedError(WebView view, int errorCode,  
	                    String description, String failingUrl) {  
	                Toast.makeText(activity, "�쳣:! " + description,  
	                        Toast.LENGTH_LONG).show();  
	            }

				@Override
				public boolean shouldOverrideUrlLoading(WebView view, String url) {
					// TODO Auto-generated method stub
					if(url.indexOf("http://weibo.com/")<0){//�Ͼ�������λ�������΢��������Ϊ����֮�󣬷��������� 
						view.loadUrl(url); 
						} 
						return true; 
						} 
	  
	        });  
	        webview.loadUrl("http://www.scaukcg.com/KCGS/activity/");  
	  
	    }  
	  
	    /* 
	     * ͨ��WebView��goBack(),goForward()����������ǰ���ͺ��� 
	     */  
	    @Override  
	    public boolean onKeyDown(int keyCode, KeyEvent event) {  
	        // TODO Auto-generated method stub  
	        if (webview.canGoBack() && event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {  
	            // ����ǰһ��ҳ��  
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
