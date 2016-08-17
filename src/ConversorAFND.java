import java.util.ArrayList;

public class ConversorAFND {
	private Automato automato;
	
	public ConversorAFND(Automato automato){
		this.automato = automato;
	}
	
	
	//funcao recursiva que retorna o feixoLambida de um estado 
	public void  gerarFechoLambida(Estado estado, ArrayList<Estado> feixo){
		
		//verifica se estado ja esta no feixo
		if(!feixo.contains(estado)){
			
			if(estado.getTransicao(".") == null){	//verifica se estado tem transição lambida, caso não retorna apenas um estado no feixo
				feixo.add(estado);	//adiciona estado ao feixo lambida
			}
			else{
				feixo.add(estado);	//adiciona proprio estado no feixo
				Transicao transicaoLambida = estado.getTransicao(".");	//pega a transicao lambida do estado
				ArrayList<Estado> estadosTransicaoLambida = transicaoLambida.getEstadosSaida(); //recebe a lista de estados de saida
				for(Estado aux : estadosTransicaoLambida){	//para cada estado de saida chama a funcao recursivamente
					this.gerarFechoLambida(aux, feixo);	//chama função recursiva
				}
			}
		}
		
		
	}
	
	
	public void mapeiaLambida(){
		
		
		//percorrendo cada estado do automato
		for(Estado estadoAtual : automato.getEstados()){
			
			ArrayList<Estado> feixoLambida = new ArrayList<Estado>();
			this.gerarFechoLambida(estadoAtual, feixoLambida);
			
			/*
			
			for(Estado aux: feixoLambida){
				System.out.print(aux.getNome());
			}
			System.out.println(""); */
			
			//verificando se existe transições para o proprio estado
			//percorre lista de transicoes do estado atual
			for(Transicao transicaoEstadoAtual: estadoAtual.getTransicoes()){
				
				//verifica se transicao contem estado de saida igual ao estado atual
				if(transicaoEstadoAtual.getEstadosSaida().contains(estadoAtual)){
					
					//percorre os estados presentes no feixo lambida
					for(Estado estadoFeixo: feixoLambida){
						//verifica se a lista de saidas da transicao atual ja contém ou não o estado referente ao estado atual
						if(!transicaoEstadoAtual.getEstadosSaida().contains(estadoFeixo)){
							transicaoEstadoAtual.addEstadoSaida(estadoFeixo);
						}
					}
					
				}		
			}	
			
			//percorrendo todos os estados do feixo
			for(Estado estadoFeicho: feixoLambida){
				
				//percorrendo todas as transicoes de cada estado dentro do feixo
				for(Transicao transicaoEstadoFecho: estadoFeicho.getTransicoes() ){
					
					//se transicao do estado do feixo não existe no estado atual ela é adicionada
					if(estadoAtual.getTransicao(transicaoEstadoFecho.getLetra()) == null){
						estadoAtual.addTransicao(transicaoEstadoFecho);
					}
					else{	//se transicao do estado do feixo existe no estado atual
						
						Transicao transicaoEstadoAtual = estadoAtual.getTransicao(transicaoEstadoFecho.getLetra());
						
						//percorre os estados de saida da transicao lambida de cada estado do feixo
						for(Estado saidaTransicaoEstadoFecho: transicaoEstadoFecho.getEstadosSaida()){
							
							//verifica se a lista de saidas para uma certa transicao, ja contem alguma saida igual da recebida do feixo
							if(!transicaoEstadoAtual.getEstadosSaida().contains(saidaTransicaoEstadoFecho)){				
								transicaoEstadoAtual.getEstadosSaida().add(saidaTransicaoEstadoFecho);
							}
						}
					}
				}	
			}
		} 
	}
}
