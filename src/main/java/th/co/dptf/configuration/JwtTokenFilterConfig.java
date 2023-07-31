package th.co.dptf.configuration;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
@Configuration
public class JwtTokenFilterConfig {
	
	@Value("${app.client.jwt.key}")
	private String secret;

	@Bean
	public FilterRegistrationBean<Filter> jwtTokenFilterRegistration() {
		FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
		registration.setFilter(jwtTokenFilter());
		registration.addUrlPatterns("/api/**");
		registration.setOrder(1);
		return registration;
	}

	@Bean
	public Filter jwtTokenFilter() {
		return new Filter() {
			@Override
			public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
					FilterChain filterChain) throws IOException, ServletException {
				HttpServletRequest request = (HttpServletRequest) servletRequest;
				HttpServletResponse response = (HttpServletResponse) servletResponse;

				String token = extractTokenFromRequest(request);
				if (token != null) {
					try {
						Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
						
						
					} catch (SignatureException e) {
						
						response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
						return;
					} catch (ExpiredJwtException e) {
						response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token has expired");
						return;
					} catch (Exception e) {
						response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unsupported token");
						return;
					}
				} else {
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing token");
					return;
				}

				filterChain.doFilter(request, response);
			}

			private String extractTokenFromRequest(HttpServletRequest request) {
				String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
				if (authorizationHeader != null && (authorizationHeader.startsWith("Bearer ")
						|| authorizationHeader.startsWith("bearer ") || authorizationHeader.startsWith("BEARER "))) {

					return authorizationHeader.substring(7);
				}
				return null;
			}
		};
	}
}
