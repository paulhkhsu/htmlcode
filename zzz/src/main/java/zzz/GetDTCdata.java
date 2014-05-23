package zzz;

import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.io.FileUtils;

public class GetDTCdata {

	public static void main(String[] argv) {

		String vin = "3HGGK5H83FM700151";
		String sql = "select vfu.CreateTimestamp,vfu.UploadedDataTxt "
				+ "from VfEventVehicleUploadData vfu , VfEvent vf "
				+ "where vf.UUIDNo = vfu.UUIDNo "
				+ "and vf.Vin = " + "'" + vin + "'" 
				+ "and vfu.UploadedDataTxt like '%Diagnostics%DTC%' "
				+ "and vfu.CreateTimestamp >= '2014-05-22 10:30:00.000' "
				+ "order by vfu.CreateTimestamp desc";

		String outdir = "c:\\conv";
		try {

			Class.forName("net.sourceforge.jtds.jdbc.Driver");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			return;

		}

		Connection connection = null;

		try {

			connection = DriverManager.getConnection(
					"jdbc:jtds:sqlserver://S3AHMNGTSQLV01/NGTCC_STG",
					"ngt_readonly_id", "honda123");

		} catch (SQLException e) {

			e.printStackTrace();
			return;

		}

		

		try {
			System.out.println("Creating statement...");
			Statement stmt = connection.createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			int i = 0;
			while (rs.next()) {
				// Retrieve by column name
				String ts = rs.getString(1);
				String dtc = rs.getString(2);
				System.out.println(ts + "|" + dtc);
				String outf = outdir + "\\" + ++i + ".txt";
				FileUtils.writeStringToFile(new File(outf), dtc);
			}
			rs.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
