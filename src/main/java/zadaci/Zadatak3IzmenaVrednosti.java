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
public class Zadatak3IzmenaVrednosti {

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


            //pronaci robu koja ima vrednost Plasticna stolica i izmeniti u Drvena stolica

            robe=robaDao.queryForEq(Roba.POLJE_OPIS,"Plasticna stolica");
            Roba izmena=robe.get(0);
            izmena.setOpis("Drvena stolica");
            robaDao.update(izmena);


            // izlistavanje sve ROBE nakon update-a
            List<Roba> robeNakonIzmene = robaDao.queryForAll();
            for (Roba robePosleIzmene : robeNakonIzmene)
                System.out.println(robePosleIzmene.toString());
            

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
