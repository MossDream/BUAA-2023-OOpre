# 关于第三次作业的解析与说明
 
> 文档简介：第三次作业在第二次的基础上进行扩展，本文也从已经**通过第二次作业强测**的程序基础上进行讲解分析。  
> **_注意：划删除线的部分并非过时信息！_**

# Part 1

## 指导书要求分析

指导书扩展要求后，要求为：  
实现以下类

```text
Adventure ：ID，名字，药水瓶和装备各自的容器，背包，体力（HitPoint），等级（level）
Bottle：ID，名字，容量(capacity)
Equipment：ID，名字，星级(star)
Food：ID，名字，能量(energy)
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
7.给冒险者增加一个食物
8.删除冒险者的一个食物
9.冒险者尝试携带他拥有的某件装备
10.冒险者尝试携带他拥有的某个药水瓶
11.冒险者尝试携带他拥有的某个食物
12.冒险者使用某个药水瓶
13.冒险者使用某个食物
```

其中，新增的**背包要求**是本次作业的核心，引用如下：
> 背包限制  
> 在上一次的作业里，我们定义了添加的概念（add），这个仅仅是让这名冒险者拥有了这个物品，但是他并没有携带这个物品。我们认为，当且仅当
**这个物品属于该冒险者且在该名冒险者的背包中**，才算他携带了这个物品。下面对每类物品给出携带与使用的规定。

> 装备

- 限制  
  **冒险者只能携带一件同名装备**。  
  若冒险者已经携带了名字为 `sword` 的装备（该装备 `id` 为 1），下一次再尝试携带另一个名字为 `sword ` 的不同装备（该装备`id`为
  2）时，原本 `id` 为 1 的 `sword` 会被顶替。注意被顶替的装备依然属于此冒险者。

> 药水瓶    
> 当冒险者携带药水瓶 A 时，他才能使用该药水瓶 A，否则为使用失败。  
> 冒险者使用某药水瓶时，若药水瓶不为空，则冒险者的体力增加 x（x 为该药水瓶的容积），药水瓶变空。  
> 若药水瓶为空，冒险者的体力增加为 0，同样视作使用成功。同时，为了给继续携带药水瓶腾出空间，在使用后冒险者将丢弃该空药水瓶，即该药水瓶将不再被该冒险者拥有。对于这个丢弃行为我们不需要进行输出。

- 限制  
  冒险者只能携带 `max_bottles` 个同名的药水瓶，其中max_bottles的值满足 `max_bottles = level / 5 + 1`。
  例如，若冒险者等级为 19, 则 `max_bottles` 的值是 4。假如在这时，该冒险者携带了四个同名药水瓶 `water`
  ，之后再尝试携带同名的药水瓶后时，他的状态不会有任何改变，也不需要输出任何内容，他也不会携带新的同名药水瓶。

> 食物  
> 当冒险者携带食物 A 的时候，他才能使用该食物 A，否则为使用失败。  
> 当冒险者使用食物时，他将消耗掉该食物（从此该食物**不再属于**该冒险者），冒险者的等级提升 x（x 为食物的能量）。  
> 比如冒险者当前等级为 1，拥有食物 A，A 的能量为 2。冒险者在携带后使用食物 A，则冒险者不再拥有食物 A，同时冒险者的等级变为
> 3。

- 限制
  背包对食物的数量没有任何限制。

> 特别的，我们规定，假设冒险者 A 尝试携带的物品 B ，本身已经在冒险者背包里了，那么该条指令不会造成任何影响（不需要任何输出，同时物品
> B 依旧在冒险者的背包里 ）

## 思路要点

应该分成两个部分

### 基于上次作业形式的直接扩展与修改

具体有：

1. *Food*类的实现与管理
2. `remove`型函数返回类型的优化

### 本次作业的核心新增特性：背包管理

具体有：

3. **携带**动作：`takenBottles`、`takenEquipments`、`takenFoods`的实现与管理
4. **使用**动作：`useBottle`、`eatFood`的实现

## 具体分析

针对思路要点里面的思路进行实现

### 1. *Food*类的实现与管理

~~这点很简单。~~  
*Food*类和先前的*Bottle*类、*Equipment*类内部结构很像，`foods`容器的管理也和先前的`bottles`、`equipments`
容器的管理很像，还有`add`和`remove`函数的实现也很像，所以这里不再赘述，参照前一作业模板即可。

### 2. `remove`型函数返回类型的优化

~~这点也不是很难。~~  
由于第四点**使用**动作要求使用成功与失败返回不同的形式，因此不能直接返回一个*PrintInfo*类类型。最简单的思路就是采用
**直接打印的形式**，分情况打印不同的信息。

### 3. **携带**动作：`takenBottles`、`takenEquipments`、`takenFoods`的实现与管理

**三、四两点是本次作业的核心。**  
携带动作的要求上面已经引用。我目前的实现思路是将*Bottle*、_Equipment_、*Food*三个物品分别对应设置*Adventure*
类中的三个私有属性：`takenBottles`、`takenEquipments`、`takenFoods`，分别用于管理冒险者携带的药水瓶、装备、食物。

