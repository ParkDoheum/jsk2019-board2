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
		}

		return result;
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
}
