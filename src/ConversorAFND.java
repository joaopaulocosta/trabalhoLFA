import java.util.ArrayList;

public class ConversorAFND {
	private Automato automato;
	
	public ConversorAFND(Automato automato){
		this.automato = automato;
	}
	
	
	//funcao recursiva que retorna o feixoLambida de um estado 
	public void  gerarFechoLambida(Estado estado, ArrayList<Estado> fecho){
		
		//verifica se estado ja esta no feixo
		if(!fecho.contains(estado)){
			
			if(estado.getTransicao(".") == null){	//verifica se estado tem transição lambida, caso não retorna apenas um estado no feixo
				fecho.add(estado);	//adiciona estado ao feixo lambida
			}
			else{
				fecho.add(estado);	//adiciona proprio estado no feixo
				Transicao transicaoLambida = estado.getTransicao(".");	//pega a transicao lambida do estado
				ArrayList<Estado> estadosTransicaoLambida = transicaoLambida.getEstadosSaida(); //recebe a lista de estados de saida
				for(Estado aux : estadosTransicaoLambida){	//para cada estado de saida chama a funcao recursivamente
					this.gerarFechoLambida(aux, fecho);	//chama função recursiva
				}
			}
		}
		
		
	}
	
	
	public void mapeiaLambida(){
		
		
		//percorrendo cada estado do automato
		for(Estado estadoAtual : automato.getEstados()){
			
			ArrayList<Estado> fechoLambida = new ArrayList<Estado>();
			
			this.gerarFechoLambida(estadoAtual, fechoLambida);
			fechoLambida.sort((Estado estado1, Estado estado2) -> estado1.getNome().compareTo(estado2.getNome()));
			//imprime fecho lambida
			
			/*System.out.print("Fecho: "+ estadoAtual.getNome() + " {");
			for(Estado aux: fechoLambida){
				System.out.print( aux.getNome());
			}
			System.out.println("}"); */
			
			//percorrer todos os estados do fecho lambida
			for(Estado estadoDoFecho: fechoLambida){
				//percorre todas as transicoes do estado do fecho
				for(Transicao transicoesEstadoDoFecho : estadoDoFecho.getTransicoes()){
					
					String letra =  transicoesEstadoDoFecho.getLetra();
					Transicao novaTransicao = new Transicao(letra);
					
					if(!letra.equals(".")){
						//pega lista de estados finais das transições
						
						for(Estado listaEstadosFinais: transicoesEstadoDoFecho.getEstadosSaida() ){
						
							//pesquisa novo fecho
							ArrayList<Estado> fechoDoFecho = new ArrayList<Estado>();
							this.gerarFechoLambida(listaEstadosFinais, fechoDoFecho);			
							for(Estado aux: fechoDoFecho){
								novaTransicao.addEstadoSaida(aux);
							}						
						}

					}
					if(estadoAtual.getTransicao(letra) == null){
						estadoAtual.addTransicao(novaTransicao);
					}
					else{

						for(Estado aux : novaTransicao.getEstadosSaida() ){
							estadoAtual.getTransicao(letra).addEstadoSaida(aux);
						}

						//estadoAtual.getTransicao(letra).getEstadosSaida().addAll(novaTransicao.getEstadosSaida());
					}
					transicoesEstadoDoFecho.getEstadosSaida().sort((Estado estado1, Estado estado2) -> estado1.comparaIndice(estado2.getIndiceOrdenacao()));
					
				}
			}
		} 
	}
}
