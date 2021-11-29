package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.CustomerForm;
import com.codegym.service.CustomerService;
import com.codegym.service.HibernateCustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Value("${file-upload}")
    private String fileUpload;
    @Autowired
   private  CustomerService customerService;
    @RequestMapping("/home")
    public ModelAndView home(){
        List<Customer> customerList = customerService.findAll();
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("customers",customerList);
        return modelAndView;
    }
    @RequestMapping("/create")
    public ModelAndView create(){
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("customer",new CustomerForm());
        return modelAndView;
    }
    @PostMapping("/save")
    public String create(@ModelAttribute CustomerForm customerForm){
        MultipartFile multipartFile = customerForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try{
            FileCopyUtils.copy(customerForm.getImage().getBytes(),new File(fileUpload+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Customer customer = new Customer(customerForm.getName(),customerForm.getEmail(),customerForm.getAddress(),fileName);
        customerService.save(customer);
        return "redirect:/customer/home";
    }

}
