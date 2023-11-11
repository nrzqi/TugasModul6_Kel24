package converter.ejb;

import java.text.DecimalFormat;
import jakarta.ejb.Stateless;
import jakarta.ejb.LocalBean;

@Stateless
@LocalBean
public class ConverterBean implements ConverterBeanLocal {
    
    private DecimalFormat twoDigits = new DecimalFormat("0.00");
    
    public String rtof(double r) {
        String result = twoDigits.format(((r*9.0/4.0+32)*100)/100.0);
        return result;
    }
    
    public String ftor(double f) {
        String result = twoDigits.format(((f-32)*4.0/9.0*100)/100.0);
        return result;
    }
}
