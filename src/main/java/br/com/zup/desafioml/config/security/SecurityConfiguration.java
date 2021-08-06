package br.com.zup.desafioml.config.security;

import br.com.zup.desafioml.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override // necessário para configurar a classe authenticationManager no Spring
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override // controle de acesso por autenticação
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());

    }

    @Override // controle de autorização de acesso a URLs
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()

                .antMatchers(HttpMethod.POST, "/usuarios").permitAll() // libera POST em /usuarios
                .antMatchers(HttpMethod.POST, "/categorias").permitAll() // libera POST em /categorias
                .antMatchers(HttpMethod.POST, "/auth").permitAll() // libera POST em /auth
                .antMatchers(HttpMethod.GET, "/produtos/**").permitAll() // libera POST em /auth
                .anyRequest().authenticated() // requer autenticação nos demais endpoints
                .and().csrf().disable() // precisa ser desabilitado para acesso sem autenticação
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // inibe sessões
                .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);
                // filtra as requests antes dela ser executadas na aplicação
    }

    @Override // controle de acesso a recursos estáticos (js, css, img, etc)
    public void configure(WebSecurity web) throws Exception {}

}
