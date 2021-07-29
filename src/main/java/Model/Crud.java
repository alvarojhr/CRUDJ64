package Model;

import Model.Models.Inventario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Crud {

    public static ArrayList<Inventario> read(){
        Connection connection = DbConection.connect();
        ArrayList<Inventario> productos = new ArrayList<Inventario>();

        ResultSet result = null;

        try {
            String sql = "SELECT * FROM Inventario";
            result = connection.prepareStatement(sql).executeQuery();

            while (result.next()){
                Inventario producto = new Inventario(result.getInt(1), result.getString(2),result.getInt(3),result.getDouble(4),result.getDouble(5));
                productos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("[CONNECTION] "+e);
        }finally {
            try {
                result.close();
                connection.close();
            } catch (SQLException e) {
                System.out.println("[CONNECTION] "+e);
            }
        }

        return productos;
    }
}
