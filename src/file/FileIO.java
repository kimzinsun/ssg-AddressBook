package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dto.AddressDto;
import singleton.SingletonClass;

public class FileIO {

	public static void save(String filename) {
		File file = new File(filename + ".txt");

		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));

			SingletonClass sc = SingletonClass.getInstance();

			for (int i = 0; i < sc.list.size(); i++) {
				AddressDto dto = sc.list.get(i);
				pw.println(dto.toString());
			}
			pw.close();

			System.out.println("성공적으로 저장되었습니다");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void load(String filename) {
		File file = new File(filename + ".txt");

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));

			SingletonClass sc = SingletonClass.getInstance();

			String str = "";
			while ((str = br.readLine()) != null) {
				String arr[] = str.split(",");

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

				Date utilDate = dateFormat.parse(arr[3]);

				java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

				AddressDto dto = new AddressDto(arr[0], Integer.parseInt(arr[1]), arr[2], sqlDate, arr[4]);

				sc.list.add(dto);
			}
			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
