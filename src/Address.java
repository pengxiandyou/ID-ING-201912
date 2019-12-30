package simple_id_system;
/**
 * 
 * @author 15081
 *
 */
public class Address {
	private String province;
	private String city;
	private String name;
	private String code;
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	/** （非 Javadoc）
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		//不加括号会只得前面
		return (province!=null?province+" ":" ")+(city!=null?city+" ":" ")+(name!=null?name+" ":" ");
	}
	
}
