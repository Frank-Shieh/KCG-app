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

	private String[] data=new String[]{"事业部：负责学术竞赛的组织策划（如ERP、ACM、商业策划大赛等）；"
			+ "担任竞赛答辩会主持人；公关团对外拓展商家赞助的领域，联系商家合作、寻求商家赞助；"
			+ "负责与院际科技创新组织联系和部门间的交流联系。事业事业，要你卓越！热血青年千万别错过了！",
			       "秘书部：管理整个组织的内部政务，协调好各个部门的工作，"
					+ "保障科创工作的有序进行。主要的工作包括人力资源管理、物资管理、"
					+ "内务工作的策划和组织、活动签到和综合测评，并制定组织管理的相关制度"
					+ "。爱好文书工作的你赶紧加入小秘的行列来吧！",
					"项目管理部：负责项目的管理, 包括项目"
							+ "（省级、校级、院级、丁颖杯以及其它以项目形式进行技创新活动）"
							+ "资料的收集、整理、递交,；负责项目组成员的联络、召集，"
							+ "项目答辩会的组织策划等。想第一时间接触学院的创新项目，"
							+ "加入项目管理部就对了！",
				"技术宣传部：负责科创中心的对外宣传工作，"
			    + "包括活动现场的海报、宣传单制作、新闻稿撰写、"
				+ "现场拍摄等工作；技术团负责管理中心的技术后台，"
				+ "主管官方网站、微博、微信等网络设施的维护工作。"
				+ "加入技术宣传部，一起来绽放个性，绽放创意吧！"};
	
	
	
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
