package clases;


public class Libro {

    private int Id;
    private String Nombre;
    private int Anho;

    public Libro(int Id, String Nombre, int Anho) {
        this.Id = Id;
        this.Nombre = Nombre;
        this.Anho = Anho;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getAnho() {
        return Anho;
    }

    public void setAnho(int Anho) {
        this.Anho = Anho;
    }

    @Override
    public String toString() {
        
        return "Libro :" + "Id =" + Id + "\n" + "Nombre = " + Nombre + "\n" + "Año = " + Anho + "\n";
    }
    
    

}
