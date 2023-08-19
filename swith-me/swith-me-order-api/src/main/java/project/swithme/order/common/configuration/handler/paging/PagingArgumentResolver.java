package project.swithme.order.common.configuration.handler.paging;

import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import project.swithme.order.common.annotation.paging.CursorPageable;
import project.swithme.order.common.utils.Cursor;
import project.swithme.order.common.utils.CursorFactory;

@Order(2)
public class PagingArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String CURSOR_POINTER = "index";
    private static final String PAGE_SIZE = "limit";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CursorPageable.class);
    }

    @Override
    public Object resolveArgument(
        MethodParameter parameter,
        ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest,
        WebDataBinderFactory binderFactory
    ) {
        return createCursor(webRequest);
    }

    private Cursor createCursor(NativeWebRequest webRequest) {
        return CursorFactory.createCursor(
            webRequest.getParameter(CURSOR_POINTER),
            webRequest.getParameter(PAGE_SIZE)
        );
    }
}
