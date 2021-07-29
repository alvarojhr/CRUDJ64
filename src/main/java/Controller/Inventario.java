package Controller;

import Model.Crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Inventario {
    public static String showInventario(){
        ArrayList<Model.Models.Inventario> data = Crud.read();
        String result = "";

        for (Model.Models.Inventario producto : data) {
            result += producto.getId() +"-"+producto.getNombre()+"-"+producto.getCantidad()+"-"+producto.getCostoUnitario()+"-"+producto.getValorVenta()+"\n";
        }

        return result;
    }
}
