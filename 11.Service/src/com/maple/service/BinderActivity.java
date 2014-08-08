package com.maple.service;

import com.maple.service.BinderService.MyBinder;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BinderActivity extends Activity {
	
	
	private Button btnStartBinderService;
	private Button btnStopBinderService;
	
	private boolean isConnected = false;
	
	private OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.btnStartBinderService:
					bindService();
					break;
				case R.id.btnStopBinderService:
					unbind();
					break;
				default:
					break;
			}
		}

	};
	
	private void unbind() {
		// TODO Auto-generated method stub
		if (isConnected)
			unbindService(conn);
		isConnected = false;
	}

	private void bindService() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(BinderActivity.this, BinderService.class);
		bindService(intent, conn, Context.BIND_AUTO_CREATE);
		isConnected = true;
	}
	
	private ServiceConnection conn = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder binder) {
			// TODO Auto-generated method stub
			MyBinder myBinder = (MyBinder) binder;
			@SuppressWarnings("unused")
			BinderService service = myBinder.getService();
		}
	};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder);
        
        btnStartBinderService = (Button) findViewById(R.id.btnStartBinderService);
        btnStopBinderService = (Button) findViewById(R.id.btnStopBinderService);
        btnStartBinderService.setOnClickListener(listener);
        btnStopBinderService.setOnClickListener(listener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_binder, menu);
        return true;
    }
}
