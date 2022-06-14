package com.kakao.cafe.global;

import com.kakao.cafe.global.util.SecureUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SecureUtilsTest {

    @Test
    public void 암호화_데이터_생성() throws Exception{

        String text = "1234";

        assertDoesNotThrow(() -> SecureUtils.encrypt(text));
    }

    @Test
    public void 암호화_데이터_일치_테스트() throws Exception{

        String text1 = new String("1234");
        String text2 = new String("1234");

        assertEquals(SecureUtils.encrypt(text1), SecureUtils.encrypt(text2));
    }

}
