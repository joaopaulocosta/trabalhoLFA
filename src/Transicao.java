
public class Transicao {
	private String letra;
	private Estado estadoSaida;
	private Estado estadoEntrada;
	
	public Transicao(String letra, Estado estadoSaida, Estado estadoEntrada){
		this.letra = letra;
		this.estadoSaida = estadoSaida;
		this.estadoEntrada = estadoEntrada;
	}
	
	public String getNome(){
		return this.letra;
	}
	
	public Estado getEstadoSaida(){
		return this.estadoSaida;
	}
	
	public Estado getEstadoEntrada(){
		return this.estadoEntrada;
	}
}
