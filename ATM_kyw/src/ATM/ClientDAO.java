package ATM;

public class ClientDAO {
	Util sc;
	Client[] cList;
	int cnt;
	int maxNo;
	Client log;
	ClientDAO(){
		sc = new Util();
		maxNo = 100; 
	}
	
	void addClient(Client client) {
		if(cnt == 0) {
			cList = new Client[cnt+1];
		}else {
			Client[] temp = cList;
			cList = new Client[cnt+1];
			for( int i =0; i<cnt; i+=1) {
				cList[i] = temp[i];
			}
		}
		cList[cnt] = client;
		cnt+=1;
	}
	
	void addDatasFromFile(String data) {
		String[] temp = data.split("\n");
		cList = new Client[temp.length];
		for(int i = 0; i< temp.length; i+=1) {
			String[] info = temp[i].split("/");
			Client client = new Client(Integer.parseInt(info[0]),info[1],info[2],info[3]);
			addClient(client);
		}
	}
	
	void printAllCilent() {
		if(cnt == 0 ) return;
		for(Client client : cList) {
			System.out.println(client);
		}
	}
	
	boolean hasData() {
		if(cnt == 0) {
			System.out.println("데이터 없음");
			return false;
		}
		return true;
	}
	
	void showAllClient() {
		if(!hasData()) return;
		for(Client c : cList) {
			System.out.println(c.toString());
		}
		System.out.println("================");
	}
	
	void upDateClient() {
		if(!hasData()) return;
		String id = sc.getValue("아이디");
		Client c = getClientById(id);
		if(c== null) {
			System.out.println("아이디가 없음");
			return;
		}
		
		c.pw = sc.getValue("변경할 pw: " );
		c.name = sc.getValue("변경할이름: ");
		System.out.println("변경완료");
	}
	
	int getIdx(Client c) {
		for(int i = 0 ; i<cnt ; i+=1) {
			if(c == cList[i]) {
				return i;
			}
		}
		return -1;
	}
	Client getClientById(String id) {
		if(cnt ==0) return null;
		for(Client c : cList) {
			if(c.id.equals(id)) {
				return c;
			}
		}
		return null;
	}
	
	void removeClient(int delIdx) {
		if(cnt ==1) {
			cList = null;
			return;
		}
		
		Client[] temp = cList;
		cList = new Client[cnt -1];
		int idx = 0;
		for( int i =0; i<cnt; i+=1) {
			if(delIdx !=i) {
				cList[idx++] = temp[i];
			}
		}
		cnt-=1;
	}
	
	void delClient(AccountDAO aDAO) {
		if(!hasData()) return;
		String id = sc.getValue("아이디");
		Client c = getClientById(id);
		if(c == null) {
			System.out.println("아이디가 존재하지 않음");
			return;
		}
		int delIdx = getIdx(c);
		aDAO.delAllAccOneClient(c);
		removeClient(delIdx);
		System.out.println("삭제 완료");
	}
	
	
	String saveAsFileData() {
		if(cnt ==0) {
			return"";
		}
		String data = "";
		for(Client c :cList) {
			data+= c.saveToData();
		}
		return data;
	}
	
	
	
	
	
	
	
}
