package boardgamecafe;

import java.util.ArrayList;
import java.util.Scanner;

public class Member extends NonMember {
    // 이름 전화번호 비밀번호 포인트(결제 금액의 5%)
    // 서팔광 01017634965 3645 4154
    // member가 주문하면 주문은 어떻게 처리할까?
    public String password;

    @Override
    public void read(Scanner scan) {
        super.read(scan);
        password = scan.next();
    }

    // 파일을 추가하면서 수동으로 입력을 해야하는 경우가 생겨서 read를 오버로딩
    public void read(String name, String phoneNumber, String password){
        super.read(name, phoneNumber);
        this.password = password;
    }

    @Override
    public void print() {
        super.print();
        System.out.printf("\t비밀번호:%s\n", password);
    }

    @Override
    public boolean matches(String kwd) {
        if (super.matches((kwd))) {
            return true;
        }
        if (password.equals(kwd))
            return true;
        return false;
    }

    public void printUserType() {
        System.out.print("[회원] ");
    }
}
