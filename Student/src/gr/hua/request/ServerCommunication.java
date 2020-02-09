package gr.hua.request;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import gr.hua.entity.Person;
import gr.hua.entity.Student;

public class ServerCommunication {
	private static ObjectMapper mapper;
	private static OkHttpClient client;
	
	public static void postStudent (Student student) throws IOException {
		client = new OkHttpClient();
		mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(student);
		RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);
		Request req = new Request.Builder()
				.url("http://localhost:8080/Assignment/api/savePetition")
				.post(requestBody)
				.build();
		
		Call call = client.newCall(req);
		Response response = call.execute();
	}
	
	/*public static String getEmail (int id) throws IOException {
		client = new OkHttpClient();
		mapper = new ObjectMapper();
		
		Request request = new Request.Builder().url("http://localhost:8080/Assignment/api/email/" + id)
				.method("GET", null).build();
		
		ResponseBody response = client.newCall(request).execute().body();
		String email = response.string();
		return email;
	}*/
	
	public static Student getStudent (int id) throws IOException {
		client = new OkHttpClient();
		mapper = new ObjectMapper();
		
		Request request = new Request.Builder().url("http://localhost:8080/Assignment/api/petition/" + id)
				.method("GET", null).build();
		
		ResponseBody response = client.newCall(request).execute().body();
		Student student = mapper.readValue(response.string(), Student.class);
		return student;
	}
	
	public static void putStudent (Student student) throws IOException {
		client = new OkHttpClient();
		mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(student);
		RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);
		
		Request req = new Request.Builder()
				.url("http://localhost:8080/Assignment/api/updateInfo")
				.put(requestBody)
				.build();
		
		Call call = client.newCall(req);
		Response response = call.execute();
	}
	
	public static List<Person> getStudents() throws IOException {
		client = new OkHttpClient();
		mapper = new ObjectMapper();
		Request request = new Request.Builder().url("http://localhost:8080/Assignment/api/students/")
				.method("GET", null).build();
		
		ResponseBody response = client.newCall(request).execute().body();
		List<Person> students = mapper.readValue(response.string(), new TypeReference<List<Person>>(){});;
		return students;
	}
}
