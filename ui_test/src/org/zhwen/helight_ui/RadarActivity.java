package org.zhwen.helight_ui;


import android.app.Activity;
import android.os.Bundle;

import org.zhwen.helight_ui.view.SearchDevicesView;

public class RadarActivity extends Activity {
	
	SearchDevicesView search_device_view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.radar_layout);
		search_device_view = (SearchDevicesView) findViewById(R.id.search_device_view);
		search_device_view.setWillNotDraw(false);
	}
}
