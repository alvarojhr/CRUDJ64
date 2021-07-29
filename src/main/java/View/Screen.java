package View;

import Controller.Inventario;

public class Screen {
    public static void listProducts(){
        System.out.println(Inventario.showInventario());
    }
}
