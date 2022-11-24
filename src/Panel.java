import java.awt.*;
import static java.awt.Color.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.lang.Math;

class Panel extends JPanel {
    JButton EXITGame = new JButton("EXIT");
    public int x = 100;
    public int y = 0;
    private int boyx = 320;
    private int boyy = 460;
    int score = 0;
    Thread timer;

    public Panel() {
        timer = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10);
                        repaint();
                    } catch (Exception e) {

                    }
                }
            }
        });
        timer.start();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        boyx -= 25;
                        break;
                    case KeyEvent.VK_RIGHT:
                        boyx += 25;
                        break;

                }
                repaint();
            }
        });
    }
    class Listener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == EXITGame){
                System.exit(0);
            }
        }
        
    }

    @Override
    public void paintComponent(Graphics g) {
        Random rand = new Random();
        super.paintComponent(g);
        try {
            g.drawImage(ImageIO.read(new File("img\\footpath.png")), 0, 0, this.getWidth(), this.getHeight(), null);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            g.drawImage(ImageIO.read(new File("img\\boy.png")), boyx, boyy, 80, 80, null);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            g.drawImage(ImageIO.read(new File("img\\fuel.png")), x, y, 80, 80, null);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        g.setColor(black);
        g.setFont(new Font("Jokerman", Font.PLAIN, 30));
        g.drawString("SCORE : " + score, 35, 30);

        if (boyx > 650) {
            boyx = 0;
        }
        if (boyx < 0) {
            boyx = 650;
        }
        if (score <= 10) {
            y += 9;
            g.setColor(BLUE);
            g.setFont(new Font("Jokerman", Font.PLAIN, 30));
            g.drawString("SPEED : 1 ", 550, 30);

        }
        if (10 < score && score <= 20) {
            y += 12;
            g.setColor(yellow);
            g.setFont(new Font("Jokerman", Font.PLAIN, 30));
            g.drawString("SPEED : 2 ", 550, 30);
        }
        if (20 < score && score <= 30) {
            y += 15;
            g.setColor(red);
            g.setFont(new Font("Jokerman", Font.PLAIN, 30));
            g.drawString("SPEED : 3 ", 550, 30);
        }
        if (30 < score && score <= 40) {
            y += 17;
            g.setColor(green);
            g.setFont(new Font("Jokerman", Font.PLAIN, 30));
            g.drawString("SPEED : 4 ", 550, 30);
        }
        if (40 < score) {
            y += 20;
            g.setColor(orange);
            g.setFont(new Font("Jokerman", Font.PLAIN, 30));
            g.drawString("SPEED : MAX!!!!! ", 450, 30);
        }

        double con = (Math.pow(boyx - x, 2)) + (Math.pow(boyy - y, 2));
        double Collision = Math.sqrt(con);
        if (Collision <= 75) {
            y = 0;
            x = rand.nextInt(650);
            score += 1;

        }

        if (y > 580) {
            g.setColor(red);
            g.setFont(new Font("Jokerman", Font.PLAIN, 50));
            g.drawString("GAME OVER", 225, 250);
            g.setColor(black);
            g.setFont(new Font("Jokerman", Font.PLAIN, 20));
            g.drawString("YOUR SCORE : " + score, 290, 300);
            add(EXITGame);
            EXITGame.setBounds(325, 320, 100, 50);
            EXITGame.addActionListener(new Listener());
            repaint();

        }

    }
}