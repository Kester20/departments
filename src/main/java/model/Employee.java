package model;

import net.sf.oval.constraint.CheckWith;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;
import validator.EmployeeValidator;

import java.time.LocalDate;

import static util.Constants.Messages.EMPLOYEE_WITH_THIS_EMAIL_IS_ALREADY_EXIST;
import static util.Constants.Messages.MUST_NOT_BE_EMPTY;

/**
 * @author Arsalan
 */
public class Employee {

    private Integer id;
    @NotEmpty(message = MUST_NOT_BE_EMPTY)
    @NotNull(message = MUST_NOT_BE_EMPTY)
    private String name;

    @NotEmpty(message = MUST_NOT_BE_EMPTY)
    @NotNull(message = MUST_NOT_BE_EMPTY)
    private Integer age;

    @NotEmpty(message = MUST_NOT_BE_EMPTY)
    @NotNull(message = MUST_NOT_BE_EMPTY)
    private LocalDate dateOfBirth;

    @CheckWith(value = EmployeeValidator.class, message = EMPLOYEE_WITH_THIS_EMAIL_IS_ALREADY_EXIST)
    @NotEmpty(message = MUST_NOT_BE_EMPTY)
    @NotNull(message = MUST_NOT_BE_EMPTY)
    @Length(max = 30)
    private String email;

    private Integer departmentId;

    public Employee() {

    }

    public Employee(Integer id, String name, Integer age, LocalDate dateOfBirth, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (!id.equals(employee.id)) return false;
        if (!name.equals(employee.name)) return false;
        if (!age.equals(employee.age)) return false;
        if (!dateOfBirth.equals(employee.dateOfBirth)) return false;
        if (!email.equals(employee.email)) return false;
        return departmentId.equals(employee.departmentId);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + age.hashCode();
        result = 31 * result + dateOfBirth.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + departmentId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", departmentId=" + departmentId +
                '}';
    }
}
