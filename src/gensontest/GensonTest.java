package gensontest;

import com.owlike.genson.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Random;

//import sun.net.www.http.HttpClient;

public class GensonTest {

	public static void main(String[] args) {
		
		Genson genson = new Genson();
		City cityOne = new City();
		
		String cityID = "463829";
		// Строка запроса.
		String strURL = "http://api.openweathermap.org/data/2.5/"
				+ "forecast?id=" + cityID + "&appid=23c44e5e16a618179ddb96457d819225";
		String answerBody;
		
		// Создание клиента.
		HttpClient client1 = HttpClient.newHttpClient();
		// Создание запроса.
		HttpRequest client1Request = HttpRequest.newBuilder()
				.uri(URI.create(strURL))
				.GET()
				.build();
		
		// Отправка запроса.
		try {
			HttpResponse response = client1.send(client1Request, HttpResponse.BodyHandlers.ofString());
			answerBody = response.body().toString();
			cityOne = genson.deserialize(answerBody, City.class);
		} catch (Exception e) {
		}

            
		// Форматированный вывод.
		// Выводим все элементы массива.
		for (int i = 0; i < cityOne.list.length; i++) {
//				System.out.printf("%2od\t", i);
			System.out.print(TimeStampConvert(cityOne.list[i].dt) + '\t');
//			System.out.print(cityOne.list[i].dt + '\t');
			System.out.printf("temp: %3.2f%c\t", 
					(cityOne.list[i].main.temp - 273), '\u00B0');
			System.out.printf("pressure: %4.2f mmHg \t", 
					ConvertPressureFromHPaTommHg(cityOne.list[i].main.pressure));
			System.out.printf("humidity: %3.1f%%\n", 
					cityOne.list[i].main.humidity);
		}
//            TimeStampConvert(1570525200);
	}
        
        // Конверсия Unix timestamp.
        static String TimeStampConvert(int in)    {
			// Получаю экземпляр типа Instant, чтобы работать со временем.
//			Instant instant1 = Instant.ofEpochSecond((long)in);
			LocalDateTime ldt1 = LocalDateTime.ofEpochSecond((long)in, 0, ZoneOffset.ofHours(2));
//			System.out.println(ldt1.toString());
//			System.out.println(ldt1.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)));
//            java.util.Date time = new java.util.Date((long)in * 1000);
//            LocalDate lc = new LocalDate(time);
//            DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE.format(time);
            return ldt1.format(DateTimeFormatter
					.ofLocalizedDateTime(FormatStyle.SHORT));
//            return formatter.format(time);
        }
        
        // Конверсия hPa в mmHg.
		static float ConvertPressureFromHPaTommHg(float in) {
            final float k = 0.75006157584566f;
            return k * in;
        }
		// Инициализация массива случайными числами.
		// Ф-ция ничего не возвращает, т.к. заменяет элементы массива непосредственно.
		static void RNDFuncForArray(int array[])	{
			Random rndGenerator = new Random();

			for (int i = 0; i < array.length; i++) {
				array[i] = rndGenerator.nextInt(100);
			}
	}
}
