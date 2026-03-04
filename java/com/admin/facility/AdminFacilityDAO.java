package com.admin.facility;

import static com.util.JdbcUtil.close;
import static com.util.JdbcUtil.getConnection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.sql.Statement;

import jakarta.servlet.ServletContext;

public class AdminFacilityDAO {

	private Properties props = new Properties();

	public AdminFacilityDAO(ServletContext context) {
		try {
			InputStream input = context.getResourceAsStream("/WEB-INF/config/adminMapper.xml");

			props.loadFromXML(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<AdminFacilityDTO> selectAll() {

		ArrayList<AdminFacilityDTO> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = props.getProperty("facilitySelectAll");

			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				AdminFacilityDTO dto = new AdminFacilityDTO();
				dto.setFacilityId(rs.getInt("facility_id"));
				dto.setFacilityType(rs.getString("facility_type"));
				dto.setFacilityName(rs.getString("facility_name"));
				dto.setLocation(rs.getString("location"));
				dto.setOpenTime(rs.getString("open_time"));

				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
			close(conn);
		}

		return list;
	}

	public void updateFacility(Connection conn, int facilityId, String facilityName, String facilityType,
			String location, String openTime, String description) throws Exception {

		String sql = props.getProperty("facilityUpdate");

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, facilityName);
		ps.setString(2, facilityType);
		ps.setString(3, location);
		ps.setString(4, openTime);
		ps.setString(5, description);
		ps.setInt(6, facilityId);

		ps.executeUpdate();
		ps.close();
	}

	public String getImagePath(Connection conn, int imageId) throws Exception {

		String sql = props.getProperty("facilityImagePath");

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, imageId);

		ResultSet rs = ps.executeQuery();

		String path = null;

		if (rs.next()) {
			path = rs.getString("image_path");
		}

		rs.close();
		ps.close();

		return path;
	}

	public void deleteImage(Connection conn, int imageId) throws Exception {

		String sql = props.getProperty("facilityImageDelete");

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, imageId);
		ps.executeUpdate();
		ps.close();
	}

	public void insertImage(Connection conn, int facilityId, String imagePath, String isMain) throws Exception {

		String sql = props.getProperty("facilityImageInsert");

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, facilityId);
		ps.setString(2, imagePath);
		ps.setString(3, isMain);
		ps.executeUpdate();
		ps.close();
	}

	public void resetMainImage(Connection conn, int facilityId) throws Exception {
		String sql = props.getProperty("facilityResetMain");
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, facilityId);
		ps.executeUpdate();
		ps.close();
	}

	public void setMainImage(Connection conn, int imageId) throws Exception {

		String sql = props.getProperty("facilitySetMain");

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, imageId);
		ps.executeUpdate();
		ps.close();
	}

	public AdminFacilityDTO selectOne(int id) throws Exception {

		String sql = props.getProperty("facilitySelectOne");

		Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		AdminFacilityDTO dto = null;

		if (rs.next()) {
			dto = new AdminFacilityDTO();
			dto.setFacilityId(rs.getInt("facility_id"));
			dto.setFacilityType(rs.getString("facility_type"));
			dto.setFacilityName(rs.getString("facility_name"));
			dto.setLocation(rs.getString("location"));
			dto.setOpenTime(rs.getString("open_time"));
			dto.setDescription(rs.getString("description"));
		}

		close(rs);
		close(ps);
		close(conn);

		return dto;
	}

	public ArrayList<FacilityImageDTO> selectImages(int facilityId) throws Exception {

		String sql = props.getProperty("facilitySelectImages");

		Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, facilityId);

		ResultSet rs = ps.executeQuery();

		ArrayList<FacilityImageDTO> list = new ArrayList<>();

		while (rs.next()) {
			FacilityImageDTO dto = new FacilityImageDTO();
			dto.setImageId(rs.getInt("image_id"));
			dto.setImagePath(rs.getString("image_path"));
			dto.setIsMain(rs.getString("is_main"));
			list.add(dto);
		}

		close(rs);
		close(ps);
		close(conn);

		return list;
	}

	public int insertFacility(Connection conn, AdminFacilityDTO dto) throws Exception {

		int facilityId = 0;

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String sql = props.getProperty("facilityInsert");

			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, dto.getFacilityType());
			ps.setString(2, dto.getFacilityName());
			ps.setString(3, dto.getLocation());
			ps.setString(4, dto.getOpenTime());
			ps.setString(5, dto.getDescription());

			ps.executeUpdate();

			rs = ps.getGeneratedKeys();

			if (rs.next()) {
				facilityId = rs.getInt(1);
			}

		} finally {
			close(rs);
			close(ps);
		}

		return facilityId;
	}
}