package net.josegarvin.generadorClassificacions;

/**
 * Mètode principal del programa "generador de classificacions".
 *
 * @author Jose Garvin Victoria
 *
 */
public final class App {

	/**
	 * Constructor per defecte de la classe.
	 */
	private App() {

	}

	/**
	 * Mètode principal del programa.
	 *
	 * @param args
	 *            -->.
	 */
	public static void main(final String[] args) {
		Controlador controlador = new Controlador();
		FinestraClassificacions finestra = new FinestraClassificacions(
				controlador);
		finestra.setVisible(true);

	}
}
