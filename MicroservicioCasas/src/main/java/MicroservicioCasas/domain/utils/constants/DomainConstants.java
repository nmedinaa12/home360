package MicroservicioCasas.domain.utils.constants;

public final class DomainConstants {



    private DomainConstants() {
        throw new IllegalStateException("Utility class");
    }

    //Category
    public static final String FIELD_NAME_NULL_MESSAGE = "Field 'name' can not be null";
    public static final String FIELD_DESCRIPTION_NULL_MESSAGE = "Field 'description' can not be null";

    //Location
    public static final String FIELD_CITY_NULL_MESSAGE = "Field 'city' can not be null";
    public static final String FIELD_DEPARTMENT_NULL_MESSAGE = "Field 'department' can not be null";
}
