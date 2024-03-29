import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class FillRectFrame extends JFrame {

   private StatusBar statusBar;

   public FillRectFrame() {
      setTitle("Mazinga");
      setSize(650, 815);
      addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
            System.exit(0);
         }
      });

      statusBar = new StatusBar();
      Container contentPane = getContentPane();
      contentPane.add(new MazingerPanel(statusBar), "Center");
      contentPane.add(statusBar, "South");
   }
}

class FillRectFrame1 extends WindowAdapter {
   private FillRectFrame this0;

   FillRectFrame1(FillRectFrame var1) {
      this.this0 = var1;
   }
}