package org.zhwen.helight_ui.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.zhwen.helight_ui.R;

public class MineFragment extends Fragment {  
  
    @Override  
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState) {  
        View mineLayout = inflater.inflate(R.layout.mine_layout,  
                container, false);  
        return mineLayout;  
    }  
  
}  