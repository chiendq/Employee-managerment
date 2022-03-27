package com.example.springboot2.controller;

import com.example.springboot2.model.Company;
import com.example.springboot2.model.Employee;
import com.example.springboot2.repository.CompanyRepository;
import com.example.springboot2.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CompanyRepository companyRepository;

    @RequestMapping(value = "/{id}")
    public String getCompanyById(
            @PathVariable(value = "id") Long id, Model model){
        Company company = companyRepository.getById(id);
        List<Employee> employees = employeeRepository.findByCompanyId(id);
        model.addAttribute("employees", employees);
        model.addAttribute("company", company);
        return "companyDetail";
    }

    @RequestMapping("/list")
    public String getAllCompany(Model model) {
        List<Company> companies = companyRepository.findAll();
        model.addAttribute("companies", companies);
        return "companyList";
    }
    @RequestMapping(value = "/add")
    public String addEmployee (Model model) {
        Company company = new Company();
        model.addAttribute("company", company);
        return "companyAdd";
    }

    @RequestMapping (value = "/update/{id}")
    public String updateCompany(
            @PathVariable (value = "id") Long id, Model model)  {
        Company company = companyRepository.getById(id);
        model.addAttribute(company);
        return "companyUpdate";
    }

    @RequestMapping(value = "/save")
    public String saveUpdate(
            @RequestParam(value = "id", required = false) Long id, @Valid Company company, BindingResult result)
    {
        if (result.hasErrors()) {
            if (id == null) {
                return "companyAdd";
            } else {
                return "companyUpdate";
            }
        }
        company.setId(id);
        companyRepository.save(company);
        return "redirect:/company/list";
    }

    @RequestMapping(value = "/delete/{id}")
    public String deleteCompany(
            @PathVariable(value = "id") Long id) {
        Company company = companyRepository.getById(id);
        companyRepository.delete(company);
        return "redirect:/company/list";
    }

    @RequestMapping("/search")
    public String searchCompany( Model model, @RequestParam(value = "name") String name){
        List<Company> companies = companyRepository.findByNameContaining(name);
        model.addAttribute("companies", companies);
        return "companyList";
    }

    @RequestMapping("/asc")
    public String sortAscendingByName( Model model){
        List<Company> companies = companyRepository.findAllByOrderByNameAsc();
        model.addAttribute("companies", companies);
        return "companyList";
    }

    @RequestMapping("/desc")
    public String sortDescendingByName( Model model){
        List<Company> companies = companyRepository.findAllByOrderByNameDesc();
        model.addAttribute("companies", companies);
        return "companyList";
    }
}
