package Model;

import java.util.ArrayList;
import java.util.List;

import View.MemberDAO;

public class TeamService {

	
	private Team teamA = new TeamA();
	private Team teamB = new TeamB();
	private ArrayList<String> list = new ArrayList<>();
	private MemberDAO dao;
	
	
	public TeamService(MemberDAO dao) {
		this.dao = dao;
	}
	
	
	
	public boolean chkTeam(MemberVO member) {
		for(MemberVO m : teamA.list) {
			if(m.getNickname().equals(member.getNickname())) {
				System.out.println("'"+member.getNickname()+"'님은 A팀에 이미 포함되어있습니다");
				return true;
			}
		}
			
		for(MemberVO m : teamB.list) {
			if(m.getNickname().equals(member.getNickname())) {
				System.out.println("'"+member.getNickname()+"'님은 B팀에 이미 포함되어있습니다");
				return true;
			}
		}
		return false;
	}
	
	public boolean addTeam(MemberVO member, int teamNumber) {
		
        if (teamNumber == 1) {
            if(!teamA.list.contains(member.getNickname())) {
            	   teamA.add(member);
            }
            return true;         
        } else {
            if(!teamB.list.contains(member.getNickname())) {
         	   teamB.add(member);
         	   return true;
            }
            
        }
        return false;
    }

	
	
	public ArrayList<MemberVO> getTeamA() { 
		return teamA.list; }
    public ArrayList<MemberVO> getTeamB() { 
    	return teamB.list; }
	
	

 // 게임 시작 가능 여부
    public boolean canStartGame() {
    	if(teamA.list.size()> 0 && teamB.list.size() > 0) {
    		if(Math.abs(teamA.list.size() - teamB.list.size()) == 0) {
    			return true;
    		}
    	}
		return false;
        
    }
}

    
 
    

