package cn.garymb.ygomobile.core;

public class GameSize {
    private int width;
    private int height;
    private int touchX;
    private int touchY;

    public void update(GameSize size) {
        synchronized (this) {
            this.width = size.width;
            this.height = size.height;
            this.touchX = size.touchX;
            this.touchY = size.touchY;
        }
    }

    public void setTouch(int touchX, int touchY) {
        synchronized (this) {
            this.touchX = touchX;
            this.touchY = touchY;
        }
    }

    public int getWidth() {
        synchronized (this) {
            return width;
        }
    }

    public int getHeight() {
        synchronized (this) {
            return height;
        }
    }

    public int getTouchX() {
        synchronized (this) {
            return touchX;
        }
    }

    public int getTouchY() {
        synchronized (this) {
            return touchY;
        }
    }

    public GameSize() {

    }

    public GameSize(int width, int height, int touchX, int touchY) {
        this.width = width;
        this.height = height;
        this.touchX = touchX;
        this.touchY = touchY;
    }

    @Override
    public String toString() {
        return "GameSize{" +
                "width=" + width +
                ", height=" + height +
                ", touchX=" + touchX +
                ", touchY=" + touchY +
                '}';
    }
}