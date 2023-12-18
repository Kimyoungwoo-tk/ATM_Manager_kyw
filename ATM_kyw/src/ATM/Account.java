package ATM;

public class Account {
  String clientId;
  String accNumber;
  int money;
Account(String clientId, String accNumber, int money) {
	this.clientId = clientId;
	this.accNumber = accNumber;
	this.money = money;
}
@Override
public String toString() {
	return clientId + "\t" + accNumber +  "\t" + money;
}

String saverToData() {
	return "%s/%s/%d\n".formatted(clientId,accNumber,money);
}

}
