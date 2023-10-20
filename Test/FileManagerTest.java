import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileManagerTest {

    FileManager fileManager = new FileManager();
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd;HH:mm");
    GymMember magnusSvensk = new GymMember("Magnus Svensk", "9001010101",
            LocalDate.now().minusMonths(2));
    String testLog = "Magnus Svensk;9001010101;" + dateFormatter.format(LocalDateTime.now());
    String fileToString;
    List<GymMember> gymMemberList = new ArrayList<>();

    @Test
    void logTrainingSessionTest(){
        String readLine;
        String path = "Test/TrainingSessionLogTest.txt";
        fileManager.logTrainingSession(magnusSvensk, path);
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(path))){
            while((readLine = bufferedReader.readLine()) != null){
                fileToString = readLine;
            }
            assert(fileToString.equals(testLog));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void addGymMembersFromFileTest(){
        String path = "Test/DataTest.txt";
        fileManager.addGymMembersFromFile(path, gymMemberList);
        assert(!gymMemberList.isEmpty());
        assert(gymMemberList.get(0).getName().equals("Alhambra Aromes"));
    }
}
