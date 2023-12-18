package ATM;

public class BankController {
	
	final String bankName = "우리은행";
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
}