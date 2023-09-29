# 关于第二次作业的解析与说明

> 文档简介：第二次作业开始正式迭代开发，本次作业难度一般，主要目标是编写**基础代码**准备接下来几周的**迭代与扩展**。  
> **_注意：划删除线的部分并非过时信息！_**
## Part 1

指导书的大致要求是：
实现以下类

```text
Adventure ：ID，名字，药水瓶和装备各自的容器
Bottle：ID，名字，容量(capacity)
Equipment：ID，名字，星级(star)
```

以及实现以下操作

```text
1.加入一个需要管理的冒险者（新加入的冒险者不携带任何药水瓶和装备）
2.给某个冒险者增加一个药水瓶
3.删除某个冒险者的某个药水瓶
4.给某个冒险者增加一个装备
5.删除某个冒险者的某个装备
6.给某个冒险者的某个装备提升一个星级
* 其中，提升星级的意思是，新星级=原有星级+1
```

并且做了一些约束使问题简化，这里不再赘述。

我认为本次作业主要考虑**三点**：

* **如何用合适的容器对*Adventure*类、*Bottle*类以及*Equipment*类进行管理**
* 上述三个类的**内部结构如何实现**
* 如何实现**同时返回**一个**字符串和整数**信息

**先来看第一点。**  
对于第一点，我一开始的思路是，用*ArrayList*来管理三个类，这是一种很自然的想法。但是，在随后的具体实现中，我发现很多操作要求通过ID来获取对象，而由于ID属性被封装在各个类中，
~~以我的水平只能~~对*ArrayList*
遍历来查找需要的冒险者、药瓶或者是装备。  
这样带来的坏处是，需要自己实现遍历查找算法，而且遍历效率极低。同时，**checkstyle**还会提示**for循环部分重复度过高**(查找
*Bottle*和*Equipment*时有大量重复结构)。  
那么，有没有更好的改进办法呢？我想到了下面的思路：
> 利用每个对象**ID**属性的唯一性，用ID作为**Key**，相应对象（*Adventure*类、*Bottle*类以及*Equipment*类）做为**Value**
> ，构建HashMap来进行管理。

也就是如下三个容器：

```java
HashMap<Integer, Adventure> adventures = new HashMap<>();
HashMap<Integer, Bottle> bottles = new HashMap<>();
HashMap<Integer, Equipment> equipments = new HashMap<>();
```

这样的好处是，HashMap类本身已经提供了通过Key快速查找Value的方法，而且**代码简洁**，**checkstyle**也不会提示重复度过高的问题。可谓一举两得。

**接下来看第二点。**
有了第一点的思路，各个类的内部结构也就比较清晰，以功能最多的*Adventure*类为例，应该如下：

```java
//属性
    private final int id;
    private final String name;
    private HashMap<Integer, Bottle> bottles = new HashMap<>();//管理Bottle
    private HashMap<Integer, Equipment> equipments = new HashMap<>();//管理Equipment
//方法
    public Adventure(int id, String name) {
    //...
    }
    public void addBottle(Bottle bottle) {
    //...
    }

    public void addEquipment(Equipment equipment) {
    //...
    }

    public PrintInfo removeBottle(int bottleId) {
    //...
    }

    public PrintInfo removeEquipment(int equipmentId) {
    //...
    }

    public PrintInfo increaseStar(int equipmentId) {
    //...
    }

    public HashMap<Integer, Bottle> getBottles() {
    //...
    }

    public HashMap<Integer, Equipment> getEquipments() {
    //...
    }
```

**最后是第三点。**  
在**java**中，一个方法或者函数**只能返回一个对象**，那么如何同时返回一个字符串和整数呢？  
一开始，我同样想用*HashMap*，但是又遇到问题：我们每次只需要**一个Key-Value对**，为此单独构造一个容器似乎没有必要，而且这也没有体现
**Key和Value的映射关系**  
因此经过考虑，我自定义了一个工具类*PrintInfo*，能根据一个整数值和字符串值构造对象，并提供不同顺序的打印方法。

```java
public class PrintInfo {
    private final int numOrStar;
    private final String name;

    public PrintInfo(int numOrStar, String name) {
        //...
    }

    public void printIntFirst() {
        //...
    }

    public void printStringFirst() {
        //...
    }
}
```

这样 负责删除且需要返回结果的方法便可以声明返回该类型。  
~~解决了这三个问题，Part 1的要求相信很快就能解决。~~

## Part 2

~~本次作业Part 2要求不高，自行阅读文档，了解*JUnit*类的使用即可。~~
