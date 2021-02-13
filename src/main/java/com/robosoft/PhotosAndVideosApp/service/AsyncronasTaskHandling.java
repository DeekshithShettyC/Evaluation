package com.robosoft.PhotosAndVideosApp.service;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Component
public class AsyncronasTaskHandling {

    @Async
    public void imageCompression100X100(String storeDirectory,String fileName) {
        try {

            //BufferedImage originalImage = ImageIO.read(new File("Photos\\"+relationShipType.toString()+"\\"+profileId+"\\"+fileName));//change path to where file is located
            BufferedImage originalImage = ImageIO.read(new File(storeDirectory+"\\"+fileName));
            int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

            BufferedImage resizeImageJpg = resizeImage(originalImage, type, 144, 144);
            ImageIO.write(resizeImageJpg, "png", new File(storeDirectory+"\\"+fileName)); //change path where you want it saved
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    @Async
    public void imageCompression200X200(String storeDirectory,String fileName) {
        try {

            //BufferedImage originalImage = ImageIO.read(new File("Photos\\"+relationShipType.toString()+"\\"+profileId+"\\"+fileName));//change path to where file is located
            BufferedImage originalImage = ImageIO.read(new File(storeDirectory+"\\"+fileName));
            int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

            BufferedImage resizeImageJpg = resizeImage(originalImage, type, 700, 220);
            ImageIO.write(resizeImageJpg, "png", new File(storeDirectory+"\\"+fileName)); //change path where you want it saved
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    @Async
    public void imageCompression300X300(String storeDirectory,String fileName) {
        try {

            //BufferedImage originalImage = ImageIO.read(new File("Photos\\"+relationShipType.toString()+"\\"+profileId+"\\"+fileName));//change path to where file is located
            BufferedImage originalImage = ImageIO.read(new File(storeDirectory+"\\"+fileName));
            int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

            BufferedImage resizeImageJpg = resizeImage(originalImage, type, 700, 220);
            ImageIO.write(resizeImageJpg, "png", new File(storeDirectory+"\\"+fileName)); //change path where you want it saved
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int type, int IMG_WIDTH, int IMG_HEIGHT) {
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();

        return resizedImage;
    }
}
