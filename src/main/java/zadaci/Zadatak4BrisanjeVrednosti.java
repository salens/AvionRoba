package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import model.Avion;
import model.Roba;

import java.io.IOException;
import java.util.List;

/**
 * Created by android on 1.10.16..
 */
public class Zadatak4BrisanjeVrednosti {

    static Dao<Avion,Integer> avionDao;
    static Dao<Roba,Integer> robaDao;

    public static void main(String[] args) {
        ConnectionSource connectionSource = null;

        try {

            connectionSource = new JdbcConnectionSource("jdbc:sqlite:AvionRoba.db");

            avionDao = DaoManager.createDao(connectionSource, Avion.class);
            robaDao = DaoManager.createDao(connectionSource, Roba.class);

            //Prikaz svih vrednosti iz tabele roba

            List<Roba> robe = robaDao.queryForAll();
            for (Roba r : robe)
                System.out.println(r.toString());

            //Brisanje robe koja ima naziv VODA

            robe = robaDao.queryForEq(Roba.POLJE_NAZIV, "Voda");
            Roba robaZaBrisanje = robe.get(0);
            robaDao.delete(robaZaBrisanje);

            //Prikaz svih vrednosti iz tabele ROBA nakon brisanja

            List<Roba> robeNakonBrisanja = robaDao.queryForAll();
            for (Roba robNakonBrisanja : robeNakonBrisanja)
                System.out.println(robNakonBrisanja.toString());



        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (connectionSource != null) {
                try {
                    connectionSource.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
