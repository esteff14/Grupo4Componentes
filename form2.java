import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class form2 {
    public JPanel pantalla;
    private JTextArea textArea;
    private JMenuBar menuBar;
    private JMenu archivo;
    private JMenu Sesion;
    private JMenuItem abrirArchivo;
    private JMenuItem cerrarArchivo;
    private JMenuItem cerrarSesion;
    private JScrollPane scrollPane;
    private JComboBox comboBox1;
    private JLabel sizeText;
    private JButton mostrarButton;

    public form2() {
        abrirArchivo.addActionListener(new ActionListener() { //Item del menu
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(); //creacion de un objeto de la clase filechooser
                //el filechooser es un componente que permite seleccionar archivos
                FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.TXT","txt");
                //filtracion de archivos a txt
                fileChooser.setFileFilter(filtro);
                int resultado = fileChooser.showDialog(null,"Abrir"); //muestra el selector de archivos
                if (resultado==JFileChooser.APPROVE_OPTION){ // verifica la seleccion abrir
                    File fichero = fileChooser.getSelectedFile(); //se obtiene el fichero escogido
                    String text=fichero.getAbsolutePath(); //ruta absoluta del fichero
                    textArea.setText(text); //se establece el fichero en un componente de textArea
                    try (FileReader fr = new FileReader(fichero)){
                        String cadena = "";
                        int valor = fr.read(); //se crea una lectura linea por linea del fichero
                        while (valor != -1){
                            cadena = cadena + (char) valor;
                            valor = fr.read();
                        }
                        textArea.setText(cadena); // se lee el texto del fichero en el textArea
                    }catch (IOException ex){ //manejo de excepción
                        System.out.println(ex.getMessage());;
                    }
                }
            }
        });
        cerrarArchivo.addActionListener(new ActionListener() { //item del menu
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText(""); // borra el contenido del textArea
            }
        });
        cerrarSesion.addActionListener(new ActionListener() { //item del menu
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.setContentPane(new form1().inicio); //se crea un nuevo objeto de la clase form1
                Main.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Main.frame.setSize(400,400);
                Main.frame.setVisible(true);
                form1.frame2.dispose();
            }
        });

        comboBox1.addItemListener(new ItemListener() { //selección de opciones
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getSource()==comboBox1){
                    int seleccionado = comboBox1.getSelectedIndex(); //obtiene el indice seleccionado en el combobox
                    if(seleccionado==0){ // tamaños de las fuentes
                        textArea.setFont(new Font("Size10",Font.PLAIN,10));
                    } else if (seleccionado==1) {
                        textArea.setFont(new Font("Size12",Font.PLAIN,12));
                    }else if (seleccionado==2) {
                        textArea.setFont(new Font("Size14",Font.PLAIN,14));
                    }else if (seleccionado==3) {
                        textArea.setFont(new Font("Size16",Font.PLAIN,16));
                    }else if (seleccionado==4) {
                        textArea.setFont(new Font("Size18",Font.PLAIN,18));
                    }else if (seleccionado==5) {
                        textArea.setFont(new Font("Size20",Font.PLAIN,20));
                    }else if (seleccionado==6) {
                        textArea.setFont(new Font("Size22",Font.PLAIN,22));
                    }
                }
            }
        });
        mostrarButton.addActionListener(new ActionListener() { //boton para abrir una nueva ventana
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Integrantes"); // se crea un objeto con el nombre integrantes
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //cierre de la ventana actual
                JTable table = createTable(); //se crea una tabla con el metodo jtable
                JScrollPane scrollPane = new JScrollPane(table); // se crea un panel de desplazamiento
                frame.setLocationRelativeTo(form1.frame2); //se establece el posicionamiento de la pantalla al
                //emerger, en este caso en el frame1 (pantalla)
                frame.getContentPane().add(scrollPane); // se agrega el panel de desplazamiento en el panel de contenido
                frame.setSize(400,250);
                frame.setVisible(true);
            }
        });
    }
    public static JTable createTable()
    {
        String[] columnNames = {"Nombre", "Apellido"}; //nombres de las columnas
        Object[][] data = {{"Estefanía", "Sanchez"},{"Isaac", "León"},{"Francisco","Caero"}};
        //definicion de datos en un arreglo bidimensional
        JTable table = new JTable(data, columnNames); // creacion de un objeto con los datos
        table.setFillsViewportHeight(true); //llena toda la altura visible de la tabla
        return table;
    }
}
