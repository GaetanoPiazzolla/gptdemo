package epam.anywhere.gptdemo;

import static org.assertj.core.api.Assertions.assertThat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import epam.anywhere.gptdemo.interceptor.AuthorizationInterceptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

class AuthorizationInterceptorTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private HandlerInterceptor interceptor = new AuthorizationInterceptor();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldAllowRequestWithValidAuthorizationHeader() throws Exception {
        // Set up the request with a valid Authorization header
        String authHeader = "Bearer some-token-here";
        org.mockito.Mockito.when(request.getHeader("Authorization")).thenReturn(authHeader);

        // Call the interceptor
        boolean result = interceptor.preHandle(request, response, new Object());

        // Verify that the interceptor allowed the request
        assertThat(result).isTrue();
    }

    @Test
    void shouldRejectRequestWithInvalidAuthorizationHeader() throws Exception {
        // Set up the request with an invalid Authorization header
        String authHeader = "InvalidToken some-token-here";
        org.mockito.Mockito.when(request.getHeader("Authorization")).thenReturn(authHeader);

        // Call the interceptor
        interceptor.preHandle(request, response, new Object());

        // Verify that the interceptor rejected the request with a 401 Unauthorized error
        org.mockito.Mockito.verify(response).sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
    }

    @Test
    void shouldRejectRequestWithoutAuthorizationHeader() throws Exception {
        // Set up the request without an Authorization header
        org.mockito.Mockito.when(request.getHeader("Authorization")).thenReturn(null);

        // Call the interceptor
        interceptor.preHandle(request, response, new Object());

        // Verify that the interceptor rejected the request with a 401 Unauthorized error
        org.mockito.Mockito.verify(response).sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
    }

}
