
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
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

public class FrmEstadistica extends JFrame {

    JTextField txtDato;
    @SuppressWarnings("rawtypes")
    JList lstMuestra;
    JTextField txtEstadistica;

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
        JScrollPane spMuestra=new JScrollPane(lstMuestra);
        spMuestra.setBounds(210, 40, 100, 150);
        getContentPane().add(spMuestra);

        JButton btnEstadistica = new JButton("Calcular");
        btnEstadistica.setBounds(10,200,100,25);
        getContentPane().add(btnEstadistica);

        JComboBox cmbEstadistica = new JComboBox();
        String[] opciones=new String[]{"Sumatoria","promedio","Desviacion","Maximo","Minimo","Moda"};
        DefaultComboBoxModel mdlEstadistica=new DefaultComboBoxModel<>(opciones);
        cmbEstadistica.setModel(mdlEstadistica);
        cmbEstadistica.setBounds(110,200,100,25);
        getContentPane().add(cmbEstadistica);

        txtEstadistica = new JTextField();
        txtEstadistica.setBounds(210,200,100,25);
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
    }
    // Declarar el arreglo que almacenará los datos de la muestra.

    private double[] muestra = new double[1000];
    private int totalDatos = -1;

    private void agregarDato() {
        double dato = Double.parseDouble(txtDato.getText());
        totalDatos++;
        muestra[totalDatos] = dato;
        mostrarMuestra();
    }

    private void mostrarMuestra() {
        String[] strMuestra = new String[totalDatos + 1];
        for (int i = 0; i <= totalDatos; i++) {
            strMuestra[i] = String.valueOf(muestra[i]);
        }
        lstMuestra.setListData(strMuestra);
    }
    
    private void quitarDato() {
        double dato = Double.parseDouble(txtDato.getText());
        totalDatos--;
        muestra[totalDatos] = dato;
        mostrarMuestra();
        }
}
