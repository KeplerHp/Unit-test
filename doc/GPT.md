在GPT辅助测试这一部分，以OrderDaoImpl.java中的getAllOrderByTime()方法为例，与GPT的对话记录如下：

首先向GPT描述需要测试的函数以及进行的一些初始化，GPT生成了第一版测试代码


>Person: 
>- 我想要对一个后端程序进行基于Junit5和Mockito的单元测试，已经做了如下初始化：
>```java
>@Mock
>OrderRepository orderRepository;
>@Mock
>BookRepository bookRepository;
>
>@InjectMocks
>OrderDaoImpl orderDao;
>
>@BeforeEach
>void init() {
>    MockitoAnnotations.initMocks(this);
>    orderDao.setOrderRepository(orderRepository);
>    orderDao.setBookRepository(bookRepository);
>}
>```
>接下来我将给你一个待测试的函数，请你给出对应的测试代码
>
>ChatGPT: 
>- 当然，很高兴能帮到你。请提供待测试的函数，我会为你提供对应的测试代码。
>
>Person: 
>```java
>public List<OrderStat> getAllOrderByTime(String t1, String t2) {
>    Timestamp time_start=new Timestamp(Long.parseLong(t1));
>    Timestamp time_end=new Timestamp(Long.parseLong(t2));
>    List<Order> tmp=orderRepository.getAllOrderByTime(time_start,time_end);  //拿到订单
>    Map<Integer,Integer> map=new HashMap<>();
>    BigDecimal total_price=new BigDecimal(0);   //统计订单的价格
>    Integer total_amount=0;
>
>    for(int i=0;i<tmp.size();i++) {     //把时间范围内的订单的订单项放到map中
>      Order now=tmp.get(i);
>      if(now.getTime().getTime()>=time_start.getTime() && now.getTime().getTime()<=time_end.getTime()) {
>        List<OrderItem> items=now.getOrderItem();
>        total_price=total_price.add(now.getPrice());
>        for(int j=0;j<items.size();j++) {
>          OrderItem item=items.get(j);
>          Integer bookId=item.getBookId();
>          Integer amount=item.getAmount();
>          if(map.containsKey(bookId)) {
>            map.put(bookId,map.get(bookId)+amount);
>          }
>          else {
>            map.put(bookId,amount);
>          }
>        }
>      }
>    }
>
>    Map<Integer,Integer> sortedMap = map.entrySet().stream()    //对map进行排序
>        .sorted(Entry.comparingByValue())
>        .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
>
>    List<OrderStat> res=new ArrayList<>();
>
>    for(Map.Entry<Integer,Integer> entry: sortedMap.entrySet()) {
>      System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
>      OrderStat orderStat=new OrderStat();
>      orderStat.setBookId(entry.getKey());
>      orderStat.setAmount(entry.getValue());
>      Book book=bookRepository.getBookByBookId(entry.getKey());
>      orderStat.setName(book.getName());
>      orderStat.setPrice(book.getPrice());
>      res.add(orderStat);
>      total_amount+=entry.getValue();
>    }
>
>    Collections.reverse(res);   //倒序排序
>
>    OrderStat orderStat=new OrderStat();
>    orderStat.setPrice(total_price);
>    orderStat.setAmount(total_amount);
>    res.add(orderStat);
>    return res;
>  }
>```
>
>ChatGPT: 
>- 好的，这是您的 `getAllOrderByTime` 函数的单元测试代码：
>
>```java
>import static org.junit.jupiter.api.Assertions.assertEquals;
>import static org.mockito.Mockito.*;
>
>import java.math.BigDecimal;
>import java.sql.Timestamp;
>import java.util.ArrayList;
>import java.util.List;
>
>import org.junit.jupiter.api.BeforeEach;
>import org.junit.jupiter.api.Test;
>
>public class OrderDaoImplTest {
>
>    private OrderDaoImpl orderDao;
>
>    private OrderRepository orderRepository;
>    private BookRepository bookRepository;
>
>    @BeforeEach
>    void init() {
>        orderRepository = mock(OrderRepository.class);
>        bookRepository = mock(BookRepository.class);
>        orderDao = new OrderDaoImpl();
>        orderDao.setOrderRepository(orderRepository);
>        orderDao.setBookRepository(bookRepository);
>    }
>
>    @Test
>    void testGetAllOrderByTime() {
>        // Mocking data
>        List<Order> orders = new ArrayList<>();
>        Order order1 = new Order();
>        order1.setTime(new Timestamp(1618970400000L)); // 2021-04-21 00:00:00
>        order1.setPrice(new BigDecimal(20));
>        OrderItem item1 = new OrderItem();
>        item1.setBookId(1);
>        item1.setAmount(2);
>        order1.addOrderItem(item1);
>        orders.add(order1);
>
>        Order order2 = new Order();
>        order2.setTime(new Timestamp(1619056800000L)); // 2021-04-22 00:00:00
>        order2.setPrice(new BigDecimal(30));
>        OrderItem item2 = new OrderItem();
>        item2.setBookId(2);
>        item2.setAmount(3);
>        order2.addOrderItem(item2);
>        orders.add(order2);
>
>        when(orderRepository.getAllOrderByTime(any(Timestamp.class), any(Timestamp.class))).thenReturn(orders);
>        when(bookRepository.getBookByBookId(1)).thenReturn(new Book(1, "Book1", new BigDecimal(10)));
>        when(bookRepository.getBookByBookId(2)).thenReturn(new Book(2, "Book2", new BigDecimal(15)));
>
>        // Invoke method under test
>        List<OrderStat> result = orderDao.getAllOrderByTime("1618970400000", "1619056800000");
>
>        // Assertions
>        assertEquals(2, result.size()); // Asserting the size of the result list
>        OrderStat orderStat1 = result.get(0);
>        assertEquals(1, orderStat1.getBookId());
>        assertEquals("Book1", orderStat1.getName());
>        assertEquals(2, orderStat1.getAmount());
>        assertEquals(new BigDecimal(10), orderStat1.getPrice());
>
>        OrderStat orderStat2 = result.get(1);
>        assertEquals(2, orderStat2.getBookId());
>        assertEquals("Book2", orderStat2.getName());
>        assertEquals(3, orderStat2.getAmount());
>        assertEquals(new BigDecimal(15), orderStat2.getPrice());
>    }
>}
>```
>
>
>这个测试用例模拟了订单的数据和订单项的数据，并且模拟了 `orderRepository` 和 `bookRepository` 的行为，以返回模拟的数据。然后它调用了 `getAllOrderByTime` 方法，断言了返回的 `OrderStat` 列表的内容是否符合预期。

