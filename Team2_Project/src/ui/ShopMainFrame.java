package ui;

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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import bbangshuttle.cart.Cart;
import bbangshuttle.cart.CartService;
import bbangshuttle.member.Member;
import bbangshuttle.member.MemberService;
import bbangshuttle.order.Order;
import bbangshuttle.order.OrderService;
import bbangshuttle.product.Product;
import bbangshuttle.product.ProductDao;
import bbangshuttle.product.ProductService;

public class ShopMainFrame extends JFrame {
	/************ 1.Service객체멤버변수선언 ************/
	private ProductService productService;
	private MemberService memberService;
	private CartService cartService;
	private OrderService orderService;
	/**************************************************/

	/***** 로그인한Member객체저장할 Member객체선언 **********/
	private Member loginMember = null;
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
	private JPanel productPopularContentPanel;
	private JPanel cartContentPanel;
	private JPanel cartPanel;
	private JScrollPane cartContentPanelScrollPane;
	private JTextField nameTextField;
	private JTextField emailTextField;
	private JTextField idTextField;
	private JTextField MemberLoginIdTF;
	private JTextField MemberLoginPasswordTF;
	private JTabbedPane MemberTabbedPane;
	private JTextField MemberJoinNameTF;
	private JTextField MemberJoinEmailTF;
	private JLabel loginPasswordMessageLabel;
	private JLabel MemberLoginPasswordLabel;
	private JLabel MemberLoginIdMessageLabel;
	private JTextField MemberJoinIdTF;
	private JTextField MemberJoinPasswordTF;
	private JLabel MemberJoinIdMsgLB;
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
		//setIconImage(Toolkit.getDefaultToolkit().getImage(ShopMainFrame.class.getResource("/images/die.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 410, 740);
		setBounds(100, 100, 410, 741);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		shopTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		shopTabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				 JTabbedPane sourceTabbedPane = (JTabbedPane)e.getSource();
			     int index = sourceTabbedPane.getSelectedIndex();
			     switch (index) {
				case 0:
					break;
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					if(loginMember!=null) {
						try {
							displayOrderList();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					break;
				default:
					break;
				}
			}
		});
		shopTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		shopTabbedPane.setForeground(Color.DARK_GRAY);
		shopTabbedPane.setBackground(Color.WHITE);
		shopTabbedPane.setFont(new Font("D2Coding", Font.BOLD, 12));
		shopTabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		contentPane.add(shopTabbedPane, BorderLayout.CENTER);

		
		JPanel productPupularPanel = new JPanel();
		productPupularPanel.setBackground(Color.WHITE);
//		shopTabbedPane.addTab("인기견", new ImageIcon(ShopMainFrame.class.getResource("/images/title_logo20.png")),
//				productPupularDogPanel, null);
		shopTabbedPane.addTab("", new ImageIcon(ShopMainFrame.class.getResource("/bbangshuttle/images/die.jpg")),
				productPupularPanel, null);
		shopTabbedPane.setBackgroundAt(0, Color.WHITE);
		productPupularPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane productPopularContentPanelScrollPane = new JScrollPane();
		productPopularContentPanelScrollPane.setBackground(Color.WHITE);
		productPopularContentPanelScrollPane.setPreferredSize(new Dimension(390, 780));
		productPopularContentPanelScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		productPupularPanel.add(productPopularContentPanelScrollPane, BorderLayout.CENTER);

		productPopularContentPanel = new JPanel();
		productPopularContentPanel.setPreferredSize(new Dimension(390, 780));
		productPopularContentPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		productPopularContentPanel.setBackground(Color.WHITE);
		productPopularContentPanelScrollPane.setViewportView(productPopularContentPanel);
		productPopularContentPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 7, 7));

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

		productImageLabel.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/bbangshuttle/images/latte.jpg")));
		productImageLabel.setBounds(3, 1, 162, 116);
		productPanel.add(productImageLabel);

		JLabel productDescLabel = new JLabel("<html>\r\n\t<font size='3'>\r\n\t\t\r\n\t\t\t" + "상품명: 라뗴<br>\r\n\t\t\t"
				+ "가격: 6,000<br>\r\n\t\t\t" + "설명: 라뗴는말이야~\r\n\t\t\r\n </font></html>");
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
		cartAddButton.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/bbangshuttle/images/cart20.png")));
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

		productPopularContentPanel.add(productPanel);
		/************* 제품1개UI패널 디자인끝 *******************/

		JPanel productSmallDogPanel = new JPanel();
		productSmallDogPanel.setBackground(Color.WHITE);
