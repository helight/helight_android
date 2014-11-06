package org.zhwen.helightxu.helight;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;

public class Appstart extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent intent = new Intent (Appstart.this,ShakeActivity.class);
                startActivity(intent);
                Appstart.this.finish();
            }
        }, 1000);
    }
}
