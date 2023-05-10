package model.cucumber.definitions;

import model.http.accounts.add.AddAccountRequest;
import model.http.accounts.editinfo.EditAccountInfoRequest;
import model.http.accounts.setdocument.SetAccountDocumentRequest;
import model.http.phonenumbers.attach.AttachPhoneNumberRequest;

public class DefinitionsBase {
    AddAccountRequest addAccountRequest = new AddAccountRequest();
    EditAccountInfoRequest editAccountInfoRequest = new EditAccountInfoRequest();
    SetAccountDocumentRequest setAccountDocumentRequest = new SetAccountDocumentRequest();
    AttachPhoneNumberRequest attachPhoneNumberRequest = new AttachPhoneNumberRequest();
}
