import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GeradorSaida {
	

	
	public  GeradorSaida(){
	}
	
	public int maiorString(ArrayList<Estado> estados){
		int cont = 0;
		for(Estado aux : estados){
			for(Transicao transicaoAUX: aux.getTransicoes()){
				if(transicaoAUX.getEstadosSaida().size() > cont){
					cont = transicaoAUX.getEstadosSaida().size();
				}
			}
		}
		
		if(cont > 8)
			cont = (int)cont/2;
		
		return cont ;
	}

	//funcao que escreve no arquivo
	//1 Parametro: Texto
	//2 Flag, usa quebra de linha no final do texto caso seja > 0
	//3 Objeto PrintWriter que grava no arquivo
	public void imprimir( String texto, int flagQuebraLinha, PrintWriter gravarArq){
		gravarArq.printf("%s", texto);
		for(int i = texto.length(); i< 40; i++){
			gravarArq.printf(" ");
		}
		
		if(flagQuebraLinha > 0)
			gravarArq.printf("\r\n");
	}
	
	public void imprimirAutomato(Automato automato, int flag, String nomeArquivo){
		try{
			FileWriter arq = new FileWriter(nomeArquivo, true);
		    PrintWriter gravarArq = new PrintWriter(arq);
		    	
		    if(flag == 0)
		    	imprimir("Saída: ("+ nomeArquivo + "):",1,gravarArq);
		    
				String texto = new String("");
				//int cont = maiorString(automato.getEstados());
				//imprimir cabeçalho
					if(flag == 0){
						imprimir("AFND-.",0, gravarArq);
					}
					else if(flag == 1){
						imprimir("AFND",0, gravarArq);
					}
					else if(flag == 2){
						imprimir("AFD",0, gravarArq);
					}
		
					for(String aux: automato.getAlfabeto()){
						imprimir(aux,0, gravarArq);
						
					}
					
					imprimir("",1, gravarArq);
				
				//imprimir linhas
					
					ArrayList<String> alfabetoComLambida = automato.getAlfabeto();
					for(Estado estados : automato.getEstados()){
						//imprimindo colunas
							if(flag != 2)
								imprimir(estados.getNome(),0, gravarArq);
							else
								imprimir("<" + estados.getNome() + ">",0, gravarArq);
							for(String aux: alfabetoComLambida){
								Transicao transicaoAux = estados.getTransicao(aux);
								if(transicaoAux != null){	//se estado possui transição com letra do alfabeto
									if(flag != 2)
										texto += "{";
									else
										texto += "<";
									texto += transicaoAux.stringEstados();
									if(flag != 2)
										texto += "}";
									else
										texto += ">";
									imprimir(texto,0, gravarArq);
									
								}
								else{	//sem transições para esta letra
									if(flag != 2)
										imprimir("0",0, gravarArq);
									else
										imprimir("-",0, gravarArq);
								}
								texto = "";
								
								
							}
							imprimir("",1, gravarArq);
						
					}
				imprimir("",1,gravarArq);
				arq.close();
					
		}
		catch (java.io.FileNotFoundException e){
			System.out.println("Arquivo de saida nao existe. Causa: " + e.getMessage());
		} catch (java.io.IOException e) {
			System.out.println( "Erro de E/S. Causa: " + e.getMessage() );
		}
	}

}
