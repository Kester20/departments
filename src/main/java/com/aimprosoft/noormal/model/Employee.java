package com.aimprosoft.noormal.model;

import com.aimprosoft.noormal.util.Constants;
import com.aimprosoft.noormal.validator.EmployeeUniqueEmailValidator;
import net.sf.oval.constraint.CheckWith;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

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

import static com.aimprosoft.noormal.util.Constants.Messages.MUST_BE_LESS_THEN_30;
import static com.aimprosoft.noormal.util.Constants.ServiceConstants.DATE_FORMAT;

/**
 * @author Arsalan
 */
@Entity
@Table(name = Constants.DbConstants.EMPLOYEE)
public class Employee {

    private Long employeeId;
    @NotEmpty(message = Constants.Messages.MUST_NOT_BE_EMPTY)
    @NotNull(message = Constants.Messages.MUST_NOT_BE_EMPTY)
    @Length(max = 30, message = MUST_BE_LESS_THEN_30)
    private String name;

    @NotEmpty(message = Constants.Messages.MUST_NOT_BE_EMPTY)
    @NotNull(message = Constants.Messages.MUST_NOT_BE_EMPTY)
    private Integer age;

    @NotEmpty(message = Constants.Messages.MUST_NOT_BE_EMPTY)
    @NotNull(message = Constants.Messages.MUST_NOT_BE_EMPTY)
    @DateTimeFormat(pattern = DATE_FORMAT)
    private Date dateOfBirth;

    @CheckWith(value = EmployeeUniqueEmailValidator.class, message = Constants.Messages.EMPLOYEE_WITH_THIS_EMAIL_IS_ALREADY_EXIST)
    @NotEmpty(message = Constants.Messages.MUST_NOT_BE_EMPTY)
    @NotNull(message = Constants.Messages.MUST_NOT_BE_EMPTY)
    @Length(max = 30, message = MUST_BE_LESS_THEN_30)
    private String email;

    private Department department;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long id) {
        this.employeeId = id;
    }

    @Basic
    @Column(name = Constants.ServiceConstants.NAME)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = Constants.ServiceConstants.AGE)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = Constants.ServiceConstants.DATE_OF_BIRTH)
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Basic
    @Column(name = Constants.ServiceConstants.EMAIL)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ManyToOne
    @JoinColumn(name = Constants.DbConstants.DEPARTMENT)
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

        if (!employeeId.equals(employee.employeeId)) return false;
        if (!name.equals(employee.name)) return false;
        if (!age.equals(employee.age)) return false;
        if (!dateOfBirth.equals(employee.dateOfBirth)) return false;
        if (!email.equals(employee.email)) return false;
        return department.equals(employee.department);
    }

    @Override
    public int hashCode() {
        int result = employeeId.hashCode();
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
                "id=" + employeeId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", departmentId=" + department +
                '}';
    }
}
