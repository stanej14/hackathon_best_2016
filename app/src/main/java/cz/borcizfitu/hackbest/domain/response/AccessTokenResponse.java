package cz.borcizfitu.hackbest.domain.response;

/**
 * Created by krata on 6.12.16.
 */

public class AccessTokenResponse {
    String accessToken;
    String tokenType;
    String uid;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
