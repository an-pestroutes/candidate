package automation.PestRoutes.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class AppData {
	static Properties properties;
	public String generalData = "application.properties";
	public String quarterlyPreferredDayData = "quarterlybyprefferedday.properties";

	public static void loadData(String needFile) {

		try {

			properties = new Properties();
			ClassLoader classLoader = new AppData().getClass().getClassLoader();
			 
	        File file = new File(classLoader.getResource(needFile).getFile());
			// InputStream location =
			// getClass().getResourceAsStream("application.properties");
//			File location = new File(
//					"C:\\Users\\AbdulAarbi\\Desktop\\pestroutesjava\\automation\\src\\test\\java\\automation\\PestRoutes\\Utilities\\Data\\application.properties");
//			// FileReader fileData = new FileReader(location);
			FileInputStream fileData = new FileInputStream(file);

			properties.load(fileData);

		} catch (Exception e) {
			System.out.println("Exception is == " + e.getMessage());
		}
	}


	public static String getData(String needData, String needFile) throws IOException {
		loadData(needFile);
		String data = properties.getProperty(needData);
		return data;
	}

	public static void addData(String needKey, String needValue, String needFile) throws Exception {
		//ClassLoader classLoader = new AppData().getClass().getClassLoader();
		File location = new File(System.getProperty("user.dir") + "/src/test/resources/"+needFile);
        //File file = new File(classLoader.getResource("application.properties").getFile());
		FileInputStream in = new FileInputStream(location);
		Properties props = new Properties();
		props.load(in);
		in.close();

		FileOutputStream out = new FileOutputStream(location);
		props.setProperty(needKey, needValue);
		props.store(out, null);
		out.close();
	}

}
