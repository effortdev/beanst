package com.dao;

public class UserDAO {
}



// sql 사용법 입니다!
// String sql = SqlManager.get("hotel.popular"); <-이거 WEB-INF/config/sql.properties 의 내용을 넣어주시면 댑니당 >


/*
 * 로그인 예시
 * public MemberDTO selectMember(String id) {

    String sql = SqlManager.get("member.login"); <- 이거에요!

    try (Connection conn = DBConn.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, id);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                MemberDTO dto = new MemberDTO();
                dto.아이디(rs.getString(아이디));
                dto.비밀번호(rs.getString(비밀번호));
                return dto;
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}
 * 
 * 
 */