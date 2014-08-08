package com.maple.scs;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.maple.scs.service.SCSScreenService;
import com.maple.scs.service.SCSThreadService;
import com.maple.scs.util.Constant;
import com.maple.scs.util.Util;

public class MainActivity extends Activity {

	private ToggleButton toggleScreenService;
	
	private ToggleButton toggleThreadService;
	
	private Intent intentScreen = null;
	
	private Intent intentThread = null;

	private boolean isServiceRunning;
	
	private SeekBar seekBarDisable;
	
	private SeekBar seekBarEnable;
	
	private TextView tvDisable;
	
	private TextView tvEnable;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
//        getResources().getString(R.string.mins);
        toggleScreenService = (ToggleButton) findViewById(R.id.toggleService);
        toggleThreadService = (ToggleButton) findViewById(R.id.toggleThread);
        tvDisable = (TextView) findViewById(R.id.tvDisable);
        tvEnable = (TextView) findViewById(R.id.tvEnable);
        seekBarDisable = (SeekBar) findViewById(R.id.seekBarDisable);
        seekBarEnable = (SeekBar) findViewById(R.id.seekBarEnable);
        
        
        SharedPreferences sharedata = getSharedPreferences(Constant.DATA, 0);  
        int timeDisable = sharedata.getInt(Constant.CURRENT_TIME_DISABLE, 60);  
        int timeEnable = sharedata.getInt(Constant.CURRENT_TIME_ENABLE, 1);
        boolean isToggleThread = sharedata.getBoolean(Constant.TOGGLE_THREAD, false);
        
        seekBarDisable.setProgress(timeDisable);
        seekBarEnable.setProgress(timeEnable);
		tvDisable.setText(""+timeDisable);
        tvEnable.setText(""+timeEnable);
        
        /*
         * Check if service is running
         */
        isServiceRunning = Util.isServiceRunning(getApplicationContext(), SCSScreenService.class.getName());
        Log.i(Constant.TAG, "isScreenServiceRunning: " + isServiceRunning);
        toggleScreenService.setChecked(isServiceRunning);
        
        isServiceRunning = Util.isServiceRunning(getApplicationContext(), SCSThreadService.class.getName());
        Log.i(Constant.TAG, "isThreadRunning: " + isServiceRunning);
        toggleThreadService.setChecked(isServiceRunning);
        seekBarDisable.setEnabled(!isServiceRunning);
        seekBarEnable.setEnabled(!isServiceRunning);
        
        toggleScreenService.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					intentScreen = new Intent(MainActivity.this, SCSScreenService.class);
					// Start Service
					startService(intentScreen);
					Toast.makeText(MainActivity.this, R.string.enable_success,
							Toast.LENGTH_SHORT).show();
					Log.i(Constant.TAG, "startScreenService");
				} else {
					intentScreen = new Intent(MainActivity.this, SCSScreenService.class);
					// Stop Service
					stopService(intentScreen);
					Toast.makeText(MainActivity.this, R.string.disable_success,
							Toast.LENGTH_SHORT).show();
					Log.i(Constant.TAG, "stopScreenService");
				}
				
				if (isChecked) {
					intentThread = new Intent(MainActivity.this, SCSThreadService.class);
					// Start Service
					saveIntData(Constant.TOGGLE_CONN_COUNT, 1);
					startService(intentThread);
//					Toast.makeText(MainActivity.this, R.string.enable_thread_success,
//							Toast.LENGTH_SHORT).show();
					Log.i(Constant.TAG, "stopThreadService");
				} else {
					intentThread = new Intent(MainActivity.this, SCSThreadService.class);
					// Start Service
					stopService(intentThread);
//					Toast.makeText(MainActivity.this, R.string.disable_thread_success,
//							Toast.LENGTH_SHORT).show();
					Log.i(Constant.TAG, "stopThreadService");
				}
				seekBarDisable.setEnabled(!isChecked);
				seekBarEnable.setEnabled(!isChecked);
				saveBooleanData(Constant.TOGGLE_THREAD, isChecked);
			}
		});
        
        toggleThreadService.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			}
		});
        
        seekBarDisable.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				intentThread = new Intent(MainActivity.this, SCSThreadService.class);
				stopService(intentThread);
				toggleThreadService.setChecked(false);
				saveIntData(Constant.CURRENT_TIME_DISABLE, seekBar.getProgress());	
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				tvDisable.setText(""+progress);
			}
		});
        
        seekBarEnable.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
					intentThread = new Intent(MainActivity.this, SCSThreadService.class);
					stopService(intentThread);
					toggleThreadService.setChecked(false);
					saveIntData(Constant.CURRENT_TIME_ENABLE, seekBar.getProgress());
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				tvEnable.setText(""+progress);
			}
		});
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_main, menu);
        return false;
    }

	private void saveIntData(String str, int val) {
		SharedPreferences.Editor editor = getSharedPreferences(Constant.DATA, 0).edit();  
		editor.putInt(str, val);
		editor.commit();
	}
	
	private void saveBooleanData(String str, boolean val) {
		SharedPreferences.Editor editor = getSharedPreferences(Constant.DATA, 0).edit();  
		editor.putBoolean(str, val);
		editor.commit();
	}
}
