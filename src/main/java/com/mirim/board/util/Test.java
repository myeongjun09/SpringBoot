package com.mirim.board.util;

import java.sql.*;

public class Test {
    public static void main(String [] args) {
        String url = "jdbc:mtsql://localhost:3306/board";

        try (Connection conn = DriverManager.getConnection(url, "root", "mirim")) {
            PreparedStatement pstmt = conn.prepareStatement("select * from post where id = ?"); // 어떤 SQL을 실행할 지 미리 준비하는 것
            pstmt.setLong(1, 1L); // ?에 들어가는 것들을 나중에 결정
            ResultSet rs = pstmt.executeQuery(); // result set을 리턴하고 이 쿼리 결과가 테이블로 돌아옴
            if(rs.next()) { // 한줄 한줄 읽고 결과를 읽음
                System.out.println(rs.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
