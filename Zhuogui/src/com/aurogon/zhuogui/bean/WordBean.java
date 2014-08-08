package com.aurogon.zhuogui.bean;

import java.io.Serializable;

public class WordBean implements Serializable {

	private static final long serialVersionUID = -2189077986244962120L;

	private String peopleWord;

	private String idiotWord;

	private String hint;

	public String getPeopleWord() {
		return peopleWord;
	}

	public void setPeopleWord(String peopleWord) {
		this.peopleWord = peopleWord;
	}

	public String getIdiotWord() {
		return idiotWord;
	}

	public void setIdiotWord(String idiotWord) {
		this.idiotWord = idiotWord;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public WordBean(String peopleWord, String idiotWord, String hint) {
		this.peopleWord = peopleWord;
		this.idiotWord = idiotWord;
		this.hint = hint;
	}

	public WordBean() {
	}
	
}
