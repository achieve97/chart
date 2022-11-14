package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import DTO.Emp;



public class EmpDao {

	DataSource ds = null;

	public EmpDao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	}
	

	/*
	 * // 전체 조회 public List<Emp> getAvgSalList() {
	 * 
	 * ResultSet rs = null; PreparedStatement pstmt = null; List<Emp> emplist = new
	 * ArrayList<Emp>();
	 * 
	 * try {
	 * 
	 * String sql = "select job, avg(sal) from emp group by job";
	 * 
	 * pstmt = conn.prepareStatement(sql); rs = pstmt.executeQuery();
	 * 
	 * while (rs.next()) { Emp emp = new Emp(); emp.setJob(rs.getString("job"));
	 * emp.setSal(rs.getInt("sal")); emplist.add(emp); } } catch (Exception e) {
	 * System.out.println(e.getMessage()); } finally { SingletonHelper.close(rs);
	 * SingletonHelper.close(pstmt); } return emplist; }
	 */
	// 조건 조회
	public ArrayList<Emp> getAvgSalList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Emp> emp = null;

		try {
			conn = ds.getConnection();
			String sql = "select job, round(avg(sal)) as sal from emp group by job";
			
			pstmt = conn.prepareStatement(sql);
			
		
			rs = pstmt.executeQuery();
			emp = new ArrayList<Emp>();
			while (rs.next()) {
				Emp emp1 = new Emp();
				emp1.setJob(rs.getString("job"));
				emp1.setAvgSal(rs.getInt("sal"));
				emp.add(emp1);
			}
			
		}catch (Exception e) {
			System.out.println("오류 :" + e.getMessage());
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();//반환
			} catch (Exception e2) {
				
			}
		}
			
		return emp;
	}
	

}
