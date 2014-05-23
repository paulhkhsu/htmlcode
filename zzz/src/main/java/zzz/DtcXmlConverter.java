package zzz;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

public class DtcXmlConverter {
	String urls = "http://localhost:8080/converter/restService/1.0/5a5ff233-7a46-44a1-9393-15745f310e2c";

	public static void main(String[] args) throws IOException {

		DtcXmlConverter dtc = new DtcXmlConverter();
		dtc.doit();
	}

	public void doit() throws IOException {

		File dir = new File("c:\\conv");

		System.out.println("Getting all files in " + dir.getAbsolutePath());
		List<File> files = (List<File>) FileUtils.listFiles(dir,
				TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
		for (File file : files) {
			System.out.println("process ..." + file.getAbsolutePath());
			String r = process(file);
			System.out.println(r);

			FileUtils
					.writeStringToFile(
							file,
							"\n\n====================== conveted result ==================\n\n",
							true);
			FileUtils.writeStringToFile(file, r, true);
		}
	}

	public String process(File file) {

		StringBuffer sb = new StringBuffer();
		try {

			URL url = new URL(urls);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/xml");
			conn.setRequestProperty("Accept", "application/xml");

			String input = FileUtils.readFileToString(file);

			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();

			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			String output;
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

		return sb.toString();
	}

}