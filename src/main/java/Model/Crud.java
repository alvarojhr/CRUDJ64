package Model;

import Model.Models.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Crud {
    private static Connection connection;

    public static void setConnection(Connection connection) {
        Crud.connection = connection;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static ArrayList<Producto> read(){

        ArrayList<Producto> productos = new ArrayList<Producto>();

        ResultSet result = null;

        try {
            String sql = "SELECT * FROM Inventario";
            result = connection.prepareStatement(sql).executeQuery();

            while (result.next()){
                Producto producto = new Producto(result.getInt(1), result.getString(2),result.getInt(3),result.getDouble(4),result.getDouble(5));
                productos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("[CONNECTION] "+e);
        }finally {
            try {
                result.close();
            } catch (SQLException e) {
                System.out.println("[CONNECTION] "+e);
            }
        }

        return productos;
    }

    public static boolean create(Producto producto){
        boolean isSuccess = false;

        PreparedStatement query = null;

        try{
            String sql = "INSERT INTO Inventario(Nombre, Cantidad, CostoUnitario, ValorVenta) VALUES (?,?,?,?);";
            query = connection.prepareStatement(sql);

            query.setString(1, producto.getNombre());
            query.setInt(2, producto.getCantidad());
            query.setDouble(3, producto.getCostoUnitario());
            query.setDouble(4, producto.getValorVenta());

            query.execute();
            isSuccess = true;
        } catch (SQLException e) {
            System.out.println("[CONNECTION] "+e);
        }

        return isSuccess;
    }

    public static void delete(int id) {
        PreparedStatement query = null;
        try{
            String sql = String.format("DELETE FROM Inventario WHERE Id = %d;",id);
            query = connection.prepareStatement(sql);
            query.execute();
        } catch (SQLException e) {
            System.out.println("[CONNECTION] "+e);
        }
    }

    public static Producto getProducto(int id){
        Producto producto = null;

        ResultSet result = null;

        try {
            String sql = String.format("SELECT * FROM Inventario WHERE id=%d", id);
            result = connection.prepareStatement(sql).executeQuery();

            producto = new Producto(result.getInt(1), result.getString(2),result.getInt(3),result.getDouble(4),result.getDouble(5));

        } catch (SQLException e) {
            System.out.println("[CONNECTION] "+e);
        }finally {
            try {
                result.close();
            } catch (SQLException e) {
                System.out.println("[CONNECTION] "+e);
            }
        }

        return producto;
    }

    public static boolean update(Producto producto) {
        boolean success = false;

        PreparedStatement query = null;
        try {
            String sql = "UPDATE Inventario SET Nombre=?, Cantidad = ?, CostoUnitario=?, ValorVenta=? WHERE Id = ?";
            query = connection.prepareStatement(sql);

            query.setString(1, producto.getNombre());
            query.setInt(2, producto.getCantidad());
            query.setDouble(3, producto.getCostoUnitario());
            query.setDouble(4, producto.getValorVenta());
            query.setInt(5, producto.getId());
            query.execute();
            success = true;
        } catch (SQLException e) {
            System.out.println("[CONNECTION] "+e);
        }

        return success;
    }
}
