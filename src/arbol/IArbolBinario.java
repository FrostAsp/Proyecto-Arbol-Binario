package arbol;

import clases.Libro;
import javax.swing.JTextArea;

public interface IArbolBinario {

    public boolean buscarId(int id);

    public String insertar(Libro info);

    public String borrarPorId(int id);
    
    public Libro buscarPorId(int id);
    
    public int numeroNodosTotales();
    
     public boolean esRaiz(int valor);
    
    //Recorridos
    
    public void recorrido_PreOrden(JTextArea textArea);
    
    public void recorrido_Postorden(JTextArea textArea);
    
    public void recorrido_InOrden(JTextArea textArea);
    
    public void mostrarNodosHojasEnArbol(JTextArea textArea);
    
    public int numeroHojasArbol();

}
