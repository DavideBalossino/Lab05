package it.polito.tdp.anagrammi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnagrammaDAO {

	public boolean isCorrect(String p) {
		String sql="SELECT * "
				+ "FROM parola "
				+ "WHERE nome=?";
		try {
			Connection conn=DBConnect.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			st.setString(1, p);
			ResultSet rs=st.executeQuery();
			
			if(rs.next()) {
				rs.close();
				st.close();
				conn.close();
				return true;
			}
			
			rs.close();
			st.close();
			conn.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return false;
	}
}
