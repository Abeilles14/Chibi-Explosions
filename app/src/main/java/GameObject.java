/*
 * objects of the game is extended from this abstract class
 * Bitmap 3 columns, 4 rows
 */
import android.graphics.Bitmap;

public abstract class GameObject {
    protected Bitmap image;

    protected final int rowCount;
    protected final int colCount;

    protected final int WIDTH;                  //image width
    protected final int HEIGHT;                 //image height

    protected final int width;
    protected final int height;
    protected int x;
    protected int y;

    public GameObject(Bitmap image, int rowCount, int colCount, int x, int y){
        this.image = image;
        this.rowCount = rowCount;
        this.colCount = colCount;

        this.x = x;
        this.y = y;

        this.WIDTH = image.getWidth();          //width of whole image
        this.HEIGHT = image.getHeight();        //height of whole image

        this.width = this.WIDTH/colCount;       //width of character
        this.height = this.HEIGHT/rowCount;     //height of character
    }

    protected Bitmap createSubImageAt(int row, int col){
        //createBitmap(bitmap, x, y, width, height) --> position in image
        Bitmap subImage = Bitmap.createBitmap(image,col*width, row*height, width, height);
        return subImage;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
