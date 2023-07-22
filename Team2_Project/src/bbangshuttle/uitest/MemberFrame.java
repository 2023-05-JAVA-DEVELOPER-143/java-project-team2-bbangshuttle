package bbangshuttle.uitest;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import bbangshuttle.member.Member;
import bbangshuttle.member.MemberService;

public class MemberFrame extends JFrame {
    private MemberService memberService;
    private Member loggedInMember;

    // 회원 정보 표시 구성 요소
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel addressLabel;
    private JLabel phoneLabel;
    private JLabel birthLabel;
    private JLabel joinDateLabel;
    private JLabel pointLabel;
    private JButton updateButton;
    private JButton deleteButton;

    // 회원 정보 수정 구성 요소
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JPanel viewPanel;
    private JPanel updatePanel;
    private JTextField emailField;
    private JTextField addressField;
    private JTextField phoneField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton saveButton;
    private JButton cancelButton;

    public MemberFrame(MemberService memberService, Member loggedInMember) {
        this.memberService = memberService;
        this.loggedInMember = loggedInMember;

        setTitle("Member Frame");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 회원 정보 표시 패널 구성
        idLabel = new JLabel("아이디: " + loggedInMember.getMemberId());
        nameLabel = new JLabel("이름: " + loggedInMember.getMemberName());
        emailLabel = new JLabel("이메일: " + loggedInMember.getMemberEmail());
        addressLabel = new JLabel("주소: " + loggedInMember.getMemberAddress());
        phoneLabel = new JLabel("연락처: " + loggedInMember.getMemberNumber());
        birthLabel = new JLabel("생년월일: " + loggedInMember.getMemberBirth());
        joinDateLabel = new JLabel("가입일자: " + formatDate(loggedInMember.getMemberRegdate()));
        pointLabel = new JLabel("포인트: " + loggedInMember.getMemberPoint());

        updateButton = new JButton("정보 수정");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 정보 수정 페이지로 전환
                cardLayout.show(cardPanel, "Update");
            }
        });

        deleteButton = new JButton("회원 탈퇴");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(MemberFrame.this, "정말로 회원 탈퇴하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        memberService.memberDelete(loggedInMember.getMemberId());
                        JOptionPane.showMessageDialog(MemberFrame.this, "회원 탈퇴가 완료되었습니다.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        // 로그인 프레임으로 돌아가기
                        cardLayout.show(cardPanel, "Login");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(MemberFrame.this, "회원 탈퇴 과정에서 오류가 발생했습니다.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(8, 1));
        infoPanel.add(idLabel);
        infoPanel.add(nameLabel);
        infoPanel.add(emailLabel);
        infoPanel.add(addressLabel);
        infoPanel.add(phoneLabel);
        infoPanel.add(birthLabel);
        infoPanel.add(joinDateLabel);
        infoPanel.add(pointLabel);
        infoPanel.add(updateButton);
        infoPanel.add(deleteButton);

        // 회원 정보 수정 패널 구성
        emailField = new JTextField(loggedInMember.getMemberEmail(), 10);
        addressField = new JTextField(loggedInMember.getMemberAddress(), 10);
        phoneField = new JTextField(loggedInMember.getMemberNumber(), 10);
        passwordField = new JPasswordField(10);
        confirmPasswordField = new JPasswordField(10);

        saveButton = new JButton("저장");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 정보 수정 기능 구현
                String email = emailField.getText();
                String address = addressField.getText();
                String phone = phoneField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                if (password.equals(confirmPassword)) {
                    // 비밀번호가 일치하는 경우에만 업데이트
                    loggedInMember.setMemberEmail(email);
                    loggedInMember.setMemberAddress(address);
                    loggedInMember.setMemberNumber(phone);

                    try {
                        memberService.memberUpdate(loggedInMember);
                        JOptionPane.showMessageDialog(MemberFrame.this, "회원 정보가 수정되었습니다.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        showMemberInfo();
                        cardLayout.show(cardPanel, "Info");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(MemberFrame.this, "회원 정보 수정에 실패했습니다.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(MemberFrame.this, "비밀번호와 비밀번호 확인이 일치하지 않습니다.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton = new JButton("취소");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 수정 취소 후 회원 정보 보기 페이지로 전환
                showMemberInfo();
                cardLayout.show(cardPanel, "Info");
            }
        });

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2));
        formPanel.add(new JLabel("비밀번호:"));
        formPanel.add(passwordField);
        formPanel.add(new JLabel("비밀번호 확인:"));
        formPanel.add(confirmPasswordField);
        formPanel.add(new JLabel("이메일:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("주소:"));
        formPanel.add(addressField);
        formPanel.add(new JLabel("연락처:"));
        formPanel.add(phoneField);
        formPanel.add(saveButton);
        formPanel.add(cancelButton);

        // 카드 레이아웃 설정
        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        cardPanel.add(infoPanel, "Info");
        cardPanel.add(formPanel, "Update");

        add(cardPanel, BorderLayout.CENTER);
        cardLayout.show(cardPanel, "Info");
    }

    private void showMemberInfo() {
        // 회원 정보 표시
        idLabel.setText("아이디: " + loggedInMember.getMemberId());
        nameLabel.setText("이름: " + loggedInMember.getMemberName());
        emailLabel.setText("이메일: " + loggedInMember.getMemberEmail());
        addressLabel.setText("주소: " + loggedInMember.getMemberAddress());
        phoneLabel.setText("연락처: " + loggedInMember.getMemberNumber());
        birthLabel.setText("생년월일: " + loggedInMember.getMemberBirth());
        joinDateLabel.setText("가입일자: " + formatDate(loggedInMember.getMemberRegdate()));
        pointLabel.setText("포인트: " + loggedInMember.getMemberPoint());
    }

    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(date);
    }
}
