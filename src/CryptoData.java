import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

public class CryptoData {
    public  boolean encrypted;
    public  String text;

    public CryptoData() {}

    public CryptoData(boolean encrypted, String text) {
        this.encrypted = encrypted;
        this.text = text;
    }

    @XmlElement(name = "encrypted")
    public boolean isEncrypted() {
        return encrypted;
    }

    public void setEncrypted(boolean encrypted) {
        this.encrypted = encrypted;
    }

    @XmlElement(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
