package Model;

public class MemberVO {

	private String nickname; 
	private int point;
	
	

	
	
	
	
	public MemberVO(String nickname,int point) {
		this.nickname = nickname;
		this.point = point;
	}
	
	public MemberVO(String nickname) {
		this.nickname = nickname;
		this.point = 0;
	}

	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
