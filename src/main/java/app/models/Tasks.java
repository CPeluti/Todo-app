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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


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

	public  String getDeadline() {
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

	public static Tasks create(Tasks task, Singleton singleton) {
		try{
			User user = singleton.getUser();
			Map<String, String> header = new HashMap<>();
			header.put("Content-Type", "application/json");
			header.put("Authorization", "bearer " + user.getSessionToken());

			String getTask = "{id:"+ task.getId()+
					"\ntitle:"+ task.getTitle()+
					"\ncategory:"+ task.getCategory()+
					"\ndone:"+ task.isDone()+
					"\nfavourite:"+ task.isFavourite()+
					"\ndeadline:"+ task.getDeadline() +
					"\ndescription:"+ task.getDescription()+
					"\ndeleted:"+ task.isDeleted() +"}";
			System.out.println(getTask);
			String url = "https://api-todo-unb.herokuapp.com/tasks/"+ user.id;
			HttpResponse<JsonNode> res = Unirest.post(url).headers(header).body(getTask).asJson();
			JSONObject jsonTask = res.getBody().getObject();
			System.out.println(jsonTask);
			if (!jsonTask.isEmpty()) {
				return new Tasks(Integer.parseInt(jsonTask.getString("id")), jsonTask.getString("title"), jsonTask.getString("category"), Boolean.parseBoolean(jsonTask.getString("done")), Boolean.parseBoolean(jsonTask.getString("favourite")), jsonTask.getString("deadline"), jsonTask.getString("description"), Boolean.parseBoolean(jsonTask.getString("deleted")));
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public Tasks lookForTask(int id, ArrayList<Tasks> list) {
		for (Tasks find: list) {
			if (find.getId() == id) {
				return find;
			}
		}
		return null;
	}

	public Tasks delete(int id, Singleton singleton){
		try{
			User user = singleton.getUser();
			Tasks taskToDelete = lookForTask(id, singleton.getListTasks());
			Map<String, String> header = new HashMap<>();
			header.put("Content-Type", "application/json");
			header.put("Authorization", "bearer " + user.getSessionToken());

			String url = "https://api-todo-unb.herokuapp.com/tasks/"+ user.id;
			HttpResponse<JsonNode> deleteTask = Unirest.delete(url).headers(header).body(taskToDelete).asJson();
			JSONObject jsonTasktoDelete = deleteTask.getBody().getObject();
			System.out.println(jsonTasktoDelete);
			if (!jsonTasktoDelete.isEmpty()) {
				return new Tasks(Integer.parseInt(jsonTasktoDelete.getString("id")), jsonTasktoDelete.getString("title"), jsonTasktoDelete.getString("category"), Boolean.parseBoolean(jsonTasktoDelete.getString("done")), Boolean.parseBoolean(jsonTasktoDelete.getString("favourite")), jsonTasktoDelete.getString("deadline"), jsonTasktoDelete.getString("description"), Boolean.parseBoolean(jsonTasktoDelete.getString("deleted")));
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public Tasks update(Tasks taskChanges, Singleton singleton) {
		try{
			User user = singleton.getUser();
			Map<String, String> header = new HashMap<>();
			header.put("Content-Type", "application/json");
			header.put("Authorization", "bearer " + user.getSessionToken());

			String getUpdate = "{id:"+ taskChanges.getId()+
					"\ntitle:"+ getTitle()+
					"\ncategory:"+ getCategory()+
					"\ndone:"+ isDone()+
					"\nfavourite:"+ isFavourite()+
					"\ndeadline:"+ getDeadline() +
					"\ndescription:"+ getDescription()+
					"\ndeleted:"+ isDeleted() +"}";
			String url = "https://api-todo-unb.herokuapp.com/tasks/"+ user.id;
			HttpResponse<JsonNode> updateTask = Unirest.put(url).headers(header).body(taskChanges).asJson();
			JSONObject jsonTasktoUpdate = updateTask.getBody().getObject();
			System.out.println(jsonTasktoUpdate);
			if (!jsonTasktoUpdate.isEmpty()) {
				return new Tasks(Integer.parseInt(jsonTasktoUpdate.getString("id")), jsonTasktoUpdate.getString("title"), jsonTasktoUpdate.getString("category"), Boolean.parseBoolean(jsonTasktoUpdate.getString("done")), Boolean.parseBoolean(jsonTasktoUpdate.getString("favourite")), jsonTasktoUpdate.getString("deadline"), jsonTasktoUpdate.getString("description"), Boolean.parseBoolean(jsonTasktoUpdate.getString("deleted")));
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
