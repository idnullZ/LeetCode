import java.util.*
import kotlin.math.abs

object ArrayTask {

    /**
     * https://leetcode.com/problems/max-consecutive-ones-iii/
     * Given a binary array nums and an integer k, return the maximum number of
     * consecutive 1's in the array if you can flip at most k 0's.
     * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
     * Output: 6
     * */
    fun longestOnes(nums: IntArray, k: Int): Int {
        var countZero = 0
        var start = 0

        for (num in nums) {
            if (num == 0) ++countZero
            if (countZero > k && nums[start++] == 0) --countZero
        }

        return nums.size - start
    }

    /**
     * Given n non-negative integers representing an elevation map where the width of each bar is 1,
     * compute how much water it can trap after raining.
     * */
    fun trap(height: IntArray): Int {
        var start = 0
        var end = height.lastIndex
        var maxL = height[start]
        var maxR = height[end]
        var result = 0

        while (start < end) {
            result += if (maxL <= maxR) {
                maxL = maxOf(maxL, height[++start])
                maxL - height[start]
            } else {
                maxR = maxOf(maxR, height[--end])
                maxR - height[end]
            }
        }

        return result
    }

    /**
     * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
     * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
     * DO NOT allocate another 2D matrix and do the rotation.2
     * */
    fun rotate(matrix: Array<IntArray>) {
        val row = matrix.size
        for (i in 0 until row) {
            for (j in i until row) {
                val temp = matrix[i][j]
                matrix[i][j] = matrix[j][i]
                matrix[j][i] = temp
            }
        }

        for (i in 0 until row) {
            for (j in 0 until row / 2) {
                val temp = matrix[i][j]
                matrix[i][j] = matrix[i][row - 1 - j]
                matrix[i][row - 1 - j] = temp
            }
        }
    }

