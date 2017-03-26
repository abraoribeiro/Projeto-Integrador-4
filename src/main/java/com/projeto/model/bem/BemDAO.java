package com.projeto.model.bem;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.projeto.util.ConnectionFactory;



public class BemDAO extends ConnectionFactory {
	
	List<Bem> lst = new ArrayList<Bem>();
	Bem b = new Bem();
	PreparedStatement p = null;
	ResultSet r = null;
	Connection c = null;
	
	public void insert(Bem b) {
		String sql = "insert into bem(nome, tipo, vidaUtil, data_Aquisicao, taxa, ativo, tempo_uso, da, vc, valor_compra, turnos, data_hoje, valor_residual, valor_venda, novo_usado) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		c = openConnection();
		p = null;
		try {
			p = c.prepareStatement(sql);
			p.setString(1, b.getNome());
			p.setString(2, b.getTipo());
			p.setDouble(3, b.getVidaUtil());
			p.setDate(4, new Date(b.getData_aquisicao().getTime()));;
			p.setDouble(5, b.getTaxa());
			p.setBoolean(6, true);
			p.setDouble(7, b.getTempo_uso());
			p.setDouble(8, b.getDa());
			p.setDouble(9, b.getVc());
			p.setDouble(10, b.getValor_compra());
			p.setInt(11, b.getTurnos());
			p.setDate(12, (Date) b.getData_hoje());
			p.setDouble(13, b.getValor_residual());
			p.setDouble(14, b.getValor_venda());
			p.setString(15, "Em Uso");
			p.executeUpdate();
		} catch (SQLException e) {
			System.err.println("----------------------------------------------------------------------------------");
			System.err.println("Erro no insert");
			e.printStackTrace();
		} finally {
			closeConnection(c, p, null);
		}
	}
	
	
	public List<Bem> select(){
		lst = new ArrayList<Bem>();
		String sql = "select id, nome, tipo, vidaUtil, data_Aquisicao, ativo, tempo_uso, valor_compra, da, vc, data_hoje,  valor_residual, novo_usado from bem where ativo= ?;";
		c = openConnection();
		try {
			p = c.prepareStatement(sql);
			p.setBoolean(1, true);
			r = p.executeQuery();
			b = new Bem();
			while(r.next()){
				b = new Bem();
				b.setId(r.getInt("id"));
				b.setNome(r.getString("nome"));
				b.setTipo(r.getString("tipo"));
				b.setVidaUtil(r.getDouble("vidaUtil"));
				b.setData_aquisicao(r.getDate("data_Aquisicao"));
				b.setAtivo(r.getBoolean("ativo"));
				b.setTempo_uso(r.getDouble("tempo_uso"));
				b.setValor_compra(r.getDouble("valor_compra"));
				b.setDa(r.getDouble("da"));
				b.setVc(r.getDouble("vc"));
				b.setData_hoje(r.getDate("data_hoje"));
				b.setValor_residual(r.getDouble("valor_residual"));
				b.setNovo_usado(r.getString("novo_usado"));
				lst.add(b);
			}
		} catch (SQLException e) {
			System.err.println("-------------------------------------");
			e.printStackTrace();
		}finally{
			closeConnection(c, p, r);
		}
		return lst;
	}
	
	
	public void delete(Bem b) {
		System.out.println("entrou");
		String sql = "delete from bem where id= ?;";
		c = openConnection();
		try {
			p = c.prepareStatement(sql);
			p.setInt(1, b.getId());
			p.executeUpdate();
		} catch (SQLException e) {
			System.err.println("----------------------------------------------------------------------------------");
			System.err.println("Erro no delete");
			e.printStackTrace();
		} finally {
			closeConnection(c, p, null);
		}
	}
	
	public Bem selectId(Bem b){
		lst = new ArrayList<Bem>();
		String sql = "select id, nome, tipo, vidaUtil, data_Aquisicao, ativo, tempo_uso, valor_compra, da, vc, valor_residual, novo_usado from bem where id= ?;";
		c = openConnection();
		try {
			p = c.prepareStatement(sql);
			p.setInt(1, b.getId());
			r = p.executeQuery();
			b = new Bem();
			while(r.next()){
				b = new Bem();
				b.setId(r.getInt("id"));
				b.setNome(r.getString("nome"));
				b.setTipo(r.getString("tipo"));
				b.setVidaUtil(r.getDouble("vidaUtil"));
				b.setData_aquisicao(r.getDate("data_Aquisicao"));
				b.setAtivo(r.getBoolean("ativo"));
				b.setTempo_uso(r.getDouble("tempo_uso"));
				b.setValor_compra(r.getDouble("valor_compra"));
				b.setDa(r.getDouble("da"));
				b.setVc(r.getDouble("vc"));
				b.setNovo_usado(r.getString("novo_usado"));
				b.setValor_residual(r.getDouble("valor_residual"));
				lst.add(b);
			}
		} catch (SQLException e) {
			System.err.println("-------------------------------------");
			System.err.println("Erro no select ID");
			e.printStackTrace();
		}finally{
			closeConnection(c, p, r);
		}
		return b;
	}
	
	
	public void update(Bem b) {
		String sql = "update bem set nome=?, tipo=?, vidaUtil=?, data_Aquisicao=?, taxa=?, ativo=?, tempo_uso=?, valor_compra=?, valor_residual=? where id=?;";
		c = openConnection();
		p = null;
		try {
			p = c.prepareStatement(sql);
			p.setString(1, b.getNome());
			p.setString(2, b.getTipo());
			p.setDouble(3, b.getVidaUtil());
			p.setDate(4,  new java.sql.Date(b.getData_aquisicao().getTime()));
			p.setDouble(5, b.getTaxa());
			p.setBoolean(6, true);
			p.setDouble(7, b.getTempo_uso());
			p.setDouble(8, b.getValor_compra());
			p.setDouble(9, b.getValor_residual());
			p.setInt(10, b.getId());
			p.executeUpdate();
		} catch (SQLException e) {
			System.err.println("----------------------------------------------------------------------------------");
			System.err.println("Erro no update");
			e.printStackTrace();
		} finally {
			closeConnection(c, p, null);
		}
	}
	public void updateStatus(Bem b) {
		String sql = "update bem set novo_usado=?, data_hoje=? where id=?;";
		c = openConnection();
		p = null;
		try {
			p = c.prepareStatement(sql);
			p.setString(1, b.getNovo_usado());
			p.setDate(2, (Date) b.getData_hoje());
			p.setInt(3, b.getId());
			p.executeUpdate();
		} catch (SQLException e) {
			System.err.println("----------------------------------------------------------------------------------");
			System.err.println("Erro no updateStatus");
			e.printStackTrace();
		} finally {
			closeConnection(c, p, null);
		}
	}
}
