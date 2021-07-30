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

        boolean success = Inventario.insertProduct(producto);

        if (success){
            System.out.println("El registro fue exitoso 😊");
        }else{
            System.out.println("Houston tenemos un problema ☹");
        }
    }

    public static void deleteProduct(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite el id del producto que desea eliminar: ");
        int id = sc.nextInt();
        Inventario.deleteProduct(id);

        System.out.println("El borrado fue exitoso.");
    }
}
