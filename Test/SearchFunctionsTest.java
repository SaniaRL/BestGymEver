import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SearchFunctionsTest {

    String magnusLarssonSocialSecurityNumber = "7002121234";
    GymMember magnusLarsson = new GymMember("Magnus Larsson", magnusLarssonSocialSecurityNumber, LocalDate.of(2023, 2,28));

    String rebecccaJohanssonSocialSecurityNumber = "9204056666";
    GymMember rebeccaJohansson = new GymMember("Rebecca Johansson", rebecccaJohanssonSocialSecurityNumber, LocalDate.of(2021, 10,12));

    String tereseBlomstromSocialSecurityNumber = "9205310001";
    GymMember tereseBlomstrom = new GymMember("Terese Blomström", tereseBlomstromSocialSecurityNumber, LocalDate.of(2022, 10,15));

    SearchFunctions sf = new SearchFunctions();
    List<GymMember> gymMemberList = new ArrayList<>();

    @BeforeEach
    void addGymMembers(){
        gymMemberList.clear();
        gymMemberList.add(magnusLarsson);
        gymMemberList.add(rebeccaJohansson);
        gymMemberList.add(tereseBlomstrom);
    }

    @Test
    void checkIfDigitsTest(){
        assert(sf.checkIfDigits("1992-06-18"));
        assertNotEquals(false, sf.checkIfDigits("12"));
        assert(!sf.checkIfDigits("Huh19"));
    }

    @Test
    void searchGymMemberByNameTest(){
        //assert(sf.searchGymMemberByName("Magnus Larsson", gymMemberList).equals(magnusLarsson));
        assert(sf.searchGymMemberByName("rebecca johansson  ", gymMemberList).equals(rebeccaJohansson));
        assert(!(sf.searchGymMemberByName("magnus larsson", gymMemberList).equals(tereseBlomstrom)));
        assert(sf.searchGymMemberByName(" terese blomström ", gymMemberList) != null);
        assert(sf.searchGymMemberByName("Kenny Starfighter", gymMemberList) == null);
    }
    @Test
    void searchGymMemberBySocialSecurityNumberTest(){
        assert(sf.searchGymMemberBySocialSecurityNumber(magnusLarssonSocialSecurityNumber, gymMemberList).equals(magnusLarsson));
        assert(sf.searchGymMemberBySocialSecurityNumber(rebecccaJohanssonSocialSecurityNumber, gymMemberList).equals(rebeccaJohansson));
        assert(!(sf.searchGymMemberBySocialSecurityNumber(magnusLarssonSocialSecurityNumber, gymMemberList).equals(tereseBlomstrom)));
        assert(sf.searchGymMemberBySocialSecurityNumber(tereseBlomstromSocialSecurityNumber, gymMemberList) != null);
        assert(sf.searchGymMemberBySocialSecurityNumber("123123123123hu", gymMemberList) == null);
    }
 /*   @Test
    void searchGymMemberByName() {

        assert((sf.searchGymMemberByName(" magnus", gMList)).);
        assert(!sf.searchGymMemberByName("Terese ", gMList).isEmpty());
        assert(sf.searchGymMemberByName("Bob", gMList).isEmpty());

        assert(!(sf.searchGymMemberByName("rebecca johansson  ", gymMemberList).contains(tereseBlomstrom)));
        assert(sf.searchGymMemberByName("magnus larsson", gymMemberList).contains(magnusLarsson));
        assert(sf.searchGymMemberByName("bob", gymMemberList).isEmpty());*/

}