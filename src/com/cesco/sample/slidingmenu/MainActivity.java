package com.cesco.sample.slidingmenu;


import java.util.ArrayList;
import java.util.List;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity implements OnItemClickListener {

	public static SlidingMenu mMenu;
	private ListView menuList;
	private CustomAdapter mAdapter;
	private List<String> entries;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		
		//menu view 
		View v = this.getLayoutInflater().inflate(R.layout.menu_content, null, false);
		menuList = (ListView) v.findViewById(R.id.menu_list);
		
		//create and attach menu
		mMenu = new SlidingMenu(this);
		mMenu.setMode(SlidingMenu.LEFT);
		mMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		mMenu.setShadowWidthRes(R.dimen.shadow_width);
		mMenu.setShadowDrawable(R.drawable.shadow);
		mMenu.setBehindWidthRes(R.dimen.slidingmenu_offset);
		mMenu.setFadeDegree(0.35f);
		mMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		//set menu layout
		mMenu.setMenu(v);
		
		
		entries = new ArrayList<String>();
		for(int i = 0; i <= 100; i++) {
			entries.add("Item "+i);
		}
		
		//create and set menu Adapter
		mAdapter = new CustomAdapter(entries, R.layout.menu_row, this);
		menuList.setAdapter(mAdapter);
		menuList.setOnItemClickListener(this);
		
		//set a DunnyFragment as initialView but without adding it to backstack
		setInitialView();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		
		//handle ActionBar title click
		case android.R.id.home: 
			mMenu.toggle(true);
			break;
		}
		return false;
	}
	
	@Override
	public void onBackPressed(){
		
		//Show fragments added to backstack
		FragmentManager fm = getFragmentManager();
		if (fm.getBackStackEntryCount() > 0) {
			Log.i("MainActivity", "popping backstack");
			fm.popBackStack();

		} else {
			Log.i("MainActivity", "nothing on backstack, calling super");
			super.onBackPressed();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		Fragment fragment = new DummyFragment();
		Bundle bundle = new Bundle();
		bundle.putString("SENT", entries.get(position));
		fragment.setArguments(bundle);
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(R.id.container,fragment);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		ft.addToBackStack("TAG");
		ft.commit();
	}
	
	private void setInitialView() {
		Fragment fragment = new DummyFragment();
		Bundle bundle = new Bundle();
		bundle.putString("SENT", "Make a Swipe or click on ActionBar title to open SlidingMenu, hit an item to replace the current fragment with a new one. Previous fragment(s) will be added to backstack and popped up on back button pressed (if any)");
		fragment.setArguments(bundle);
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(R.id.container,fragment);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		ft.commit();
	}

}
