package com.esxample.test2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.FRANK.kcg_app.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Introduction extends Activity {
	private int[] imageIds=new int[]{  com.FRANK.kcg_app.R.drawable.bluebutton,com.FRANK.kcg_app.R.drawable.redbutton,R.drawable.yellowbutton,R.drawable.orangebutton};

	private String[] data=new String[]{"��ҵ��������ѧ����������֯�߻�����ERP��ACM����ҵ�߻������ȣ���"
			+ "���ξ������������ˣ������Ŷ�����չ�̼�������������ϵ�̼Һ�����Ѱ���̼�������"
			+ "������Ժ�ʿƼ�������֯��ϵ�Ͳ��ż�Ľ�����ϵ����ҵ��ҵ��Ҫ��׿Խ����Ѫ����ǧ������ˣ�",
			       "���鲿������������֯���ڲ�����Э���ø������ŵĹ�����"
					+ "���Ͽƴ�������������С���Ҫ�Ĺ�������������Դ�������ʹ���"
					+ "�������Ĳ߻�����֯���ǩ�����ۺϲ��������ƶ���֯���������ƶ�"
					+ "���������鹤������Ͻ�����С�ص��������ɣ�",
					"��Ŀ������������Ŀ�Ĺ���, ������Ŀ"
							+ "��ʡ����У����Ժ������ӱ���Լ���������Ŀ��ʽ���м����»��"
							+ "���ϵ��ռ��������ݽ�,��������Ŀ���Ա�����硢�ټ���"
							+ "��Ŀ�������֯�߻��ȡ����һʱ��Ӵ�ѧԺ�Ĵ�����Ŀ��"
							+ "������Ŀ�����Ͷ��ˣ�",
				"����������������ƴ����ĵĶ�������������"
			    + "������ֳ��ĺ��������������������Ÿ�׫д��"
				+ "�ֳ�����ȹ����������Ÿ���������ĵļ�����̨��"
				+ "���ܹٷ���վ��΢����΢�ŵ�������ʩ��ά��������"
				+ "���뼼����������һ�������Ÿ��ԣ����Ŵ���ɣ�"};
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.introduction);
		ListView listView=(ListView)findViewById(R.id.introduction);
		
		
		
	   List<Map<String,Object>> listitems=new ArrayList<Map<String,Object>>();
		for(int i=0;i<data.length;i++)
		{
			
			Map<String,Object> listItem=new HashMap<String,Object>();
			listItem.put("image",imageIds[i] );
			listItem.put("data", data[i]);
			listitems.add(listItem);
		}
		
		SimpleAdapter simpleAdapter =new SimpleAdapter(this,listitems,R.layout.introduction_item,new String[]{"image","data"},new int[]{R.id.header,R.id.text1});
		listView.setAdapter(simpleAdapter);
		
		
	}



}
