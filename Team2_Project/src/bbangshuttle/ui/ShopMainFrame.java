package bbangshuttle.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.DecimalFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import bbangshuttle.cart.CartService;
import bbangshuttle.member.Member;
import bbangshuttle.member.MemberService;
import bbangshuttle.order.OrderService;
import bbangshuttle.product.ProductSevrice;

public class ShopMainFrame extends JFrame {
	/************ 1.Service객체멤버변수선언 ************/
	MemberService ms;
	OrderService os;
	ProductSevrice ps;
	CartService cs;
	/**************************************************/

	/***** 2.로그인한User객체저장할 User객체선언 *******/
	
	/**************************************************/

	private JPanel contentPane;
	private JTextField searchTextField;
	private JTabbedPane shopTabbedPane;
	private JPanel southMenuPanel;
	private JPanel northMenuPanel;
	private JButton cartButton;
	private JPanel productSmallDogContentPanel;
	private JPanel productMiddleDogContentPanel;
	private JPanel productBigDogContentPanel;
	private JPanel productPopularDogContentPanel;
	private JPanel cartContentPanel;
	private JLabel cartCountLabel;
	private JPanel cartPanel;
	private JScrollPane cartContentPanelScrollPane;
	private JTextField nameTextField;
	private JTextField emailTextField;
	private JTextField idTextField;
	private JTextField userLoginIdTF;
	private JTextField UserLoginPasswordTF;
	private JTabbedPane userTabbedPane;
	private JTextField userJoinNameTF;
	private JTextField userJoinEmailTF;
	private JLabel loginPasswordMessageLabel;
	private JLabel userLoginPasswordLabel;
	private JLabel userLoginIdMessageLabel;
	private JTextField userJoinIdTF;
	private JTextField userJoinPasswordTF;
	private JLabel userJoinIdMsgLB;
	private JPanel orderContentPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopMainFrame frame = new ShopMainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ShopMainFrame() throws Exception {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ShopMainFrame.class.getResource("/images/title_logo25.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 410, 740);
		setBounds(100, 100, 410, 741);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		shopTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		shopTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		shopTabbedPane.setForeground(Color.DARK_GRAY);
		shopTabbedPane.setBackground(Color.WHITE);
		shopTabbedPane.setFont(new Font("D2Coding", Font.BOLD, 12));
		shopTabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		contentPane.add(shopTabbedPane, BorderLayout.CENTER);

		JPanel productPupularDogPanel = new JPanel();
		productPupularDogPanel.setBackground(Color.WHITE);
//		shopTabbedPane.addTab("인기견", new ImageIcon(ShopMainFrame.class.getResource("/images/title_logo20.png")),
//				productPupularDogPanel, null);
		shopTabbedPane.addTab("", new ImageIcon(ShopMainFrame.class.getResource("/images/title_logo20.png")),
				productPupularDogPanel, null);
		shopTabbedPane.setBackgroundAt(0, Color.WHITE);
		productPupularDogPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane productPopularDogContentPanelScrollPane = new JScrollPane();
		productPopularDogContentPanelScrollPane.setBackground(Color.WHITE);
		productPopularDogContentPanelScrollPane.setPreferredSize(new Dimension(390, 780));
		productPopularDogContentPanelScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		productPupularDogPanel.add(productPopularDogContentPanelScrollPane, BorderLayout.CENTER);

		productPopularDogContentPanel = new JPanel();
		productPopularDogContentPanel.setPreferredSize(new Dimension(390, 780));
		productPopularDogContentPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		productPopularDogContentPanel.setBackground(Color.WHITE);
		productPopularDogContentPanelScrollPane.setViewportView(productPopularDogContentPanel);
		productPopularDogContentPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 7, 7));

		/************* 제품1개UI패널 디자인 시작 *******************/
		JPanel productPanel = new JPanel();
		productPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		productPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		productPanel.setBounds(new Rectangle(0, 0, 120, 120));
		productPanel.setMaximumSize(new Dimension(200, 200));
		productPanel.setMinimumSize(new Dimension(150, 150));
		productPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		productPanel.setBackground(Color.WHITE);
		productPanel.setBorder(null);
		productPanel.setSize(new Dimension(120, 120));
		productPanel.setPreferredSize(new Dimension(170, 190));
		productPanel.setLayout(null);
		JLabel productImageLabel = new JLabel("");
		productImageLabel.setHorizontalAlignment(SwingConstants.CENTER);

