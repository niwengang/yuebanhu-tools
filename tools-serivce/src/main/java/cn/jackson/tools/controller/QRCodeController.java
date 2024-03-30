package cn.jackson.tools.controller;

import cn.jackson.tools.service.qrcode.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * QRCodeController
 *
 * @author Jackson.Ni
 * @since 2024/3/30
 */
@RestController()
public class QRCodeController {

    private final QRCodeService qrCodeService;

    @Autowired
    public QRCodeController(QRCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    @GetMapping("/qrcode")
    public String uploadPage() {
        return "qrupload";
    }

    @PostMapping("/qrcode/upload")
    public String uploadQRCode(@RequestParam("file") MultipartFile file) {
        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            return qrCodeService.readQRCode(image);
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