- 将来有需要可以将其封装成*Package*类。

**重点是用什么容器类型实现这三个属性**。  
我们应该注意到，对背包物品的使用是以`name`属性来查找，并且取`id`最小的那个。因此，以`takenBottles`
为例，我们应该使用的是`HashMap<String , TreeMap<Integer,Bottle>>`类型。  
`HashMap`以*String*做为key，提供了按名字查找的方法，简洁高效；  
`TreeMap`以*Integer*做为key，提供了按*id*升序排列的一系列同名物品，我们要用的就是一系列同名物的第一个。    
由于携带要求不同，几个属性的实现也有所不同，具体如下：  
对于`takenBottles`，应该根据`maxBots`,先判断是否已有同名物品，若没有则新建一个以该物品名字为key，新建映射；若有，则找到value（
*TreeMap*），判断是否已有*Bottle*数是否已达到上限`maxBots`，若达到上限则不做任何操作，若未达到上限则在value中新增这个
*Bottle*。  
对于`takenEquipments`，由于其特殊性，类型可以简化为`HashMap<String , Equipment>`,每次无需判断直接加入，*HashMap*的`put`
方法会自动覆盖同名物品。  
对于`takenFoods`，和`takenBottles`类似，但是不需要判断上限，省略该步即可。

### 4. **使用**动作：`useBottle`、`eatFood`的实现

使用动作的要求上面已经引用。  
首先判断是否有所需名字的物品，没有，按格式输出；有，则利用*TreeMap*特性，取出第一个物品，进行使用。    
*TreeMap*提供了`firstEntry`方法和`pollFirstEntry`方法，前者只返回第一个映射，即*TreeMap*中*id*
最小的那个，后者返回并移除这个映射。  
对于`useBottle`，先只返回，按要求分空与不空进行处理（`hp`等），**切记当一个名字下没有物品时（*TreeMap*为空），要在*HashMap*
中移除这个映射**；  
对于`eatFood`，直接返回并移除，按要求进行处理（`level`等），**切记重算`maxBot`，同样当一个名字下没有物品时（*TreeMap*
为空），要在*HashMap*中移除这个映射**。

## 最后

在主程序*Main*类中，单独使用`main`方法已经太长，可以把每种不同的操作封装成一个方法，然后在`main`
方法中调用这些方法，这样可以使得`main`方法更加简洁，也方便调试。  
**本部分就大功告成了。**

# Part 2

仍然是*Junit*测试，这次有覆盖率要求，按照教程操作即可。

## Part 2新增内容

> 在后续的过程中，我发现不少人对于Junit的覆盖率要求比较头疼。在正式提交评测前，我本人也对Main类的结构进行了重新修改，这里分享一下我的大致结构。

```java
public static void main(String[]args){
        int n;
        HashMap<Integer, Adventure> advs=new HashMap<>();
        Scanner scanner=new Scanner(System.in);
        n=Integer.parseInt(scanner.nextLine().trim()); //注意，直接用nextInt()会出错
        for(int i=0;i<n; i++){
        String nextLine=scanner.nextLine(); // 以一行字符串的形式读入一条指令
        makeChoice(advs,getOrders(nextLine));
            /*getOrders把一行指令拆成各部分，返回一个ArrayList<String>，其中第一个元素是指令类型，后面的元素是指令的参数
             makeChoice根据指令类型，调用相应的方法，switch语句就在该方法中*/
        }
        }
```

**需要遵守的原则是，`main`方法中尽量只有输入处理，且所有输入处理都在这里面完成。然后对其他所有方法编写单元测试。**

# Part 3 bug修复记录

## Bug 001

### 说明

`remove`型函数执行时，需要将背包里的相应物品也一并删除！

### 改动详情

在`Adventure.java`文件中：

```diff

//现第70行
-  if (takenBottles.containsKey(bottleName))  
+  if (takenBottles.containsKey(bottleName) && !takenBottles.get(bottleName).isEmpty())

//现第92行
-  if (takenFoods.containsKey(foodName)) 
+  if (takenFoods.containsKey(foodName) && !takenFoods.get(foodName).isEmpty())  

//现第111行
+          if (takenBottles.containsKey(name)) {
+            takenBottles.get(name).remove(bottleId);
+            if (takenBottles.get(name).isEmpty()) {
+                takenBottles.remove(name);
+            }
+        }

//现第123行
+          if (takenEquipments.containsKey(name)) {
+            int id = takenEquipments.get(name).getId();
+            if (id == equipmentId) {
+                takenEquipments.remove(name);
+            }
+        }

//现第135行
+          if (takenFoods.containsKey(name)) {
+            takenFoods.get(name).remove(foodId);
+            if (takenFoods.get(name).isEmpty()) {
+                takenFoods.remove(name);
+            }
+        }
```
