package com.pogrom.ktsapp.networking.auth

import android.net.Uri
import net.openid.appauth.*
import timber.log.Timber

class AuthRepository {

    fun getAuthRequest(): AuthorizationRequest {
        val serviceConfiguration = AuthorizationServiceConfiguration(
            Uri.parse(AuthConfig.AUTH_URI),
            Uri.parse(AuthConfig.TOKEN_URI)
        )

        val redirectUri = Uri.parse(AuthConfig.CALLBACK_URL)

        return AuthorizationRequest.Builder(
            serviceConfiguration,
            AuthConfig.CLIENT_ID,
            AuthConfig.RESPONSE_TYPE,

            redirectUri
        )
            .setScope(AuthConfig.SCOPE)
            .setCodeVerifier(null)
            .build()
    }

    fun performTokenRequest(
        authService: AuthorizationService,
        tokenRequest: TokenRequest,
        onComplete: () -> Unit,
        onError: (response: TokenResponse?) -> Unit
    ) {
        authService.performTokenRequest(tokenRequest, getClientAuthentication()) { response, ex ->
            when {
                response != null -> {
                    Timber.d(response.toString())
                    val accessToken = response.accessToken.orEmpty()
                    AuthConfig.USER_ACCESS_TOKEN = accessToken
                    onComplete()
                }
                else -> onError(response)
            }
        }
    }

    private fun getClientAuthentication(): ClientAuthentication {
        return ClientSecretPost(AuthConfig.CLIENT_SECRET)
    }
}