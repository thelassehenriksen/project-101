package no.acntech.project101.employee.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import no.acntech.project101.company.config.CompanyDatabaseConfig;
import no.acntech.project101.employee.Employee;
import no.acntech.project101.employee.config.EmployeeDatabaseConfig;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@Import({EmployeeDatabaseConfig.class, CompanyDatabaseConfig.class})
@ContextConfiguration(classes = EmployeeRepository.class)
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository repository;

    @Test
    void save() {
        final Employee employee = new Employee("lol", "lolesen", LocalDate.of(1945, 1, 1), (long)123456);
        final Employee savedEmployee = repository.save(employee);
        assertThat(savedEmployee.getId()).isNotNull();
        assertThat(savedEmployee.getFirstName()).isEqualTo(employee.getFirstName());
        assertThat(savedEmployee.getLastName()).isEqualTo(employee.getLastName());
        assertThat(savedEmployee.getDateOfBirth()).isEqualTo(employee.getDateOfBirth());
    }
}
