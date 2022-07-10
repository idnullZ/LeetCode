object Sort {

    fun bubbleSort(arr: Array<Int>): Array<Int> {
        var sorted = false
        while (!sorted) {
            sorted = true
            for (i in 0 until arr.size - 1) {

                if (arr[i] > arr[i + 1]) {
                    val tempt = arr[i]
                    arr[i] = arr[i + 1]
                    arr[i + 1] = tempt

                    sorted = false
                }
            }
        }
        return arr
    }

    fun  sortSelection(array: Array<Int>): Array<Int> {
        fun findMinElement(array: Array<Int>, start: Int, end: Int): Int {
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

        for (i in array.indices) {
            val minIndex: Int = findMinElement(array = array, start =  i,end =  array.size);
            val tmp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = tmp;
        }
        return array
    }

}