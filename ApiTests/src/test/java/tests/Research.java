package tests;

import model.constants.Paths;
import model.constants.PlatformErrors;
import org.testng.annotations.Test;

public class Research {

    @Test
    public void smoke() {
        System.out.println(PlatformErrors.getError(113).getCode());
        System.out.println(PlatformErrors.getError(464).getCode());
        System.out.println(Paths.ADD_ACCOUNT);
    }
}
