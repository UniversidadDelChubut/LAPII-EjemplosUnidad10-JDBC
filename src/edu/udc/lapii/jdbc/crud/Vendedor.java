package edu.udc.lapii.jdbc.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class Vendedor {
	private String codigo;
	private String nombre;
	private int legajo;
	private double comision;
	
	public Vendedor() {
		
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getLegajo() {
		return legajo;
	}
	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}
	public double getComision() {
		return comision;
	}
	public void setComision(double comision) {
		this.comision = comision;
	}
	
	
	
	public Vendedor(String codigo) throws ObjetoInexistenteException, ModelException {
		Connection conn = null;
		try {
			conn = DataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					" SELECT codigo, legajo, nombre, comision " +
					" FROM vendedor " +
					" WHERE codigo = ?");
			pstmt.setString(1, codigo);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()){
				this.codigo = rs.getString("codigo");
				this.nombre = rs.getString("nombre");
				this.legajo = rs.getInt("legajo");
				this.comision = rs.getDouble("comision");
			} else {
				throw new ObjetoInexistenteException("No existe vendedor con codigo " + codigo);
			}
		} catch (SQLException e) {
			throw new ModelException("Error de acceso a datos", e);
		} finally {
			DataSource.closeConnection(conn);
		}
	}
	
	
	public void update() throws ModelException {
		Connection conn = null;
		try {
			conn = DataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					" UPDATE vendedor " +
					" SET legajo = ?, nombre = ?, comision = ? " +
					" WHERE codigo = ?");
			pstmt.setInt(1, this.legajo);
			pstmt.setString(2, this.nombre);
			pstmt.setDouble(3, this.comision);
			pstmt.setString(4, this.codigo);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ModelException("Error de acceso a datos", e);
		} finally {
 			DataSource.closeConnection(conn);
		}
		
	}
	
	public void insert() throws ModelException {
		Connection conn = null;
		try {
			conn = DataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					" INSERT INTO vendedor" +
					" (codigo, legajo, nombre, comision)" +
					" VALUES " +
					" (?, ?, ?, ?)");
			pstmt.setString(1, this.codigo);
			pstmt.setInt(2, this.legajo);
			pstmt.setString(3, this.nombre);
			pstmt.setDouble(4, this.comision);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ModelException("Error de acceso a datos", e);
		} finally {
 			DataSource.closeConnection(conn);
		}
	} 
	
	public static Collection<Vendedor> getVendedores() throws ModelException {
		Collection<Vendedor> vendedores = new LinkedList<Vendedor>();
		Connection conn = null;
		try {
			conn = DataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					" SELECT codigo, legajo, nombre, comision FROM vendedor ORDER BY nombre" );
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Vendedor v = new Vendedor();
				v.codigo = rs.getString("codigo");
				v.nombre = rs.getString("nombre");
				v.legajo = rs.getInt("legajo");
				v.comision = rs.getDouble("comision");
				vendedores.add(v);
			}
		} catch (SQLException e) {
			throw new ModelException("Error de acceso a datos", e);
		} finally {
 			DataSource.closeConnection(conn);
		}
		
		return vendedores;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Vendedor))
			return false;
		return this.getCodigo().equals(((Vendedor)obj).getCodigo());
	}
	
	@Override
	public int hashCode() {
		return this.codigo.hashCode();
	}
	
}
