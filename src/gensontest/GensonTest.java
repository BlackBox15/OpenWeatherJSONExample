package gensontest;

import com.owlike.genson.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class GensonTest {

	public static void main(String[] args) {
		
		Genson genson = new Genson();
//		int[] arrayOfInts = new int[100];
		City cityOne = new City();
		String content;
		
		// Читаем строку из файла.
		// Файл должен лежать на самом 1-м уровне каталога проекта.
		Path path = Paths.get("response.json");
		try {
			// Формируем строку с прочитанными данными из файла.
			content = new String(Files.readAllBytes(path));
			// Десериализуем JSON-структуру в экземпляр класса.
			cityOne = genson.deserialize(content, City.class);
		} 
		catch (Exception e) {
		}
//		// deserialize it back
		
		
		System.out.println("test");
//		// Заполнение массива.
//		rndFuncForArray(arrayOfInts);
//		// Формирование JSON-строки.
//		String json = genson.serialize(arrayOfInts);
//		
////		genson.deserialize(json, int[].class);
//		System.out.println(json);
	}
	
	// Инициализация массива случайными числами.
	// Ф-ция ничего не возвращает, т.к. заменяет элементы массива непосредственно.
	static void rndFuncForArray(int array[])	{
		Random rndGenerator = new Random();
		
		for (int i = 0; i < array.length; i++) {
			array[i] = rndGenerator.nextInt(100);
		}
	}
	
}
