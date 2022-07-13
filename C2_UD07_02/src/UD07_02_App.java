import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class UD07_02_App {

	public static void main(String[] args) {
		
//		2) Crea una aplicación que gestione el flujo de ventas de una caja de supermercado. El programa guardara las cantidades del
//		carrito de compra dentro de una lista. Mostrará por pantalla la siguiente informacion:
//	
//			· IVA aplicado (21% o 4%)
//			· precio total bruto y precio mas IVA.
//			· Numero de artículos comprados.
//			· Cantidad pagada.
//			· Cambio a devolver al cliente.
		
//		Variables
		Scanner sc = new Scanner(System.in);
		Hashtable<String, Integer> carrito = new Hashtable<String, Integer>();
		
		// Productos
		Hashtable<String, Hashtable<String, Float>> listaProductos = new Hashtable<String, Hashtable<String, Float>>();
		
		Hashtable<String, Float> precioIva = new Hashtable<String, Float>();
		precioIva.put("precio", (float) 1); precioIva.put("iva", (float) 0.04); listaProductos.put("Manzana", precioIva);
		
		precioIva = new Hashtable<String, Float>();
		precioIva.put("precio", (float) 2); precioIva.put("iva", (float) 0.21); listaProductos.put("Chocolate", precioIva);
		
//		Programa
		mostrarProductos(listaProductos);
		carrito = hacerCompra(carrito, listaProductos, sc);
		mostrarTicket(carrito, listaProductos, sc);
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
					
					entry3 = itrProd.next();
					ivaAplicado = entry3.getValue();
					
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

}
