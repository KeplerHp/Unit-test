package com.bookstore.interceptor;

import com.bookstore.utils.messageUtils.Message;
import com.bookstore.utils.messageUtils.MessageUtil;
import com.bookstore.utils.sessionUtils.SessionUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SessionValidateInterceptorTest {
    @InjectMocks
    private SessionValidateInterceptor sessionValidateInterceptor;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    void preHandleReturnsTrueWhenSessionIsValid() throws Exception {
        try (MockedStatic<SessionUtil> sessionUtilMockedStatic = mockStatic(SessionUtil.class)) {
            sessionUtilMockedStatic.when(SessionUtil::checkAuth).thenReturn(true);

            boolean result = sessionValidateInterceptor.preHandle(request, response, new Object());

            assertTrue(result);
        }
    }

    @Test
    void preHandleReturnsFalseWhenSessionIsInvalid() throws Exception {
        try (MockedStatic<SessionUtil> sessionUtilMockedStatic = mockStatic(SessionUtil.class)) {
            sessionUtilMockedStatic.when(SessionUtil::checkAuth).thenReturn(false);

            boolean result = sessionValidateInterceptor.preHandle(request, response, new Object());

            assertFalse(result);
        }
    }

    @Test
    void preHandleSetsResponseWhenSessionIsInvalid() throws Exception {
        try (MockedStatic<SessionUtil> sessionUtilMockedStatic = mockStatic(SessionUtil.class);
             MockedStatic<MessageUtil> messageUtilMockedStatic = mockStatic(MessageUtil.class)) {
            sessionUtilMockedStatic.when(SessionUtil::checkAuth).thenReturn(false);
            Message message = new Message(1, "test_message");
            messageUtilMockedStatic.when(() -> MessageUtil.createMessage(anyInt(), anyString())).thenReturn(message);

            sessionValidateInterceptor.preHandle(request, response, new Object());

            assertEquals("application/json; charset=utf-8", response.getContentType());
            assertEquals("UTF-8", response.getCharacterEncoding());
        }
    }

    // 通过模拟一个抛出IOException的HttpServletResponse.getWriter()方法，来测试sendJsonBack()方法中的异常处理（为了一行写这么多...）
    @Test
    void preHandlePrintsErrorMessageWhenIOExceptionOccurs() throws Exception {
        try (MockedStatic<SessionUtil> sessionUtilMockedStatic = mockStatic(SessionUtil.class);
             MockedStatic<MessageUtil> messageUtilMockedStatic = mockStatic(MessageUtil.class)) {
            sessionUtilMockedStatic.when(SessionUtil::checkAuth).thenReturn(false);
            Message message = new Message(1, "test_message");
            messageUtilMockedStatic.when(() -> MessageUtil.createMessage(anyInt(), anyString())).thenReturn(message);

            // Create a custom HttpServletResponse that throws an IOException when getWriter() is called
            HttpServletResponse response = new HttpServletResponse() {
                // 下面的方法都是接口中的方法，但是这里我们只需要实现getWriter()方法，但是其他方法必须要有否则报错...
                @Override
                public void addCookie(Cookie cookie) {

                }

                @Override
                public boolean containsHeader(String s) {
                    return false;
                }

                @Override
                public String encodeURL(String s) {
                    return null;
                }

                @Override
                public String encodeRedirectURL(String s) {
                    return null;
                }

                @Override
                public String encodeUrl(String s) {
                    return null;
                }

                @Override
                public String encodeRedirectUrl(String s) {
                    return null;
                }

                @Override
                public void sendError(int i, String s) throws IOException {

                }

                @Override
                public void sendError(int i) throws IOException {

                }

                @Override
                public void sendRedirect(String s) throws IOException {

                }

                @Override
                public void setDateHeader(String s, long l) {

                }

                @Override
                public void addDateHeader(String s, long l) {

                }

                @Override
                public void setHeader(String s, String s1) {

                }

                @Override
                public void addHeader(String s, String s1) {

                }

                @Override
                public void setIntHeader(String s, int i) {

                }

                @Override
                public void addIntHeader(String s, int i) {

                }

                @Override
                public void setStatus(int i) {

                }

                @Override
                public void setStatus(int i, String s) {

                }

                @Override
                public int getStatus() {
                    return 0;
                }

                @Override
                public String getHeader(String s) {
                    return null;
                }

                @Override
                public Collection<String> getHeaders(String s) {
                    return null;
                }

                @Override
                public Collection<String> getHeaderNames() {
                    return null;
                }

                @Override
                public String getCharacterEncoding() {
                    return null;
                }

                @Override
                public String getContentType() {
                    return null;
                }

                @Override
                public ServletOutputStream getOutputStream() throws IOException {
                    return null;
                }

                @Override
                public PrintWriter getWriter() throws IOException {
                    throw new IOException("Test exception");
                }

                @Override
                public void setCharacterEncoding(String s) {

                }

                @Override
                public void setContentLength(int i) {

                }

                @Override
                public void setContentLengthLong(long l) {

                }

                @Override
                public void setContentType(String s) {

                }

                @Override
                public void setBufferSize(int i) {

                }

                @Override
                public int getBufferSize() {
                    return 0;
                }

                @Override
                public void flushBuffer() throws IOException {

                }

                @Override
                public void resetBuffer() {

                }

                @Override
                public boolean isCommitted() {
                    return false;
                }

                @Override
                public void reset() {

                }

                @Override
                public void setLocale(Locale locale) {

                }

                @Override
                public Locale getLocale() {
                    return null;
                }
            };

            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            sessionValidateInterceptor.preHandle(request, response, new Object());

            assertTrue(outContent.toString().contains("send json back error"));
        }
    }
}