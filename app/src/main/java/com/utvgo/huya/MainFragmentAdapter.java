package com.utvgo.huya;

import java.util.List;
 
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
 
/**
 * @作者： jiatao
 * @修改时间：2016-4-12 上午7:59:22 
 * @包名：com.cctvjiatao.viewpagedemo
 * @文件名：FragAdapter.java
 * @功能： FragmentPager适配器 
 */
public class MainFragmentAdapter extends FragmentPagerAdapter {
	
	private List<Fragment> list;
 
	public MainFragmentAdapter(FragmentManager fm) {
	    super(fm);
    }
	
	public MainFragmentAdapter(FragmentManager fm, List<Fragment> list) {
	    super(fm);
	    this.list = list;
	}
 
	@Override
	public Fragment getItem(int postion) {
		return list.get(postion);
	}
 
	@Override
	public int getCount() {
		return list.size();
	}
}
 
