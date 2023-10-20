import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckStatusTest {

    CheckStatus checkStatus = new CheckStatus();

    GymMember magnusLarsson = new GymMember("Magnus Larsson", "7002121234",
            LocalDate.of(2023, 2,28));

    GymMember rebeccaJohansson = new GymMember("Rebecca Johansson", "9204056666",
            LocalDate.of(2021, 8,12));

    GymMember tereseBlomstrom = new GymMember("Terese Blomström", "9205310001",
            LocalDate.of(2022, 10,15));

    //Kan de vara negativa ? Ja, men är det funktionellt

    List<GymMember> gymMemberList = new ArrayList<>();

    @BeforeEach
    void addToGymMemberList(){
        gymMemberList.add(magnusLarsson);
        gymMemberList.add(rebeccaJohansson);
    }

    @Test
    public void checkGymMemberStatusTest(){
        assert(checkStatus.checkGymMemberStatus(magnusLarsson));
        assertFalse(checkStatus.checkGymMemberStatus(rebeccaJohansson));
    }

    @Test
    void checkTimeSinceLastPaymentTest(){
        assert(checkStatus.checkTimeSinceLastPayment(tereseBlomstrom).equals(Period.between
                (tereseBlomstrom.getDateOfLastPayment(),LocalDate.now())));
    }

    @Test
    void checkGymMemberHistoryTest(){
        assertTrue(checkStatus.checkGymMemberHistory(magnusLarsson, gymMemberList));
        assertFalse(checkStatus.checkGymMemberHistory(tereseBlomstrom, gymMemberList));
    }
}
