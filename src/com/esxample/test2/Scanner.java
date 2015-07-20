package com.esxample.test2;

import com.cnmobi.tab.TabHostActivity;
import com.google.zxing.client.android.CaptureActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Scanner extends Activity {
	 public static final int SCAN_CODE = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent intent = new Intent(Scanner.this, CaptureActivity.class);
		startActivityForResult(intent, SCAN_CODE);
		
	}
	 @Override
		protected void onActivityResult(int requestCode, int resultCode,
				Intent data) {
			// TODO Auto-generated method stub
	  switch (requestCode) {
		case SCAN_CODE:
			
			if (resultCode == RESULT_OK) {
				String result = data.getStringExtra("scan_result");
				Toast.makeText(this, result, Toast.LENGTH_LONG).show();
			} else if (resultCode == RESULT_CANCELED) {
				Intent intent=new Intent(); 
		    	intent.setClass(Scanner.this, TabHostActivity.class);
				startActivity(intent);
				finish();
				Toast.makeText(this, "É¨ÂëÊ§°Ü", Toast.LENGTH_LONG).show();
			}
			break;
		default:
			break;
		}
	}
		
	

}
