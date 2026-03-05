package com.facility;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import jakarta.servlet.ServletContext;

import static com.util.JdbcUtil.*;

public class FacilityDAO {

	private Properties props = new Properties();

	public FacilityDAO(ServletContext context) {

		try {

			InputStream input = context.getResourceAsStream("/WEB-INF/config/mainMapper.xml");

			props.loadFromXML(input);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<FacilityMainDTO> selectMainFacility() {

		List<FacilityMainDTO> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String sql = props.getProperty("selectMainFacility");

			conn = getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {

				FacilityMainDTO dto = new FacilityMainDTO();

				dto.setFacilityId(rs.getInt("facility_id"));
				dto.setFacilityName(rs.getString("facility_name"));
				dto.setDescription(rs.getString("description"));
				dto.setImagePath(rs.getString("image_path"));

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

	public FacilityDTO selectFacility(int facilityId) {

		FacilityDTO dto = null;

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String sql = props.getProperty("selectFacility");

			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, facilityId);

			rs = ps.executeQuery();

			if (rs.next()) {

				dto = new FacilityDTO();

				dto.setFacilityId(rs.getInt("facility_id"));
				dto.setFacilityName(rs.getString("facility_name"));
				dto.setDescription(rs.getString("description"));
				dto.setLocation(rs.getString("location"));
				dto.setOpenTime(rs.getString("open_time"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
			close(conn);
		}

		return dto;
	}

	public List<FacilityImageDTO> selectFacilityImages(int facilityId) {

		List<FacilityImageDTO> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String sql = props.getProperty("selectFacilityImages");

			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, facilityId);

			rs = ps.executeQuery();

			while (rs.next()) {

				FacilityImageDTO dto = new FacilityImageDTO();

				dto.setImageId(rs.getInt("image_id"));
				dto.setFacilityId(rs.getInt("facility_id"));
				dto.setImagePath(rs.getString("image_path"));
				dto.setIsMain(rs.getString("is_main"));
				dto.setDisplayOrder(rs.getInt("display_order"));

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

	public List<FacilityMainDTO> selectFacilityList() {

		List<FacilityMainDTO> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String sql = props.getProperty("selectFacilityList");

			conn = getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {

				FacilityMainDTO dto = new FacilityMainDTO();

				dto.setFacilityId(rs.getInt("facility_id"));
				dto.setFacilityName(rs.getString("facility_name"));
				dto.setDescription(rs.getString("description"));
				dto.setLocation(rs.getString("location"));
				dto.setOpenTime(rs.getString("open_time"));
				dto.setImagePath(rs.getString("image_path"));

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

}