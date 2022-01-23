import SimplerProblems.BinarySearch;
import UndergroundSystem.*;

public class Main {
    public static void main(String []args)
    {
        System.out.println("---------------------------------------------------------------------------------");
        testUndergroundSystem();
    }

    public static void testBinarySearch()
    {
        BinarySearch bs = new BinarySearch();
        int [] nums = new int []{-1,0,3,5,9,12};
        int target1 = 9;

        //int index = bs.search(nums, target1);
        //System.out.println("The search for the number: "+target1+" was found on index: "+index);

        int target2 = 2;
        int index2 = bs.search(nums, target2);
        System.out.println("The search for the number: "+target2+" was found on index: "+index2);
    }

    public static void testUndergroundSystem()
    {
        UndergroundSystem undergroundSystem = new UndergroundSystem();
        undergroundSystem.checkIn(45, "Leyton", 3);
        undergroundSystem.checkIn(32, "Paradise", 8);
        undergroundSystem.checkIn(27, "Leyton", 10);
        undergroundSystem.checkOut(45, "Waterloo", 15);  // Customer 45 "Leyton" -> "Waterloo" in 15-3 = 12
        undergroundSystem.checkOut(27, "Waterloo", 20);  // Customer 27 "Leyton" -> "Waterloo" in 20-10 = 10
        undergroundSystem.checkOut(32, "Cambridge", 22); // Customer 32 "Paradise" -> "Cambridge" in 22-8 = 14
        System.out.println(undergroundSystem.getAverageTime("Paradise", "Cambridge")); // return 14.00000. One trip "Paradise" -> "Cambridge", (14) / 1 = 14
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));    // return 11.00000. Two trips "Leyton" -> "Waterloo", (10 + 12) / 2 = 11
        undergroundSystem.checkIn(10, "Leyton", 24);
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));    // return 11.00000
        undergroundSystem.checkOut(10, "Waterloo", 38);  // Customer 10 "Leyton" -> "Waterloo" in 38-24 = 14
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));    // return 12.00000. Three trips "Leyton" -> "Waterloo", (10 + 12 + 14) / 3 = 12
    }
}