		productImageLabel.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/images/bigle.png")));
		productImageLabel.setBounds(3, 1, 162, 116);
		productPanel.add(productImageLabel);

		JLabel productDescLabel = new JLabel("<html>\r\n\t<font size='3'>\r\n\t\t\r\n\t\t\t" + "견종: 비글<br>\r\n\t\t\t"
				+ "가격: 34,000<br>\r\n\t\t\t" + "설명: 귀여워요\r\n\t\t\r\n </font></html>");
		productDescLabel.setVerticalAlignment(SwingConstants.TOP);
		productDescLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		productDescLabel.setHorizontalAlignment(SwingConstants.LEFT);
		productDescLabel.setBounds(3, 143, 164, 47);
		productPanel.add(productDescLabel);

		JButton cartAddButton = new JButton("");
		cartAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(">>카트담기...");
			}
		});
		cartAddButton.setBorder(null);
		cartAddButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cartAddButton.setOpaque(false);
		cartAddButton.setBackground(Color.WHITE);
		cartAddButton.setForeground(Color.WHITE);
		cartAddButton.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/images/cart20.png")));
		cartAddButton.setBounds(136, 119, 31, 23);
		productPanel.add(cartAddButton);

		JComboBox cartQtyComboBox = new JComboBox();
		cartQtyComboBox.setAutoscrolls(true);
		cartQtyComboBox
				.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
		cartQtyComboBox.setOpaque(false);
		cartQtyComboBox.setBorder(null);
		cartQtyComboBox.setBackground(Color.WHITE);
		cartQtyComboBox.setBounds(99, 119, 33, 23);
		cartQtyComboBox.setMaximumRowCount(cartQtyComboBox.getModel().getSize());
		productPanel.add(cartQtyComboBox);

		productPopularDogContentPanel.add(productPanel);
		/************* 제품1개UI패널 디자인끝 *******************/

		JPanel productSmallDogPanel = new JPanel();
		productSmallDogPanel.setBackground(Color.WHITE);
//		shopTabbedPane.addTab("소형견", new ImageIcon(ShopMainFrame.class.getResource("/images/small_dog20.png")),
//				productSmallDogPanel, null);
		shopTabbedPane.addTab("", new ImageIcon(ShopMainFrame.class.getResource("/images/small_dog20.png")),
				productSmallDogPanel, null);
		productSmallDogPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane productSmallDogContentPanelScrollPane = new JScrollPane();
		productSmallDogContentPanelScrollPane.setPreferredSize(new Dimension(390, 780));
		productSmallDogContentPanelScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		productSmallDogPanel.add(productSmallDogContentPanelScrollPane, BorderLayout.CENTER);

		productSmallDogContentPanel = new JPanel();
		productSmallDogContentPanel.setPreferredSize(new Dimension(390, 780));
		productSmallDogContentPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		productSmallDogContentPanelScrollPane.setViewportView(productSmallDogContentPanel);
		productSmallDogContentPanel.setBackground(Color.WHITE);
		productSmallDogContentPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 7, 7));

		shopTabbedPane.setBackgroundAt(1, Color.WHITE);

		JPanel productMiddleDogPanel = new JPanel();
		productMiddleDogPanel.setBackground(Color.WHITE);
//		shopTabbedPane.addTab("중형견", new ImageIcon(ShopMainFrame.class.getResource("/images/middle_dog20.png")),
//				productMiddleDogPanel, null);
		shopTabbedPane.addTab("", new ImageIcon(ShopMainFrame.class.getResource("/images/middle_dog20.png")),
				productMiddleDogPanel, null);
		productMiddleDogPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane productMiddleDogContentPanelScrollPane = new JScrollPane();
		productMiddleDogContentPanelScrollPane.setPreferredSize(new Dimension(390, 780));
		productMiddleDogContentPanelScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		productMiddleDogPanel.add(productMiddleDogContentPanelScrollPane);

		productMiddleDogContentPanel = new JPanel();
		productMiddleDogContentPanel.setPreferredSize(new Dimension(390, 780));
		productMiddleDogContentPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		productMiddleDogContentPanel.setBackground(Color.WHITE);
		productMiddleDogContentPanelScrollPane.setViewportView(productMiddleDogContentPanel);
		productMiddleDogContentPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 7, 7));
		shopTabbedPane.setBackgroundAt(2, Color.WHITE);

		JPanel productBigDogPanel = new JPanel();
		productBigDogPanel.setBackground(Color.WHITE);
