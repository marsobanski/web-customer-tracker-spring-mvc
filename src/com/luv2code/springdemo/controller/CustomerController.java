package com.luv2code.springdemo.controller;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;
import com.luv2code.springdemo.util.SortUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model model, @RequestParam(required=false) String sort) {

        List<Customer> paramCustomers = (List<Customer>)model.getAttribute("paramCustomers");

        // get customers from the service
        List<Customer> customers = null;

        // check for sort field
        if (sort != null) {
            int sortField = Integer.parseInt(sort);
            if (customers != null && !customers.isEmpty()) {
                List<Integer> currentCustomerIds = paramCustomers.stream().map(Customer::getId).collect(Collectors.toList());
                customers = sortCustomersByFieldName(customers.stream(), sortField);
            } else {
                customers = customerService.getCustomers(sortField);
            }
        }
        else {
            // no sort field provided ... default to sorting by last name
            customers = customerService.getCustomers(SortUtils.LAST_NAME);
        }

        // add the customers to the model
        model.addAttribute("customers", customers);

        return "customer-list";
    }

    private List<Customer> sortCustomersByFieldName(Stream<Customer> customers, int sortField) {
        switch (sortField) {
            case SortUtils.FIRST_NAME:
                customers.sorted(Comparator.comparing(Customer::getFirstName));
                break;
            case SortUtils.LAST_NAME:
                customers.sorted(Comparator.comparing(Customer::getLastName));
                break;
            case SortUtils.EMAIL:
                customers.sorted(Comparator.comparing(Customer::getEmail));
                break;
            default:
                // if nothing matches the default to sort by lastName
                customers.sorted(Comparator.comparing(Customer::getLastName));
        }
        return customers.collect(Collectors.toList());
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {

        //create empty customer to be filled from form
        Customer customer = new Customer();

        //add customer to form
        model.addAttribute("customer", customer);
        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int id, Model model) {
        Customer customer = customerService.getCustomer(id);
        model.addAttribute("customer", customer);
        return "customer-form";
    }

    @GetMapping("/deleteCustomer")
    public String deleteCustomer(@RequestParam("customerId") int id) {
        customerService.deleteCustomer(id);
        return "redirect:/customer/list";
    }

    @GetMapping("/search")
    public String searchCustomers(@RequestParam("theSearchName") String theSearchName,
                                  Model theModel) {
        // search customers from the service
        List<Customer> customers = customerService.findCustomersByName(theSearchName);

        // add the customers to the model
        theModel.addAttribute("customers", customers);
        return "customer-list";
    }
}
