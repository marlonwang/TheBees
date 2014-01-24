package fightbees;

import java.applet.AudioClip;
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

/////////JPanel上画背景图/////////////////////////
@SuppressWarnings("serial")
class ImagePanel extends JPanel 
{
	private Image image;
	public ImagePanel() 
	{
	    try 
	    {
	        image = ImageIO.read(new File("img\\bg.jpg"));//获取图片
	    } catch (IOException ex)
	    {
	        ex.printStackTrace();
	    }
	}
	
	@Override
	protected void paintComponent(Graphics g) 
	{
	    super.paintComponent(g);
	    g.drawImage(image, 0, 0, this);//画
	}
}  

////////////////////////////////
///计时器_thread()实现
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
		int i,j;//j 分钟  i 秒钟
		i=j=0;
		try {
		  String prestr = t_lable.getText();//先前label上的文字
		  while(t_b)
		  {
			  Thread.sleep(1000);//1s
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
			t_lable.setText("计时器出错+_+");
		}	
	}
}

@SuppressWarnings("serial")
public class FightBees extends JFrame 
{
	/**
	 * @窗体布局
	 */
	private boolean is_play=true;//游戏开始判断
    
	int count_beenum=0; //蜜蜂只数
	//容器
	private	Container con=this.getContentPane();
	private JLabel authorinfo;//author
	private JLabel grades;//分数
	private JLabel beenum;//蜜蜂数
	private JLabel marktime;//计时器
	
	private JLabel bullet;//bullet上画子弹
	private JLabel airplane;  //airplane上画飞机
	
	private JTextArea instruction;  //游戏说明
	private JButton btnplay; 
	private JButton btnstop;
	private JButton btnexit;

	private ImagePanel imgpanel=new ImagePanel();//实例化一个可 设置背景图片的 JPanel
	
	//////////////////////////////////////////////////    
	//创建画布
	private void createComponents()
	{
		Border lineBorder=new LineBorder(Color.white,5);//黑边框
		
		/*左边元素*/
		//背景图片400*470
		
		//panel上画背景图片OK
		imgpanel.setBorder(new EmptyBorder(5,5,5,5));
		imgpanel.setLayout(null);
		imgpanel.setBorder(lineBorder);
		imgpanel.setSize(400,470);
		
		//蜜蜂图片
		
		//子弹图片
		bullet=new JLabel();
		bullet.setIcon(new ImageIcon("img\\bullet.jpg"));
		bullet.setVisible(false);
		//飞机图片
		airplane=new JLabel();
		airplane.setIcon(new ImageIcon("img\\plane.jpg"));
		airplane.setBounds(imgpanel.getWidth() / 2 - 15, 
				imgpanel.getHeight() - 20 ,30,30);
		/*右边元素*/
		//说明
		instruction=new JTextArea();
		instruction.setText(" 游戏说明:"+"\n"+" 上、下、左、右、键"+"\n"+" 控制方向."+"\n"+" 击完蜜蜂者胜！");
		instruction.setEditable(false);//不可编辑
		//游戏作者
		authorinfo=new JLabel();
		authorinfo.setText(" By marlonwang ");
		authorinfo.setFont(new Font("SansSerif",Font.BOLD,20));
		authorinfo.setForeground(Color.lightGray);
		authorinfo.setHorizontalAlignment(JLabel.CENTER);//对齐方式	
		//游戏计分
		beenum=new JLabel(" 当前蜜蜂数: 0");
		beenum.setSize(80,30);
		grades=new JLabel(" 当前分数: 0");
		grades.setSize(80,30);
		marktime=new JLabel(" 已用时间: ");
		//开始按钮
		btnplay=new JButton("开始");
		btnplay.setSize(60, 30);
		btnplay.setToolTipText("点击开始游戏^_^");//鼠标悬浮提示
		//停止按钮
		btnstop=new JButton("停止");
		btnstop.setSize(60, 30);
		//退出
		btnexit=new JButton("退出");
		btnstop.setSize(60, 30);
	}
	////////////////////////////////////////////////
	///布局实现
	private void layoutComponents()
	{
		/////////////////左边游戏区域//////////////////////////
		//内层为bee (btnplay 中添加)  airplane 外层为imgpanel
		///////////////////////////////////////////////////
		imgpanel.add(airplane);
		imgpanel.add(bullet);//添加子弹
		
		///////////////////右边文字区域/////////////////////////
		//    共有3个panel
		////////////////////////////////////////////////////
		JPanel btn=new JPanel(new GridLayout(3,1,5,10));//按钮
		btn.add(btnplay);
		btn.add(btnstop);
		btn.add(btnexit);
		
		btn.setBorder(new TitledBorder("Two Buttons"));//类似c# 按钮 groupbox
		//文字 和 按钮 整合到一个instruct_btn panel中
		JPanel instruct_btn =new JPanel(new GridLayout(2,1,5,10));
		instruct_btn.add(instruction);
		instruct_btn.add(btn);
		
		//grades+beenum+marktime
		JPanel grade_beenum=new JPanel(new GridLayout(3,1));
		grade_beenum.add(beenum);
		grade_beenum.add(marktime);
		grade_beenum.add(grades);
		
		//textpanel整合所有 右边的组件     (图片、文字、按钮)
		JPanel textpanel=new JPanel(new BorderLayout());
		textpanel.setSize(80,480);
		textpanel.add(authorinfo,BorderLayout.NORTH);
		textpanel.add(grade_beenum,BorderLayout.CENTER);
		textpanel.add(instruct_btn,BorderLayout.SOUTH);
		
		//用BorderLayout给整体布局
		con.add(imgpanel,BorderLayout.CENTER);
		con.add(textpanel,BorderLayout.EAST);
		
	}
	
