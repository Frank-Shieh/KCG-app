package com.cnmobi.tab;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabWidget;

import com.FRANK.kcg_app.R;
import com.esxample.test2.Introduction;
import com.esxample.test2.JSONget;
import com.esxample.test2.Scanner;
import com.esxample.test2.Signin;

/**
 * 娴滆桨姹夋禍锟�
 * 閺堫剚娼垫禒鏍ㄦЦtabhost閻ㄥ嫪绔存稉顏堛�夌粵鎾呯礉娴ｅ棙妲告禒鏍ф躬鏉╂瑩鍣烽崣鍫熸Цtabhost閿涘矂鍣烽棃銏℃箒閻撗呭閵嗕浇顫嬫０鎴欙拷渚�鐓舵稊鎰╋拷浣规瀮濡楋拷4娑擃亪銆夌粵锟�
 * 娴犳牜娈�4娑擃亪銆夌粵鍓ф畱activity闁棄婀猚om.cnmobi.yishu.activity.tabhost.people.yun閸栧懘鍣烽棃锟�
 */
@SuppressWarnings("deprecation")
public class TabHostActivity  extends TabActivity implements	OnTabChangeListener {
	private RadioButton tabPhoto, tabMedia, tabMusic, tabDocument;
	private List<RadioButton> radioButtons = new ArrayList<RadioButton>();
	private GestureDetector gestureDetector;
	private AnimationTabHost tabHost;
	@SuppressWarnings("unused")
	private TabWidget tabWidget;
	private int currentTabID = 1;

