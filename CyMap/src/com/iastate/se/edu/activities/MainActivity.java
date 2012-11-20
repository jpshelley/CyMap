package com.iastate.se.edu.activities;

import com.iastate.se.edu.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button btnMap;
	private Button btnViewSched;
	private Button btnAddApt;
	
	private Context thisCtx;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thisCtx = this;
        setContentView(R.layout.activity_main);
        loadObjects();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void loadObjects(){
    	btnMap       = (Button)findViewById(R.id.btnMap);
    	btnViewSched = (Button)findViewById(R.id.btnViewSched);
    	btnAddApt    = (Button)findViewById(R.id.btnAddApt);
    	


    	
    	btnMap.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				//Intent i = new Intent(thisCtx, c);
				//startActivity(i);
			}
		});
    	
    	btnViewSched.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
		        
				final String packageName = "com.iastate.se.edu.schedule";
		        
		        try {
					Class c = Class.forName(packageName + "." + "SchedListActivity");
					Intent i = new Intent(thisCtx, c);
					startActivity(i);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				setContentView(R.layout.schedule_menu_app);
			}
		});
    	
    	btnAddApt.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				//Intent i = new Intent(thisCtx, MapViewActivity.class);
				//startActivity(i);
				//setContentView(R.layout.add_layout);
			}
		});
    }
    
    
}
