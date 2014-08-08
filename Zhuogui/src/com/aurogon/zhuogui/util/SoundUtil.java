package com.aurogon.zhuogui.util;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.ToneGenerator;

import com.aurogon.zhuogui.R;

public class SoundUtil  {
	private ToneGenerator mToneGenerator;

	private Object mToneGeneratorLock = new Object();// 监视器对象锁

	private boolean mDTMFToneEnabled = true; // 按键操作音

	private static final int TONE_LENGTH_MS = 150;// 延迟时间
	
	private SoundPool sp;//声明一个SoundPool
	private int music;//定义一个整型用load（）；来设置suondID

	private Activity context;
	
	public Activity getContext() {
		return context;
	}

	public void playMusic() {
		sp.play(music, 1, 1, 0, 0, 1);
	}

	public void setContext(Activity context) {
		this.context = context;
	}
	
	
	public SoundUtil(Activity activity) {
		super();
		this.context = activity;
		init();
	}
	
	public void init() {
		sp= new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);//第一个参数为同时播放数据流的最大个数，第二数据流类型，第三为声音质量
		music = sp.load(context, R.raw.button, 1); //把你的声音素材放到res/raw里，第2个参数即为资源文件，第3个为音乐的优先级
	}



	public void playTone(int tone) {

		// TODO 播放按键声音

		if (!mDTMFToneEnabled) {

			return;

		}

		AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

		int ringerMode = audioManager.getRingerMode();

		if ((ringerMode == AudioManager.RINGER_MODE_SILENT)

		|| (ringerMode == AudioManager.RINGER_MODE_VIBRATE)) {// 静音或震动时不发出按键声音

			return;

		}

		synchronized (mToneGeneratorLock) {

			if (mToneGenerator == null) {

				return;

			}

			mToneGenerator.startTone(tone, TONE_LENGTH_MS);// 发声

		}

	}

}
