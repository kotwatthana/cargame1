import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainP extends JFrame implements ActionListener{
    private JButton st = new JButton("Start/Stop");
    private JButton new_game = new JButton("New Game");
    private JPanel panel = new JPanel();
    private ImageIcon image = new ImageIcon(System.getProperty("user.dir") + File.separator + "car.gif");
    private JLabel finnish = new JLabel(new ImageIcon(System.getProperty("user.dir") + File.separator + "finnish.png"));
    private JLabel background = new JLabel(new ImageIcon(System.getProperty("user.dir") + File.separator + "lo.png"));
    JLabel win = new JLabel("Winner");
    private JLabel[] car = new JLabel[4];
    private MyThread[] myThread = new MyThread[4];
    private int y=70;
    int[] b = new int[4];
    private boolean flag;

    public MainP(){
        setSize(800,540);
        setTitle("RUN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);

        panel.setBackground(Color.RED);
        panel.setBounds(0,0,800,50);
        panel.setLayout(null);
        add(panel);
        new_game.setBounds(10,12,100,25);
        st.setBounds(120,12,100,25);
        win.setBounds(230,12,500,25);
        win.setForeground(Color.WHITE);
        panel.add(new_game);
        panel.add(st);
        panel.add(win);
        background.setBounds(0,50,800,450);
        add(background);
        for (int i=0;i<4;i++) {
            car[i] = new JLabel(image);
            background.add(car[i]);
        }
        finnish.setBounds(690,0,10,450);
        background.add(finnish);
        st.setEnabled(false);
        st.addActionListener(this);
        new_game.addActionListener(this);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == new_game){
            for (int i=0;i<4;i++) {
                this.myThread[i] = new MyThread(this.car[i],this,i);
                this.myThread[i].x = 20;
                this.myThread[i].sec = (int)(Math.random()*20+1);
                this.myThread[i].start();
            }
            this.car[0].setBounds(20,myThread[0].y = 70,100,35);
            this.car[1].setBounds(20,myThread[1].y = 180,100,35);
            this.car[2].setBounds(20,myThread[2].y = 290,100,35);
            this.car[3].setBounds(20,myThread[3].y = 400,100,35);
            st.setEnabled(true);
            setTitle("RUN");
            System.out.println("n");
        }
        if (e.getSource() == st){
            this.flag = !this.flag;
            for (int i = 0; i < 4; ++i) {
                this.myThread[i].check = this.flag;
            }
        }

    }

    public static void main(String[] arg){
        new MainP();
    }
}
class MyThread extends Thread{
    JLabel panel;
    int x,y,sec,j;
    boolean check = false;
    private MainP mainP;


    public MyThread( JLabel panel1, MainP mainP,  int j){
        this.panel = panel1;
        this.mainP = mainP;
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
        if (this.mainP.getTitle() == "RUN") {
            MainP mainP1 = this.mainP;
            int f = this.j;
            MainP mainP = mainP1;
            if (mainP1.getTitle() == "RUN") {
                int[] b = mainP.b;
                int n = f;
                ++b[n];
                mainP.win.setText("Winner No.1 : " + mainP.b[0] + "     No2. : " + mainP.b[1] + "   No3. : " + mainP.b[2] + "   No4. : " + mainP.b[3]);
            }
            this.mainP.setTitle("RUN The Winner is No. " + (this.j + 1));
        }

    }
}
