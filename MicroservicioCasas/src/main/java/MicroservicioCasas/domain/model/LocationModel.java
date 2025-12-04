package MicroservicioCasas.domain.model;


import MicroservicioCasas.domain.exceptions.DescriptionDepMaxSizeExceededException;
import MicroservicioCasas.domain.exceptions.NameMaxSizeExceededException;
import MicroservicioCasas.domain.utils.constants.DomainConstants;

import java.util.Objects;

public class LocationModel {

    private final Long id;
    private String city;
    private String department;
    private String description;

    public LocationModel(Long id, String city, String department, String description){

        if (city.length() > 50) throw new NameMaxSizeExceededException();
        if (department.length() > 50) throw new NameMaxSizeExceededException();
        if (description.length() > 120) throw new DescriptionDepMaxSizeExceededException();
        this.id = id;
        this.city = Objects.requireNonNull(city, DomainConstants.FIELD_CITY_NULL_MESSAGE);
        this.department = Objects.requireNonNull(department, DomainConstants.FIELD_DEPARTMENT_NULL_MESSAGE);
        this.description = Objects.requireNonNull(description, DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
    }

    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getDepartment() {
        return department;
    }


    public String getDescription() {
        return description;
    }

}