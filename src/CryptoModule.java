import java.io.BufferedReader;
import java.io.BufferedWriter;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.NoSuchElementException;
import java.util.Scanner;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.JAXB;

public class CryptoModule {

    private static SecretKeySpec secretKey;
    private static byte[] key;

    public CryptoModule() {}

    public void readFromXML(String filePath) {
        try(BufferedReader input = Files.newBufferedReader(Paths.get(filePath))) {
            CryptoData cryptoData = JAXB.unmarshal(input, CryptoData.class);
            System.out.println("Successfully read from XML file.\n");
        } catch (Exception e) {
            System.out.println("Error while reading from XML: " + e.toString());
        }
    }

    public void encryptStringToFile(String filePath) {
        System.out.println("Please enter the string you would like to encrypt:");
        try(BufferedWriter output = Files.newBufferedWriter(Paths.get(filePath))) {
            Scanner scanner = new Scanner(System.in);
            CryptoDataset cryptoDataset = new CryptoDataset();

            while (scanner.hasNext()) {
                try {
                    String inputString = scanner.nextLine();
                    String encryptedString = encrypt(inputString);
                    CryptoData cryptoData = new CryptoData(true, encryptedString);
                    cryptoDataset.getCryptoDatas().add(cryptoData);
                } catch (NoSuchElementException elementException) {
                    System.out.println("Invalid input. Please try again.");
                    break;
                }
            }
            JAXB.marshal(cryptoDataset, output);
            System.out.println("Successfully wrote encrypted string to file.\n");
        } catch (Exception e) {
            System.out.println("Error while writing encrypted string to file: " + e.toString());
        }
    }

    public void decryptStringFromFile(String filePath) {
        try(BufferedReader input = Files.newBufferedReader(Paths.get(filePath))) {
            CryptoDataset cryptoDataset = JAXB.unmarshal(input, CryptoDataset.class);
            for (CryptoData cryptoData : cryptoDataset.getCryptoDatas()) {
                if (cryptoData.isEncrypted()) {
                    String decryptedString = decrypt(cryptoData.getText());
                    System.out.println("Decrypted string: " + decryptedString);
                } else {
                    System.out.println("The text is not encrypted: " + cryptoData.getText());
                }
            }
            System.out.println("Successfully read from XML file and decrypted strings.\n");
        } catch (Exception e) {
            System.out.println("Error while reading from XML: " + e.toString());
        }
    }

    public static void setKey() {
        MessageDigest sha = null;
        try {
            key = Strings.superTopSecretKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String strToEncrypt) {
        try {
            setKey();
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static String decrypt(String strToDecrypt) {
        try {
            setKey();
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
}

