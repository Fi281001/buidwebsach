package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.sun.jdi.connect.spi.Connection;

import bean.loaibean;
import bean.sachbean;

public class sachdao {
	
	

	public ArrayList<sachbean> getsach() {
//		ArrayList<sachbean> dssach = new ArrayList<sachbean>();
//		dssach.add(new sachbean("s1", "Cấu trúc dữ liệu", "Nguyễn Hoảng Hà", 100, 100000, "b1.jpg", "tin"));
//		dssach.add(new sachbean("s2", "Cấu trúc dữ liệu", "Nguyễn Hoảng Hà", 100, 100000, "b2.jpg", "tin"));
//		dssach.add(new sachbean("s3", "Cơ sở toán", "Nguyễn Hoảng Hà", 100, 100000, "b3.jpg", "toan"));
//		dssach.add(new sachbean("s4", "Giải tích", "Nguyễn Hoảng Hà", 100, 100000, "b4.jpg", "toan"));
//		dssach.add(new sachbean("s5", "Truyền Kiều", "Nguyễn Hoảng Hà", 100, 100000, "b5.jpg", "van"));
//		
//		return dssach;
		
		try {
			ArrayList<sachbean> dssach = new ArrayList<sachbean>();
//			   b1: ket noi vao csdl
			   CoSodao cs = new CoSodao();
			   cs.KetNoi();

			//			   b2: Lay du lieu ve
			   String sql = "select * from sach";
			   PreparedStatement cmd = cs.cn.prepareStatement(sql);
			   ResultSet rs = cmd.executeQuery();
			   while(rs.next()) {
				   String maSach = rs.getString("masach");
				   String tenSach = rs.getString("tensach");
				   String tacGia = rs.getString("tacgia");
				   long soLuong = rs.getLong("soluong");
				   long gia = rs.getLong("gia");
				   String anh = rs.getString("anh");
				   String maLoai = rs.getString("maloai");
				   dssach.add(new sachbean(maSach, tenSach, tacGia, soLuong, gia, anh, maLoai));

			   }

			   rs.close();
			   cs.cn.close();

			   return dssach;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return null;
			}
		
		
	}
	public int them (sachbean s, String sotap, Date date) throws Exception {
		CoSodao cs = new CoSodao();
		cs.KetNoi();;
		
		String sql = "insert into sach (masach, tensach, soluong, gia, maloai, sotap, anh, NgayNhap, tacgia)\r\n"
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement cmd = cs.cn.prepareStatement(sql);
		cmd.setString(1, s.getMaSach());
		cmd.setString(2, s.getTenSach());
		cmd.setLong(3, s.getSoLuong());
		cmd.setLong(4, s.getGia());
		cmd.setString(5, s.getMaLoai());
		cmd.setString(6, sotap);
		cmd.setString(7, s.getAnh());
		cmd.setDate(8, date);
		cmd.setString(9, s.getTacGia());
		return cmd.executeUpdate();
	}
	
}