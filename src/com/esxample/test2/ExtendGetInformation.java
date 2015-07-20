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
        // �ڱ���������ʾ����  
        getWindow().requestFeature(Window.FEATURE_PROGRESS);  
  
        // ����WebView  
        webview = new WebView(this);  
        setContentView(webview);  
  
        // ���������  
        webview.setScrollBarStyle(0);  
  
        // ����JS����  
        webview.getSettings().setJavaScriptEnabled(true);  
  
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
  
        });  
        webview.loadUrl(url);  
  
    }  
  
    /* 
     * ͨ��WebView��goBack(),goForward()����������ǰ���ͺ��� 
     */  
    @Override  
    public boolean onKeyDown(int keyCode, KeyEvent event) {  
        // TODO Auto-generated method stub  
        if (keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack()) {  
            // ����ǰһ��ҳ��  
            webview.goBack();  
            return true;  
        }  
        return super.onKeyDown(keyCode, event);  
    }  


}
