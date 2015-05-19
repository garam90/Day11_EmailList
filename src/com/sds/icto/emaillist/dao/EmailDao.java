package com.sds.icto.emaillist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.sds.icto.emaillist.exception.UserDeleteException;
import com.sds.icto.emaillist.exception.UserInsertException;
import com.sds.icto.emaillist.vo.EmailVO;

@Repository
public class EmailDao {

	private Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		Connection conn = DriverManager.getConnection(url, "webdb", "webdb");
		return conn;
	}

	public void insert(EmailVO vo) {
		try{
			Connection conn = getConnection();
			PreparedStatement stmt = null;
			String sql = "insert into email_list values (email_list_no_seq.nextval, ?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, vo.getFirstName());
			stmt.setString(2, vo.getLastName());
			stmt.setString(3, vo.getEmail());
			stmt.executeUpdate();
			
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}catch(ClassNotFoundException | SQLException e){
			//Exception 처리 ( Log 필요 )
			throw new UserInsertException(e.getMessage());

		}
	}

	
	public ArrayList<EmailVO> selectList() {
		
		ArrayList<EmailVO> list = new ArrayList<EmailVO>();
		try{
			Connection conn = getConnection();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String sql = "select * from email_list";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				EmailVO vo = new EmailVO(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4));
				list.add(vo);
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			
		}catch(ClassNotFoundException | SQLException e){
			e.printStackTrace();
		}
		return list;
	}

	public void delete(Long id) throws SQLException, ClassNotFoundException{
		Connection conn = getConnection();
		PreparedStatement stmt = null;
		String sql = "delete from email_list where no = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setLong(1, id);
		stmt.executeUpdate();
		
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void delete(){
		try{
			Connection conn = getConnection();
			PreparedStatement stmt = null;
			String sql = "delete from email_list";
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
			
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}catch(ClassNotFoundException | SQLException e){
			throw new UserDeleteException(e.getMessage());
		}
	}
}
