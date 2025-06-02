package com.back;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        labs1();
    }

    public static void labs1(){
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