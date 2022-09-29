package com.luv2code.springdemo.dao;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.util.SortUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers(int sortField) {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // determine sort field
        String fieldName = null;

        switch (sortField) {
            case SortUtils.FIRST_NAME:
                fieldName = "firstName";
                break;
            case SortUtils.LAST_NAME:
                fieldName = "lastName";
                break;
            case SortUtils.EMAIL:
                fieldName = "email";
                break;
            default:
                // if nothing matches the default to sort by lastName
                fieldName = "lastName";
        }

        // create a query"
        String queryString = "from Customer order by " + fieldName;
        Query<Customer> theQuery =
                currentSession.createQuery(queryString, Customer.class);

        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();

        // return the results
        return customers;
    }

    @Override
    public void saveCustomer(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(customer); //wtedy jeśli primary key jest puste (z hidden form), wtedy save, inaczej update
    }

    @Override
    public Customer getCustomer(int id) {
        Session session = sessionFactory.getCurrentSession();
        Customer customer = session.get(Customer.class, id);
        return customer;
    }

    @Override
    public void deleteCustomer(int id) {
        Session session = sessionFactory.getCurrentSession();

        //ponzsza wersja też poprawna, ale użyjemy ciekawsszej
        //Customer customer = session.get(Customer.class, id);
        //session.delete(customer);

        Query query = session.createQuery("delete from Customer where id = :customerId");
        query.setParameter("customerId", id);
        query.executeUpdate();
    }

    @Override
    public List<Customer> findCustomersByName(String name) {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery = null;

        //
        // only search by name if theSearchName is not empty
        //
        if (name != null && name.trim().length() > 0) {
            // search for firstName or lastName ... case insensitive
            theQuery =currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
            theQuery.setParameter("theName", "%" + name.toLowerCase() + "%");
        }
        else {
            // theSearchName is empty ... so just get all customers
            theQuery =currentSession.createQuery("from Customer", Customer.class);
        }

        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();

        // return the results
        return customers;
    }
}
