package bbangshuttle.uitest;

// MemberFrame.java

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import bbangshuttle.member.Member;
import bbangshuttle.member.MemberService;

public class MemberFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JPanel loginPanel;
    private JPanel profilePanel;
    private JPanel updatePanel;
    private JPanel deletePanel;

    // 로그인 화면 요소
    private JTextField loginIdField;
    private JPasswordField loginPasswordField;
    private JButton loginButton;

    // 회원 정보 보기 화면 요소
    private JLabel profileIdLabel;
    private JLabel profileNameLabel;
    private JLabel profileEmailLabel;
    private JLabel profileAddressLabel;
    private JLabel profilePhoneLabel;
    private JLabel profileBirthLabel;
    private JLabel profilePointLabel;
    private JButton viewProfileButton;
    private JButton updateProfileButton;

    // 회원 정보 수정 화면 요소
    private JTextField updateNameField;
    private JTextField updateEmailField;
    private JTextField updateAddressField;
    private JTextField updatePhoneField;
    private JTextField updateBirthField;
    private JButton updateButton;

    // 로그인한 회원 정보를 저장할 변수
    private Member loggedInMember;

    public MemberFrame(Member loggedInMember) {
        this.loggedInMember = loggedInMember;

        setTitle("회원 정보 관리");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // 로그인, 회원 정보, 회원 정보 수정, 회원 탈퇴 화면 생성
        loginPanel = createLoginPanel();
        profilePanel = createProfilePanel();
        updatePanel = createUpdatePanel();
        deletePanel = createDeletePanel();

        mainPanel.add(loginPanel, "login");
        mainPanel.add(profilePanel, "profile");
        mainPanel.add(updatePanel, "update");
        mainPanel.add(deletePanel, "delete");

        // 처음에는 프로필 화면을 보여줌
        cardLayout.show(mainPanel, "profile");
        add(mainPanel);

        setVisible(true);
    }

    // 로그인 화면 생성
    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("로그인");
        JLabel idLabel = new JLabel("아이디:");
        JLabel passwordLabel = new JLabel("비밀번호:");

        loginIdField = new JTextField(20);
        loginPasswordField = new JPasswordField(20);

        loginButton = new JButton("로그인");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
					login();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });

        JPanel formPanel = new JPanel();
        formPanel.add(idLabel);
        formPanel.add(loginIdField);
        formPanel.add(passwordLabel);
        formPanel.add(loginPasswordField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    // 회원 정보 보기 화면 생성
    private JPanel createProfilePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("회원 정보");
        profileIdLabel = new JLabel();
        profileNameLabel = new JLabel();
        profileEmailLabel = new JLabel();
        profileAddressLabel = new JLabel();
        profilePhoneLabel = new JLabel();
        profileBirthLabel = new JLabel();
        profilePointLabel = new JLabel();

        viewProfileButton = new JButton("회원 정보 보기");
        viewProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showProfileInfo(loggedInMember);
            }
        });

        updateProfileButton = new JButton("회원 정보 수정");
        updateProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "update");
            }
        });

        JPanel profilePanel = new JPanel();
        profilePanel.add(profileIdLabel);
        profilePanel.add(profileNameLabel);
        profilePanel.add(profileEmailLabel);
        profilePanel.add(profileAddressLabel);
        profilePanel.add(profilePhoneLabel);
        profilePanel.add(profileBirthLabel);
        profilePanel.add(profilePointLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(viewProfileButton);
        buttonPanel.add(updateProfileButton);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(profilePanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    // 회원 정보 수정 화면 생성
    private JPanel createUpdatePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("회원 정보 수정");

        // 정보 수정을 위한 텍스트 필드 생성
        updateNameField = new JTextField(20);
        updateEmailField = new JTextField(20);
        updateAddressField = new JTextField(20);
        updatePhoneField = new JTextField(20);
        updateBirthField = new JTextField(20);

        updateNameField.setText(loggedInMember.getMemberName());
        updateEmailField.setText(loggedInMember.getMemberEmail());
        updateAddressField.setText(loggedInMember.getMemberAddress());
        updatePhoneField.setText(loggedInMember.getMemberNumber());
        updateBirthField.setText(loggedInMember.getMemberBirth());

        updateButton = new JButton("수정 완료");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 회원 정보 수정 처리
                updateMemberInfo();
            }
        });

        JPanel formPanel = new JPanel();
        formPanel.add(new JLabel("이름:"));
        formPanel.add(updateNameField);
        formPanel.add(new JLabel("이메일:"));
        formPanel.add(updateEmailField);
        formPanel.add(new JLabel("주소:"));
        formPanel.add(updateAddressField);
        formPanel.add(new JLabel("연락처:"));
        formPanel.add(updatePhoneField);
        formPanel.add(new JLabel("생년월일(YYYY/MM/DD):"));
        formPanel.add(updateBirthField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(updateButton);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    // 회원 정보 보기 기능을 처리하는 메소드
    private void showProfileInfo(Member member) {
        if (member != null) {
            profileIdLabel.setText("아이디: " + member.getMemberId());
            profileNameLabel.setText("이름: " + member.getMemberName());
            profileEmailLabel.setText("이메일: " + member.getMemberEmail());
            profileAddressLabel.setText("주소: " + member.getMemberAddress());
            profilePhoneLabel.setText("연락처: " + member.getMemberNumber());
            profileBirthLabel.setText("생년월일: " + member.getMemberBirth());
            profilePointLabel.setText("회원 포인트: " + member.getMemberPoint());
        }
    }

    // 회원 정보 수정 기능을 처리하는 메소드
    private void updateMemberInfo() {
        String name = updateNameField.getText();
        String email = updateEmailField.getText();
        String address = updateAddressField.getText();
        String phoneNumber = updatePhoneField.getText();
        String birthDateStr = updateBirthField.getText();

        // 생년월일을 Date 타입으로 변환
        Date birthDate = parseBirthDate(birthDateStr);
        if (birthDate == null) {
            showMessageDialog("올바른 생년월일 형식을 입력해주세요. (YYYY/MM/DD)");
            return;
        }

        // 회원 정보를 업데이트하는 코드가 와야합니다.
        // 업데이트 후 DB에 회원 정보를 저장하거나 다른 처리를 해야합니다.

        // 임시로 업데이트한 회원 정보를 출력
        showMessageDialog("회원 정보가 업데이트되었습니다.");

        loggedInMember.setMemberName(name);
        loggedInMember.setMemberEmail(email);
        loggedInMember.setMemberAddress(address);
        loggedInMember.setMemberNumber(phoneNumber);
        loggedInMember.setMemberBirth(formatDate(birthDate));

        cardLayout.show(mainPanel, "profile");
        showProfileInfo(loggedInMember);
    }

    // 회원 탈퇴 기능을 처리하는 메소드
    private void deleteMember() {
        // 회원 탈퇴 처리를 수행하는 코드가 와야합니다.
        // 탈퇴 후 DB에서 회원 정보를 삭제하거나 다른 처리를 해야합니다.

        showMessageDialog("회원 탈퇴가 완료되었습니다.");

        // 탈퇴 후 MainFrame으로 돌아갑니다.
        cardLayout.show(mainPanel, "login");
    }

    // Helper 메소드: 문자열(YYYY/MM/DD)을 Date로 변환
    private Date parseBirthDate(String birthDateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            return sdf.parse(birthDateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    // Helper 메소드: Date를 포맷된 문자열(YYYY/MM/DD)로 변환
    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(date);
    }

    // Helper 메소드: 메시지 다이얼로그를 표시
    private void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    // 로그인 기능을 처리하는 메소드
    private void login() throws Exception {
        String memberId = loginIdField.getText();
        String password = new String(loginPasswordField.getPassword());

        // 실제로 회원 정보를 DB 등에서 조회하고, 회원 정보를 반환하는 로직을 구현합니다.
        // 만약 회원 정보가 맞다면 해당 회원의 Member 객체를 반환하고, 틀리다면 null을 반환합니다.
        // 예시로, 임시로 아래와 같이 회원 정보를 생성합니다.
        try {
            MemberService memberService = new MemberService();
            loggedInMember = memberService.loginMember(memberId, password);

            if (loggedInMember != null) {
                showMessageDialog("로그인 성공!");
                cardLayout.show(mainPanel, "profile");
                showProfileInfo(loggedInMember);
            } else {
                showMessageDialog("아이디 또는 비밀번호가 일치하지 않습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showMessageDialog("로그인에 실패하였습니다. 관리자에게 문의하세요.");
        }
    }
    private JPanel createDeletePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("회원 탈퇴");
        JButton deleteProfileButton = new JButton("회원 탈퇴");
        deleteProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteMember();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(deleteProfileButton);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new MemberFrame(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
