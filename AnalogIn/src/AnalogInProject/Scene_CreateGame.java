package AnalogInProject;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Scene_CreateGame extends SceneManager {
	/************************************************
	   Resource
	*************************************************/
	// Music
//	private Audio introMusic;
	
	// Image
	private Image background = ImageManager.background;
	private Image image_mainFrame = ImageManager.mainFrame;
	private Image image_subFrame = ImageManager.subFrame;
	
	/************************************************
	   Component/object
	*************************************************/	
	private Scene_CreateGame thisInstance = this; // thisInstance
	private JButton createBlockButton = new JButton(
			new ImageIcon(ImageManager.testButtonImage_1.getScaledInstance(240, 320, Image.SCALE_SMOOTH)));
	private JButton imageLoadButton = new JButton(
			new ImageIcon(ImageManager.testButtonImage_2.getScaledInstance(240, 320, Image.SCALE_SMOOTH)));
	private JButton deleteButton = new JButton(
			new ImageIcon(ImageManager.testButtonImage_3.getScaledInstance(240, 320, Image.SCALE_SMOOTH)));
	private JButton ruleButton = new JButton(
			new ImageIcon(ImageManager.testButtonImage_4.getScaledInstance(240, 320, Image.SCALE_SMOOTH)));
	private JButton exitButton = new JButton(
			new ImageIcon(ImageManager.testButtonImage_5.getScaledInstance(240, 320, Image.SCALE_SMOOTH)));
	
	private JLabel createBlockField = new JLabel();
	
	/************************************************
	   GameController
	*************************************************/	
	
	
	///GAME APPLICATION이 실행될 때 반드시 초기화해야하는 GIM 변수
	/// - KeyInputBuffer / GIM-currentScene / GIM-blockPriority // BlockObject
	/// - 
	public Scene_CreateGame() {
		// 
		GIM.currentScene = this;
		// Input 등록
		GIM.keyInputBuffer = new KeyInputBuffer();
		GIM.keyInputBuffer.start();		
		GIM.blockObject = blockObject;
		
		// Block의 우선순위
		GIM.blockPriority = 1;
		
		// button
		createBlockButton.setBounds(910, 90, 100, 100);
		createBlockButton.setBorderPainted(true); // 버튼 배치 테스트 때문에 true로 변경
		createBlockButton.setContentAreaFilled(false); // 채우지마
		createBlockButton.setFocusPainted(false);
		createBlockButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// button.setIcon으로 아이콘변경
				createBlockButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// button.setIcon으로 아이콘변경
				createBlockButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				createBlockField.setVisible(true);
			}
		});
		systemObject.add(createBlockButton);
		
		// image Upload button
		imageLoadButton.setBounds(1027, 90, 100, 100);
		imageLoadButton.setBorderPainted(true); // 버튼 범위 측정 때문에 true로 변경 및 테스트함
		imageLoadButton.setContentAreaFilled(false); // 채우지마
		imageLoadButton.setFocusPainted(false);
		imageLoadButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// button.setIcon으로 아이콘변경
				imageLoadButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// button.setIcon으로 아이콘변경
				imageLoadButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if(GIM.checkedBlock!=null)
				{
					String path = ImageManager.loadImage();
					if(path!=null && GIM.checkedBlock!=null)
					{
						GIM.checkedBlock.blockInfo.setImagePath(path);
						GIM.checkedBlock.synBlockInfo();				
					}
				}
			}
		});
		systemObject.add(imageLoadButton);
		
		// delete button
		deleteButton.setBounds(1146, 90, 100, 100);
		deleteButton.setBorderPainted(true); // 버튼 배치 테스트 때문에 true로 변경
		deleteButton.setContentAreaFilled(false);
		deleteButton.setFocusPainted(false);
		deleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 이벤트 처리는 createBlock이랑 똑같이 설정되어있음 누르면 rule이 뜨게 해야함
			}
				
			@Override
			public void mouseExited(MouseEvent e) {
				// button.setIcon으로 아이콘변경
				deleteButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
				
			@Override
			public void mousePressed(MouseEvent e) {
				deleteButton.setVisible(true);
			}
		});
		systemObject.add(deleteButton);
				
		createBlockField.setBounds(0, 20, 1280, 700); // 크기 설정 자유자재 아니였음? 테스트 버전?
		createBlockField.setVisible(false);
		createBlockField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		createBlockField.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println(e.getX() + " " + e.getY());
				Block b = new Block(new BlockInformation(e.getX(),e.getY(),50,50,""));
				blockObject.add(b);
				GIM.GameObject.add(b,GIM.blockPriority);
				createBlockField.setVisible(false);
			}
		});
		systemObject.add(0,createBlockField);
		
		// rule button
				ruleButton.setBounds(910, 580, 175, 115);
				ruleButton.setBorderPainted(true); // 버튼 배치 테스트 때문에 true로 변경
				ruleButton.setContentAreaFilled(false); // 채우지마
				ruleButton.setFocusPainted(false);
				ruleButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						// button.setIcon으로 아이콘변경
						ruleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// button.setIcon으로 아이콘변경
						ruleButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}

					@Override
					public void mousePressed(MouseEvent e) {
						ruleButton.setVisible(true);
					}
				});
				systemObject.add(ruleButton);
		
		// exit button
				exitButton.setBounds(1220, 8, 29, 29);
				exitButton.setBorderPainted(true); // 버튼 배치 테스트 때문에 true로 변경
				exitButton.setContentAreaFilled(false); // 채우지마
				exitButton.setFocusPainted(false);
				exitButton.addMouseListener(new MouseAdapter() {
					// 누르면 나가게 이벤트 처리해줘야 함
					@Override
					public void mouseEntered(MouseEvent e) {
						exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
					}

					@Override
					public void mouseExited(MouseEvent e) {
						exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}

					@Override
					public void mousePressed(MouseEvent e) {
						exitButton.setVisible(true);
					}
				});
				systemObject.add(exitButton);		
//		blockObject.add(new Block(new BlockInformation(400,300,300,300,ImageManager.testButtonImage)));
//		blockObject.add(new Block(new BlockInformation(500,100,300,300,ImageManager.testButtonImage)));		
		

		for(Component b : systemObject){
			GIM.GameObject.add(b);	
		}
		GIM.blockPriority = systemObject.size();
		for(Block b : blockObject){
			GIM.GameObject.add(b);	
		}

		GIM.GameObject.repaint();
		
//		introMusic = new Audio("testMusic.mp3", true); 자꾸 에러나서 주석처리 해놓음
//		introMusic.start();	
	}
	@Override
	public void removeScene()
	{
		GIM.removeGIM();
		for(Component c : systemObject){
			GIM.GameObject.remove(c);			
		}
		for(Block b : blockObject){
			GIM.GameObject.remove(b);			
		}
//		introMusic.close();
	}
	public void screenDraw(Graphics g) {
		g.drawImage(background, 0, 0, 1280, 720, null);
		g.drawImage(image_subFrame, 890, 35, 375, 670, null);
		g.drawImage(image_mainFrame, 15, 35, 860, 670, null);
		
		GIM.GameObject.paintComponents(g);
		GIM.GameObject.repaint();
	}
}
