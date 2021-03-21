import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Random;


public class Main {

    private static ArrayList<String> hashes;

    public static void main(String[] args) {
        hashes = new ArrayList<>();
        byte[] array = new byte[8];

        while (true) {
            new Random().nextBytes(array);
            String x = new String(array, StandardCharsets.UTF_8);

            sha256(x);
        }
    }

    public static void sha256(String x) {
        if (x == null || x.isEmpty()) {
            return;
        }

        String s = "private key";
        x = s + x;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(x.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(b & 0xFF);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            String hashString = hexString.toString();
            if (hashString.startsWith("00000")) {
                hashes.add(hashString);
                System.out.println(hashes.size() + " " + hashString);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
