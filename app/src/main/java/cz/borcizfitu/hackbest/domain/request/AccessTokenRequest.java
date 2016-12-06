package cz.borcizfitu.hackbest.domain.request;

/**
 * Created by krata on 6.12.16.
 */

public class AccessTokenRequest {
    String code;
    String grantType;

    public AccessTokenRequest(String code) {
        this.code = code;
        this.grantType = "authorization_code";
    }
}
