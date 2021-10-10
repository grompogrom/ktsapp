package com.pogrom.ktsapp.networking.auth

import net.openid.appauth.ResponseTypeValues

object AuthConfig {
    const val AUTH_URI = "https://unsplash.com/oauth/authorize"
    const val TOKEN_URI = "https://unsplash.com/oauth/token"
    const val RESPONSE_TYPE = ResponseTypeValues.CODE
    const val SCOPE = "public read_user write_user read_photos write_photos write_likes " +
            "write_followers read_collections write_collections"

    const val CLIENT_ID = "m5jIoFCy8IyMBEvNQEURziyGmomJN-B-Y7uu3e8KRXY"
    const val CLIENT_SECRET = "nXHroalqxUCieEw6XKwFOrprd1zadzys7eX4_tVa6jE"
    const val CALLBACK_URL = "school://kts.studio/callback"
    var USER_ACCESS_TOKEN = ""
}