import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainProgram {
    private final Scanner scanner;
    private final List<GymMember> gymMemberList;
    private final CheckStatus checkStatus;
    private final String filePathData;
    private final String filePathTrainingLog;
    private final SearchFunctions searchFunctions;

    public MainProgram(String filePathData, String filePathTrainingLog) {
        this.filePathData = filePathData;
        this.filePathTrainingLog = filePathTrainingLog;
        this.scanner = new Scanner(System.in);
        this.gymMemberList = new ArrayList<>();
        this.checkStatus = new CheckStatus();
        this.searchFunctions = new SearchFunctions();
    }

    public static void main(String[] args) {
        MainProgram mainProgram = new MainProgram("src/Data.txt", "src/TrainingSessionLog.txt");
        mainProgram.mainProgram();
    }

    private void mainProgram() {
        try {
            boolean loop = true;
            while (loop) {
                FileManager fileManager = new FileManager();
                fileManager.addGymMembersFromFile(filePathData, gymMemberList);

                switch (startMenu()) {
                    case "1" -> menuSearchGymMember();
                    case "2" -> addNewGymMember();
                    case "3" -> fileManager.printTrainingSessionLog(filePathTrainingLog);
                    case "4" -> loop = false;
                    default -> System.out.printf("\n%-40s", "[Choose a Valid Option]");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("[File Not Found]");
            e.printStackTrace(System.err);

        } catch (Exception e) {
            System.out.println("[Unknown Error]");
            e.printStackTrace(System.err);
        }
    }

    private String startMenu() {
        System.out.printf("\n\n%-5d%-40s\n%-5d%-40s\n%-5d%-40s\n%-5d%-40s\n\n%-60s\n", 1, "Search Gym Member",
                2, "Add New Gym Member", 3, "Check Training Session Logs", 4, "Exit Program",
                "[Choose Option By Typing Corresponding Number And Pressing Enter]");

        String input = scanner.nextLine().trim();
        while (!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4")) {
            System.out.printf("%-60s\n", "[Choose a Valid Option]");
            input = scanner.nextLine().trim();
        }
        return input;
    }

    private GymMember searchGymMember() {
        GymMember gymMember = null;
        while (gymMember == null) {
            System.out.printf("\n\n%-40s", "Type Full Name or Social Security Number: ");
            String input = scanner.nextLine();
            if (searchFunctions.checkIfDigits(input)) {
                gymMember = searchFunctions.searchGymMemberBySocialSecurityNumber(input, gymMemberList);
            } else {
                gymMember = searchFunctions.searchGymMemberByName(input, gymMemberList);
            }
            if (gymMember == null) {
                System.out.printf("\n%-40s", "This Person Has Never Been A Registered Gym Member at BestGymEver");
                System.out.printf("\n\n%-5d%-40s\n%-5d%-40s\n%-5d%-40s\n%-5d%-40s\n\n%-60s\n", 1, "New Search",
                        2, "Add New Gym Member", 3, "Main Menu", 4, "Exit Program",
                        "[Choose Option By Typing Corresponding Number And Pressing Enter]");
                input = scanner.nextLine().trim();
                switch (input) {
                    case "1" -> menuSearchGymMember();
                    case "2" -> addNewGymMember();
                    case "3" -> startMenu();
                    case "4" -> exitProgram();
                    default -> System.out.printf("\n%-40s", "[Choose a Valid Option]");
                }
            }
        }
        return gymMember;
    }

    private void menuSearchGymMember() {
        GymMember gymMember = searchGymMember();
        System.out.printf("\n\n%-40s%-40s%-20s\n%-40s%-40s%-20s", "Name", "Social Security Number", "Date Of Last Payment",
                gymMember.getName(), gymMember.getSocialSecurityNr(), gymMember.getDateOfLastPayment());
        if (checkStatus.checkGymMemberStatus(gymMember)) {
            logTrainingSession(gymMember);
            System.out.printf("\n\n%-40s\n%-40s\n%-40s", gymMember.getName() + " is a Registered Member",
                    "Valid through " + gymMember.getDateOfMembershipDuration(), "Training Session Logged");
        } else {
            System.out.printf("\n\n%-40s\n%-40s\n\n%-5d%-40s\n%-5d%-40s\n%-5d%-40s\n\n%-60s\n", gymMember.getName() +
                            " is a Former Member", "Valid through " + gymMember.getDateOfMembershipDuration(),
                    1, "Pay The Yearly Premium To Renew Membership?", 2, "Main Menu", 3, "Exit Program",
                    "[Choose Option By Typing Corresponding Number And Pressing Enter]");
            String input = "hello";
            while(!input.equals("1") && !input.equals("2")){
                input = scanner.nextLine().trim();
                switch (input){
                    case "1" -> payYearlyPremium(gymMember);
                    case "2" -> startMenu();
                    case "3" -> exitProgram();
                    default ->  System.out.printf("\n%-40s", "[Choose a Valid Option]");
                }
            }
        }
        /*String input = "";
        boolean loop = true;
        while (!input.equals("1") && !input.equals("2") && !input.equals("3") && loop){
            System.out.printf("\n\n%-5d%-40s\n%-5d%-40s\n%-5d%-40s\n%-5d%-40s\n\n%-60s\n", 1, "Log Training Session",
                    2, "Pay Yearly Premium", 3, "Main Menu", 4, "Exit Program",
                    "[Choose Option By Typing Corresponding Number And Pressing Enter]");

            input = scanner.nextLine().trim();
            switch (input) {
                case "1" -> {
                    logTrainingSession(gymMember);
                    loop = false;
                }
                case "2" -> {
                    payYearlyPremium(gymMember);
                    loop = false;
                }
                case "3" -> {
                    startMenu();
                    loop = false;
                }
                case "4" -> exitProgram();
                default -> System.out.printf("\n%-40s", "[Choose a Valid Option]");
            }
        }

         */
    }

    private void logTrainingSession(GymMember gymMember) {
        boolean loop = true;
        while (loop) {
            if (gymMember.getDateOfMembershipDuration().isAfter(LocalDate.now())) {
                FileManager fileManager = new FileManager();
                fileManager.logTrainingSession(gymMember, filePathTrainingLog);
                System.out.printf("\n\n%-40s\n", "Training Session Logged");
                loop = false;
            } else {
                System.out.printf("\n\n%-40s\n", "Pay Yearly Premium To Use Gym");
                System.out.printf("\n\n%-5d%-40s\n%-5d%-40s\n%-5d%-40s\n\n%-60s\n", 1, "Pay Yearly Premium",
                        2, "Main Menu", 3, "Exit Program",
                        "[Choose Option By Typing Corresponding Number And Pressing Enter]");
                String input = scanner.nextLine().trim();
                switch (input) {
                    case "1" -> {
                        payYearlyPremium(gymMember);
                        loop = false;
                    }
                    case "2" -> {
                        startMenu();
                        loop = false;
                    }
                    case "3" -> exitProgram();
                    default -> System.out.printf("\n%-40s", "[Choose a Valid Option]");
                }
            }
        }
    }

    private void payYearlyPremium(GymMember gymMember) {
        if (gymMember.getDateOfMembershipDuration().isAfter(LocalDate.now())) {
            gymMember.setDateOfLastPayment(gymMember.getDateOfLastPayment().plusYears(1));
            System.out.printf("\n\n%-40s\n%-40s", gymMember.getName() + " is a Registered Member",
                    "Valid Through " + gymMember.getDateOfLastPayment().plusYears(1));
        } else {
            gymMember.setDateOfLastPayment(LocalDate.now());
        }
        //There should be a variable for how long the membership is valid, bc the payment date
        //saved does not have to match the actual payment date, and could be in the future
        System.out.printf("\n\n%-40s\n\n%-40s%-20s", "Yearly Premium Payed", "Gym Membership Valid Through",
                gymMember.getDateOfMembershipDuration());
    }

    private void exitProgram() {
        System.exit(0);
    }

    private void addNewGymMember() {
        String name;
        while (true) {
            System.out.printf("\n\n%-40s", "Full name: ");
            name = scanner.nextLine().trim();
            if (searchFunctions.checkIfDigits(name)) {
                System.out.printf("\n\n%-40s", "Names Have To Consist Of Letters");
            } else {
                break;
            }
        }
        String socialSecurityNumber;
        while (true) {
            System.out.printf("\n\n%-40s", "Social Security Number: ");
            socialSecurityNumber = scanner.nextLine().trim();
            if (socialSecurityNumber.length() != 10 || !searchFunctions.checkIfDigits(socialSecurityNumber)) {
                System.out.printf("\n\n%-40s", "Social Security Number Must Be 10 Digits");
            } else {
                break;
            }
        }
        boolean loop = true;
        while(loop){
            System.out.printf("\n\n%-40s\n\n%-5d%-40s\n%-5d%-40s\n%-5d%-40s\n%-5d%-40s\n\n%-60s\n", "Do You Want To Register "
                            + name + " " + socialSecurityNumber, 1, "YES", 2, "Change Input", 3, "Main Menu", 4, "Exit Program",
                    "[Choose Option By Typing Corresponding Number And Pressing Enter]");
            String input = scanner.nextLine().trim();
            switch (input) {
                case "1" -> {
                    GymMember gymMember = new GymMember(name, socialSecurityNumber, LocalDate.now());
                    gymMemberList.add(gymMember);
                    System.out.printf("\n\n%-40s\n%-30s%-30s%-20s\n%-30s%-30s%-20s", "New Member Registered:",
                            "Name", "Social Security Number", "Gym Membership Valid Through",
                            gymMember.getName(), gymMember.getSocialSecurityNr(), gymMember.getDateOfMembershipDuration());
                    loop = false;
                }
                case "2" -> {
                    addNewGymMember();
                    loop = false;
                }
                case "3" -> loop = false;
                    //startMenu();
                case "4" -> exitProgram();
                default -> System.out.printf("\n%-40s", "[Choose a Valid Option]");
            }
        }
    }
}