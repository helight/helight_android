package org.zhwen.helight_ui.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.zhwen.helight_ui.R;

public class SettingFragment extends Fragment {  
	  
    @Override  
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState) {  
        View settingLayout = inflater.inflate(R.layout.setting_layout,  
                container, false);  
        return settingLayout;  
    }  
  
} 