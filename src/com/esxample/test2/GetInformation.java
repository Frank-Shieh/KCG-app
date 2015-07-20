package com.esxample.test2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import com.FRANK.kcg_app.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
public class GetInformation extends Activity  {
	SwipeRefreshLayout mSwipeLayout=null;
	    ListView listview;  
	    Handler handler;  
	    List<Map<String, Object>> data;  
	  
	    final String CSDNURL = "http://www.csdn.net";  
	  
	    @Override  
	    protected void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState);  
	       
	        setContentView(R.layout.getnews);
	        
	         
	        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.id_swipe_ly); 
	     
	        listview=(ListView)findViewById(R.id.listview);
        handler = getHandler();  
 	        ThreadStart(); 
	    //  initListview();
	        mSwipeLayout.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,  
	                android.R.color.holo_orange_light, android.R.color.holo_red_light);  
	        
	        mSwipeLayout.setOnRefreshListener(new OnRefreshListener(){
	        	
	        	  @Override
	              public void onRefresh() {
	              	mSwipeLayout.setRefreshing(true);
	              	 ( new Handler()).postDelayed(new Runnable() {
	                       @Override
	                       public void run() {
	                      	   handler = getHandler();  
	         	    	        ThreadStart(); 
	         	    	       //initListview();
	         	    	     mSwipeLayout.setRefreshing(false);
	        
	                       }
	                   }, 3000);
	               
	      	    
	              }
	        });
	       
	    
	        listview.setOnScrollListener(new AbsListView.OnScrollListener() {
	            @Override
	             public void onScrollStateChanged(AbsListView absListView, int i) {
	 
	        }
	 
	            @Override
	             public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
	                if (firstVisibleItem == 0)
	                	listview.setEnabled(true);
	                else
	                	listview.setEnabled(false);
	        }
	    });
	         
	    }  
	    /** 
	     * 新开辟线程处理联网操作 
	   
	     */  
	    private void ThreadStart() {  
	        new Thread() {  
	            public void run() {  
	                Message msg = new Message();  
	                try {  
	                    data = getCsdnNetDate();  
	                    msg.what = data.size();  
	                } catch (Exception e) {  
	                    e.printStackTrace();  
	                    msg.what = -1;  
	                }  
	                handler.sendMessage(msg);  
	               
	            }  
	        }.start();  
	    }  
	    /** 
	     * 联网获得数据 
	    
	     */  
	    private List<Map<String, Object>> getCsdnNetDate() {  
	        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();  
	        String csdnString = http_get(CSDNURL);  
	        //<li><a title="(.*?)" href="(.*?)" target="_blank" onclick="LogClickCount\(this,363\);">\1</a></li>  
	        //title="(.*?)" href="(.*?)".*?,363\)  
	   //System.out.println(csdnString);
	        Pattern p = Pattern.compile("title=\"(.*?)\" href=\"(.*?)\".*?330");  
	        Matcher m = p.matcher(csdnString);  
	        while (m.find()) {  
	            MatchResult mr=m.toMatchResult();  
	            Map<String, Object> map = new HashMap<String, Object>();  
	            map.put("title", mr.group(1));  
	            map.put("url", mr.group(2));  
	            result.add(map);  
	        }  
	        return result;  
	    }  
	    /** 
	     * 处理联网结果，显示在listview 
	  
	     */  
	    private Handler getHandler() {  
	        return new Handler(){  
	            public void handleMessage(Message msg) {  
	                if (msg.what < 0) {  
	                    Toast.makeText(GetInformation.this, "数据获取失败", Toast.LENGTH_SHORT).show();  
	                }else {  
	                    initListview();  
	                    
	                }  
	            }  
	        };  
	    }  
	    /** 
	     * 在listview里显示数据 
	    
	     */  
	    private void initListview() {  
	        //listview = getListView();
	        SimpleAdapter adapter = new SimpleAdapter(this, data,  
	                R.layout.list_item, new String[] { "title"},  
	                new int[] { R.id.title});  
	        listview.setAdapter(adapter);  
	        listview.setOnItemClickListener(new OnItemClickListener() {  
	            @Override  
	            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,  
	                    long arg3) {  
	                Map<String, Object> map = data.get(arg2);  
	                String url = (String)(map.get("url"));  
	                Bundle bundle=new Bundle();
	                bundle.putString("ExtendUrl", url);
	                Intent intent = new Intent();
	                intent.putExtras(bundle);
	                 intent.setClass(GetInformation.this, ExtendGetInformation.class);
	                startActivity(intent);  
	            }  
	        });  
	    }  
	  
	    /** 
	     * get请求URL，失败时尝试三次 
	    
	     */  
	    private String http_get(String url) {  
	        final int RETRY_TIME = 3;  
	        HttpClient httpClient = null;  
	        HttpGet httpGet = null;  
	  
	        String responseBody = "";  
	        int time = 0;  
	        do {  
	            try {  
	                httpClient = getHttpClient();  
	                httpGet = new HttpGet(url);  
	                HttpResponse response = httpClient.execute(httpGet);  
	                if (response.getStatusLine().getStatusCode() == 200) {  
	                    //用utf-8编码转化为字符串  
	                    byte[] bResult = EntityUtils.toByteArray(response.getEntity());  
	                    if (bResult != null) {  
	                        responseBody = new String(bResult,"utf-8");  
	                    }  
	                }  
	                break;  
	            } catch (IOException e) {  
	                time++;  
	                if (time < RETRY_TIME) {  
	                    try {  
	                        Thread.sleep(1000);  
	                    } catch (InterruptedException e1) {  
	                    }  
	                    continue;  
	                }  
	                e.printStackTrace();  
	            } finally {  
	                httpClient = null;  
	            }  
	        } while (time < RETRY_TIME);  
	  
	        return responseBody;  
	    }  
	  
	    private  HttpClient getHttpClient() {  
	        HttpParams httpParams = new BasicHttpParams();  
	        //设定连接超时和读取超时时间  
	        HttpConnectionParams.setConnectionTimeout(httpParams, 6000);  
	        HttpConnectionParams.setSoTimeout(httpParams, 30000);  
	        return new DefaultHttpClient(httpParams);  
	    }
	  
	
	}  