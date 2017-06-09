package com.aimprosoft.noormal.model;

import com.aimprosoft.noormal.validator.DepartmentUniqueNameValidator;
import net.sf.oval.constraint.CheckWith;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import static com.aimprosoft.noormal.util.Constants.DbConstants.DEPARTMENT;
import static com.aimprosoft.noormal.util.Constants.Messages.DEPARTMENT_WITH_THIS_NAME_IS_ALREADY_EXIST;
import static com.aimprosoft.noormal.util.Constants.Messages.MUST_NOT_BE_EMPTY;
import static com.aimprosoft.noormal.util.Constants.ServiceConstants.NAME;

/**
 * @author Arsalan
 */
@Entity
@Table(name = DEPARTMENT)
public class Department {

    private Long departmentId;
    @CheckWith(value = DepartmentUniqueNameValidator.class, message = DEPARTMENT_WITH_THIS_NAME_IS_ALREADY_EXIST)
    @NotEmpty(message = MUST_NOT_BE_EMPTY)
    @NotNull(message = MUST_NOT_BE_EMPTY)
    @Length(max = 30)
    private String name;

    public Department() {

    }

    public Department(Long departmentId, String name) {
        this.departmentId = departmentId;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long id) {
        this.departmentId = id;
    }

    @Basic
    @Column(name = NAME)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        if (!departmentId.equals(that.departmentId)) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = departmentId.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + departmentId +
                ", name='" + name + '\'' +
                '}';
    }
}
