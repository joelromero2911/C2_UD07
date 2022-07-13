import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

public class UD07_04_App {

	public static void main(String[] args) {
		
//		4) Combina los métodos generados en las actividades 2 y 3 creando una aplicación que gestione ventas y control de stock en
//		una misma interfaz. Utiliza para ello las estructuras de datos que creas conveniente.
		
//		Variables
		Scanner sc = new Scanner(System.in);
		Hashtable<String, Integer> carrito = new Hashtable<String, Integer>();
		boolean salir = false;
		
		// Productos
		Hashtable<String, Hashtable<String, Float>> listaProductos = new Hashtable<String, Hashtable<String, Float>>();
		
		Hashtable<String, Float> precioCant = new Hashtable<String, Float>();
		precioCant.put("precio", (float) 1); precioCant.put("cantidad", (float) 4); precioCant.put("iva", (float) 0.21); listaProductos.put("Manzana", precioCant);
		precioCant = new Hashtable<String, Float>(); precioCant.put("precio", (float) 2); precioCant.put("cantidad", (float) 5); precioCant.put("iva", (float) 0.21); listaProductos.put("Chocolate", precioCant);
		precioCant = new Hashtable<String, Float>(); precioCant.put("precio", (float) 5.6); precioCant.put("cantidad", (float) 12); precioCant.put("iva", (float) 0.04); listaProductos.put("Agua", precioCant);
		precioCant = new Hashtable<String, Float>(); precioCant.put("precio", (float) 3); precioCant.put("cantidad", (float) 20); precioCant.put("iva", (float) 0.15); listaProductos.put("Papel", precioCant);
		precioCant = new Hashtable<String, Float>(); precioCant.put("precio", (float) 2.5); precioCant.put("cantidad", (float) 7); precioCant.put("iva", (float) 0.04); listaProductos.put("Chorizo", precioCant);
		precioCant = new Hashtable<String, Float>(); precioCant.put("precio", (float) 7); precioCant.put("cantidad", (float) 8); precioCant.put("iva", (float) 0.21); listaProductos.put("Aguacate", precioCant);
		precioCant = new Hashtable<String, Float>(); precioCant.put("precio", (float) 8); precioCant.put("cantidad", (float) 9); precioCant.put("iva", (float) 0.21); listaProductos.put("Pera", precioCant);
		precioCant = new Hashtable<String, Float>(); precioCant.put("precio", (float) 3.24); precioCant.put("cantidad", (float) 10); precioCant.put("iva", (float) 0.04); listaProductos.put("Platano", precioCant);
		precioCant = new Hashtable<String, Float>(); precioCant.put("precio", (float) 6.99); precioCant.put("cantidad", (float) 40); precioCant.put("iva", (float) 0.21); listaProductos.put("Cebolla", precioCant);
		precioCant = new Hashtable<String, Float>(); precioCant.put("precio", (float) 0.50); precioCant.put("cantidad", (float) 2); precioCant.put("iva", (float) 0.04); listaProductos.put("Helado", precioCant);
		
//		Programa
		while(salir == false) {
			String respuesta = JOptionPane.showInputDialog("Selecciona:\n  1. Anadir productos.\n  2. Consultar info productos.\n  3. Comprar.\n  0. Salir.");
			
			switch (respuesta) {
			case "1":
				listaProductos = anadirProducto(listaProductos);
				break;
			case "2":
				consultarInfoProducto(listaProductos);
				break;
			case "3":
				mostrarProductos(listaProductos);
				carrito = hacerCompra(carrito, listaProductos, sc);
				mostrarTicket(carrito, listaProductos, sc);
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
	
	public static void mostrarProductos(Hashtable<String, Hashtable<String, Float>> listaProductos) {
		
		Enumeration<String> productos = listaProductos.keys();
		
		System.out.println("Productos:\n");
		while(productos.hasMoreElements()) {
			System.out.println(productos.nextElement());
		}
	}
	
	public static Hashtable<String, Integer> hacerCompra(Hashtable<String, Integer> carrito, Hashtable<String, Hashtable<String, Float>> listaProductos, Scanner sc){
		
		String producto = "";
		
		System.out.println("\nEscribe \"hecho\" cuando hayas acabado.");
		
		while(!producto.equals("hecho")){
			
			System.out.println("Nombre producto que quieres comprar: ");
			producto = sc.next();
			
			if(!producto.equals("hecho")) {
				System.out.println("Cantidad: ");
				int cantidad = sc.nextInt();
				
				carrito.put(producto, cantidad);
			}
		}
		
		return carrito;
	}
	
	public static void mostrarTicket(Hashtable<String, Integer> carrito, Hashtable<String, Hashtable<String, Float>> listaProductos, Scanner sc) {
		
		float ivaAplicado = 0;
		float precioBruto = 0;
		float precioConIva = 0;
		float precioTotal = 0;
		float cantidadPagada = 0;
		float cambioDevolver = 0;
		
		Iterator<Map.Entry<String, Integer>> itrCarrito = carrito.entrySet().iterator();
		Map.Entry<String, Integer> entry = null;

		System.out.println("\nTicket:");
		
		while(itrCarrito.hasNext()){
			
			entry = itrCarrito.next();
			String prod = entry.getKey();
		    int cant = entry.getValue();

		    Iterator<Map.Entry<String, Hashtable<String, Float>>> itrlistaProductos = listaProductos.entrySet().iterator();
			Map.Entry<String, Hashtable<String, Float>> entry2 = null;
		    
			while(itrlistaProductos.hasNext()){
				
				entry2 = itrlistaProductos.next();
				if(prod.equals(entry2.getKey())) {
					
					Iterator<Entry<String, Float>> itrProd = entry2.getValue().entrySet().iterator();
					Map.Entry<String, Float> entry3 = null;
					System.out.println(entry2.getValue());
					entry3 = itrProd.next();
					ivaAplicado = entry3.getValue();
					
					entry3 = itrProd.next();
					entry3 = itrProd.next();
					precioBruto = entry3.getValue();
				}
			}
			
			precioBruto *= cant;
			precioConIva = precioBruto+(precioBruto*ivaAplicado);
			precioTotal += precioConIva;
			
		    System.out.println(prod + " | Cantidad: " + cant + " | Precio bruto: " + precioBruto + " + " + "IVA(" + ivaAplicado*100 + "%) " + (precioBruto*ivaAplicado) + " = " + precioConIva + " eur.");
		}
		
		System.out.println("Precio a pagar: " + precioTotal);
		System.out.println("Introduzca la cantidad de euros con la que va a pagar: ");
		cantidadPagada = sc.nextFloat();
		cambioDevolver = cantidadPagada - precioTotal;
		System.out.println("Cambio: " + cambioDevolver);
		
	}
	
	public static Hashtable<String, Hashtable<String, Float>> anadirProducto(Hashtable<String, Hashtable<String, Float>> listaProductos) {

		String nombre = JOptionPane.showInputDialog("Dime el nombre del producto que quieres añadir: ");
		float precio = Float.parseFloat(JOptionPane.showInputDialog("El precio: "));
		float cantidad = Float.parseFloat(JOptionPane.showInputDialog("La cantidad: "));
		float iva = Float.parseFloat(JOptionPane.showInputDialog("% de IVA: "));
		iva /= 100;
		
		Hashtable<String, Float> precioCant = new Hashtable<String, Float>(); precioCant.put("precio", precio); precioCant.put("cantidad", cantidad); precioCant.put("iva", iva); listaProductos.put(nombre, precioCant);

		JOptionPane.showMessageDialog(null, "Producto añadido correctamente.");
		
		return listaProductos;
	}
	
	public static void consultarInfoProducto(Hashtable<String, Hashtable<String, Float>> listaProductos) {
//		System.out.println(listaProductos);
		
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
					JOptionPane.showMessageDialog(null, "Nombre: " + entry.getKey() + "\nIVA: " + values.nextElement() + "\nCantidad: " + values.nextElement() + "\nPrecio: " + values.nextElement());
					break;
				}
			}
			break;
			
		case "2":
			String contenidoMsg = "";
			
			while(itrlistaProductos.hasNext()){
				entry = itrlistaProductos.next();
				Enumeration<Float> values = entry.getValue().elements();
				contenidoMsg += "Nombre: " + entry.getKey() + "\nIVA: " + values.nextElement() + "\nCantidad: " + values.nextElement() + "\nPrecio: " + values.nextElement() + "\n-------------------\n";
			}
			JOptionPane.showMessageDialog(null, contenidoMsg);
			break;

		default:
			JOptionPane.showMessageDialog(null, "Opcion incorrecta.");
			break;
		}
	}

}
