import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.Test;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.function.Function;

/**
 * Created by wangguomin on 17-9-27.
 */
public class TestUtil {
    private PathMatcher urlMatcher = new AntPathMatcher();
    @Test
    public void testMatch(){
        System.out.println(urlMatcher.match("/persons/*","/persons/admin"));
    }
    @Test
    public void testToken(){
        System.out.println(getClaimFromToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1ZCI6IndlYiIsImV4cCI6MTUwNzA1MjA4MywiaWF0IjoxNTA2NDQ3MjgzfQ.S6NXBcl40MMIwC2xi-lrNKxvZscUtOnl2BpnPZklCHMMst93Mps9u_jsIgAIXuY67yZ98ymC21jON0785e017w", Claims::getSubject));
    }
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey("mySecret")
                .parseClaimsJws(token)
                .getBody();
    }
}
