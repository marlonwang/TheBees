package fightbees;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/////////JPanel�ϻ�����ͼ/////////////////////////
@SuppressWarnings("serial")
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

////////////////////////////////
///��ʱ��_thread()ʵ��
class PrintTime implements Runnable
{
	private JLabel t_lable;
	private boolean t_b;
	
	public PrintTime(JLabel jb, boolean b)
	{
		t_lable = jb;
		t_b = b; 
	}
	@Override
	public void run()
	{
		int i,j;
		i=j=0;
		try {
		  String prestr = t_lable.getText();//��ǰlabel�ϵ�����
		  while(t_b)
		  {
			  Thread.sleep(1000);
			  i++;
			  if(i>59 && j<60)
				{
					i=0;
					j++;
				}
				if(j<10)
				{
					if(i<10)
				      t_lable.setText(prestr +"0"+ j +":0"+ i);
					else
					  t_lable.setText(prestr +"0"+ j +":"+ i);
				}
				else
				{
					if(i<10)
					  t_lable.setText(prestr + j +":0"+ i);
					else
					  t_lable.setText(prestr + j +":"+ i);
				}
		  }
		 
		}
		catch (Exception e) {
			// TODO: handle exception
			t_lable.setText("��ʱ������+_+");
		}	
	}
}

@SuppressWarnings("serial")
public class FightBees extends JFrame 
{
	/**
	 * @���岼��
	 */
	private boolean is_play=true;//��Ϸ��ʼ�ж�
    
	int count_beenum=0; //�۷�ֻ��
	//����
	private	Container con=this.getContentPane();
	private JLabel authorinfo;//author
	private JLabel grades;//����
	private JLabel beenum;//�۷���
	private JLabel marktime;//��ʱ��
	
	private JLabel bullet;//bullet�ϻ��ӵ�
	private JLabel airplane;  //airplane�ϻ��ɻ�
	
	private JTextArea instruction;  //��Ϸ˵��
	private JButton btnplay; 
	private JButton btnstop;
	private JButton btnexit;

	private ImagePanel imgpanel=new ImagePanel();//ʵ����һ���� ���ñ���ͼƬ�� JPanel
	
