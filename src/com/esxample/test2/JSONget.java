package com.esxample.test2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.FRANK.kcg_app.R;
public class JSONget extends Activity  {
	 Handler handler;  
	SwipeRefreshLayout mSwipeLayout=null;
    ListView listview;  
    List<Map<String, Object>> data; 
    String strUrl = "http://www.scaukcg.com/index.php?r=Api/news/list"; 
   // private Button btnJsonMulti; 
    @Override 
    public void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.getnews); 
  
 	   listview=(ListView)this.findViewById(R.id.listview);
 	  mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.id_swipe_ly); 
 	 
 				ThreadStart();
 				handler=getHandler();
 				 mSwipeLayout.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,  
 			                android.R.color.holo_orange_light, android.R.color.holo_red_light);  
 			        
 			        mSwipeLayout.setOnRefreshListener(new OnRefreshListener(){
 			        	
 			        	  @Override
 			              public void onRefresh() {
 			              	mSwipeLayout.setRefreshing(true);
 			              	 ( new Handler()).postDelayed(new Runnable() {
 			                       @Override
 			                       public void run() {
 			                    	   ThreadStart();
 			           				handler=getHandler(); 
 			         	    	       //initListview();
 			         	    	     mSwipeLayout.setRefreshing(false);
 			        
 			                       }
 			                   }, 3000);
 			               
 			      	    
 			              }
 			        });
 			       
   
    } 
    
    private void ThreadStart(){
    	new Thread(){
    		public void run() { 
    			 Message msg = new Message();  
    			try{
    				 
    	              //  String strUrl = ServerPageUtil.getStrUrl(UrlsOfServer.JSON_SINGER); 
    	                //获得返回的Json字符串 
    	                String strResult = connServerForResult(strUrl); 
    	                //解析Json字符串  
    	                data=parseJson(strResult); 
    	                msg.what = data.size();  
    			}
    			
    			catch(Exception e){
    				e.printStackTrace();
    			}
    		    handler.sendMessage(msg);  
	               
    		}
    
    	}.start();
    }
    
    private Handler getHandler() {  
        return new Handler(){  
            public void handleMessage(Message msg) {  
                if (msg.what < 0) {  
                    Toast.makeText(JSONget.this, "数据获取失败", Toast.LENGTH_SHORT).show();  
                }else {  
                    initListview();  
                    
                }  
            }  
        };  
    }  
    
    
    private String connServerForResult(String strUrl) { 
        // HttpGet对象 
        HttpGet httpRequest = new HttpGet(strUrl); 
        String strResult = ""; 
        try { 
            // HttpClient对象 
            HttpClient httpClient = new DefaultHttpClient(); 
            // 获得HttpResponse对象 
            HttpResponse httpResponse = httpClient.execute(httpRequest); 
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { 
                // 取得返回的数据 
                strResult = EntityUtils.toString(httpResponse.getEntity()); 
            } 
        } catch (ClientProtocolException e) {  
            e.printStackTrace(); 
        } catch (IOException e) {  
            e.printStackTrace(); 
        } 
        return strResult; 
    } 
    // 普通Json数据解析 
    private List<Map<String, Object>> parseJson(String strResult) { 
    	  List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();  
    		 try {  
    			JSONArray array=new JSONArray(strResult);
    			for(int i=0;i<array.length();i++)
    			{
    				JSONObject obj=array.getJSONObject(i);
    				//String title=obj.getString("title");
    				 Map<String, Object> map = new HashMap<String, Object>();  
    		            map.put("title", obj.getString("title"));  
    		       
    		            result.add(map);  
    			}
    
    	        } catch (JSONException e) {  
    	            // TODO Auto-generated catch block  
    	            e.printStackTrace();  
    	        }  
    		 return result;  	 
    }
    private void initListview() {  
        //listview = getListView();
        SimpleAdapter adapter = new SimpleAdapter(this, data,  
                R.layout.list_item, new String[] { "title"},  
                new int[] { R.id.title});  
        listview.setAdapter(adapter);  
       
    }  

}



