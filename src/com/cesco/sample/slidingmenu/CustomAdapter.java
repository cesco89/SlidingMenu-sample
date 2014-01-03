package com.cesco.sample.slidingmenu;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter{
	
	List<String> menuEntries;
	int layoutResourceId;
	Context mContext;
	
	//constructor
	public CustomAdapter(List<String> entries, int resID, Context con) {
		this.menuEntries = entries;
		this.layoutResourceId = resID;
		this.mContext = con;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return menuEntries.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return menuEntries.get(position);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View v, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(v == null) {
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(layoutResourceId, parent, false);
		}
		
		TextView title = (TextView) v.findViewById(R.id.menu_title);
		title.setText(menuEntries.get(position));
		
		return v;
	}

}
