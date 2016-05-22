package set_1.hex_to_base64;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class HexToBase64 {
    public static void main(String[] args) {
//        byte[] input = {0x4D, 0x61, 0x6E};
        String input = new Scanner(System.in).nextLine();
//        System.out.println(hexToBase64(input.getBytes(StandardCharsets.US_ASCII)));
        System.out.println(hexToBase64(input.getBytes()));
    }

    public static String hexToBase64(byte[] input) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
        StringBuilder builder = new StringBuilder();
        int sz = 0;
        byte result = 0;
        byte currentByte = 0;
        for (int i = 0; i < input.length; ++i) {
            currentByte = input[i];
            byte tmp = (byte)((currentByte >> 8 - (6 - sz)) & ~(-1 << 6 - sz));
            System.out.println((result << sz) + tmp);
            builder.append(alphabet.charAt((result << 6 - sz) + tmp));
            sz = (8 - (6 - sz)) % 6;
            if (sz == 0) {
                System.out.println(currentByte & ~(-1 << 6));
                builder.append(alphabet.charAt(currentByte & ~(-1 << 6)));
                result = 0;
            } else {
                result = (byte) (currentByte & ~(-1 << sz));
            }
        }
        if (sz != 0) {
            System.out.println((currentByte & ~(-1 << 8 - sz)) << 6 - sz);
            builder.append(alphabet.charAt((currentByte & ~(-1 << 8 - sz)) << 6 - sz));
            builder.append("=");
        }
        return builder.toString();
    }
}