//		shopTabbedPane.addTab("소형견", new ImageIcon(ShopMainFrame.class.getResource("/images/small_dog20.png")),
//				productSmallDogPanel, null);
		shopTabbedPane.addTab("", new ImageIcon(ShopMainFrame.class.getResource("/bbangshuttle/images/die.jpg")),
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
		shopTabbedPane.addTab("", new ImageIcon(ShopMainFrame.class.getResource("/bbangshuttle/images/die.jpg")),
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
		shopTabbedPane.addTab("", new ImageIcon(ShopMainFrame.class.getResource("/bbangshuttle/images/die.jpg")),
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
		shopTabbedPane.addTab("", new ImageIcon(ShopMainFrame.class.getResource("/bbangshuttle/images/die.jpg")), cartPanel,
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
		cartItemImageLabel.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/bbangshuttle/images/die.jpg")));
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
		cartItemDeleteButton.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/bbangshuttle/images/die.jpg")));
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

		JPanel MemberPanel = new JPanel();
		MemberPanel.setBackground(Color.WHITE);
		MemberPanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {

			}
		});
//		shopTabbedPane.addTab("회원", new ImageIcon(ShopMainFrame.class.getResource("/images/Member20.png")), MemberPanel,
//				null);
		shopTabbedPane.addTab("", new ImageIcon(ShopMainFrame.class.getResource("/bbangshuttle/images/die.jpg")), MemberPanel,
				null);
		shopTabbedPane.setEnabledAt(5, true);
		shopTabbedPane.setBackgroundAt(5, Color.WHITE);
		MemberPanel.setLayout(new BorderLayout(0, 0));

		MemberTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		MemberPanel.add(MemberTabbedPane, BorderLayout.CENTER);

		JPanel MemberLoginPanel = new JPanel();
		MemberTabbedPane.addTab("로그인", null, MemberLoginPanel, null);
		MemberLoginPanel.setLayout(null);

		MemberLoginIdTF = new JTextField();
		MemberLoginIdTF.setText("guard1");
		MemberLoginIdTF.setBounds(102, 92, 240, 21);
		MemberLoginPanel.add(MemberLoginIdTF);
		MemberLoginIdTF.setColumns(10);

		MemberLoginPasswordTF = new JTextField();
		MemberLoginPasswordTF.setText("1111");
		MemberLoginPasswordTF.setBounds(102, 149, 240, 21);
		MemberLoginPanel.add(MemberLoginPasswordTF);
		MemberLoginPasswordTF.setColumns(10);

		MemberLoginPasswordLabel = new JLabel("패쓰워드");
		MemberLoginPasswordLabel.setBounds(12, 152, 57, 15);
		MemberLoginPanel.add(MemberLoginPasswordLabel);

		JLabel MemberLoginIdLabel = new JLabel("아이디");
		MemberLoginIdLabel.setBounds(12, 95, 57, 15);
		MemberLoginPanel.add(MemberLoginIdLabel);

		JButton MemberLoginLoginBtn = new JButton("로그인");
		MemberLoginLoginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/******** 로그인버튼클릭시 *********/
				// 1.입력유효성체크
				String Memberid = MemberLoginIdTF.getText();
				String password = MemberLoginPasswordTF.getText();
				if (Memberid.equals("")) {
					MemberLoginIdMessageLabel.setText("아이디를 입력하세요.");
					MemberLoginIdTF.requestFocus();
					return;
				}
				if (password.equals("")) {
					loginPasswordMessageLabel.setText("패쓰워드를 입력하세요.");
					MemberLoginPasswordTF.requestFocus();
					return;
				}
				// 2.MemberService.login 메쏘드호출
				/*********** 2.MemberService.login 메쏘드호출 **************/
				try {

					int result = memberService.login(Memberid, password);
					if (result == 1) {
						/*
						 * 로그인성공 
						 * 	- 성공한아이디로 Member정보얻기 
						 * 	- ShopMainFrame에 Member객체 넘겨주기(ShopMainFrame객체의 메소드호출시 인자로 넘겨주기) 
						 * 	- 로그인창닫기
						 */
						Member loginMember = memberService.memberDetail(password);
						loginProcess(loginMember);

					} else if (result == 0) {
						// 로그인실패
						MemberLoginIdMessageLabel.setText("아이디또는비밀번호가 일치하지않습니다.");
						MemberLoginIdTF.requestFocus();
						MemberLoginIdTF.setSelectionStart(0);
						MemberLoginIdTF.setSelectionEnd(Memberid.length());
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				/******************************************************/
			}
		});
		MemberLoginLoginBtn.setBounds(28, 224, 140, 23);
		MemberLoginPanel.add(MemberLoginLoginBtn);

		MemberLoginIdMessageLabel = new JLabel("");
		MemberLoginIdMessageLabel.setBounds(102, 119, 240, 20);
		MemberLoginPanel.add(MemberLoginIdMessageLabel);

		loginPasswordMessageLabel = new JLabel("");
		loginPasswordMessageLabel.setBounds(102, 180, 240, 20);
		MemberLoginPanel.add(loginPasswordMessageLabel);

		JLabel MemberInfoTitleLabel = new JLabel("로그인");
		MemberInfoTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MemberInfoTitleLabel.setFont(new Font("굴림", Font.BOLD, 22));
		MemberInfoTitleLabel.setBounds(28, 39, 314, 29);
		MemberLoginPanel.add(MemberInfoTitleLabel);

		JButton MemberLoginJoinBtn = new JButton("가입");
		MemberLoginJoinBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemberTabbedPane.setSelectedIndex(1);
			}
		});
		MemberLoginJoinBtn.setBounds(202, 224, 140, 23);
		MemberLoginPanel.add(MemberLoginJoinBtn);

		JPanel MemberJoinPanel = new JPanel();
		MemberTabbedPane.addTab("회원가입", null, MemberJoinPanel, null);
		MemberJoinPanel.setLayout(null);

		JLabel MemberJoinNameLabel = new JLabel("이름");
		MemberJoinNameLabel.setBounds(29, 208, 61, 15);
		MemberJoinPanel.add(MemberJoinNameLabel);

		MemberJoinNameTF = new JTextField();
		MemberJoinNameTF.setColumns(10);
		MemberJoinNameTF.setBounds(102, 205, 238, 21);
		MemberJoinPanel.add(MemberJoinNameTF);

		JLabel MemberJoinEmailLabel = new JLabel("이메일");
		MemberJoinEmailLabel.setBounds(29, 259, 61, 15);
		MemberJoinPanel.add(MemberJoinEmailLabel);

		MemberJoinEmailTF = new JTextField();
		MemberJoinEmailTF.setColumns(10);
		MemberJoinEmailTF.setBounds(102, 256, 238, 21);
		MemberJoinPanel.add(MemberJoinEmailTF);

		JLabel MemberJoinIdLabel = new JLabel("아이디");
		MemberJoinIdLabel.setBounds(27, 112, 64, 15);
		MemberJoinPanel.add(MemberJoinIdLabel);

		MemberJoinIdTF = new JTextField();
		MemberJoinIdTF.setColumns(10);
		MemberJoinIdTF.setBounds(103, 109, 237, 21);
		MemberJoinPanel.add(MemberJoinIdTF);

		JLabel MemberJoinPasswordLabel = new JLabel("패쓰워드");
		MemberJoinPasswordLabel.setBounds(29, 164, 61, 15);
		MemberJoinPanel.add(MemberJoinPasswordLabel);

		MemberJoinPasswordTF = new JTextField();
		MemberJoinPasswordTF.setColumns(10);
		MemberJoinPasswordTF.setBounds(103, 161, 237, 21);
		MemberJoinPanel.add(MemberJoinPasswordTF);

		JLabel MemberJoinTitleLabel = new JLabel("회원가입");
		MemberJoinTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MemberJoinTitleLabel.setFont(new Font("굴림", Font.BOLD, 22));
		MemberJoinTitleLabel.setBounds(29, 56, 314, 29);
		MemberJoinPanel.add(MemberJoinTitleLabel);

		JButton MemberJoinBtn = new JButton("가입");
		MemberJoinBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String MemberId = MemberJoinIdTF.getText();
					boolean isDuplicate = memberService.isDuplicatedId(MemberId);
					if (isDuplicate) {
						MemberJoinIdTF.setSelectionStart(0);
						MemberJoinIdTF.setSelectionEnd(MemberId.length());
						MemberJoinIdTF.requestFocus();
						MemberJoinIdMsgLB.setText("아이디가 사용중입니다.");
						return;
					}
					String password = MemberJoinPasswordTF.getText();
					String name = MemberJoinNameTF.getText();
					String email = MemberJoinEmailTF.getText();
					Member member = new Member(MemberId, password, MemberId, MemberId, MemberId, MemberId, MemberId, null, 0);
					memberService.addMember(member);
					MemberTabbedPane.setSelectedIndex(0);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		MemberJoinBtn.setBounds(74, 306, 97, 23);
		MemberJoinPanel.add(MemberJoinBtn);

		JButton MemberJoinCancleBtn = new JButton("취소");
		MemberJoinCancleBtn.setBounds(221, 306, 97, 23);
		MemberJoinPanel.add(MemberJoinCancleBtn);

		MemberJoinIdMsgLB = new JLabel("");
		MemberJoinIdMsgLB.setForeground(Color.RED);
		MemberJoinIdMsgLB.setBounds(113, 136, 227, 15);
		MemberJoinPanel.add(MemberJoinIdMsgLB);

		JPanel MemberInfoPanel = new JPanel();
		MemberInfoPanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				idTextField.setText(loginMember.getMemberId());
				nameTextField.setText(loginMember.getMemberName());
				emailTextField.setText(loginMember.getMemberEmail());
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
				idTextField.setText("");
				nameTextField.setText("");
				emailTextField.setText("");
			}
		});
		MemberTabbedPane.addTab("회원정보", null, MemberInfoPanel, null);
		MemberTabbedPane.setEnabledAt(2, false);
		MemberInfoPanel.setLayout(null);

		JLabel nameLabel = new JLabel("이름");
		nameLabel.setBounds(71, 173, 57, 15);
		MemberInfoPanel.add(nameLabel);

		JLabel emailLabel = new JLabel("이메일");
		emailLabel.setBounds(71, 213, 57, 15);
		MemberInfoPanel.add(emailLabel);

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
		MemberInfoPanel.add(logoutButton);

		nameTextField = new JTextField();
		nameTextField.setEditable(false);
		nameTextField.setColumns(10);
		nameTextField.setBounds(172, 170, 116, 21);
		MemberInfoPanel.add(nameTextField);

		emailTextField = new JTextField();
		emailTextField.setEditable(false);
		emailTextField.setColumns(10);
		emailTextField.setBounds(172, 210, 116, 21);
		MemberInfoPanel.add(emailTextField);

		JLabel idLabel = new JLabel("아이디");
		idLabel.setBounds(71, 129, 57, 15);
		MemberInfoPanel.add(idLabel);

		idTextField = new JTextField();
		idTextField.setEnabled(false);
		idTextField.setEditable(false);
		idTextField.setColumns(10);
		idTextField.setBounds(172, 126, 116, 21);
		MemberInfoPanel.add(idTextField);

		JLabel MemberInfoLabel = new JLabel("회원정보");
		MemberInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MemberInfoLabel.setFont(new Font("굴림", Font.BOLD, 22));
		MemberInfoLabel.setBounds(38, 35, 314, 29);
		MemberInfoPanel.add(MemberInfoLabel);

		JPanel orderPanel = new JPanel();
		
		
