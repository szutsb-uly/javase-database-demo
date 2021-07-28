package hu.ulyssys.java.course.database.hibernate;

import hu.ulyssys.java.course.database.hibernate.entity.Aircraft;
import hu.ulyssys.java.course.database.hibernate.entity.Tank;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class HibernateMain {

    public static void main(String[] args) {

        Aircraft aircraft = new Aircraft();
        aircraft.setName("Stuper repcsi");
        aircraft.setCreatedDate(new Date());
        aircraft.setLastModifiedDate(new Date());
        insertAircraft(aircraft);

        //Save
        Tank tank = new Tank();
        tank.setName("példa tigris tank");
        tank.setType("tigris tank");
        tank.setCreatedDate(new Date());
        tank.setLastModifiedDate(new Date());

        Long id = insertTank(tank);

        // find By id
        Tank persisTank = findById(id);
        // lekérdezzük a tank

        persisTank.setName("test");
        persisTank.setCreatedDate(new Date());
        update(persisTank);
        findAll().forEach(tank1 -> {
            System.out.println(tank1.getId() + " " + tank1.getName());
        });

        DatabaseSessionProvider.getInstance().getSessionObj().close();
    }

    private static List<Tank> findAll() {
        Session session = DatabaseSessionProvider.getInstance().getSessionObj();
        Query<Tank> query = session.createQuery("select n from Tank n", Tank.class);
        return query.getResultList();
    }

    private static void update(Tank tank) {
        Session session = DatabaseSessionProvider.getInstance().getSessionObj();
        session.beginTransaction();
        session.update(tank);
        session.getTransaction().commit();
    }

    private static Tank findById(Long id) {
        Session session = DatabaseSessionProvider.getInstance().getSessionObj();
        return session.find(Tank.class, id);
    }

    private static Long insertAircraft(Aircraft aircraft) {
        Session session = DatabaseSessionProvider.getInstance().getSessionObj();
        session.beginTransaction();
        session.save(aircraft);
        session.getTransaction().commit();
        return aircraft.getId();
    }

    private static Long insertTank(Tank tank) {
        Session session = DatabaseSessionProvider.getInstance().getSessionObj();
        session.beginTransaction();
        session.save(tank);
        session.getTransaction().commit();
        return tank.getId();
    }
}
