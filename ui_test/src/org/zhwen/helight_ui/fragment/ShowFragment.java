package org.zhwen.helight_ui.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.zhwen.helight_ui.R;

public class ShowFragment extends Fragment {  
  
    @Override  
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState) {  
        View showLayout = inflater.inflate(R.layout.show_layout,  
                container, false);  
        return showLayout;  
    }  
  
}  