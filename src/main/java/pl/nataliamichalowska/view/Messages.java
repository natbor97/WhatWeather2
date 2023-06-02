package pl.nataliamichalowska.view;

public class Messages {
    private static final String emptyLocations= "Podaj nazwy miast!";
    private static final String emptyYourLocation= "Podaj nazwę swojego miasta!";
    private static final String emptyPurposeLocation= "Podaj nazwę miasta docelowego!";
    private static final String noCityInDatabase = "Podanego miasta nie ma w bazie.";

    private static final String apiError = "Błąd serwera, spróbuj ponownie później";

    public String getEmptyLocations(){
        return emptyLocations;
    }

    public String getEmptyYourLocation(){
        return emptyYourLocation;
    }

    public String getEmptyPurposeLocation(){
        return emptyPurposeLocation;
    }

    public String getNoCityInDatabase(){
        return noCityInDatabase;
    }

    public String getApiError(){
        return apiError;
    }
}
