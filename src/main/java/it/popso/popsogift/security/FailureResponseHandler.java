//package it.popso.popsogift.security;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import it.popso.popsogift.utils.ClasseFault;
//import it.popso.popsogift.utils.CustomErrorResponse;
//import it.popso.popsogift.utils.POJOConverter;
//import org.owasp.encoder.Encode;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//
//public class FailureResponseHandler implements AuthenticationFailureHandler, AccessDeniedHandler, AuthenticationEntryPoint {
//
//
//    @Override
//    public void onAuthenticationFailure(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            AuthenticationException exception)
//            throws IOException, ServletException {
//
//        writeError(response);
//    }
//
//    @Override
//    public void handle(HttpServletRequest request,
//                       HttpServletResponse response, AccessDeniedException ade) throws IOException, ServletException {
//
//        writeError(response);
//    }
//
//
//    @Override
//    public void commence(HttpServletRequest request,
//                         HttpServletResponse response, AuthenticationException ae) throws IOException {
//
//        writeError(response);
//    }
//
//
//    private void writeError(HttpServletResponse response) throws IOException {
//        response.setStatus(HttpStatus.FORBIDDEN.value());
//        response.setHeader("Content-Type", "application/json");
//        CustomErrorResponse customErrorResponse=new CustomErrorResponse(HttpStatus.FORBIDDEN, "La matricola non è autorizzata", ClasseFault.APPLICATION_FAULT);
//        String customErrorResponseString= POJOConverter.writeValueAsString(customErrorResponse);
//        response.getOutputStream()
//                .println(Encode.forHtml(customErrorResponseString));
//    }
//
//}