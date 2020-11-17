package search;

import java.util.ArrayList;
import java.util.List;

public class ArgsParser {
    String[] args;

    ArgsParser() {

    }
    ArgsParser(String[] args) {
        this.args = args;
    }

    public String fileName() {
        if (args != null && args.length > 0) {
            if (args[0].equals("--data")) {
                return args[1];
            }
        }
        return null;
    }

    public List<String> parseString(String str) {
        List<String> answer = new ArrayList<>();
        for (String s : str.split(" ")) {
            answer.add(s);
        }
        return answer;
    }

}
