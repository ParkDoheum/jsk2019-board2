package kr.hkit.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
	private static Connection getCon() throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		final String user = "hr";
		final String pw = "hkit2019";
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe", user, pw);
		System.out.println("DB 연결!!");
		return con;
	}

	private static void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int insertBoard(BoardVO vo) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;

		String sql = " insert into t_board (i, title, content, pic, pw) " + " values (seq_board.nextval, ?, ?, ?, ?) ";

		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getContent());
			ps.setString(3, vo.getPic());
			ps.setString(4, vo.getPw());
			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
			return result;
		}
	}

	public static List<BoardVO> selectAll(String search) {
		List<BoardVO> list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = " SELECT * FROM t_board ";
		if (search != null) {
			sql += " where title like '%" + search + "%' ";
		}
		sql += " order by i desc ";

		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				int i = rs.getInt("i");
				String title = rs.getString("title");
				String rdate = rs.getString("rdate");

				BoardVO vo = new BoardVO();
				vo.setI(i);
				vo.setTitle(title);
				vo.setRdate(rdate);

				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}

		return list;
	}
	
	public static BoardVO getBoard(int i) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;		
		BoardVO vo = new BoardVO();		
		String sql = " SELECT * FROM t_board WHERE i = ? ";		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, i);			
			rs = ps.executeQuery();
			while(rs.next()) {
				String title = rs.getString("title");
				String content = rs.getString("content");
				String pic = rs.getString("pic");
				String rdate = rs.getString("rdate");
				
				vo.setI(i);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setPic(pic);
				vo.setRdate(rdate);				
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		
		return vo;
	}
	
	
	public static BoardVO getBoard(String i) {
		int intI = Integer.parseInt(i);		
		return getBoard(intI);
	}
	
	//true : 비밀번호 맞음, false:비밀번호 틀림
	public static boolean confirmPw(String pw, int i) {
		boolean result = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " SELECT i FROM t_board WHERE i = ? AND pw = ? ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, i);
			ps.setString(2, pw);
			
			result = ps.executeUpdate() == 1 ? true : false;			
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		return result;
	}
	
	//레코드 삭제
	public static void delBoard(int i) {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " delete from t_board where i = ?  ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, i);			
			ps.execute();
			
		} catch (Exception e) {		
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
	}
	
	public static int updateBoard(BoardVO vo) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " update t_board set title = ?, content = ?, pic = ?, pw = ?"
				+ " WHERE i = ? ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1,  vo.getTitle());
			ps.setString(2, vo.getContent());
			ps.setString(3,  vo.getPic());
			ps.setString(4,  vo.getPw());
			ps.setInt(5, vo.getI());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
			return result;
		}
	}
}