//		shopTabbedPane.addTab("대형견", new ImageIcon(ShopMainFrame.class.getResource("/images/big_dog20.png")),
//				productBigDogPanel, null);
		shopTabbedPane.addTab("", new ImageIcon(ShopMainFrame.class.getResource("/images/big_dog20.png")),
				productBigDogPanel, null);
		productBigDogPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane productBigDogContentPanelScrollPane = new JScrollPane();
		productBigDogContentPanelScrollPane.setPreferredSize(new Dimension(390, 780));
		productBigDogContentPanelScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		productBigDogPanel.add(productBigDogContentPanelScrollPane, BorderLayout.CENTER);

		productBigDogContentPanel = new JPanel();
		productBigDogContentPanel.setPreferredSize(new Dimension(390, 780));
		productBigDogContentPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		productBigDogContentPanel.setBackground(Color.WHITE);
		productBigDogContentPanelScrollPane.setViewportView(productBigDogContentPanel);
		productBigDogContentPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 7, 7));
		shopTabbedPane.setBackgroundAt(3, Color.WHITE);

		cartPanel = new JPanel();
		cartPanel.setBackground(Color.WHITE);
//		shopTabbedPane.addTab("카트", new ImageIcon(ShopMainFrame.class.getResource("/images/cart20.png")), cartPanel,
//				null);
		shopTabbedPane.addTab("", new ImageIcon(ShopMainFrame.class.getResource("/images/cart20.png")), cartPanel,
				null);
		shopTabbedPane.setEnabledAt(4, false);
		cartPanel.setLayout(new BorderLayout(0, 0));

		cartContentPanelScrollPane = new JScrollPane();
		cartContentPanelScrollPane.setBackground(Color.WHITE);
		cartContentPanelScrollPane.setPreferredSize(new Dimension(390, 780));
		cartContentPanelScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		cartPanel.add(cartContentPanelScrollPane, BorderLayout.CENTER);

		cartContentPanel = new JPanel();
		cartContentPanel.setPreferredSize(new Dimension(390, 780));
		cartContentPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		cartContentPanel.setBackground(Color.WHITE);
		cartContentPanelScrollPane.setViewportView(cartContentPanel);
		cartContentPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 7, 10));
		/**************** UIcartItemPanel[카트아이템1개Panel] **************/
		JPanel cartItemPanel = new JPanel();
		cartItemPanel.setBorder(null);
		cartItemPanel.setBackground(Color.WHITE);
		cartItemPanel.setPreferredSize(new Dimension(350, 72));
		cartItemPanel.setLayout(null);

		JLabel cartItemImageLabel = new JLabel("비글");
		cartItemImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cartItemImageLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
		cartItemImageLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		cartItemImageLabel.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/images/50" + "bigle.png")));
		cartItemImageLabel.setBounds(0, 0, 69, 72);
		cartItemPanel.add(cartItemImageLabel);

		JLabel cartItemDescLabel = new JLabel(new DecimalFormat("#,###").format(300000));
		cartItemDescLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		cartItemDescLabel.setFont(new Font("D2Coding", Font.PLAIN, 13));
		cartItemDescLabel.setBounds(61, 25, 74, 23);
		cartItemPanel.add(cartItemDescLabel);

		JButton cartItemDeleteButton = new JButton("");

		cartItemDeleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cartItemDeleteButton.setBorder(null);
		cartItemDeleteButton.setBackground(Color.WHITE);
		cartItemDeleteButton.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/images/cart_item_delete30.png")));
		cartItemDeleteButton.setBounds(335, 27, 15, 17);
		cartItemPanel.add(cartItemDeleteButton);

		JLabel cartItemTotPrice = new JLabel(new DecimalFormat("#,###").format(9000000));
		cartItemTotPrice.setFont(new Font("D2Coding", Font.PLAIN, 13));
		cartItemTotPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		cartItemTotPrice.setBounds(187, 25, 69, 22);
		cartItemPanel.add(cartItemTotPrice);

		JComboBox cartItemQtyComboBox = new JComboBox();
		cartItemQtyComboBox
				.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
		cartItemQtyComboBox.setBounds(141, 24, 39, 23);
		cartItemQtyComboBox.setSelectedItem(2 + "");
		cartItemPanel.add(cartItemQtyComboBox);
		JButton cartItemUpdateButton = new JButton("수정");

		cartItemUpdateButton.setPreferredSize(new Dimension(57, 23));
		cartItemUpdateButton.setFont(new Font("D2Coding", Font.BOLD, 11));

		cartItemUpdateButton.setBounds(268, 23, 59, 27);
		cartItemPanel.add(cartItemUpdateButton);

		cartContentPanel.add(cartItemPanel);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setPreferredSize(new Dimension(350, 15));
		cartContentPanel.add(lblNewLabel);
		/*
		 * JButton cartOrderButton = new JButton("주문하기");
		 * cartOrderButton.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { //orderService.create(sU); } });
		 * 
		 * cartOrderButton.setBorder(new
		 * LineBorder(UIManager.getColor("Button.shadow")));
		 * cartOrderButton.setBackground(UIManager.getColor("Button.light"));
		 * cartOrderButton.setFont(new Font("D2Coding", Font.PLAIN, 17));
		 * cartOrderButton.setPreferredSize(new Dimension(350, 40));
		 * cartContentPanel.add(cartOrderButton);
		 */
		/**************** UIcartItemPanel[카트아이템1개Panel] **************/

		shopTabbedPane.setBackgroundAt(4, Color.WHITE);

		JPanel userPanel = new JPanel();
		userPanel.setBackground(Color.WHITE);
		userPanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {

			}
		});
