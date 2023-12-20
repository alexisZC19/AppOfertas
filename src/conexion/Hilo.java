package conexion;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

public class Hilo extends Thread {

    JLabel labelcito;
    String query;
    DefaultTableModel contenido;
    public java.sql.Connection cp = null;

    Object[] ofertas;

    /**
     *
     * @param labelcito Jlabel a utilizar.
     * @param query String a utilizar
     * @param ofertas
     * 
     *
     * se crea un constructor el cual recibe un Jlabel para la impresion de la
     * informacion de la consulta.
     */
    public Hilo(JLabel labelcito, String query, Object[] ofertas ) {
        this.labelcito = labelcito;
        this.query = query;
        this.ofertas = ofertas;
   

    }

    /**
     * se sobreescribre el metodo run de la clase Thread
     */
    @Override
    public void run() {

        // Obtener la hora actual
        //hacemos la consulta
        java.sql.ResultSet r = new conexion.Conectar().consulta(query);
        int i = 1;
        try {
            while (r.next()) {
                ofertas[0] = r.getInt("folio");
                ofertas[1] = r.getString("nombrep");

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
                }
                i = i + 1;
            }
            
            
             labelcito.setText((String) ofertas[1]);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
