import kotlin.math.abs

class ListNode(var value: Int) {
    var next: ListNode? = null
}

object LinkedList {
    /**
     * Given an integer array nums of length n where all the integers of nums are in the range [1, n] and
     * each integer appears once or twice, return an array of all the integers that appears twice.
     * You must write an algorithm that runs in O(n) time and uses only constant extra space.
     * */
    fun findDuplicates(nums: IntArray): List<Int> {
        val result = mutableListOf<Int>()

        for (num in nums) {
            if (nums[abs(num) - 1] < 0)
                result.add(abs(num))

            nums[abs(num) - 1] *= -1
        }
        return result
    }

    /**
     * Let's call an array arr a mountain if the following properties hold:arr.length >= 3
     * There exists some i with 0 < i < arr.length - 1 such that: arr[0] < arr[1] < ... arr[i-1] < arr[i]
     * arr[i] > arr[i+1] > ... > arr[arr.length - 1] Given an integer array arr that is guaranteed to be a mountain,
     * return any i such that arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1].
     * */
    fun peakIndexInMountainArray(arr: IntArray): Int {
        var left = 0
        var right = arr.size - 2

        while (left <= right) if (arr[right] < arr[right + 1]) left = right + 1 else right -= 1

        return left
    }

    /**
     * Given the head of a singly linked list, return the middle node of the linked list.
     * If there are two middle nodes, return the second middle node.
     * */
    fun middleNode(head: ListNode?): ListNode? {
        var first = head
        var second = head
        while (first?.next != null) {
            first = first.next?.next
            second = second?.next
        }

        return second
    }

    /**
     * Given the head of a linked list, remove the nth node from the end of the list and return its head.
     * */
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        var fast: ListNode? = head
        var slow: ListNode? = head
        for (i in 0 until n) fast = fast?.next
        if (fast == null) return head?.next
        while (fast?.next != null) {
            fast = fast.next
            slow = slow?.next
        }
        slow?.next = slow?.next?.next
        return head
    }

    /**
     * Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect.
     * If the two linked lists have no intersection at all, return null.
     * For example, the following two linked lists begin to intersect at node c1:
     * */
    fun getIntersectionNode(headA: ListNode?, headB: ListNode?): ListNode? {
        var pointA = headA
        var pointB = headB
        while (pointA != pointB) {
            pointA = if (pointA == null) headB else pointA.next
            pointB = if (pointB == null) headA else pointB.next
        }
        return pointA
    }

    fun reverseList(head: ListNode?): ListNode? {
        var previous: ListNode? = null
        var current = head
        while (current != null) {
            val nextElement = current.next
            current.next = previous
            previous = current
            current = nextElement
        }
        return previous
    }


    fun deleteNode(node: ListNode?) {
        node?.let {
            with(node) { next = next?.next.also { value = next!!.value } }
        }
    }

    class MyLinkedList {
        class Node(val value: Int) {
            var next: Node? = null
        }

        private var head: Node? = Node(0)
        private var size = 0

        fun get(index: Int): Int {
            if (index < 0 || index >= size) return -1

            var node = head
            for (i in 0 until index + 1) {
                node = node?.next
            }
            return node?.value ?: -1
        }

        fun addAtHead(value: Int) {
            addAtIndex(0, value)
        }

        fun addAtTail(value: Int) {
            addAtIndex(size, value)
        }

        fun addAtIndex(index: Int, value: Int) {
            size++
            var node = head
            for (i in 0 until index) {
                node = node?.next
            }
            if (node != null) {
                val newNode = Node(value)
                newNode.next = node.next
                node.next = newNode
            }
        }

        fun deleteAtIndex(index: Int) {
            if (index < 0 || index >= size) return
            size--
            var node = head
            for (i in 0 until index) {
                node = node?.next
            }
            if (node != null) {
                node.next = node.next?.next
            }
        }
    }


    /**
     * Given head, the head of a linked list, determine if the linked list has a cycle in it.
     * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously
     * following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is
     * connected to. Note that pos is not passed as a parameter.
     * Return true if there is a cycle in the linked list. Otherwise, return false.
     * */
    fun hasCycle(head: ListNode?): Boolean {
        var slow = head
        var fast = head
        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
            if (slow === fast) {
                return true
            }
        }
        return false
    }

    /**
     * Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
     * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously
     * following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer
     * is connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.
     * Do not modify the linked list.
     * */
    fun detectCycle(head: ListNode?): ListNode? {
        var slow = head
        var fast = head
        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
            if (slow === fast) {
                fast = head
                while (fast != slow) {
                    fast = fast?.next;
                    slow = slow?.next;
                }
                return slow;
            }
        }
        return null
    }
}