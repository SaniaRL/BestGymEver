import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FileManager {

    //TODO Remove Try Catch

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd;HH:mm");

    public void logTrainingSession(GymMember gymMember, String path){
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, true))){
            bufferedWriter.write(gymMember.getName() + ";" + gymMember.getSocialSecurityNr() + ";" +
                    dateFormatter.format(LocalDateTime.now()));
            bufferedWriter.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addGymMembersFromFile(String path, List<GymMember> gymMemberList) throws RuntimeException{
        String temp;
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(path))){
            while((temp = bufferedReader.readLine()) != null){
                String[] nameSocialSecurityNr = temp.split(",");
                temp = bufferedReader.readLine();
                LocalDate dateOfLastPayment = LocalDate.parse(temp);
                GymMember gymMember = new GymMember(nameSocialSecurityNr[1].trim(), nameSocialSecurityNr[0].trim(), dateOfLastPayment);
                gymMemberList.add(gymMember);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printTrainingSessionLog(String path) throws FileNotFoundException {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(path))){
            String temp;
            /*if((temp = bufferedReader.readLine()) == null) {
                System.out.printf("\n%-40s", "No Training Session Logged");
            }
            else{*/
                while((temp = bufferedReader.readLine()) != null){
                    String[] strings = temp.split(";");
                    System.out.printf("\n%-40s%-40s%-20s%-20s", strings[0], strings[1], strings[2], strings[3]);
                }
            //}
        }catch(IOException e){
            System.out.println("File Can Not Be Found");
            e.printStackTrace(System.err);
        }
    }
}
