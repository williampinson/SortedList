import java.util.ArrayList;
import java.util.Collections;

public class SortedList extends ArrayList<String> {

    SortedListGUI gui;

    public SortedList() {
        gui = new SortedListGUI(this);
    }
    public void addString(String newString) {
        add(Math.abs(binarySearch(newString)),newString);
        //Collections.sort(this);
    }
    // if not found, index returned as negative.
    private int binarySearch(String searchString) {
        int low = 0;
        int high = this.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            String midStr = this.get(mid);
            if (midStr.toLowerCase().compareTo(searchString.toLowerCase()) == 0) {
                return mid;
            }
            else if (midStr.toLowerCase().compareTo(searchString.toLowerCase()) < 0) {
                low++;
            }
            else {
                high--;
            }
        }
        return -low;
    }
    public String search(String searchString) {
        int index = binarySearch(searchString);
        if (index < 0) { // not found
            index = Math.abs(index);
            return "\"" + searchString + "\" not found. Would be placed at index: " + index + ".";
        }
        else { // found
            return "\"" + get(index) + "\" found at index: " + index + ".";
        }
    }
}
