
public class main {
	public static void main(String [] args){
		GeradorAutomato gerador = new GeradorAutomato("desc_af1.txt");
		Automato automato = gerador.getAutomato();
		GeradorSaida saida = new GeradorSaida();
		saida.imprimirAutomato(automato);
	}
}
