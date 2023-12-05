package dados;

public class Sinal {
	
	private int id;
	private int assunto_id;
	private String exemplo;
	private String exemplo_libras;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setAssuntoId(int assunto_id) {
		this.assunto_id = assunto_id;
	}
	public int getAssuntoId() {
		return assunto_id;
	}
	public String getExemplo() {
		return exemplo;
	}
	public void setExemplo(String exemplo) {
		this.exemplo = exemplo;
	}
	public String getExemploLibras() {
		return exemplo_libras;
	}
	public void setExemploLibras(String exemplo_libras) {
		this.exemplo_libras = exemplo_libras;
	}
}
