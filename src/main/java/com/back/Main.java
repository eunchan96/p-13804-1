package com.back;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        labs1();
//        labs2();
        new App(new Scanner(System.in)).run();
    }

    private static void labs2() {
        System.out.println("안녕하세요.");

        PrintStream originalOut = System.out; // 원래의 System.out 저장

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output, true));

        System.out.println("반갑습니다.");
        String str1 = output.toString(); // 반갑습니다.\n
        System.out.println("좋아요 하하.");
        String str2 = output.toString(); // 반갑습니다.\n좋아요 하하.\n

        System.setOut(originalOut); // 원래의 System.out으로 복원
        System.out.println("str1: " + str1);
        System.out.println("str2: " + str2);
    }

    private static void labs1(){
        String input = """
                등록
                나의 죽음을 적들에게 알리지 말라.
                """;

        Scanner scanner = new Scanner(input);
        String cmd = scanner.nextLine();
        String content = scanner.nextLine();

        System.out.println("명령어: " + cmd);
        System.out.println("내용: " + content);

    }
}