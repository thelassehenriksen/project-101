package no.acntech.project101.web.employee.resources;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import no.acntech.project101.web.company.resources.CompanyDto;
import no.acntech.project101.web.company.resources.converter.CompanyConverter;
import no.acntech.project101.web.company.resources.converter.CompanyDtoConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import no.acntech.project101.company.Company;
import no.acntech.project101.company.service.CompanyService;
import no.acntech.project101.employee.Employee;
import no.acntech.project101.employee.service.EmployeeService;
import no.acntech.project101.web.employee.resources.converter.EmployeeConverter;
import no.acntech.project101.web.employee.resources.converter.EmployeeDtoConverter;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("employees")
//TODO This is a REST controler and should receive request on path employees
public class EmployeeResource {
    private final EmployeeService employeeService;
    private final EmployeeDtoConverter employeeDtoConverter;
    private final EmployeeConverter employeeConverter;
//    private List<EmployeeDto> employees = new ArrayList<>();

    //TODO The constructor needs to accept the required dependencies and assign them to class variables
    public EmployeeResource(final EmployeeService employeeService,
                            final EmployeeDtoConverter employeeDtoConverter,
                            final EmployeeConverter employeeConverter) {
        this.employeeService = employeeService;
        this.employeeDtoConverter = employeeDtoConverter;
        this.employeeConverter = employeeConverter;
//        this.employees.add(new EmployeeDto(Long.valueOf(1), "Donald", "Trump", LocalDate.now(), Long.valueOf(1234567890)));
//        this.employees.add(new EmployeeDto(Long.valueOf(2), "Donald", "Trump", LocalDate.now(), Long.valueOf(1234567890)));
//        this.employees.add(new EmployeeDto(Long.valueOf(3), "Donald", "Trump", LocalDate.now(), Long.valueOf(1234567890)));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> findAll() {
        final List<Employee> employees = this.employeeService.findAll();
        final List<EmployeeDto> collect = employees.stream()
                .map(this.employeeDtoConverter::convert)
                .collect(Collectors.toList());

        return ResponseEntity.ok(collect);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> findById(@PathVariable final Long id) {
        final Optional<Employee> employee = this.employeeService.findById(id);

        if (employee.isPresent()) {
            final EmployeeDto convert = this.employeeDtoConverter.convert(employee.get());
            return ResponseEntity.ok(convert);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity createEmployee(@RequestBody final EmployeeDto employeeDto) {
        final Employee convert = this.employeeConverter.convert(employeeDto);
        final Employee saved = this.employeeService.save(convert);
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteEmployee() {
        final Optional<Employee> employee = this.employeeService.findById(id);

        if (employee.isPresent()) {
            this.employeeService.delete(id);
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity updateEmployee(@PathVariable final Long id, @RequestBody final EmployeeDto employeeDto) {
        final Optional<Employee> optionalEmployee = this.employeeService.findById(id);

        if (optionalEmployee.isPresent()) {
            Employee existingCompany = optionalEmployee.get();
            existingCompany.setFirstName(employeeDto.getFirstName());
            existingCompany.setLastName(employeeDto.getLastName());
            existingCompany.setDateOfBirth(employeeDto.getDateOfBirth());
            existingCompany.setCompanyId(employeeDto.getCompanyId());

            Employee saved = this.employeeService.save(existingCompany);

            final EmployeeDto convert = this.employeeDtoConverter.convert(saved);
            return ResponseEntity.ok(convert);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
