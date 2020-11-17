package search;

import java.net.Inet4Address;
import java.util.*;

public class Indexer {

    public Map<String, List<Integer>> createInvertedIndex(List<String> list) {
        Set<String> set = new HashSet<>();
        for (String str : list) {
            for (String s : str.split(" ")) {
                set.add(s.toLowerCase());
            }
        }

        Map<String, List<Integer>> map = new HashMap<>();
        for (String s : set) {
            if (!map.containsKey(s)) {
                List<Integer> value = integerList(s, list);
                map.put(s, value);
            }
        }
        return map;
    }

    private List<Integer> integerList(String find, List<String> list) {
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).toLowerCase().contains(find.toLowerCase())) {
                answer.add(i);
            }
        }
        return answer;
    }

    public List<String> find(Map<String, List<Integer>> map, String find, List<String> list) {
        List<Integer> l = map.get(find);
        List<String> answer = new ArrayList<>();
        if (l != null) {
            for (Integer i : l) {
                answer.add(list.get(i));
            }
            return answer;
        }
        return null;
    }

    public Set<String> any(Map<String, List<Integer>> map, List<String> find, List<String> list) {
        //System.out.println("map = " + map);
        Set<String> set = new HashSet<>();
        for (String s : find) {
            //System.out.println(s);
            for (int i : map.get(s)) {
                set.add(list.get(i));
            }
        }
        return set;
    }

    public Set<String> all(Map<String, List<Integer>> map, List<String> find, List<String> list) {
        List<List<Integer>> listOfListsOfInteger = new ArrayList<>();
        for (String f : find) {
            if (map.get(f) != null) {
                listOfListsOfInteger.add(map.get(f));
            }
        }
        Set<String> set = new HashSet<>();
        System.out.println(listOfListsOfInteger);
        for (List<Integer> l : listOfListsOfInteger) {
            for (int i : l) {
                if (isOneInAllList(i, listOfListsOfInteger)) {
                    set.add(list.get(i));
                }
            }
        }
        return set;
    }

    private boolean isOneInList(int i, List<Integer> list) {
        for (int k = 0; k < list.size(); k++) {
            if (list.get(k) == i) {
                return true;
            }
        }
        return false;
    }

    private boolean isOneInAllList(int i, List<List<Integer>> list) {
        for (List<Integer> l : list) {
            if (!isOneInList(i, l)) {
                return false;
            }
        }
        return true;
    }

    public Set<String> none(Map<String, List<Integer>> map, List<String> find, List<String> list) {


        List<List<Integer>> listOfListsOfInteger = new ArrayList<>();
        for (String f : map.keySet()) {
            listOfListsOfInteger.add(map.get(f));
        }
        List<List<Integer>> listOfFind = new ArrayList<>();
        for (String f : find) {
            listOfFind.add(map.get(f));
        }
        System.out.println(listOfFind);
        System.out.println(listOfListsOfInteger);
        Set<String> set = new HashSet<>();
        for (List<Integer> l : listOfListsOfInteger) {
            if (isNoteInAllList(listOfFind, l)) {
                for (int i : l) {
                    set.add(list.get(i));
                }
            }
        }
        return set;
    }

    private boolean isNoteInAllList(List<List<Integer>> find, List<Integer> list) {
        for (List<Integer> l : find) {
            for (int i : l) {
                if (isOneInList(i, list)) {
                    return false;
                }
            }
        }
        return true;
    }

}
