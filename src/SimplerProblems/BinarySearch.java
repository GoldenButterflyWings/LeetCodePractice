package SimplerProblems;

public class BinarySearch
{
    public int search(int[] nums, int target)
    {
        int startindex = 0;
        int lastindex = nums.length;

        return binarysearchrecussive(nums, startindex, lastindex-1, target);
    }

    private int binarysearchrecussive(int arr[], int start, int end, int target)
    {
        int mid = start + (end - start)/2; //pick the quotient
        System.out.println("mid: "+mid);
        if (end >= start)
        {
            System.out.println("arr[mid]: "+arr[mid]);
            if (arr[mid] ==  target) //the mid value is the correct one
                return mid;

            if(arr[mid] > target) // this means it on the left of the array, the end moves one less
            {
                System.out.println("start: "+start+", end: "+(mid-1));
                return binarysearchrecussive(arr,start, mid-1, target);
            }

            //else right of the array, the start moves one forward
            System.out.println("start: "+(mid+1)+", end: "+end);
            return binarysearchrecussive(arr,mid+1, end, target);
        }
        else
            return -1;
    }
}