    /**
     * Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
     * */
    fun setZeroes(matrix: Array<IntArray>) {
        val index = mutableListOf<Int>()
        val index2 = mutableListOf<Int>()

        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                if (matrix[i][j] == 0) {
                    index.add(j)
                    index2.add(i)
                }
            }
        }
        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                if (index.contains(j)) {
                    matrix[i][j] = 0
                }
            }
        }
        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                if (index2.contains(i)) {
                    matrix[i][j] = 0
                }
            }
        }
    }

    /**
     * You are given an array prices where prices[i] is the price of a given stock on the ith day.
     * You want to maximize your profit by choosing a single day to buy one stock
     * and choosing a different day in the future to sell that stock.
     * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
     * */
    fun maxProfit(prices: IntArray): Int {
        var maxProfit = 0
        var minPrice = prices[0]
        for (i in 1 until prices.size) {
            val price = prices[i]
            maxProfit = kotlin.math.max(price - minPrice, maxProfit)
            minPrice = price.coerceAtMost(minPrice)
        }
        return maxProfit
    }


    /**
     * Given a 2D integer array nums where nums[i] is a non-empty array of distinct positive integers,
     * return the list of integers that are present in each array of nums sorted in ascending order.
     * */
    fun intersection(nums: Array<IntArray>): List<Int> {
        val result = mutableListOf<Int>()
        val set = mutableSetOf<Int>()
        for (i in nums.first().indices) {
            for (j in nums.indices) {
                if (!nums[j].contains(nums.first()[i])) {
                    set.add(nums.first()[i])
                }
            }
        }
        for (i in nums.first().indices) {
            if (!set.contains(nums.first()[i])) {
                result.add(nums.first()[i])
            }
        }

        return result.sorted()
    }

    /**
     * Given an array nums of size n, return the majority element.
     * The majority element is the element that appears more than ⌊n / 2⌋ times.
     * You may assume that the majority element always exists in the array.
     * */
    fun majorityElement(nums: IntArray): Int {
        var result = 0
        var count = 0
        val set = nums.toHashSet()
        for (i in set) {
            val currentCount = nums.count { it == i }
            if (count < currentCount) {
                count = currentCount
                result = i
            }
        }
        return result
    }

    /**
     * Given an integer array nums, find the contiguous subarray (containing at least one number)
     * which has the largest sum and return its sum.
     * A subarray is a contiguous part of an array.
     * */
    fun maxSubArray(nums: IntArray): Int {
        var maxNow: Int? = null
        var maxEnd: Int? = null
        for (i in nums) {
            if (maxEnd == null) maxEnd = i else maxEnd += i
            maxEnd = maxOf(maxEnd, i)
            maxNow = if (maxNow == null) i else maxOf(maxNow, maxEnd)
        }
        return maxNow ?: nums[0]
    }

    /**
     * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix.
     * This matrix has the following properties:
     * Integers in each row are sorted from left to right.
     * The first integer of each row is greater than the last integer of the previous row.
     * */
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        for (i in matrix) {
            if (i[i.size - 1] >= target) {
                for (h in i) {
                    if (h == target) {
                        println(h)
                        return true
                    }
                }
            }
        }
        return false
    }

    /**
     * Given a 0-indexed integer array nums, return true if it can be made strictly increasing after removing exactly
     * one element, or false otherwise. If the array is already strictly increasing, return true.
     * */
    fun canBeIncreasing(nums: IntArray): Boolean {
        var removed = false
        for (i in 1 until nums.size) if (nums[i - 1] >= nums[i]) {
            if (removed) return false
            removed = true
            if (i > 1 && nums[i - 2] >= nums[i]) nums[i] = nums[i - 1]
        }
        return true
    }

    fun shiftGrid(grid: Array<IntArray>, k: Int): List<List<Int>> {
        var z = 0
        val gridSize = grid[0].size
        val result = mutableListOf<List<Int>>()

        val list = mutableListOf<Int>()
        for (i in grid.indices) {
            for (j in 0 until gridSize) {
                list.add(grid[i][j])
            }
        }
        println()
        z = if (k > list.size) {
            k % list.size
        } else {
            k
        }
        val need = mutableListOf<Int>()
        for (i in list.size - z until list.size) {
            need.add(list[i])
        }
        for (i in 0 until list.size - z) {
            need.add(list[i])
        }
        var counter = 0
        print(need)
        var tempt = mutableListOf<Int>()
        for (i in need.indices) {
            if (counter != gridSize) {
                counter++
                tempt.add(need[i])
            } else {
                result.add(tempt)
                counter = 1
                tempt = mutableListOf(need[i])
            }
            if (i == need.size - 1) {
                result.add(tempt)
            }

        }
        return result

    }


    /**
     * Given a sorted array of distinct integers and a target value, return the index if the target is found.
     * If not, return the index where it would be if it were inserted in order.
     * */
    fun searchInsert(nums: IntArray, target: Int): Int {
        for (i in nums.indices) {
            if (nums[i] == target) {
                return i
            } else {
                if (target < nums[i]) {
                    return i
                }
            }
        }
        return nums.size
    }

    /**
     * Input: nums = [3,0,1]
     * Output: 2
     * Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2
     * is the missing number in the range since it does not appear in nums.
     * */
    fun missingNumber(nums: IntArray): Int {
        nums.sort()
        for (i in 0..nums.size - 2) {
            if (nums[i] != nums[i + 1] - 1) {
                return nums[i] + 1
            }
        }
        return if (nums[nums.size - 1] == nums.size) 0 else nums.size
    }


    fun findDuplicate(nums: IntArray): Int {
        var result = -1
        for (i in nums.indices) {
            val num = abs(nums[i])
            if (nums[num] < 0) {
                result = num
            } else {
                nums[num] = -1 * nums[num]
            }
        }
        return result
    }

    /**
     * Input: haystack = "hello", needle = "ll"
     * Output: 2
     * */
    fun strStr(haystack: String, needle: String): Int {
        var result = -1
        var indexedValue = 0
        val haystackSize = haystack.length - 1
        for (i in haystack.indices) {
            if (haystack[i] == needle[0]) {
                if (haystackSize - i >= needle.length - 1) {
                    indexedValue = i
                    if (result != -1) {
                        break
                    }
                    for (j in needle.indices) {
                        result = i
                        if (needle[j] == haystack[indexedValue]) {
                            indexedValue += 1
                        } else {
                            result = -1
                            break
                        }
                    }
                } else {
                    break
                }
            }
        }
        return result
    }

    /**
     * Input ["eat","tea","tan","ate","nat","bat"],
     * Output: [["ate","eat","tea"],["nat","tan"],["bat"]]
     * */
    fun groupWords(words: Array<String>): List<List<String>> {
        val result: MutableList<List<String>> = mutableListOf()
        val map = mutableMapOf<String, MutableList<String>>()
        for (word in words) {
            val sortedWord = word.toCharArray().sorted().joinToString("")
            if (map.containsKey(sortedWord)) {
                map[sortedWord]?.add(word)
            } else {
                map[sortedWord] = mutableListOf(word)
            }
        }
        for ((key, value) in map) {
            result.add(value)
        }
        return result

    }

    /**
     * Input: nums = [2,7,11,15,3,2],  nums2 = [2,7,2,15,9],
     * Output: [2,7,2,15]
     * */
    fun intersect(a1: IntArray, a2: IntArray): List<Int> {
        val s1 = a1.toHashSet()
        val s2 = a2.toHashSet()
        val result = mutableListOf<Int>()

        for (e in s1) {
            if (s2.contains(e)) {
                val nums = minOf(a1.count { it == e }, a2.count { it == e })
                repeat(nums) { result.add(e) }
            }
        }
        return result
    }


    /**
     * Input: nums = [2,7,11,15], target = 9
     * Output: [0,1]
     * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
     * */
    fun twoSum(nums: IntArray, target: Int): IntArray {
        var stop = true
        val result = ArrayList<Int>()
        for (i in nums.indices) {
            for (j in nums.indices) {
                val res = nums[i] + nums[j]
                if (res == target) {
                    if (i != j) {
                        if (stop) {
                            result.add(i)
                            result.add(j)
                            stop = false
                            break
                        }
                    }
                }
            }

        }

        return result.toIntArray()
    }

    /**
     * Input: digits = [1,2,3]
     * Output: [1,2,4]
     * Explanation: The array represents the integer 123.
     * Incrementing by one gives 123 + 1 = 124.
     * Thus, the result should be [1,2,4].
     * */
    fun plusOne(digits: IntArray): IntArray {
        val value = digits[digits.size - 1]

        if (value == 9) {
            var nine = false
            val arr = IntArray(digits.size + 1)
            for (i in digits.indices) {
                if (digits[i] != 9) {
                    nine = true
                }
            }
            if (nine) {
                for (i in digits.size - 1 downTo 0) {
                    if (digits[i] == 9) {
                        digits[i] = 0
                    } else {
                        val d = digits[i]
                        digits[i] = d + 1
                        break
                    }
                }
            } else {
                arr[0] = 1
                arr[arr.size - 1] = 0
            }
            return if (nine) {
                digits
            } else {
                arr
            }

        } else {
            digits[digits.size - 1] = value + 1
            return digits
        }
    }

    /**
     * Given an integer array nums, return true if any value appears at least twice in the array,
     * and return false if every element is distinct
     * */
    fun containsDuplicate(nums: IntArray): Boolean {
        val set = mutableSetOf<Int>()
        for (i in nums.indices) {
            if (!set.add(nums[i])) {
                return true
            }
        }
        return false
    }

    /**
     * Input: nums = [1,2,3,4,5,6,7], k = 3
     * Output: [5,6,7,1,2,3,4]
     * */
    fun rotate(nums: IntArray, k: Int) {
        if (nums.size == 1) return
        fun reRotate() {
            val tempt = nums[nums.size - 1]
            for (i in nums.size - 1 downTo 1) {
                nums[i] = nums[i - 1]
            }
            nums[0] = tempt
        }

        repeat(k) {
            reRotate()
        }


    }

    /**
     * Given an array nums of n integers where nums[i] is in the range [1, n],
     * return an array of all the integers in the range [1, n] that do not appear in nums.
     * Input: nums = [4,3,2,7,8,2,3,1]
     * Output: [5,6]
     * */
    fun findDisappearedNumbers(nums: IntArray): List<Int> {
        val result = ArrayList<Int>()
        for (i in 1..nums.size) {
            if (!nums.contains(i)) {
                result.add(i)
            }
        }

        return result
    }


    /**
     *Given an integer array nums, return the third distinct maximum number in this array.
     * If the third maximum does not exist, return the maximum number.
     *
     * Input: nums = [1,2]
     * Output: 2
     * Explanation:
     * The first distinct maximum is 2.
     * The second distinct maximum is 1.
     * The third distinct maximum does not exist, so the maximum (2) is returned instead.
     * */
    fun thirdMax(nums: IntArray): Int {
        var result = 0
        var count = 0
        nums.sort()
        for (i in nums.size - 1 downTo 0) {
            if (nums[i] != result) {
                result = nums[i]
                count++
                if (count == 3) {
                    break
                }
            }
        }
        if (count != 3) {
            result = nums[nums.size - 1]
        }
        return result
    }

    /**
     * Input: heights = [1,1,4,2,1,3]
     * Output:
     * Explanation:
     * heights:  [1,1,4,2,1,3]
     * expected: [1,1,1,2,3,4]
     * Indices 2, 4, and 5 do not match
     * */
    fun heightChecker(heights: IntArray): Int {
        var result = 0
        val temptArray = IntArray(heights.size)
        for (i in heights.indices) {
            temptArray[i] = heights[i]
        }
        temptArray.sort()
        for (i in heights.indices) {
            if (temptArray[i] != heights[i]) {
                result++
            }
        }
        return result
    }


    /**
     * Given an integer array nums, move all the even integers at the beginning of the array followed by all the odd integers.
     * Return any array that satisfies this condition.
     * Input: nums = [3,1,2,4]
     * Output: [2,4,3,1]
     * Explanation: The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
     * */
    fun sortArrayByParity(nums: IntArray): IntArray {
        val resultArray = IntArray(nums.size)
        val oddArray = IntArray(nums.size)
        var index = 0
        for (i in nums.indices) {
            if (nums[i] % 2 == 0) {
                resultArray[index] = nums[i]
                index++

            } else {
                oddArray[i] = nums[i]
            }
        }
        for (i in oddArray.indices) {
            if (oddArray[i] != 0) {
                resultArray[index] = oddArray[i]
                index++
            }
        }
        return resultArray

    }

    /**
     * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
     * Note that you must do this in-place without making a copy of the array.Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
     * Note that you must do this in-place without making a copy of the array.
     * Input: nums = [0,1,0,3,12]
     * Output: [1,3,12,0,0]
     * */
    fun moveZeroes(nums: IntArray): Unit {
        var arrayZero = IntArray(nums.size)
        var index = 0

        for (i in nums.indices) {
            if (nums[i] != 0) {
                arrayZero[index] = nums[i]
                index++
            }
        }

        for (i in nums.indices) {
            nums[i] = arrayZero[i]
        }
    }

    /**
     * Input: arr = [17,18,5,4,6,1]
     * Output: [18,6,6,6,1,-1]
     * Explanation:
     * - index 0 --> the greatest element to the right of index 0 is index 1 (18).
     * - index 1 --> the greatest element to the right of index 1 is index 4 (6).
     * - index 2 --> the greatest element to the right of index 2 is index 4 (6).
     * - index 3 --> the greatest element to the right of index 3 is index 4 (6).
     * - index 4 --> the greatest element to the right of index 4 is index 5 (1).
     * - index 5 --> there are no elements to the right of index 5, so we put -1.
     * */
    fun replaceElements(inPutArr: IntArray): IntArray {
        fun findMaxElement(array: IntArray, start: Int, end: Int): Int {
            var max = array[start + 1]

            for (i in start + 1 until end) {
                if (array[i] > max) {
                    max = array[i]
                }
            }
            return max
        }

        val outPutArray = IntArray(inPutArr.size)

        for (i in inPutArr.indices) {
            if (i == inPutArr.size - 1) {
                outPutArray[i] = -1
            } else {
                outPutArray[i] = findMaxElement(inPutArr, i, inPutArr.size)
            }


        }
        return outPutArray
    }

    /**
     * Input: arr = [0,3,2,1]
     * Output: true
     * */
    fun validMountainArray(arr: IntArray): Boolean {
        var start = 0
        var end = arr.size - 1
        var i = 0
        while (start < end && i++ < arr.size) {
            if (arr[start] < arr[start + 1]) start++
            if (arr[end - 1] > arr[end]) end--
        }
        return start != 0 && start != arr.size - 1 && start == end
    }


    /**Input: arr = [10,2,5,3]
     * Output: true
     * Explanation: N = 10 is the double of M = 5,that is, 10 = 2 * 5.
     * */
    fun checkIfExist(arr: IntArray): Boolean {
        var zero = 0
        for (i in arr) {
            if (i == 0) {
                zero += 1
            }
            val first = i + i

            for (i in arr) {
                if (i != 0) {
                    if (first == i) {
                        return true
                    }
                }

            }
            if (zero == 2) return true
        }
        return false
    }


    /**
     * Input: nums = [0,0,1,1,1,2,2,3,3,4]
     * Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
     * Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
     * It does not matter what you leave beyond the returned k (hence they are underscores).*/
    fun removeDuplicates(nums: IntArray): Int {
        var length = 0
        for (i in nums.indices) {
            if (i == nums.size - 1) {
                nums[length] = nums[i]
                length++
                break
            }
            if (nums[i] != nums[i + 1]) {

                nums[length] = nums[i]
                length++
            }
        }
        return length

    }

    /**
     * Input: nums = [3,2,2,3], val = 3
     * Output: 2, nums = [2,2,_,_]
     * Explanation: Your function should return k = 2, with the first two elements of nums being 2.
     * It does not matter what you leave beyond the returned k (hence they are underscores).
     * */
    fun removeElement(nums: IntArray, v: Int): Int {
        var length = 0

        for (i in nums.indices) {
            if (nums[i] != v) {
                nums[length] = nums[i]
                length++
            }
        }
        return length
    }

    /**
     * Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
     * Output: [1,2,2,3,5,6]
     * Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
     * The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1
     * */
    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
        val innerArray = ArrayList<Int>()
        for (i in 0 until m) {
            innerArray.add(nums1[i])
        }

        for (i in 0 until n) {
            innerArray.add(nums2[i])
        }

        innerArray.sort()
        for (i in nums1.indices) {
            nums1[i] = innerArray[i]

        }
    }

    /**
     * Input: arr = [1,0,2,3,0,4,5,0]
     * Output: [1,0,0,2,3,0,0,4]
     * Explanation: After calling your function, the input array is modified to: [1,0,0,2,3,0,0,4]
     * */
    fun duplicateZeros(arr: IntArray): Unit {
        val indexes = ArrayList<Int>()
        var count = 0
        for (i in arr.indices) {
            if (arr[i] == 0) {
                indexes.add(i + count)
                count += 1
            }
        }
        indexes.forEach {
            println(it)
            for (i in arr.size - 2 downTo it) {
                arr[i + 1] = arr[i]
            }
        }
    }

    /**
     * Input: nums = [1,1,0,1,1,1]
     * Output: 3
     * Explanation: The first two digits or the last three digits are consecutive 1s.
     * The maximum number of consecutive 1s is 3.
     */
    fun findMaxConsecutiveOnes(nums: Array<Int>): Int {
        var res = 0
        var count = 0

        for (n in nums) {
            count += n
            if (count > res) res = count
            if (n == 0) count = 0
        }
        return res
    }

    /**
     * Input: nums = [12,345,2,6,7896]
     * Output: 2
     * Explanation:
     * 12 contains 2 digits (even number of digits).
     * 345 contains 3 digits (odd number of digits).
     * 2 contains 1 digit (odd number of digits).
     * 6 contains 1 digit (odd number of digits).
     * 7896 contains 4 digits (even number of digits).
     * **/
    fun findNumbers(nums: Array<Int>): Int {
        var count = 0
        for (n in nums) {
            if (n.toString().length % 2 == 0) {
                count++
            }
        }
        return count
    }


    /**
     * Input: nums = [-4,-1,0,3,10]
     *  Output: [0,1,9,16,100]
     *  Explanation: After squaring, the array becomes [16,1,0,9,100].
     * After sorting, it becomes [0,1,9,16,100].
     * */
    fun sortedSquares(nums: Array<Int>): Array<Int> {

        fun min(array: Array<Int>, start: Int, end: Int): Int {
            var minIndex = start
            var minValue = array[start]
            for (i in start + 1 until end) {
                if (array[i] < minValue) {
                    minValue = array[i]
                    minIndex = i
                }
            }
            return minIndex
        }


        fun sortSelection(array: Array<Int>): Array<Int> {
            for (i in array.indices) {
                val minIndex: Int = min(array = array, start = i, end = array.size);
                val tmp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = tmp;
            }
            return array
        }

        val result: MutableList<Int> = nums.toMutableList()

        for (n in nums) {
            result.add(n * n)
        }
        return sortSelection(result.toTypedArray())
    }

}