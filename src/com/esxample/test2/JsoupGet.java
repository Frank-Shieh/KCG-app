package com.esxample.test2;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.FRANK.kcg_app.R;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

  
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
@SuppressLint("InlinedApi")
public class JsoupGet extends Activity
{ 
    Handler handler;  
    List<Map<String, String>> data; 
    Document doc=null;
    SwipeRefreshLayout mSwipeLayout=null;
    ListView listview;
   
	
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@SuppressLint("InlinedApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
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
	
	private void ThreadStart() {
		// TODO Auto-generated method stub
		new Thread(){
			public void run(){
				  Message msg = new Message();  
				try{
					data=getData();
					msg.what=data.size();
						
				}
				catch(Exception e){
					e.printStackTrace();
					msg.what=-1;
				}
				handler.sendMessage(msg);	
			}

		}.start();
	}
	
	/*private void BlurThread(){
		new Thread(){
			public void run(){
				try {
					
					
				}catch(Exception e ){
					
					e.printStackTrace();
				}
			}
			
		}.start();
		
	
	}*/
	
	
    private Handler getHandler() {  
        return new Handler(){  
            public void handleMessage(Message msg) {  
                if (msg.what < 0) {  
                    Toast.makeText(JsoupGet.this, "数据获取失败", Toast.LENGTH_SHORT).show();  
                }else {  
                    initListview();  
                    
                }  
            }  
        };  
    }  
	
	private List<Map<String, String>> getData() {
		// TODO Auto-generated method stub
	//f	List<Map<String, String>> result =new ArrayList<Map<String,String>>();  
		 try {
		
			 String string = "http://www.scaukcg.com";
				doc = Jsoup.parse(new URL(string), 5000);
			 } catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		/* Elements es = doc.getElementsByClass("topic");
			for (org.jsoup.nodes.Element e : es) {
				 Map<String, String> map = new HashMap<String, String>(); 
				map.put("title", e.getElementsByTag("a").text());
				map.put("href", "http://www.cnbeta.com"
						+ e.getElementsByTag("a").attr("href"));
				result.add(map);}*/

		
		 List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			
		 	Elements es = doc.getElementById("head_news_main").getElementsByClass("item");
			
		    //Element link= doc.getElementById("head_news_main");
			for (Element e : es) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("title",e.getElementsByTag("a").text());
					map.put("href",  "http://www.scaukcg.com/"+e.getElementsByTag("a").attr("href"));
					list.add(map);
				
			}
			return list;
	
}


	 private void initListview() {  
	        //listview = getListView();
	
	
		 SimpleAdapter adapter= new SimpleAdapter(this, data,  
	                R.layout.list_item, new String[] {"title"},  
	                new int[] { R.id.title });
		
	       listview.setAdapter(adapter);
		
	
		listview.setOnItemClickListener(new OnItemClickListener() {  
	            @Override  
	            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,  
	                    long arg3) {  
	                Map<String, String> map = data.get(arg2);  
	                String url = (String)(map.get("href"));  
	                Bundle bundle=new Bundle();
	                bundle.putString("ExtendUrl", url);
	                Intent intent = new Intent();
	                intent.putExtras(bundle);
	                 intent.setClass(JsoupGet.this, ExtendGetInformation.class);
	                startActivity(intent);  
	            }  
	        });  
	    }  



	








}