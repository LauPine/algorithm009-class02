# 数据结构与算法脑图

![数据结构脑图（待补充和扩展）](https://raw.githubusercontent.com/LauPine/algorithm009-class02/master/Week_01/picture/2.jpg)

![算法脑图（待补充和扩展）](https://raw.githubusercontent.com/LauPine/algorithm009-class02/master/Week_01/picture/3.jpg)



# 用add first或add last这套新的API改写Deque的代码

```java
/**
 * 用add first或add last这套新的API改写Deque的代码
 * [c, b, a]
 * c
 * [c, b, a]
 * c
 * b
 * a
 * []
 */
public class UseDequeAddFirst {

    public static void main(String[] args) {
        Deque<String> deque = new LinkedList<String>();

        deque.addFirst("a");
        deque.addFirst("b");
        deque.addFirst("c");
        System.out.println(deque);

        String str = deque.getFirst();
        System.out.println(str);
        System.out.println(deque);

        while(deque.size() > 0) {
            System.out.println(deque.removeFirst());
        }

        System.out.println(deque);
    }
}
```



# Queue源码分析

#### Queue类图：

![Queue类图](https://raw.githubusercontent.com/LauPine/algorithm009-class02/master/Week_01/picture/1.jpg)



#### Queue的源码及解释：

```java
public interface Queue<E> extends Collection<E> {
	//如果可以在不违反容量限制的情况下立即执行此操作，则将指定的元素插入此队列，成功时返回true，如果当前没有可用空间则抛出IllegalStateException。
    boolean add(E e); 
	//如果可以在不违反容量限制的情况下立即执行此操作，则将指定的元素插入此队列，成功时返回true，如果当前没有可用空间则返回 false。
	boolean offer(E e);
	//检索并删除此队列的头部元素。 此方法与poll的不同之处仅在于，如果此队列为空，则抛出异常NoSuchElementException。
	E remove();
	//检索并删除此队列的头部，如果此队列为空，则返回null。
	E poll();
	//检索但不删除此队列的头部。 此方法与peek的不同之处仅在于，如果此队列为空，则抛出异常NoSuchElementException。
	E element();
	//检索但不移除此队列的头部，如果此队列为空，则返回null。
	E peek();
}
```

#### Queue常用操作：

| Operation（操作） | Throws exception（抛出异常） | Returns special value（返回指定值） |
| ----------------- | ---------------------------- | ----------------------------------- |
| Insert（插入）    | add(e)                       | offer(e)                            |
| Remove（删除）    | remove()                     | poll()                              |
| Examine（检查）   | element()                    | peek()                              |

#### Java Array to Queue：

```java
 public static void main(String[] args) {
     String nums[] = {"one", "two", "three", "four", "five"};
     Queue<String> queue = new LinkedList<>();
     Collections.addAll(queue, nums);
     System.out.println(queue);//[one, two, three, four, five]
 }
```

#### Java Queue to Array：

```java
public static void main(String[] args) {
    Queue<String> queue = new LinkedList<>();
    queue.add("one");
    queue.add("two");
    queue.add("three");
    queue.add("four");
    queue.add("five");
    String strArray[] = queue.toArray(new String[queue.size()]);
    System.out.println(Arrays.toString(strArray));//[one, two, three, four, five]
}

```

#### 注意点：

1. 队列实现通常不允许插入null元素，尽管某些实现（如LinkedList）不禁止插入null。 即使在允许它的实现中，也不应将null插入到Queue中，因为null也被poll方法用作特殊返回值，以指示队列不包含任何元素。
2. BlockingQueues不接受null元素。 如果我们执行任何与null相关的操作，它将抛出NullPointerException。
3. BlockingQueues用于实现基于生产者/消费者的应用程序。
4. BlockingQueues是线程安全的。
5. java.util包中可用的所有队列都是无界队列，java.util.concurrent包中可用的队列是有界队列。
6. 所有Deques都不是线程安全的。



# PriorityQueue源码分析

#### PriorityQueue方法总结：

| 修饰符和类型  | 方法                 | 描述                                                         |
| :------------ | :------------------- | :----------------------------------------------------------- |
| `boolean`     | `add(E e)`           | 将指定的元素插入此优先级队列。                               |
| `void`        | `clear()`            | 从此优先级队列中删除所有元素。                               |
| `Comparator`  | `comparator()`       | 返回用于对此队列中的元素进行排序的比较器，或者返回`null`根据该队列的[自然顺序](https://docs.oracle.com/javase/10/docs/api/java/lang/Comparable.html)对队列进行排序的比较器。 |
| `boolean`     | `contains(Object o)` | 返回`true`此队列是否包含指定的元素。                         |
| `Iterator`    | `iterator()`         | 返回对该队列中的元素进行迭代的迭代器。                       |
| `boolean`     | `offer(E e)`         | 将指定的元素插入此优先级队列。                               |
| `boolean`     | `remove(Object o)`   | 从该队列中删除指定元素的单个实例（如果存在）。               |
| `Spliterator` | `spliterator()`      | 在此队列中的元素上创建*[后期绑定](https://docs.oracle.com/javase/10/docs/api/java/util/Spliterator.html#binding)* 和*故障* [`Spliterator`](https://docs.oracle.com/javase/10/docs/api/java/util/Spliterator.html)转移。 |
| `Object[]`    | `toArray()`          | 返回一个包含此队列中所有元素的数组。                         |
| ` T[]`        | `toArray(T[] a)`     | 返回一个包含此队列中所有元素的数组；返回数组的运行时类型是指定数组的运行时类型。 |

#### 注意点：

1. java 中 PriorityQueue 是一个基于 priority head 的无界优先队列。
2. 不支持 null 元素，元素必须能够进行比较。
3. 可以通过实现了 Comparable 接口的元素或者构造时传入实现了 Comparator 的比较器实现比较。
4. iterator() 返回的迭代器是无序的。
5. 不是线程安全的，线程安全的版本是 [`PriorityBlockingQueue`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/PriorityBlockingQueue.html)。



# 学习总结

#### 方法论：

1. 暴力法。
2. 一维的数据结构想要加速，经常采用的方式就是空间换时间升维，变成二维。
3. 左右夹逼办法（双指针法）（快慢指针）。
4. 找最近重复性规律。
5. 有最近相关性可以考虑用栈来解决。
6. 固定中间棒子找到左边界和右边界的方法。
7. 滑动窗口类题目可以用队列解决，双端队列。
8. 只用栈来实现队列的问题，可以考虑用两个栈。
9. 只用队列来实现栈的问题，可以考虑用两个队列。