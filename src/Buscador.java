import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class Buscador extends JFrame {
    private JTextField txtBusqueda;
    private JButton btnBuscar;
    private JTextArea textCuadro;
    private JButton btnAdelante;
    private JButton btnVolver;
    private JPanel PanelBuscador;
    private Stack<String> pilaAnteriores = new Stack<String>();
    private Stack<String> pilaAnterioresImpresion = new Stack<String>();
    private Stack<String> pilaPosteriores = new Stack<String>();

    private String Mensaje;
    private String MensajeFinal;

    public Buscador (String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(PanelBuscador);
        this.pack();//ajusta el tamaño de la ventana
        enableButtons();
        //pilaAnteriores.push("Inicio del Buscador");
        //textCuadro.setText(pilaAnteriores.peek());
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPagina();
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regresarPagina();
            }
        });
        btnAdelante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adelantarPagina();
            }
        });
    }

    private void adelantarPagina() {
       //System.out.println("TAMANIO LUEGO POP"+pilaPosteriores.size());
        String aux=pilaPosteriores.pop();
        pilaAnteriores.push(aux);
        pilaAnterioresImpresion.addAll(pilaAnteriores);
        textCuadro.setText("");
        Imprimir(pilaAnterioresImpresion);
        System.out.println("TAMANIO "+pilaAnteriores.size());
        enableButtons();
    }

    private void regresarPagina() {
        //enableButtons();
        String aux=pilaAnteriores.pop();
        pilaPosteriores.push(aux);
        pilaAnterioresImpresion.addAll(pilaAnteriores);
        textCuadro.setText("");
        Imprimir(pilaAnterioresImpresion);
        System.out.println("TAMANIO "+pilaAnteriores.size());
        enableButtons();

    }

    private void buscarPagina() {
        //enableButtons();
        //System.out.printf(""+pilaPosteriores.isEmpty());
        if(pilaPosteriores.isEmpty()){
            Mensaje=txtBusqueda.getText();
            pilaAnteriores.push(Mensaje);
            pilaAnterioresImpresion.addAll(pilaAnteriores);
            textCuadro.setText("");
            Imprimir(pilaAnterioresImpresion);
            System.out.println("TAMANIO "+pilaAnteriores.size());
            enableButtons();
        }else{
            pilaPosteriores.clear();
            Mensaje=txtBusqueda.getText();
            pilaAnteriores.push(Mensaje);
            pilaAnterioresImpresion.addAll(pilaAnteriores);
            textCuadro.setText("");
            Imprimir(pilaAnterioresImpresion);
            System.out.println("TAMANIO "+pilaAnteriores.size());
            enableButtons();
        }



    }
    private void enableButtons(){
        if (pilaAnteriores.isEmpty()){
            btnVolver.setEnabled(false);
        }else{
            btnVolver.setEnabled(true);
        }
        if (pilaPosteriores.isEmpty()){
            btnAdelante.setEnabled(false);;
        }
        else{
            btnAdelante.setEnabled(true);
        }
    }
    private void Imprimir(Stack<String> pila){
        MensajeFinal="";
        while(!pila.isEmpty()) { // Mientras la pila no esté vacía
            //System.out.println(pila.pop());// Imprime y desapila el elemento de la cima
            MensajeFinal=pila.pop() +"  ,  "+ MensajeFinal;

        }
        textCuadro.setText(MensajeFinal);
    }

    public static void main(String[] args) {
        JFrame buscador= new Buscador("UNDO BUSCADOR");
        buscador.setVisible(true);
    }
}
