package fightbees;
/*Time:2013-9-18 22:47
 *Function:���۷� 
 *Author: l0st541
 */
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

/////////JPanel�ϻ�ͼ/////////////////////////////
class ImagePanel extends JPanel 
{
	private Image image;
	public ImagePanel() 
	{
	    try {
	        image = ImageIO.read(new File("img\\bg.jpg"));
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawImage(image, 0, 0, this);
	}
}    

//bee  ����һ���̳� JPanel���۷�  ���  �۷��Զ������ƶ��ķ���
class beeLabel extends JLabel
{
	private Image image2;
	
	public beeLabel()
	{
		try {
            image2 = ImageIO.read(new File("img\\ybee.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
		
	}
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image2, 0, 0, this);
    }
	
}

//���ָ����Χ�� nλ����ͬ����
class randomBee
{
	String path1="img\\gbee.jpg";//green
	String path2="img\\bbee.jpg";//blue
	String path3="img\\ybee.jpg";//yellow
	String path4="img\\rbee.jpg";//red
	int num=0;
	
	private void CreateBee(int x)
	{
		
	}
	Random random=new Random();
	
	
}

@SuppressWarnings("serial")
public class FightBees extends JFrame 
{
	/**
	 * @param args
	 */
	//private JLabel gamebox; //panel �м� ��ͼƬ�� label ʵ�ֱ���Ч��
	private JLabel authorinfo;
	private JLabel imgblock;
	
	private JLabel bee;  //bee�ϻ��۷�
	private beeLabel bee2;
	private JLabel bullet;//bullet�ϻ��ӵ�
	private JLabel airplane;  //airplane�ϻ��ɻ�
	
	private JTextArea instruction;  //��Ϸ˵��
	private JButton btnplay; 
	private JButton btnstop;

	private ImagePanel imgpanel=new ImagePanel();//ʵ����һ���� ���ñ���ͼƬ�� JPanel
	
