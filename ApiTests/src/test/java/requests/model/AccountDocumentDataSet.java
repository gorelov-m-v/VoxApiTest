package requests.model;

public class AccountDocumentDataSet {

    // Mandatory for 'INDIVIDUAL'
    private int account_id;
    private String api_key;
    private String legal_status;
    private boolean esia_verified;
    private int individual_passport_series;
    private int individual_passport_number;
    private String individual_passport_issued_by;
    private String individual_passport_issue_date;
    private String individual_full_name;
    private String individual_birth_date;
    private String individual_registration_address;
    private String individual_phone_number;
    private String document_delivery_address;
    private String email;

    public int getAccountId() {
        return account_id;
    }
    public String getApiKey() {
        return api_key;
    }
    public String getLegalStatus() {
        return legal_status;
    }
    public boolean getEsiaVerified() {
        return esia_verified;
    }
    public int getIndividualPassportSeries() {
        return individual_passport_series;
    }
    public int getIndividualPassportNumber() {
        return individual_passport_number;
    }
    public String getIndividualPassportIssued_by() {
        return individual_passport_issued_by;
    }
    public String getIndividualPassportIssueDate() {
        return individual_passport_issue_date;
    }
    public String getIndividualFullName() {
        return individual_full_name;
    }
    public String getIndividualBirthDate() {
        return individual_birth_date;
    }
    public String getIndividualRegistrationAddress() {
        return individual_registration_address;
    }
    public String getIndividualPhoneNumber() {
        return individual_phone_number;
    }
    public String getDocumentDeliveryAddress() {
        return document_delivery_address;
    }
    public String getEmail() {
        return email;
    }

    public AccountDocumentDataSet withAccountId(int account_id) {
        this.account_id = account_id;
        return this;
    }
    public AccountDocumentDataSet withApiKey(String api_key) {
        this.api_key = api_key;
        return this;
    }
    public AccountDocumentDataSet withLegalStatus(String legal_status) {
        this.legal_status = legal_status;
        return this;
    }
    public AccountDocumentDataSet withEsiaVerified(boolean esia_verified) {
        this.esia_verified = esia_verified;
        return this;
    }
    public AccountDocumentDataSet withIndividualPassportSeries(int individual_passport_series) {
        this.individual_passport_series = individual_passport_series;
        return this;
    }
    public AccountDocumentDataSet withIndividualPassportNumber(int individual_passport_number) {
        this.individual_passport_number = individual_passport_number;
        return this;
    }
    public AccountDocumentDataSet withIndividualPassportIssuedBy(String individual_passport_issued_by) {
        this.individual_passport_issued_by = individual_passport_issued_by;
        return this;
    }
    public AccountDocumentDataSet withIndividualPassportIssueDate(String individual_passport_issue_date) {
        this.individual_passport_issue_date = individual_passport_issue_date;
        return this;
    }
    public AccountDocumentDataSet withIndividualFullName(String individual_full_name) {
        this.individual_full_name = individual_full_name;
        return this;
    }
    public AccountDocumentDataSet withIndividualBirthDate(String individual_birth_date) {
        this.individual_birth_date = individual_birth_date;
        return this;
    }
    public AccountDocumentDataSet withIndividualRegistrationAddress(String individual_registration_address) {
        this.individual_registration_address = individual_registration_address;
        return this;
    }
    public AccountDocumentDataSet withIndividualPhoneNumber(String individual_phone_number) {
        this.individual_phone_number = individual_phone_number;
        return this;
    }
    public AccountDocumentDataSet withDocumentDeliveryAddress(String document_delivery_address) {
        this.document_delivery_address = document_delivery_address;
        return this;
    }
    public AccountDocumentDataSet withEmail(String email) {
        this.email = email;
        return this;
    }
}
