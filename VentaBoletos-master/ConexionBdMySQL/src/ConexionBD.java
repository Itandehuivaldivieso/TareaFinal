import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;

public class ConexionBD {
    java.util.Date date = new Date();
        public String db = "clavepantalla";
        public String url = "jdbc:mysql://localhost/" + db
                + "?useUnicode=true"
                + "&useJDBCCompliantTimezoneShift=true"
                + "&useLegacyDatetimeCode=false"
                + "&serverTimezone=UTC";
        public String user = "root";
        public String pass = "numerito1";

        Connection link;

        public Connection Conectar(){
            link = null;

            try{
                //Class.forName("org.gjt.mm.mysql.Driver");  //version 5
                Class.forName("com.mysql.cj.jdbc.Driver");   //version 8

                link = DriverManager.getConnection(this.url, this.user, this.pass);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex);
            }

            return link;
        }
    public ResultSet consultarClientes(){
        ResultSet registros = null;
        try {
            PreparedStatement stConsultar = link.prepareStatement(
                    "Select id, nombre, fecha,hora from datos1 order by id");

            registros = stConsultar.executeQuery();

        }
        catch(SQLException error){
            error.printStackTrace();
        }

        return registros;
    }
    public ArrayList rec(ResultSet n) {
        int total1 = 0;
        int total=0;
        ArrayList listaDatos = new ArrayList();
        try {

            String vec[]= new String[4];
            while (n.next()) {
                //System.out.println(n.getString(2));
                listaDatos.add(n.getString(1));
                listaDatos.add( n.getString(2));
                listaDatos.add( n.getString(3));
                listaDatos.add( n.getString(4));
            }

        } catch (SQLException e) {
        e.printStackTrace();
    }
        return listaDatos;
    }
        public String[][] consultaRegistro(ResultSet n, int total){

            String matriz[][]=new String[total][4];
            try {

                while (n.next()){
                    System.out.println(n.getString(1));
                    matriz[total][0]=n.getString("id");
                    matriz[total][1]=n.getString("nombre");
                    matriz[total][2]=n.getString("fecha");
                    matriz[total][3]=n.getString("hora");
                    total++;
                }
                //System.out.println(matriz[0][1]);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return matriz;
        }
        public int insertarCliente(String nombre1){
            int numRegs = 0;
            try {
                java.util.Date fecha = new Date();
                Calendar fecha1 = Calendar.getInstance();
                String año1 = Integer.toString( fecha1.get(Calendar.YEAR));
                String mes =Integer.toString(fecha1.get(Calendar.MONTH) + 1);
                String dia =Integer.toString(fecha1.get(Calendar.DAY_OF_MONTH));
                String hola =año1+"-"+mes+"-"+dia;
                System.out.println(hola);
                String hora1=Integer.toString(fecha1.get(Calendar.HOUR_OF_DAY))+":"+Integer.toString(fecha1.get(Calendar.MINUTE))+":"+Integer.toString(fecha1.get(Calendar.SECOND));
                link = DriverManager.getConnection(this.url, this.user, this.pass);
                String query = "INSERT INTO datos1 (nombre,fecha,hora) values('"+nombre1+"','"+hola+"','"+hora1+"')";
                Statement stmt = link.createStatement();
               numRegs= stmt.executeUpdate(query);
            }
            catch(SQLException error){
                error.printStackTrace();
            }

            return numRegs;
        }


}









