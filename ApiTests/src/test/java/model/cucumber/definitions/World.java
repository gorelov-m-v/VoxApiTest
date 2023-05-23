package model.cucumber.definitions;

import model.database.SmsHistory;
import model.http.accounts.add.AddAccountDataSet;
import model.http.accounts.add.AddAccountResponse;
import model.http.accounts.editinfo.EditAccountInfoDataSet;
import model.http.accounts.setdocument.AccountDocumentDataSet;
import model.http.accounts.setdocument.SetAccountDocumentResponse;
import model.http.phonenumbers.attach.AttachPhoneNumberDataSet;
import model.http.phonenumbers.attach.AttachPhoneNumberResponse;
import model.http.sms.control.ControlSmsDataSet;
import model.http.sms.received.ReceivedSMSRequest;
import model.http.sms.received.ReceiverSmsHTTPDataSet;
import model.http.sms.send.SendSmsMessageDataSet;
import model.http.sms.send.SendSmsMessageResponse;
import model.http.sms.send.SmsSendingInfoDataSet;
import model.http.universal.UniversalResponse;

public class World {
    AddAccountDataSet addAccountDataSet;
    AddAccountResponse addAccountResponse;

    EditAccountInfoDataSet editAccountInfoDataSet;

    AccountDocumentDataSet accountDocumentDataSet;
    SetAccountDocumentResponse setAccountDocumentResponse;

    AttachPhoneNumberDataSet attachPhoneNumberDataSet;
    AttachPhoneNumberResponse attachPhoneNumberResponse;

    ControlSmsDataSet controlSmsDataSet;
    UniversalResponse universalResponse;

    SendSmsMessageDataSet sendSmsMessageDataSet;
    SendSmsMessageResponse sendSmsMessageResponse;
    SmsHistory smsHistory;
    SmsSendingInfoDataSet smsSendingInfoDataSet;
    ReceiverSmsHTTPDataSet receivedSmsHTTPDataSet;
}
