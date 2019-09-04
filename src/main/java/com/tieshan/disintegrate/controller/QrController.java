package com.tieshan.disintegrate.controller;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
/**
 * @description:二维码测试控制类
 * @author: huxuanhua
 * @date: Created in 2019/8/29 9:55
 * @version: 1.0
 * @modified By:
 */
@CommonsLog
@RestController
public class QrController {
    @GetMapping("/qr")
    public void get(@RequestParam(name = "w", defaultValue = "200", required = false) int width,
                    @RequestParam(name = "h", defaultValue = "200", required = false) int height,
                    @RequestParam(name = "f", defaultValue = "png", required = false) String format,
                    @RequestParam(name = "c", defaultValue = "content") String content,
                    HttpServletResponse response) throws Exception {
        log.info("日志打印");
        ServletOutputStream out = response.getOutputStream();
        Map<EncodeHintType, Object> config = new HashMap<>();
        config.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        config.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        config.put(EncodeHintType.MARGIN, 0);
        content = "铁扇公主，芭蕉扇厉害的狠";
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, config);
        MatrixToImageWriter.writeToStream(bitMatrix, format, out);
        System.out.println("二维码生成完毕，已经输出到页面中。");
    }
}
