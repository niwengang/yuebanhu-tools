package cn.jackson.tools.service.qrcode;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.springframework.stereotype.Service;
import java.awt.image.BufferedImage;


/**
 * QRCodeService
 *
 * @author Jackson.Ni
 * @since 2024/3/30
 */
@Service
public class QRCodeService {


    public String readQRCode(BufferedImage image){
        try {
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Result result = new MultiFormatReader().decode(bitmap);
            return "QR Code content: " + result.getText();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
