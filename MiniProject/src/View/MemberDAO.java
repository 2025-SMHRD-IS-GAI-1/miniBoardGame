package View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Model.MemberVO;

public class MemberDAO {

	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;

	// 드라이버
	private void getConn() {

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@project-db-cgi.smhrd.com:1524:xe";
			String user = "CGI_25IS_GA_P1_5";
			String password = "smhrd5";
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 자원 반납
	private void getClose() {

		try {
			if (rs != null) {
				rs.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 회원가입
	public int register(MemberVO mvo) {
		int row = 0;

		try {
			getConn();

			String sql = "INSERT INTO MEMBER(NICKNAME, POINT) VALUES(?,?)";

			psmt = conn.prepareStatement(sql);

			psmt.setString(1, mvo.getNickname());
			psmt.setInt(2, mvo.getPoint());

			row = psmt.executeUpdate(); // => 실행만
			conn.commit();
		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			getClose();
		}
		return row;
	}

	// 점수반영 시키는거..
	
	public int updatePoint(MemberVO member) {
		int row = 0;
		try {
			getConn();
			String sql = "UPDATE MEMBER SET POINT = POINT + ? WHERE NICKNAME = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, 10);
			psmt.setString(2, member.getNickname());
			row = psmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}

		return row;

	}

	
	// 특정 닉네임 회원 조회
	
	public MemberVO searchNickname(String nickname) {
	    MemberVO member = null;
	    try {
	        getConn();
	        String sql = "SELECT NICKNAME, POINT FROM MEMBER WHERE NICKNAME = ?";
	        psmt = conn.prepareStatement(sql);
	        psmt.setString(1, nickname);
	        rs = psmt.executeQuery();

	        if (rs.next()) {
	            String nick = rs.getString("NICKNAME");
	            int point = rs.getInt("POINT");
	            member = new MemberVO(nick, point);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        getClose();
	    }
	    return member;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 랭킹조회
	
	public List<MemberVO> rankedSelectAll() {
		List<MemberVO> list = new ArrayList<>();

		try {
			getConn();

			String sql = "SELECT NICKNAME, POINT FROM MEMBER ORDER BY POINT DESC";

			psmt = conn.prepareStatement(sql);

			ResultSet rs = psmt.executeQuery(); // => 실행만

			while (rs.next()) {
				String nickname = rs.getString("NICKNAME");
				int point = rs.getInt("POINT");
				list.add(new MemberVO(nickname, point));
			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			getClose();
		}
		return list;
	}

}
