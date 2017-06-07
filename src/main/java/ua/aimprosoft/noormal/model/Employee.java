package ua.aimprosoft.noormal.model;

import net.sf.oval.constraint.CheckWith;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;
import ua.aimprosoft.noormal.validator.EmployeeUniqueEmailValidator;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

import static ua.aimprosoft.noormal.util.Constants.DbConstants.DEPARTMENT;
import static ua.aimprosoft.noormal.util.Constants.DbConstants.EMPLOYEE;
import static ua.aimprosoft.noormal.util.Constants.Messages.EMPLOYEE_WITH_THIS_EMAIL_IS_ALREADY_EXIST;
import static ua.aimprosoft.noormal.util.Constants.Messages.MUST_NOT_BE_EMPTY;
import static ua.aimprosoft.noormal.util.Constants.ServiceConstants.AGE;
import static ua.aimprosoft.noormal.util.Constants.ServiceConstants.DATE_OF_BIRTH;
import static ua.aimprosoft.noormal.util.Constants.ServiceConstants.EMAIL;
import static ua.aimprosoft.noormal.util.Constants.ServiceConstants.NAME;

/**
 * @author Arsalan
 */
@Entity
@Table(name = EMPLOYEE)
public class Employee {

    private Long id;
    @NotEmpty(message = MUST_NOT_BE_EMPTY)
    @NotNull(message = MUST_NOT_BE_EMPTY)
    private String name;

    @NotEmpty(message = MUST_NOT_BE_EMPTY)
    @NotNull(message = MUST_NOT_BE_EMPTY)
    private Integer age;

    @NotEmpty(message = MUST_NOT_BE_EMPTY)
    @NotNull(message = MUST_NOT_BE_EMPTY)
    private Date dateOfBirth;

    @CheckWith(value = EmployeeUniqueEmailValidator.class, message = EMPLOYEE_WITH_THIS_EMAIL_IS_ALREADY_EXIST)
    @NotEmpty(message = MUST_NOT_BE_EMPTY)
    @NotNull(message = MUST_NOT_BE_EMPTY)
    @Length(max = 30)
    private String email;

    private Department department;

    public Employee() {

    }

    public Employee(Long id, String name, Integer age, Date dateOfBirth, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = NAME)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = AGE)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = DATE_OF_BIRTH)
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Basic
    @Column(name = EMAIL)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ManyToOne
    @JoinColumn(name = DEPARTMENT)
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department departmentId) {
        this.department = departmentId;
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
        return department.equals(employee.department);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + age.hashCode();
        result = 31 * result + dateOfBirth.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + department.hashCode();
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
                ", departmentId=" + department +
                '}';
    }
}