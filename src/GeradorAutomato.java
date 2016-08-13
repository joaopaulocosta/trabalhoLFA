import java.io.File;
import java.io.Reader;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.StringTokenizer;
import java.util.ArrayList;




public class GeradorAutomato {
	
	
//Funções de leitura de arquivo--------------------------------------------------------------------------------------------------	
	
	//funcao que lê o conteúdo do arquivo e salva em um StringBuilder
	public StringBuilder lerArquivo(String nomeArquivo){
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
	

//Funções de extração de dados do arquivo--------------------------------------------------------------------------------------------------
	
	
	//funcao que extrai os estados do automato dentro da StringBuilder
	public StringTokenizer  extraiEstados(StringBuilder conteudoArquivo){
		StringTokenizer estados;
		int indiceInicial = 0, indiceFinal = 0;
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
		
		return estados;
		
	}
	
	//funcao que extrai o alfabeto usado no automato, dentro da StringBuilder
	public  StringTokenizer extraiAlfabeto(StringBuilder conteudoArquivo){
		StringTokenizer alfabeto;
		int indiceInicial = 0, indiceFinal = 0;
		
		//procurando o segundo conjunto de chaves que é o alfabeto
			while(conteudoArquivo.charAt(indiceInicial) != '{'){
				indiceInicial++;
				indiceFinal++;
			}
		
			indiceInicial++;
			indiceFinal++;
			
			while(conteudoArquivo.charAt(indiceInicial) != '{'){
				indiceInicial++;
				indiceFinal++;
			}
		
		while(conteudoArquivo.charAt(indiceFinal) != '}'){
			indiceFinal++;
		}
		
		indiceInicial++;
		
		alfabeto = new StringTokenizer(conteudoArquivo.substring(indiceInicial, indiceFinal), ",");
		
		return alfabeto;
	}
	
	//funcao que extrai as transições 
	public  StringTokenizer extraiTransicoes(StringBuilder conteudoArquivo){
		
		StringTokenizer transicoes;
		int indiceInicial = 0, indiceFinal = 0;
		
		//procurando o terceiro conjunto de chaves que são as transições
			while(conteudoArquivo.charAt(indiceInicial) != '{'){	//estados
				indiceInicial++;
				indiceFinal++;
			}
			indiceInicial++;
			while(conteudoArquivo.charAt(indiceInicial) != '{'){	//alfabeto
				indiceInicial++;
				indiceFinal++;
			}
			indiceInicial++;
			while(conteudoArquivo.charAt(indiceInicial) != '{'){
				indiceInicial++;
				indiceFinal++;
			}
			indiceInicial++;
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
		return transicoes;
	}
	
	//funcao que extrai o estado inicial
	public  String extraiEstadoInicial(StringBuilder conteudoArquivo){
		String estadoInicial;
		int indiceInicial = 0, indiceFinal = 0;
		//procurando o estado inicial
			while(conteudoArquivo.charAt(indiceInicial) != '{'){	//estados
				indiceInicial++;
				indiceFinal++;
			}
			indiceInicial++;
			while(conteudoArquivo.charAt(indiceInicial) != '{'){	//alfabeto
				indiceInicial++;
				indiceFinal++;
			}
			indiceInicial++;
			while(conteudoArquivo.charAt(indiceInicial) != '{'){	//Entra transicoes
				indiceInicial++;
				indiceFinal++;
			}
			indiceInicial++;
			do{									
				
				while(conteudoArquivo.charAt(indiceFinal) != '}'){
					indiceFinal++;
					
				}
				
				indiceFinal++;
			
			}while(conteudoArquivo.charAt(indiceFinal) == ',');		//sai transicoes
			
			indiceInicial = indiceFinal;
			
			while(conteudoArquivo.charAt(indiceInicial) != ','){
				indiceInicial++;
				indiceFinal++;
			}
			indiceInicial++;
			indiceFinal++;
			
			while(conteudoArquivo.charAt(indiceFinal) != ','){
				indiceFinal++;
			}
		
			
		estadoInicial = conteudoArquivo.substring(indiceInicial , indiceFinal);
		return estadoInicial.trim();
	
	}
	
	//funcao que extrai os estados finais
	public  ArrayList<String> extraiEstadosFinais(StringBuilder conteudoArquivo){
		int indiceInicial = 0, indiceFinal = 0;
		ArrayList<String> estadosFinais = new ArrayList<String>();
		
		//procurando estados finais
			while(conteudoArquivo.charAt(indiceInicial) != '{'){	//estados
				indiceInicial++;
				indiceFinal++;
			}
			indiceInicial++;
			while(conteudoArquivo.charAt(indiceInicial) != '{'){	//alfabeto
				indiceInicial++;
				indiceFinal++;
			}
			indiceInicial++;
			while(conteudoArquivo.charAt(indiceInicial) != '{'){	//Entra transicoes
				indiceInicial++;
				indiceFinal++;
			}
			indiceInicial++;
			do{									
				
				while(conteudoArquivo.charAt(indiceFinal) != '}'){
					indiceFinal++;
					
				}
				
				indiceFinal++;
			
			}while(conteudoArquivo.charAt(indiceFinal) == ',');		//sai transicoes
			
			indiceInicial = indiceFinal;
			
			while(conteudoArquivo.charAt(indiceInicial) != ','){
				indiceInicial++;
				indiceFinal++;
			}
			indiceInicial++;
			indiceFinal++;
			
			while(conteudoArquivo.charAt(indiceFinal) != ','){
				indiceFinal++;
			}
		
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
		
		return estadosFinais;
	}
	

//Funções de criação de automato--------------------------------------------------------------------------------------------------
	
	//funcao que cria os estados ap partir das strings passadas e adiciona esses estados ao automato 
	public  void addEstados(Automato automato, StringTokenizer estados, String estadoInicial, ArrayList<String> estadosFinais){
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
	}
	
	/*funcao que cria os objetos transições, vincula cada transição ao seu respectivo estado e adiciona os estados
	 *	ao automato 
	 **/
	public  void addTransicoes(Automato automato, StringTokenizer transicoes, StringTokenizer estados){
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
	}
	
	//Função que adiciona as letras ao automato formando o alfabeto
	public  void addAlfabeto(Automato automato, StringTokenizer alfabeto){
		//Adicionando as letras do alfabeto
		while(alfabeto.hasMoreTokens()){
			String novaLetra = new String(alfabeto.nextToken());
			automato.addLetra(novaLetra);
		}
	}
	
	//funcao que cria um objeto automato de acordo com conteudo lido do arquivo de entrada
	public  Automato criarAutomato(StringBuilder conteudoArquivo){
		
		Automato automato = new Automato();	//objeto que sera preenchido com dados lidos no arquivo de entrada
		
		StringTokenizer estados, alfabeto, transicoes;
		ArrayList<String> estadosFinais;
		String estadoInicial;						
													// cada barra é a divisão dos tokens
		estados = extraiEstados(conteudoArquivo);	//stringtokenizer contendos os estados  q0/q1/q2
		
		alfabeto = extraiAlfabeto(conteudoArquivo);	//stringtokenizer contendos os estados  a/b/c
		
		transicoes = extraiTransicoes(conteudoArquivo);	/*stringtokenizer contendos os estados  (q0,a)->{q0,q1,q2/ ,
																								(q1,b)->{q1/ ,
																								(q2,c)->{q2/ ,
																								(q2,.)->{q1/  */
		
		estadoInicial = extraiEstadoInicial(conteudoArquivo);	//String com estado inicial q0
		
		estadosFinais = extraiEstadosFinais(conteudoArquivo);	//ArrayList com os stados finais q1
		
		
		/*Apos separação dos elementos: estados, alfabeto, transições, estado inicial e estados finais
		 * sera criado os objetos necessarios automato, estados e transições
		 */
		
		addEstados(automato, estados, estadoInicial, estadosFinais); 
		addTransicoes(automato, transicoes, estados);
		addAlfabeto(automato, alfabeto);
		return automato;
	}
	

//Construtor--------------------------------------------------------------------------------------------------	
	
	private Automato automato;
	
	public GeradorAutomato(String arquivo){
		StringBuilder conteudoArquivo = lerArquivo(arquivo);
		this.automato = criarAutomato(conteudoArquivo);
	}
	
	public Automato getAutomato(){
		return this.automato;
	}
	
}
