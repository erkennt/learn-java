package learn.second.domain.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	public void configure(WebSecurity web) throws Exception {
		// セキュリティ設定を無視するリクエスト設定
		// 静的リソース(images、css、javascript)に対するアクセスはセキュリティ設定を無視する
		web.ignoring().antMatchers("/resources/img/**", "/resources/Bootstrap/**", "/resources/spring/**",
				"/resources/template/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	}

	// 一般
	@Configuration
	@Order(2)
	public static class MemberConfigurerAdapter extends WebSecurityConfigurerAdapter {
		@Autowired
		CommonUsersService commonUsersService;

		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			// 認証するユーザーを設定する
			auth.userDetailsService(commonUsersService)
					// 入力値をbcryptでハッシュ化した値でパスワード認証を行う
					.passwordEncoder(new BCryptPasswordEncoder());

		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			// 認可の設定

			http.antMatcher("/common/**").authorizeRequests().antMatchers("/common/").permitAll() // indexは全ユーザーアクセス許可
					.antMatchers("/common/game/**").hasAnyRole("USER").anyRequest().authenticated() // それ以外は全て認証無しの場合アクセス不許可
					.and()

					// ログイン設定
					.formLogin().loginProcessingUrl("/common/login") // 認証処理のパス
					.loginPage("/") // ログインフォームのパス
					// .failureHandler(new AuthenticationFailureHandler()) //
					// 認証失敗時に呼ばれるハンドラクラス
					.defaultSuccessUrl("/common/game") // 認証成功時の遷移先
					.failureUrl("/?error=true").usernameParameter("username").passwordParameter("password") // ユーザー名、パスワードのパラメータ名
					.and()

					// ログアウト設定
					.logout().logoutRequestMatcher(new AntPathRequestMatcher("/common/logout**")) // ログアウト処理のパス
					.logoutSuccessUrl("/") // ログアウト完了時のパス
					.deleteCookies("JSESSIONID").and().csrf().disable();

		}

	}

	// 管理画面用設定
	@Configuration
	@Order(1)
	public static class AdminConfigurerAdapter extends WebSecurityConfigurerAdapter {

		@Autowired
		AdminUsersService adminUsersService;

		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			// 認証するユーザーを設定する

			auth.userDetailsService(adminUsersService)

			// 入力値をbcryptでハッシュ化した値でパスワード認証を行う
			 .passwordEncoder(new BCryptPasswordEncoder())
			;
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			// 認可の設定
			http.antMatcher("/admin/**").authorizeRequests().antMatchers(("/admin"), ("/admin?error=true")).permitAll() // indexは全ユーザーアクセス許可
					.antMatchers("/admin/menu/**").hasRole("ADMIN").anyRequest().authenticated() // それ以外は全て認証無しの場合アクセス不許可
					.and()
					// ログイン設定
					.formLogin().loginProcessingUrl("/admin/login") // 認証処理のパス
					.loginPage("/admin") // ログインフォームのパス
					// .failureHandler(new AuthenticationFailureHandler()) //
					// 認証失敗時に呼ばれるハンドラクラス
					.failureUrl("/admin?error=true").defaultSuccessUrl("/admin/menu") // 認証成功時の遷移先
					.usernameParameter("username").passwordParameter("password") // ユーザー名、パスワードのパラメータ名
					.and()

					// ログアウト設定
					.logout().logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout**")) // ログアウト処理のパス
					.logoutSuccessUrl("/admin") // ログアウト完了時のパス
					.deleteCookies("JSESSIONID").and().csrf().disable();

		}
	}

	/*
	 * @Configuration public static class AdminAuthenticationConfiguration
	 * extends GlobalAuthenticationConfigurerAdapter {
	 *
	 * @Autowired AdminUsersService adminnUsersService;
	 *
	 * @Override public void init(AuthenticationManagerBuilder auth) throws
	 * Exception { // 認証するユーザーを設定する
	 *
	 * auth.userDetailsService(adminnUsersService)
	 *
	 * // 入力値をbcryptでハッシュ化した値でパスワード認証を行う // .passwordEncoder(new
	 * BCryptPasswordEncoder()) ; } }
	 *
	 * @Configuration protected static class CommonAuthenticationConfiguration
	 * extends GlobalAuthenticationConfigurerAdapter {
	 *
	 * @Autowired CommonUsersService commonUsersService;
	 *
	 * @Override public void init(AuthenticationManagerBuilder auth) throws
	 * Exception { // 認証するユーザーを設定する auth.userDetailsService(commonUsersService)
	 * // 入力値をbcryptでハッシュ化した値でパスワード認証を行う // .passwordEncoder(new
	 * BCryptPasswordEncoder()) ;
	 *
	 * } }
	 *
	 */

}