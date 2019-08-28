package no.acntech.project101.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import no.acntech.project101.employee.Employee;
//import no.acntech.project101.company.consumer.BrregRestClient;
import no.acntech.project101.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee save(final Employee employee) {
        return this.employeeRepository.save(employee);
    }

    public Optional<Employee> findById(final Long id) {
        return this.employeeRepository.findById(id);
    }

    public List<Employee> findAll() {
        return this.employeeRepository.findAll();
    }

    public void delete(final Long id) {
        if (this.employeeRepository.existsById(id)) {
            this.employeeRepository.deleteById(id);
        }
    }
}
