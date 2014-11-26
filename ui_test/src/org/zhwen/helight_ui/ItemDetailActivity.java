package org.zhwen.helight_ui;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

public class ItemDetailActivity extends Activity {
	private Intent mIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // requestWindowFeature(Window.FEATURE_NO_TITLE); //隐藏标题
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //         WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏

        setContentView(R.layout.detail_layout);
        mIntent = getIntent();  
        Bundle b = mIntent.getExtras();  
        
        Log.v("ItemDetailActivity", "title: " + b.getString("title"));
        Log.v("ItemDetailActivity", "info: " + b.getString("info"));
        
        TextView  title = (TextView) findViewById(R.id.title); 
        title.setText("title: " + b.getString("title"));
        TextView info = (TextView) findViewById(R.id.info); 
        info.setText("info: " + b.getString("info")); 

/*        
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent intent = new Intent(ItemDetailActivity.this, TabActivity.class);
        		startActivity(intent);
        		ItemDetailActivity.this.finish();
            }
        }, 1000);*/
    }
}
