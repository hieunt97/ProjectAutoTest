package hieu.data;

import org.testng.annotations.DataProvider;

public class DataTest {
    @DataProvider(name="data-login")
    public static Object[][] dataProviderMethod(){
        return new Object[][] {
                {"admin@mailinator.com", "123456"}
        };
    }

}
