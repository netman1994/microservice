package org.ywk.auth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.util.Base64;


@SpringBootTest
public class AuthApplicationTest {

    @Autowired
    PasswordEncoder passwordEncoder;


    @Test
    public void generatedPwd() {

        System.out.println(passwordEncoder.encode("ywk"));
    }


    @Test
    public void contextLoads() {

        // eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9
        // .eyJleHAiOjE3MTg5MDE2MjAsInVzZXJfbmFtZSI6InVzZXIiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiZXFucE1PbnRvbFlvMHBnOVdqOVNFY0kyeUU4IiwiY2xpZW50X2lkIjoiaHItY2xpZW50Iiwic2NvcGUiOlsiYWxsIl19
        // .w6bbKji9KVHpOgd6N6CBGPRUGqcHUAdv7wlvIKtl4XUKstwI8lGb4gPqA4i805vvHSrSC2QQZjNrTDZGOeifItxTsMPvOvEktdSs7d0mVeR6xr9rEaZt1_8AmkEXpAKal99yXLNRexKgVF-YSqAFHitzcrbHL_9XTTrLnugpvJSxAwLZcWRCvuk3byWQHYNkzokGgrtFsikK1lX8JITt_oc5Rr5J9dmHd8NPuGiH1vE0BnApnM9tlKEpiwdIXjJc89L7L0ncMWqQhTf0oAWPwS7Fx3JhjKKdKGg-9N7POdpqmTQBWUpdC8q_CcSu-dH2seHaYGULLyqWywv68vZfgA

        byte[] decoder = Base64.getDecoder().decode("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9");
        System.out.println("result1 \t" + new String(decoder));


        byte[] decoder1 = Base64.getDecoder().decode("eyJleHAiOjE3MTg5MDE2MjAsInVzZXJfbmFtZSI6InVzZXIiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiZXFucE1PbnRvbFlvMHBnOVdqOVNFY0kyeUU4IiwiY2xpZW50X2lkIjoiaHItY2xpZW50Iiwic2NvcGUiOlsiYWxsIl19");
        System.out.println("result2 \t" +new String(decoder1));


        byte[] encode = Base64.getEncoder().encode("ywk:ywk".getBytes(StandardCharsets.UTF_8));
        System.out.println("result3 \t" + new String(encode));

    }

}
