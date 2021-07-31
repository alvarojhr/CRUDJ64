package View;

import Controller.Inventario;
import Model.Models.Producto;

import java.util.Scanner;

public class Screen {
    public static void listProducts(){
        System.out.println(Inventario.showInventario());
    }

    public static void createProduct(){
        Producto producto = new Producto();
        Scanner sc = new Scanner(System.in);
        System.out.print("Nombre: ");
        producto.setNombre(sc.nextLine());
        System.out.print("Cantidad: ");
        producto.setCantidad(sc.nextInt());
        System.out.print("Costo unitario: ");
        producto.setCostoUnitario(sc.nextDouble());

        System.out.println(Inventario.insertProduct(producto));

    }

    public static void deleteProduct(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite el id del producto que desea eliminar: ");
        int id = sc.nextInt();
        Inventario.deleteProduct(id);

        System.out.println("El borrado fue exitoso.");
    }

    public static void updateProduct(){
        Scanner sc = new Scanner(System.in);
        listProducts();
        System.out.print("Digite el id del producto que desea actualizar: ");
        int id = sc.nextInt();

        Producto producto = Inventario.getProducto(id);
        System.out.println("Si no desea actualizar alguna propiedad, oprima ENTER.");

        System.out.println("Nombre actual: "+producto.getNombre());
        System.out.print("Nuevo nombre: ");
        sc.nextLine();
        String nombre = sc.nextLine();
        if (!nombre.isBlank()){
            producto.setNombre(nombre);
        }

        System.out.println("Cantidad actual: "+producto.getCantidad());
        System.out.print("Nueva cantidad: ");
        String cantidad = sc.nextLine();
        if (!cantidad.isBlank()){
            producto.setCantidad(Integer.parseInt(cantidad));
        }

        System.out.println("Costo unitario actual: "+producto.getCostoUnitario());
        System.out.print("Nuevo costo unitario: ");
        String costo = sc.nextLine();
        if (!costo.isBlank()){
            producto.setCostoUnitario(Double.parseDouble(costo));
        }

        System.out.println(Inventario.updateProducto(producto));

    }
}
