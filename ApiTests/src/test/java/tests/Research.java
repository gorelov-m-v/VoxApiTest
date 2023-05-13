package tests;

import model.constants.Paths;
import model.constants.PlatformErrors;
import model.database.SmsHistory;
import model.http.sms.received.ReceivedSmsMQDataSet;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static model.cucumber.definitions.DefinitionsBase.app;

public class Research {

    @Test
    public void smoke() {
        System.out.println(PlatformErrors.getError(113).getCode());
        System.out.println(PlatformErrors.getError(464).getCode());
        System.out.println(Paths.ADD_ACCOUNT);


//        app.generate().randomSmsSendingInfoMQDataSet2(smsHistory);
        String uuid = "fsdkjfhsdkjfh";
        ReceivedSmsMQDataSet receivedSmsMQDataSet =
                new ReceivedSmsMQDataSet().withReceivedDate(LocalDate.now().toString())
                                .withSourceNumber("sdfsfsdf")
                                .withDestinationNumber("32466905403")
                                .withFragmentsCount(1)
                                .withMessage("sdfssdsdf")
                                .withUuid(uuid)
                                ;

        System.out.println(app.generate().randomReceivedSmsMQDataSet2(receivedSmsMQDataSet));
        System.out.println(app.generate().randomReceivedSmsMQDataSet(receivedSmsMQDataSet));
//        System.out.println(app.generate().randomReceivedSmsMQDataSet3(receivedSmsMQDataSet));
    }
}
