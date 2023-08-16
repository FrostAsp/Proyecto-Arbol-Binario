package arbol;

import clases.Libro;
import javax.swing.JTextArea;

public class ArbolBinario implements IArbolBinario {

    public Nodo raiz;

    public int altura;
    public int cantidad;

    public ArbolBinario() {
        raiz = null;
    }

    @Override
    public boolean buscarId(int id) {
        return buscarIdRepetidoRecursivo(raiz, id);
    }

    private boolean buscarIdRepetidoRecursivo(Nodo nodo, int id) {
        if (nodo == null) {
            return false;
        }

        if (nodo.info.getId() == id) {
            return true;
        } else if (id < nodo.info.getId()) {
            return buscarIdRepetidoRecursivo(nodo.izquierda, id);
        } else {
            return buscarIdRepetidoRecursivo(nodo.derecha, id);
        }
    }

    @Override
    public String insertar(Libro informacion) {

        Nodo nuevo = new Nodo();
        nuevo.info = informacion;
        nuevo.izquierda = null;
        nuevo.derecha = null;

        if (raiz == null) {
            raiz = nuevo;
            return "Raiz ingresada";
        } else {
            if (informacion.getId() < raiz.info.getId()) {
                if (raiz.izquierda == null) {
                    raiz.izquierda = nuevo;
                    return "Dato ingresado a la izquierda";
                }
                insertarEnSubarbol(raiz.izquierda, nuevo);
                return "Dato ingresado a la izquierda";
            } else {
                if (raiz.derecha == null) {
                    raiz.derecha = nuevo;
                    return "Dato ingresado a la derecha";
                }
                insertarEnSubarbol(raiz.derecha, nuevo);
                return "Dato ingresado a la derecha";
            }
        }
    }

    private void insertarEnSubarbol(Nodo nodoPadre, Nodo nuevo) {
        if (nuevo.info.getId() < nodoPadre.info.getId()) {
            if (nodoPadre.izquierda == null) {
                nodoPadre.izquierda = nuevo;
            } else {
                insertarEnSubarbol(nodoPadre.izquierda, nuevo);
            }
        } else {
            if (nodoPadre.derecha == null) {
                nodoPadre.derecha = nuevo;
            } else {
                insertarEnSubarbol(nodoPadre.derecha, nuevo);
            }
        }
    }

    @Override
    public String borrarPorId(int id) {
        if (raiz == null) {
            return null; 
        }

        if (id == raiz.info.getId()) {
            return "Raíz no se puede eliminar"; 
        }

        raiz = borrarPorIdRecursivo(raiz, id);

        if (raiz != null) {
            return "Nodo eliminado";
        } else {
            
            return null; 
        }
    }

    private Nodo borrarPorIdRecursivo(Nodo nodo, int id) {
        if (nodo == null) {
            return null;
        }

        if (id < nodo.info.getId()) {
            nodo.izquierda = borrarPorIdRecursivo(nodo.izquierda, id);
        } else if (id > nodo.info.getId()) {
            nodo.derecha = borrarPorIdRecursivo(nodo.derecha, id);
        } else {
            if (nodo.izquierda == null) {
                return nodo.derecha;
            } else if (nodo.derecha == null) {
                return nodo.izquierda;
            }

            Nodo sucesor = encontrarMinimoNodo(nodo.derecha);
            nodo.info = sucesor.info;
            nodo.derecha = borrarPorIdRecursivo(nodo.derecha, sucesor.info.getId());
        }
        return nodo;
    }

    private Nodo encontrarMinimoNodo(Nodo nodo) {
        while (nodo.izquierda != null) {
            nodo = nodo.izquierda;
        }
        return nodo;
    }

    @Override
    public Libro buscarPorId(int id) {
        Nodo encontrado = buscarPorIdRecursivo(raiz, id);

        if (encontrado != null) {
            return encontrado.info;
        } else {
            return null;
        }
    }

    private Nodo buscarPorIdRecursivo(Nodo nodo, int id) {
        if (nodo == null || nodo.info.getId() == id) {
            return nodo;
        }

        if (id < nodo.info.getId()) {
            return buscarPorIdRecursivo(nodo.izquierda, id);
        } else {
            return buscarPorIdRecursivo(nodo.derecha, id);
        }
    }

    @Override
    public int numeroNodosTotales() {

        return contarTotalNodos(raiz);
    }

    private int contarTotalNodos(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contarTotalNodos(nodo.izquierda) + contarTotalNodos(nodo.derecha);
    }

    @Override
    public boolean esRaiz(int valor) {
        return raiz != null && raiz.info.getId() == valor;
    }

    //RECORRIDOS SOLIDADOS
    @Override
    public void recorrido_PreOrden(JTextArea textArea) {

        recorridoPreorden(raiz, textArea);

    }

    private void recorridoPreorden(Nodo nodo, JTextArea textArea) {
        if (nodo != null) {

            textArea.append("ID: " + nodo.info.getId() + ", Nombre: " + nodo.info.getNombre() + ", Año: " + nodo.info.getAnho() + "\n");
            recorridoPreorden(nodo.izquierda, textArea);
            recorridoPreorden(nodo.derecha, textArea);
        }
    }

    @Override
    public void recorrido_Postorden(JTextArea textArea) {

        recorridoPostorden(raiz, textArea);

    }

    private void recorridoPostorden(Nodo nodo, JTextArea textArea) {
        if (nodo != null) {

            recorridoPostorden(nodo.izquierda, textArea);
            recorridoPostorden(nodo.derecha, textArea);
            textArea.append("ID: " + nodo.info.getId() + ", Nombre: " + nodo.info.getNombre() + ", Año: " + nodo.info.getAnho() + "\n");
        }
    }

    @Override
    public void recorrido_InOrden(JTextArea textArea) {

        recorridoInorden(raiz, textArea);
    }

    private void recorridoInorden(Nodo nodo, JTextArea textArea) {
        if (nodo != null) {

            recorridoInorden(nodo.izquierda, textArea);
            textArea.append("ID: " + nodo.info.getId() + ", Nombre: " + nodo.info.getNombre() + ", Año: " + nodo.info.getAnho() + "\n");
            recorridoInorden(nodo.derecha, textArea);
        }
    }

    @Override
    public void mostrarNodosHojasEnArbol(JTextArea textArea) {

        boolean hayNodosHojas = buscarNodosHojas(raiz);

        if (hayNodosHojas) {

            mostrarNodosHojas(raiz, textArea);
        }
    }

    private void mostrarNodosHojas(Nodo nodo, JTextArea textArea) {

        if (nodo != null) {
            if (nodo.izquierda == null && nodo.derecha == null) {
                textArea.append("ID: " + nodo.info.getId() + ", Nombre: " + nodo.info.getNombre() + ", Año: " + nodo.info.getAnho() + "\n");
            } else {
                mostrarNodosHojas(nodo.izquierda, textArea);
                mostrarNodosHojas(nodo.derecha, textArea);
            }
        }
    }

    private boolean buscarNodosHojas(Nodo nodo) {

        if (nodo != null) {
            if (nodo.izquierda == null && nodo.derecha == null) {
                return true;
            }
            return buscarNodosHojas(nodo.izquierda) || buscarNodosHojas(nodo.derecha);
        }
        return false;
    }

    @Override
    public int numeroHojasArbol() {

        return contarNodosHojas(raiz);
    }

    private int contarNodosHojas(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        if (nodo.izquierda == null && nodo.derecha == null) {
            return 1;
        }
        return contarNodosHojas(nodo.izquierda) + contarNodosHojas(nodo.derecha);
    }

}
