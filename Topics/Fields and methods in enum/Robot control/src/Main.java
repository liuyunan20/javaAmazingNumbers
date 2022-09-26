class Move {
    public static void moveRobot(Robot robot, int toX, int toY) {
        int currentX = robot.getX();
        int currentY = robot.getY();
        // if the target is on the right of current position
        if(toX - currentX > 0) {
            while(!Direction.RIGHT.equals(robot.getDirection())) {
                robot.turnRight();
            }
            while(toX - currentX > 0) {
                robot.stepForward();
                currentX = robot.getX();
            }
        } else if(toX - currentX < 0) {
            while(!Direction.LEFT.equals(robot.getDirection())) {
                robot.turnLeft();
            }
            while(toX - currentX < 0) {
                robot.stepForward();
                currentX = robot.getX();
            }
        }

        if(toY - currentY > 0) {
            while(!Direction.UP.equals(robot.getDirection())) {
                robot.turnRight();
            }
            while(toY - currentY > 0) {
                robot.stepForward();
                currentY = robot.getY();
            }
        } else if(toY - currentY < 0) {
            while(!Direction.DOWN.equals(robot.getDirection())) {
                robot.turnLeft();
            }
            while(toY - currentY < 0) {
                robot.stepForward();
                currentY = robot.getY();
            }
        }
    }
}

//Don't change code below

enum Direction {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Direction turnLeft() {
        switch (this) {
            case UP:
                return LEFT;
            case DOWN:
                return RIGHT;
            case LEFT:
                return DOWN;
            case RIGHT:
                return UP;
            default:
                throw new IllegalStateException();
        }
    }

    public Direction turnRight() {
        switch (this) {
            case UP:
                return RIGHT;
            case DOWN:
                return LEFT;
            case LEFT:
                return UP;
            case RIGHT:
                return DOWN;
            default:
                throw new IllegalStateException();
        }
    }

    public int dx() {
        return dx;
    }

    public int dy() {
        return dy;
    }
}

class Robot {
    private int x;
    private int y;
    private Direction direction;

    public Robot(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void turnLeft() {
        direction = direction.turnLeft();
    }

    public void turnRight() {
        direction = direction.turnRight();
    }

    public void stepForward() {
        x += direction.dx();
        y += direction.dy();
    }

    public Direction getDirection() {
        return direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}