//package it.popso.popsogift.security;
//
//import it.popso.sso.auth.filter.SSOAuthenticationFilter;
//
//import org.springframework.boot.autoconfigure.security.SecurityProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
//import org.springframework.security.web.csrf.CsrfTokenRepository;
//
//@Configuration
//@EnableWebSecurity
//@Order(SecurityProperties.BASIC_AUTH_ORDER)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Bean
//	public SSOAuthenticationFilter ssoAuthenticationFilter() {
//		return new SSOAuthenticationFilter();
//	}
//
//	private CsrfTokenRepository getCsrfTokenRepository() {
//		CookieCsrfTokenRepository tokenRepository = new CookieCsrfTokenRepository();
//		tokenRepository.setCookieHttpOnly(false);
//		tokenRepository.setCookiePath("/");
//		return tokenRepository;
//	}
//
//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//
//		FailureResponseHandler failureResponseHandler = new FailureResponseHandler();
//
//		http
//				.csrf()
//				.csrfTokenRepository(this.getCsrfTokenRepository())
//				.and()
//				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//				.and()
//				.addFilterBefore(ssoAuthenticationFilter(), BasicAuthenticationFilter.class)
//				.authorizeRequests()
//				.antMatchers("/actuator/**").permitAll()
//				.anyRequest().authenticated()
//				.and()
//				.exceptionHandling()
//				.authenticationEntryPoint(failureResponseHandler)
//				.accessDeniedHandler(failureResponseHandler)
//				.and()
//				.headers()
//				.xssProtection()
//				.and()
//				.contentSecurityPolicy("script-src 'self'");
//	}
//
//}