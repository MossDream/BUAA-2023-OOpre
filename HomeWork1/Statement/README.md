# 第一次作业指导书

## 训练目标

- 学习使用 `git`以及 `gitlab`相关操作
- 学习使用课程网站提交
- 学习 Java 的基本语法并完成给定代码的错误修改

## 任务一：git 学习

### step 0 git 安装与配置

#### git 的安装

##### Linux

```bash
sudo apt-get install git
```

##### Mac OS X

从 AppStore 安装 Xcode，运行 Xcode，选择菜单 "Xcode" -> "Preferences"，在弹出窗口中找到 "Downloads"，选择 "Command Line Tools"，点 "Install" 即可完成安装。

##### Windows

在[官网](https://git-scm.com/downloads)上选择最新版本进行下载安装。

#### git 的配置

```bash
git config --global user.name "你的名字"
git config --global user.email "22xxxxxx@buaa.edu.cn"
```

利用上述指令配置 git，注意将 "你的名字" 和 "22xxxxxx@buaa.edu.cn" 分别替换成你真实姓名和北航邮箱。

#### 配置 ssh key

参考[官网](https://docs.gitlab.com/ee/ssh/)上给出的介绍说明进行 ssh key 的配置

### step 1 新建仓库

**在本地新建一个空文件夹，在此目录下打开终端（bash/git bash/powershell/...）**

输入

```bash
git clone 你的个人第一次作业的远程仓库链接
```

（其中个人作业 1 仓库的名字为 ooprehomework_2023_你的学号_hw_1，个人第一次作业的远程仓库链接进入仓库后可以看见）


### step 2 尝试一次提交

下面两种方式均可，建议刚上手的同学使用IDEA提交

#### 命令行提交

在你的仓库目录下

```bash
git add 你要提交的文件
git commit -m "Init commit"
git push
```

#### IDEA提交

用IDEA打开你`git clone`下来的仓库使用内置的git工具提交即可

至此，任务一的内容已全部介绍完毕，如果按照上述步骤操作后得到预期结果（例如可以正常 `pull` 和 `push`），即可开始进行任务二。

## 参考资料

1. [Git 使用心得 & 常见问题整理](https://juejin.cn/post/6844904191203213326)
2. [Git 廖雪峰教程](https://www.liaoxuefeng.com/wiki/896043488029600)文档中可以找到相关内容。

##  其他

以上部分仅为练习，不计入作业成绩。

## 任务二：基于给定 Java 代码完成错误的修改

### Part 1. Java 基础知识梳理

> 以下教程的操作与提示全部基于 **IntelliJ IDEA**

#### 一、从 C 到 Java

在本次作业，我们希望同学们能通过实现一系列基础的类，并且熟悉类、属性和方法的使用，从面向过程编程思想迈向面向对象编程思想。

##### Java 编程的模式

![Java 项目示例](http://api.oo.buaa.edu.cn/image/2023_08_31_21_40_52_cfdb355a3861c7274d19fbac667145da9a12db0a)

区别于我们之前高级语言程序设计、数据结构课程所使用的基于过程的程序设计方法，即使用一个文件( XXX.c )包含所有代码，
面向对象编程则是将程序分解为一个个的对象，让每个 **类( .java 文件)** 做自己的事情，它将数据和操作数据的函数封装在一起。在程序的最终执行过程中，是消息传递的，即通过类之间的消息传递来实现程序的执行。

##### 程序入口

在刚刚的编程模式的图文讲解中我们出现了 `MainClass` 即 `主类` 的概念，那么 MainClass 究竟又有什么作用呢？

```java
public class MainClass {
    public static void main(String[] argv) {
       /* …… */
       /*coding context*/
       /* …… */
    }
}
```

Java 中的方法 `public static void main(String[] argv)`，就相当于 C 语言的入口函数 `main()` 。这个 `main` 是你 Java 主程序的执行入口，当运行 Java 程序时，你可以理解成将会从此处开始执行。为了方便评测，在 OO 课程中，**请你保证 `public static void main(String[] argv)` 方法出现且只出现在一个类（通常为 `MainClass`）中**。

> 实际应用中，可以有多个类中包含 `main` 方法，我们可以手动指定一个类中的 `main` 方法作为程序入口。

##### hw1 涉及的语法知识

1. 输出

    我们向屏幕输出 `Hello World!`：

    ``` java
    String str = "Hello";
    System.out.println(str + " World!"); 
    // 输出结果: 
    // Hello World!
    ```

    在输出过程中可以发现，我们用到了 System 类的一个方法。System 类位于 Java.lang 包，代表当前 Java 程序的运行平台，系统级的很多属性和控制方法都放置在该类的内部。目前同学们可以把它理解为 Java 为我们提供的一系列“库函数”。System 类是一个很特殊的类，我们无法创建该类的对象，即无法实例化该类。但 System 类提供了一系列静态的类变量和类方法，我们可以直接通过 System 类来调用这些类变量和类方法。

2. 输入

    我们从标准输入读入整数`a`，浮点数`b`，字符串`c` 和 `d`：

    ``` java
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt(); // 从标准输入读入一个 int 型的数
    double b = scanner.nextDouble(); // 从标准输入读入一个 double 型的数
    String c = scanner.next(); // next 方法以空白符作为分隔从标准输入读入字符串
    String d = scanner.nextLine(); // nextLine 方法以换行符作为分隔从标准输入读入字符串
    // 对于 c 和 d 的读入方式，若分别输入 hello world
    // c = "hello"
    // d = "hello world"
    ```

    在输入过程中可以发现，我们用到了 `Scanner` 类，并对他进行了实例化。`Scanner` 类是 Java 的一个内置类，它提供了一种简便的方法从标准输入（System.in）或者文件（FileInputStream）中读取数据类型和字符串的功能。

    Scanner 类可以读取`int`、`long`、`float`、`double`和`String`等类型。它还可以使用正则表达式读取符合模式的文本。

    Scanner 类提供了一系列的方法来读取不同类型的数据。除了上述我们介绍的几种读取方法，Scanner 类还提供了 `hasNext()` 和 `hasNextLine()` 等方法，用于检测是否还有更多的数据可以读取。

    这部分内容对同学们来说或许有些难以理解，可以容后再看，在接触一门新语言时候，我们先掌握他的功能性用法就足够了。

    此外，除了这几种用法，Scanner 还有一个叫 `nextLine()` 的方法，你可能会在这次的作业中用到，它们的区别可以参考以下链接：[Java Scanner 类 | 菜鸟教程 (runoob.com)](https://www.runoob.com/java/java-scanner-class.html)

    值得注意的是，在今后的学习中，同学们还会遇到很多无法靠名字准确识别功能的方法，这时候就需要大家查阅资料或者翻看其具体实现，避免因为认识不清而导致的 bug

3. `if-else`, `switch-case`,`for`,`while`等关键字的用法和 C 是相同的。

##### 错误处理

1. 编写程序时的错误:

    ![语法错误](http://api.oo.buaa.edu.cn/image/2023_08_31_21_48_23_261c908607a8c474a6e5fae756171ddce805c31d)

    当类右上角出现红色感叹号证明编写程序有误，无法通过编译，可点击右上角红色感叹号观察对应错误信息并进行修改。

2. 程序执行时的错误：

    ![运行时错误](http://api.oo.buaa.edu.cn/image/2023_08_31_21_48_37_4e576adf19d6883e37eab924b2610cf346696737) 

    当程序已经通过编译，在执行时出现错误，可以点击对应报错部分的蓝色高亮区域跳转到出错位置，idea会有浮动窗口显示可能的错误原因，方便同学修改代码。

3. 常见编译错误

+ **…… Expected**

``` java
System.out.println("缺少分号") // !
System.out.println("缺一半括号"; // !
```

+ **Unclosed String Literal**
  这类编译错误出现的原因大概率是你的字符串缺少了 **”**

``` java
System.out.println("缺少一半引号); // !
```

+ **Cannot Find Symbol**
  这类编译错误出现的原因主要是使用了未定义的变量。例如：标识符声明时的拼写可能与代码中使用时的拼写不一致；变量从未被声明；未在同一作用域内声明该变量；没有导入类

```java
int defined = 3;
int result = define - i; // !
```

``` java
int sum = 0;
for (int i = 0 ; i < 3; i++) {
    sum += i*i;
}
i++; // !
```

+ **Missing Return Statement**
  编译器抛出该问题的可能性有：返回语句被错误地省略了；该方法没有返回任何值，但是在方法声明中未声明类型为void

``` java
public int add(int a, int b) {
    int sum = a + b;
}    
```

```java
public int add(int a) {
    int b = -1;
    if (b > 0) {
        return a + b;
    }
}    
```

+ **Non-Static Method … Cannot Be Referenced From a Static Context**
  当编译器抛出这个问题的时候，意味着代码存在从静态上下文中引用非静态变量

```java
public class StaticTest {  
    private int count=0;  
    public static void main(String args[]) throws IOException {  
        count++; // !
    }  
} 
```

+ **Non-Static Method … Cannot Be Referenced From a Static Context**
  当编译器抛出这个问题的时候，意味着代码尝试在静态类中调用非静态方法

```java
class Sample {  
   private int age;  
   public int getAge()  
   {  
      return age;  
   }  
   public static void main(String args[])  
   {  
       System.out.println("Age is:"+ getAge());  
   }  
}  
```

+ **Constructor in class cannot be applied to given types**
  当编译器抛出该类问题的时候，代码中可能出现了构造函数有错误的返回类型；创建类对象的时候参数和构造函数的参数不匹配。

``` java
public class Constructor1 {
    private int a;
    public void Constructor1(int a) {
        this.a = a;
    }
}

public class Constructor2 {
    private int a;
    public Constructor2() {
        this.a = 100;
    }    
}   

public class Test {
    public static void main(String args[]) {
        Constructor1 c1 = new Constructor1(3); 
        Constructor2 c2 = new Constructor2(3); // !
    }
}    
```

+ **Cannot access private property**
  当编译器抛出这类问题的时候，说明代码中出现了一个类中的私有属性/私有方法被外部类访问的情况

```java
public class Private {
    private int a;
    public Private() {
        a = 1024;
    }    
    private void modify() {
        a = a / 2;
    }    
}     

public class Other {
    public void test() {
        Private p = new Private();
        P.a = 3; // !
    }
    public static void main(String args[]) {
        Private p = new Private();
        p.modify(); // !
    }       
}        
```

#### 二、构造一个类

接下来我们来讨论一下，上文提到了很多次的 **"类"** 
打个比方，如果说我们现在需要设计一个**宠物商店**。宠物商店里一定有狗，那么我们就新建一个 `Dog` 类：

![创建新的类](http://api.oo.buaa.edu.cn/image/2023_08_31_21_47_56_9b841616573f125ff41245c7db76a6c45a583dad)



我们回忆C语言的结构体，如果小狗是一个结构体……

```c
struct Dog {
    char name[30];
    char type[30];
    int age;
    double price;
    int isBought; //0 for on sale, 1 for saled
};
```

在 Java 中，小狗是……

``` java
//Dog 类
public class Dog {
    //属性
    private String name;
    private String type;
    private int age;
    private double price;
    private boolean isBought;

    //方法
    public double getPrice() {
        return price;
    }

    public void setBought(boolean bought) {
        this.isBought = bought;
    }

    public void addAge() {
        this.age++;
    }
}
```

##### 属性

对比 C - Java ，我们发现，姓名、年龄、价格这些曾经作为结构体的**结构变量**的内容，会成为类中的**属性**。  

所有属性均是私有→**private** 的，外部完全看不到它们。我们这里可以理解为，比如说现在又有一个新的**Person**类，那这个人如果想要直接 **修改** 或 **看见** 小狗的价格是不可以的。他必须通过调用 Dog 类的 `getPrice()` 方法才能看到小狗的价格，同时调用小狗的 `setBought()` 方法来修改小狗是否已经被卖出的情况。

面向对象开发强调封装和私有保护，我们一般不允许把属性定义成 public 的。面向对象方法的基本特点是私有化保护内部数据，暴露对数据的必要操作接口，即可以提供 `setter-getter` 方法。但是需要注意，如果对于全部的属性都无脑设置配套的 get+set ，那么将成员属性设置成 private 的意义就不大了。

> 功能提示：在类中按 **alt+insert** 按键可弹出如下窗口（Mac 操作系统下为 **Command+N**，提供更便捷的添加构造方法，`setter-getter` 方法等。
>
> ![快速生成](http://api.oo.buaa.edu.cn/image/2023_08_31_21_48_09_2bdd483644f75afe67775fc060d80c65d5a7f7a1)

##### 方法

对比 C - Java ,我们发现 Java 方法类似于 C 的函数，是一段用来完成特定功能的代码片段。
和均为 **private** 的所有属性不同，部分类内方法需要暴露对类内属性的必要操作接口，因此需要其修饰符应设定成 **public** 。比如给小狗增加年龄的这个方法，他可以被一个计时器类调用，从而实现给小狗进行年龄增长的操作。

#### 三、实例化

现在我们的宠物商店有了dog的"模板"，但事实上，宠物商店应当有的是真正的小狗，而非小狗的概念，因此，我们还需要在宠物商店中创造一个真正存在的小狗，当然，为了更好的表达和这个小狗有关的事物，我们需要通过一个“名称”来指代这只小狗，这个名称，在java中，叫做引用。

对比 C 语言和 Java 语言

```c
void initial(struct Dog* dog, char name[30], char type[30], int age, double price) {
    strcpy(dog->name, name);
    strcpy(dog->type, type);
    dog->age = age;
    dog->price = price;
    dog->isBought = 0;
}

int main() {
    struct Dog dog; 
    // 初始化
    initial(&dog, "puppy", "Bernese Montain", 1, 18000);
    // 购买
    struct Person person = {"小明"};
    buy(&dog, &person);
}
```

``` java
//在Dog 类
public class Dog {
    //成员变量们
    public Dog(String name, String type, int age, double price) {
        this.name = name;
        this.type = type;
        this.age = age;
        this.price = price;
        this.isBought = false;
    }
    //方法们
}

//在MainClass 类
public class MainClass {
    public static void main(String[] args) {
        Dog dog;
        dog = new Dog("puppy","Bernese Mountain", 1, 18000);
        Person person = new Person("小明");
        person.buy(dog);
    }
}
```

我们发现对于 Java 语言，**Dog** 形如 `int`、`char` 等类型的变量类型。
于是我们就可以使用这条语句：`Dog dog;` 来声明一个 `Dog` 型的变量`dog`了。

对比 C-Java ，Java 里引用类型的**变量**类似于 C 语言中的一个结构体**指针**，如果不对其进行**初始化**或执行任何**赋值**等操作，那么这个变量就会指向一个 `null` 量，代表这是一个空引用/空指针，此时还没有任何内存空间被分配用于存储对象的信息。  

> 在Java中，数据类型分为基础数据类型和引用类型：  
> - **基础数据类型**是存储简单数据值的变量，如 `boolean`、`int`、`double`、`char`；
> - **引用类型**是指对象的引用，它们可以指向任意复杂的数据结构。如数组、类和接口。
>
> 基础数据类型的变量在声明时会直接被赋予默认初始值，而不会指向 `null`。

在**本段**代码中，如果我们不使用**构造函数** new 出来一个新的小狗，那么此时这个 dog 也是一个 null 量。
和 `initial()` 函数作用类似的 `public Dog()` 名为构造函数，它的用途是在你需要创建一个对象的时候完成一些初始化工作，即给对象的属性赋予初始值。  

我们推荐在所有主类以外的类中都显式写出形如 `public 类名 (参数列表){}` 的构造函数。

### Part 2. 任务描述

#### 作业要求

课程组在本次作业对应的git仓库中提供了一份有错误的代码，你需要对代码进行修改，使程序能够正确的完成上面的场景逻辑。完成代码的修改后，你需要将代码提交到hw1的评测窗口，并且通过所有测试点

#### 代码场景描述

该代码模拟一个孩子从水果店购买水果和吃水果的场景。

题目中涉及的水果种类有且仅有苹果（apple）和香蕉（banana）。初始时刻，孩子持有 20 元且没有任何水果，水果店有 5 个苹果和 5 个香蕉，其中每个苹果 3 元，每个香蕉 2 元。

在水果店购买水果可以增加孩子手中的对应的水果数目。对于一种水果，只有孩子的钱足够购买它，并且店内这种水果的数量大于 0 时才可以成功购买这种水果，否则购买失败。

吃水果会减少孩子手中水果数目，只有孩子手中某种水果的数目大于 0 孩子才可以食用这种水果，否则食用失败

#### 输入

本次作业的输入按照如下的格式约定：

- 在第一行输入一个 整数 $n$     ($1<n<50$)
- 接下来 n 行每行输入一个命令，格式为 `eat/buy`+ 空格 + 水果名称，代表孩子尝试食用/购买对应水果。
- 保证水果名称仅仅有 banana 和 apple。

#### 输出

输出的内容在我们所给的代码中是正确完成的，同学们不修改 `System.out` 相关的内容即可。正确实现后的代码会在每一个接受一个命令后输出执行成功或者失败。若成功则输出 `ok` ，失败则输出 `failed`。

#### 样例

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