//		shopTabbedPane.addTab("회원", new ImageIcon(ShopMainFrame.class.getResource("/images/user20.png")), userPanel,
//				null);
		shopTabbedPane.addTab("", new ImageIcon(ShopMainFrame.class.getResource("/images/user20.png")), userPanel,
				null);
		shopTabbedPane.setEnabledAt(5, true);
		shopTabbedPane.setBackgroundAt(5, Color.WHITE);
		userPanel.setLayout(new BorderLayout(0, 0));

		userTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		userPanel.add(userTabbedPane, BorderLayout.CENTER);

		JPanel userLoginPanel = new JPanel();
		userTabbedPane.addTab("로그인", null, userLoginPanel, null);
		userLoginPanel.setLayout(null);

		userLoginIdTF = new JTextField();
		userLoginIdTF.setText("guard1");
		userLoginIdTF.setBounds(102, 92, 240, 21);
		userLoginPanel.add(userLoginIdTF);
		userLoginIdTF.setColumns(10);

		UserLoginPasswordTF = new JTextField();
		UserLoginPasswordTF.setText("1111");
		UserLoginPasswordTF.setBounds(102, 149, 240, 21);
		userLoginPanel.add(UserLoginPasswordTF);
		UserLoginPasswordTF.setColumns(10);

		userLoginPasswordLabel = new JLabel("패쓰워드");
		userLoginPasswordLabel.setBounds(12, 152, 57, 15);
		userLoginPanel.add(userLoginPasswordLabel);

		JLabel userLoginIdLabel = new JLabel("아이디");
		userLoginIdLabel.setBounds(12, 95, 57, 15);
		userLoginPanel.add(userLoginIdLabel);

		JButton userLoginLoginBtn = new JButton("로그인");
		userLoginLoginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/******** 로그인버튼클릭시 *********/
				// 1.입력유효성체크
				
				// 2.UserService.login 메쏘드호출
				/*********** 2.UserService.login 메쏘드호출 **************/
				try {
						/*
						 * 로그인성공 
						 * 	- 성공한아이디로 User정보얻기 
						 * 	- ShopMainFrame에 User객체 넘겨주기(ShopMainFrame객체의 메소드호출시 인자로 넘겨주기) 
						 * 	- 로그인창닫기
						 */
					
						/*
						 *  로그인실패
						 */
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				/******************************************************/
			}
		});
		userLoginLoginBtn.setBounds(28, 224, 140, 23);
		userLoginPanel.add(userLoginLoginBtn);

		userLoginIdMessageLabel = new JLabel("");
		userLoginIdMessageLabel.setBounds(102, 119, 240, 20);
		userLoginPanel.add(userLoginIdMessageLabel);

		loginPasswordMessageLabel = new JLabel("");
		loginPasswordMessageLabel.setBounds(102, 180, 240, 20);
		userLoginPanel.add(loginPasswordMessageLabel);

		JLabel userInfoTitleLabel = new JLabel("로그인");
		userInfoTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userInfoTitleLabel.setFont(new Font("굴림", Font.BOLD, 22));
		userInfoTitleLabel.setBounds(28, 39, 314, 29);
		userLoginPanel.add(userInfoTitleLabel);

		JButton userLoginJoinBtn = new JButton("가입");
		userLoginJoinBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userTabbedPane.setSelectedIndex(1);
			}
		});
		userLoginJoinBtn.setBounds(202, 224, 140, 23);
		userLoginPanel.add(userLoginJoinBtn);

		JPanel userJoinPanel = new JPanel();
		userTabbedPane.addTab("회원가입", null, userJoinPanel, null);
		userJoinPanel.setLayout(null);

		JLabel userJoinNameLabel = new JLabel("이름");
		userJoinNameLabel.setBounds(29, 208, 61, 15);
		userJoinPanel.add(userJoinNameLabel);

		userJoinNameTF = new JTextField();
		userJoinNameTF.setColumns(10);
		userJoinNameTF.setBounds(102, 205, 238, 21);
		userJoinPanel.add(userJoinNameTF);

		JLabel userJoinEmailLabel = new JLabel("이메일");
		userJoinEmailLabel.setBounds(29, 259, 61, 15);
		userJoinPanel.add(userJoinEmailLabel);

		userJoinEmailTF = new JTextField();
		userJoinEmailTF.setColumns(10);
		userJoinEmailTF.setBounds(102, 256, 238, 21);
		userJoinPanel.add(userJoinEmailTF);

		JLabel userJoinIdLabel = new JLabel("아이디");
		userJoinIdLabel.setBounds(27, 112, 64, 15);
		userJoinPanel.add(userJoinIdLabel);

		userJoinIdTF = new JTextField();
		userJoinIdTF.setColumns(10);
		userJoinIdTF.setBounds(103, 109, 237, 21);
		userJoinPanel.add(userJoinIdTF);

		JLabel userJoinPasswordLabel = new JLabel("패쓰워드");
		userJoinPasswordLabel.setBounds(29, 164, 61, 15);
		userJoinPanel.add(userJoinPasswordLabel);

		userJoinPasswordTF = new JTextField();
		userJoinPasswordTF.setColumns(10);
		userJoinPasswordTF.setBounds(103, 161, 237, 21);
		userJoinPanel.add(userJoinPasswordTF);

		JLabel userJoinTitleLabel = new JLabel("회원가입");
		userJoinTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userJoinTitleLabel.setFont(new Font("굴림", Font.BOLD, 22));
		userJoinTitleLabel.setBounds(29, 56, 314, 29);
		userJoinPanel.add(userJoinTitleLabel);

		JButton userJoinBtn = new JButton("가입");
		userJoinBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					/*********************************
					 UserService.create 메쏘드호출
					 ********************************/
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		userJoinBtn.setBounds(74, 306, 97, 23);
		userJoinPanel.add(userJoinBtn);

		JButton userJoinCancleBtn = new JButton("취소");
		userJoinCancleBtn.setBounds(221, 306, 97, 23);
		userJoinPanel.add(userJoinCancleBtn);

		userJoinIdMsgLB = new JLabel("");
		userJoinIdMsgLB.setForeground(Color.RED);
		userJoinIdMsgLB.setBounds(113, 136, 227, 15);
		userJoinPanel.add(userJoinIdMsgLB);

		JPanel userInfoPanel = new JPanel();
		
		userTabbedPane.addTab("회원정보", null, userInfoPanel, null);
		userTabbedPane.setEnabledAt(2, false);
		userInfoPanel.setLayout(null);

		JLabel nameLabel = new JLabel("이름");
		nameLabel.setBounds(71, 173, 57, 15);
		userInfoPanel.add(nameLabel);

		JLabel emailLabel = new JLabel("이메일");
		emailLabel.setBounds(71, 213, 57, 15);
		userInfoPanel.add(emailLabel);

		JButton logoutButton = new JButton("로그아웃");
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					logoutProcess();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		logoutButton.setForeground(Color.DARK_GRAY);
		logoutButton.setBackground(Color.WHITE);
		logoutButton.setBounds(79, 305, 209, 29);
		userInfoPanel.add(logoutButton);

		nameTextField = new JTextField();
		nameTextField.setEditable(false);
		nameTextField.setColumns(10);
		nameTextField.setBounds(172, 170, 116, 21);
		userInfoPanel.add(nameTextField);

		emailTextField = new JTextField();
		emailTextField.setEditable(false);
		emailTextField.setColumns(10);
		emailTextField.setBounds(172, 210, 116, 21);
		userInfoPanel.add(emailTextField);

		JLabel idLabel = new JLabel("아이디");
		idLabel.setBounds(71, 129, 57, 15);
		userInfoPanel.add(idLabel);

		idTextField = new JTextField();
		idTextField.setEnabled(false);
		idTextField.setEditable(false);
		idTextField.setColumns(10);
		idTextField.setBounds(172, 126, 116, 21);
		userInfoPanel.add(idTextField);

		JLabel userInfoLabel = new JLabel("회원정보");
		userInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userInfoLabel.setFont(new Font("굴림", Font.BOLD, 22));
		userInfoLabel.setBounds(38, 35, 314, 29);
		userInfoPanel.add(userInfoLabel);

		JPanel orderPanel = new JPanel();
		
		
