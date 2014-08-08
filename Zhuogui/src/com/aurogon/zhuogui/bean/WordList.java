package com.aurogon.zhuogui.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class WordList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1293167233847166252L;
	
	public static ArrayList<WordBean> wordList = new ArrayList<WordBean>();
	
	public static int getCount() {
		if (wordList != null)
			return wordList.size();
		return 0;
	}
	
	public static WordBean getWord(int i) {
		if (wordList != null && wordList.size() > i) {
			return wordList.get(i);
		} 
		return null;
	}

	static {
		wordList.add(new WordBean("����", "��Ӣ", "2����"));
		wordList.add(new WordBean("����", "��Ӣ", "2����"));
		wordList.add(new WordBean("Ԫ��", "չ��", "2����"));
		wordList.add(new WordBean("��ȸ", "��ѻ", "2����"));
		wordList.add(new WordBean("����", "����", "2����"));
		wordList.add(new WordBean("üë", "����", "2����"));
		wordList.add(new WordBean("����", "ά��", "2����"));
		wordList.add(new WordBean("״Ԫ", "�ھ�", "2����"));
		wordList.add(new WordBean("����", "����", "2����"));
		wordList.add(new WordBean("�����", "�����", "3����"));
		wordList.add(new WordBean("Ħ�г�", "�綯��", "3����"));
		wordList.add(new WordBean("�߸�Ь", "����Ь", "3����"));
		wordList.add(new WordBean("������", "�����", "3����"));
		wordList.add(new WordBean("С����", "��«��", "3����"));
		wordList.add(new WordBean("֩����", "֩�뾫", "3����"));
		wordList.add(new WordBean("�ڽڸ���", "Ʊ������", "4����"));
		wordList.add(new WordBean("��������", "�ҵ��޻�", "4����"));
		wordList.add(new WordBean("õ��", "�¼�", "2����"));
		wordList.add(new WordBean("����", "����", "2����"));
		wordList.add(new WordBean("����", "�紨", "2����"));
		wordList.add(new WordBean("л��", "����", "2����"));
		wordList.add(new WordBean("�Ϸ�", "�ּ�", "2����"));
		wordList.add(new WordBean("ţ��", "����", "2����"));
		wordList.add(new WordBean("����", "����", "2����"));
		wordList.add(new WordBean("�ײ�", "����", "2����"));
		wordList.add(new WordBean("����", "��ĩ", "2����"));
		wordList.add(new WordBean("��ӹ", "����", "2����"));
		wordList.add(new WordBean("����", "����", "2����"));
		wordList.add(new WordBean("����", "��ʨ", "2����"));
		wordList.add(new WordBean("ˮ��", "ˮͰ", "2����"));
		wordList.add(new WordBean("����", "�ں�", "2����"));
		wordList.add(new WordBean("ɭ��", "�Դ�", "2����"));
		wordList.add(new WordBean("����", "����", "2����"));
		wordList.add(new WordBean("����", "ˮ��", "2����"));
		wordList.add(new WordBean("ֽ��", "����", "2����"));
		wordList.add(new WordBean("����", "����", "2����"));
		wordList.add(new WordBean("���", "̨��", "2����"));
		wordList.add(new WordBean("�׶�", "����", "2����"));
		wordList.add(new WordBean("����", "����", "2����"));
		wordList.add(new WordBean("����", "����", "2����"));
		wordList.add(new WordBean("̫��", "����", "2����"));
		wordList.add(new WordBean("����", "�۷�", "2����"));
		wordList.add(new WordBean("СƷ", "����", "2����"));
		wordList.add(new WordBean("���", "����", "2����"));
		wordList.add(new WordBean("����", "����", "2����"));
		wordList.add(new WordBean("����", "����", "2����"));
		wordList.add(new WordBean("����", "����", "2����"));
		wordList.add(new WordBean("ʣŮ", "����", "2����"));
		wordList.add(new WordBean("ͯ��", "��", "2����"));
		wordList.add(new WordBean("����", "���", "2����"));
		wordList.add(new WordBean("����", "����", "2����"));
		wordList.add(new WordBean("���", "����", "2����"));
		wordList.add(new WordBean("����", "����", "2����"));
		wordList.add(new WordBean("�Ϸ�", "�ּ�", "2����"));
		wordList.add(new WordBean("��Ӣ", "����", "2����"));
		wordList.add(new WordBean("���", "����", "2����"));
		wordList.add(new WordBean("����", "����", "2����"));
		wordList.add(new WordBean("����", "�黨", "2����"));
		wordList.add(new WordBean("��ֽ", "����", "2����"));
		wordList.add(new WordBean("��ͷ", "����", "2����"));
		wordList.add(new WordBean("�ֻ�", "����", "2����"));
		wordList.add(new WordBean("ͬѧ", "ͬ��", "2����"));
		wordList.add(new WordBean("��ɴ", "ϲ��", "2����"));
		wordList.add(new WordBean("�Ϸ�ү", "����ү", "3����"));
		wordList.add(new WordBean("ħ��ʦ", "ħ��ʦ", "3����"));
		wordList.add(new WordBean("Ѽ��ñ", "����ñ", "3����"));
		wordList.add(new WordBean("˫��̥", "����̥", "3����"));
		wordList.add(new WordBean("���˽�", "�����", "3����"));
		wordList.add(new WordBean("��СѼ", "�ҹ���", "3����"));
		wordList.add(new WordBean("������", "�߸�˧", "3����"));
		wordList.add(new WordBean("�����", "�㻨Ǯ", "3����"));
		wordList.add(new WordBean("��˷�", "������", "3����"));
		wordList.add(new WordBean("���¸�", "������", "3����"));
		wordList.add(new WordBean("ͼ���", "ͼ���", "3����"));
		wordList.add(new WordBean("������", "ǰ����", "3����"));
		wordList.add(new WordBean("ϴ�·�", "��Ƿ�", "3����"));
		wordList.add(new WordBean("ţ���", "���⸬", "3����"));
		wordList.add(new WordBean("������", "������", "3����"));
		wordList.add(new WordBean("С����", "��С��", "3����"));
		wordList.add(new WordBean("������", "������", "3����"));
		wordList.add(new WordBean("֩����", "������", "3����"));
		wordList.add(new WordBean("������", "ľ�Ǵ�", "3����"));
		wordList.add(new WordBean("�����", "ˮ����", "3����"));
		wordList.add(new WordBean("С����", "������", "3����"));
		wordList.add(new WordBean("޹�²�", "������", "3����"));
		wordList.add(new WordBean("���غ�", "������", "3����"));
		wordList.add(new WordBean("��ʫʫ", "�����", "3����"));
		wordList.add(new WordBean("��ִ�", "��¥��", "3����"));
		wordList.add(new WordBean("���ӵ�", "������", "3����"));
		wordList.add(new WordBean("������", "���ʽ�", "3����"));
		wordList.add(new WordBean("�����", "��˿��", "3����"));
		wordList.add(new WordBean("������", "�ʳȶ�", "3����"));
		wordList.add(new WordBean("��ԡ¶", "��ԡ��", "3����"));
		wordList.add(new WordBean("ϴ��¶", "������", "3����"));
		wordList.add(new WordBean("���г�", "�綯��", "3����"));
		wordList.add(new WordBean("������", "����Ա", "3����"));
		wordList.add(new WordBean("��ɽ��", "������", "3����"));
		wordList.add(new WordBean("������", "���ݴ�", "3����"));
		wordList.add(new WordBean("ʮ�����", "�������", "4����"));
		wordList.add(new WordBean("�ɼ�˼��", "Ŭ������", "4����"));
		wordList.add(new WordBean("л���Ž�", "�˳���ٳ", "4����"));
		wordList.add(new WordBean("����Ħ˹", "������һ", "4����"));
		wordList.add(new WordBean("�������", "�����Ứ", "4����"));
		wordList.add(new WordBean("���ǻ�԰", "��������", "4����"));
		wordList.add(new WordBean("�������", "�����˲�", "4����"));
		wordList.add(new WordBean("��������", "�ǳ�����", "4����"));
		wordList.add(new WordBean("����ֱǰ", "ȫ���Ը�", "4����"));
		wordList.add(new WordBean("������˿", "��ϲ����", "4����"));
		wordList.add(new WordBean("���Ŷ���", "Ƥ������", "4����"));
		wordList.add(new WordBean("�����״�", "�ʲ�����", "4����"));
		wordList.add(new WordBean("��Ŀ���", "����֮��", "4����"));
		wordList.add(new WordBean("�����۾�", "�����۾�", "4����"));
		wordList.add(new WordBean("�����ļ�", "��������", "4����"));
		wordList.add(new WordBean("�ļ���ǧ��", "����˯����", "5����"));
		wordList.add(new WordBean("����ʮ����", "�����׹�צ", "5����"));
		wordList.add(new WordBean("����ţ����", "����ţ����", "5����"));
		wordList.add(new WordBean("����style", "���������", "7����"));
		wordList.add(new WordBean("��ɽ����ףӢ̨", "����ŷ������Ҷ", "7����"));
	}
}
