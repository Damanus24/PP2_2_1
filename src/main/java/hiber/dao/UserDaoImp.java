package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public void add(Car car) {
      sessionFactory.getCurrentSession().save(car);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<Car> listCars() {
      TypedQuery<Car> query=sessionFactory.getCurrentSession().createQuery("from Car");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listCarUsers(String model, int series) {
      TypedQuery<User> query = sessionFactory.getCurrentSession()
              .createQuery("SELECT u FROM User u JOIN u.car c WHERE c.model = :model AND c.series = :series")
              .setParameter("model", model)
              .setParameter("series", series);
//              .createQuery("SELECT u FROM User u JOIN u.car c WHERE c.model = 'ВАЗ' AND c.series = 2114");
      List<User> listCarUsers = query.getResultList();
      return listCarUsers;
   }
}
