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
		wordList.add(new WordBean("王菲", "那英", "2个字"));
		wordList.add(new WordBean("王菲", "那英", "2个字"));
		wordList.add(new WordBean("元芳", "展昭", "2个字"));
		wordList.add(new WordBean("麻雀", "乌鸦", "2个字"));
		wordList.add(new WordBean("胖子", "肥肉", "2个字"));
		wordList.add(new WordBean("眉毛", "胡须", "2个字"));
		wordList.add(new WordBean("何炅", "维嘉", "2个字"));
		wordList.add(new WordBean("状元", "冠军", "2个字"));
		wordList.add(new WordBean("饺子", "包子", "2个字"));
		wordList.add(new WordBean("端午节", "中秋节", "3个字"));
		wordList.add(new WordBean("摩托车", "电动车", "3个字"));
		wordList.add(new WordBean("高跟鞋", "增高鞋", "3个字"));
		wordList.add(new WordBean("汉堡包", "肉夹馍", "3个字"));
		wordList.add(new WordBean("小矮人", "葫芦娃", "3个字"));
		wordList.add(new WordBean("蜘蛛侠", "蜘蛛精", "3个字"));
		wordList.add(new WordBean("节节高升", "票房大卖", "4个字"));
		wordList.add(new WordBean("反弹琵琶", "乱弹棉花", "4个字"));
		wordList.add(new WordBean("玫瑰", "月季", "2个字"));
		wordList.add(new WordBean("董永", "许仙", "2个字"));
		wordList.add(new WordBean("若曦", "晴川", "2个字"));
		wordList.add(new WordBean("谢娜", "李湘", "2个字"));
		wordList.add(new WordBean("孟非", "乐嘉", "2个字"));
		wordList.add(new WordBean("牛奶", "豆浆", "2个字"));
		wordList.add(new WordBean("保安", "保镖", "2个字"));
		wordList.add(new WordBean("白菜", "生菜", "2个字"));
		wordList.add(new WordBean("辣椒", "芥末", "2个字"));
		wordList.add(new WordBean("金庸", "古龙", "2个字"));
		wordList.add(new WordBean("赵敏", "黄蓉", "2个字"));
		wordList.add(new WordBean("海豚", "海狮", "2个字"));
		wordList.add(new WordBean("水盆", "水桶", "2个字"));
		wordList.add(new WordBean("唇膏", "口红", "2个字"));
		wordList.add(new WordBean("森马", "以纯", "2个字"));
		wordList.add(new WordBean("烤肉", "涮肉", "2个字"));
		wordList.add(new WordBean("气泡", "水泡", "2个字"));
		wordList.add(new WordBean("纸巾", "手帕", "2个字"));
		wordList.add(new WordBean("杭州", "苏州", "2个字"));
		wordList.add(new WordBean("香港", "台湾", "2个字"));
		wordList.add(new WordBean("首尔", "东京", "2个字"));
		wordList.add(new WordBean("橙子", "橘子", "2个字"));
		wordList.add(new WordBean("葡萄", "提子", "2个字"));
		wordList.add(new WordBean("太监", "人妖", "2个字"));
		wordList.add(new WordBean("蝴蝶", "蜜蜂", "2个字"));
		wordList.add(new WordBean("小品", "话剧", "2个字"));
		wordList.add(new WordBean("裸婚", "闪婚", "2个字"));
		wordList.add(new WordBean("新年", "跨年", "2个字"));
		wordList.add(new WordBean("吉他", "琵琶", "2个字"));
		wordList.add(new WordBean("公交", "地铁", "2个字"));
		wordList.add(new WordBean("剩女", "御姐", "2个字"));
		wordList.add(new WordBean("童话", "神话", "2个字"));
		wordList.add(new WordBean("作家", "编剧", "2个字"));
		wordList.add(new WordBean("警察", "捕快", "2个字"));
		wordList.add(new WordBean("结婚", "订婚", "2个字"));
		wordList.add(new WordBean("奖牌", "金牌", "2个字"));
		wordList.add(new WordBean("孟飞", "乐嘉", "2个字"));
		wordList.add(new WordBean("那英", "韩红", "2个字"));
		wordList.add(new WordBean("面包", "蛋糕", "2个字"));
		wordList.add(new WordBean("作文", "论文", "2个字"));
		wordList.add(new WordBean("油条", "麻花", "2个字"));
		wordList.add(new WordBean("壁纸", "贴画", "2个字"));
		wordList.add(new WordBean("枕头", "抱枕", "2个字"));
		wordList.add(new WordBean("手机", "座机", "2个字"));
		wordList.add(new WordBean("同学", "同桌", "2个字"));
		wordList.add(new WordBean("婚纱", "喜服", "2个字"));
		wordList.add(new WordBean("老佛爷", "老天爷", "3个字"));
		wordList.add(new WordBean("魔术师", "魔法师", "3个字"));
		wordList.add(new WordBean("鸭舌帽", "遮阳帽", "3个字"));
		wordList.add(new WordBean("双胞胎", "龙凤胎", "3个字"));
		wordList.add(new WordBean("情人节", "光棍节", "3个字"));
		wordList.add(new WordBean("丑小鸭", "灰姑娘", "3个字"));
		wordList.add(new WordBean("富二代", "高富帅", "3个字"));
		wordList.add(new WordBean("生活费", "零花钱", "3个字"));
		wordList.add(new WordBean("麦克风", "扩音器", "3个字"));
		wordList.add(new WordBean("郭德纲", "周立波", "3个字"));
		wordList.add(new WordBean("图书馆", "图书店", "3个字"));
		wordList.add(new WordBean("男朋友", "前男友", "3个字"));
		wordList.add(new WordBean("洗衣粉", "皂角粉", "3个字"));
		wordList.add(new WordBean("牛肉干", "猪肉脯", "3个字"));
		wordList.add(new WordBean("泡泡糖", "棒棒糖", "3个字"));
		wordList.add(new WordBean("小沈阳", "宋小宝", "3个字"));
		wordList.add(new WordBean("土豆粉", "酸辣粉", "3个字"));
		wordList.add(new WordBean("蜘蛛侠", "蝙蝠侠", "3个字"));
		wordList.add(new WordBean("口香糖", "木糖醇", "3个字"));
		wordList.add(new WordBean("酸菜鱼", "水煮鱼", "3个字"));
		wordList.add(new WordBean("小笼包", "灌汤包", "3个字"));
		wordList.add(new WordBean("薰衣草", "满天星", "3个字"));
		wordList.add(new WordBean("张韶涵", "王心凌", "3个字"));
		wordList.add(new WordBean("刘诗诗", "刘亦菲", "3个字"));
		wordList.add(new WordBean("甄嬛传", "红楼梦", "3个字"));
		wordList.add(new WordBean("甄子丹", "李连杰", "3个字"));
		wordList.add(new WordBean("包青天", "狄仁杰", "3个字"));
		wordList.add(new WordBean("大白兔", "金丝猴", "3个字"));
		wordList.add(new WordBean("果粒橙", "鲜橙多", "3个字"));
		wordList.add(new WordBean("沐浴露", "沐浴盐", "3个字"));
		wordList.add(new WordBean("洗发露", "护发素", "3个字"));
		wordList.add(new WordBean("自行车", "电动车", "3个字"));
		wordList.add(new WordBean("班主任", "辅导员", "3个字"));
		wordList.add(new WordBean("过山车", "碰碰车", "3个字"));
		wordList.add(new WordBean("铁观音", "碧螺春", "3个字"));
		wordList.add(new WordBean("十面埋伏", "四面楚歌", "4个字"));
		wordList.add(new WordBean("成吉思汗", "努尔哈赤", "4个字"));
		wordList.add(new WordBean("谢娜张杰", "邓超孙俪", "4个字"));
		wordList.add(new WordBean("福尔摩斯", "工藤新一", "4个字"));
		wordList.add(new WordBean("贵妃醉酒", "黛玉葬花", "4个字"));
		wordList.add(new WordBean("流星花园", "花样男子", "4个字"));
		wordList.add(new WordBean("神雕侠侣", "天龙八部", "4个字"));
		wordList.add(new WordBean("天天向上", "非诚勿扰", "4个字"));
		wordList.add(new WordBean("勇往直前", "全力以赴", "4个字"));
		wordList.add(new WordBean("鱼香肉丝", "四喜丸子", "4个字"));
		wordList.add(new WordBean("麻婆豆腐", "皮蛋豆腐", "4个字"));
		wordList.add(new WordBean("语无伦次", "词不达意", "4个字"));
		wordList.add(new WordBean("鼠目寸光", "井底之蛙", "4个字"));
		wordList.add(new WordBean("近视眼镜", "隐形眼镜", "4个字"));
		wordList.add(new WordBean("美人心计", "倾世皇妃", "4个字"));
		wordList.add(new WordBean("夏家三千金", "爱情睡醒了", "5个字"));
		wordList.add(new WordBean("降龙十八掌", "九阴白骨爪", "5个字"));
		wordList.add(new WordBean("红烧牛肉面", "香辣牛肉面", "5个字"));
		wordList.add(new WordBean("江南style", "最炫民族风", "7个字"));
		wordList.add(new WordBean("梁山伯与祝英台", "罗密欧与朱丽叶", "7个字"));
	}
}
