package negocio;

import java.sql.SQLException;
import java.util.List;

import dados.Assunto;
import dados.Sinal;
import dados.Usuario;
import excessoes.DeleteException;
import excessoes.InsertException;
import excessoes.SelectException;
import excessoes.SenhaIncorretaException;
import excessoes.UsuarioInvalidoException;
import persistencia.AssuntoDAO;
import persistencia.SinalDAO;
import persistencia.UsuarioDAO;

public class Gerenciador {

	private UsuarioDAO usuarioDAO; 
	private SinalDAO sinalDAO;
	private AssuntoDAO assuntoDAO;

	private Usuario u;
	
	public Gerenciador() {
		try {
			usuarioDAO = UsuarioDAO.getInstance();
			sinalDAO = SinalDAO.getInstance();
			assuntoDAO = AssuntoDAO.getInstance();

		} catch (ClassNotFoundException e1) {
			System.out.println(e1.getMessage());
		} catch (SQLException e2) {
			System.out.println(e2.getMessage());
		} catch (SelectException e3) {
			System.out.println(e3.getMessage());
		}
	}
	
	public Usuario getU () {
		return this.u;
	}
	
	public void logarUsuario (String nome, String senha) throws UsuarioInvalidoException, SenhaIncorretaException, SelectException {
		Usuario usuario = null;
		
		for (Usuario i: getUsuarios()) {
			if (i.getNome().equals(nome)) {
				usuario = i;
			}
		}
		if( usuario == null) 
			throw new UsuarioInvalidoException("Usuário não encontrado.");
	
		if (usuario.getSenha().equals(senha)) {
			this.u = usuario;
		}
		else 
			throw new SenhaIncorretaException("Senha incorreta.");
	}
	
	public void sairUsuario () {
		this.u = null;
	}
	
	public List<Usuario> getUsuarios () throws SelectException{
		return usuarioDAO.selectAll();
	}
	
	public void cadastrarUsuario (Usuario usuario) throws InsertException, SelectException {
		usuarioDAO.insert(usuario);
	}
	
	public void excluirUsuario (Usuario usuario) throws DeleteException {
		usuarioDAO.delete(usuario);
	}
	
	public List<Sinal> getSinais () throws SelectException{
		return sinalDAO.selectAll();
	}
	
	public void cadastrarSinal (Sinal sinal) throws InsertException, SelectException {
		sinalDAO.insert(sinal);
	}
	
	public void excluirSinal (Sinal sinal) throws DeleteException {
		sinalDAO.delete(sinal);
	}
	
	public List<Assunto> getAssuntos () throws SelectException{
		return assuntoDAO.selectAll();
	}
	
	public void cadastrarAssunto (Assunto assunto) throws InsertException, SelectException {
		assuntoDAO.insert(assunto);
	}
	
	public void excluirAssunto (Assunto assunto) throws DeleteException {
		assuntoDAO.delete(assunto);
	}
	
	public List<Sinal> FiltrarAssunto (int assunto_id) throws SelectException {
		return assuntoDAO.selectSinais(assunto_id);
	}
}