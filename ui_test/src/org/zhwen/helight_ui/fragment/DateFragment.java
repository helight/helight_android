package org.zhwen.helight_ui.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.zhwen.helight_ui.R;


public class DateFragment extends Fragment {  
  
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState) {  
        View dateLayout = inflater.inflate(R.layout.date_layout, container, false);  
        return dateLayout;  
    }  
  
}  