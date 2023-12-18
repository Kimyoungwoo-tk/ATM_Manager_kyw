package ATM;

import java.util.Scanner;

public class Util {
Scanner sc = new Scanner(System.in);
final String CUR_PATH = System.getProperty("user.dir")+"\\src\\ATM";
//account.txt , client.txt
String clientFileName = "client.txt";
String accountFileName = "account.txt";





void tempData() {
	String userdata = "1001/test01/pw1/김철수\n";
	userdata += "1002/test02/pw2/이영희\n";
	userdata += "1003/test03/pw3/신민수\n";
	userdata += "1004/test04/pw4/최상민";

	String accountdata = "test01/1111-1111-1111/8000\n";
	accountdata += "test02/2222-2222-2222/5000\n";
	accountdata += "test01/3333-3333-3333/11000\n";
	accountdata += "test03/4444-4444-4444/9000\n";
	accountdata += "test01/5555-5555-5555/5400\n";
	accountdata += "test02/6666-6666-6666/1000\n";
	accountdata += "test03/7777-7777-7777/1000\n";
	accountdata += "test04/8888-8888-8888/1000";
}
}
