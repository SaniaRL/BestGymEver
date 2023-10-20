import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class CheckStatus {

    Period checkTimeSinceLastPayment(GymMember gymMember){
        return Period.between(gymMember.getDateOfLastPayment(), LocalDate.now());
    }
    public boolean checkGymMemberStatus(GymMember gymMember){
        return checkTimeSinceLastPayment(gymMember).getYears() < 1;
    }

    public boolean checkGymMemberHistory(GymMember gymMember, List<GymMember> gymMemberList){
        for(GymMember gM : gymMemberList){
            if (gM.equals(gymMember)){
                return true;
            }
        }return false;
    }
}
