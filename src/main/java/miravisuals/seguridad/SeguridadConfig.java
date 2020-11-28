package miravisuals.seguridad;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class SeguridadConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	DataSource datasource;

	@Bean
	public PersistentTokenRepository ptr() {

		JdbcTokenRepositoryImpl jtri = new JdbcTokenRepositoryImpl();
		jtri.setDataSource(datasource);

		return jtri;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/webjars/**", "/css/**", "/h2-console/**", "/public/**", "/auth/**", "/files/**",
						"/imagenes/**", "/")
				.permitAll().anyRequest().authenticated().and().formLogin().loginPage("/auth/login").permitAll()
				.defaultSuccessUrl("/public/index", true).loginProcessingUrl("/auth/login-post").and().rememberMe()
				.tokenRepository(ptr()).userDetailsService(userDetailsService).and().logout().logoutUrl("/auth/logout")
				.logoutSuccessUrl("/public/index");

	}

}
