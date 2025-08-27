package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CapitalDAO {

	Scanner sc = new Scanner(System.in);

	private PreparedStatement psmt = null;
	private Connection conn = null;
	private ResultSet rs = null;

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
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public CapitalQuizVO randomQuiz() {
	    List<CapitalQuizVO> list = new ArrayList<>();
	    String sql = "SELECT CAPITAL_NUM, QUESTION, ANSWER, QUESTIONTIME FROM CAPITAL_QUIZ";

	    try {
	        getConn();
	        psmt = conn.prepareStatement(sql);
	        rs = psmt.executeQuery();

	        while (rs.next()) {
	            CapitalQuizVO vo = new CapitalQuizVO();
	            vo.setCapitalNum(rs.getInt("CAPITAL_NUM"));  // 문제 번호
	            vo.setQuestion(rs.getString("QUESTION"));    // 문제
	            vo.setAnswer(rs.getString("ANSWER"));        // 정답
	            vo.setTime(rs.getInt("QUESTIONTIME"));       // 제한시간
	            list.add(vo);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        getClose();
	    }

	    if (list.isEmpty()) return null;

	    // 랜덤 인덱스 뽑아서 1문제 반환
	    Random r = new Random();
	    int idx = r.nextInt(list.size());
	    return list.get(idx);
	}

	// 사용자 입력과 정답 비교
	public boolean checkAnswer(CapitalQuizVO quiz, String userInput) {
	    if (quiz == null) return false;
	    return quiz.getAnswer().equals(userInput); // 정확히 일치해야 정답
	}
}
