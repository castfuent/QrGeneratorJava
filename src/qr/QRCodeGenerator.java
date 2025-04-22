package qr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;



import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeGenerator {
 
    public static void generateQRCode(String data, String filePath) throws Exception {
        int qr_image_width = 400;
        int qr_image_height = 400;
        String IMAGE_FORMAT = "png";

        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix matrix = writer.encode(data, BarcodeFormat.QR_CODE, qr_image_width, qr_image_height);
        BufferedImage image = new BufferedImage(qr_image_width, qr_image_height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < qr_image_height; y++) {
            for (int x = 0; x < qr_image_width; x++) {
                int grayValue = (matrix.get(x, y) ? 0 : 1) & 0xff;
                image.setRGB(x, y, (grayValue == 0 ? 0 : 0xFFFFFF));
            }
        }

        FileOutputStream qrCode = new FileOutputStream(filePath);
        ImageIO.write(image, IMAGE_FORMAT, qrCode);
        qrCode.close();
    }

    



    public static String buildVCard(String name, String org, String title, String email, String phone, String url, String address) {
        return "BEGIN:VCARD\n" +
               "VERSION:3.0\n" +
               "N:" + name + "\n" +
               "ORG:" + org + "\n" +
               "TITLE:" + title + "\n" +
               "EMAIL:" + email + "\n" +
               "TEL;CELL:" + phone + "\n" +
               "URL:" + url + "\n" +
               "ADR;WORK:;;" + address + "\n" +
               "END:VCARD";
    }
}