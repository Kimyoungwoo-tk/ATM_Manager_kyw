package ATM;
//[1] 관리자 [2] 사용자 [0]종료
//관리자
//[1] 회원목록 [2] 회원수정 [3]회원삭제 [4]데이터 저장 [5] 데이터 불러오기
// 회원수정 : 회원 아이디 검색 >비밀번호, 이름 수정가능
// 회원 삭제 : 회원 아이디 검색
//데이터 저장 : account.txt , client.txt

//사용자 메뉴
//[1] 회원가입 [2] 로그인 [0] 뒤로가기
//회원가입 : 회원 아이디 중복 확인

// 로그인 메뉴
//[1]계좌추가 [2]계좌삭제 [3]입금 [4]출금 [5]이체 [6] 탈퇴 [7] 마이페이지 [0]로그아웃
// 계좌 추가 숫자 4개-숫자4개-숫자4개 일치할때 추가 가능
//계좌 삭제 : 본인 회원 계좌만 가능

//입금 : accList  계좌가 있을 때만 입금 가능 : 100원이상 입금/ 이체/ 출금 : 계좌 잔고만큼만
//이체 : 이체할 계좌랑 이체받을 계좌가 일치 안하면 가능
//탈퇴 : 패스워드 다시 입력 받아서 탈퇴

//마이페이지 : 내 계좌 목록(+잔고) 확인

public class BankController {
	AccountDAO aDAO;
	 ClientDAO cDAO;
	 Util util;
	final String bankName = "우리은행";
	
	BankController(){
		aDAO=new AccountDAO();
		cDAO=new ClientDAO();
		util=new Util();
		
		mainMenu();
	}
	
	void mainMenu() {
		while(true) {
			System.out.println("["+bankName+"ATM]");
			System.out.println("1.관리자");
			System.out.println("2.사용자");
			System.out.println("0.종료");
			int sel = util.getValue("메뉴", 0, 2);
			if(sel == 0) {
				System.out.println("종료");
				return;
			}else if(sel ==1) {
				adminMenu();
			}else if (sel ==2) {
				userMenu();
			}
			
		}
	}
	
	void adminMenu() {
		while(true) {
			System.out.println(" ==관리자 메뉴==");
			System.out.println("1. 회원목록");
			System.out.println("2. 회원정보 수정");
			System.out.println("3. 회원정보 삭제");
			System.out.println("4. 데이터 저장");
			System.out.println("5. 데이터 불러오기");
			System.out.println("0. 뒤로가기");
			int sel = util.getValue("",0,5);
			if(sel ==0) {
				System.out.println("뒤로가기");
				return;
			}else if(sel ==1) {
				System.out.println("회원목록");
				cDAO.showAllClient();
			}else if(sel ==2) {
				System.out.println("회원정보 수정");
				cDAO.upDateClient();
			}else if(sel == 3) {
				System.out.println("회원정보 삭제");
				cDAO.delClient(aDAO);
			}else if(sel == 4) {
				System.out.println("데이터 저장");
				util.loadFromFile(cDAO, aDAO);
			}else if(sel ==5) {
				System.out.println("데이터 불러오기");
				util.loadFromFile(cDAO, aDAO);
			}
			
		}
	}
	
	void userMenu() {
		while(true) {
			System.out.println("==사용자메뉴==");
			System.out.println("1.회원가입");
			System.out.println("2. 로그인");
			System.out.println("0.뒤로가기");
			int sel = util.getValue("", 0, 2);
			if(sel ==0) {
				System.out.println("뒤로가기");
				return;
			}else if(sel ==1) {
				System.out.println("회원가입");
				cDAO.joinClient();
			}else if(sel ==2) {
				System.out.println("로그인");
				if(cDAO.loginClient()) {
					loginUserMenu();
				}
			}
		}
	}
	
	void loginUserMenu() {
		while(true) {
			System.out.println("1.계좌 추가");
			System.out.println("2.계좌 삭제");
			System.out.println("3.입금");
			System.out.println("4.출금");
			System.out.println("5.이체");
			System.out.println("6.탈퇴");
			System.out.println("7.마이페이지");
			System.out.println("0.로그아웃");
			int sel = util.getValue("", 0, 7);
			if(sel ==0) {
				cDAO.log = null;
				return;
			}else if(sel ==1) {
				System.out.println("계좌추가");
				aDAO.addClinetAcc(cDAO.log);
			}else if(sel ==2) {
				System.out.println("계좌삭제");
				aDAO.delClientAcc(cDAO.log);
			}else if(sel ==3) {
				System.out.println("입금");
				aDAO.depositMoney(cDAO.log);
			}else if(sel ==4) {
				System.out.println("출금");
				aDAO.withdrawMoney(cDAO.log);
			}else if(sel ==5) {
				System.out.println("이체");
				aDAO.transferMoney(cDAO.log);
			}else if(sel ==6) {
				System.out.println("탈퇴");
				if(cDAO.deleteLogClient(aDAO)) return;
			}else if(sel ==7) {
				System.out.println("마이페이지");
				aDAO.printMypage(cDAO.log);
			}
		}
	}
	
	
	
}
