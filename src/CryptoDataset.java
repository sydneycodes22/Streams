import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cryptoDataset")
@XmlAccessorType(XmlAccessType.FIELD)
public class CryptoDataset {

    @XmlElement(name = "cryptoData")
    private List<CryptoData> cryptoDatas = new ArrayList<>();

    public CryptoDataset() {}

    public List<CryptoData> getCryptoDatas() { return cryptoDatas;}

    public void setCryptoDatas(List<CryptoData> cryptoDatas) { this.cryptoDatas = cryptoDatas; }
}
