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
// ����TabHost
public TabHost mth;
public static final String TAB_HOME = "�ƴ�����";
public static final String TAB_NEWS = "ǩ��";
public static final String TAB_ABOUT = "���Ž���";
public static final String TAB_SEARCH = "ɨ��ά��";

public RadioGroup radioGroup;
@Override
public void onCreate(Bundle savedInstanceState)
{
  super.onCreate(savedInstanceState);
  // ȥ������
  //requestWindowFeature(Window.FEATURE_NO_TITLE);
  setContentView(R.layout.activity_main);
  // ��ʼ���ײ��˵�
  init();
  // �ײ��˵�����¼�
  clickevent();
}
/**
  * ÿһ���ײ���ť����¼����л���Ӧ�Ľ���
  */
private void clickevent()
{
  this.radioGroup = (RadioGroup) findViewById(R.id.main_radio);
  radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener()
  {
   @Override
   public void onCheckedChanged(RadioGroup group, int checkedId)
   {
    // ���ݵ���İ�ť��ת����Ӧ�Ľ���
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
  * ʵ����TabHost,��TabHost���5������
  */
private void init()
{
  // ʵ����TabHost
  mth = this.getTabHost();
  TabSpec ts1 = mth.newTabSpec(TAB_HOME).setIndicator(TAB_HOME);
  ts1.setContent(new Intent(MainActivity.this, JsoupGet.class));
  mth.addTab(ts1);// ��TabHost�е�һ���ײ��˵���ӽ���
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
//�������ؼ�
@Override
public boolean dispatchKeyEvent(KeyEvent event)
{
if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK)
{
new AlertDialog.Builder(this).setCancelable(false).setTitle("��ܰ��ʾ").setMessage("��ȷ��Ҫ�˳���?").setPositiveButton("ȷ��", new DialogInterface.OnClickListener()
{
 public void onClick(DialogInterface dialog, int which)
 {
  finish();
 }
}).setNegativeButton("ȡ��", new DialogInterface.OnClickListener()
{
 public void onClick(DialogInterface dialog, int which)
 {
 }
}).show();
return true;// ��֪������true����false��ʲô����??
}
return super.dispatchKeyEvent(event);
}

}



