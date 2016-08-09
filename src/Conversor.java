import java.io.File;
import java.io.Reader;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.StringTokenizer;
import java.util.ArrayList;




public class Conversor {
	
	
	//funcao que l� o conte�do do arquivo e salva em um StringBuilder
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
	
		//procurando o primeiro conjunto de chaves que s�o os estados
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
		
		//procurando o segundo conjunto de chaves que � o alfabeto
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
		
		
		//procurando o terceiro conjunto de chaves s�o as transi��es
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
			
			transicoes = new StringTokenizer(conteudoArquivo.substring(indiceInicial, indiceFinal), "},");
			
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
		
			
		//procurando o quarto conjunto de chaves s�o os estados finais
			while(conteudoArquivo.charAt(indiceInicial) != '{'){
				indiceInicial++;
				indiceFinal++;
			}
			
			while(conteudoArquivo.charAt(indiceFinal) != '}'){
				indiceFinal++;
			}
			
			indiceInicial++;
			
			/*criando arrayList de estadosFinais para facilitar a adi��o no objeto automato e n�o necessitar duplica��o
			de objetos */
			String [] auxEstadosFinais = conteudoArquivo.substring(indiceInicial, indiceFinal).split(",");
			for(String aux: auxEstadosFinais){
				estadosFinais.add(aux);
			}
			
			
			
			
		//Adicionando os estados
			while(estados.hasMoreTokens()){
				Estado novoEstado = new Estado(estados.nextToken());
				
				//Adiciona estado inicial
				if(novoEstado.getNome().equals(estadoInicial.trim())){ //fun��o trim remove espa�os no inicio e final da string
					automato.addEstadoInicial(novoEstado);
				}
				
				//Adiciona estado a lista de estados finais caso seja um estado final
				if(estadosFinais.contains(novoEstado.getNome())){
					automato.addEstadoFinal(novoEstado);
				}
				
				automato.addEstado(novoEstado);
			}
			
		//Adicionando as letras do alfabeto
			while(estados.hasMoreTokens()){
				String novaLetra = new String(estados.nextToken());
				automato.addLetra(novaLetra);
			}
			
		
		//Falta adicionar transi�oes
			
	
		
		return automato;
	}
	
	
	
	
	public static void main(String[] args){
		//verifica se a entrada � v�lida
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
