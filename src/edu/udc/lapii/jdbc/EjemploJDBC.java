package edu.udc.lapii.jdbc;

import java.nio.charset.CodingErrorAction;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EjemploJDBC {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Class.forName("org.postgresql.Driver");
		
		Connection conn = DriverManager.getConnection(
				"jdbc:postgresql://127.0.0.1:5432/ventas", "postgres", "postgres");
		
		
		
		System.out.println(conn.getClass().getCanonicalName());
		
		Statement stmt = conn.createStatement();
		
		
		
		
		
		String sql = 
				" SELECT total, vendedor " +
					    " FROM venta " +
					    " WHERE vendedor = '" + args[0] + "' LIMIT 5";
		
		System.out.println(sql);
		
		ResultSet rs1 = stmt.executeQuery(sql);
		
		System.out.println("toral      | vendedor");
		while(rs1.next()){
			double total = rs1.getDouble("total");
			String codVendedor = rs1.getString("vendedor");
			
			System.out.println(total + "    | " +  codVendedor);
		}
		
		
		
		PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM vendedor WHERE codigo = ? ");
		pstmt.setString(1, args[0]);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()){
			System.out.print( rs.getString("codigo") );
			System.out.print("->");
			System.out.println( rs.getString("nombre") );
		}
		
		
		for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
			System.out.println( rs.getMetaData().getColumnName(i) + "  " +rs.getMetaData().getColumnTypeName(i)); 
		}
		
		
		rs.close();
		pstmt.close();
		
		
		
		PreparedStatement pstmtUpate = conn.prepareStatement(
				"UPDATE vendedor SET nombre = 'GOMES, LUCHO' WHERE codigo = ? ");
		pstmtUpate.setString(1, "GLU");
		
		
		int cant = pstmtUpate.executeUpdate();
		System.out.println("Se actualizaron " + cant + " filas" );
		
		
		
		
		
		conn.close();
		
		
		
	}
}