//		shopTabbedPane.addTab("주문", new ImageIcon(ShopMainFrame.class.getResource("/images/checklist20.png")),
//				orderPanel, null);
		shopTabbedPane.addTab("", new ImageIcon(ShopMainFrame.class.getResource("/bbangshuttle/images/die.jpg")),
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
		orderItemImageLabel.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/bbangshuttle/images/die.jpg")));
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

		

		JButton homeButton = new JButton("홈");
		homeButton.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/bbangshuttle/images/big_dog20.png")));
		homeButton.setHorizontalTextPosition(SwingConstants.CENTER);
		homeButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				shopTabbedPane.setSelectedIndex(0);
			}
		});
		homeButton.setBackground(Color.WHITE);
		homeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		homeButton.setBorderPainted(false);
		//homeButton.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/bbangshuttle/images/die.jpg")));
		homeButton.setOpaque(false);
		southMenuPanel.add(homeButton);

		JButton MemberButton = new JButton("MY");
		MemberButton.setHorizontalTextPosition(SwingConstants.CENTER);
		MemberButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		MemberButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/***** 하단메뉴 사용자버튼클릭시 로그인 혹은 사용자정보 보여주기 *******/
				if (loginMember == null) {
					 /*************
					  1.로그인다이알로그 보여주기******** 
					   LoginDialog loginDialog = new LoginDialog();
					   loginDialog.setVisible(true); 
					   // 로그인다이알로그의 메쏘드호출시 ShopMainFrame객체의 참조변수를 인자로대입 
					   loginDialog.setShopMainFrame(ShopMainFrame.this);
					 */
					shopTabbedPane.setSelectedIndex(5);
					MemberTabbedPane.setSelectedIndex(0);

				} else {
					/************** 2.로그인한사용자 정보 보여주기 *************/
					shopTabbedPane.setSelectedIndex(5);
					idTextField.setText(loginMember.getMemberId());
					nameTextField.setText(loginMember.getMemberName());
					emailTextField.setText(loginMember.getMemberEmail());

				}

			}
		});
		MemberButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		MemberButton.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/bbangshuttle/images/cart20.png")));
		MemberButton.setOpaque(false);
		MemberButton.setBorderPainted(false);
		MemberButton.setBackground(Color.WHITE);
		southMenuPanel.add(MemberButton);

		northMenuPanel = new JPanel();
		northMenuPanel.setPreferredSize(new Dimension(10, 35));
		northMenuPanel.setBackground(Color.WHITE);
		contentPane.add(northMenuPanel, BorderLayout.NORTH);
		northMenuPanel.setLayout(null);

		searchTextField = new JTextField();
		searchTextField.setPreferredSize(new Dimension(10, 21));
		searchTextField.setBounds(79, 2, 160, 29);
		northMenuPanel.add(searchTextField);
		searchTextField.setColumns(10);

		cartButton = new JButton("카트");
		cartButton.setHorizontalAlignment(SwingConstants.LEFT);
		cartButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (loginMember == null) {
					// 1.로그인 안했을때
					/*
					 * LoginDialog loginDialog = new LoginDialog();
					 * loginDialog.setShopMainFrame(ShopMainFrame.this);
					 * loginDialog.setVisible(true);
					 */
					shopTabbedPane.setSelectedIndex(5);
					MemberTabbedPane.setSelectedIndex(0);
				} else {
					// 2.로그인 했을때
					/********** 카트리스트보여주기 *******/
					try {
						// 카트패널보여주기
						shopTabbedPane.setSelectedIndex(4);
						// 카트리스트보여주기
						displayCartList();

					} catch (Exception e1) {
						e1.printStackTrace();
					}
					/**********************************/
				}
			}
		});
		cartButton.setOpaque(false);
		//cartButton.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/bbangshuttle/images/cart50.jpg")));
		cartButton.setBorderPainted(false);
		cartButton.setBackground(Color.WHITE);
		cartButton.setBounds(326, 0, 68, 35);
		northMenuPanel.add(cartButton);

		JLabel mainLogoLabel = new JLabel("");
		mainLogoLabel.setBackground(Color.WHITE);
		mainLogoLabel.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/bbangshuttle/images/main_logo76X35.png")));
		mainLogoLabel.setBounds(0, 0, 76, 35);
		northMenuPanel.add(mainLogoLabel);
		
		JButton searchButton = new JButton("검색");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyword = searchTextField.getText();
				try {
					productService.ProductFindByKetword(keyword);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		searchButton.setBounds(275, 3, 55, 29);
		northMenuPanel.add(searchButton);

		/************ Service객체멤버변수초기화 ************/
		productService = new ProductService();
		memberService = new MemberService();
		cartService = new CartService();
		orderService = new OrderService();
		/*************************************************/
		

	}// 생성자끝

	/********************** 주문리스트보여주기 ****************/
	public void displayOrderList() throws Exception {
		List<Order> orderList= orderService.orderWithOrderItemList(loginMember.getMemberId());
		System.out.println(orderList);
		orderContentPanel.removeAll();
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
		for (int i = 0; i < 5; i++) {
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
			orderItemImageLabel.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/bbangshuttle/images/die.jpg")));
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
		}

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
	}

	/********************** 로그인한유저의카트리스트보여주기 ****************/
	public void displayCartList() throws Exception {
		// 카트목록삭제
		cartContentPanel.removeAll();
		List<Cart> cartList = cartService.getCartItemByUserId(loginMember.getMemberId());
		for (Cart cart : cartList) {
			/**************** cartItemPanel[카트아이템1개Panel] **************/
			JPanel cartItemPanel = new JPanel();
			cartItemPanel.setBorder(null);
			cartItemPanel.setBackground(Color.WHITE);
			cartItemPanel.setPreferredSize(new Dimension(350, 72));
			cartItemPanel.setLayout(null);

			JLabel cartItemImageLabel = new JLabel(cart.getProduct().getP_name());
			cartItemImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
			cartItemImageLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
			cartItemImageLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			cartItemImageLabel.setIcon(
					new ImageIcon(ShopMainFrame.class.getResource("/bbangshuttle/images/"+ cart.getProduct().getP_image())));
			cartItemImageLabel.setBounds(0, 0, 69, 72);
			cartItemPanel.add(cartItemImageLabel);

			JLabel cartItemDescLabel = new JLabel(new DecimalFormat("#,###").format(cart.getProduct().getPrice()));
			cartItemDescLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			cartItemDescLabel.setFont(new Font("D2Coding", Font.PLAIN, 13));
			cartItemDescLabel.setBounds(61, 25, 74, 23);
			cartItemPanel.add(cartItemDescLabel);

			JButton cartItemDeleteButton = new JButton("");
			cartItemDeleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			cartItemDeleteButton.addActionListener(new ActionListener() {
				private Cart c = cart;
				JPanel cPanel = cartItemPanel;

				public void actionPerformed(ActionEvent e) {

					/************** 카트아이템1개삭제 ******************/
					try {
						// 카트아이템삭제
						int deleteCartItemCount = cartService.deleteCartItemByCartNo(c.getCart_no());
						JOptionPane.showMessageDialog(null, deleteCartItemCount + " 개의 제품이 삭제되었습니다.");
						// cart수량 아이콘변경하기
						int cartItemCount = cartService.getCartItemByUserId(loginMember.getMemberId()).size();
						cartCountLabel.setIcon(
								new ImageIcon(ShopMainFrame.class.getResource("/bbangshuttle/images/die.jpg")));
						// 카트리스트보여주기
						displayCartList();
						shopTabbedPane.setSelectedIndex(0);
						shopTabbedPane.setSelectedIndex(4);

					} catch (Exception ex) {
						ex.printStackTrace();
					}
					/*************************************************/

				}
			});

			cartItemDeleteButton.setBorder(null);
			cartItemDeleteButton.setBackground(Color.WHITE);
			cartItemDeleteButton
					.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/bbangshuttle/images/die.jpg")));
			cartItemDeleteButton.setBounds(335, 27, 15, 17);
			cartItemPanel.add(cartItemDeleteButton);

			JLabel cartItemTotPrice = new JLabel(
					new DecimalFormat("#,###").format(cart.getProduct().getPrice() * cart.getCart_qty()));
			cartItemTotPrice.setFont(new Font("D2Coding", Font.PLAIN, 13));
			cartItemTotPrice.setHorizontalAlignment(SwingConstants.RIGHT);
			cartItemTotPrice.setBounds(187, 25, 69, 22);
			cartItemPanel.add(cartItemTotPrice);

			JComboBox cartItemQtyComboBox = new JComboBox();
			cartItemQtyComboBox.setModel(
					new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
			cartItemQtyComboBox.setBounds(141, 24, 39, 23);
			cartItemQtyComboBox.setSelectedItem(cart.getCart_qty() + "");
			cartItemPanel.add(cartItemQtyComboBox);

			cartItemQtyComboBox.addItemListener(new ItemListener() {
				private Cart c = cart;

				public void itemStateChanged(ItemEvent e) {
					/*** 수량변경시 선택될때마다호출 ***/
					if (e.getStateChange() == ItemEvent.SELECTED) {
						/**************** 카트수량수정 ******************/
						try {
							String cartSelectedQtyStr = (String) cartItemQtyComboBox.getSelectedItem();
							int cartSelectedQty = Integer.parseInt(cartSelectedQtyStr);
							int updateCartCount = cartService.updateCart(c.getCart_no(), cartSelectedQty);
							// 카트아이템총가격변경
							cartItemTotPrice.setText(
									new DecimalFormat("#,###").format(c.getProduct().getPrice() * cartSelectedQty));

						} catch (Exception ex) {
							ex.printStackTrace();
						}
						/**********************************************/
					}
				}
			});

			JButton cartItemUpdateButton = new JButton("수정");
			cartItemUpdateButton.setPreferredSize(new Dimension(57, 23));
			cartItemUpdateButton.setFont(new Font("D2Coding", Font.BOLD, 11));
			cartItemUpdateButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

				}
			});
			cartItemUpdateButton.setBounds(268, 23, 59, 27);
			cartItemPanel.add(cartItemUpdateButton);
			cartContentPanel.add(cartItemPanel);
			/***************************************************************/

		}
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setPreferredSize(new Dimension(350, 15));
		cartContentPanel.add(lblNewLabel);

		JButton cartOrderButton = new JButton("주문하기");
		cartOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("주문~~~~~~~~~");
					orderService.orderWithOrderItemList(loginMember.getMemberId());
					// 카트패널보여주기
					shopTabbedPane.setSelectedIndex(5);
					// 카트리스트보여주기
					displayCartList();
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
		cartOrderButton.setBorder(new LineBorder(UIManager.getColor("Button.shadow")));
		cartOrderButton.setBackground(UIManager.getColor("Button.light"));
		cartOrderButton.setFont(new Font("D2Coding", Font.PLAIN, 17));
		cartOrderButton.setPreferredSize(new Dimension(350, 40));
		cartContentPanel.add(cartOrderButton);

	}

	/*********** 로그인다이알로그에서 로그인성공시 호출하는 메쏘드 ****************/
	public void loginProcess(Member loginMember) throws Exception {
		/*
		 * 1. ShopMainFrame의멤버변수에 로그인한Member객체를 저장 2. ShopMainFrame의 타이틀변경 3. 카트의 수량아이콘변경
		 */
		this.loginMember = loginMember;
		setTitle(loginMember.getMemberName());
		int cartItemCount = cartService.getCartItemByUserId(loginMember.getMemberId()).size();
		cartCountLabel.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/bbangshuttle/images/die.jpg")));
		shopTabbedPane.setEnabledAt(4, true);
		shopTabbedPane.setSelectedIndex(0);

		MemberTabbedPane.setEnabledAt(0, false);
		MemberTabbedPane.setEnabledAt(1, false);
		MemberTabbedPane.setEnabledAt(2, true);
		MemberTabbedPane.setSelectedIndex(2);
	}

	public void logoutProcess() throws Exception {
		/*
		 * 1. ShopMainFrame의멤버변수에 로그인한Member객삭제 
		 * 2. ShopMainFrame의 타이틀변경 
		 * 3. 카트탭불활성화
		 */
		this.loginMember = null;
		setTitle("");
		int cartItemCount = 0;
		cartCountLabel.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/bbangshuttle/images/die.jpg")));

		shopTabbedPane.setEnabledAt(4, false);
		
		MemberTabbedPane.setEnabledAt(0, true);
		MemberTabbedPane.setEnabledAt(1, true);
		MemberTabbedPane.setEnabledAt(2, false);
		
		MemberTabbedPane.setSelectedIndex(0);
		shopTabbedPane.setSelectedIndex(0);
	}

	/*********************** 인기견보여주기 *******************************/
	public void displayProductPopularDogList() throws Exception {
		productPopularContentPanel.removeAll();
		List<Product> productList = productService.ProductFindByAll();
		Collections.shuffle(productList);
		for (Product product : productList) {
			/************* 제품1개UI디자인 *******************/
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

			productImageLabel
					.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/bbangshuttle/images/" + product.getP_image())));
			productImageLabel.setBounds(3, 1, 162, 116);
			productPanel.add(productImageLabel);

			JLabel productDescLabel = new JLabel("<html><font size='3'>" + "상품이: " + product.getP_name() + "<br>"
					+ "가격: " + new DecimalFormat("#,###").format(product.getPrice()) + "<br>" + "설명: "
					+ product.getP_desc() + "</font></html>");

			productDescLabel.setVerticalAlignment(SwingConstants.TOP);
			productDescLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			productDescLabel.setHorizontalAlignment(SwingConstants.LEFT);
			productDescLabel.setBounds(3, 143, 164, 47);
			productPanel.add(productDescLabel);

			JComboBox cartQtyComboBox = new JComboBox();
			cartQtyComboBox.setAutoscrolls(true);
			cartQtyComboBox.setModel(
					new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
			cartQtyComboBox.setOpaque(false);
			cartQtyComboBox.setBorder(null);
			cartQtyComboBox.setBackground(Color.WHITE);
			cartQtyComboBox.setBounds(99, 119, 33, 23);
			cartQtyComboBox.setMaximumRowCount(cartQtyComboBox.getModel().getSize());
			productPanel.add(cartQtyComboBox);

			JButton cartAddButton = new JButton("");
			cartAddButton.addActionListener(new ActionListener() {
				private Product p = product;

				public void actionPerformed(ActionEvent e) {

					if (loginMember != null) {
						// 로그인했을때
						/****************** 카트에담기 ***************/
						try {
							String cartQtyStr = (String) cartQtyComboBox.getSelectedItem();
							int cartQty = Integer.parseInt(cartQtyStr);
							Cart addCartItem = new Cart(0, Integer.parseInt((String)cartQtyComboBox.getSelectedItem()),loginMember.getMemberId(),
									new Product(p.getP_no(), p.getP_name(), p.getP_desc(), p.getP_image(), p.getPrice(), p.getP_view_count(), p.getP_category()));

							int addCount = cartService.addCart(loginMember.getMemberId(), p.getP_no(), cartQty);
							// 카트수량아이콘변경
							int cartItemCount = cartService.getCartItemByUserId(loginMember.getMemberId()).size();
							cartCountLabel.setIcon(new ImageIcon(
									ShopMainFrame.class.getResource("/bbangshuttle/images/die.jpg")));
							// 메세지출력
							JOptionPane.showMessageDialog(null, "장바구니에 제품이담겼습니다");

						} catch (Exception ex) {
							ex.printStackTrace();
						}
						/*******************************************/

					} else {
//						// 로그인안했을때
						/****************** 로그인다이알로그뛰우기 ***************/
						LoginDialog loginDialog;
						try {
							loginDialog = new LoginDialog();
							loginDialog.setShopMainFrame(ShopMainFrame.this);
							loginDialog.setVisible(true);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}

				}
			});
			cartAddButton.setBorder(null);
			cartAddButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			cartAddButton.setOpaque(false);
			cartAddButton.setBackground(Color.WHITE);
			cartAddButton.setForeground(Color.WHITE);
			cartAddButton.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/bbangshuttle/images/die.jpg")));
			cartAddButton.setBounds(136, 119, 31, 23);
			productPanel.add(cartAddButton);

			productPopularContentPanel.add(productPanel);
			/*******************************/

		}

	}

	/*********************** 소형견보여주기 *******************************/
	public void displayProductSmallDogList() throws Exception {
		List<Product> productList = productService.ProductFindByAll();
		Collections.shuffle(productList);

		for (int i = 0; i < productList.size() - 4; i++) {
			Product product = productList.get(i);
			/************* 제품1개UI디자인 *******************/
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

			productImageLabel
					.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/bbangshuttle/images/" + product.getP_image())));
			productImageLabel.setBounds(3, 1, 162, 116);
			productPanel.add(productImageLabel);

			JLabel productDescLabel = new JLabel("<html><font size='3'>" + "견종: " + product.getP_name() + "<br>"
					+ "가격: " + new DecimalFormat("#,###").format(product.getPrice()) + "<br>" + "설명: "
					+ product.getP_desc() + "</font></html>");

			productDescLabel.setVerticalAlignment(SwingConstants.TOP);
			productDescLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			productDescLabel.setHorizontalAlignment(SwingConstants.LEFT);
			productDescLabel.setBounds(3, 143, 164, 47);
			productPanel.add(productDescLabel);

			JComboBox cartQtyComboBox = new JComboBox();
			cartQtyComboBox.setAutoscrolls(true);
			cartQtyComboBox.setModel(
					new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
			cartQtyComboBox.setOpaque(false);
			cartQtyComboBox.setBorder(null);
			cartQtyComboBox.setBackground(Color.WHITE);
			cartQtyComboBox.setBounds(99, 119, 33, 23);
			cartQtyComboBox.setMaximumRowCount(cartQtyComboBox.getModel().getSize());
			productPanel.add(cartQtyComboBox);

			JButton cartAddButton = new JButton("");
			cartAddButton.addActionListener(new ActionListener() {
				private Product p = product;

				public void actionPerformed(ActionEvent e) {

					if (loginMember != null) {
						// 로그인했을때
						/****************** 카트에담기 ***************/
						try {
							String cartQtyStr = (String) cartQtyComboBox.getSelectedItem();
							int cartQty = Integer.parseInt(cartQtyStr);
							int addCount = cartService.addCart(loginMember.getMemberId(), p.getP_no(), cartQty);
							// 카트수량아이콘변경
							int cartItemCount = cartService.getCartItemByUserId(loginMember.getMemberId()).size();
							cartCountLabel.setIcon(new ImageIcon(
									ShopMainFrame.class.getResource("/bbangshuttle/images/" + cartItemCount + ".png")));
							// 메세지출력
							JOptionPane.showMessageDialog(null, "장바구니에 제품이담겼습니다");

						} catch (Exception ex) {
							ex.printStackTrace();
						}
						/*******************************************/

					} else {
						// 로그인안했을때
						/****************** 로그인다이알로그뛰우기 ***************/
						LoginDialog loginDialog;
						try {
							loginDialog = new LoginDialog();
							loginDialog.setShopMainFrame(ShopMainFrame.this);
							loginDialog.setVisible(true);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}

				}
			});
			cartAddButton.setBorder(null);
			cartAddButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			cartAddButton.setOpaque(false);
			cartAddButton.setBackground(Color.WHITE);
			cartAddButton.setForeground(Color.WHITE);
			cartAddButton.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/bbangshuttle/images/die.jpg")));
			cartAddButton.setBounds(136, 119, 31, 23);
			productPanel.add(cartAddButton);

			// productSmallDogContentPanel에 제품UI추가
			productSmallDogContentPanel.add(productPanel);
			/*******************************/

		}
	}

	/*********************** 중형견보여주기 *******************************/
	public void displayProductMiddleDogList() throws Exception {
		List<Product> productList = productService.ProductFindByAll();
		Collections.shuffle(productList);

		for (int i = 0; i < productList.size() - 5; i++) {
			Product product = productList.get(i);
			/************* 제품1개UI디자인 *******************/
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

			productImageLabel
					.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/bbangshuttle/images/" + product.getP_image())));
			productImageLabel.setBounds(3, 1, 162, 116);
			productPanel.add(productImageLabel);

			JLabel productDescLabel = new JLabel("<html><font size='3'>" + "견종: " + product.getP_name() + "<br>"
					+ "가격: " + new DecimalFormat("#,###").format(product.getPrice()) + "<br>" + "설명: "
					+ product.getP_desc() + "</font></html>");

			productDescLabel.setVerticalAlignment(SwingConstants.TOP);
			productDescLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			productDescLabel.setHorizontalAlignment(SwingConstants.LEFT);
			productDescLabel.setBounds(3, 143, 164, 47);
			productPanel.add(productDescLabel);

			JComboBox cartQtyComboBox = new JComboBox();
			cartQtyComboBox.setAutoscrolls(true);
			cartQtyComboBox.setModel(
					new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
			cartQtyComboBox.setOpaque(false);
			cartQtyComboBox.setBorder(null);
			cartQtyComboBox.setBackground(Color.WHITE);
			cartQtyComboBox.setBounds(99, 119, 33, 23);
			cartQtyComboBox.setMaximumRowCount(cartQtyComboBox.getModel().getSize());
			productPanel.add(cartQtyComboBox);

			JButton cartAddButton = new JButton("");
			cartAddButton.addActionListener(new ActionListener() {
				private Product p = product;

				public void actionPerformed(ActionEvent e) {

					if (loginMember != null) {
						// 로그인했을때
						/****************** 카트에담기 ***************/
						try {
							String cartQtyStr = (String) cartQtyComboBox.getSelectedItem();
							int cartQty = Integer.parseInt(cartQtyStr);
							Cart addCartItem = new Cart(0, cartQty,loginMember.getMemberId(),
									new Product(p.getP_no(), p.getP_name(), p.getP_desc(), p.getP_image(), p.getPrice(), p.getP_view_count(), p.getP_category()));

							int addCount = cartService.addCart(loginMember.getMemberId(), p.getP_no(), cartQty);
							// 카트수량아이콘변경
							int cartItemCount = cartService.getCartItemByUserId(loginMember.getMemberId()).size();
							cartCountLabel.setIcon(new ImageIcon(
									ShopMainFrame.class.getResource("/bbangshuttle/images/die.jpg")));
							// 메세지출력
							JOptionPane.showMessageDialog(null, "장바구니에 제품이담겼습니다");

						} catch (Exception ex) {
							ex.printStackTrace();
						}
						/*******************************************/

					} else {
						// 로그인안했을때
						/****************** 로그인다이알로그뛰우기 ***************/
						LoginDialog loginDialog;
						try {
							loginDialog = new LoginDialog();
							loginDialog.setShopMainFrame(ShopMainFrame.this);
							loginDialog.setVisible(true);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}

				}
			});
			cartAddButton.setBorder(null);
			cartAddButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			cartAddButton.setOpaque(false);
			cartAddButton.setBackground(Color.WHITE);
			cartAddButton.setForeground(Color.WHITE);
			cartAddButton.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/bbangshuttle/images/die.jpg")));
			cartAddButton.setBounds(136, 119, 31, 23);
			productPanel.add(cartAddButton);

			productMiddleDogContentPanel.add(productPanel);
			/*******************************/

		}
	}

	/*********************** 대형견보여주기 *******************************/
	public void displayProductBigDogList() throws Exception {
		List<Product> productList = productService.ProductFindByAll();
		Collections.shuffle(productList);
		for (int i = 0; i < productList.size() - 7; i++) {
			Product product = productList.get(i);
			/************* 제품1개UI디자인 *******************/
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

			productImageLabel
					.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/bbangshuttle/images/" + product.getP_image())));
			productImageLabel.setBounds(3, 1, 162, 116);
			productPanel.add(productImageLabel);

			JLabel productDescLabel = new JLabel("<html><font size='3'>" + "견종: " + product.getP_name() + "<br>"
					+ "가격: " + new DecimalFormat("#,###").format(product.getPrice()) + "<br>" + "설명: "
					+ product.getP_desc() + "</font></html>");

			productDescLabel.setVerticalAlignment(SwingConstants.TOP);
			productDescLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			productDescLabel.setHorizontalAlignment(SwingConstants.LEFT);
			productDescLabel.setBounds(3, 143, 164, 47);
			productPanel.add(productDescLabel);

			JComboBox cartQtyComboBox = new JComboBox();
			cartQtyComboBox.setAutoscrolls(true);
			cartQtyComboBox.setModel(
					new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
			cartQtyComboBox.setOpaque(false);
			cartQtyComboBox.setBorder(null);
			cartQtyComboBox.setBackground(Color.WHITE);
			cartQtyComboBox.setBounds(99, 119, 33, 23);
			cartQtyComboBox.setMaximumRowCount(cartQtyComboBox.getModel().getSize());
			productPanel.add(cartQtyComboBox);

			JButton cartAddButton = new JButton("");
			cartAddButton.addActionListener(new ActionListener() {
				private Product p = product;

				public void actionPerformed(ActionEvent e) {

					if (loginMember != null) {
						// 로그인했을때
						/****************** 카트에담기 ***************/
						try {
							String cartQtyStr = (String) cartQtyComboBox.getSelectedItem();
							int cartQty = Integer.parseInt(cartQtyStr);
							int addCount = cartService.addCart(loginMember.getMemberId(), p.getP_no(), cartQty);
							// 카트수량아이콘변경
							int cartItemCount = cartService.getCartItemByUserId(loginMember.getMemberId()).size();
							cartCountLabel.setIcon(new ImageIcon(
									ShopMainFrame.class.getResource("/bbangshuttle/images/die.jpg")));
							// 메세지출력
							JOptionPane.showMessageDialog(null, "장바구니에 제품이담겼습니다");

						} catch (Exception ex) {
							ex.printStackTrace();
						}
						/*******************************************/

					} else {
						// 로그인안했을때
						/****************** 로그인다이알로그뛰우기 ***************/
						
						try {
							LoginDialog loginDialog = new LoginDialog();
							loginDialog.setShopMainFrame(ShopMainFrame.this);
							loginDialog.setVisible(true);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
				}
			});
			cartAddButton.setBorder(null);
			cartAddButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			cartAddButton.setOpaque(false);
			cartAddButton.setBackground(Color.WHITE);
			cartAddButton.setForeground(Color.WHITE);
			cartAddButton.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/bbangshuttle/images/die.jpg")));
			cartAddButton.setBounds(136, 119, 31, 23);
			productPanel.add(cartAddButton);

			productBigDogContentPanel.add(productPanel);
			/*******************************/

		}
	}
}// end class
