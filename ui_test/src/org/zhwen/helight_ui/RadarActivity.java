package org.zhwen.helight_ui;


import android.app.Activity;
import android.os.Bundle;

import org.zhwen.helight_ui.view.RadarView;

public class RadarActivity extends Activity {
	
	RadarView search_device_view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.radar_layout);
		search_device_view = (RadarView) findViewById(R.id.radar_view);
		search_device_view.setWillNotDraw(false);
	}
}
