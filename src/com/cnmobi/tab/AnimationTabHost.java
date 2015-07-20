package com.cnmobi.tab;

import com.FRANK.kcg_app.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TabHost;

/** 缂佈勫 TabHost 缂佸嫪娆㈤敍灞界敨閺堝鍨忛崗銉ュ瀼閸戣櫣娈戝鎴濆З閸斻劎鏁鹃弫鍫熺亯閵嗭拷 */
public class AnimationTabHost extends TabHost {

	private Animation slideLeftIn;// 娴犲骸鐫嗛獮鏇炰箯鏉堢绻橀弶锟�
	private Animation slideLeftOut;// 娴犲骸鐫嗛獮鏇炰箯鏉堢懓鍤崢锟�
	private Animation slideRightIn;// 娴犲骸鐫嗛獮鏇炲礁鏉堢绻橀弶锟�
	private Animation slideRightOut;// 娴犲骸鐫嗛獮鏇炲礁鏉堢懓鍤崢锟�

	/** 鐠佹澘缍嶉弰顖氭儊閹垫挸绱戦崝銊ф暰閺佸牊鐏� */
	private boolean isOpenAnimation;
	/** 鐠佹澘缍嶈ぐ鎾冲閺嶅洨顒锋い鐢垫畱閹粯鏆� */
	private int mTabCount;

	public AnimationTabHost(Context context, AttributeSet attrs) {
		super(context, attrs);
		/** 閸掓繂顫愰崠鏍帛鐠併倕濮╅悽锟� */
		slideLeftIn = AnimationUtils.loadAnimation(context,
				R.anim.slide_left_in);
		slideLeftOut = AnimationUtils.loadAnimation(context,
				R.anim.slide_left_out);
		slideRightIn = AnimationUtils.loadAnimation(context,
				R.anim.slide_right_in);
		slideRightOut = AnimationUtils.loadAnimation(context,
				R.anim.slide_right_out);
		isOpenAnimation = false;// 閸斻劎鏁炬妯款吇閸忔娊妫�

	}

	/**
	 * 鐠佸墽鐤嗛弰顖氭儊閹垫挸绱戦崝銊ф暰閺佸牊鐏�
	 * 
	 * @param isOpenAnimation
	 *            true閿涙碍澧﹀锟�
	 */
	public void setOpenAnimation(boolean isOpenAnimation) {
		this.isOpenAnimation = isOpenAnimation;
	}

	/**
	 * 
	 * @return 鏉╂柨娲栬ぐ鎾冲閺嶅洨顒锋い鐢垫畱閹粯鏆�
	 */

	public int getTabCount() {
		return mTabCount;
	}

	@Override
	public void addTab(TabSpec tabSpec) {
		mTabCount++;
		super.addTab(tabSpec);
	}

	// 闁插秴鍟搒etCurrentTab(int index) 閺傝纭堕敍宀冪箹闁插苯濮為崗銉ュЗ閻紮绱掗崗鎶芥暛閻愮懓姘ㄩ崷銊ㄧ箹閵嗭拷
	@Override
	public void setCurrentTab(int index) {
		// 閸掑洦宕查崜宥嗗閸︺劑銆夐惃鍕�夐棃锟�
		int mCurrentTabID = getCurrentTab();
		if (null != getCurrentView()) {
			// 缁楊兛绔村▎陇顔曠純锟� Tab 閺冭绱濈拠銉ワ拷闂磋礋 null閵嗭拷
			if (isOpenAnimation) {
				// 缁傝绱戦惃鍕�夐棃锟�
				// 瀵邦亞骞嗛弮璁圭礉閺堫偊銆夐崚鎵儑娑擄拷妞わ拷(鏉堝湱鏅径鍕倞)
				if (mCurrentTabID == (mTabCount - 1) && index == 0) {
					getCurrentView().startAnimation(slideLeftOut);
				}
				// 瀵邦亞骞嗛弮璁圭礉妫ｆ牠銆夐崚鐗堟汞妞わ拷
				else if (mCurrentTabID == 0 && index == (mTabCount - 1)) {
					getCurrentView().startAnimation(slideRightOut);
				}
				// 閸掑洦宕查崚鏉垮礁鏉堝湱娈戦悾宀勬桨閿涘奔绮犲锕佺珶缁傝绱�
				else if (index > mCurrentTabID) {
					getCurrentView().startAnimation(slideLeftOut);
				}
				// 閸掑洦宕查崚鏉夸箯鏉堝湱娈戦悾宀勬桨閿涘奔绮犻崣瀹犵珶缁傝绱�
				else if (index < mCurrentTabID) {
					getCurrentView().startAnimation(slideRightOut);
				}
			}
		}
		// 鐠佸墽鐤嗚ぐ鎾冲妞わ拷
		super.setCurrentTab(index);

		if (isOpenAnimation) {
			// 瑜版挸澧犳い浣冪箻閺夈儲妲搁崝銊ф暰
			// 瀵邦亞骞嗛弮璁圭礉閺堫偊銆夐崚鎵儑娑擄拷妞わ拷
			if (mCurrentTabID == (mTabCount - 1) && index == 0) {
				getCurrentView().startAnimation(slideRightIn);
			}
			// 瀵邦亞骞嗛弮璁圭礉妫ｆ牠銆夐崚鐗堟汞妞わ拷(鏉堝湱鏅径鍕倞)
			else if (mCurrentTabID == 0 && index == (mTabCount - 1)) {
				getCurrentView().startAnimation(slideLeftIn);
			}
			// 閸掑洦宕查崚鏉垮礁鏉堝湱娈戦悾宀勬桨閿涘奔绮犻崣瀹犵珶鏉╂稒娼�
			else if (index > mCurrentTabID) {
				getCurrentView().startAnimation(slideRightIn);
			}
			// 閸掑洦宕查崚鏉夸箯鏉堝湱娈戦悾宀勬桨閿涘奔绮犲锕佺珶鏉╂稒娼�
			else if (index < mCurrentTabID) {
				getCurrentView().startAnimation(slideLeftIn);
			}
		}
	}
}