/*
    Utiliza el fichero sql adjunto para crear la BD sample e implementa métodos para poder ejecutar las siguientes operaciones:
        - Visualiza número y nombre de todos los centros.
        - Modifica el nombre de un centro cuyo número se pase como argumento. No utilices sentencias preparadas.Visualiza además el número de filas afectadas.
        - Realiza el ejercicio 2 utilizando sentencias preparadas.
        - Realiza el ejercicio 2 utilizando transacciones.
        - Ten en cuenta la posible reutilización del código posteriormente
 */

import java.sql.*;
import java.util.ArrayList;

public class Ejercicio2 {

    public static void main(String[] args) {

        conexionBD();

    }

    public static void conexionBD() {
        try(Connection con = DriverManager.getConnection("jdbc:mysql://bd-mysql.c9riw8ew65p8.us-east-1.rds.amazonaws.com:3306/practicajdbc","admin","18072110k")) {

            try(Statement stmnt = con.createStatement()) {

                for (int i = 0; i < consultas().size(); i++){

                    ResultSet rs = stmnt.executeQuery(consultas().get(i));

                    while (rs.next()){

                        String nombre = rs.getString("nomce");
                        int numero = rs.getInt("numce");
                        System.out.printf("Nombre: %s, numero: %s\n", nombre, numero);

                    }
                }

                PreparedStatement prst;
                for (int i = 0; i < modificaciones().size(); i++){

                    prst = con.prepareStatement(modificaciones().get(i));
                    prst.executeUpdate();
                }

            } catch (SQLException e){
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> consultas(){
        ArrayList<String> consultas = new ArrayList<>();

        consultas.add("Select numce, nomce from centros");

        return consultas;

    }

    public static ArrayList<String> modificaciones(){
        ArrayList<String> modificaciones = new ArrayList<>();

        String nombreCen = "SEDE DERECHA";
        int numeroCen = 10;

        String modificacion = "Update centros set nomce = '" + nombreCen + "' where numce = '" + numeroCen + "'";
        modificaciones.add(modificacion);

        return modificaciones;

    }
}
