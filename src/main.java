
public class main {
	public static void main(String [] args){
		GeradorAutomato gerador = new GeradorAutomato("teste.txt");
		Automato automato = gerador.getAutomato();
		GeradorSaida saida = new GeradorSaida();		
		System.out.println("Saída: (desc_af1.txt):");
		System.out.println("");
		saida.imprimirAutomato(automato);				//imprimindo primeira tabela
		
		ConversorAFND conversorAfnd = new ConversorAFND(automato);
		conversorAfnd.mapeiaLambida();
		automato.delLetra(".");
		System.out.println("");
		saida.imprimirAutomato(automato);				//imprimindo segunda tabela
	}
}
