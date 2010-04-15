package util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import modele.Niveau;

/**
 *
 * @author marya
 */
public class NiveauDAO extends ModeleDAO {

    public static HashMap<Integer, Niveau> getAll() throws SQLException {
        HashMap<Integer, Niveau> niveaux = new HashMap<Integer, Niveau>();
        String sql = "SELECT niveau.id_niveau, niveau.libelle, niveau.est_actif, COUNT( questionnaire.id_niveau ) AS compteur "
                + "FROM niveau "
                + "LEFT OUTER JOIN questionnaire ON questionnaire.id_niveau = niveau.id_niveau "
                + "GROUP BY niveau.id_niveau "
                + "ORDER BY niveau.id_niveau ASC";
        ResultSet rs = execute(sql);
        while (rs.next()) {
            Niveau niveau = new Niveau(
                    rs.getInt("id_niveau"),
                    rs.getString("libelle"),
                    rs.getInt("compteur"),
                    rs.getBoolean("est_actif"));
            niveaux.put(niveau.getIdNiveau(), niveau);
        }
        rs.close();
        return niveaux;
    }

    public static Niveau getById(int idNiveau) throws SQLException {
        Niveau niveau = null;
        String sql = "SELECT niveau.id_niveau, niveau.libelle, niveau.est_actif, COUNT( questionnaire.id_niveau ) AS compteur "
                + "FROM niveau "
                + "LEFT OUTER JOIN questionnaire ON questionnaire.id_niveau = niveau.id_niveau "
                + "WHERE niveau.id_niveau = ? "
                + "GROUP BY niveau.id_niveau "
                + "ORDER BY niveau.id_niveau ASC";
        ResultSet rs = selectById(sql, idNiveau);
        if (rs.next()) {
            niveau = new Niveau(
                    rs.getInt("id_niveau"),
                    rs.getString("libelle"),
                    rs.getInt("compteur"),
                    rs.getBoolean("est_actif"));
        }
        rs.close();
        return niveau;
    }

    public static void update(Niveau niveau) throws SQLException {
        String sql = "UPDATE niveau SET libelle = ?, est_actif = ? WHERE id_niveau = ?";
        PreparedStatement ps = getConnection().prepareStatement(sql);
        ps.setString(1, niveau.getLibelle());
        ps.setBoolean(2, niveau.estActif());
        ps.setInt(3, niveau.getIdNiveau());
        ps.executeUpdate();
        ps.close();
    }
}
