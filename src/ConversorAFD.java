import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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
				listaEstados.sort((Estado estado1, Estado estado2) -> estado1.getNome().compareTo(estado2.getNome()));
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
		// cria a lista que ir� capturar o fecho do estado inicial, para criar o novo estado inicial
		ArrayList<Estado> novoEstadoInicial = new ArrayList<Estado>();
		// criado apenas para termos acesso ao metodo gerador de fecho
		ConversorAFND conversorAux = new ConversorAFND(automato);
		// criar o novo conjunto de estados iniciais baseado no fecho lambida
		conversorAux.gerarFechoLambida(estadoInicial, novoEstadoInicial);
		
		novoEstadoInicial.sort((Estado estado1, Estado estado2) -> estado1.getNome().compareTo(estado2.getNome()));
		
		estadoInicial = geraEstado(novoEstadoInicial);
		automatoAux.addEstado( estadoInicial);
		
		boolean validador = false;
		
		Queue<Estado> listaEstados = new LinkedList<Estado>();
		listaEstados.add(estadoInicial);
		
		//procura novos estados
		while(!listaEstados.isEmpty()){
			//percorre as transicoes do estado verificado
			for(Transicao transicaoAux : listaEstados.poll().getTransicoes()){
				String nomeEstado = "";
				if(!transicaoAux.getLetra().equals(".")){
					transicaoAux.getEstadosSaida().sort((Estado estado1, Estado estado2) -> estado1.getNome().compareTo(estado2.getNome()));
					//concatena estados da transicao para verificacao de nome
					for(Estado estadosTransicao: transicaoAux.getEstadosSaida()){
						nomeEstado += estadosTransicao.getNome() + ",";
					}
					
					//verifica se existe algum estado com o nome
					
					Estado novoEstado = automatoAux.getEstado(nomeEstado);
					if(novoEstado == null){
						novoEstado = geraEstado( transicaoAux.getEstadosSaida() );
						automatoAux.addEstado(novoEstado);
						listaEstados.add(novoEstado);
					}
					
					
				}
			
			}
		}
		System.out.println("");
		return automatoAux;
	}
}