package com.aurogon.zhuogui.util;

public interface Constant {

	public interface HOST_DATA {
		public static final String DATA = "data";
		public static final String NAME = "name";
		public static final String IP = "IP";
		public static final String TOTAL = "total";
		public static final String ROOM = "room";
	}
	
	public interface PEOPLE_TYPE {
		public static final String GHOST = "ghost";
		public static final String IDIOT = "idiot";
		public static final String PEOPLE = "people";
	}

	public interface WORD_TYPE {
		public static final String PEOPLE_WORD = "peopleword";
		public static final String IDIOT_WORD = "idiotword";
	}
	
	public interface MESSAGE_TYPE {
		public static final int PLAYER_IS_READY = 1;
		public static final int PLAYER_HAS_LEFT = 2;
		public static final int HOST_HAS_LEFT = 9;
		public static final int SHOW_MESSAGE = 0;
		public static final int ROOM_IS_FULL = 8;
		public static final int GET_YOUR_WORD = 11;
		public static final int SEND_YOUR_TICKEDT = 12;
		public static final int SEND_MY_TICKET = 13;
		public static final int PLAYER_GAME_OVER = 99;
		public static final int GAME_OVER = 100;
	}
	
	public interface USER_OPRATION_RESULT {
		public static final int USER_ADD_SUCCESS = 0;
		public static final int USER_DEL_SUCCESS = 0;
		public static final int USER_NAME_EXISTS = 1;
		public static final int USER_RE_CONN = 2;
		public static final int USER_IS_NULL = 3;
		public static final int USER_NOT_FOUND = 4;
		public static final int USER_NOT_EXISTS = 5;
		public static final int ROOM_IS_FULL = 6;
	}
	
}
