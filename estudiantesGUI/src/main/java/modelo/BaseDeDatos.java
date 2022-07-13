package modelo;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import org.supercsv.io.ICsvBeanWriter;

public class BaseDeDatos {

    private ArrayList<modelo> lstEstudiantes; //unico atributo

    public BaseDeDatos() {
        this.lstEstudiantes = new ArrayList(); //nueva lista vacia
    }

    public void guardarEstudiante(modelo estudiante) {  //Metodo recibe modelos

        this.lstEstudiantes.add(estudiante); //guarde los modelos en la lista
        System.out.println("Estudiante guardado en la lista");
    }

    public modelo buscarEstudiante(String id) {
        modelo result = null; //Por defecto
        for (int i = 0; i < this.lstEstudiantes.size(); i++) {
            if (this.lstEstudiantes.get(i).getId().equals(id)) {
                System.out.println("Estudiante encontrado");
                result = this.lstEstudiantes.get(i);
            }
        }
        return result;
    }

    public void modificarEst(String id, modelo est) {
        for (int i = 0; i < this.lstEstudiantes.size(); i++) {
            if (this.lstEstudiantes.get(i).getId().equals(id)) {
                this.lstEstudiantes.get(i).setNombre(est.getNombre());
                this.lstEstudiantes.get(i).setApellido(est.getApellido());
                this.lstEstudiantes.get(i).setCorreo(est.getCorreo());
                this.lstEstudiantes.get(i).setTelefono(est.getTelefono());
                this.lstEstudiantes.get(i).setPrograma(est.getPrograma());
            }
        }
    }

    public void eliminar(String id) {
        for (int i = 0; i < this.lstEstudiantes.size(); i++) {
            if (this.lstEstudiantes.get(i).getId().equals(id)) {
                this.lstEstudiantes.remove(i);
            }
        }

    }

    
    //Trabajando con un DAT
    public void guardarArchivo() throws IOException {
        try {
            FileOutputStream archivo = new FileOutputStream("src/main/estudiantes.dat");  //Crear un archivo externo
            ObjectOutputStream salida = new ObjectOutputStream(archivo);  //Crear un lector de objetos que empalmara informacion con archivo
            salida.writeObject(this.lstEstudiantes);
            salida.close();
            archivo.close();
            
            /*for (int i = 0; i < this.lstEstudiantes.size(); i++) {  //alimentamos al lector de objetos con nuestra lista estudiantes
                modelo est = this.lstEstudiantes.get(i);
                salida.writeObject(est); } */ //escribimos los objetos en salida
            
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo crear o encontrar el archivo");
        } catch (IOException e) {
            System.out.println("Hubo un error en el sistema");
            e.printStackTrace(); //Muestre los errores
        } 
}
    
    public void recuperarArchivo() throws ClassNotFoundException {
        try{
            FileInputStream archivo= new FileInputStream("src/main/estudiantes.dat");
            ObjectInputStream entrada= new ObjectInputStream(archivo);
            this.lstEstudiantes= (ArrayList) entrada.readObject();
            archivo.close();
            entrada.close();            
        }catch (FileNotFoundException e) {
            System.out.println("No se pudo crear o encontrar el archivo");
        } catch (IOException e) {
            System.out.println("Hubo un error en el sistema");
        } catch (ClassCastException e){ //En caso de que el archivo no coincida con el formato que el metodo espera
            System.out.println("El tipo de clase no corresponde");
        }
    }
    
    
    //Trabajando con CSV
    public void guardarCSV(){
        String csvFileName="archivoCSV.csv";
        ICsvBeanWriter beanWriter= null;
    }
    

@Override
public String toString() {
        return "BaseDeDatos{" + "lstEstudiantes=" + lstEstudiantes + '}';
    }

    public ArrayList<modelo> getLstEstudiantes() {
        return lstEstudiantes;
    }

    public void setLstEstudiantes(ArrayList<modelo> lstEstudiantes) {
        this.lstEstudiantes = lstEstudiantes;
    }
         

    
}
