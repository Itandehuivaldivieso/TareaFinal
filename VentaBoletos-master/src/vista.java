import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
public class vista extends JFrame implements ActionListener {
    //Declaracion de lo que se usará
    JPanel panelTitulo = new JPanel();
    JPanel panelIcono = new JPanel();
    JPanel panelContra = new JPanel();
    JPanel panelDesbloqueo = new JPanel();
    private JScrollPane mibarra1;
    private JTable tablon ;
    private JButton botonIcono, aceptar,cancelar,conexion,registro,atras;
    private JTextField nombre, contraseña;
    private JLabel nombre1,contraseña1;
    ConexionBD interfazMysql;
    public vista(){
        super("Blockeo de pantalla");
        this.setLayout(new GridBagLayout());
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        GridBagConstraints restricciones1= new GridBagConstraints();

        Container contenedorPrincipal = this.getContentPane();
        contenedorPrincipal.setLayout(new GridBagLayout());
        contenedorPrincipal.setBackground(Color.yellow);

        //Panel
        panelTitulo.setLayout(new GridBagLayout());
        panelTitulo.setVisible(false);

        //Panel icono

        panelIcono.setLayout(new GridBagLayout());

        //panel contraseña

        panelContra.setLayout(new GridBagLayout());
        panelContra.setVisible(false);

        //panel desbloqueo

        panelDesbloqueo.setLayout(new GridBagLayout());
        panelDesbloqueo.setVisible(false);

        //Panel icono boton
        botonIcono = new JButton("Entra");
        botonIcono.setPreferredSize(new Dimension(200,32));
        /*ImageIcon icono = new ImageIcon("n.png");
        botonIcono.setIcon(icono);*/
        restricciones1.gridx = 1;
        restricciones1.gridy = 1;
        restricciones1.gridheight = 1;
        restricciones1.gridwidth = 1;
        restricciones1.weightx = 1;
        restricciones1.weighty = 1;
        restricciones1.insets.set(1,1,1,1);
        restricciones1.fill = GridBagConstraints.BOTH;
        panelIcono.add(botonIcono,restricciones1);
        botonIcono.addActionListener(this);

        //especificaciones del panel
        restricciones1.gridx = 2;
        restricciones1.gridy = 2;
        restricciones1.gridheight = 1;
        restricciones1.gridwidth = 1;
        restricciones1.weightx = 1;
        restricciones1.weighty = 1;
        restricciones1.fill = GridBagConstraints.HORIZONTAL;
        restricciones1.anchor = GridBagConstraints.NORTH;
        contenedorPrincipal.add(panelIcono,restricciones1);

        //panel registro
        nombre1     = new JLabel("Nombre");
        restricciones1.gridx = 1;
        restricciones1.gridy = 0;
        restricciones1.gridheight = 1;
        restricciones1.gridwidth = 1;
        restricciones1.weightx = 2;
        restricciones1.weighty = 1;
        restricciones1.insets.set(1,1,1,1);
        restricciones1.fill = GridBagConstraints.BOTH;
        panelContra.add(nombre1,restricciones1);
        nombre      = new JTextField();
        restricciones1.gridx = 1;
        restricciones1.gridy = 1;
        restricciones1.gridheight = 1;
        restricciones1.gridwidth = 1;
        restricciones1.weightx = 2;
        restricciones1.weighty = 1;
        restricciones1.insets.set(1,1,1,1);
        restricciones1.fill = GridBagConstraints.BOTH;
        panelContra.add(nombre,restricciones1);
        contraseña1 = new JLabel("Contraseña");
        restricciones1.gridx = 1;
        restricciones1.gridy = 2;
        restricciones1.gridheight = 1;
        restricciones1.gridwidth = 1;
        restricciones1.weightx = 2;
        restricciones1.weighty = 1;
        restricciones1.insets.set(1,1,1,1);
        restricciones1.fill = GridBagConstraints.BOTH;
        panelContra.add(contraseña1,restricciones1);
        contraseña  = new JTextField();
        restricciones1.gridx = 1;
        restricciones1.gridy = 3;
        restricciones1.gridheight = 1;
        restricciones1.gridwidth = 1;
        restricciones1.weightx = 2;
        restricciones1.weighty = 1;
        restricciones1.insets.set(1,1,1,1);
        restricciones1.fill = GridBagConstraints.BOTH;
        panelContra.add(contraseña,restricciones1);

        aceptar     = new JButton("Aceptar");
        restricciones1.gridx = 1;
        restricciones1.gridy = 4;
        restricciones1.gridheight = 1;
        restricciones1.gridwidth = 1;
        restricciones1.weightx = 1;
        restricciones1.weighty = 1;
        restricciones1.insets.set(1,1,1,1);
        restricciones1.fill = GridBagConstraints.BOTH;
        aceptar.addActionListener(this);
        panelContra.add(aceptar,restricciones1);

        //Boton cancelar
        cancelar    = new JButton("conectar");
        restricciones1.gridx = 2;
        restricciones1.gridy = 4;
        restricciones1.gridheight = 1;
        restricciones1.gridwidth = 1;
        restricciones1.weightx = 1;
        restricciones1.weighty = 1;
        restricciones1.insets.set(1,1,1,1);
        restricciones1.fill = GridBagConstraints.BOTH;
        cancelar.addActionListener(this);
        panelContra.add(cancelar,restricciones1);

        //especificaciones del panel
        restricciones1.gridx = 0;
        restricciones1.gridy = 1;
        restricciones1.gridheight = 1;
        restricciones1.gridwidth = 1;
        restricciones1.weightx = 1;
        restricciones1.weighty = 1;
        restricciones1.fill = GridBagConstraints.HORIZONTAL;
        restricciones1.anchor = GridBagConstraints.NORTH;
        contenedorPrincipal.add(panelContra,restricciones1);

        atras    = new JButton("atras");
        restricciones1.gridx = 3;
        restricciones1.gridy = 3;
        restricciones1.gridheight = 1;
        restricciones1.gridwidth = 1;
        restricciones1.weightx = 1;
        restricciones1.weighty = 1;
        restricciones1.insets.set(1,1,1,1);
        restricciones1.fill = GridBagConstraints.BOTH;
        atras.addActionListener(this);
        panelDesbloqueo.add(atras,restricciones1);

        //JTable
        mibarra1 = new JScrollPane();
        restricciones1.gridx = 1;
        restricciones1.gridy = 1;
        restricciones1.gridheight = 1;
        restricciones1.gridwidth = 1;
        restricciones1.weightx = 3;
        restricciones1.weighty = 3;
        restricciones1.insets.set(1,1,1,1);
        restricciones1.fill = GridBagConstraints.BOTH;
        cancelar.addActionListener(this);
        panelDesbloqueo.add(mibarra1,restricciones1);

        //especificaciones del panel
        restricciones1.gridx = 0;
        restricciones1.gridy = 1;
        restricciones1.gridheight = 1;
        restricciones1.gridwidth = 1;
        restricciones1.weightx = 1;
        restricciones1.weighty = 1;
        restricciones1.fill = GridBagConstraints.HORIZONTAL;
        restricciones1.anchor = GridBagConstraints.NORTH;
        contenedorPrincipal.add(panelDesbloqueo,restricciones1);

    }
    public static void main( String[] args){
        vista vista1= new vista();
        vista1.setSize(2000,3300);
        //vista1.setDefaultCloseOperation(EXIT_ON_CLOSE);
        vista1.pack();
        vista1.setLocationRelativeTo(null);
        vista1.setVisible(true);

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vista().setVisible(true);
            }
        });
    }
    public void llenarTabla(ArrayList n) {
        String columna[]=new String[]{"id","nombre","fecha","hora"};

        int numero = n.size()/4;
        String vec[][]= new String[numero][4];
        int w=0,z=0;
        for (int x=0;x<numero;x++){
            for(int y=0;y<4;y++){
                vec[x][y]=(String)n.get(w);
                w++;
            }
        }
        tablon=new JTable(vec,columna);
        mibarra1.setViewportView(tablon);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("conectar")) {
        }else if((JButton)e.getSource()==botonIcono){
            panelIcono.setVisible(false);
            panelContra.setVisible(true);
            interfazMysql = new ConexionBD();
            Connection bdCon = interfazMysql.Conectar();
        }else if((JButton)e.getSource()==aceptar){
                if(this.contraseña.getText().equals("1234")){
                    JOptionPane.showMessageDialog(this,"entro");
                    String nombre1= nombre.getText();
                    JOptionPane.showMessageDialog(this,nombre1);
                    int nRegs = interfazMysql.insertarCliente(nombre1);
                    panelContra.setVisible(false);
                    panelDesbloqueo.setVisible(true);
                    interfazMysql = new ConexionBD();
                    Connection bdCon = interfazMysql.Conectar();
                    ResultSet registros = interfazMysql.consultarClientes();
                    ArrayList datos = new ArrayList();
                    datos = interfazMysql.rec(registros);
                    llenarTabla(datos);
                }
        }else if((JButton)e.getSource()==atras){
            System.exit(0);
        }

        }

    }

