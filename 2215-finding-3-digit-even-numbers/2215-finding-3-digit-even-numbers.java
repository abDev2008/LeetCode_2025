class Solution 
{
    public int[] findEvenNumbers(int[] digits) 
    {
        HashSet<Integer> set = new HashSet<>();
        int n = digits.length;

        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                for(int k = 0; k < n; k++)
                {
                    if(i == j || i == k || j == k)
                    {
                        continue;
                    }

                    int num = (digits[i] * 100) + (digits[j] * 10) + digits[k];

                    if(num % 2 == 0 && digits[i] != 0)
                    {
                        set.add(num);
                    }
                }
            }
        }    

        ArrayList<Integer> list = new ArrayList<>(set);
        Collections.sort(list);

        int[] result = new int[list.size()];
        for(int i = 0; i < result.length; i++)
        {
            result[i] = list.get(i);
        }

        return result;
    }
}