# 关于第一次作业的解析与说明

> 作者：MossDream  
> 文档简介：目的在于对作业要求的**源代码修改**进行总结并展示自己的理解，若有错误敬请指正

## 源代码语法错误点

### Main类

- 第9行
    ```java
    for (i = 0; i < opCount; ++i)
    ```
  该行代码中的**i**是**未定义**的

### Seller(Store)类

- 第16行
    ```java
    child.money -= APPLE_PRICE;
    ```
  *Child*类的**money**属性是**private**的,无法直接访问，应该调用**public**方法**subMoney**

### Child类

- 第6行
    ```java
    public void Child(int money)
    ```
  这是一个构造器,没有返回类型,应该去掉**void**关键字
- 第16行
    ```java
    public static void addOneFruit(String goal)
    ```
  该方法在*Seller(Store)* 类中通过对象被调用,不应该有**static**修饰
- 第36行
    ```java
     public boolean buyFromStore(String goal, Store store)
    ```
  方法体中没有返回语句,也无需返回值,返回类型改为**void**

## 源代码风格改进点

### Main类

#### Magic Number问题

- 第5行
    ```java
    Store store = new Store(5, 5);
    ```

  向该类构造器传入**数字字面量(Magic Number)** 5,不利于快速理解代码含义,
  建议改为**appleCount, bananaCount**

- 第6行
    ```java
    Child child = new Child(20);
    ```
  **20**建议改为**initMoney**

- 第12行、第14行
    ```java
    child.eatOneFruit(instr.substring(4));
    child.buyFromStore(instr.substring(4), store);
    ```
  **4**表示对输入指令字符串的起始位置,建议改为**beginIndex**

### Seller(Store)类

#### 类名问题

源代码中类文件名是*Seller.java*,但是类名是*Store*,建议保持一致，这里将类名改为*Seller*

