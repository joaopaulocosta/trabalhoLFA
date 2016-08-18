
public class main {
	public static void main(String [] args){
		GeradorAutomato gerador = new GeradorAutomato(args[0]);
		Automato automato = gerador.getAutomato();
		GeradorSaida saida = new GeradorSaida();			
		System.out.println("");
		saida.imprimirAutomato(automato,0,args[1]);				//imprimindo primeira tabela
		
		ConversorAFND conversorAfnd = new ConversorAFND(automato);
		conversorAfnd.mapeiaLambida();
		automato.delLetra(".");
		System.out.println("");
		automato.getEstados().sort((Estado estado1, Estado estado2) -> estado1.getNome().compareTo(estado2.getNome()));
		saida.imprimirAutomato(automato,1,args[1]);				//imprimindo segunda tabela
		
		ConversorAFD conversorAfd = new ConversorAFD(automato);
		Automato afd = conversorAfd.Conversion();
		
		saida.imprimirAutomato(afd,2,args[1]);
		
	}
}
