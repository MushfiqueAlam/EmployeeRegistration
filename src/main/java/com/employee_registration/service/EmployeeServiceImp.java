package com.employee_registration.service;

import com.employee_registration.Repository.EmployeeRepository;
import com.employee_registration.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EmployeeServiceImp implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
       return employeeRepository.findAll();
    }

    @Override
    public void saveEmployee(Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeByEmployeeId(long id) {
       Optional<Employee>employee= employeeRepository.findById(id);
       if(employee.isPresent()){
           return employee.get();
       }else {
           throw new RuntimeException("Employee not found fot this is"+id);
       }
    }

    @Override
    public void deleteEmployeeById(long id) {
        this.employeeRepository.deleteById(id);
    }

    @Override
    public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort=sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField).ascending():Sort.by(sortField).descending();
        Pageable pageable= PageRequest.of(pageNo-1,pageSize);
        return this.employeeRepository.findAll(pageable);

    }
}
