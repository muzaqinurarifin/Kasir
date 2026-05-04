package proyekkasiralfacom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {

    private static Connection koneksi;

    public static Connection getKoneksi() {
        if (koneksi == null) {
            try {
                String url = "jdbc:mysql://localhost/db_alfacom_kasir";
                String users = "root";
                String pass = "";

                koneksi = DriverManager.getConnection(url, users, pass);
                System.out.println(" yes Koneksi Berhasil");
                
            } catch (SQLException e) {
                System.out.println("Koneksi Gagal : " + e.getMessage());
            }
        }
        return koneksi;
    }
}