	//////////////////////////////////////////////////    
	//��������
	private void createComponents()
	{
		Border lineBorder=new LineBorder(Color.white,5);//�ڱ߿�
		
		/*���Ԫ��*/
		//����ͼƬ530*470
		/*
			ImageIcon image = new ImageIcon("img\\bg.jpg"); 
			image.setImage(image.getImage().getScaledInstance(530,470,Image.SCALE_DEFAULT)); 
			gamebox=new JLabel(image);
			gamebox.setSize(530,470);
			gamebox.setBorder(new EmptyBorder(5, 5, 5, 5));//��߾��������Ҹ�5
			gamebox.setBorder(lineBorder);
		*/
		//panel�ϻ�����ͼƬOK
		imgpanel.setBorder(new EmptyBorder(5,5,5,5));
		imgpanel.setBorder(lineBorder);
		imgpanel.setSize(530,470);
		
		//�۷�ͼƬ
		bee=new JLabel();
		bee.setIcon(new ImageIcon("img\\gbee.jpg"));
		
		//bee.setBounds(0,0,30,30);
		//bee.setIcon(new ImageIcon("img\\gbee.jpg"));
		//�ӵ�ͼƬ
		bullet=new JLabel();
		bullet.setIcon(new ImageIcon("img\\bullet.jpg"));
		bullet.setVisible(false);
		//�ɻ�ͼƬ
		airplane=new JLabel();
		airplane.setIcon(new ImageIcon("img\\plane.jpg"));
		
		/*�ұ�Ԫ��*/
		//˵��
		instruction=new JTextArea();
		instruction.setText("��Ϸ˵��:"+"\n"+"�ϡ��¡����ҡ���"+"\n"+"���Ʒ���."+"\n"+"�����۷���ʤ��");
		instruction.setEditable(false);//���ɱ༭
		//��Ϸ����
		authorinfo=new JLabel();
		authorinfo.setText("Created by vvwang");
		authorinfo.setFont(new Font("SansSerif",Font.BOLD,20));
		authorinfo.setForeground(Color.lightGray);
		authorinfo.setHorizontalAlignment(JLabel.CENTER);//���뷽ʽ
		
		//��ϷͼƬ
		imgblock=new JLabel();
		imgblock.setSize(100, 200);
		imgblock.setIcon(new ImageIcon("img\\jietu.jpg"));
		//��ʼ��ť
		btnplay=new JButton("��ʼ");
		btnplay.setSize(60, 30);
		btnplay.setToolTipText("�����ʼ��Ϸ^_^");//���������ʾ
		//ֹͣ��ť
		btnstop=new JButton("ֹͣ");
		btnstop.setSize(60, 30);
	}
	////////////////////////////////////////////////
	///����ʵ��
	private void layoutComponents()
	{
		//����
		Container con=this.getContentPane();
		
		/////////////////�����Ϸ����//////////////////////////
		//�ڲ�Ϊbee  airplane ���Ϊimgpanel
		///////////////////////////////////////////////////
	    //	airplane.setBounds(530 / 2 - 30, 470 - 30, 30, 30);
		imgpanel.add(airplane);
		imgpanel.add(bee);
		imgpanel.add(bullet);//����ӵ�
		/*panel����� ����ͼƬ�� label �����label ����ʵ�� ͼ�� ����Ч��
			JPanel gamepanel=new JPanel();
			gamepanel.add(gamebox);
			gamepanel.setSize(540,480);
		*/
		
		///////////////////�ұ���������/////////////////////////
		//    ����3��panel
		////////////////////////////////////////////////////
		JPanel btn=new JPanel(new GridLayout(3,1,5,10));//��ť
		btn.add(btnplay);
		btn.add(btnstop);
		btn.setBorder(new TitledBorder("Two Buttons"));//����c# ��ť groupbox
		//���� �� ��ť ���ϵ�һ��instruct_btn panel��
		JPanel instruct_btn =new JPanel(new GridLayout(2,1,5,10));
		instruct_btn.add(instruction);
		instruct_btn.add(btn);
		
		//textpanel�������� �ұߵ����     (ͼƬ�����֡���ť)
		JPanel textpanel=new JPanel(new BorderLayout());
		textpanel.setSize(100,480);
		textpanel.add(authorinfo,BorderLayout.NORTH);
		textpanel.add(imgblock,BorderLayout.CENTER);
		textpanel.add(instruct_btn,BorderLayout.SOUTH);
		
		//��BorderLayout�����岼��
		//con.add(gamepanel,BorderLayout.CENTER);//panel �м� ��ͼƬ�� label ʵ�ֱ���Ч��
		con.add(imgpanel,BorderLayout.CENTER);
		con.add(textpanel,BorderLayout.EAST);
		
	}
	
	////////////////////////////////////////////////////////
	////paintBees���۷�Ⱥ
	private void BaintBees()
	{
		int margin_left=10;
		int [] []beeTeam={{1,1,1,1,1,1,1,1},
				             {1,1,1,1,1,1,1,1},
		                      {1,1,1,1,1,1,1,1},};
		
		for(int i=0;i<9;i++)
		{
			if (beeTeam[0][i]==1)
			{
				
			}
		}
	}
	
	////////////////////////////////////////////////////////
	///���۷�
	public FightBees()
	{
		createComponents();
		layoutComponents();
		//���岼��
		setTitle("JAVAС��Ϸ�������۷�");
		setSize(735,480); //�����ô�Щ ʵ�ʴ�СƫС
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);//����
    	//pack();//�Զ����ƴ�С   panel �м� ��ͼƬ�� label ʵ�ֱ���Ч��ʱʹ�� 
        
        @SuppressWarnings("unused")
		KeyMove keymove=new KeyMove();
    	btnEventHanders();
    	this.setResizable(false);
	}
	////////////////////////////////////////////////////////
	///���� �������ҿ��Ʒɻ��ƶ�
	private class KeyMove implements KeyListener
	{
		/*�ɻ��ƶ���Χ  x [10,510]
		 *         y [10,395]  
		 */
	    int x=260,y=385;//Ĭ��x yλ��
	    int increment=10;
	    public KeyMove()
	    {
	        airplane.setLocation(x, y);
	        airplane.addKeyListener(this);//����¼�������
	        airplane.setFocusable(true);
	    }
	    //�������ƶ�
        private void leftmvlimit()
        {
        	if(airplane.getLocation().getX()>=15 && airplane.getLocation().getX()<=510)//<15����
        	{
        		x-=increment;
        	}
        	else {
        		x=(int) airplane.getLocation().getX();
			}
        }
        //�������ƶ�
        private void rightmvlimit()
        {
        	if(airplane.getLocation().getX()>=10 && airplane.getLocation().getX()<=485)//>485����
        	{
        		x+=increment;
        	}
        	else {
        		x=(int) airplane.getLocation().getX();
			}
        }
        //��������
        private void upmvlimit()
        {
        	if(airplane.getLocation().getY()>=20 && airplane.getLocation().getY()<=395)//<15����
        	{
        		y-=increment;
        	}
        	else {
				y=(int) airplane.getLocation().getY();
			}
        }
        //��������
        private void downmvlimit()
        {
        	if(airplane.getLocation().getY()>=10 && airplane.getLocation().getY()<=385)//>385����
        	{
        		y+=increment;
        	}
        	else {
				y=(int) airplane.getLocation().getY();
			}
        }
        //�ӵ�����
       
