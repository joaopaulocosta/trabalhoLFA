import java.util.ArrayList;

public class Estado {
	private String nome;
	private ArrayList<Transicao> listaTransicoes;
	private int indiceOrdenacao;
	
	public Estado(String nome){
		this.nome = nome;
		listaTransicoes = new ArrayList<Transicao>();
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public void setIndiceOrdenacao(int indice){
		this.indiceOrdenacao = indice;
	}
	
	//fun��o que adiciona transi�ao no estado, caso a transi��o ja exista ele concatena a lista de estados de sa�da 
	//verificando se existe estados repetidos
	public void addTransicao(Transicao transicao){
		//se transicao n�o existe adiciona normalmente
		if(this.getTransicao(transicao.getLetra()) == null){
			this.listaTransicoes.add(transicao);
		}
		else{	//se transicao ja existe deve ser feita a concatena��o verificando os estados repetidos
			
			ArrayList<Estado> estadosSaidaAtual = this.getTransicao(transicao.getLetra()).getEstadosSaida();
			
			//percorrendo lista de estados de saida da transicao
			for(Estado estadoTransicao : transicao.getEstadosSaida()){
				
				//verifica se estado ja esta contido na transi��o do estado atual
				if(!estadosSaidaAtual.contains(estadoTransicao)){
					this.getTransicao(transicao.getLetra()).addEstadoSaida(estadoTransicao);
				}
			}
		}
	}
	
	//retorna transicao de acordo com letra
	public Transicao getTransicao(String letra){
		for(Transicao aux: this.listaTransicoes){
			if(aux.getLetra().equals(letra))
				return aux;
		}
		return null;
	}
	
	//retorna string com as transicoes no formato exigido
	public ArrayList<Transicao> getTransicoes(){
		/*String transicoes =  new String("");
		for(Transicao aux: this.listaTransicoes){
			transicoes += "("+ this.getNome() + "," + aux.getLetra() + ") ->{" + aux.listaEstadosSaida() + "}\n" ;	
		} */
		
		return this.listaTransicoes;
	}
	
	
}
