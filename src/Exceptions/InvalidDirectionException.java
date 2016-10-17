package Exceptions;

public class InvalidDirectionException extends Exception {
    private int direction;

    public InvalidDirectionException(String message, int direction){
        super(message);
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }
}
