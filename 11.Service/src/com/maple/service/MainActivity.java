package com.maple.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.maple.service.BinderService.MyBinder;

public class MainActivity extends Activity {
	
	protected static final String TAG = "MainActivity";
	private Button btnStartService;
	private Button btnStopService;
	

	private Button btnStartBinderService;
	private Button btnStopBinderService;
	
	private Button btnStartNormalService;
	private Button btnStartIntentService;
	
	private boolean isConnected = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStartService = (Button) findViewById(R.id.btnStartService);
        btnStopService = (Button) findViewById(R.id.btnStopService);
        
        btnStartService.setOnClickListener(listener);
        btnStopService.setOnClickListener(listener);
        
        btnStartBinderService = (Button) findViewById(R.id.btnStartBinderService);
        btnStopBinderService = (Button) findViewById(R.id.btnStopBinderService);
        
        btnStartNormalService = (Button) findViewById(R.id.btnStartNormalService);
        btnStartIntentService = (Button) findViewById(R.id.btnStartIntentService);
        
        btnStartBinderService.setOnClickListener(listener);
        btnStopBinderService.setOnClickListener(listener);
        
        btnStartNormalService.setOnClickListener(listener);
        btnStartIntentService.setOnClickListener(listener);
    }
    
    private OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = null;
			switch (v.getId()) {
				case R.id.btnStartService:
					intent = new Intent(MainActivity.this, ExampleService.class);
					startService(intent);
					break;
				case R.id.btnStopService:
					intent = new Intent(MainActivity.this, ExampleService.class);
					stopService(intent);
					break;
				case R.id.btnStartBinderService:
					bindService();
					break;
				case R.id.btnStopBinderService:
					unbind();
					break;
				case R.id.btnStartNormalService:
					intent = new Intent(MainActivity.this, MyService.class);
					Log.i(TAG, "MainActivity Thread Id: " + Thread.currentThread().getId());
					startService(intent);
					break;
				case R.id.btnStartIntentService:
					intent = new Intent(MainActivity.this, MyIntentService.class);
					Log.i(TAG, "MainActivity Thread Id: " + Thread.currentThread().getId());
					startService(intent);
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
		Intent intent = new Intent(MainActivity.this, BinderService.class);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
