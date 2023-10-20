import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestData {
    String magnusLarssonSocialSecurityNumber = "7002121234";
    GymMember magnusLarsson = new GymMember("Magnus Larsson", magnusLarssonSocialSecurityNumber, LocalDate.of(2023, 2,28));

    String rebecccaJohanssonSocialSecurityNumber = "9204056666";
    GymMember rebeccaJohansson = new GymMember("Rebecca Johansson", rebecccaJohanssonSocialSecurityNumber, LocalDate.of(2021, 10,12));

    String tereseBlomstromSocialSecurityNumber = "9205310001";
    GymMember tereseBlomstrom = new GymMember("Terese Blomström", tereseBlomstromSocialSecurityNumber, LocalDate.of(2022, 10,15));

    public void addTestGymMembers(List<GymMember> gymMemberList){
        gymMemberList.add(magnusLarsson);
        gymMemberList.add(rebeccaJohansson);
        gymMemberList.add(tereseBlomstrom);
    }
}
