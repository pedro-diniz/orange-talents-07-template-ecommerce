package br.com.zup.desafioml.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override // necessário para configurar a classe authenticationManager no Spring
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override // controle de acesso por autenticação
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {}

    @Override // controle de autorização de acesso a URLs
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // libera as POST requests em /usuarios
                .antMatchers(HttpMethod.POST, "/usuarios").permitAll()
                .and().csrf().disable(); // precisa ser desabilitado para acesso sem autenticação
    }

    @Override // controle de acesso a recursos estáticos (js, css, img, etc)
    public void configure(WebSecurity web) throws Exception {}

}
