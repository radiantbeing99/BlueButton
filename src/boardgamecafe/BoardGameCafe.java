package boardgamecafe;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import gui.MainGUI;
import mgr.Factory;
import mgr.Manageable;
import mgr.Manager;

public class BoardGameCafe extends Manager {
    Scanner scan = new Scanner(System.in);    
    public static Manager gameMgr = new Manager();
    public static Manager snackMgr = new Manager();
    public static Manager nonMemberMgr = new Manager();
    public static Manager memberMgr = new Manager();
    public static Manager adminMgr = new Manager();
    public static Manager roomMgr = new Manager();
    final static int MAX_ROOM_NUMBER = 10;
    Administrator ad = new Administrator();

    public void run() {
        gameMgr.readAll("boardgame.txt", new Factory() {
            public Manageable create() {
                return new Game();
            }
        });

        snackMgr.readAll("snack.txt", new Factory() {
            public Manageable create() {
                return new Snack();
            }
        });

        nonMemberMgr.readAll("non_member.txt", new Factory() {
            public Manageable create() {
                return new NonMember();
            }
        });

        memberMgr.readAll("member.txt", new Factory() {
            public Manageable create() {
                return new Member();
            }
        });
        adminMgr.readAll("admin.txt", new Factory() {
        	public Manageable create() {
        		return new Admin();
        	}
        });
        
        // 방정보 추가
        for(int i = 0 ; i < MAX_ROOM_NUMBER; i++) {
        	Room r = new Room();
        	r.setRoomNumber(i);
        	roomMgr.getList().add(r);
        }
        // openMenu();      <- 이게 남아있으면 gui가 작동하지 않습니다
        MainGUI.gui = new MainGUI();
        MainGUI.gui.run();
    }

    void openMenu() {
        int menuNum = 0;
        Loop1:while (true) {
            System.out.println("(1) 게임 출력 (2) 회원 출력 (3) 비회원 출력 " +
                    "(4) 간식 출력 (5) 방 정보 출력 (기타) 종료");
            System.out.print(">> ");
            menuNum = scan.nextInt();
            switch (menuNum) {
                case 1: gameMgr.printAll(); break;
                case 2: memberMgr.printAll(); break;
                case 3: nonMemberMgr.printAll(); break;
                case 4: snackMgr.printAll(); break;
                case 5: roomMgr.printAll(); break;
                default: break Loop1;
            }
        }
    }

    public static void main(String[] args) {
        BoardGameCafe bgc = new BoardGameCafe();
        bgc.run();
    }
}
