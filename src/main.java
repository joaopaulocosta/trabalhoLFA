
public class main {
	public static void main(String [] args){
		GeradorAutomato gerador = new GeradorAutomato("teste.txt");
		Automato automato = gerador.getAutomato();
		GeradorSaida saida = new GeradorSaida();			
		System.out.println("");
		saida.imprimirAutomato(automato,0,"saida.txt");				//imprimindo primeira tabela
		
		ConversorAFND conversorAfnd = new ConversorAFND(automato);
		conversorAfnd.mapeiaLambida();
		automato.delLetra(".");
		System.out.println("");
		saida.imprimirAutomato(automato,1,"saida.txt");				//imprimindo segunda tabela
		
		ConversorAFD conversorAfd = new ConversorAFD(automato);
		Automato afd = conversorAfd.Conversion();
		
		saida.imprimirAutomato(afd,2,"saida.txt");
		
	}
}
