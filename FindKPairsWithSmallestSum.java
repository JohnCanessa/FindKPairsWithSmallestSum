import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;


/**
 * 
 */
public class FindKPairsWithSmallestSum {


    /**
     * You are given two integer arrays nums1 and nums2 
     * sorted in ascending order and an integer k.
     * 
     * Define a pair (u, v) which consists of one element 
     * from the first array and one element from the second array.
     * 
     * Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) 
     * with the smallest sums.
     * 
     * Runtime: 14 ms, faster than 40.74% of Java online submissions.
     * Memory Usage: 39.6 MB, less than 80.78% of Java online submissions.
     */
    static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {

        // **** create priority queue with comparator ****
        PriorityQueue<List<Integer>> pq = new PriorityQueue<>(
            k, 
            (a, b) -> ((b.get(0) + b.get(1)) - (a.get(0) + a.get(1)))
        );
 
        // **** populate priority queue O(n * m) ****
        int maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {

                // **** add pair to priority queue *****
                if (pq.size() < k) {

                    // **** ****
                    pq.add(Arrays.asList(nums1[i], nums2[j]));

                    // **** update max value (if needed) ****
                    if (nums1[i] + nums2[j] > maxVal)
                        maxVal = nums1[i] + nums2[j];
                } else {

                    // **** pair smaller than max value in priority queue ****
                    if (nums1[i] + nums2[j] < maxVal) {

                        // **** remove head (largest value in pq) ****
                        pq.remove();

                        // **** add current pair ****
                        pq.add(Arrays.asList(nums1[i], nums2[j]));

                        // **** get pair at head of the priority queue (largest value in pq) ****
                        List<Integer> pair = pq.peek();

                        // **** update max value ****
                        maxVal = pair.get(0) + pair.get(1);
                    }
                }
            }
        }

        // **** return list of pairs ****
        return new ArrayList<>(pq);
    }


    /**
     * Test scaffold.
     * 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read k ****
        int k = Integer.parseInt(br.readLine().trim());

        // **** read nums1 array ****
        int[] nums1 = Arrays.stream(br.readLine().trim().split(","))
                        .mapToInt(Integer::parseInt)
                        .toArray();

        // **** read nums2 array ****
        int[] nums2 = Arrays.stream(br.readLine().trim().split(","))
                        .mapToInt(Integer::parseInt)
                        .toArray();

        // **** close buffered reader ****
        br.close();

        // ???? ????
        System.out.println("main <<<     k: " + k);
        System.out.println("main <<< nums1: " + Arrays.toString(nums1));
        System.out.println("main <<< nums2: " + Arrays.toString(nums2));

        // **** call function of interest and display result ****
        System.out.println("main <<< pairs: " + kSmallestPairs(nums1, nums2, k));
    }
}