package search;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ArgsParser parser = new ArgsParser(args);

        if (parser.fileName() != null) {
            FileHandler handler = new FileHandler(parser.fileName());
            List<String> listOfPeople = handler.loadPeople();
            Menu menu = new Menu(listOfPeople);
            menu.menu();
        }
    }

}