        int bullet_y=(int) airplane.getLocation().getY()-30;
        int bullet_x=(int) airplane.getLocation().getX();
        private void bulletshoot()
        {
        	//airplane.setLocation(x,y);
        	bullet.setBounds(bullet_x, bullet_y, 30, 30);//û�� ������  �ӵ�����
        	bullet.setLocation(bullet_x, bullet_y);//��ʼ�� �ӵ� �趨�ڷɻ����Ϸ�
        	bullet.setVisible(true);
        	//imgpanel.add(bullet);
        	//��ӷ����ӵ�����
            ClassLoader classLoader = this.getClass().getClassLoader();
        	AudioClip au = JApplet.newAudioClip(classLoader.getResource("BONG.wav")); 
			au.play();
        	Timer timer = new Timer(10, new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent arg0) {
    				// TODO Auto-generated method stub
    				if(bullet_y < 10)
    				{
    					bullet_y  = 355;
    					//AudioClip au = JApplet.newAudioClip(classLoader.getResource("BONG.wav")); 
    					//au.play();
    					//imgpanel.remove(bullet);
    				}
    				else {
    					bullet_x=(int) airplane.getLocation().getX();
    					bullet_y -= 4;
    				}
    				bullet.setLocation(bullet_x, bullet_y );  				
    			}
    		});
    		
    		timer.start();
        }
		@Override
	    public void keyPressed(KeyEvent e) {
	        // TODO Auto-generated method stub
	        switch(e.getKeyCode())  
	        {   
	            case KeyEvent.VK_LEFT:  
	            case KeyEvent.VK_A:
	            	leftmvlimit();//x-
	                break ;  
	            case KeyEvent.VK_RIGHT: 
	            case KeyEvent.VK_D:
	            	rightmvlimit();//x+
	                break ;  
	            case KeyEvent.VK_UP: 
	            case KeyEvent.VK_W:
	            	upmvlimit();
	                break;  
	            case KeyEvent.VK_DOWN:
	            case KeyEvent.VK_S:
	            	downmvlimit();
	            	break;
	            case KeyEvent.VK_SPACE:
	            	bulletshoot();
	                break;  
	        }
	       airplane.setLocation(x,y);
	      
	    }
	    @Override
	    public void keyReleased(KeyEvent e) {
	        // TODO Auto-generated method stub
	    }
	    @Override
	    public void keyTyped(KeyEvent e) {
	        // TODO Auto-generated method stub
	    }
	}
	
	////////////////////////////////////////////////////////
	///������
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub 
        JFrame.setDefaultLookAndFeelDecorated(true);
        FightBees frame=new FightBees();
        frame.setVisible(true);
        /*���ڲ���Ϊ�Ǿ�̬��  ����һ���ڲ���Ķ�������
            //OuterClass.InnerClass innerObj= outerObj.InnerClass();
           //���� 
		  //FightBees.KeyMove keymove=frame.new KeyMove();//???
         //frame.addKeyListener(keymove);
        */
	}
	
	////////////////////////////////////////////////////////
	//��ť�¼�����
	private class playgame implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	private class stopgame implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	/////////////////////////////////////////////////////////
	///��ť�¼�  ����
	private void btnEventHanders()
	{
		playgame playhandel=new playgame();
		btnplay.addActionListener(playhandel);
		stopgame stophandel=new stopgame();
		btnstop.addActionListener(stophandel);
	}
}
