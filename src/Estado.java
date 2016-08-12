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
	
	//retorna string com as transicoes no formato exigido
	public String getTransicoes(){
		String transicoes =  new String("");
		for(Transicao aux: this.listaTransicoes){
			transicoes += "("+ this.getNome() + "," + aux.getLetra() + ") ->{" + aux.listaEstadosSaida() + "}\n" ;	
		}
		
		return transicoes;
	}
	
}
