import java.util.ArrayList;

public class Automato {
	private ArrayList<Estado> listaEstados;
	private ArrayList<String> alfabeto;
	private Estado estadoInicial;
	private ArrayList<Estado> estadosFinais;
	
	public Automato(){
		listaEstados = new ArrayList<Estado>();
		alfabeto = new ArrayList<String>();
		estadosFinais = new ArrayList<Estado>();
		estadoInicial = null;
	}
	
	public void addEstado(Estado estado){
		this.listaEstados.add(estado);
	}
	
	public void addLetra(String letra){
		this.alfabeto.add(letra);
	}
	
	public void addEstadoInicial(Estado estadoInicial){
		this.estadoInicial = estadoInicial;
	}
	
	public void addEstadoFinal(Estado estadoFinal){
		this.estadosFinais.add(estadoFinal);
	}
	
	public void imprimir(){
		
		
		if(!this.listaEstados.isEmpty()){	//verifica se lista esta vazia
			System.out.print("Estados: ");
			for(Estado aux: this.listaEstados){
				System.out.print(aux.getNome() +", ");
			}
			System.out.println();
		}
		
		if(!this.alfabeto.isEmpty()){		//verifica se lista esta vazia
			System.out.print("Alfabeto: ");
			for(String aux: this.alfabeto){
				System.out.print(aux);
			}
			System.out.println();
		}
		/*System.out.print("Transicoes: ");
		for(String aux: this.alfabeto){
			System.out.print(aux);
		}
		System.out.println(); */
		
		if(this.estadoInicial != null){		//verifica se objeto esta instanciado
			System.out.println("Estado inicial: "+ this.estadoInicial.getNome());
		}
		
		if(!this.estadosFinais.isEmpty()){	//verifica se lista esta vazia
			System.out.print("Estados finais: ");
			for(Estado aux: this.estadosFinais){
				System.out.print(aux.getNome() +", ");
			}
			System.out.println();
		}
	}
}
