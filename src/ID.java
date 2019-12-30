package simple_id_system;

import java.util.regex.Pattern;

/**
 * IDcard.
 * @author 15081
 * 
 * 
 */
public class ID {
	private final static int ID_LENGTH = 18;
	private String  id;
	private Address address;
	public ID(String id) {
		super();
		this.id = id;
		try {
			this.address=GB2260.getInstance().getAddress(id.substring(0,6));
		} catch (MyException e) {
			System.out.println(e.getMessage());
		}
	}
	public ID() {
		this("");
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	/**
	 * check id.
	 * 
	 */
	public void check() {
		
		try {
			//1.length
			if (this.id.length() != ID_LENGTH) {
				throw new MyException("身份证长度不正确");
			}
			//2.number or letter
			String reg = "^(\\d{6})(18|19|20)?(\\d{2})([01]\\d)([0123]\\d)(\\d{3})(\\d|X|x)?$";
			Pattern pattern = Pattern.compile(reg);
			if (!pattern.matcher(this.id).find()) {
				throw new MyException("身份证不符合一般规则");
			}
			//3.rule1
			int[] weight =new int[ID_LENGTH];
			int[] a = new int[ID_LENGTH];
			int sum=0;
			for(int i=0;i<ID_LENGTH;i++) {
				weight[i]=((int)(Math.pow(2, 17-i)))%11;
			}
			char[] charArray = this.id.toCharArray();
			for(int i=0;i<ID_LENGTH;i++) {
				if(charArray[i] == 'X' || charArray[i] == 'x' ) {
					a[i]=10;
				}else {
					//不加“”会有奇怪现象 接收String 传入char
					a[i]=Integer.valueOf(charArray[i]+"");
				}
				if(i!=17) {
					sum=sum+a[i]*weight[i];
				}
			}
			//int temp=(sum%11)>=2?12-(sum%11):(sum%11>0?0:1);
			 int temp=(12-sum%11)%11;
			 if(a[ID_LENGTH-1]!=temp) {
				 throw new MyException("身份证未通过数学校验");
			 }
			 //4.existence ?
			 if(this.address==null) {
				 System.out.println("是身份证号码但不存在");
			 }else {
				 System.out.println("是身份证号码且存在 "+this.address.toString());
			 }
			
		} catch (MyException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	
}
