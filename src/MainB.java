import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainB extends JFrame implements ActionListener{
    private JButton st = new JButton("Start/Stop");
    private JButton new_game = new JButton("New Game");
    private JLabel top = new JLabel(new ImageIcon(System.getProperty("user.dir") + File.separator + "topbar.png"));
    private ImageIcon image = new ImageIcon(System.getProperty("user.dir") + File.separator + "car.gif");
    private JLabel finnish = new JLabel(new ImageIcon(System.getProperty("user.dir") + File.separator + "finnish."));
    private JLabel background = new JLabel(new ImageIcon(System.getProperty("user.dir") + File.separator + "lo.png"));
    JLabel win = new JLabel("",SwingConstants.CENTER);
    JLabel[] no = new JLabel[4];
    private JLabel[] car = new JLabel[4];
    private MyThreader[] myThreader = new MyThreader[4];
    int[] number = new int[4];
    private boolean flag;
    private int x=280;
    private Font font = new Font("Fria code",Font.BOLD,20);

    public MainB(){
        this.setSize(800,540);
        this.setTitle("RUN");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setVisible(true);
        this.top.setBounds(0,0,800,50);
        this.add(top);
        this.new_game.setBounds(40,12,100,25);
        this.st.setBounds(160,12,100,25);
        this.win.setBounds(680,12,80,25);
        this.win.setFont(font);
        this.top.add(new_game);
        this.top.add(st);
        this.top.add(win);
        this.background.setBounds(0,50,800,450);
        this.add(background);
        for (int i=0;i<4;i++) {
            this.no[i] = new JLabel("",SwingConstants.CENTER);
            this.no[i].setBounds(x,12,80,25);
            this.no[i].setFont(font);
            this.car[i] = new JLabel(image);
            this.top.add(no[i]);
            this.background.add(car[i]);
            this.x+=100;
        }
        this.car[0].setBounds(0,70,100,35);
        this.car[1].setBounds(0,180,100,35);
        this.car[2].setBounds(0,290,100,35);
        this.car[3].setBounds(0,400,100,35);
        this.finnish.setBounds(690,0,10,450);
        this.background.add(finnish);
        this.st.setEnabled(false);
        this.st.addActionListener(this);
        this.new_game.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == new_game){
            for (int i=0;i<4;i++) {
                this.myThreader[i] = new MyThreader(this.car[i],this,i);
                this.myThreader[i].x = 20;
                this.myThreader[i].sec = (int)(Math.random()*15+5);
                this.myThreader[i].start();
            }
            this.car[0].setBounds(20,myThreader[0].y = 70,100,35);
            this.car[1].setBounds(20,myThreader[1].y = 180,100,35);
            this.car[2].setBounds(20,myThreader[2].y = 290,100,35);
            this.car[3].setBounds(20,myThreader[3].y = 400,100,35);
            this.st.setEnabled(true);
            this.win.setText("");
        }
        if (e.getSource() == st){
            this.flag = !this.flag;
            for (int i = 0; i < 4; ++i) {
                this.myThreader[i].check = this.flag;
            }
        }
    }
    public static void main(String[] arg){
        new MainB();
    }
}
class MyThreader extends Thread{
    JLabel panel;
    int x,y,sec,j;
    boolean check = false;
    private MainB mainB;

    public MyThreader( JLabel panel1, MainB mainB,  int j){
        this.panel = panel1;
        this.mainB = mainB;
        this.j = j;
    }
    @Override
    public void run() {
        for(;this.x <= 680;){
            try {
                Thread.sleep(1);
            }
            catch (InterruptedException e) {}
            for(;check;) {
                try {
                    if (x<=680) x++;
                    this.panel.setLocation(x,y);
                    Thread.sleep(sec);

                }
                catch (InterruptedException e) {}
                if (this.x < 680) {
                    continue;
                }
                break;
            }
        }
        if (this.mainB.win.getText() == "") {
            MainB mainB1 = this.mainB;
            int f = this.j;
            MainB mainB = mainB1;
            if (mainB1.win.getText() == "") {
                int[] nub = mainB.number;
                nub[f]++;
                for (int i=0;i<4;i++) {
                    mainB.no[i].setText("" + mainB.number[i]);
                }
            }
            this.mainB.win.setText("Car. " + (this.j + 1));
        }
    }
}
