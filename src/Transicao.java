import java.util.ArrayList;

public class Transicao {
	private String letra;

	private ArrayList<Estado> estadosSaida;
	
	public Transicao(){
		this.letra = null;
		this.estadosSaida = new ArrayList<Estado>();		//lista de estados que serão alcançados pelo transição com letra
	}
	
	public Transicao(String letra){
		this.letra = letra;
		this.estadosSaida = new ArrayList<Estado>();		//lista de estados que serão alcançados pelo transição com letra
	}
	
	public String getLetra(){
		return this.letra;
	}
	
	
	public void setLetra(String letra){
		this.letra = letra;
	}
	
	public String  listaEstadosSaida(){
		String estados = new String("");
		for(Estado aux: this.estadosSaida){
			estados += aux.getNome() + ",";
		}
		return estados.substring(0, estados.length() - 1);
	}
	
	
	public void addEstadoSaida(Estado estado){
		//se lista de saida ainda não contem estado, adiciona
		if(!this.estadosSaida.contains(estado))
			this.estadosSaida.add(estado);

	}
	

	
	//funcao retorna string com estados da lista de estados de saida 
	public String stringEstados(){
		this.getEstadosSaida().sort((Estado estado1, Estado estado2) -> estado1.comparaIndice(estado2.getIndiceOrdenacao()));
		String estados = new String("");
		int cont = 0;
		for(Estado aux : this.estadosSaida){
			estados += aux.getNome();
			if(cont < this.estadosSaida.size() - 1){	//retira a ultima virgula q0,q1,q2 
				estados += ",";
			}
			cont++;
		}
		return estados;
	}
	
	public ArrayList<Estado> getEstadosSaida(){
		return this.estadosSaida;
	}
	
}
