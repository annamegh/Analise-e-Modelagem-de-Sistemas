package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dados.Assunto;
import dados.Sinal;
import excessoes.DeleteException;
import excessoes.InsertException;
import excessoes.SelectException;

public class AssuntoDAO  {
	
		private static AssuntoDAO instance = null;
		private PreparedStatement select;
		private PreparedStatement insert;
		private PreparedStatement delete;
		private PreparedStatement selectAll;
		private PreparedStatement selectSinais;
		
		public static AssuntoDAO getInstance() throws ClassNotFoundException, SQLException, SelectException {
		if (instance == null)
			instance = new AssuntoDAO();
			return instance;
		}
		
		private AssuntoDAO() throws ClassNotFoundException , SQLException , SelectException {
			Connection conn = DatabaseConnection.getConnection();
			select = conn.prepareStatement("select * from assuntos where assunto_id = ? ");
			selectAll = conn.prepareStatement("select * from assuntos");
			selectSinais = conn.prepareStatement("select s.* from assuntos a join sinais s on s.assunto_id = a.assunto_id where assunto_id = ?");
			insert = conn.prepareStatement("insert into public.assuntos" + "(assunto_id, assunto) values (?, ?)");
			delete = conn.prepareStatement("delete from assuntos where assunto_id = ?");
		}


		public Assunto select (int code) throws SelectException {
			ResultSet rs;
			Assunto us = null;
			try {
				select.setInt(1, code);
				rs = select.executeQuery();
				if (rs.next()) {
					us = new Assunto();
					us.setId(rs.getInt("assunto_id"));
					us.setAssunto(rs.getString("assunto"));

				}
			} catch (SQLException e) {
				throw new SelectException("Erro ao buscar Assunto");
			}
			return us;
		}
		
		public void insert (Assunto us) throws InsertException, SelectException{
			try {
				insert.setString(1, us.getAssunto());
				insert.executeUpdate();
			} catch (SQLException e) {
				throw new InsertException("Erro ao inserir Assunto");
			}
		}
		
		public void delete (Assunto us) throws DeleteException {
			try {
				delete.setInt(1, us.getId());
				delete.executeUpdate();
			} catch (SQLException e) {
				throw new DeleteException("Erro ao deletar assunto");
			}
		} 
		
		public List<Assunto> selectAll () throws SelectException {
			List<Assunto> us = new ArrayList<Assunto>();
			Assunto u;
			
			try {
				ResultSet rs = selectAll.executeQuery();
				while(rs.next()) {
					u = new Assunto();
					u.setId(rs.getInt(1));
					u.setAssunto(rs.getString(2));
					
					us.add(u);
				}
			} catch (SQLException e) {
				throw new SelectException("Erro ao buscar todos os assuntos");
			}
			return us;
		}
		
		public List<Sinal> selectSinais (int code) throws SelectException {
			List<Sinal> us = new ArrayList<Sinal>();
			Sinal u;
			
			ResultSet rs;
			try {
				selectSinais.setInt(1, code);
				rs = selectSinais.executeQuery();
				while(rs.next()) {
					u = new Sinal();
					u.setId(rs.getInt(1));
					u.setAssuntoId(rs.getInt(2));
					u.setExemplo(rs.getString(3));
					u.setExemploLibras(rs.getString(4));
					
					us.add(u);
				}
			} catch (SQLException e) {
				throw new SelectException("Erro ao buscar os sinais correspondentes ao assunto");
			}
			return us;
		}
}