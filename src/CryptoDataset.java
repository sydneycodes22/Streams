import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

public class CryptoDataset {
    @XmlElement(name = "cryptoData")
    private List<CryptoData> cryptoDatas = new ArrayList<>();

    public List<CryptoData> getCryptoDatas() {
        return cryptoDatas;
    }

     public void setCryptoDatas(List<CryptoData> cryptoDatas) {
        this.cryptoDatas = cryptoDatas;
     }
    
}
