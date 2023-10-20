import java.time.LocalDate;

public class GymMember {

    private String name;
    private String socialSecurityNr;
    private LocalDate dateOfLastPayment;


    public GymMember(String name, String socialSecurityNr, LocalDate dateOfLastPayment) {
        this.name = name;
        this.socialSecurityNr = socialSecurityNr;
        this.dateOfLastPayment = dateOfLastPayment;
    }

    //region Getters
    public String getName() {
        return this.name;
    }
    public String getSocialSecurityNr() {
        return socialSecurityNr;
    }
    public LocalDate getDateOfLastPayment() {
        return dateOfLastPayment;
    }
    public LocalDate getDateOfMembershipDuration(){return dateOfLastPayment.plusYears(1);}
    public void setName(String name) {this.name = name;}
    public void setSocialSecurityNr(String socialSecurityNr) {this.socialSecurityNr = socialSecurityNr;}
    public void setDateOfLastPayment(LocalDate dateOfLastPayment) {this.dateOfLastPayment = dateOfLastPayment;}
    //endregion
}