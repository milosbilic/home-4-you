package home.four.you.config;

//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfig {

//	@Autowired
//	private UserDetailsServiceImpl userDetailsService;
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//	@Bean
//	public DaoAuthenticationProvider authProvider() {
//		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//		authProvider.setUserDetailsService(userDetailsService);
//		authProvider.setPasswordEncoder(passwordEncoder());
//		return authProvider;
//	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(authProvider());
//	}
//
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/resources/**");
//	}

//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService);
//	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable();
//		http.authorizeRequests().and()
//				// .antMatchers("/users/**").hasRole("ADMIN").and()
//				// .antMatchers("/secured/**").hasAnyRole("USER", "ADMIN").and()
//				.formLogin().permitAll().defaultSuccessUrl("/").loginPage("/login").loginProcessingUrl("/app-login")
//				.usernameParameter("username").passwordParameter("password").and().logout().logoutSuccessUrl("/").and()
//				.exceptionHandling().accessDeniedPage("/accessDenied");
//	}

//	@Configuration
//	public class SecurityConfiguration {
//
//		@Bean
//		public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//			http
//					.authorizeHttpRequests((authz) -> authz
//							.anyRequest().permitAll()
//					)
//					.httpBasic(withDefaults());
//			return http.build();
//		}

//	}

}
