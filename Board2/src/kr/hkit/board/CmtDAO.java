package kr.hkit.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
	
	public static List<CmtVO> selectCmt(String i_board) {
		int intIBoard = Integer.parseInt(i_board);
		List<CmtVO> list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select i_cmt, cmt, rdate from t_board_cmt  where i_board = ? ";
		
		try {
			con = BoardDAO.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, intIBoard);
			rs = ps.executeQuery();			
			while(rs.next()) {
				CmtVO vo = new CmtVO();
				int i_cmt = rs.getInt("i_cmt");
				String cmt = rs.getString("cmt");
				String rdate = rs.getString("rdate");
				vo.setI_cmt(i_cmt);
				vo.setCmt(cmt);
				vo.setRdate(rdate);
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BoardDAO.close(con, ps, rs);
		}
		
		return list;
	}
}










