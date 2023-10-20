import org.junit.jupiter.api.Test;
import java.time.LocalDate;

class GymMemberTest {
    GymMember gymMember = new GymMember("Kenny Starfighter", "7010121551",
            LocalDate.of(2020,1,1));

    @Test
    void getName() {
        assert(gymMember.getName().equals("Kenny Starfighter"));
        assert(!gymMember.getName().equals("Dr Deo"));
    }

    @Test
    void getSocialSecurityNr() {
        assert(gymMember.getSocialSecurityNr().equals("7010121551"));
        assert(!gymMember.getSocialSecurityNr().equals("1234567890"));
    }

    @Test
    void getDateOfLastPayment() {
        assert(gymMember.getDateOfLastPayment().equals(LocalDate.of(2020,1,1)));
        assert(!gymMember.getDateOfLastPayment().equals(LocalDate.of(2012,2,2)));
    }

    @Test
    void getDateOfMembershipDuration() {
        assert(gymMember.getDateOfMembershipDuration().equals(gymMember.getDateOfLastPayment().plusYears(1)));
        assert(!gymMember.getDateOfMembershipDuration().equals(gymMember.getDateOfLastPayment()));
    }

    @Test
    void setName() {
        assert(!gymMember.getName().equals("Bob"));
        gymMember.setName("Bob");
        assert(gymMember.getName().equals("Bob"));
    }

    @Test
    void setSocialSecurityNr() {
        assert(!gymMember.getSocialSecurityNr().equals("1231231231"));
        gymMember.setSocialSecurityNr("1231231231");
        assert(gymMember.getSocialSecurityNr().equals("1231231231"));
    }

    @Test
    void setDateOfLastPayment() {
        assert(!gymMember.getDateOfLastPayment().equals(LocalDate.of(2020, 8, 8)));
        gymMember.setDateOfLastPayment(LocalDate.of(2020,8,8));
        assert(gymMember.getDateOfLastPayment().equals(LocalDate.of(2020, 8, 8)));
    }
}