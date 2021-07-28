package hu.ulyssys.java.course.database.jpa;

import hu.ulyssys.java.course.database.jpa.dao.CustomerDAO;
import hu.ulyssys.java.course.database.jpa.entity.Customer;

public class JPAMain {

    public static void main(String[] args) {
        CustomerDAO customerDAO = new CustomerDAO();

        Customer customer = new Customer();
        customer.setCash(1);
        customer.setFullName("2Szüts Bálint");
        customer.setUsername("22");
        customerDAO.save(customer);

        customerDAO.findAll().forEach(customer1 -> {
            System.out.println(customer1.getId() + " " + customer1.getFullName() + " " + customer1.getUsername());
        });

        customerDAO.findByName("2Szüts Bálint").forEach(customer1 -> {
            customer1.setFullName("Kecske"+System.currentTimeMillis());
            customerDAO.update(customer1);
        });

        customerDAO.findAll().forEach(customer1 -> {
            System.out.println(customer1.getId() + " " + customer1.getFullName() + " " + customer1.getUsername());
        });
    }

}
