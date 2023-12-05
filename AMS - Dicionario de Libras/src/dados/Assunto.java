package dados;

import java.util.ArrayList;

public class Assunto {
	
	private int id;
	private String assunto;
	private ArrayList<Sinal> sinais;
	public int getId() {
		
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public ArrayList<Sinal> getSinais() {
		return sinais;
	}
	public void setSinais(ArrayList<Sinal> sinais) {
		this.sinais = sinais;
	}
}
