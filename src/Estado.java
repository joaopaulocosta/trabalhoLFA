import java.util.ArrayList;

public class Estado {
	private String nome;
	private ArrayList<Transicao> listaTransicoes;
	
	public Estado(String nome){
		this.nome = nome;
		listaTransicoes = new ArrayList<Transicao>();
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public void addTransicao(Transicao transicao){
		this.listaTransicoes.add(transicao);
	}
	
	
}
