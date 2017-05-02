package springDemo.admin.applets;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class FocusBall extends Applet implements Runnable,FocusListener {
  int x,y,dx,dy,diam,sizex,sizey; 
  volatile boolean hasFocus;
  
  public void init() {
    setBackground(Color.black);
   
    x=y=0; dx=dy=5; diam=10;

    sizex=getSize().width;
    sizey=getSize().height;

    addFocusListener(this);

    (new Thread(FocusBall.this)).start();
  }

  public void focusGained(FocusEvent e) {
    hasFocus=true;
  }

  public void focusLost(FocusEvent e) {
    hasFocus=false;
  }

  public void run() {
    while (true) {
      try {
        Thread.currentThread().sleep(40);
      }
      catch (InterruptedException e) {};

      if (hasFocus) {
        x+=dx; y+=dy;
        if ((x<=0)||(x+dx+diam>=sizex)) dx=-dx;
        if ((y<=0)||(y+dy+diam>=sizey)) dy=-dy;

        repaint();
      }
    }
  }

  public void paint(Graphics g) {
    g.setColor(Color.white);
    g.fillArc(x,y,diam,diam,0,360);
  }
}