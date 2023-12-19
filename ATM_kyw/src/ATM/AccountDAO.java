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
	
	boolean ckAccValue(String data) {
		if(data.length() != 14) {
			return false;
		}
		for(int i =0; i<data.length(); i+=1) {
			if(i!=4 &&i!= 9 && data.charAt(i)!='-') {				
			}else if(i ==4 ||i ==9) {
				if(data.charAt(i)!= '-') {
					return false;
				}
			}else {
				return false;
			}
		}
		return true;
	}
	
	String getAccValue() {
		while(true) {
			String accNum = sc.getValue("계좌");
			if(!ckAccValue(accNum)) {
				System.out.println("올바른 계좌 형태가 아닙니다");
				continue;
			}
			return accNum;
		}
	}
	
	void addClinetAcc(Client client) {
		Account[] list = getAccListOneClient(client.id);
		if(list.length ==3) {
			System.out.println("최대 3개만 생성 가능");
			return;
		}
		String acc = getAccValue();
		if(getAccbyNum(acc)!=null) {
			System.out.println("이미 존재하는 계좌번호 있음");
			return;
		}
		addAccount(new Account(client.id,acc,0));
		System.out.println(acc);
		System.out.println("추가완료");
	}
	
	Account getAccbyNum(String accNum) {
		for(Account acc: accList) {
			if(acc.accNumber.equals(accNum)) {
				return acc;
			}
		}
		return null;
	}
	
	Account getLogAcc(String id) {
		Account[] list = getAccListOneClient(id);
		if(list.length==0) {
			System.out.println("계좌가 존재하지 않습니다");
			return null;
		}
		while(true) {
			String accNum = getAccValue();
			Account acc = getAccbyNum(accNum);
			if(acc == null) {
				System.out.println("해당 계좌번호가 없음");
				continue;
			}
			return acc;
		}
	}
	
	void delClientAcc(Client client) {
		Account acc= getLogAcc(client.id);
		removeAcc(getAccIdxByAcc(acc));
		System.out.println("삭제 완료");
	}
	
	void depositMoney(Client client) {
		Account acc= getLogAcc(client.id);
		if(acc == null) return;
		int money = sc.getValue("입금", 100, 100000);
		acc.money = money;
		System.out.println(acc);
		System.out.println("입금완료");
	}
	
	void withdrawMoney(Client client) {
		Account acc = getLogAcc(client.id);
		if(acc ==null) return;
		int money = sc.getValue("출금", 100, acc.money);
		if(acc.money-money<0) {
			System.out.println("잔액이 부족합니다");
			return;
		}
		acc.money -= money;
		System.out.println(acc);
		System.out.println("출금완료");
	}
	
	void transferMoney(Client client) {
		Account myacc= getLogAcc(client.id);
		if(myacc ==null) return;
		Account youracc = getAccbyNum(getAccValue());
		if(youracc == null) {
			System.out.println("존재하지 않는 계좌번호");
			return;
		}
		if(myacc == youracc) {
			System.out.println("본인계좌 이체 불가");
			return;
		}
		int money = sc.getValue("이체", 100, myacc.money);
		if(myacc.money-money<0) {
			System.out.println("잔액부족");
			return;
		}
		myacc.money -= money;
		youracc.money +=money;
		System.out.println("이체완료");
		
	}
	
	void printMypage(Client client) {
		Account[] list = getAccListOneClient(client.id);
		for(Account acc : list) {
			System.out.println(acc);
		}
		
	}
	
}
