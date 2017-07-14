package pl.example.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * Created by PC on 2017-07-14.
 */

@Configuration
public class OAuth2ServerConfig {


    @Configuration
    @EnableResourceServer
    protected static class ResourceServer extends ResourceServerConfigurerAdapter {


        public static final String GARDEN_CARE_TO_DO_APP = "gardenCareToDo-app";

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().antMatchers("/gardencaretodo/api").access("#oauth2.hasScope('read')");
        }

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.resourceId(GARDEN_CARE_TO_DO_APP);
        }
    }

    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients
                    .inMemory()
                    .withClient("my-app-name")
                    .resourceIds(ResourceServer.GARDEN_CARE_TO_DO_APP)
                    .authorizedGrantTypes("client_credentials")
                    .scopes("read", "write")
                    .secret("password");
        }
    }

}
