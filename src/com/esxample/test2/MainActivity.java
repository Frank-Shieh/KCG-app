package com.esxample.test2;


import com.FRANK.kcg_app.R;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity
{
// 创建TabHost
public TabHost mth;
public static final String TAB_HOME = "科创新闻";
public static final String TAB_NEWS = "签到";
public static final String TAB_ABOUT = "部门介绍";
public static final String TAB_SEARCH = "扫二维码";

public RadioGroup radioGroup;
@Override
public void onCreate(Bundle savedInstanceState)
{
  super.onCreate(savedInstanceState);
  // 去除标题
  //requestWindowFeature(Window.FEATURE_NO_TITLE);
  setContentView(R.layout.activity_main);
  // 初始化底部菜单
  init();
  // 底部菜单点击事件
  clickevent();
}
/**
  * 每一个底部按钮点击事件，切换相应的界面
  */
private void clickevent()
{
  this.radioGroup = (RadioGroup) findViewById(R.id.main_radio);
  radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener()
  {
   @Override
   public void onCheckedChanged(RadioGroup group, int checkedId)
   {
    // 根据点击的按钮跳转到相应的界面
    switch (checkedId)
    {
    case R.id.radio_button0:
     mth.setCurrentTabByTag(TAB_HOME);
     break;
    case R.id.radio_button1:
     mth.setCurrentTabByTag(TAB_NEWS);
     break;
    case R.id.radio_button2:
     mth.setCurrentTabByTag(TAB_ABOUT);
     break;
    case R.id.radio_button3:
     mth.setCurrentTabByTag(TAB_SEARCH);
     break;
   
    }
   }
  });
}
/**
  * 实例化TabHost,往TabHost添加5个界面
  */
private void init()
{
  // 实例化TabHost
  mth = this.getTabHost();
  TabSpec ts1 = mth.newTabSpec(TAB_HOME).setIndicator(TAB_HOME);
  ts1.setContent(new Intent(MainActivity.this, JsoupGet.class));
  mth.addTab(ts1);// 往TabHost中第一个底部菜单添加界面
  TabSpec ts2 = mth.newTabSpec(TAB_NEWS).setIndicator(TAB_NEWS);
  ts2.setContent(new Intent(MainActivity.this, Signin.class));
  mth.addTab(ts2);
  TabSpec ts3 = mth.newTabSpec(TAB_ABOUT).setIndicator(TAB_ABOUT);
  ts3.setContent(new Intent(MainActivity.this, Introduction.class));
  mth.addTab(ts3);
  TabSpec ts4 = mth.newTabSpec(TAB_SEARCH).setIndicator(TAB_SEARCH);
  ts4.setContent(new Intent(MainActivity.this, Scanner.class));
  mth.addTab(ts4);
  
}
//监听返回键
@Override
public boolean dispatchKeyEvent(KeyEvent event)
{
if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK)
{
new AlertDialog.Builder(this).setCancelable(false).setTitle("温馨提示").setMessage("您确定要退出吗?").setPositiveButton("确定", new DialogInterface.OnClickListener()
{
 public void onClick(DialogInterface dialog, int which)
 {
  finish();
 }
}).setNegativeButton("取消", new DialogInterface.OnClickListener()
{
 public void onClick(DialogInterface dialog, int which)
 {
 }
}).show();
return true;// 不知道返回true或是false有什么区别??
}
return super.dispatchKeyEvent(event);
}

}



