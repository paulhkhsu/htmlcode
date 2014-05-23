package zzz;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.io.FileUtils;

public class ReadCSV {

	public static void main(String args[]) throws IOException {

		String filepath = "C:\\Logs\\3HGGK5H8XFM703385.csv";
		List<String> lines = FileUtils.readLines(new File(filepath));

		String outdir = "c:\\conv";
		int i = 0;
		for (String s : lines) {

			// System.out.println(s);
			if (i > 0) {
				StringTokenizer st = new StringTokenizer(s, "\t");
				st.nextToken();
				String sout = st.nextToken().replace("\"\"", "\"");
				int len = sout.length();
				String finalout = sout.substring(1, len-1);
				String outf = outdir + "\\" + i + ".txt";
				System.out.println(finalout);
				FileUtils.writeStringToFile(new File(outf), finalout);
			}
			i++;
		}

	}
}
