package com.projeto.model.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.projeto.util.ConnectionFactory;


public class UsuarioDAO extends ConnectionFactory {

	public void insert(Usuario u) {
		String sql = "insert into usuario(nome, senha, tipo) values(?,?,?);";
		Connection c = openConnection();
		PreparedStatement p = null;
		try {
			p = c.prepareStatement(sql);
			p.setString(1, u.getNome());
			p.setString(2, u.getSenha());
			p.setString(3, u.getTipo());
			p.executeUpdate();
		} catch (SQLException e) {
			System.err.println("----------------------------------------------------------------------------------");
			System.err.println("Erro no insert");
			e.printStackTrace();
		} finally {

			closeConnection(c, p, null);
		}
	}

	public Boolean select(Usuario u) {
		String sql = "select nome, senha, tipo from usuario where nome=? and senha=?;";
		Connection c = openConnection();
		PreparedStatement ps = null;
		ResultSet r = null;
		try {
			ps = c.prepareStatement(sql);
			ps.setString(1, u.getNome());
			ps.setString(2, u.getSenha());
			r = ps.executeQuery();
			while (r.next()) {
				if (r.getString("nome") != null && r.getString("senha") != null) {
					u.setTipo(r.getString("tipo"));
					return true;
				} else {
					return false;
				}
			}
			return null;
		} catch (SQLException e) {
			System.err.println("-------------------------------------------------------------------");
			System.err.println("Erro no select");
			e.printStackTrace();
			return false;
		} finally {
			closeConnection(c, ps, r);
		}
	}
}
