package MicroservicioCasas.infrastructure.exceptionshandler;

public final class ExceptionConstants {

    private ExceptionConstants(){}

    public static final String NAME_MAX_SIZE_MESSAGE = "The name of the category can not exceed 50 characters";
    public static final String DESCRIPTION_MAX_SIZE_MESSAGE = "The description of the category can not exceed 90 characters";
    public static final String CATEGORY_EXISTS_EXCEPTION = "The category already exists";
    public static final String PAGE_NUMBER_LESS_THAN_ZERO = "The page number cannot be less than zero";
    public static final String PAGE_SIZE_LESS_THAN_ONE = "The page size cannot be less than one";

    //location
    public static final String LOCATION_EXISTS_EXCEPTION = "Ya existe esta ubicacion";
}
