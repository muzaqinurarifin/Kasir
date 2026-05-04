package proyekkasiralfacom;

public class SessionUser {

    private static int idUser = 0;
    private static String namaUser = null;
    private static String role = null;

    public static void setUser(int id, String nama, String roleUser) {
        idUser = id;
        namaUser = nama;
        role = roleUser;
    }

    public static int getIdUser() {
        return idUser;
    }

    public static String getNamaUser() {
        return namaUser;
    }

    public static String getRole() {
        return role;
    }

    public static void clear() {
        idUser = 0;
        namaUser = null;
        role = null;
    }
}
