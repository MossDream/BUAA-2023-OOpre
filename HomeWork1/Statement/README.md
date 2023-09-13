# 第一次作业指导书

## 训练目标

- 学习 Java 的基本语法并完成给定代码的错误修改

## 任务：基于给定 Java 代码完成错误的修改

### 作业要求

课程组在本次作业对应的git仓库中提供了一份有错误的代码，你需要对代码进行修改，使程序能够正确的完成上面的场景逻辑。完成代码的修改后，你需要将代码提交到hw1的评测窗口，并且通过所有测试点

### 代码场景描述

该代码模拟一个孩子从水果店购买水果和吃水果的场景。

题目中涉及的水果种类有且仅有苹果（apple）和香蕉（banana）。初始时刻，孩子持有 20 元且没有任何水果，水果店有 5 个苹果和 5 个香蕉，其中每个苹果 3 元，每个香蕉 2 元。

在水果店购买水果可以增加孩子手中的对应的水果数目。对于一种水果，只有孩子的钱足够购买它，并且店内这种水果的数量大于 0 时才可以成功购买这种水果，否则购买失败。

吃水果会减少孩子手中水果数目，只有孩子手中某种水果的数目大于 0 孩子才可以食用这种水果，否则食用失败

### 输入

本次作业的输入按照如下的格式约定：

- 在第一行输入一个 整数 n    (1<n<50)
- 接下来 n 行每行输入一个命令，格式为 `eat/buy`+ 空格 + 水果名称，代表孩子尝试食用/购买对应水果。
- 保证水果名称仅仅有 banana 和 apple。

### 输出

输出的内容在我们所给的代码中是正确完成的，同学们不修改 `System.out` 相关的内容即可。正确实现后的代码会在每一个接受一个命令后输出执行成功或者失败。若成功则输出 `ok` ，失败则输出 `failed`。

### 样例

样例 1

```
4
buy apple
eat apple
eat apple
eat banana
```

期望输出

```
buy apple ok!
eat apple ok!
eat apple failed!
eat banana failed!
```

<br/>
样例 2

```
5
eat banana
eat apple
buy banana
eat apple
eat banana
```

期望输出

```
eat banana failed!
eat apple failed!
buy banana ok!
eat apple failed!
eat banana ok!
```

<br/>
样例 3

```
6
buy apple
buy apple
buy apple
buy apple
buy apple
buy apple
```

期望输出

```
buy apple ok!
buy apple ok!
buy apple ok!
buy apple ok!
buy apple ok!
buy apple failed!
```
