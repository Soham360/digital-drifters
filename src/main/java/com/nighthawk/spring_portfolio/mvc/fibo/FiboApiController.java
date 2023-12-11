package com.nighthawk.spring_portfolio.mvc.fibo;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import com.nighthawk.spring_portfolio.mvc.fibo.Fibo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;


@RestController
@RequestMapping("/api/fibo") 
public class FiboApiController { 
    String last_run = null;  

    @GetMapping("/generate")
    public byte[] getFibonacci() {
        Fibo fibo = new Fibo("random");
        BufferedImage image = fibo.generate();
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } 
        catch (IOException e) {
            return null;
        }

        
    }
}