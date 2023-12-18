package ATM;

public class Client {
	int clientNo;
	String id;
	String pw;
	String name;
	Client(int clientNo, String id, String pw, String name) {
		this.clientNo = clientNo;
		this.id = id;
		this.pw = pw;
		this.name = name;
	}
	@Override
	public String toString() {
		return ""+clientNo+"\t" + id + "\t" + pw + "\t" + name;
	}
	
	String saveToData() {
		return "%s/%s/%s/%s\n".formatted(clientNo,id,pw,name);
	}
	
	
}
