package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import model.Avion;

import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * Created by android on 1.10.16..
 */
public class AvionNit extends Thread {

    private Avion avion;

    static Dao<Avion,Integer> avionDao;

    public AvionNit(Avion avion) {
        this.avion = avion;
    }

    public static void main(String[] args) {
        ConnectionSource connectionSource = null;
        try {
            connectionSource = new JdbcConnectionSource("jdbc:sqlite:AvionRoba.db");

            avionDao = DaoManager.createDao(connectionSource, Avion.class);

            List<Avion> sviAvioni = avionDao.queryForAll();

            AvionNit av1 = new AvionNit(sviAvioni.get(0));
            AvionNit av2 = new AvionNit(sviAvioni.get(1));

            av1.start();
            av2.start();

            try {
                av1.join();
                av2.join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Avioni poleteli.");

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

    @Override
    public void run() {
        boolean dozvoljenoSletanje = false;
        System.out.println("Pocinju provere za avion " + avion.getId() + " Avion oznaka " + avion.getOznaka());
        do {
            synchronized (avion) {
                System.out.println("Avion " + avion.getId() + " je spreman za poletanje i ceka dozvolu za poletanje");
                if (!dozvoljenoSletanje) {
                    synchronized (avion){
                        System.out.println("Avion " + avion.getId() + " izlazi na pistu i polece");
                        dozvoljenoSletanje = false;
                    }

                    Random random = new Random();

                    try {

                        sleep(random.nextInt(2000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Avion " + avion.getId()  + " je poleteo ");
                    dozvoljenoSletanje = true;
                }

            }
        } while (!dozvoljenoSletanje) ;
    }

}
