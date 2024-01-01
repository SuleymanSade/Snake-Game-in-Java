import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Mapper {
    static BufferedImage groundImage;
    static BufferedImage snakeImage;
    static BufferedImage appleImage;
    static BufferedImage wallImage;

    static int TALE_SIZE = 64;

    static int[][] map;
    
    Mapper() throws IOException{
        groundImage = ImageIO.read(new File("images/ground.png"));
        snakeImage = ImageIO.read(new File("images/snake.png"));
        appleImage = ImageIO.read(new File("images/apple.png"));
        wallImage = ImageIO.read(new File("images/wall.png"));
    }

    public void draw(Graphics2D g2){
        int currentRow = 0;
        int currentCol = 0;
        while (currentRow < 8) {
            if (currentCol <= 15) {
                if (map[currentRow][currentCol] == 0) {
                    g2.drawImage(groundImage, currentCol*TALE_SIZE, currentRow*TALE_SIZE, TALE_SIZE, TALE_SIZE, null);
                }
                else if(map[currentRow][currentCol] == 1){
                    g2.drawImage(snakeImage, currentCol*TALE_SIZE, currentRow*TALE_SIZE, TALE_SIZE, TALE_SIZE, null);
                }
                else if(map[currentRow][currentCol] == 2){
                    g2.drawImage(appleImage, currentCol*TALE_SIZE, currentRow*TALE_SIZE, TALE_SIZE, TALE_SIZE, null);
                }
                else if(map[currentRow][currentCol] == 3){
                    g2.drawImage(wallImage, currentCol*TALE_SIZE, currentRow*TALE_SIZE, TALE_SIZE, TALE_SIZE, null);
                }
                currentCol++;
                if (currentCol ==16) {
                    currentCol = 0;
                    currentRow++;
                }
                
            }
        }
        
    }
}
