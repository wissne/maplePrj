import java.util.ArrayList;
import java.util.List;


public class Test {
	
	private List<TicketBean> ticketBeanList = new ArrayList<TicketBean>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int a = 14;
//		for (int i = 4; i < 20; i++) {
//			System.out.println(i + ":" + Math.floor((i + 2)/4.0));
//		}
		
		int all = 10;
		int ghost = (int) Math.floor((10 + 2)/4.0);
		int idiot = 1;
		int people = all - idiot - ghost;
		
		System.out.println(people);
		int left = all;
		for (int i = 0; i < all; i++) {
//			System.out.println(Math.floor(Math.random() * left % left));
			boolean con = Math.floor(Math.random() * left % left) < ghost && ghost > 0;
			if (con) {
				ghost--;
				System.out.println(i + " is " + " Ghost ");
			}
			left--;
		}
		
		// word
		String [][] words = {{"111", "112"}, {"222", "223"}, {"333", "334"}};
		int ind = (int) (Math.floor(Math.random() * words.length) % words.length);
		System.out.println(ind);

		ArrayList<String> list = new ArrayList<String>();
		
		list.add("123");
		list.add("223");
		
		System.out.println(list.size());
		list.remove(1);
		System.out.println(list.size());
		
		Test test = new Test();
		TicketBean bean1 = new TicketBean();
		bean1.setName("maple");
		test.addTicketBean(bean1);
		
		bean1 = new TicketBean();
		bean1.setName("maple");
		test.addTicketBean(bean1);
		
		bean1 = new TicketBean();
		bean1.setName("vic");
		test.addTicketBean(bean1);
		
		bean1 = new TicketBean();
		bean1.setName("vic");
		test.addTicketBean(bean1);
		bean1 = new TicketBean();
		bean1.setName("vic");
		test.addTicketBean(bean1);
		
		System.out.println(test.getMaxTicketNameString());
	}
	
	public void addTicketBean(TicketBean bean) {
		if (bean == null)
			return;
		for (TicketBean ticket : ticketBeanList) {
			if (ticket.getName().equals(bean.getName())) {
				ticket.addCount();
				return;
			}
		}
		ticketBeanList.add(bean);
	}
	
	public String[] getMaxTicketNames() {
		String[] names = new String[20];
		int maxCount = 0;
		int pp=0;
		for (TicketBean ticket : ticketBeanList) {
			if (ticket.getCount() > maxCount) {
				names = new String[20];
				pp = 0;
				names[pp++] = ticket.getName();
				maxCount = ticket.getCount();
			} else if (ticket.getCount() == maxCount) {
				names[pp++] = ticket.getName();
			}
		}
		return names;
	}
	
	public String getMaxTicketNameString() {
		String [] names = getMaxTicketNames();
		String res = "";
		for (int i = 0; i < names.length; i++) {
			if (names[i]!= null) {
				if (i>0)
					res += ",";
				res += names[i];
			}
		}
		return res;
	}
	
}

class TicketBean{
	private String name;
	private int count;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	public void addCount() {
		this.count = this.count + 1;
	}

}
