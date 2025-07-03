

package oop.finalexam.t1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * FinalTask1 performs a specific transformation and deletion process
 * on two predefined lists (list1 and list2).
 *
 * <p>For each value "n" in list1:
 * 1. Fetch the element at index (n + 1) from list2.
 * 2. Append the value n to the string, forming "stringN".
 * 3. Add "stringN" to list3.
 *
 * After list3 is constructed, iterate through list1 again and:
 * - Treat each value n as an index.
 * - Sort those indices in descending order.
 * - Remove the element at that index from list3 (if valid).
 * - If the index is invalid (i.e., too large), display an error message.
 *
 * This version avoids shifting errors by deleting in reverse order.
 */
public class ListTransform {

    /**
     * Executes the transformation and deletion logic as described above.
     */
    public static void runTask() {
        List<Integer> list1 = Arrays.asList(7, 5, 8, 9, 6, 3, 10, 4, 2, 1);
        List<String> list2 = Arrays.asList(
                "ief", "yby", "xil", "dps", "qzb", "ssd", "izp",
                "wwt", "bfh", "nqm", "aoz", "kba"
        );

        List<String> list3 = new ArrayList<>();

        System.out.println("Creating list3 using list1 and list2...");

        // Step 1: Build list3
        for (Integer n : list1) {
            int index = n + 1;
            if (index < list2.size()) {
                String result = list2.get(index) + n;
                list3.add(result);
                System.out.println("Added to list3: " + result);
            } else {
                System.out.println("[ERROR] Index " + index + " is out of bounds for list2 (n = " + n + "). Skipping.");
            }
        }

        System.out.println("\nList3 after initial creation: " + list3);

        // Step 2: Prepare indices for deletion
        List<Integer> removalIndices = new ArrayList<>();
        for (Integer n : list1) {
            removalIndices.add(n);
        }
        Collections.sort(removalIndices, Collections.reverseOrder());

        System.out.println("\nRemoving elements from list3 using sorted indices from list1...");
        for (Integer n : removalIndices) {
            if (n < list3.size()) {
                String removed = list3.remove((int) n);
                System.out.println("Removed element at index " + n + ": " + removed);
            } else {
                System.out.println("[ERROR] Cannot remove at index " + n + ": index out of bounds in list3.");
            }
        }

        System.out.println("\nFinal list3: " + list3);
    }

    /**
     * Main method to run the task.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        runTask();
    }
}


