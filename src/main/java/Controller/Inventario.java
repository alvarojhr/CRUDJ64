package Controller;

import Model.Crud;
import Model.Models.Producto;

import java.util.ArrayList;

public class Inventario {
    private static final float utilidad = 1.5f;

    public static String showInventario(){
        ArrayList<Producto> data = Crud.read();
        String result = "";

        for (Producto producto : data) {
            result += producto.getId() +"-"+producto.getNombre()+"-"+producto.getCantidad()+"-"+producto.getCostoUnitario()+"-"+producto.getValorVenta()+"\n";
        }

        return result;
    }

    public static String insertProduct(Producto producto){
        String output = "";
        calcularGanancia(producto);

        boolean success = Crud.create(producto);

        if (success){
            output += "El registro fue exitoso 😊\n";
        }else{
            output +="Houston tenemos un problema ☹\n";
        }

        return output;
    }

    public static void deleteProduct(int id) {
        Crud.delete(id);
    }

    public static Producto getProducto(int id){
        return Crud.getProducto(id);
    }

    public static String updateProducto(Producto producto) {
        String output = "";
        calcularGanancia(producto);

        if (Crud.update(producto)){
            output += "La actualización fue exitosa 😊\n";
        }else{
            output +="Houston tenemos un problema ☹\n";
        }

        return output;
    }

    private static void calcularGanancia(Producto producto){
        double venta = producto.getCostoUnitario() * utilidad;
        producto.setValorVenta(venta);
    }
}
