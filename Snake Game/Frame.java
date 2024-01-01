
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Frame extends JPanel implements Runnable{
    double FPS = 4;
    int TALE_SIZE = 64;
    int currentCol;
    int currentRow;

    public static Boolean isGameEnded;
    public static Boolean start;

    Mapper mapper;
    Snake snake;

    Thread gameThread;

    int[] newArray;

    JFrame frame;

    public JLabel scoreText;

    public JButton restartButton;

    Frame() throws IOException{
        newArray = new int[2];

        mapper = new Mapper();
        snake = new Snake();

        isGameEnded = false;

        scoreText = new JLabel();
        scoreText.setBounds(0, 0, Mapper.TALE_SIZE, Mapper.TALE_SIZE);
        scoreText.setFont(new Font("Arial", Font.BOLD, 58));

        restartButton = new JButton();
        restartButton.setBounds(400, 200, Mapper.TALE_SIZE*4, Mapper.TALE_SIZE);
        restartButton.setBackground(Color.GREEN);
        restartButton.setFocusable(false);
        restartButton.addActionListener(e -> snake.reset());
        restartButton.setFont(new Font("Arial", Font.BOLD, 58));
    }

    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        mapper.draw(g2);

        g2.dispose();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS; // 0.016666 sec.
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null) {
            
            
            
            if (isGameEnded) {
                scoreText.setText(Integer.toString(snake.snake.length));
                restartButton.setText("Restart");
            }
            else{ 
                repaint();
                snake.move();
            }

            
            
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
