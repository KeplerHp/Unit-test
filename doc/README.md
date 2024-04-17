# 软件测试 作业2 单元测试

> 汪毅恒  &emsp; &emsp; 521021910451  

## 被测代码
被测代码为后端项目中的Order和OrderItem部分，包括它们的daoimpl、entity、serviceimpl等部分。

## daoimpl部分
该部分需要测试的代码为OrderDaoImpl.java和OrderItemDaoImpl.java。

### DD路径分析
对每个待测试文件中的每个待测方法，绘制对应的DD路径，节点中的数字为待测试类所在文件的代码行号。

![alt text](OrderDaoImpl_DD.drawio.png)
 
![alt text](OrderItemDaoImpl_DD.drawio.png)

### 数据流分析
对每个待测试文件中的每个待测方法，分析其中变量的数据流，节点中的数字为待测试类所在文件的代码行号
- OrderDaoImpl    
    | 待测函数           | 变量         | 路径开始节点 | 路径结束节点 | 是否定义清除 |
    | ------------------ | ------------ | ------------ | ------------ | ------------ |
    | getOrder           | userId       | 43           | 49           | 是           |
    | \                  | res          | 47           | 49           | 是           |
    | getOrderRepository | 返回值       | 33           | 33           | 是           |
    | getBookRepository  | 返回值       | 38           | 38           | 是           |
    | getOrderByOrderId  | 返回值       | 54           | 54           | 是           |
    | getAllOrder        | 返回值       | 59           | 59           | 是           |
    | getAllOrderByTime  | time_start   | 65           | 115          | 是           |
    | \                  | time_end     | 66           | 115          | 是           |
    | \                  | tmp          | 67           | 115          | 是           |
    | \                  | map          | 68           | 115          | 是           |
    | \                  | total_price  | 69           | 115          | 是           |
    | \                  | total_amount | 70           | 115          | 是           |
    | \                  | now          | 73           | 88           | 是           |
    | \                  | items        | 75           | 87           | 是           |
    | \                  | item         | 78           | 86           | 是           |
    | \                  | bookId       | 79           | 86           | 是           |
    | \                  | amount       | 80           | 86           | 是           |
    | \                  | sortedMap    | 91           | 115          | 是           |
    | \                  | res          | 95           | 115          | 是           |
    | \                  | orderStat    | 99           | 106          | 是           |
    | \                  | book         | 102          | 106          | 是           |
    | \                  | orderStat    | 111          | 115          | 是           |
    | getOrderByTime     | time_start   | 65           | 115          | 是           |
    | \                  | userId       | 120          | 175          | 是           |
    | \                  | time_end     | 124          | 175          | 是           |
    | \                  | time_end     | 125          | 175          | 是           |
    | \                  | tmp          | 126          | 175          | 是           |
    | \                  | map          | 127          | 175          | 是           |
    | \                  | total_price  | 128          | 175          | 是           |
    | \                  | total_amount | 129          | 175          | 是           |
    | \                  | now          | 132          | 147          | 是           |
    | \                  | items        | 134          | 146          | 是           |
    | \                  | item         | 137          | 145          | 是           |
    | \                  | bookId       | 138          | 145          | 是           |
    | \                  | amount       | 139          | 145          | 是           |
    | \                  | sortedMap    | 150          | 175          | 是           |
    | \                  | res          | 154          | 175          | 是           |
    | \                  | orderStat    | 158          | 165          | 是           |
    | \                  | book         | 161          | 165          | 是           |
    | \                  | orderStat    | 111          | 175          | 是           |


- OrderItemDaoImpl
    | 待测函数               | 变量   | 路径开始节点 | 路径结束节点 | 是否定义清除 |
    | ---------------------- | ------ | ------------ | ------------ | ------------ |
    | setOrderItemRepository | 返回值 | 19           | 19           | 是           |
    | setOrderRepository     | 返回值 | 24           | 24           | 是           |
    | getOrderItemById       | ans    | 29           | 31           | 是           |


## entity部分
这部分代码是定义Order和OrderItem实体类，用于映射数据库中的对应信息表，它会自动生成类的getters、setters、equals、hashCodetoString方法。我们只需要测试这些方法是否正确即可，具体见测试代码即可。这里不展示DD路径和数据流。

## serviceimpl部分
serviceimpl部分只调用了dao提供的方法接口，所以测试只需要验证是否成功调用dao提供的方法。这里不展示DD路径和数据流。