	/** 鐠佹澘缍嶈ぐ鎾冲閸掑棝銆塈D */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_tab_people_yun);

		initTab();

		initRadioButton();

		gestureDetector = new GestureDetector(new TabHostTouch());
		
		tabHost.setOnTabChangedListener(this);
		//onTabChanged("1");// 娴滆桨璐熺拫鍐暏閸ョ偠鐨熼弬瑙勭《閿涘苯鍨垫慨瀣闁銆嶉崡顡篴bs閻ㄥ嫰顤侀懝锟�
	}

	/**
	 * 閸掓稑缂撻柅澶愩�嶉崡锟� : newTabSpec(String tag), 閸掓稑缂撴稉锟芥稉顏堬拷澶愩�嶉崡锟�; 鐠佸墽鐤嗛幐澶愭尦閸氬秶袨 : setIndicator(閸欘偄鍚�);
	 * 鐠佸墽鐤嗛柅澶愩�嶉崡鈥冲敶鐎癸拷 : setContent(), 閸欘垯浜掔拋鍓х枂鐟欏棗娴樼紒鍕, 閸欘垯浜掔拋鍓х枂Activity, 娑旂喎褰叉禒銉啎缂冪摷ragement;
	 */
	public void initTab() {
		tabHost = (AnimationTabHost) findViewById(android.R.id.tabhost);
		tabWidget = (TabWidget) findViewById(android.R.id.tabs);

		tabHost.addTab(tabHost.newTabSpec("1").setIndicator("photo")
				.setContent(new Intent(this, JSONget.class)));
		tabHost.addTab(tabHost.newTabSpec("2").setIndicator("media")
				.setContent(new Intent(this, Signin.class)));
		tabHost.addTab(tabHost.newTabSpec("3").setIndicator("music")
				.setContent(new Intent(this, Introduction.class)));
		tabHost.addTab(tabHost.newTabSpec("4").setIndicator("document")
				.setContent(new Intent(this, Scanner.class)));
		 

		tabHost.setOpenAnimation(true);

	}

	public void initRadioButton() {
		tabPhoto = (RadioButton) findViewById(R.id.people_yun_photo);
		tabMedia = (RadioButton) findViewById(R.id.people_yun_media);
		tabMusic = (RadioButton) findViewById(R.id.people_yun_music);
		tabDocument = (RadioButton) findViewById(R.id.people_yun_document);

		radioButtons.add(tabPhoto);
		radioButtons.add(tabMedia);
		radioButtons.add(tabMusic);
		radioButtons.add(tabDocument);
		
		tabPhoto.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				tabHost.setCurrentTabByTag("1");

			}
		});

		tabMedia.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				tabHost.setCurrentTabByTag("2");

			}
		});

		tabMusic.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				tabHost.setCurrentTabByTag("3");

			}
		});

		tabDocument.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				tabHost.setCurrentTabByTag("4");

			}
		});

		  
	}

	 
	@Override
	public void onTabChanged(String tabId) {
		updateTab(tabId);
	}
	
	/**
	 * 閺囧瓨鏌奣ab閺嶅洨顒烽惃鍕杹閼硅绱濋崪灞界摟娴ｆ挾娈戞０婊嗗
	 * @param tabHost
	 */
	private void updateTab(String tabId) {
		// tabId 娑撶皠ewTabSpec(String tag) 娑擃厺绱堕崗銉ф畱鐎涙顑佹稉鐬璦g閿涘矁绻栭柌瀹糰g閺勶拷1,2,3 ,4,5閸欘垯浜掓潪顒佸床娑撶儤鏆ｈぐ顫┒娴滃骸鍨介弬锟�
		int tabID = Integer.parseInt(tabId);
		for (int i = 1; i <= 4; i++) {
			if (i == tabID) {
				if (radioButtons.get(i - 1) != null) {
					radioButtons.get(i - 1).setChecked(true);
				}
			} else {
				if (radioButtons.get(i - 1) != null) {
					radioButtons.get(i - 1).setChecked(false);
				}
			}
		}
	} 

	//娑擄拷鐎规俺顩﹂崘娆掔箹娑擃亝鏌熷▔鏇礉閸氾箑鍨憴锔芥嚋娑撳秷鎹ｆ担婊呮暏
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		if (gestureDetector.onTouchEvent(event)) {
			event.setAction(MotionEvent.ACTION_CANCEL);
		}
		return super.dispatchTouchEvent(event);
	}

	
	//娑擄拷鐎规俺顩﹂柌宥呭晸鏉╂瑤閲滈弬瑙勭《娑撳娼伴惃鍕磧閸氼剙娅掗惃鍒糿Fling()閺傝纭堕幍宥嗘箒閺佸牄锟斤拷
	@Override
	public boolean onTouchEvent(MotionEvent me) {
		
		return true;
	}
	
	 
	private class TabHostTouch extends SimpleOnGestureListener {
		/** 濠婃垵濮╃紙濠氥�夐幍锟介棁锟界捄婵堫瀲 */
		private  int ON_TOUCH_DISTANCE_X = 480/4;
		private  int ON_TOUCH_DISTANCE_Y = 800/8;


		/*
		 * boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
		 * float velocityY) Touch娴滃棙绮﹂崝銊ょ閻愮绐涚粋璇叉倵閿涘瘈p閺冩儼袝閸欐垯锟藉倸宓嗛悽銊﹀煕閹稿绗呯仦蹇撶閿涘苯鎻╅柅鐔盒╅崝銊ユ倵閺夋儳绱戦敍鍫濇皑閺勵垰婀仦蹇撶娑撳﹥绮﹂崝顭掔礆
		 * e1:缁楊兛绔存稉鐙滳TION_DOWN娴滃娆㈤敍鍫熷閹稿洦瀵滄稉瀣畱闁絼绔撮悙鐧哥礆 e2:閺堬拷閸氬簼绔存稉鐙滳TION_MOVE娴滃娆� 閿涘牊澧滈幐鍥ㄦ緱瀵拷閻ㄥ嫰鍋呮稉锟介悙鐧哥礆
		 * velocityX:閹靛瀵氶崷鈻佹潪瀵感╅崝銊ф畱闁喎瀹� 閸楁洑缍呴敍姘剼缁憋拷/缁夛拷 velocityY:閹靛瀵氶崷鈻傛潪瀵感╅崝銊ф畱闁喎瀹� 閸楁洑缍呴敍姘剼缁憋拷/缁夛拷
		 */
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			
			boolean b = Math.abs(e2.getX() - e1.getX()) > Math.abs(e2.getY() - e1.getY()) && Math.abs(e2.getY() - e1.getY()) <= ON_TOUCH_DISTANCE_Y;

			// 閸欒櫕绮﹂崝顭掔礉閸掑洦宕查崚鏉夸箯鏉堥�涚娑撶尲ab
			if (e2.getX() - e1.getX() >= ON_TOUCH_DISTANCE_X && b) {
				currentTabID = Integer.parseInt(tabHost.getCurrentTabTag());
				if (currentTabID > 1) { 
					currentTabID --;
				}else {// 瀵邦亞骞�
					currentTabID = 4;
				}
				tabHost.setCurrentTabByTag(currentTabID + "");
			}
			// 瀹革附绮﹂崝顭掔礉閸掑洦宕查崚鏉垮礁鏉堥�涚娑撶尲ab
			else if (e1.getX() - e2.getX() >= ON_TOUCH_DISTANCE_X && b) {
				currentTabID = Integer.parseInt(tabHost.getCurrentTabTag());
				if (currentTabID < 4) {
					currentTabID++;
				}else {// 瀵邦亞骞�
					currentTabID = 1;
				}
				tabHost.setCurrentTabByTag(currentTabID + "");
			}
			
			return false;
		}
	}

     
    
    
    
	long firstTime;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			long secondTime = System.currentTimeMillis();
			if (secondTime - firstTime > 2000) {// 婵″倹鐏夋稉銈嗩偧閹稿鏁弮鍫曟？闂傛挳娈ф径褌绨�2000濮ｎ偆顫楅敍灞藉灟娑撳秹锟斤拷閸戯拷
				firstTime = secondTime;// 閺囧瓨鏌奻irstTime
				return true;
			} else {
				System.exit(0);// 閸氾箑鍨柅锟介崙铏光柤鎼达拷
			}

			return false;
		} else if (keyCode == KeyEvent.KEYCODE_MENU
				&& event.getRepeatCount() == 0) {
			return true; // 鏉╂柨娲杢rue鐏忓彉绗夋导姘剨閸戞椽绮拋銈囨畱setting閼挎粌宕�
		}

		return false;
	}
	
	//鐩戝惉杩斿洖閿�
		@Override
		public boolean dispatchKeyEvent(KeyEvent event)
		{
		if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK)
		{
		new AlertDialog.Builder(this).setCancelable(false).setTitle("娓╅Θ鎻愮ず").setMessage("鎮ㄧ‘瀹氳閫�鍑哄悧?").setPositiveButton("纭畾", new DialogInterface.OnClickListener()
		{
		 public void onClick(DialogInterface dialog, int which)
		 {
		  finish();
		 }
		}).setNegativeButton("鍙栨秷", new DialogInterface.OnClickListener()
		{
		 public void onClick(DialogInterface dialog, int which)
		 {
		 }
		}).show();
		return true;// 涓嶇煡閬撹繑鍥瀟rue鎴栨槸false鏈変粈涔堝尯鍒�??
		}
		return super.dispatchKeyEvent(event);
		}

}
