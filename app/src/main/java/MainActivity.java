
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set full screen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //hide title
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
<<<<<<< HEAD:app/src/main/java/MainActivity.java
        this.setContentView(new GameSurface(this));
=======
        this.setContentView(new GameSurface(this.))
>>>>>>> d6e2505ebfdcf17cece0cc2a90c8f7a7b337ad41:app/src/main/java/com/isabelle/androidgame2d/MainActivity.java
    }
}
