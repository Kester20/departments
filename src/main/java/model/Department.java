package model;

import net.sf.oval.constraint.CheckWith;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;
import validator.DepartmentValidator;

import static util.Constants.Messages.DEPARTMENT_WITH_THIS_NAME_IS_ALREADY_EXIST;
import static util.Constants.Messages.MUST_NOT_BE_EMPTY;

/**
 * @author Arsalan
 */
public class Department {

    private Integer id;
    @CheckWith(value = DepartmentValidator.class, message = DEPARTMENT_WITH_THIS_NAME_IS_ALREADY_EXIST)
    @NotEmpty(message = MUST_NOT_BE_EMPTY)
    @NotNull(message = MUST_NOT_BE_EMPTY)
    @Length(max = 30)
    private String name;

    public Department() {

    }

    public Department(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        if (id != that.id) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
