import java.util.ArrayList;

public class GeradorSaida {
	

	
	public  GeradorSaida(){
	}

	public void imprimirAutomato(Automato automato){
		
		//imprimir cabeçalho
			System.out.print("ND-.\t\t");
			for(String aux: automato.getAlfabeto()){
				System.out.print(aux + "\t\t");
			}
			
			System.out.println(".");
		
		//imprimiar linhas
			
			ArrayList<String> alfabetoComLambida = automato.getAlfabeto();
			alfabetoComLambida.add(".");
			for(Estado estados : automato.getEstados()){
				//imprimindo colunas
					System.out.print(estados.getNome() + "\t\t");
					for(String aux: alfabetoComLambida){
						Transicao transicaoAux = estados.getTransicao(aux);
						if(transicaoAux != null){	//se estado possui transição com letra do alfabeto
							System.out.print("{");
							System.out.print(transicaoAux.stringEstados());
							System.out.print("}");
							if(transicaoAux.stringEstados().length() < 6)
								System.out.print("\t");
							
						}
						else{	//sem transições para esta letra
							System.out.print("0");
							System.out.print("\t");
						}
						
						System.out.print("\t");
						
					}
				System.out.println("");
				
			}
	}

}
