import java.io.File;
import java.io.Reader;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.StringTokenizer;
import java.util.ArrayList;




public class Conversor {
	
	
	//funcao que lê o conteúdo do arquivo e salva em um StringBuilder
	public static StringBuilder lerArquivo(String nomeArquivo){
		StringBuilder conteudoArquivo = new StringBuilder();
		
		//leitura do arquivo de entrada
				try{
					
					File arquivo = new File(nomeArquivo);
					Reader in = new FileReader( arquivo);
					LineNumberReader reader = new LineNumberReader(in);
					while(reader.ready()){
						conteudoArquivo.append(reader.readLine());
					}
					reader.close();
					in.close();
				
				//tratamento de escessoes
				} catch (java.io.FileNotFoundException e){
					System.out.println("Arq. nao existe. Causa: " + e.getMessage());
				} catch (java.io.IOException e) {
					System.out.println( "Erro de E/S. Causa: " + e.getMessage() );
				}
		
		return conteudoArquivo;
	}
	
	
	//funcao que cria um objeto automato de acordo com conteudo lido do arquivo de entrada
	public static Automato criarAutomato(StringBuilder conteudoArquivo){
		Automato automato = new Automato();
		
		
		int indiceInicial = 0, indiceFinal = 0;
		StringTokenizer estados, alfabeto, transicoes;
		ArrayList<String> estadosFinais = new ArrayList<String>();
		String estadoInicial;
	
		//procurando o primeiro conjunto de chaves que são os estados
			while(conteudoArquivo.charAt(indiceInicial) != '{'){
				indiceInicial++;
				indiceFinal++;
			}
			
			while(conteudoArquivo.charAt(indiceFinal) != '}'){
				indiceFinal++;
			}
			
			indiceInicial++;
			
			estados = new StringTokenizer(conteudoArquivo.substring(indiceInicial, indiceFinal), ",");
			
			indiceInicial = indiceFinal;
		
		//procurando o segundo conjunto de chaves que é o alfabeto
			while(conteudoArquivo.charAt(indiceInicial) != '{'){
				indiceInicial++;
				indiceFinal++;
			}
			
			while(conteudoArquivo.charAt(indiceFinal) != '}'){
				indiceFinal++;
			}
			
			indiceInicial++;
			
			alfabeto = new StringTokenizer(conteudoArquivo.substring(indiceInicial, indiceFinal), ",");
			
			indiceInicial = indiceFinal;
		
		
		//procurando o terceiro conjunto de chaves que são as transições
			while(conteudoArquivo.charAt(indiceInicial) != '{'){
				indiceInicial++;
				indiceFinal++;
			}
			
			//gambiarra pra percorrer conjunto de chaves { {} {} {} }
			do{									
			
				while(conteudoArquivo.charAt(indiceFinal) != '}'){
					indiceFinal++;
				}
				
				indiceFinal++;
			
			}while(conteudoArquivo.charAt(indiceFinal) == ',');
			
			indiceFinal ++;
			indiceInicial++;
			
			transicoes = new StringTokenizer(conteudoArquivo.substring(indiceInicial, indiceFinal), "}");
			
			indiceInicial = indiceFinal;
				
		//procurando o estado inicial
			while(conteudoArquivo.charAt(indiceInicial) != ','){
				indiceInicial++;
				indiceFinal++;
			}
			
			indiceFinal++;
			
			while(conteudoArquivo.charAt(indiceFinal) != ','){
				indiceFinal++;
			}
			
			
			estadoInicial = conteudoArquivo.substring(indiceInicial + 1, indiceFinal);
			
			indiceInicial = indiceFinal;
		
			
		//procurando o quarto conjunto de chaves são os estados finais
			while(conteudoArquivo.charAt(indiceInicial) != '{'){
				indiceInicial++;
				indiceFinal++;
			}
			
			while(conteudoArquivo.charAt(indiceFinal) != '}'){
				indiceFinal++;
			}
			
			indiceInicial++;
			
			/*criando arrayList de estadosFinais para facilitar a adição no objeto automato e não necessitar duplicação
			de objetos */
			String [] auxEstadosFinais = conteudoArquivo.substring(indiceInicial, indiceFinal).split(",");
			for(String aux: auxEstadosFinais){
				estadosFinais.add(aux);
			}
			
			
		
		
		/*Apos separação dos elementos estados, alfabeto, transições, estado inicial e estados finais
		 * sera criado os objetos necessarios automato, estados e transições
		 */
			
			
		//Adicionando os estados ao objeto Automato
			while(estados.hasMoreTokens()){
				Estado novoEstado = new Estado(estados.nextToken());
				
				//Adiciona estado inicial
				if(novoEstado.getNome().equals(estadoInicial.trim())){ //função trim remove espaços no inicio e final da string
					automato.addEstadoInicial(novoEstado);
				}
				
				//Adiciona estado a lista de estados finais caso seja um estado final
				if(estadosFinais.contains(novoEstado.getNome())){
					automato.addEstadoFinal(novoEstado);
				}
				
				automato.addEstado(novoEstado);
			}
	
		//Adicionando objetos Transições
			while(transicoes.hasMoreTokens()){
				String transicaoAtual = new String(transicoes.nextToken());
				int tIndiceInicial = 0, tIndiceFinal = 0;	//indices usados para percorrer string transicao
				
				
				//pegando estado dono da transicao
				
					if(transicaoAtual.length() > 1){
							while(transicaoAtual.charAt(tIndiceInicial) != '('){
								
								tIndiceInicial++;
								tIndiceFinal++;
							}
							
							while(transicaoAtual.charAt(tIndiceFinal) != ','){
								tIndiceFinal++;
							}
										
					}
					else{
						break;	//para de processar as transições (ultima linha de transicao, normalmente vazia)
					}
				
				tIndiceInicial++;
					
				//pegando referencia do estado dono da transicao
				Estado estadoTransicao = automato.getEstado(transicaoAtual.substring(tIndiceInicial, tIndiceFinal));
				Transicao novaTransicao = new Transicao(); //criando nova transicao vazia
				
				
				
				tIndiceInicial = ++tIndiceFinal;
				
				//pegando letra de transicao
					
					if(transicaoAtual.length() > 5){						
							while(transicaoAtual.charAt(tIndiceFinal) != ')'){
								tIndiceFinal++;
							}
							
			
					}
					
				novaTransicao.setLetra(transicaoAtual.substring(tIndiceInicial, tIndiceFinal)); 
				
				tIndiceInicial = ++tIndiceFinal; 
				
				//pegando conjunto de estados da transicao
				
					while(transicaoAtual.charAt(tIndiceInicial) != '{'){
						tIndiceInicial++;
						tIndiceFinal++;
					}
					
					tIndiceInicial++;
					
					//separando os estados
					estados = new StringTokenizer(transicaoAtual.substring(tIndiceInicial, transicaoAtual.length()), ",");
					
					//System.out.println(transicaoAtual.substring(tIndiceInicial, transicaoAtual.length()).trim());
					
					while(estados.hasMoreElements()){
						Estado estadoSaida = automato.getEstado(estados.nextToken());	//referenciado cada estado pelo nome
						novaTransicao.addEstadoSaida(estadoSaida);	//adicionando estado em lista da transicao
					} 
				
				estadoTransicao.addTransicao(novaTransicao); //add transicao ao estado de origem
			}
		
			
			
		//Adicionando as letras do alfabeto
			while(alfabeto.hasMoreTokens()){
				String novaLetra = new String(alfabeto.nextToken());
				automato.addLetra(novaLetra);
			}
			
		
		//Falta adicionar transiçoes
			
	
		
		return automato;
	}
	
	
	
	
	public static void main(String[] args){
		//verifica se a entrada é válida
		/*if(args.length != 2){
			System.out.println("Formato de entrada invalida! O formato adequado "
					+ "deve conter um arquivo de entrada e outro de saida.");
			return;
		} */
		 
		StringBuilder conteudoArquivo = lerArquivo("desc_af1.txt");

		Automato automato = criarAutomato(conteudoArquivo);
		
		automato.imprimir();
		
		
		
	}
}