	//////////////////////////////////////////////////    
	//��������
	private void createComponents()
	{
		Border lineBorder=new LineBorder(Color.white,5);//�ڱ߿�
		
		/*���Ԫ��*/
		//����ͼƬ400*470
		
		//panel�ϻ�����ͼƬOK
		imgpanel.setBorder(new EmptyBorder(5,5,5,5));
		imgpanel.setBorder(lineBorder);
		imgpanel.setSize(400,470);
		
		//�۷�ͼƬ
		
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
		instruction.setText(" ��Ϸ˵��:"+"\n"+" �ϡ��¡����ҡ���"+"\n"+" ���Ʒ���."+"\n"+" �����۷���ʤ��");
		instruction.setEditable(false);//���ɱ༭
		//��Ϸ����
		authorinfo=new JLabel();
		authorinfo.setText(" By marlonwang ");
		authorinfo.setFont(new Font("SansSerif",Font.BOLD,20));
		authorinfo.setForeground(Color.lightGray);
		authorinfo.setHorizontalAlignment(JLabel.CENTER);//���뷽ʽ	
		//��Ϸ�Ʒ�
		beenum=new JLabel(" ��ǰ�۷���: 0");
		beenum.setSize(80,30);
		grades=new JLabel(" ��ǰ����: 0");
		grades.setSize(80,30);
		marktime=new JLabel(" ����ʱ��: ");
		//��ʼ��ť
		btnplay=new JButton("��ʼ");
		btnplay.setSize(60, 30);
		btnplay.setToolTipText("�����ʼ��Ϸ^_^");//���������ʾ
		//ֹͣ��ť
		btnstop=new JButton("ֹͣ");
		btnstop.setSize(60, 30);
		//�˳�
		btnexit=new JButton("�˳�");
		btnstop.setSize(60, 30);
	}
	////////////////////////////////////////////////
	///����ʵ��
	private void layoutComponents()
	{
		/////////////////�����Ϸ����//////////////////////////
		//�ڲ�Ϊbee (btnplay �����)  airplane ���Ϊimgpanel
		///////////////////////////////////////////////////
	   
		imgpanel.add(airplane);
		imgpanel.add(bullet);//����ӵ�
		
		///////////////////�ұ���������/////////////////////////
		//    ����3��panel
		////////////////////////////////////////////////////
		JPanel btn=new JPanel(new GridLayout(3,1,5,10));//��ť
		btn.add(btnplay);
		btn.add(btnstop);
		btn.add(btnexit);
		
		btn.setBorder(new TitledBorder("Two Buttons"));//����c# ��ť groupbox
		//���� �� ��ť ���ϵ�һ��instruct_btn panel��
		JPanel instruct_btn =new JPanel(new GridLayout(2,1,5,10));
		instruct_btn.add(instruction);
		instruct_btn.add(btn);
		
		//grades+beenum+marktime
		JPanel grade_beenum=new JPanel(new GridLayout(3,1));
		grade_beenum.add(beenum);
		grade_beenum.add(marktime);
		grade_beenum.add(grades);
		
		//textpanel�������� �ұߵ����     (ͼƬ�����֡���ť)
		JPanel textpanel=new JPanel(new BorderLayout());
		textpanel.setSize(80,480);
		textpanel.add(authorinfo,BorderLayout.NORTH);
		textpanel.add(grade_beenum,BorderLayout.CENTER);
		textpanel.add(instruct_btn,BorderLayout.SOUTH);
		
		//��BorderLayout�����岼��
		con.add(imgpanel,BorderLayout.CENTER);
		con.add(textpanel,BorderLayout.EAST);
		
	}
	
	////////////////////////////////////////////////////////
	////paintBees���۷�Ⱥ
	private void paintBees()
	{
		int x;
		String path1="img\\gbee.jpg";//green
		String path2="img\\bbee.jpg";//blue
		String path3="img\\ybee.jpg";//yellow
		String path4="img\\rbee.jpg";//red
		
		JLabel blab=new JLabel();
	  	//���ָ����Χ�� nλ����ͬ����
		x = (int) (1+Math.random()*4);
		switch(x)
		{
		case 1:
			blab.setIcon(new ImageIcon(path1));
			break;
		case 2:
			blab.setIcon(new ImageIcon(path2));
			break;
		case 3:
			blab.setIcon(new ImageIcon(path3));
			break;
		case 4:
			blab.setIcon(new ImageIcon(path4));
			break;
		default:
			blab.setIcon(new ImageIcon(path1));
			break;
		}
		blab.setBounds(getX(), getY(), 30, 30);
		blab.setBorder(new EmptyBorder(10, 10, 10, 10));
		imgpanel.add(blab);  
	}
	
	////////////////////////////////////////////////////////
	///���� �������ҿ��Ʒɻ��ƶ�
	private class KeyMove implements KeyListener,Runnable
	{
		
		/*�ɻ��ƶ���Χ  x [10,510]
		 *         y [10,395]  
		 */
	    private int x,y,i;//Ĭ��x yλ��185, 385,  i 10;