初版测试代码已经较为完善，但还无法直接运行。第一个问题是代码中对bookRepository.getBookByBookId(int)打桩时，返回的是一个在构造时传入参数的Book对象，而这种构造方法在原项目中并未实现，因此需要将这部分修改为先创建空的Book对象，再设置其具体参数。第二个问题是Order.setOrderItem()方法接受的参数类型为List\<OrderItem\>，而非代码中的OrderItem。因此将这个问题告诉GPT，它给出了修改后的测试代码

>Person: 
>- 你的代码有个问题，就是Order对象的方法setOrderItem()接受的参数类型为List\<OrderItem\>，请你做出修改
>
>ChatGPT: 
>- 好的，感谢指出。我会作出相应的修改，更新测试代码如下：
>
>```java
>import static org.junit.jupiter.api.Assertions.assertEquals;
>import static org.mockito.Mockito.*;
>
>import java.math.BigDecimal;
>import java.sql.Timestamp;
>import java.util.ArrayList;
>import java.util.List;
>
>import org.junit.jupiter.api.BeforeEach;
>import org.junit.jupiter.api.Test;
>
>public class OrderDaoImplTest {
>
>    private OrderDaoImpl orderDao;
>
>    private OrderRepository orderRepository;
>    private BookRepository bookRepository;
>
>    @BeforeEach
>    void init() {
>        orderRepository = mock(OrderRepository.class);
>        bookRepository = mock(BookRepository.class);
>        orderDao = new OrderDaoImpl();
>        orderDao.setOrderRepository(orderRepository);
>        orderDao.setBookRepository(bookRepository);
>    }
>
>    @Test
>    void testGetAllOrderByTime() {
>        // Mocking data
>        List<Order> orders = new ArrayList<>();
>        Order order1 = new Order();
>        order1.setTime(new Timestamp(1618970400000L)); // 2021-04-21 00:00:00
>        order1.setPrice(new BigDecimal(20));
>        List<OrderItem> items1 = new ArrayList<>();
>        OrderItem item1 = new OrderItem();
>        item1.setBookId(1);
>        item1.setAmount(2);
>        items1.add(item1);
>        order1.setOrderItems(items1);
>        orders.add(order1);
>
>        Order order2 = new Order();
>        order2.setTime(new Timestamp(1619056800000L)); // 2021-04-22 00:00:00
>        order2.setPrice(new BigDecimal(30));
>        List<OrderItem> items2 = new ArrayList<>();
>        OrderItem item2 = new OrderItem();
>        item2.setBookId(2);
>        item2.setAmount(3);
>        items2.add(item2);
>        order2.setOrderItems(items2);
>        orders.add(order2);
>
>        when(orderRepository.getAllOrderByTime(any(Timestamp.class), any(Timestamp.class))).thenReturn(orders);
>        when(bookRepository.getBookByBookId(1)).thenReturn(new Book(1, "Book1", new BigDecimal(10)));
>        when(bookRepository.getBookByBookId(2)).thenReturn(new Book(2, "Book2", new BigDecimal(15)));
>
>        // Invoke method under test
>        List<OrderStat> result = orderDao.getAllOrderByTime("1618970400000", "1619056800000");
>
>        // Assertions
>        assertEquals(2, result.size()); // Asserting the size of the result list
>        OrderStat orderStat1 = result.get(0);
>        assertEquals(1, orderStat1.getBookId());
>        assertEquals("Book1", orderStat1.getName());
>        assertEquals(2, orderStat1.getAmount());
>        assertEquals(new BigDecimal(10), orderStat1.getPrice());
>
>        OrderStat orderStat2 = result.get(1);
>        assertEquals(2, orderStat2.getBookId());
>        assertEquals("Book2", orderStat2.getName());
>        assertEquals(3, orderStat2.getAmount());
>        assertEquals(new BigDecimal(15), orderStat2.getPrice());
>    }
>}
>```
>
>
>
>我已经更新了订单对象的设置订单项的方法，现在使用了 `setOrderItems` 方法，并且对应的测试代码也做了相应修改。

