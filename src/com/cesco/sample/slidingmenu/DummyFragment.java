package com.cesco.sample.slidingmenu;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DummyFragment extends Fragment {

	private String bundleTAG = "SENT";
	private String bundleContent;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Bundle bundle = this.getArguments();
		if(bundle != null) {
			bundleContent = bundle.getString(bundleTAG,"none");
		}
		
		//close menu if is showing
		if(MainActivity.mMenu.isMenuShowing()) {
			MainActivity.mMenu.toggle(true);
		}
		
		setRetainInstance(true);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View v = inflater.inflate(R.layout.dummy_fragment_layout, container,false);
		TextView dummyText = (TextView) v.findViewById(R.id.dummy_text);
		dummyText.setText(bundleContent);
		
		return v;
	}

}
