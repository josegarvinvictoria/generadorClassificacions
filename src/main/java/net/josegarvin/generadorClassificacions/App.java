package net.josegarvin.generadorClassificacions;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Controlador controlador = new Controlador();
        FinestraClassificacions finestra = new FinestraClassificacions(controlador);
        finestra.setVisible(true);
        
        
        
    }
}