	    public KeyMove(int position_x,int position_y,int position_increment)
	    {
	    	x = position_x;
	    	y = position_y;
	    	i = position_increment;
	    }
	    public void run()
	    {
	    	airplane.setLocation(x, y);
	        airplane.addKeyListener(this);//����¼�������
	        airplane.setFocusable(true);
	    }
	    //�������ƶ�
        private void leftmvlimit()
        {
        	if(airplane.getLocation().getX()>=20 && airplane.getLocation().getX()<=380)//<15����
        	{
        		x-=i;
        	}
        	else {
        		x=(int) airplane.getLocation().getX();
			}
        }
        //�������ƶ�
        private void rightmvlimit()
        {
        	if(airplane.getLocation().getX()>=10 && airplane.getLocation().getX()<=350)//>355����
        	{
        		x+=i;
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
        		y-=i;
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
        		y+=i;
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
        	
        	bullet.setBounds(bullet_x, bullet_y, 30, 30);//û�� ������  �ӵ�����
        	bullet.setLocation(bullet_x, bullet_y);//��ʼ�� �ӵ� �趨�ڷɻ����Ϸ�
        	bullet.setVisible(true);
        	//imgpanel.add(bullet);
        	//��ӷ����ӵ�����
//          ClassLoader classLoader = this.getClass().getClassLoader();
//        	AudioClip au = JApplet.newAudioClip(classLoader.getResource("BONG.wav")); 
//			au.play();
        	Timer timer = new Timer(10, new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent arg0) {
    				// TODO �ӵ��Զ�����
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
	        // TODO ���������ƶ�
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
	        // TODO �����ͷ�
	    }
	    @Override
	    public void keyTyped(KeyEvent e) {
	        // TODO Auto-generated method stub
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
		setSize(570,480); //�����ô�Щ ʵ�ʴ�СƫС
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);//����
		
		//Ĭ��x yλ��185, 385,  i 10;
		Runnable planeMove=new KeyMove(185, 385, 10);
		Thread moveThread=new Thread(planeMove);
		moveThread.start();
		//this.setResizable(false);//�̶���С
		
		btnEventHanders();
	}
		
	////////////////////////////////////////////////////////
	///������
	public static void main(String[] args) 
	{
		// TODO ������ 
		//��ʾЧ����ͬ
        //JFrame.setDefaultLookAndFeelDecorated(true);
        FightBees frame=new FightBees();
        frame.setVisible(true);   
	}
	
	////////////////////////////////////////////////////////
	//��ť�¼�����
	
	//��ʼ
	private class playgame implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			// TODO ��ʼ��ť ���� �����۷�
			int rdnum=(int)(1+Math.random()*3);//���1~3���۷�
			for(int i=0;i<rdnum;i++)
			{
			   paintBees();
			   count_beenum++;
			}
			beenum.setText(" ��ǰ�۷���: " + count_beenum);
			//��ʱ��ʼ
			
			Runnable setime=new PrintTime(marktime, is_play);
			Thread timeThread=new Thread(setime);
			timeThread.start();//���߳� ���ڼ�ʱ
			
			is_play=false;//�����ٴε����ʼ �����һ���߳�
			
			//imgpanel.add(bullet);
			FightBees.this.airplane.requestFocus();//airplane�ػ񽹵�
			btnplay.setEnabled(false);//������
		}
	}
	
	//ֹͣ
	private class stopgame implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			// TODO ֹͣ��Ϸ
			int ok=JOptionPane.showConfirmDialog(null, "ȷ��ֹͣ��Ϸ��","ֹͣ",
					JOptionPane.OK_CANCEL_OPTION);
			if(ok==0)//0--ok ; 1--cancle
				
			{
			  imgpanel.remove(bullet);
			  is_play=false;//
			  invalidate();
			  btnplay.setEnabled(true);
			  
			}
		    FightBees.this.airplane.requestFocus();//airplane�ػ񽹵�  
		}
	}
	
	//�˳�
	private class exitgame implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			int ok=JOptionPane.showConfirmDialog(null, "ȷ���˳���Ϸ��","�˳�",
					JOptionPane.OK_CANCEL_OPTION);
			if(ok==0)//0--ok ; 1--cancle
		    System.exit(0);
			FightBees.this.airplane.requestFocus();//airplane�ػ񽹵�
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
		
		exitgame exithandle=new exitgame();
		btnexit.addActionListener(exithandle);
	}
}
