import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
public class blockeo {
    private JFrame jframe=null;
    public blockeo(JFrame f){this.jframe=f;}

    public  void block(){
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(
                new Runnable()
                {
                    @Override
                    public void run() {
                        front();
                    }
                }, 500, 50 , TimeUnit.MILLISECONDS );
    }
    public void front()
    {
        jframe.setExtendedState( JFrame.MAXIMIZED_BOTH );
        jframe.toFront();
    }
}
