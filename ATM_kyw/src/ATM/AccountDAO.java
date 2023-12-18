package ATM;

public class AccountDAO {
	Util sc;
	int cnt;
	Account[] accList;
	AccountDAO(){
		sc = new Util();
	}
	
	
	void addDatasFromFile(String data) {
		String[] temp = data.split("\n");
		accList = new Account[temp.length];
		for(int i = 0; i < temp.length; i+=1) {
			String[] info = temp[i].split("/");
			Account Account = new Account(info[0],info[1],Integer.parseInt(info[2]));
			addAccount(Account);
		}
	}
	
	void addAccount(Account Account) {
		if(cnt ==0) {
			accList = new Account[cnt +1];
		}else {
			Account[] temp = accList;
			accList = new Account[cnt+1];
			for(int i = 0; i<cnt; i+=1) {
				accList[i] = temp[i];
			}
		}
		accList[cnt] = Account;
		cnt+=1;
	}
	
	void delAllAccOneClient(Client client) {
		Account[] list = getAccListOneClient(client.id);
		for(Account acc : list) {
			int idx = getAccIdxByAcc(acc);
			removeAcc(idx);
		}
	}
	int getAccIdxByAcc(Account acc) {
		for(int i = 0; i<cnt ; i+=1) {
			if( acc == accList[i]) {
				return i;
			}
		}
		return -1;
	}
	
	Account[] getAccListOneClient(String id) {
		if(this.cnt ==0) return null;
		int cnt = 0;
		for(Account acc : accList) {
			if(acc.clientId.equals(id)) {
				cnt+=1;
			}
		}
		Account[] list = new Account[cnt];
		int idx =0;
		for(Account acc: accList) {
			if(acc.clientId.equals(id)) {
				list[idx++] = acc;
			}
		}
		return list;
	}
	
	void removeAcc(int delIdx) {
		if(cnt ==1) {
			accList = null;
			return;
		}
		Account [] temp = accList;
		accList = new Account[cnt-1];
		int idx =0;
		for(int i =0; i<cnt; i+=1) {
			if(delIdx != i) {
				accList[idx++] = temp[i];
			}
		}
		cnt-=1;
}
	String saveAsFileData() {
		if(cnt ==0) {
			return "";
		}
		String data = ""; 
		for(Account acc : accList) {
			data+= acc.saverToData();
		}
		return data;
	}
	
}
