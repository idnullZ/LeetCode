object Dynamic {

    fun fib(n: Int): Int {
        if (n ==0||n==1)return n
        val arr = IntArray(n + 1)
        arr[0] = 0
        arr[1] = 1
        for (i in 2..n) arr[i] = arr[i - 1] + arr[i - 2]
        return arr[n]
    }

    /**
     * Input: n = 2
     * Output: 2
     * Explanation: There are two ways to climb to the top.
     * 1. 1 step + 1 step
     * 2. 2 steps
     * */
    fun climbStairs(n: Int): Int {
        val matrix = IntArray(n)
        if (n < 3) {
            return n
        }
        matrix[0] = 1
        matrix[1] = 2

        for (i in 2 until matrix.size) {
            matrix[i] = matrix[i - 1] + matrix[i - 2]
        }
        return matrix[n - 1]
    }

}