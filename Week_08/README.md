## 第16课 位运算

### 一、位运算

1. 将x最右边的n位清零：x &(~0 << n)
2. 获取x的第n位值（0|1）: (x>>n)&1
3. 获取x的第n位的幂值：x&(1 << n )
4. 仅将第n位置为1：x|(1 << n )
5. 仅将第n位置为0：x&(~(1<< n))
6. 将x最高位至第n位（含）清零 x&((1<< n) -1)

### 二、逻辑移位与算术移位

逻辑移位是指逻辑左移和逻辑右移，移出的空位都用0来补。

算术移位需要分有符号型值和无符号型值。

对于无符号型值，算术移位等同于逻辑移位。

而对于有符号型值，算术左移等同于逻辑左移，算术右移补的是符号位，正数补0，负数补1。

## 第17课 布隆过滤器和LRU缓存

### 一、布隆过滤器

一个很长的二进制向量和一系列随机映射函数。可用于检索一个元素是否在集合中。

优点：空间效率和时间效率远超一般算法 缺点：有一定的误判率、删除困难

应用：比特币网络、分布式系统（Hadoop）、Redis 缓存、垃圾邮件评论过滤

### 二、LRU缓存

- 两个要素：大小、替换策略
- 实现：HashTable + Double LinkedList
- 特性：O(1)查询、更新

## 第18课 排序算法

### 一、排序算法

排序算法可以分成两类，比较类排序和非比较类排序。

#### 1. 比较类排序

通过比较来决定元素间的相对次序，由于其时间复杂度不能突破O(n log n)，因此也称为非线性时间比较类排序。

比较类排序的例子：交换排序（冒泡排序、快速排序）、插入排序（简单插入排序、希尔排序）、选择排序（简单选择排序、堆排序）、归并排序（二路归并排序、多路归并排序）。

#### 2. 非比较类排序

不通过比较来决定元素间的相对次序，它可以突破基于比较排序的时间下界，以线性时间运行，因此也称为线性时间非比较类排序。

非比较类排序的例子：计数排序、桶排序、基数排序。

### 二、初级排序——O(n^2)

- 选择排序（Selection Sort）

  每次找最小值，然后放到待排序数组的起始位置。

- 插入排序（Insertion Sort）

  从前到后逐步构建有序序列；对于未排序数组，在已排序序列中从后向前扫描，找到相应位置并插入。

- 冒泡排序（Bubble Sort）

  嵌套循环，每次查看相邻的元素，如果逆序，则交换。

### 三、高级排序——O(n log n)

- 快速排序（Quick Sort）

  数组取标杆pivot，将小元素放pivot左边，大元素放pivot右边，然后依次对左边和右边的子数组继续快排，以达到整个序列有序。

- 归并排序（Merge Sort）

  1. 把长度为n的输入序列分成两个长度为n/2的子序列；
  2. 对这两个子序列分别采用归并排序；
  3. 将两个排序号的子序列合并成一个最终的排序序列。

- 堆排序（Heap Sort）

  1. 数组元素依次建立小顶堆；
  2. 依次取堆顶元素，并删除。

### 四、作业：实现初级排序代码

```java
    public void selectionSort(int[] arr) {
        // 总共要经过 N-1 轮比较
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            // 每轮需要比较的次数 N-i
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    // 记录目前能找到的最小值元素的下标
                    min = j;
                }
            }
            // 将找到的最小值和i位置所在的值进行交换
            if (i != min) {
                int tmp = arr[i];
                arr[i] = arr[min];
                arr[min] = tmp;
            }

        }
        return arr;
    }

    public void insertionSort(int[] nums) {
        // 从下标为1的元素开始选择合适的位置插入，因为下标为0的只有一个元素，默认是有序的
        for (int i = 1; i < arr.length; i++) {
            // 记录要插入的数据
            int tmp = arr[i];
            // 从已经排序的序列最右边的开始比较，找到比其小的数
            int j = i;
            while (j > 0 && tmp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            // 存在比其小的数，插入
            if (j != i) {
                arr[j] = tmp;
            }
        }
        return arr;
    }

    public void bubbleSort(int[] nums) {
        for (int i = 1; i < arr.length; i++) {
            // 设定一个标记，若为true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已经完成。
            boolean flag = true;
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;

                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
        return arr;
    }

```

