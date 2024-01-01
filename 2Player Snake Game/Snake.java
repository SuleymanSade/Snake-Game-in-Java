import java.util.Random;

public class Snake {
    public static String direction = "right";
    public static String direction2 = "left";

    int[][] snake;
    int[][] snake2;

    static int[] oldSnakeHeadPos;
    static int[] oldSnakeTailPos;

    static int[] apple;

    public String winnerSnake;

    Random random;

    Snake(){
        

        random = new Random();
        reset();
        
    }

    public void reset(){
        Mapper.map = new int[][]{
                          {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
                          {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
                          {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
                          {3,0,1,1,1,0,0,0,0,0,0,0,0,0,0,3},
                          {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
                          {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
                          {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
                          {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3}}; 

        snake = new int[][]{{3,2},{3,3},{3,4}};
        snake2 = new int[][]{{3,11},{3,12},{3,13}};

        oldSnakeHeadPos = new int[]{3,4};
        oldSnakeTailPos = new int[]{3,2};

        apple = new int[]{3,8};
        
        for (int i = 0; i < 3; i++) {
            createNewApple();
        }

        Frame.isGameEnded = false;

        direction = "right";
        direction2 = "left";
    }

    public void createNewApple(){
        while (Mapper.map[apple[0]][apple[1]] != 0 && Mapper.map[apple[0]][apple[1]] != 4) {
            apple[0] = random.nextInt(8);
            apple[1] = random.nextInt(16);
        }
        Mapper.map[apple[0]][apple[1]] = 2;
    }

    public int[][] elementAdder(int[][] snake){
        int carrierArray[][] = new int[snake.length+1][2];

        for (int i = 0; i < snake.length-1; i++) {
            for (int j = 0; j < 2; j++) {
                carrierArray[i][j] = snake[i][j];
            }
        }

        carrierArray[carrierArray.length-2][0] = oldSnakeHeadPos[0];
        carrierArray[carrierArray.length-2][1] = oldSnakeHeadPos[1];

        carrierArray[carrierArray.length-1][0] = snake[snake.length-1][0];
        carrierArray[carrierArray.length-1][1] = snake[snake.length-1][1];

        return carrierArray;
    }
    public int[][] move(int[][] snake, String direction, int snakeMapNumber){
        oldSnakeHeadPos[0] = snake[snake.length-1][0];
        oldSnakeHeadPos[1] = snake[snake.length-1][1];

        oldSnakeTailPos[0] = snake[0][0];
        oldSnakeTailPos[1] = snake[0][1];


        if (direction == "up") {
            snake[snake.length-1][0] -= 1;
        }
        else if (direction == "down") {
            snake[snake.length-1][0] += 1;
        }
        else if (direction == "right") {
            snake[snake.length-1][1] += 1;
        }
        else if (direction == "left") {
            snake[snake.length-1][1] -= 1;
        }

        switch (Mapper.map[snake[snake.length-1][0]][snake[snake.length-1][1]]) {
            case 0:
                Mapper.map[snake[0][0]][snake[0][1]] = 0;
                for (int i = 0; i < snake.length-2; i++) {
                    snake[i][0] = snake[i+1][0];
                    snake[i][1] = snake[i+1][1];
                }
                snake[snake.length-2][0] = oldSnakeHeadPos[0];
                snake[snake.length-2][1] = oldSnakeHeadPos[1];
                break;
            case 2:
                snake = elementAdder(snake);
                createNewApple();
                break;
            case 1:
            case 3:
            case 4:
                Frame.isGameEnded = true;
                switch (snakeMapNumber) {
                    case 1:
                        winnerSnake = "blue";
                        break;
                    case 4:
                        winnerSnake = "green";
                        break;
                
                }
                break;
        }

        for (int i = 0; i < snake.length; i++) {
            Mapper.map[snake[i][0]][snake[i][1]] = snakeMapNumber;
        }

        return snake;
    }
}
