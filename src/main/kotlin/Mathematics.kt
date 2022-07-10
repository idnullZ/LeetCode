object Mathematics {


    /**
     * Given an integer n, return true if it is a power of three. Otherwise, return false.
     * An integer n is a power of three, if there exists an integer x such that n == 3x.
     * https://leetcode.com/problems/power-of-three/
     * */
    fun isPowerOfThree(n: Int) = n > 0 && 1162261467 % n == 0
    /**
     * Given an integer n, return true if it is a power of four. Otherwise, return false.
     * An integer n is a power of four, if there exists an integer x such that n == 4x.
     * */
    fun isPowerOfFour(n: Int) = n != 0 && n and n - 1 == 0 && n and -0x55555556 == 0

    /**
     * Given an integer n, return true if it is a power of two. Otherwise, return false.
     * An integer n is a power of two, if there exists an integer x such that n == 2x.
     * */
    fun isPowerOfTwo(x: Int): Boolean {
        if (x <= 0) return false
        return (x and x - 1) == 0
    }

    /**
     * Given an integer num, repeatedly add all its digits until the result has only one digit, and return it.
     * */
    fun addDigits(num: Int): Int {
        fun sum(value: String): Int {
            var result = 0
            for (i in value) {
                result += Integer.valueOf(i.toString())
            }
            return result
        }

        var result = num

        while (result > 9) {
            println(result)
            result = sum(result.toString())
        }
        return result
    }

}