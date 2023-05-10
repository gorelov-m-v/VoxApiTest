package model.cucumber.definitions;

import model.http.accounts.add.AddAccountDataSet;
import model.http.accounts.add.AddAccountResponse;
import model.http.accounts.editinfo.EditAccountInfoDataSet;
import model.http.accounts.setdocument.AccountDocumentDataSet;
import model.http.accounts.setdocument.SetAccountDocumentResponse;
import model.http.phonenumbers.attach.AttachPhoneNumberDataSet;
import model.http.phonenumbers.attach.AttachPhoneNumberResponse;

public class World {

    AddAccountDataSet addAccountDataSet;
    AddAccountResponse addAccountResponse;

    EditAccountInfoDataSet editAccountInfoDataSet;

    AccountDocumentDataSet accountDocumentDataSet;
    SetAccountDocumentResponse setAccountDocumentResponse;

    AttachPhoneNumberDataSet attachPhoneNumberDataSet;
    AttachPhoneNumberResponse attachPhoneNumberResponse;
}
