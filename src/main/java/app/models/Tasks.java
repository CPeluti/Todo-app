package app.models;

import app.controllers.Singleton;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;


public class Tasks{
	public int id;
	public String title;//conteudo
	public String category;//categoria
	public  boolean done = false;//completada
	public  boolean favourite = false;// favorito
	public String deadline;//data formato da data vai ser dd/mm/aaaa ou dd/MM/yyyy HH:mm:ss
	public String description;// descricao
	public boolean deleted = false;//tarefa deletada?

	public double getId() { return id; }

	public String getTitle() {
		return title;
	}

	public  String getCategory() {
		return category;
	}

	public boolean isDone() {
		return done;
	}

	public boolean isFavourite() {
		return favourite;
	}

	public String getDeadline() {
		return deadline;
	}

	public String getDescription() {
		return description;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public Tasks(int id, String title, String category, boolean done, boolean favourite, String deadline, String description, boolean deleted) {
		super();
		this.id = id;
		this.title = title;
		this.category = category;
		this.done = done;
		this.favourite = favourite;
		this.deadline = deadline;
		this.description = description;
		this.deleted = deleted;
	}


	public static void remainingTime(String deadline) throws Exception { //tempo restante
		LocalDateTime now = LocalDateTime.now();
		Date timeEnd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(deadline);
		Instant instant = now.toInstant(ZoneOffset.UTC); //essas duas linhas pega o formato LocalDateTime para o Date
		Date timeNow = Date.from(instant);
		
		long timeLeft = timeEnd.getTime() - timeNow.getTime();
		long daysLeft = timeLeft/(60*60*24*1000);
		long hoursLeft = (timeLeft - daysLeft*(60*60*24*1000))/(60*60*1000);
		long minutesLeft = (timeLeft - daysLeft*(60*60*24*1000) - hoursLeft*(60*60*1000))/(60*1000);
		long secondsLeft = (timeLeft - minutesLeft*(60*1000) - daysLeft*(60*60*24*1000) - hoursLeft*(60*60*1000))/1000;
		
		System.out.format("%d Days %d Hours %d Minutes %d Seconds", daysLeft, hoursLeft, minutesLeft, secondsLeft);
	}

	public static void create(Tasks task, Singleton singleton) {
	    User user = singleton.getUser();
	    System.out.println(user.sessionToken);
	    Map<String, String> header = new HashMap<>();
	    header.put("Content-Type","application/json");
	    header.put("Authorization", "bearer " + user.sessionToken);

	    String getTask = "{" +
                    "\"id\":\""+ task.id+ "\","+
                    "\"title\":\""+ task.title+ "\","+
                    "\"category\":\""+ task.category+ "\","+
                    "\"done\":\""+ task.done+ "\","+
                    "\"favourite\":\""+ task.favourite+ "\","+
                    "\"deadline\":\""+ task.deadline + "\","+
                    "\"description\":\""+ task.description+ "\","+
                    "\"deleted\":\""+ task.deleted +"\"" +
                "}";
	    System.out.println(getTask);
	    HttpResponse<JsonNode> res = Unirest.post("https://api-todo-unb.herokuapp.com/tasks/{userId}").routeParam("userId",user.id).headers(header).body(getTask).asJson();
	    JSONObject jsonTask = res.getBody().getObject();
	    System.out.println(jsonTask);
	}

	public Tasks lookForTask(int id, ArrayList<Tasks> list) {
		for (Tasks find: list) {
			if (find.getId() == id) {
				return find;
			}
		}
		return null;
	}

	public void delete(int id, Singleton singleton){
		User user = singleton.getUser();
		String url = "https://api-todo-unb.herokuapp.com/tasks/" + user.id;
		String dataString = "{\"taskId\":\""+id+"\"}";

		HttpResponse<JsonNode> data = Unirest.delete(url)
				.header("Content-Type","application/json")
				.header("Authorization", "bearer " + user.getSessionToken())
				.body(dataString)
				.asJson();
		JSONObject json = data.getBody().getObject();
	}

	public void update(Tasks taskChanges, Singleton singleton) {
		User user = singleton.getUser();
		String url = "https://api-todo-unb.herokuapp.com/tasks/" + user.id;
		String getTask = "{" +
				"\"id\":\""+ taskChanges.id+ "\","+
				"\"title\":\""+ taskChanges.title+ "\","+
				"\"category\":\""+ taskChanges.category+ "\","+
				"\"done\":\""+ taskChanges.done+ "\","+
				"\"favourite\":\""+ taskChanges.favourite+ "\","+
				"\"deadline\":\""+ taskChanges.deadline + "\","+
				"\"description\":\""+ taskChanges.description+ "\","+
				"\"deleted\":\""+ taskChanges.deleted +"\"" +
				"}";
		HttpResponse<JsonNode> data = Unirest.put(url)
				.header("Content-Type","application/json")
				.header("Authorization", "bearer " + user.getSessionToken())
				.body(getTask)
				.asJson();
		JSONObject json = data.getBody().getObject();
	}
}
