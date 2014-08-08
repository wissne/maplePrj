package com.aurogon.zhuogui.util;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.ToneGenerator;

import com.aurogon.zhuogui.R;

public class SoundUtil  {
	private ToneGenerator mToneGenerator;

	private Object mToneGeneratorLock = new Object();// ������������

	private boolean mDTMFToneEnabled = true; // ����������

	private static final int TONE_LENGTH_MS = 150;// �ӳ�ʱ��
	
	private SoundPool sp;//����һ��SoundPool
	private int music;//����һ��������load������������suondID

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
		sp= new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);//��һ������Ϊͬʱ���������������������ڶ����������ͣ�����Ϊ��������
		music = sp.load(context, R.raw.button, 1); //����������زķŵ�res/raw���2��������Ϊ��Դ�ļ�����3��Ϊ���ֵ����ȼ�
	}



	public void playTone(int tone) {

		// TODO ���Ű�������

		if (!mDTMFToneEnabled) {

			return;

		}

		AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

		int ringerMode = audioManager.getRingerMode();

		if ((ringerMode == AudioManager.RINGER_MODE_SILENT)

		|| (ringerMode == AudioManager.RINGER_MODE_VIBRATE)) {// ��������ʱ��������������

			return;

		}

		synchronized (mToneGeneratorLock) {

			if (mToneGenerator == null) {

				return;

			}

			mToneGenerator.startTone(tone, TONE_LENGTH_MS);// ����

		}

	}

}
