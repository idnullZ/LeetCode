class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

object Tree {

    /**
     * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
     * Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
     * */
    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        if (p == null && q == null) return true
        if (p == null || q == null) return false
        if (p.`val` != q.`val`) return false
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right)
    }

    /**
     * Given an integer array nums where the elements are sorted in ascending order,
     * convert it to a height-balanced binary search tree.
     * A height-balanced binary tree is a binary tree in which the depth of the two subtrees of
     * every node never differs by more than one.
     *
     * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
     * */
    fun sortedArrayToBST(nums: IntArray): TreeNode? {
        fun buildTree(nums: IntArray, start: Int, end: Int): TreeNode? {
            if (start > end) return null

            val middle = (start + end) / 2
            val middleNode = TreeNode(nums[middle])
            middleNode.left = buildTree(nums, start, middle - 1)
            middleNode.right = buildTree(nums, middle + 1, end)
            return middleNode
        }

        return buildTree(nums, start = 0, end = nums.size - 1)
    }
}












