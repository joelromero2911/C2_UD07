import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class UD07_01_App {

	public static void main(String[] args) {
		
//		1) crea una aplicación que calcule la nota media de los alumnos pertenecientes al curso de programación. Una vez
//		calculada la nota media se guardara esta información en un diccionario de datos que almacene la nota media de cada
//		alumno. Todos estos datos se han de proporcionar por pantalla.
		
//		Variables
		Scanner sc = new Scanner(System.in);
		int respuesta = 99;
		Hashtable<String, Integer> alumnosNotas = new Hashtable<String, Integer>();
		
//		Programa
		while(respuesta != 0) {
			System.out.print("\nResponde con el numero del apartado que quieres:\n1. Rellenar notas alumno.\n0. He acabado, calcular medias y mostrar.\nRESPUESTA ---> ");
			respuesta = sc.nextInt();
			
			if(respuesta == 1) {
				alumnosNotas = rellenarAlumnos(sc, alumnosNotas);
			}
		}
		
		mostrarDatos(alumnosNotas);
	}
	
	public static Hashtable<String, Integer> rellenarAlumnos(Scanner sc, Hashtable<String, Integer> alumnosNotas){
		
		int suma = 0;
		int cont = 0;
		
		System.out.println("\nNombre del alumno? ");
		String nombre = sc.next();
		
		int resp = 0;
		System.out.println("\nTe ire pidiendo notas, cuando hayas puesto todas responde \"99\".");
		
		while(resp != 99) {
			System.out.println("Introduce nota: ");
			resp = sc.nextInt();
			if(resp != 99) {
				suma += resp;
				cont++;
			}
		}
		
		alumnosNotas.put(nombre, suma/cont);
		return alumnosNotas;
	}
	
	public static void mostrarDatos(Hashtable<String, Integer> alumnosNotas) {
		
		Iterator<Map.Entry<String, Integer>> itr = alumnosNotas.entrySet().iterator();
		Map.Entry<String, Integer> entry = null;
		
		System.out.println("\nMostrando datos:\n");
		while(itr.hasNext()){
		    entry = itr.next();
		    System.out.println("Alumno: " + entry.getKey() + " | Nota media: " + entry.getValue());
		}
	}

}