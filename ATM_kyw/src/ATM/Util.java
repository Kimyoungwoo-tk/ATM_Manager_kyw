package ATM;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Util {
Scanner sc;
final String CUR_PATH = System.getProperty("user.dir")+"\\src\\ATM";
//account.txt , client.txt
String clientFileName = "client.txt";
String accountFileName = "account.txt";

Util(){
	sc = new Scanner(System.in);
}

int getValue(String msg, int start, int end) {
	while(true) {
		System.out.printf("%s 입력:",msg);
		try {
			int num = sc.nextInt();
			sc.nextLine();
			if(num<start || num> end) {
				System.out.printf("%d~%d사이 값 입력 ",start,end);
				continue;
			}
			return num;
		} catch(InputMismatchException e) {
			sc.nextLine();
			System.out.println("숫자값만 입력");
		}
	}
}

	String getValue(String msg) {
		System.out.println(">>"+msg + ":");
		msg= sc.next();
		return msg;
	}
	
	void loadFromFile(ClientDAO cDAO, AccountDAO aDAO) {
		String clientData = loadData(clientFileName);
		String accountData = loadData(accountFileName);
		cDAO.addDatasFromFile(clientData);
		aDAO.addDatasFromFile(accountData);
	}
	
	String loadData(String fileName) {
		String data = "";
		try(FileReader fr = new FileReader(CUR_PATH + fileName); BufferedReader br = new BufferedReader(fr)){
			while(true) {
				String line = br.readLine();
				if(line == null) {
					break;
				}
				data+=line;
				data+="\n";
			}
			data= data.substring(0,data.length()-1);
			System.out.println("로드완료");
		} catch (FileNotFoundException e) {
			System.out.println("로드실패");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	void save(String fileName, String data) {
		try(FileWriter fw = new FileWriter(CUR_PATH+fileName)){
			fw.write(data);
			System.out.println("저장성공");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void saveToFile(ClientDAO cDAO, AccountDAO aDAO) {
		String clientData = cDAO.saveAsFileData();
		String accountData = aDAO.saveAsFileData();
		
		save(clientFileName,clientData);
		save(accountFileName, accountData);
		
	}


void tempData(ClientDAO cDAO, AccountDAO aDAO) {
	String userdata = "1001/test01/pw1/김철수\n";
	userdata += "1002/test02/pw2/이영희\n";
	userdata += "1003/test03/pw3/신민수\n";
	userdata += "1004/test04/pw4/최상민";
	
	cDAO.addDatasFromFile(userdata);
	
	String accountdata = "test01/1111-1111-1111/8000\n";
	accountdata += "test02/2222-2222-2222/5000\n";
	accountdata += "test01/3333-3333-3333/11000\n";
	accountdata += "test03/4444-4444-4444/9000\n";
	accountdata += "test01/5555-5555-5555/5400\n";
	accountdata += "test02/6666-6666-6666/1000\n";
	accountdata += "test03/7777-7777-7777/1000\n";
	accountdata += "test04/8888-8888-8888/1000";
	
	aDAO.addDatasFromFile(accountdata);
	System.out.println("로드완료");
	
}
}
