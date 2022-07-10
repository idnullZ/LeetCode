import java.util.*

object StringTasks {


    /**
     * You are given a 0-indexed string array words, where words[i] consists of lowercase English letters.
     * In one operation, select any index i such that 0 < i < words.length and words[i - 1] and words[i] are anagrams,
     * and delete words[i] from words. Keep performing this operation as long as you can select an index that satisfies
     * the conditions. Return words after performing all operations. It can be shown that selecting the indices for each
     * operation in any arbitrary order will lead to the same result.An Anagram is a word or phrase formed by
     * rearranging the letters of a different word or phrase using all the original letters exactly once. For example,
     * "dacb" is an anagram of "abdc".
     * */
    fun removeAnagrams(words: Array<String>): List<String> {
        fun isAnagram(s1: String, s2: String): Boolean {
            if (s1.length != s2.length) return false
            val a1 = s1.toCharArray()
            a1.sort()
            val a2 = s2.toCharArray()
            a2.sort()
            return a1.contentEquals(a2)
        }

        if (words.size == 1) return words.toList()
        val result = mutableListOf<String>()
        var comperason: String = words.first()
        result.add(words.first())

        for (i in 1 until words.size) {
            if (!isAnagram(comperason, words[i])) {
                comperason = words[i]
                result.add(words[i])
            }
        }
        return result
    }

    /**
     * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
     * Input: n = 3
     * Output: ["((()))","(()())","(())()","()(())","()()()"]
     * */
    fun generateParenthesis(n: Int): List<String> {
        val result = mutableListOf<String>()
        fun generate(open: Int, close: Int, current: String) {
            if (open + close == 2 * n) {
                result.add(current)
                return
            }
            if (open < n) generate(open + 1, close, "$current(")
            if (close < open) generate(open, close + 1, "$current)")
        }
        generate(0, 0, "")
        return result
    }

