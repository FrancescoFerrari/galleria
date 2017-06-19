package it.uniroma3.galleria.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select username,password, enabled from users where username=?")
		.authoritiesByUsernameQuery("select username, role from user_roles where username=?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/index.html","/","/css/**","/contactform/**","/font/**","/img/**","/tweet/**", "/js/**", "/img/**","/vendor/**","/less/**","/mail/**","/signUp","/home","/","/signUp","/stanzaList","/autoreList","/mostraAutore","/mostraStanza","/mostraOpera","/operaListTotale","/visualizzaPerAnnoOpera","/visualizzaPerTitoloOpera","/visualizzaPerTitoloAutore","/visualizzaPerAnnoAutore","/visualizzaPerAnnoNascitaAutore","/visualizzaPerNomeAutore","/visualizzaPerOpereEsposte","/visualizzaPerNomeStanza","/visualizzaPerTitoloStanza","/visualizzaPerAnnoStanza")                    
		.permitAll().antMatchers("/admin","/opera","/autore","/stanza").hasRole("ADMIN")
		.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and().logout()
		.permitAll();
		http.exceptionHandling().accessDeniedPage("/403");
	}
}