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

    public static boolean isGameEnded;
    public static boolean is2Player;
    public static boolean start;

    Mapper mapper;
    Snake snake;

    Thread gameThread;

    int[] newArray;

    JFrame frame;

    public JLabel scoreText;
    public JLabel scoreText2;
    public JLabel winnerText;

    public JButton singlePlayerButton;
    public JButton multiPlayerButton;

    Frame() throws IOException{
        newArray = new int[2];

        mapper = new Mapper();
        snake = new Snake();

        isGameEnded = true;
        is2Player = false;

        scoreText = new JLabel();
        scoreText.setBounds(0, 0, Mapper.TALE_SIZE, Mapper.TALE_SIZE);
        scoreText.setFont(new Font("Arial", Font.BOLD, 58));

        scoreText2 = new JLabel();
        scoreText2.setBounds(Mapper.TALE_SIZE*15, 0, Mapper.TALE_SIZE, Mapper.TALE_SIZE);
        scoreText2.setFont(new Font("Arial", Font.BOLD, 58));

        winnerText = new JLabel();
        winnerText.setBounds(200, 100, Mapper.TALE_SIZE*15, Mapper.TALE_SIZE);
        winnerText.setFont(new Font("Arial", Font.BOLD, 58));

        singlePlayerButton = new JButton();
        singlePlayerButton.setBounds(200, 200, Mapper.TALE_SIZE*4, Mapper.TALE_SIZE);
        singlePlayerButton.setBackground(Color.RED);
        singlePlayerButton.addActionListener(e -> is2Player=false);
        singlePlayerButton.addActionListener(e -> isGameEnded=false);
        singlePlayerButton.addActionListener(e -> snake.reset());
        singlePlayerButton.setFont(new Font("Arial", Font.BOLD, 58));
        singlePlayerButton.setVisible(false);

        multiPlayerButton = new JButton();
        multiPlayerButton.setBounds(600, 200, Mapper.TALE_SIZE*4, Mapper.TALE_SIZE);
        multiPlayerButton.setBackground(Color.YELLOW);
        multiPlayerButton.addActionListener(e -> is2Player=true);
        multiPlayerButton.addActionListener(e -> isGameEnded=false);
        multiPlayerButton.addActionListener(e -> snake.reset());
        multiPlayerButton.setFont(new Font("Arial", Font.BOLD, 58));
        multiPlayerButton.setVisible(false);
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
                if (is2Player) {
                    scoreText2.setText(Integer.toString(snake.snake2.length));
                    winnerText.setText(snake.winnerSnake+" snake won");
                }
                scoreText.setText(Integer.toString(snake.snake.length));
                singlePlayerButton.setText("1player");
                multiPlayerButton.setText("2player");

                singlePlayerButton.setVisible(true);
                multiPlayerButton.setVisible(true);
            }
            else{ 
                repaint();
                snake.snake = snake.move(snake.snake, Snake.direction, 1);
                if (is2Player) {
                    snake.snake2 = snake.move(snake.snake2, Snake.direction2, 4);
                }
                multiPlayerButton.setVisible(false);
                singlePlayerButton.setVisible(false);

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
