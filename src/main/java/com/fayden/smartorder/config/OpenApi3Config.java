package com.fayden.smartorder.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(info = @Info(title = "SmartOrder", description = "Follow order", version = "1.0"))
@SecurityScheme(name = "security_auth",
        type = SecuritySchemeType.OAUTH2,

        flows = @OAuthFlows(
                password = @OAuthFlow(
                        authorizationUrl = "${springdoc.oAuthFlow.authorizationUrl}",
                        tokenUrl = "${springdoc.oAuthFlow.tokenUrl}",
                        scopes = {
                                @OAuthScope(name = "openid", description = "openid"),
                                @OAuthScope(name = "read", description = "read scope"),
                                @OAuthScope(name = "write", description = "write scope"),
                        })))
@Configuration
public class OpenApi3Config {
    public final static String SECURITY_AUTH_SCHEME_NAME = "security_auth";
}
