import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

public class UD07_03_App {

	public static void main(String[] args) {
		
//		3) Crea una base de datos de 10 artículos para controlar el stock de productos de una tienda por medio de un diccionario
//		de datos (articulo:precio). El usuario podrá añadir, por medio de interfaz visual artículos nuevos y cantidades de estos. El
//		usario podrá consultar la información almacenada en el diccionario referente a un articulo concreto e incluso listar toda
//		la información en la terminal del programa.
		
//		Variables
		boolean salir = false;
		
		// Productos
		Hashtable<String, Hashtable<String, Float>> listaProductos = new Hashtable<String, Hashtable<String, Float>>();
		
		Hashtable<String, Float> precioCant = new Hashtable<String, Float>();
		precioCant.put("precio", (float) 1); precioCant.put("cantidad", (float) 4); listaProductos.put("Manzana", precioCant);
		precioCant = new Hashtable<String, Float>(); precioCant.put("precio", (float) 2); precioCant.put("cantidad", (float) 5); listaProductos.put("Chocolate", precioCant);
		precioCant = new Hashtable<String, Float>(); precioCant.put("precio", (float) 5.6); precioCant.put("cantidad", (float) 12); listaProductos.put("Agua", precioCant);
		precioCant = new Hashtable<String, Float>(); precioCant.put("precio", (float) 3); precioCant.put("cantidad", (float) 20); listaProductos.put("Papel", precioCant);
		precioCant = new Hashtable<String, Float>(); precioCant.put("precio", (float) 2.5); precioCant.put("cantidad", (float) 7); listaProductos.put("Chorizo", precioCant);
		precioCant = new Hashtable<String, Float>(); precioCant.put("precio", (float) 7); precioCant.put("cantidad", (float) 8); listaProductos.put("Aguacate", precioCant);
		precioCant = new Hashtable<String, Float>(); precioCant.put("precio", (float) 8); precioCant.put("cantidad", (float) 9); listaProductos.put("Pera", precioCant);
		precioCant = new Hashtable<String, Float>(); precioCant.put("precio", (float) 3.24); precioCant.put("cantidad", (float) 10); listaProductos.put("Platano", precioCant);
		precioCant = new Hashtable<String, Float>(); precioCant.put("precio", (float) 6.99); precioCant.put("cantidad", (float) 40); listaProductos.put("Cebolla", precioCant);
		precioCant = new Hashtable<String, Float>(); precioCant.put("precio", (float) 0.50); precioCant.put("cantidad", (float) 2); listaProductos.put("Helado", precioCant);
		
//		Programa
		while(salir == false) {
			String respuesta = JOptionPane.showInputDialog("Selecciona:\n  1. Anadir productos.\n  2. Consultar info productos.\n  0. Salir.");
			
			switch (respuesta) {
			case "1":
				listaProductos = anadirProducto(listaProductos);
				break;
			case "2":
				consultarInfoProducto(listaProductos);
				break;
			case "0":
				salir = true;
				break;
				
			default:
				JOptionPane.showMessageDialog(null, "Opcion incorrecta.");
				break;
			}
		}
	}

	public static Hashtable<String, Hashtable<String, Float>> anadirProducto(Hashtable<String, Hashtable<String, Float>> listaProductos) {

		String nombre = JOptionPane.showInputDialog("Dime el nombre del producto que quieres añadir: ");
		float precio = Float.parseFloat(JOptionPane.showInputDialog("El precio: "));
		float cantidad = Float.parseFloat(JOptionPane.showInputDialog("La cantidad: "));
		
		Hashtable<String, Float> precioCant = new Hashtable<String, Float>(); precioCant.put("precio", precio); precioCant.put("cantidad", cantidad); listaProductos.put(nombre, precioCant);

		JOptionPane.showMessageDialog(null, "Producto añadido correctamente.");
		
		return listaProductos;
	}
	
	public static void consultarInfoProducto(Hashtable<String, Hashtable<String, Float>> listaProductos) {
		System.out.println(listaProductos);
		
		String respuesta = JOptionPane.showInputDialog("Consultar:\n  1. Producto en especifico.\n  2. Listar todos.");
		
		Iterator<Map.Entry<String, Hashtable<String, Float>>> itrlistaProductos = listaProductos.entrySet().iterator();
		Entry<String, Hashtable<String, Float>> entry = null;
		
		switch (respuesta) {
		case "1":
			String producto = JOptionPane.showInputDialog("Nombre producto a consultar: ");

			while(itrlistaProductos.hasNext()){
				entry = itrlistaProductos.next();
				if(entry.getKey().equals(producto)) {
					Enumeration<Float> values = entry.getValue().elements();
					JOptionPane.showMessageDialog(null, "Nombre: " + entry.getKey() + "\nCantidad: " + values.nextElement() + "\nPrecio: " + values.nextElement());
					break;
				}
			}
			break;
			
		case "2":
			String contenidoMsg = "";
			
			while(itrlistaProductos.hasNext()){
				entry = itrlistaProductos.next();
				Enumeration<Float> values = entry.getValue().elements();
				contenidoMsg += "Nombre: " + entry.getKey() + "\nCantidad: " + values.nextElement() + "\nPrecio: " + values.nextElement() + "\n-------------------\n";
			}
			JOptionPane.showMessageDialog(null, contenidoMsg);
			break;

		default:
			JOptionPane.showMessageDialog(null, "Opcion incorrecta.");
			break;
		}
	}
}
