import java.util.ArrayList;
import java.util.Collections;

public class ConversorAFD {
	
	private Automato automato;
	
	public ConversorAFD(Automato automato){
		this.automato = automato;
	}
	
	
	public Estado geraEstado( ArrayList<Estado> listaEstados){
		// novo estado temporario
				Estado estadoAux = new Estado("novoEstado");
				
				for(Estado estado: listaEstados){
					
					for(Transicao transicaoEstadoInicial : estado.getTransicoes()){
						
						// verifico se o estado auxiliar possui a atual transicao 
						if(estadoAux.getTransicao(transicaoEstadoInicial.getLetra()) == null){
							estadoAux.addTransicao(transicaoEstadoInicial);		
						}
						// cria a saida para a corrente transicao no novo estado inicial
						for(Estado aux: transicaoEstadoInicial.getEstadosSaida()){
							estadoAux.getTransicao(transicaoEstadoInicial.getLetra()).addEstadoSaida(aux);
						}

					}
				}
				
				String novoNome = "";
				for(Estado aux: listaEstados){
					novoNome += aux.getNome() + ",";
					
				}
								
				estadoAux.setNome(novoNome);
				
				return estadoAux;
	}
	
	
	public Automato Conversion(){
		
		// adiciona esse novo estado ao novo automato
		// automato auxiliar
		Automato automatoAux = new Automato();
		
		//adicionando alfabeto
		for(String letra: automato.getAlfabeto()){
			automatoAux.addLetra(letra);
		}
		
		// armazena o estado inicial. 
		Estado estadoInicial = automato.getEstadoInicial();
		// cria a lista que irá capturar o fecho do estado inicial, para criar o novo estado inicial
		ArrayList<Estado> novoEstadoInicial = new ArrayList<Estado>();
		// criado apenas para termos acesso ao metodo gerador de fecho
		ConversorAFND conversorAux = new ConversorAFND(automato);
		// criar o novo conjunto de estados iniciais baseado no fecho lambida
		conversorAux.gerarFechoLambida(estadoInicial, novoEstadoInicial);

		estadoInicial = geraEstado(novoEstadoInicial);
		automatoAux.addEstado( estadoInicial);
		
		boolean validador = false;

		while(validador == false){
			for(Transicao transicaoAux : estadoInicial.getTransicoes()){
				
			}
			validador = true;
		}
		

		System.out.println("");
		
		
		return automatoAux;
	}
}