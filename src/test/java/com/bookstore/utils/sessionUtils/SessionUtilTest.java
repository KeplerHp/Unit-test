package com.bookstore.utils.sessionUtils;

import net.sf.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.junit.jupiter.api.Assertions.*;

class SessionUtilTest {

    private MockHttpServletRequest request;
    private MockHttpSession session;

    @BeforeEach
    void setUp() {
        request = new MockHttpServletRequest();
        session = new MockHttpSession();
        request.setSession(session);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    void setSessionShouldSetAttributes() {
        JSONObject data = new JSONObject();
        data.put("key1", "value1");
        data.put("key2", "value2");

        SessionUtil.setSession(data);

        assertEquals("value1", session.getAttribute("key1"));
        assertEquals("value2", session.getAttribute("key2"));
    }

    @Test
    void removeSessionShouldInvalidateSession() {
        session.setAttribute("key", "value");

        assertTrue(SessionUtil.removeSession());
    }

    @Test
    void ShouldReturnFalseWhenNoSession() {
        RequestContextHolder.resetRequestAttributes();

        assertFalse(SessionUtil.removeSession());
        assertFalse(SessionUtil.checkAuth());
        assertNull(SessionUtil.getUserId());
    }

    @Test
    void checkAuthShouldReturnTrueWhenUserTypeIsPositive() {
        session.setAttribute("userType", 1);

        assertTrue(SessionUtil.checkAuth());
    }

    @Test
    void checkAuthShouldReturnFalseWhenUserTypeIsNegative() {
        session.setAttribute("userType", -1);

        assertFalse(SessionUtil.checkAuth());
    }

    @Test
    void checkAuthShouldReturnFalseWhenNoUserType() {
        assertFalse(SessionUtil.checkAuth());
    }

    @Test
    void getAuthShouldReturnAuthObject() {
        session.setAttribute("userId", 1);
        session.setAttribute("username", "test");
        session.setAttribute("userType", 1);

        JSONObject authObject = SessionUtil.getAuth();

        assertNotNull(authObject);
        assertEquals(1, authObject.getInt("userId"));
        assertEquals("test", authObject.getString("username"));
        assertEquals(1, authObject.getInt("userType"));
    }

    @Test
    void getAuthShouldReturnNullWhenNoSession() {
        RequestContextHolder.resetRequestAttributes();

        assertNull(SessionUtil.getAuth());
    }

    @Test
    void getUserIdShouldReturnUserId() {
        session.setAttribute("userId", 1);

        assertEquals(1, SessionUtil.getUserId());
    }

    @Test
    void getUserIdShouldReturnNullWhenNoUserId() {
        assertNull(SessionUtil.getUserId());
    }
}