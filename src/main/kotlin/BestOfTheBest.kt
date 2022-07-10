object BestOfTheBest {


    fun search(nums: IntArray, target: Int): Int {
        var lo = 0
        var hi = nums.size - 1
        while (lo <= hi) {
            val mid = lo + hi ushr 1
            if (nums[mid] > target) {
                hi = mid - 1
            } else if (nums[mid] < target) {
                lo = mid + 1
            } else {
                return mid
            }
        }
        return -1
    }
}