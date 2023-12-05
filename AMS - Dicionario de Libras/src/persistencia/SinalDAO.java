package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dados.Sinal;
import excessoes.DeleteException;
import excessoes.InsertException;
import excessoes.SelectException;

public class SinalDAO  {
	
		private static SinalDAO instance = null;
		private PreparedStatement select;
		private PreparedStatement insert;
		private PreparedStatement delete;
		private PreparedStatement selectAll;
		
		public static SinalDAO getInstance() throws ClassNotFoundException, SQLException, SelectException {
		if (instance == null)
			instance = new SinalDAO();
			return instance;
		}
		
		private SinalDAO() throws ClassNotFoundException , SQLException , SelectException {
			Connection conn = DatabaseConnection.getConnection();
			select = conn.prepareStatement("select * from sinais where sinal_id = ? ");
			selectAll = conn.prepareStatement("select * from sinais");
			insert = conn.prepareStatement("insert into public.sinais" + "(assunto_id, exemplo, exemplo_libras) values (?, ?, ?)");
			delete = conn.prepareStatement("delete from sinais where sinal_id = ?");
		}


		public Sinal select (int code) throws SelectException {
			ResultSet rs;
			Sinal us = null;
			try {
				select.setInt(1, code);
				rs = select.executeQuery();
				if (rs.next()) {
					us = new Sinal();
					us.setId(rs.getInt("sinal_id"));
					us.setAssuntoId(rs.getInt("nome"));
					us.setExemplo(rs.getString("email"));
					us.setExemploLibras(rs.getString("senha"));

				}
			} catch (SQLException e) {
				throw new SelectException("Erro ao buscar Sinal");
			}
			return us;
		}
		
		public void insert (Sinal us) throws InsertException, SelectException{
			try {
				insert.setInt(1, us.getAssuntoId());
				insert.setString(2, us.getExemplo());
				insert.setString(3, us.getExemploLibras());
				insert.executeUpdate();
			} catch (SQLException e) {
				throw new InsertException("Erro ao inserir Sinal");
			}
		}
		
		public void delete (Sinal us) throws DeleteException {
			try {
				delete.setInt(1, us.getId());
				delete.executeUpdate();
			} catch (SQLException e) {
				throw new DeleteException("Erro ao deletar sinal");
			}
		} 
		
		public List<Sinal> selectAll () throws SelectException {
			List<Sinal> us = new ArrayList<Sinal>();
			Sinal u;
			
			try {
				ResultSet rs = selectAll.executeQuery();
				while(rs.next()) {
					u = new Sinal();
					u.setId(rs.getInt(1));
					u.setAssuntoId(rs.getInt(2));
					u.setExemplo(rs.getString(3));
					u.setExemploLibras(rs.getString(4));
					
					us.add(u);
				}
			} catch (SQLException e) {
				throw new SelectException("Erro ao buscar todos os sinais");
			}
			return us;
		}
		
}