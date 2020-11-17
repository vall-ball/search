package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
    FileWriter writer = null;
    String fileName;

    FileHandler(String fileName) {
        this.fileName = fileName;
    }

    public List<String> loadPeople() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        List<String> listOfPeople = new ArrayList<>();
        while (scanner.hasNext()) {
            listOfPeople.add(scanner.nextLine());
        }
        scanner.close();
        return listOfPeople;
    }


}
