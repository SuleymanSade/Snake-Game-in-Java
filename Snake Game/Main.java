import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JFrame;

class Main{
    public static void main(String[] args) throws IOException {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("Snake Game");
        window.setBounds(0,0,1024,512);

        Frame frame = new Frame();
        
        
        window.add(frame.scoreText);
        window.add(frame.restartButton);
        window.add(frame);


        window.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_UP){
                    if (Snake.direction != "down") {
                        Snake.direction = "up";
                    } 
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    if (Snake.direction != "up") {
                        Snake.direction = "down";
                    }
                    
                }
                if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    if (Snake.direction != "left") {
                        Snake.direction = "right";
                    }
                    
                }
                if(e.getKeyCode() == KeyEvent.VK_LEFT){
                    if (Snake.direction != "right") {
                        Snake.direction = "left";
                    }
                    
                }
            }
        });

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        window.setBounds(0,0,1024,512);
        window.setFocusable(true);

        window.requestFocusInWindow();
        
        frame.startGameThread();

    }
}