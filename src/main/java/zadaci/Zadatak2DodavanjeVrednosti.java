package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import model.Avion;
import model.Roba;

import java.io.IOException;

/**
 * Created by android on 1.10.16..
 */
public class Zadatak2DodavanjeVrednosti {

    static Dao<Avion,Integer> avionDao;
    static Dao<Roba,Integer> robaDao;

    public static void main(String[] args) {

        ConnectionSource connectionSource = null;

        try {

            connectionSource = new JdbcConnectionSource("jdbc:sqlite:AvionRoba.db");


            //kreriranje objekata AVION
            Avion avion1 = new Avion("Avion 1", 34);
            avionDao.create(avion1);

            Avion avion2 = new Avion("Avion 2", 21);
            avionDao.create(avion2);

            // kreiranje objekata ROBA

            Roba roba1 = new Roba("Patike", "Duboke patike", 1, 0.1, 0.4, 0.05);
            roba1.setAvion(avion1);
            robaDao.create(roba1);

            Roba roba2 = new Roba("Kosulja", "Na duge rukave", 0.4, 0.01, 2.4, 0.5);
            roba1.setAvion(avion1);
            robaDao.create(roba2);

            Roba roba3 = new Roba("Voda", "Voda za pice", 1.4, 0.3, 0.04, 0.03);
            roba1.setAvion(avion1);
            robaDao.create(roba3);

            Roba roba4 = new Roba("Ploce", "Drevne ploce", 3.4, 0.1, 3, 2.3);
            roba1.setAvion(avion2);
            robaDao.create(roba4);
            
            Roba roba5 = new Roba("Stolica", "Plasticna stolica", 2.4, 1.2, 0.8, 0.5);
            roba1.setAvion(avion2);
            robaDao.create(roba5);



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
