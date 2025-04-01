package com.employee_registration.service;

import com.employee_registration.model.Employee;
import org.springframework.data.domain.Page;


import java.util.List;


public interface EmployeeService {
    List<Employee> getAllEmployees();
    void saveEmployee(Employee employee);
    Employee getEmployeeByEmployeeId(long id);
    void deleteEmployeeById(long id);
    Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
