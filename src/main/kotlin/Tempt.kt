object Tempt {

    fun singleNumber(nums: IntArray): Int {
        var res = nums[0]
        for (i in 1 until nums.size) res = res xor nums[i]
        return res

    }

    fun findSingle(ar: IntArray, ar_size: Int): Int {
        var res = ar[0]
        for (i in 1 until ar_size) res = res xor ar[i]
        return res
    }


    fun reverseString(s: String): String {
        if (s.length == 1) return s
        return "" + s[s.length - 1] + reverseString(s.substring(0, s.length - 1))

    }

}