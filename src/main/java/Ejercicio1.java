
import java.sql.*;

public class Ejercicio1 {

    public static void main(String[] args) {

            String consulta = "SELECT nombre_cliente, apellido_cliente FROM clientes";
            String consulta1 = "SELECT nombre_destino FROM destinos";
            String consulta2 = "SELECT COUNT(distinct nombre_cliente) AS 'cantidad' FROM clientes";

            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://db-programacion-ej1.cbved7bhnvqz.us-east-1.rds.amazonaws.com:3306/viajes","admin","piramide");
                Statement stmnt = con.createStatement();
                ResultSet rs = stmnt.executeQuery(consulta);

                while (rs.next()){
                    String nombre = rs.getString("nombre_cliente");
                    String apellido = rs.getString("apellido_cliente");
                    System.out.printf("Nombre: %s, apellido: %s\n", nombre, apellido);
                }
                rs.close();
                ResultSet rs1 = stmnt.executeQuery(consulta1);
                while (rs1.next()){
                    String nombre = rs1.getString("nombre_destino");
                    System.out.printf("Nombre de destino: %s\n", nombre);
                }
                rs1.close();

                ResultSet rs2 = stmnt.executeQuery(consulta2);
                while (rs2.next()){
                    int cantidad = rs2.getInt("cantidad");
                    System.out.printf("Cantidad de nombres de clientes distintos: %s\n", cantidad);
                }

                rs2.close();
                stmnt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }
}

