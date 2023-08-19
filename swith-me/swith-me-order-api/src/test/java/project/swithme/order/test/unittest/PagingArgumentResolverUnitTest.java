package project.swithme.order.test.unittest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.lang.reflect.Method;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import project.swithme.domain.common.StudyWithMeUser;
import project.swithme.order.common.configuration.handler.paging.PagingArgumentResolver;
import project.swithme.order.common.utils.Cursor;
import project.swithme.order.core.presentation.query.OrderPagingAPI;

@DisplayName("[UnitTest] 페이징 ArgumentResolver 테스트")
class PagingArgumentResolverUnitTest {

    private PagingArgumentResolver argumentResolver;

    @BeforeEach
    void setUp() {
        argumentResolver = new PagingArgumentResolver();
    }

    @Test
    @DisplayName("CursorPageable 어노테이션을 확인할 수 있다.")
    void paging_argument_test() throws Exception {
        Method method = OrderPagingAPI.class
            .getMethod("findMyOrders", StudyWithMeUser.class, Cursor.class);
        MethodParameter methodParameter = new MethodParameter(method, 1);

        assertTrue(argumentResolver.supportsParameter(methodParameter));
    }

    @Test
    @DisplayName("올바른 값이 추출되면 Cursor 타입 객체가 추출된다.")
    void argument_resolver_cast_test() {
        NativeWebRequest webRequestMock = mock(NativeWebRequest.class);
        when(webRequestMock.getHeader("index"))
            .thenReturn("10");
        when(webRequestMock.getHeader("limit"))
            .thenReturn("10");

        Object object = argumentResolver.resolveArgument(null, null, webRequestMock, null);

        assertTrue(object instanceof Cursor);
    }
}
