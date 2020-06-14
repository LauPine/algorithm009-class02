第9课 深度优先搜索和广度优先搜索

#### 一、深度优先搜索

- 深度优先搜索使用递归或栈实现。

- 对于树的深度优先搜索，首先访问根节点，然后递归遍历每个子树，一个子树遍历完毕再遍历另一个子树。

- 对于图的深度优先搜索，从起点开始，和树的情况相似。

- 递归写法：

  ```
  visited.add(node)
  
  # process current node here
  ...
  for next_node in node.children():
  	if not next_node in visited:
  		dfs(next_node,visited)
  ```

- 非递归写法：

  ```
  visited,stack = [],[tree.root]
  
  while stack:
  	node = stack.pop()
  	visited.add(node)
  	
  	process(node)
  	node = generate_related_nodes(node)
  	stack.push(nodes)
  	
  #other prosessing work
  ```

  

#### 二、广度优先搜索

- 广度优先搜索使用队列实现。

- 广度优先搜索每次遍历位于同一层的所有节点，可以用于最短路径求解。

- 模板：

  ```
  while queue:
  	node = queue.pop()
  	visited.add(node)
  	
  	process(node)
  	nodes = generate_related_nodes(node)
  	queue.push(nodes)
  	
  #other prosessing work
  ```

  

# 第10课 贪心算法

#### 一、贪心算法

贪心算法是一种在每一步选择中都采取在当前状态下最好或最优（即最有利）的选择，从而希望导致结果是全局最好或最优的算法。

贪心算法与动态规划的不同在于它对每个子问题的解决方案都做出选择，不能回退。动态规划则会保存以前的运算结果，并根据以前的结果对当前进行选择，有回退功能。

贪心算法可以解决一些最优化问题，但是并不总是能得到最优解。

# 第11课 二分查找

#### 一、二分查找的前提

1. 目标函数单调性（单调递增或递减）。
2. 存在上下界（bounded）。
3. 能够通过索引访问（index accessible）。

#### 二、二分查找的模板

```
left, right = 0, len(array) - 1
while left <= right:
    mid = (left + right) / 2
    if array[mid] == target:
        # find the target!!
        break or return result
    elif array[mid] < target:
        left = mid + 1
    else:
        right = mid - 1
```

