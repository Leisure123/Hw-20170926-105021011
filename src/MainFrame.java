import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends Frame{
    private Button btnL = new Button("＜－");
    private Button btnR = new Button("－＞");
    private Button btnF = new Button("Fire");
    private Button btnExit = new Button("Exit");
    private Button btnAuto = new Button("Auto move");
    private Label labScore = new Label("Score: 0");
    private int Score = 0;
    private Label lab = new Label();
    private Font f = new Font(null,Font.BOLD,14);
    private boolean check1 = true;
    private boolean checkAuto = true;
    private Timer auto;
    private Timer fly;

    private ImageIcon a = new ImageIcon("Image/arrow.PNG");
    private ImageIcon b = new ImageIcon("Image/bow.PNG");
    private ImageIcon t = new ImageIcon("Image/target.PNG");
    private JLabel arrow = new JLabel(a);
    private JLabel bow = new JLabel(b);
    private JLabel target = new JLabel(t);
    private int targetX = (int)(Math.random()*800);
    private int bowX = 380;
    private int arrowX[] = new int [1];
    private int arrowY = 480;

    public MainFrame(){
        initComp();
    }

    private void initComp(){
        this.setLayout(null);
        this.setResizable(false);
        this.setTitle("ヽ(∀ﾟ)人(ﾟ∀ﾟ)人( ﾟ∀)人(∀ﾟ)人(ﾟ∀ﾟ)人( ﾟ∀)ﾉ");
        this.setBounds(100,100,900,850);
        btnExit.setBounds(810,800,70,30);
        btnL.setBounds(270,670,100,30);
        btnF.setBounds(400,670,100,30);
        btnR.setBounds(530,670,100,30);
        btnAuto.setBounds(30,800,100,30);
        labScore.setBounds(50,670,110,35);
        lab.setBounds(0,650,900,5);
        lab.setBackground(Color.BLACK);
        labScore.setFont(f);
        this.add(btnExit);
        this.add(btnL);
        this.add(btnR);
        this.add(btnF);
        this.add(btnAuto);
        this.add(labScore);
        this.add(lab);

        arrow.setSize(50,60);
        bow.setBounds(bowX,550,150,100);
        target.setBounds(targetX,50,110,110);
        arrow.setVisible(false);
        this.add(target);
        this.add(bow);
        this.add(arrow);

        btnL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(bowX > 0){
                    bowX -= 20;
                    bow.setLocation(bowX,550);
                }
            }
        });

        btnR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(bowX < 750){
                    bowX += 20;
                    bow.setLocation(bowX,550);
                }
            }
        });

        btnF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arrowX[0] = bowX + 50;
                arrow.setLocation(arrowX[0], arrowY);
                arrow.setVisible(true);
                btnF.setEnabled(false);
                fly.start();
            }
        });

        btnAuto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkAuto){
                    auto.start();
                    checkAuto = false;
                    btnAuto.setLabel("Stop Auto");
                }else{
                    auto.stop();
                    checkAuto = true;
                    btnAuto.setLabel("Auto move");
                }
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        fly = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arrowY -= 10;
                arrow.setLocation(arrowX[0],arrowY);
                if(arrowY < 0){
                    arrowY = 480;
                    arrow.setVisible(false);
                    btnF.setEnabled(true);
                    fly.stop();
                }else if(arrowX[0] < targetX+160 && arrowX[0] > targetX-50 && arrowY < 160){
                    arrowY = 480;
                    arrow.setVisible(false);
                    btnF.setEnabled(true);
                    fly.stop();
                    Score++;
                    labScore.setText("Score: " + Score);
                    targetX = (int)(Math.random()*800);
                    target.setLocation(targetX,50);
                }
            }
        });

        auto = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(bowX < 750 && check1){
                    bowX += 20;
                    if(bowX > 750){
                        check1 = false;
                    }
                    bow.setLocation(bowX,550);
                }else{
                    bowX -= 20;
                    if(bowX == 0){
                        check1 = true;
                    }
                    bow.setLocation(bowX,550);
                }
            }
        });
    }
}
