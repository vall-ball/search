package search;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Menu {

    List<String> listOfPeople;
    final static Scanner scanner = new Scanner(System.in);

    Menu(List<String> listOfPeople) {
        this.listOfPeople = listOfPeople;
    }

    public void menu() {
        while (true) {
            System.out.println("=== Menu ===");
            System.out.println("1. Find a person");
            System.out.println("2. Print all people");
            System.out.println("0. Exit");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    findPerson();
                    break;
                case 2:
                    printAllPeople();
                    break;
                case 0:
                    System.out.println("Bye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Incorrect option! Try again.");
                    break;
            }
        }
    }

    public void findPerson() {
        System.out.println("Select a matching strategy: ALL, ANY, NONE");
        String choice = scanner.nextLine();
        System.out.println("Enter a name or email to search all suitable people.");
        ArgsParser parser = new ArgsParser();
        List<String> find = parser.parseString(scanner.nextLine());
        Indexer indexer = new Indexer();
        Map<String, List<Integer>> map = indexer.createInvertedIndex(listOfPeople);
        Set<String> answer = null;
        switch (choice) {
            case "ALL":
                answer = indexer.all(map, find, listOfPeople);
                break;
            case "ANY":
                answer = indexer.any(map, find, listOfPeople);
                break;
            case "NONE":
                answer = indexer.none(map, find, listOfPeople);
                break;
            default:
                break;
        }
        System.out.println(answer.size() + "  persons found:");
        if (answer.size() > 0) {
            for (String s : answer) {
                System.out.println(s);
            }
        }
    }

    public void printAllPeople() {
        System.out.println("=== List of people ===");
        for (String s : listOfPeople) {
            System.out.println(s);
        }
    }

}