//		shopTabbedPane.addTab("주문", new ImageIcon(ShopMainFrame.class.getResource("/images/checklist20.png")),
//				orderPanel, null);
		shopTabbedPane.addTab("", new ImageIcon(ShopMainFrame.class.getResource("/images/checklist20.png")),
				orderPanel, null);
		orderPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane orderContentPanelScrollPane = new JScrollPane();
		orderContentPanelScrollPane.setPreferredSize(new Dimension(390, 780));
		orderContentPanelScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		orderContentPanelScrollPane.setBackground(Color.WHITE);
		orderPanel.add(orderContentPanelScrollPane, BorderLayout.CENTER);

		orderContentPanel = new JPanel();
		orderContentPanel.setPreferredSize(new Dimension(390, 780));
		orderContentPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		orderContentPanel.setBackground(Color.WHITE);
		orderContentPanelScrollPane.setViewportView(orderContentPanel);
		orderContentPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		/*****************************
		 * orderTitlePanel start
		 ********************************/
		JPanel orderTitlePanel = new JPanel();
		orderTitlePanel.setLayout(null);
		orderTitlePanel.setPreferredSize(new Dimension(350, 36));
		orderTitlePanel.setBorder(null);
		orderTitlePanel.setBackground(Color.WHITE);
		orderContentPanel.add(orderTitlePanel);

		JLabel orderItemProductTitleLabel = new JLabel("Product Name");
		orderItemProductTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		orderItemProductTitleLabel.setFont(new Font("D2Coding", Font.BOLD, 13));
		orderItemProductTitleLabel.setBounds(0, 10, 103, 23);
		orderTitlePanel.add(orderItemProductTitleLabel);

		JLabel orderItemSubTotalTitleLabel = new JLabel("Subtotal");
		orderItemSubTotalTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		orderItemSubTotalTitleLabel.setFont(new Font("D2Coding", Font.BOLD, 13));
		orderItemSubTotalTitleLabel.setBounds(261, 10, 89, 23);
		orderTitlePanel.add(orderItemSubTotalTitleLabel);
		/*****************************
		 * orderTitlePanel end
		 ********************************/

		/*****************************
		 * orderItemPanel start
		 ********************************/
		JPanel orderItemPanel = new JPanel();
		orderItemPanel.setLayout(null);
		orderItemPanel.setPreferredSize(new Dimension(350, 72));
		orderItemPanel.setBorder(null);
		orderItemPanel.setBackground(Color.WHITE);
		orderContentPanel.add(orderItemPanel);

		JLabel orderItemImageLabel = new JLabel("");
		orderItemImageLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
		orderItemImageLabel.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/images/50bigle.jpg")));
		orderItemImageLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
		orderItemImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		orderItemImageLabel.setBounds(12, 10, 50, 52);
		orderItemPanel.add(orderItemImageLabel);

		JLabel orderItemDescLabel = new JLabel("<html><b>비글 X 3</b> <br/><br/> ￦ 550,000</html>");
		orderItemDescLabel.setHorizontalAlignment(SwingConstants.CENTER);
		orderItemDescLabel.setFont(new Font("D2Coding", Font.PLAIN, 13));
		orderItemDescLabel.setBounds(70, 0, 100, 72);
		orderItemPanel.add(orderItemDescLabel);

		JLabel orderItemTotPrice = new JLabel("￦ 9,000,000");
		orderItemTotPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		orderItemTotPrice.setFont(new Font("D2Coding", Font.PLAIN, 13));
		orderItemTotPrice.setBounds(231, 10, 107, 52);
		orderItemPanel.add(orderItemTotPrice);
		/********************************
		 * orderItemPanel end
		 ******************************/
		/********************************
		 * orderBottomTitlePanel start
		 *********************/
		JLabel orderLabel = new JLabel("");
		orderLabel.setPreferredSize(new Dimension(350, 15));
		orderContentPanel.add(orderLabel);
		JPanel orderBottomTitlePanel = new JPanel();
		orderBottomTitlePanel.setLayout(null);
		orderBottomTitlePanel.setPreferredSize(new Dimension(350, 36));
		orderBottomTitlePanel.setBorder(null);
		orderBottomTitlePanel.setBackground(Color.WHITE);
		orderContentPanel.add(orderBottomTitlePanel);

		JLabel orderItemTotPriceLabel = new JLabel("￦ 1,050,000");
		orderItemTotPriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		orderItemTotPriceLabel.setFont(new Font("D2Coding", Font.BOLD, 13));
		orderItemTotPriceLabel.setBounds(241, 10, 109, 23);
		orderBottomTitlePanel.add(orderItemTotPriceLabel);

		JLabel orderItemBottomTotalTitleLabel = new JLabel("Total:");
		orderItemBottomTotalTitleLabel.setFont(new Font("굴림", Font.BOLD, 12));
		orderItemBottomTotalTitleLabel.setBounds(205, 10, 39, 23);
		orderBottomTitlePanel.add(orderItemBottomTotalTitleLabel);
		/********************************
		 * orderBottomTitlePanel end
		 ******************************/

		southMenuPanel = new JPanel();
		southMenuPanel.setBackground(Color.WHITE);
		FlowLayout fl_southMenuPanel = (FlowLayout) southMenuPanel.getLayout();
		fl_southMenuPanel.setHgap(25);
		contentPane.add(southMenuPanel, BorderLayout.SOUTH);

		JButton searchButton = new JButton("검색");
		searchButton.setHorizontalTextPosition(SwingConstants.CENTER);
		searchButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		searchButton.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/images/search_dog50.png")));
		searchButton.setOpaque(false);
		searchButton.setBorderPainted(false);
		searchButton.setBackground(Color.WHITE);
		southMenuPanel.add(searchButton);

		JButton homeButton = new JButton("홈");
		homeButton.setHorizontalTextPosition(SwingConstants.CENTER);
		homeButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/***** 하단메뉴홈버튼클릭시인기견보여주기 *******/
				shopTabbedPane.setSelectedIndex(0);
			}
		});
		homeButton.setBackground(Color.WHITE);
		homeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		homeButton.setBorderPainted(false);
		homeButton.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/images/shoppingmall.png")));
		homeButton.setOpaque(false);
		southMenuPanel.add(homeButton);

		JButton userButton = new JButton("MY");
		userButton.setHorizontalTextPosition(SwingConstants.CENTER);
		userButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		userButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/***** 하단메뉴 사용자버튼클릭시 로그인 혹은 사용자정보 보여주기 *******/
					 /*************1.로그인다이알로그 보여주기(로그인전)******** 
							
					 /************** 2.로그인한사용자 정보 보여주기(로그인후)*************/
			}
		});
		userButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		userButton.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/images/user.png")));
		userButton.setOpaque(false);
		userButton.setBorderPainted(false);
		userButton.setBackground(Color.WHITE);
		southMenuPanel.add(userButton);

		northMenuPanel = new JPanel();
		northMenuPanel.setPreferredSize(new Dimension(10, 35));
		northMenuPanel.setBackground(Color.WHITE);
		contentPane.add(northMenuPanel, BorderLayout.NORTH);
		northMenuPanel.setLayout(null);

		searchTextField = new JTextField();
		searchTextField.setPreferredSize(new Dimension(10, 21));
		searchTextField.setBounds(79, 2, 185, 29);
		northMenuPanel.add(searchTextField);
		searchTextField.setColumns(10);

		cartButton = new JButton("");
		cartButton.setVerticalAlignment(SwingConstants.BOTTOM);
		cartButton.setHorizontalAlignment(SwingConstants.LEFT);
		cartButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/***** 카트버튼 클릭시 카트정보 보여주기 *******/
					// 1.로그인 안했을때
				    /********** 로그인창보여주기***************/
					// 2.로그인 했을때
					/********** 카트리스트보여주기 *******/
			}
		});
		cartButton.setOpaque(false);
		cartButton.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/images/cart25.png")));
		cartButton.setBorderPainted(false);
		cartButton.setBackground(Color.WHITE);
		cartButton.setBounds(312, 1, 45, 35);
		northMenuPanel.add(cartButton);

		cartCountLabel = new JLabel("");
		cartCountLabel.setVerticalAlignment(SwingConstants.TOP);
		cartCountLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
		cartCountLabel.setHorizontalAlignment(SwingConstants.LEFT);
		cartCountLabel.setOpaque(true);
		cartCountLabel.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/images/0.png")));
		cartCountLabel.setBackground(Color.WHITE);
		cartCountLabel.setBounds(357, 0, 20, 13);
		northMenuPanel.add(cartCountLabel);

		JLabel mainLogoLabel = new JLabel("");
		mainLogoLabel.setBackground(Color.WHITE);
		mainLogoLabel.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/images/main_logo60X25.png")));
		mainLogoLabel.setBounds(0, 0, 76, 35);
		northMenuPanel.add(mainLogoLabel);

		JLabel searchImageLabel = new JLabel("");
		searchImageLabel.setBackground(Color.WHITE);
		searchImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		searchImageLabel.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/images/search_image20.png")));
		searchImageLabel.setBounds(264, 2, 32, 29);
		northMenuPanel.add(searchImageLabel);

		/************ Service객체멤버변수초기화 ************/
		
		/*************************************************/
		try {
			/************ 인기견리스트보여주기 ************/
			/************ 소형견리스트보여주기 ************/
			/************ 중형견리스트보여주기 ************/
			/************ 대형견리스트보여주기 ************/
		
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}// 생성자끝

	/********************** 주문리스트보여주기 ****************/
	public void displayOrderList() throws Exception {
		
	}

	/********************** 로그인한유저의카트리스트보여주기 ****************/
	public void displayCartList() throws Exception {
		
	}

	/*********** 로그인다이알로그에서 로그인성공시 호출하는 메쏘드 ****************/
	public void loginProcess(Member loginUser) throws Exception {
		/*
		 * 1. ShopMainFrame의멤버변수에 로그인한User객체를 저장 
		 * 2. ShopMainFrame의 타이틀변경 
		 * 3. 카트의 수량아이콘변경
		 */
		
	}
	/*********** 로그아웃시 호출하는 메쏘드 ****************/
	public void logoutProcess() throws Exception {
		/*
		 * 1. ShopMainFrame의멤버변수에 로그인한User객삭제 
		 * 2. ShopMainFrame의 타이틀변경 
		 * 3. 카트탭불활성화
		 */
		
	}

	/*********************** 인기견보여주기 *******************************/
	public void displayProductPopularDogList() throws Exception {
	
	}
	/*********************** 소형견보여주기 *******************************/
	public void displayProductSmallDogList() throws Exception {
		
	}

	/*********************** 중형견보여주기 *******************************/
	public void displayProductMiddleDogList() throws Exception {
		
	}

	/*********************** 대형견보여주기 *******************************/
	public void displayProductBigDogList() throws Exception {
	}
}// end class
