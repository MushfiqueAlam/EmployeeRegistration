package com.employee_registration.controller;

import com.employee_registration.model.Employee;
import com.employee_registration.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/")
    public String viewHomePage(Model model){
        return findPaginated(1,"firstName","asc",model);
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model){
        Employee employee=new Employee();
        model.addAttribute("employee",employee);
        return "new_employee";
    }


    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute ("employee") Employee employee){
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable long id,Model model){
        Employee employee=employeeService.getEmployeeByEmployeeId(id);
        model.addAttribute("employee",employee);
        return "update_employee";
    }

    @GetMapping("/deleteEmployee/{id}")
    private String deleteEmployee(@PathVariable long id){
        employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }

    @GetMapping("/page/{pageNo}")
    private String findPaginated(@PathVariable (value="pageNo") int pageNo,
                                 @RequestParam(value = "sortField") String sortField,
                                 @RequestParam (value = "sortDir") String sortDir, Model model) {

        int pageSize=5;
        Page<Employee> page=employeeService.findPaginated(pageNo,pageSize,sortField,sortDir);
        List<Employee> listEmployees=page.getContent();

        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());

        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir",sortDir.equals("asc")?"desc":"asc");

        model.addAttribute("listEmployees",listEmployees);

        return "index";


    }


}