    /**
     * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
     * determine if the input string is valid.
     * An input string is valid if:
     * Open brackets must be closed by the same type of brackets
     * Open brackets must be closed in the correct order.
     * */
    fun isValid(s: String): Boolean {
        var stack = ArrayDeque<Char>()
        for (ch in s) {
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch)
            } else {
                if (stack.isEmpty()) {
                    return false
                } else {
                    val trailingChar = stack.pop()
                    if (ch == ')' && trailingChar != '(') {
                        return false
                    } else if (ch == '}' && trailingChar != '{') {
                        return false
                    } else if (ch == ']' && trailingChar != '[') {
                        return false
                    }
                }
            }
        }

        return stack.isEmpty()
    }

    /**
     * Given a string s consisting of some words separated by some number of spaces,
     * return the length of the last word in the string.
     * A word is a maximal substring consisting of non-space characters only.
     */
    fun lengthOfLastWord(s: String): Int {
        var tempt = false
        var lengths = 0
        for (i in s.length - 1 downTo 0) {
            if (s[s.length - 1] != ' ') {
                if (s[i] == ' ') break else lengths++
            } else {
                if (s[i] == ' ') {
                    if (tempt) break
                } else {
                    tempt = true
                    if (s[i] == ' ') break else lengths++
                }
            }
        }
        return lengths
    }

    /**
     * Given two strings ransomNote and magazine, return true
     * if ransomNote can be constructed from magazine and false otherwise.
     * Each letter in magazine can only be used once in ransomNote.
     * */
    fun canConstruct(ransomNote: String, magazine: String): Boolean {
        val s1 = ransomNote.toHashSet()
        val s2 = magazine.toHashSet()
        for (i in s1) {
            if (s2.contains(i)) {
                if (ransomNote.count { it == i } > magazine.count { it == i }) return false
            } else {
                return false
            }
        }
        return true
    }

    fun hammingWeight(n: Int): Int {
        var count = 0
        for (i in 0..31) {
            if (n ushr i and 1 == 1) {
                ++count
            }
        }
        return count
    }

    /**
     * https://leetcode.com/explore/featured/card/top-interview-questions-easy/127/strings/884/
     * Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer (similar to C/C++'s atoi function).
     * The algorithm for myAtoi(string s) is as follows:

     * Read in and ignore any leading whitespace.
     * Check if the next character (if not already at the end of the string) is '-' or '+'. Read this character in if it is either. This determines if the final result is negative or positive respectively. Assume the result is positive if neither is present.
     * Read in next the characters until the next non-digit character or the end of the input is reached. The rest of the string is ignored.
     * Convert these digits into an integer (i.e. "123" -> 123, "0032" -> 32). If no digits were read, then the integer is 0. Change the sign as necessary (from step 2).
     * If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then clamp the integer so that it remains in the range. Specifically, integers less than -231 should be clamped to -231, and integers greater than 231 - 1 should be clamped to 231 - 1.
     * Return the integer as the final result.
     * Note:

     * Only the space character ' ' is considered a whitespace character.
     * Do not ignore any characters other than the leading whitespace or the rest of the string after the digits.
     *
     *
     * */
    fun myAtoi(s: String?): Int {
        if (s == null) return 0
        val n = s.length
        if (n == 0) return 0
        var i = 0
        while (s[i] == ' ') {
            if (++i == n) return 0
        }
        var sign = 1
        if (s[i] == '-') sign = -1
        if (s[i] == '-' || s[i] == '+') ++i
        var res = 0
        val flag = Int.MAX_VALUE / 10
        while (i < n) {

            if (s[i] < '0' || s[i] > '9') break
            if (res > flag || res == flag && s[i] > '7') return if (sign > 0) Int.MAX_VALUE else Int.MIN_VALUE
            res = res * 10 + (s[i] - '0')
            ++i
        }
        return sign * res
    }


    /***
     * Input: x = 123
     * Output: 321
     */
    fun reverse(x: Int): Int {
        var res = "0"
        if (x < 0) {
            res = "-"
        }
        if (x < 9 && x > -9) {
            return x
        }
        val value = if (x < 0) x * -1 else x
        val result = value.toString().reversed()
        res += result

        return try {
            res.toInt() ?: 0
        } catch (e: NumberFormatException) {
            0
        }
    }


    fun reverseString(s: CharArray?) {
        fun helper(index: Int, s: CharArray?) {
            if (index >= s!!.size - index - 1 || s == null) {
                return
            }
            val temp = s[index]
            s[index] = s[s.size - index - 1]
            s[s.size - index - 1] = temp
            helper(1 + index, s)
        }

        helper(0, s)
    }

    fun isPalindrome(s: String): Boolean {
        var value = s.toCharArray()
        var str = ""

        for (i in value.size - 1 downTo 0) {
            if (Character.isDigit(value[i]) || Character.isLetter(value[i])) {
                str += value[i]
            }
        }
        str = str.lowercase(Locale.getDefault())
        return str == str.reversed()
    }

    /**
     * Given a string s, find the first non-repeating character in it and return its index.
     * If it does not exist, return -1.
     * */
    fun firstUniqChar(s: String): Int {
        var chars = ""
        var char = -1
        for (i in s.indices) {
            if (!chars.contains(s[i])) {
                char = i
                for (j in i + 1 until s.length) {
                    if (s[j] == s[i]) {
                        chars += s[i]
                        char = -1
                        break
                    }
                }
                if (char != -1) {
                    break
                }

            }

        }
        return char
    }

    /**
     * Input: strs = ["flower","flow","flight"]
     * Output: "fl"
     * */
    fun longestCommonPrefix(strs: Array<String>): String {
        fun eqw(s1: String, s2: String): String {
            println(s1)
            println(s2)
            var result = ""
            val min = minOf(s1.length, s2.length)
            for (i in 0 until min) {
                if (s1[i] == s2[i]) {
                    result += s1[i]
                } else {
                    break
                }
            }
            return result
        }

        var result = strs[0]
        for (i in 1 until strs.size) {
            result = eqw(result, strs[i])
        }

        return result
    }

    /**
     * Input: s = "anagram", t = "nagaram"
     * Output: true
     * */
    fun isAnagram(s: String, t: String): Boolean {
        val mapS = mutableMapOf<Char, Int>()
        val mapT = mutableMapOf<Char, Int>()

        for (i in s.indices) {
            mapS[s[i]]?.let {
                mapS[s[i]] = it + 1
            }
            if (mapS[s[i]] == null) {
                mapS[s[i]] = 1
            }
        }

        for (i in t.indices) {
            mapT[t[i]]?.let {
                mapT[t[i]] = it + 1
            }
            if (mapT[t[i]] == null) {
                mapT[t[i]] = 1
            }

        }

        return mapS == mapT

    }

    /**
     * Input: AAAARRRDDDD,
     * Output: A4R3D4
     * */
    fun countLetters(str: String): String {
        var current = str[0]
        var count = 1
        var result = ""

        for (let in str.substring(1)) {
            if (current == let) {
                count++
            } else {
                if (count == 1) {
                    result += current
                } else {
                    result += "$current$count"
                }
                count = 1
                current = let
            }

        }

        if (count == 1) {
            result += current
        } else {
            result += "$current$count"
        }

        return result
    }

    fun doubleX(str: String): Boolean {

        /**
         *
        Given a string, return true if the first instance of "x"
        in the string is immediately followed by another "x".
         * */
        val i = str.indexOf("x")
        if (i == -1) return false

        return if (i + 1 >= str.length) false
        else str.substring(i + 1, i + 2) == "x"
    }


    fun stringTimes(value: String, count: Int): String {
        /**
        Given a string and a non-negative int n,
        return a larger string that is n copies of the original string.**/

        val res = StringBuilder()

        repeat(count) {
            res.append(value)
        }

        return res.toString()
    }


    fun endUp(str: String): String {

        /**
        Given a string, return a new string where the last 3 chars are now in upper case.
        If the string has less than 3 chars, uppercase whatever is there.
        Note that str.toUpperCase() returns the uppercase version of a string.
         **/

        if (str.length <= 3) return str.uppercase(Locale.getDefault())
        val del = str.length - 3
        val front = str.substring(0, del)
        val back = str.substring(del)
        return front + back.uppercase(Locale.getDefault())
    }

    fun stringE(str: String): Boolean {
        /**
        Return true if the given string contains between 1 and 3 'e' chars.
         * **/

        var count = 0
        for (element in str) {
            if (element == 'e') count++

        }
        return count in 1..3
    }


    fun frontBack(str: String): String {

        /**
         *Given a string, return a new string where the first and last chars have been exchanged.
         * **/
        val size = str.length

        return if (size > 2) {

            val first = str[0]

            val last = str[size - 1]

            last.toString() + str.substring(1, size - 1) + first

        } else if (size == 2) {

            "" + str[1] + str[0]
        } else {
            str
        }
    }


    fun front3(str: String): String {

        /**
         * Given a string, we'll say that the front is the first 3 chars of the string.
         * If the string length is less than 3, the front is whatever is there.
         * Return a new string which is 3 copies of the front.
         * **/

        var res = ""
        if (str.length > 3) {
            val value = str.substring(0, 3)
            repeat(3) {
                res += value
            }
        } else {
            res = str
        }
        return res
    }


    fun missingChar(value: String, number: Int): String {
        /**
         *
        Given a non-empty string and an int n,
        return a new string where the char at index n
        has been removed.
        The value of n will be a valid index of a char in the original string
        (i.e. n will be in the range 0..str.length()-1 inclusive).
         * **/

        var result = ""

        for (i in 0..value.length) {
            if (i != number) {
                result += value[i]
            }
        }
        return result

    }

    fun delDel(str: String): String {
        /**
         *
        Given a string, if the string "del" appears starting at index 1,
        return a string where that "del" has been deleted.
        Otherwise, return the string unchanged.
         **/
        var result = ""

        if (str.length > 3) {
            result = if (str.substring(1, 4) == "del") {
                str[0].toString() + str.substring(4, str.length)
            } else {
                str
            }
        }
        return result
    }


    fun changeWord(value: String): String {
        /**
         * input: aaa * bbb
         *
         *
         * output: ababab
         */
        val input = value.replace(" ", "")
        val size = input.length
        var indexDivorce = input.indexOfFirst {
            it == '*'
        }
        val str1 = input.substring(0, indexDivorce)
        val str2 = input.substring(++indexDivorce, size)
        val innerSize = str1.length
        var result = ""
        repeat(innerSize) { result += "${str1[it]}${str2[it]}" }
        return result
    }


    fun returnRepeatCharWord(str: String): String {
        /**
         *  input: hello my current job
         *
         *  output: hello current
         */
        val res = mutableListOf<String>()
        var word = ""
        var hasRepeatedLetters = false

        for (i in str.indices) {
            val char = str[i]
            val nextChar = if (i + 1 < str.length) str[i + 1] else 0.toChar()

            if (char != ' ') {
                word += char
            }

            if (char == nextChar) {
                hasRepeatedLetters = true
            }

            if (i == str.length - 1 || char == ' ') {
                if (hasRepeatedLetters) {
                    res.add(word)
                    hasRepeatedLetters = false
                }

                word = ""
            }
        }

        return res.joinToString(" ")
    }

}