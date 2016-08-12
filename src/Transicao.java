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
		this.estadosSaida.add(estado);
	}
	
	
}
