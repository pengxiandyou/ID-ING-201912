package simple_id_system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * 
 * @author 15081
 *
 */
public class GB2260 {
	private HashMap<String, String> data;
	private static GB2260 instance = new GB2260();
	public static GB2260 getInstance() {
		return instance;
	}
	 public GB2260() {
	       
	        data = new HashMap<String, String>();
	        //文件末尾不能有不可见字符
	        InputStream inputStream = getClass().getResourceAsStream( "GB2260-2019.txt");
	        BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
	        try {
	            while (r.ready()) {
	                String line = r.readLine();
	                String[] split = line.split(" ");
	               // System.out.println(line+" "+split[0]+" "+split[1]+" "+split.length);
	                String code = split[0];
	                String name = split[1];
	                data.put(code, name);
	            }
	        } catch (IOException e) {
	            System.err.println("Error in loading GB2260 data!");
	            throw new RuntimeException(e);
	        }
	    }
	 public Address getAddress(String code) throws MyException {
		 if(code.length()!=6) {
			 throw new MyException("违法代码");
		 }
		 if(!data.containsKey(code)) {
			 return null;
		 }
		 Address address = new Address();
		 address.setCode(code);
		 address.setProvince(data.get(code.substring(0, 2)+"0000"));
		 address.setCity(data.containsKey(code.substring(0, 4)+"00")?data.get(code.substring(0, 4)+"00"):null);
		 address.setName(data.get(code));
		 return address;
		 	 
	 }
}
