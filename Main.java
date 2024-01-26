import javax.swing.*;
import java.awt.*;

public class Main {
    static JFrame frame = new JFrame("Inicio de Sesion");// creacion de un objeto en el panel de contenido del frame inicio
    public static void main(String[] args) {
        frame.setContentPane(new form1().inicio);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // centrar la ventana
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.setSize(500,500);
        frame.setVisible(true);
    }
}