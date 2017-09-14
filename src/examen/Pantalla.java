package examen;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Sergio Cañas Serrano
 * @version 1.0
 */
public class Pantalla implements ActionListener{
    // Ventana del programa
    JFrame ventana;
    
    // Texto "convertidor"
    JLabel convertidor;
    
    //Arreglo de botones para numeros
    JButton[] arregloBotones = new JButton[9];
    
    //Botones adicionales
    JButton cero, punto, convertir, limpiar;
    
    //Pantalla de numeros
    JTextField pantalla;
    
    //Contenedor de los elementos
    Container cont;
    
    public Pantalla(){
        ventana = new JFrame("Convertidor");
        ventana.setSize(450, 450);
        ventana.setLayout(null);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        
        cont = ventana.getContentPane();
        
        Componentes();
        
        
        
        ventana.setVisible(true);
    }
    
    public void Componentes(){
        
        convertidor = new JLabel("Convertidor");
        convertidor.setBounds(10, 10, 80, 30);
        cont.add(convertidor);
        
        pantalla = new JTextField("$ ");
        pantalla.setEditable(false);
        pantalla.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        pantalla.setBounds(10, 40, 430, 60);
        pantalla.setBackground(Color.white);
        cont.add(pantalla);
        
        int y = 110;
        int x = 30;
        int contador = 0;
        for(int i = 0; i < 9 ; i++){
            arregloBotones[i] = new JButton(String.valueOf(i + 1));
            arregloBotones[i].setBounds(x, y, 60, 40);
            arregloBotones[i].addActionListener(this);
            cont.add(arregloBotones[i]);
            x+= 70;
            contador++;
            if(contador == 3){
                y+= 50;
                x = 30;
                contador = 0;
            }
        }
        
        cero = new JButton("0");
        cero.setBounds(x, y, 60, 40);
        cero.addActionListener(this);
        cont.add(cero);
        
        punto = new JButton(".");
        punto.setBounds((x + 70), y, 60, 40);
        punto.addActionListener(this);
        cont.add(punto);
        
        convertir = new JButton("Convertir");
        convertir.setBounds(300, 110, 110, 40);
        convertir.addActionListener(this);
        cont.add(convertir);
        
        limpiar = new JButton("Limpiar");
        limpiar.setBounds(300, 160, 110, 40);
        limpiar.addActionListener(this);
        cont.add(limpiar);
    }
    
    /**
     * Escribe el valor del btoon
     * 
     * @param n Boton numerico.
     */
    
    public void Escribir(JButton n){
        pantalla.setText(pantalla.getText() + n.getText());
    }
    
    /**
     * Realiza la conversion de pesos a dolar mediante division
     */
    
    public void Convertir(){
        String cadena = "";
        for(int f = 0; f < pantalla.getText().length(); f++){
            if(f > 0){
                cadena += pantalla.getText().charAt(f);
            }
        }
        double pesos = Double.parseDouble(cadena);
        double resultado = pesos / 17;
        
        pantalla.setText("$$" + String.valueOf(resultado));
    }
    
    /**
     * Acciones a realizar segun el boton seleccionado
     * 
     * @param e Evento click.
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() != convertir && e.getSource() != limpiar){
            Escribir((JButton) e.getSource());
        } else{
            if(e.getSource() == convertir){
                Convertir();
            } else{
                pantalla.setText("$");
            }
        }
    }
}
