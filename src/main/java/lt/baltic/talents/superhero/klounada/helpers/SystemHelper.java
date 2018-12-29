package lt.baltic.talents.superhero.klounada.helpers;

public class SystemHelper {

    public static String getSystemInformation(String variable) {
        return System.getProperty(variable);
    }

}
