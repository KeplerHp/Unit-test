package com.bookstore.daoimpl;

import com.bookstore.entity.Book;
import com.bookstore.entity.CartItem;
import com.bookstore.entity.Order;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.CartRepository;
import com.bookstore.repository.OrderItemRepository;
import com.bookstore.repository.OrderRepository;
import com.bookstore.utils.sessionUtils.SessionUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

class CartDaoImplTest {
    @Mock
    CartRepository cartRepository;
    @Mock
    BookRepository bookRepository;
    @Mock
    OrderRepository orderRepository;
    @Mock
    OrderItemRepository orderItemRepository;
    @Mock
    SessionUtil sessionUtil;

    @InjectMocks
    CartDaoImpl cartDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        cartDao.setCartRepository(cartRepository);
        cartDao.setBookRepository(bookRepository);
        cartDao.setOrderRepository(orderRepository);
        cartDao.setOrderItemRepository(orderItemRepository);
    }

    @Test
    void setCartRepository() {
        assertSame(cartRepository, cartDao.cartRepository);
    }

    @Test
    void setBookRepository() {
        assertSame(bookRepository, cartDao.bookRepository);
    }

    @Test
    void setOrderRepository() {
        assertSame(orderRepository, cartDao.orderRepository);
    }

    @Test
    void setOrderItemRepository() {
        assertSame(orderItemRepository, cartDao.orderItemRepository);
    }

    @Test
    void getCartItems() {
        try(MockedStatic<SessionUtil> theMock= Mockito.mockStatic(SessionUtil.class)){
            System.out.println("test when userid is 1");
            theMock.when(SessionUtil::getUserId).thenReturn(1);
            cartDao.getCartItems();
            assertSame(1, SessionUtil.getUserId());
            Mockito.verify(cartRepository, times(1)).getItems(1);
        }
        try(MockedStatic<SessionUtil> theMock= Mockito.mockStatic(SessionUtil.class)){
            System.out.println("test when userid is null");
            theMock.when(SessionUtil::getUserId).thenReturn(null);
            cartDao.getCartItems();
            assertSame(null, SessionUtil.getUserId());
            Mockito.verify(cartRepository, times(0)).getItems(null);
        }
    }

    @Test
    void getRealCartItems() {
        try(MockedStatic<SessionUtil> theMock= Mockito.mockStatic(SessionUtil.class)){
            System.out.println("test when userid is 1");
            theMock.when(SessionUtil::getUserId).thenReturn(1);
            cartDao.getRealCartItems();
            assertSame(1, SessionUtil.getUserId());
            Mockito.verify(cartRepository, times(1)).getRealCartItems(1);
            assertEquals(cartDao.getRealCartItems(), cartRepository.getRealCartItems(1));
        }
        try(MockedStatic<SessionUtil> theMock= Mockito.mockStatic(SessionUtil.class)){
            System.out.println("test when userid is null");
            theMock.when(SessionUtil::getUserId).thenReturn(null);
            cartDao.getRealCartItems();
            assertSame(null, SessionUtil.getUserId());
            Mockito.verify(cartRepository, times(0)).getRealCartItems(null);
            assertNull(cartDao.getRealCartItems());
        }
    }

    @Test
    void addCartItem() {
        try(MockedStatic<SessionUtil> theMock= Mockito.mockStatic(SessionUtil.class)){
            System.out.println("test when userid is 1");
            theMock.when(SessionUtil::getUserId).thenReturn(1);
            CartItem cartItem = new CartItem();
            cartItem.setAmount(0);
            Mockito.when(cartRepository.getCartItemById(1, 1)).thenReturn(cartItem);
            cartDao.addCartItem(1, 1, true);
            int amount = 1 + cartRepository.getCartItemById(1, 1).getAmount();
            assertSame(1, SessionUtil.getUserId());
            Mockito.verify(cartRepository, times(1)).addCartItem(1, 1, 1, true);
        }
        try(MockedStatic<SessionUtil> theMock= Mockito.mockStatic(SessionUtil.class)){
            System.out.println("test when userid is null");
            theMock.when(SessionUtil::getUserId).thenReturn(null);
            cartDao.addCartItem(1, 1, true);
            assertSame(null, SessionUtil.getUserId());
            Mockito.verify(cartRepository, times(0)).addCartItem(null, null, null, null);
        }
    }

    @Test
    void setCartItem() {
        try(MockedStatic<SessionUtil> theMock= Mockito.mockStatic(SessionUtil.class)){
            System.out.println("test when userid is 1");
            theMock.when(SessionUtil::getUserId).thenReturn(1);
            cartDao.setCartItem(1, true);
            assertSame(1, SessionUtil.getUserId());
            Mockito.verify(cartRepository, times(1)).setCartItem(1, 1 ,true);
        }
        try(MockedStatic<SessionUtil> theMock= Mockito.mockStatic(SessionUtil.class)){
            System.out.println("test when userid is null");
            theMock.when(SessionUtil::getUserId).thenReturn(null);
            cartDao.setCartItem(1, true);
            assertSame(null, SessionUtil.getUserId());
            Mockito.verify(cartRepository, times(0)).setCartItem(null, null, null);
        }
    }

    @Test
    void deleteCartItem() {
        try(MockedStatic<SessionUtil> theMock= Mockito.mockStatic(SessionUtil.class)){
            System.out.println("test when userid is 1");
            theMock.when(SessionUtil::getUserId).thenReturn(1);
            cartDao.deleteCartItem(1);
            assertSame(1, SessionUtil.getUserId());
            Mockito.verify(cartRepository, times(1)).deleteCartItem(1, 1);
        }
        try(MockedStatic<SessionUtil> theMock= Mockito.mockStatic(SessionUtil.class)){
            System.out.println("test when userid is null");
            theMock.when(SessionUtil::getUserId).thenReturn(null);
            cartDao.deleteCartItem(1);
            assertSame(null, SessionUtil.getUserId());
            Mockito.verify(cartRepository, times(0)).setCartItem(null, null, null);
        }
    }

    @Test
    void submitCart() {
        try(MockedStatic<SessionUtil> theMock= Mockito.mockStatic(SessionUtil.class)){
            System.out.println("test when userid is 1");
            theMock.when(SessionUtil::getUserId).thenReturn(1);

            Book book = new Book();
            book.setBookId(1);
            book.setName("test");
            book.setInventory(100);
            book.setPrice(new BigDecimal("19.99"));

            CartItem cartItem1 = new CartItem();
            cartItem1.setUserId(1);
            cartItem1.setActive(true);
            cartItem1.setAmount(1);
            cartItem1.setBook(book);
            cartItem1.setBookId(1);
            CartItem cartItem2 = new CartItem();
            cartItem2.setUserId(1);
            cartItem2.setActive(true);
            cartItem2.setAmount(114514);
            cartItem2.setBook(book);
            cartItem2.setBookId(1);

            List<CartItem> myCart = new ArrayList<>();
            myCart.add(cartItem1);
            myCart.add(cartItem2);

            Order order = new Order();
            order.setOrderId(1);

            Mockito.when(cartRepository.getRealCartItems(1)).thenReturn(myCart);
            Mockito.when(bookRepository.getBookByBookId(1)).thenReturn(book);
            Mockito.when(orderRepository.getMaxOrder()).thenReturn(order);

            cartDao.submitCart();
            assertSame(1, SessionUtil.getUserId());
            Mockito.verify(cartRepository, times(1)).getRealCartItems(1);
            // bookRepository.getBookByBookId(myItem.getBookId())调用4次，2*2
            Mockito.verify(bookRepository, times(4)).getBookByBookId(1);
            Mockito.verify(cartRepository, times(1)).submitCart(1);
        }
    }
}