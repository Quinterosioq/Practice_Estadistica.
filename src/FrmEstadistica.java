
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

public class FrmEstadistica extends JFrame {

    JTextField txtDato;
    @SuppressWarnings("rawtypes")
    JList lstMuestra;
    JTextField txtEstadistica;
    JComboBox cmbEstadistica;

    @SuppressWarnings("rawtypes")
    public FrmEstadistica() {

        setSize(400, 300);
        setTitle("Estadistica");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblDato = new JLabel("Dato");
        lblDato.setBounds(10, 10, 100, 25);
        getContentPane().add(lblDato);

        txtDato = new JTextField();
        txtDato.setBounds(80, 10, 100, 25);
        getContentPane().add(txtDato);

        JLabel lblDatos = new JLabel("Muestra");
        lblDatos.setBounds(210, 10, 100, 25);
        getContentPane().add(lblDatos);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(80, 40, 100, 25);
        getContentPane().add(btnAgregar);

        JButton btnQuitar = new JButton("Quitar");
        btnQuitar.setBounds(80, 70, 100, 25);
        getContentPane().add(btnQuitar);

        lstMuestra = new JList();
        JScrollPane spMuestra = new JScrollPane(lstMuestra);
        spMuestra.setBounds(210, 40, 100, 150);
        getContentPane().add(spMuestra);

        JButton btnEstadistica = new JButton("Calcular");
        btnEstadistica.setBounds(10, 200, 100, 25);
        getContentPane().add(btnEstadistica);

        cmbEstadistica = new JComboBox();
        String[] opciones = new String[] { "Sumatoria", "promedio", "Desviacion", "Maximo", "Minimo", "Moda" };
        DefaultComboBoxModel mdlEstadistica = new DefaultComboBoxModel<>(opciones);
        cmbEstadistica.setModel(mdlEstadistica);
        cmbEstadistica.setBounds(110, 200, 100, 25);
        getContentPane().add(cmbEstadistica);

        txtEstadistica = new JTextField();
        txtEstadistica.setBounds(210, 200, 100, 25);
        getContentPane().add(txtEstadistica);

        // Ahora se va a asociar un evento a la GUI:

        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                agregarDato();
            }
        });

        btnQuitar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                quitarDato();
            }
        });

        // Declarar el arreglo que almacenará los datos de la muestra.

        btnEstadistica.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                calcularEstadistica();
            }
        });
    }

    private double[] muestra = new double[1000];
    private int totalDatos = -1;

    private void agregarDato() {
        try {
            double dato = Double.parseDouble(txtDato.getText());
            totalDatos++;
            muestra[totalDatos] = dato;
            txtDato.setText("");
            mostrarMuestra();
        } catch (Exception ex) {
            txtDato.setText("");
            JOptionPane.showMessageDialog(null, "Debe especificar un valor numerico.");
        }
    }

    @SuppressWarnings("unchecked")
    private void mostrarMuestra() {
        String[] strMuestra = new String[totalDatos + 1];
        for (int i = 0; i <= totalDatos; i++) {
            strMuestra[i] = String.valueOf(muestra[i]);
        }
        lstMuestra.setListData(strMuestra);
    }

    private void quitarDato() {
        int posicion = lstMuestra.getSelectedIndex();
        if (posicion >= 0) {
            // retirar la posicion del vector
            for (int i = posicion; i < totalDatos; i++) {
                muestra[i] = muestra[i + 1];
            }
            totalDatos--;
            mostrarMuestra();
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar la posicion");
        }
    }

    private double sumatoria() {
        double suma = 0;
        for (int i = 0; i <= totalDatos; i++) {
            suma += muestra[i];
        }
        return suma;
    }

    private double promedio() {
        double promedioCalculando = 0;
        if (totalDatos >= 0) {
            promedioCalculando = sumatoria() / (totalDatos + 1);

        }
        return promedioCalculando;
    }

    private double DesviacionEstandar() {
        double DesviacionDe = 0;
        double promedioCalculando = promedio();
        for (int i = 0; i <= totalDatos; i++) {
            DesviacionDe += Math.abs(muestra[i] - promedioCalculando);
        }
        return totalDatos > 0 ? DesviacionDe / totalDatos:0;
    // El signo de interrogacion funciona como un "entonces" remplazando la funcion de un if y un else.
    }

    private double Maximo() {
        double Mayor = muestra[0];
        for (int i = 1; i <= totalDatos; i++){
            if(muestra[i]>Mayor){
                Mayor = muestra[i];
            }
        }
        return Mayor;
    }

    @SuppressWarnings("unused")
    private void calcularEstadistica() {
        switch (cmbEstadistica.getSelectedIndex()) {
            case 0:
                txtEstadistica.setText(String.valueOf(sumatoria()));
                break;
            case 1:
                txtEstadistica.setText(String.valueOf(promedio()));
                break;
            case 2:
                txtEstadistica.setText(String.valueOf(DesviacionEstandar()));
                break;
            case 3:
                txtEstadistica.setText(String.valueOf(Maximo()));
                break;
        }
    }
}
