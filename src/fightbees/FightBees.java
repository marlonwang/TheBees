package fightbees;
/*Time:2013-9-18 22:47
 *Function:打蜜蜂 
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

/////////JPanel上画图/////////////////////////////
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

//bee  构造一个继承 JPanel的蜜蜂  添加  蜜蜂自动向下移动的方法
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

//随机指定范围的 n位不相同的数
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
	//private JLabel gamebox; //panel 中加 带图片的 label 实现背景效果
	private JLabel authorinfo;
	private JLabel imgblock;
	
	private JLabel bee;  //bee上画蜜蜂
	private beeLabel bee2;
	private JLabel bullet;//bullet上画子弹
	private JLabel airplane;  //airplane上画飞机
	
	private JTextArea instruction;  //游戏说明
	private JButton btnplay; 
	private JButton btnstop;

	private ImagePanel imgpanel=new ImagePanel();//实例化一个可 设置背景图片的 JPanel
	
	//////////////////////////////////////////////////    
	//创建画布
	private void createComponents()
	{
		Border lineBorder=new LineBorder(Color.white,5);//黑边框
		
		/*左边元素*/
		//背景图片530*470
		/*
			ImageIcon image = new ImageIcon("img\\bg.jpg"); 
			image.setImage(image.getImage().getScaledInstance(530,470,Image.SCALE_DEFAULT)); 
			gamebox=new JLabel(image);
			gamebox.setSize(530,470);
			gamebox.setBorder(new EmptyBorder(5, 5, 5, 5));//外边距上下左右各5
			gamebox.setBorder(lineBorder);
		*/
		//panel上画背景图片OK
		imgpanel.setBorder(new EmptyBorder(5,5,5,5));
		imgpanel.setBorder(lineBorder);
		imgpanel.setSize(530,470);
		
		//蜜蜂图片
		bee=new JLabel();
		bee.setIcon(new ImageIcon("img\\gbee.jpg"));
		
		//bee.setBounds(0,0,30,30);
		//bee.setIcon(new ImageIcon("img\\gbee.jpg"));
		//子弹图片
		bullet=new JLabel();
		bullet.setIcon(new ImageIcon("img\\bullet.jpg"));
		bullet.setVisible(false);
		//飞机图片
		airplane=new JLabel();
		airplane.setIcon(new ImageIcon("img\\plane.jpg"));
		
		/*右边元素*/
		//说明
		instruction=new JTextArea();
		instruction.setText("游戏说明:"+"\n"+"上、下、左、右、键"+"\n"+"控制方向."+"\n"+"击完蜜蜂者胜！");
		instruction.setEditable(false);//不可编辑
		//游戏作者
		authorinfo=new JLabel();
		authorinfo.setText("Created by vvwang");
		authorinfo.setFont(new Font("SansSerif",Font.BOLD,20));
		authorinfo.setForeground(Color.lightGray);
		authorinfo.setHorizontalAlignment(JLabel.CENTER);//对齐方式
		
		//游戏图片
		imgblock=new JLabel();
		imgblock.setSize(100, 200);
		imgblock.setIcon(new ImageIcon("img\\jietu.jpg"));
		//开始按钮
		btnplay=new JButton("开始");
		btnplay.setSize(60, 30);
		btnplay.setToolTipText("点击开始游戏^_^");//鼠标悬浮提示
		//停止按钮
		btnstop=new JButton("停止");
		btnstop.setSize(60, 30);
	}
	////////////////////////////////////////////////
	///布局实现
	private void layoutComponents()
	{
		//容器
		Container con=this.getContentPane();
		
		/////////////////左边游戏区域//////////////////////////
		//内层为bee  airplane 外层为imgpanel
		///////////////////////////////////////////////////
	    //	airplane.setBounds(530 / 2 - 30, 470 - 30, 30, 30);
		imgpanel.add(airplane);
		imgpanel.add(bee);
		imgpanel.add(bullet);//添加子弹
		/*panel中添加 带有图片的 label 再添加label 不能实现 图层 分离效果
			JPanel gamepanel=new JPanel();
			gamepanel.add(gamebox);
			gamepanel.setSize(540,480);
		*/
		
		///////////////////右边文字区域/////////////////////////
		//    共有3个panel
		////////////////////////////////////////////////////
		JPanel btn=new JPanel(new GridLayout(3,1,5,10));//按钮
		btn.add(btnplay);
		btn.add(btnstop);
		btn.setBorder(new TitledBorder("Two Buttons"));//类似c# 按钮 groupbox
		//文字 和 按钮 整合到一个instruct_btn panel中
		JPanel instruct_btn =new JPanel(new GridLayout(2,1,5,10));
		instruct_btn.add(instruction);
		instruct_btn.add(btn);
		
		//textpanel整合所有 右边的组件     (图片、文字、按钮)
		JPanel textpanel=new JPanel(new BorderLayout());
		textpanel.setSize(100,480);
		textpanel.add(authorinfo,BorderLayout.NORTH);
		textpanel.add(imgblock,BorderLayout.CENTER);
		textpanel.add(instruct_btn,BorderLayout.SOUTH);
		
		//用BorderLayout给整体布局
		//con.add(gamepanel,BorderLayout.CENTER);//panel 中加 带图片的 label 实现背景效果
		con.add(imgpanel,BorderLayout.CENTER);
		con.add(textpanel,BorderLayout.EAST);
		
	}
	
	////////////////////////////////////////////////////////
	////paintBees画蜜蜂群
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
	///打蜜蜂
	public FightBees()
	{
		createComponents();
		layoutComponents();
		//窗体布局
		setTitle("JAVA小游戏——打蜜蜂");
		setSize(735,480); //不设置大些 实际大小偏小
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);//居中
    	//pack();//自动控制大小   panel 中加 带图片的 label 实现背景效果时使用 
        
        @SuppressWarnings("unused")
		KeyMove keymove=new KeyMove();
    	btnEventHanders();
    	this.setResizable(false);
	}
	////////////////////////////////////////////////////////
	///键盘 上下左右控制飞机移动
	private class KeyMove implements KeyListener
	{
		/*飞机移动范围  x [10,510]
		 *         y [10,395]  
		 */
	    int x=260,y=385;//默认x y位置
	    int increment=10;
	    public KeyMove()
	    {
	        airplane.setLocation(x, y);
	        airplane.addKeyListener(this);//添加事件监听器
	        airplane.setFocusable(true);
	    }
	    //控制左移动
        private void leftmvlimit()
        {
        	if(airplane.getLocation().getX()>=15 && airplane.getLocation().getX()<=510)//<15不动
        	{
        		x-=increment;
        	}
        	else {
        		x=(int) airplane.getLocation().getX();
			}
        }
        //控制右移动
        private void rightmvlimit()
        {
        	if(airplane.getLocation().getX()>=10 && airplane.getLocation().getX()<=485)//>485不动
        	{
        		x+=increment;
        	}
        	else {
        		x=(int) airplane.getLocation().getX();
			}
        }
        //控制上移
        private void upmvlimit()
        {
        	if(airplane.getLocation().getY()>=20 && airplane.getLocation().getY()<=395)//<15不动
        	{
        		y-=increment;
        	}
        	else {
				y=(int) airplane.getLocation().getY();
			}
        }
        //控制下移
        private void downmvlimit()
        {
        	if(airplane.getLocation().getY()>=10 && airplane.getLocation().getY()<=385)//>385不动
        	{
        		y+=increment;
        	}
        	else {
				y=(int) airplane.getLocation().getY();
			}
        }
        //子弹发射
       
        int bullet_y=(int) airplane.getLocation().getY()-30;
        int bullet_x=(int) airplane.getLocation().getX();
        private void bulletshoot()
        {
        	//airplane.setLocation(x,y);
        	bullet.setBounds(bullet_x, bullet_y, 30, 30);//没有 该条件  子弹不动
        	bullet.setLocation(bullet_x, bullet_y);//初始化 子弹 设定在飞机正上方
        	bullet.setVisible(true);
        	//imgpanel.add(bullet);
        	//添加发射子弹声音
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
	///主函数
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub 
        JFrame.setDefaultLookAndFeelDecorated(true);
        FightBees frame=new FightBees();
        frame.setVisible(true);
        /*若内部类为非静态的  创建一个内部类的对象如下
            //OuterClass.InnerClass innerObj= outerObj.InnerClass();
           //例如 
		  //FightBees.KeyMove keymove=frame.new KeyMove();//???
         //frame.addKeyListener(keymove);
        */
	}
	
	////////////////////////////////////////////////////////
	//按钮事件监听
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
	///按钮事件  整合
	private void btnEventHanders()
	{
		playgame playhandel=new playgame();
		btnplay.addActionListener(playhandel);
		stopgame stophandel=new stopgame();
		btnstop.addActionListener(stophandel);
	}
}