	////////////////////////////////////////////////////////
	////paintBees画蜜蜂群
	
	private void paintBees()
	{
		int x;
		
		String path1="img\\gbee.jpg";//green
		String path2="img\\bbee.jpg";//blue
		String path3="img\\ybee.jpg";//yellow
		String path4="img\\rbee.jpg";//red
		
	    final JLabel blab=new JLabel();
	  	//随机指定范围的 n位不相同的数
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
		blab.setBounds((int)(Math.random() * (imgpanel.getWidth() - 30)),
				(int)(10+Math.random()*50), 30, 30);//蜜蜂位置
		
		//blab.setBounds((int)(10+Math.random() * 366),(int)(5+Math.random()*15), 30, 30);
		imgpanel.add(blab);  
		//System.out.print(imgpanel.getWidth()); //测试imgpanel宽为396
		
		//Timer时间 蜜蜂移动
		Timer timer = new Timer(30, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			  
				// TODO 蜜蜂坠落
				if(blab.getLocation().getY()+30==airplane.getLocation().getY()
						&& (blab.getLocation().getX()>=(airplane.getLocation().getX()-30)
						&& (blab.getLocation().getX()<=(airplane.getLocation().getX()+30))
						)
						)
					//判断是否发生碰撞
				{
					ClassLoader classLoader = this.getClass().getClassLoader();
		        	AudioClip au = JApplet.newAudioClip(classLoader.getResource("112.wav")); 
					au.play();
					//无声???
					JOptionPane.showMessageDialog(null,"你输了", "Game Over", JOptionPane.WARNING_MESSAGE);
					//警告信息 游戏结束
					imgpanel.remove(blab);
					imgpanel.remove(airplane);//用remove()将panel(蜜蜂控件)移除
					repaint();
					
				}
				//没有发生撞击 分数+10
				else if(blab.getLocation().getY()>airplane.getLocation().getY()+30
						)
				{
//					ClassLoader classLoader= this.getClass().getClassLoader();
//					AudioClip au = JApplet.newAudioClip(classLoader.getResource("guing.mp3")); 
//					au.play();
					
					grades.setText(" 当前分数: "+10*count_beenum);
				}
				if( blab.getLocation().getY()>405)
				{
					//blab.setLocation( (int) blab.getLocation().getX(), 15); //从最上方开始
					imgpanel.remove(blab);
					repaint();
					//超出范围则消失
				}
				else 
				{
					blab.setLocation( (int)blab.getLocation().getX(),
							(int)blab.getLocation().getY()+1); 
					//子弹纵坐标+1
				}
				repaint();
			}
		});
		
		timer.start();
	}
	
	////////////////////////////////////////////////////////
	///键盘 上下左右控制飞机移动
	private class KeyMove implements KeyListener,Runnable
	{
		
		/*飞机移动范围  x [10,510]
		 *         y [10,405]  
		 */
	    private int x,y,i;
	    
	    //默认x y位置185, 405,  i 10;
	    
	    public KeyMove(int position_x,int position_y,int position_increment)
	    {
	    	x = position_x;
	    	y = position_y;
	    	i = position_increment;
	    }
	    public void run()
	    {
	    	airplane.setLocation(x, y);
	        airplane.addKeyListener(this);//添加事件监听器
	        airplane.setFocusable(true);
	    }
	    //控制左移动
        private void leftmvlimit()
        {
        	if(airplane.getLocation().getX()>=20 && airplane.getLocation().getX()<=380)//<15不动
        	{
        		x-=i;
        	}
        	else {
        		x=(int) airplane.getLocation().getX();
			}
        }
        //控制右移动
        private void rightmvlimit()
        {
        	if(airplane.getLocation().getX()>=10 && airplane.getLocation().getX()<=350)//>355不动
        	{
        		x+=i;
        	}
        	else {
        		x=(int) airplane.getLocation().getX();
			}
        }

        //控制上移
        private void upmvlimit()
        {
        	if(airplane.getLocation().getY()>=20 && airplane.getLocation().getY()<=415)//<15不动
        	{
        		y-=i;
        	}
        	else {
				y=(int) airplane.getLocation().getY();
			}
        }
        //控制下移
        private void downmvlimit()
        {
        	if(airplane.getLocation().getY()>=10 && airplane.getLocation().getY()<=405)//>405不动
        	{
        		y+=i;
        	}
        	else {
				y=(int) airplane.getLocation().getY();
			}
        }
        //子弹发射
        //该功能已经去除了
        int bullet_y=(int) airplane.getLocation().getY()-30;
        int bullet_x=(int) airplane.getLocation().getX();
        
        @SuppressWarnings("unused")
		private void bulletshoot()
        {
        	imgpanel.add(bullet);
        	bullet.setBounds(bullet_x, bullet_y, 30, 30);//没有 该条件  子弹不动
        	bullet.setLocation(bullet_x, bullet_y);//初始化 子弹 设定在飞机正上方
        	bullet.setVisible(true);
        	//imgpanel.add(bullet);
        	//添加发射子弹声音
            ClassLoader classLoader = this.getClass().getClassLoader();
        	AudioClip au = JApplet.newAudioClip(classLoader.getResource("BONG.wav")); 
			au.play();
        	Timer timer = new Timer(100, new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent arg0) {
    				// TODO 子弹自动上移
    				if(bullet_y < 10)
    				{
    					bullet_y  = 355;
    				    //bullet.setVisible(false);
    					imgpanel.remove(bullet);
    				    repaint();
    					//AudioClip au = JApplet.newAudioClip(classLoader.getResource("BONG.wav")); 
    					//au.play();
    					
    				}
    				else {
    					bullet_x=(int) airplane.getLocation().getX();
    					bullet_y -= 4;
    				}
    				bullet.setLocation(bullet_x, bullet_y);  				
    			}
    		});
    		
    		timer.start();
        }
		@Override
	    public void keyPressed(KeyEvent e) {
	        // TODO 上下左右移动
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
	            	//bulletshoot();   //子弹功能不完善，去除
	                break;  
	        }
	       airplane.setLocation(x,y);
	       
	    }
	    @Override
	    public void keyReleased(KeyEvent e) {
	        // TODO 按键释放
	    }
	    @Override
	    public void keyTyped(KeyEvent e) {
	        // TODO Auto-generated method stub
	    }
	}
	
	
	////////////////////////////////////////////////////////
	///躲蜜蜂
	public FightBees()
	{
		
		createComponents();
		layoutComponents();
		
		//窗体布局
		setTitle("JAVA小游戏——躲蜜蜂");
		setSize(570,513); //不设置大些 实际大小偏小
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);//居中
		
		//线程 飞机移动        默认x y位置185, 405,  i 10;
		Runnable planeMove=new KeyMove(185, 405, 10);
		Thread moveThread=new Thread(planeMove);
		moveThread.setPriority(1);
		moveThread.start();
		this.setResizable(false);//固定大小
		
		btnEventHanders();
	}
		
	////////////////////////////////////////////////////////
	///主函数
	public static void main(String[] args) 
	{
		// TODO 主函数 
		//显示效果不同
        //JFrame.setDefaultLookAndFeelDecorated(true);
        FightBees frame=new FightBees();
        frame.setVisible(true);   
	}
	
	////////////////////////////////////////////////////////
	//按钮事件监听
	
	//开始
	private class playgame implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			// TODO 开始按钮 负责 生成蜜蜂
			
			int rdnum=(int)(1+Math.random()*5);//随机1~5个蜜蜂
			for(int i=0;i<rdnum;i++)
			{
			   paintBees();
			   count_beenum++;
			   
//			   try 
//			   {
//				Thread.sleep(200);
//			   } 
//			   catch (InterruptedException e1)
//			   {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			   }
			}
			beenum.setText(" 当前蜜蜂数: " + count_beenum);
			
			//计时开始
			
			Runnable setime=new PrintTime(marktime, is_play);
			Thread timeThread=new Thread(setime);
			timeThread.start();//新线程 用于计时
			
			is_play=false;//避免再次点击开始 又添加一个线程
			
			//imgpanel.add(bullet);
			FightBees.this.airplane.requestFocus();//airplane重获焦点
			btnplay.setEnabled(false);//不可行
			
		}
	}
	
	//停止
	private class stopgame implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			// TODO 停止游戏

			  imgpanel.remove(bullet);
			  is_play=false;//
			  repaint();
			  btnplay.setEnabled(true);
			  
		      FightBees.this.airplane.requestFocus();//airplane重获焦点  
		}
	}
	
	//退出
	private class exitgame implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			int ok=JOptionPane.showConfirmDialog(null, "确定退出游戏？","退出",
					JOptionPane.OK_CANCEL_OPTION);
			if(ok==0)//0--ok ; 1--cancle
		    System.exit(0);
			FightBees.this.airplane.requestFocus();//airplane重获焦点
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
		
		exitgame exithandle=new exitgame();
		btnexit.addActionListener(exithandle);
	}
}
