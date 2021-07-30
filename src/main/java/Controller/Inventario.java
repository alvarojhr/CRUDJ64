package Controller;

import Model.Crud;
import Model.Models.Producto;

import java.util.ArrayList;

public class Inventario {
    public static String showInventario(){
        ArrayList<Producto> data = Crud.read();
        String result = "";

        for (Producto producto : data) {
            result += producto.getId() +"-"+producto.getNombre()+"-"+producto.getCantidad()+"-"+producto.getCostoUnitario()+"-"+producto.getValorVenta()+"\n";
        }

        return result;
    }

    public static boolean insertProduct(Producto producto){
        double venta = producto.getCostoUnitario() *1.5;
        producto.setValorVenta(venta);
        return Crud.create(producto);
    }

    public static void deleteProduct(int id) {
        Crud.delete(id);
    }
}
