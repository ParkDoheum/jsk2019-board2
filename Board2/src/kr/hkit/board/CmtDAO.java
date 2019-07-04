package kr.hkit.board;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CmtDAO {
	//댓글 달기
	public static void insertCmt(CmtVO vo) {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " insert into t_board_cmt (i_cmt, i_board, cmt)"
				+ " values(seq_board_cmt.nextval, ?, ?) ";
		
		try {
			con = BoardDAO.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, vo.getI_board());
			ps.setString(2,  vo.getCmt());
			ps.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BoardDAO.close(con, ps, null);
		}		
	}
}
