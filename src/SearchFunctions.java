import java.util.List;

public class SearchFunctions {

    public boolean checkIfDigits(String input){
        input = input.replaceAll("-", "");
        for(int i = 0; i < input.length(); i++){
            if(!Character.isDigit(input.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public GymMember searchGymMemberByName(String name, List<GymMember> gymMemberList){
        for (GymMember gymMember : gymMemberList) {
            if(gymMember.getName().equalsIgnoreCase(name.trim())){
                return gymMember;
            }
        }
        return null;
    }

    public GymMember searchGymMemberBySocialSecurityNumber(String socialSecurityNumber, List<GymMember> gymMemberList){
        for (GymMember gymMember : gymMemberList) {
            if(gymMember.getSocialSecurityNr().equals(socialSecurityNumber.trim())){
                return gymMember;
            }
        }
        return null;
    }


   /* public List<GymMember> searchGymMemberByName(String name, List<GymMember> gymMemberList){
        List<GymMember> matchingGymMembers = new ArrayList<>();
        for (GymMember gymMember : gymMemberList) {
            if(gymMember.getName().contains(name.trim())){
                matchingGymMembers.add(gymMember);
            }
        }
        return matchingGymMembers;
    }

    */
}
