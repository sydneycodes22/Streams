import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cryptoData")
@XmlAccessorType(XmlAccessType.FIELD)
public class CryptoData {

    private boolean encrypted;
    private String text;

    public CryptoData() {}

    public CryptoData(boolean encrypted, String text) {
        this.encrypted = encrypted;
        this.text = text;
    }

    public boolean isEncrypted() { return encrypted;}

    public void setEncrypted(boolean encrypted) { this.encrypted = encrypted;}

    public String getText() { return text; }

    public void setText(String text) { this.text = text; }
}