再次运行修改后的测试代码，成功完成了运行，但是发现测试并未通过。原因为对待测函数返回值中元素个数的断言失败，待测函数最终返回的List\<OrderStat\>实际包含了各个OrderItem以及总和的数量与价格，因此其中的元素个数应为n+1，在本例中即为2+1=3。将此处断言进行修改，再次运行测试代码，发现成功通过了测试，但是有一个条件分支里的语句没有被覆盖。这个条件分支在检查到重复的bookId时才会跳转，因此将该信息告知GPT，它做出相应修改以满足该条件

>Person: 
>- 测试的结果显示下面这个分支里的语句没有运行到：
>```java
>if(map.containsKey(bookId)) {
>       map.put(bookId,map.get(bookId)+amount);
> }
>```
>看起来是需要Order中包含重复的OrderItem，请你做出修改
>
>ChatGPT: 
>- 明白了，您想修改测试代码以确保覆盖到订单中包含重复订单项的情况。这里是相应的修改：
>
>```java
>import static org.junit.jupiter.api.Assertions.assertEquals;
>import static org.mockito.Mockito.*;
>
>import java.math.BigDecimal;
>import java.sql.Timestamp;
>import java.util.ArrayList;
>import java.util.List;
>
>import org.junit.jupiter.api.BeforeEach;
>import org.junit.jupiter.api.Test;
>
>public class OrderDaoImplTest {
>
>    private OrderDaoImpl orderDao;
>
>    private OrderRepository orderRepository;
>    private BookRepository bookRepository;
>
>    @BeforeEach
>    void init() {
>        orderRepository = mock(OrderRepository.class);
>        bookRepository = mock(BookRepository.class);
>        orderDao = new OrderDaoImpl();
>        orderDao.setOrderRepository(orderRepository);
>        orderDao.setBookRepository(bookRepository);
>    }
>
>    @Test
>    void testGetAllOrderByTime() {
>        // Mocking data
>        List<Order> orders = new ArrayList<>();
>        Order order1 = new Order();
>        order1.setTime(new Timestamp(1618970400000L)); // 2021-04-21 00:00:00
>        order1.setPrice(new BigDecimal(20));
>        List<OrderItem> items1 = new ArrayList<>();
>        OrderItem item1 = new OrderItem();
>        item1.setBookId(1);
>        item1.setAmount(2);
>        items1.add(item1);
>        // Adding the same item again to simulate duplicate order items
>        items1.add(item1);
>        order1.setOrderItems(items1);
>        orders.add(order1);
>
>        Order order2 = new Order();
>        order2.setTime(new Timestamp(1619056800000L)); // 2021-04-22 00:00:00
>        order2.setPrice(new BigDecimal(30));
>        List<OrderItem> items2 = new ArrayList<>();
>        OrderItem item2 = new OrderItem();
>        item2.setBookId(2);
>        item2.setAmount(3);
>        items2.add(item2);
>        order2.setOrderItems(items2);
>        orders.add(order2);
>
>        when(orderRepository.getAllOrderByTime(any(Timestamp.class), any(Timestamp.class))).thenReturn(orders);
>        when(bookRepository.getBookByBookId(1)).thenReturn(new Book(1, "Book1", new BigDecimal(10)));
>        when(bookRepository.getBookByBookId(2)).thenReturn(new Book(2, "Book2", new BigDecimal(15)));
>
>        // Invoke method under test
>        List<OrderStat> result = orderDao.getAllOrderByTime("1618970400000", "1619056800000");
>
>        // Assertions
>        assertEquals(3, result.size()); // Asserting the size of the result list
>        OrderStat orderStat1 = result.get(0);
>        assertEquals(1, orderStat1.getBookId());
>        assertEquals("Book1", orderStat1.getName());
>        assertEquals(4, orderStat1.getAmount()); // Here, we expect 4 because we added the same order item twice
>        assertEquals(new BigDecimal(10), orderStat1.getPrice());
>
>        OrderStat orderStat2 = result.get(1);
>        assertEquals(2, orderStat2.getBookId());
>        assertEquals("Book2", orderStat2.getName());
>        assertEquals(3, orderStat2.getAmount());
>        assertEquals(new BigDecimal(15), orderStat2.getPrice());
>    }
>}
>```
>
>
>我已经修改了测试数据以模拟订单中包含重复订单项的情况，并且调整了断言以确保测试覆盖了这种情况。

最终的测试代码成功通过了测试，并且达到了100%的行覆盖。
