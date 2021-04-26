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


public class Tasks{
	public static int id;
	public static String title;//antigo conteudo
	public static String category;//antiga categoria
	public static boolean done = false;//antiga completada
	public static boolean favourite = false;//antigo favorito
	public static String deadline;//antiga data formato da data vai ser dd/mm/aaaa ou dd/MM/yyyy HH:mm:ss
	public static String description;// antiga descricao
	public static boolean deleted = false;

	public double getId() { return id; }

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
			String getTask = "{id:"+ id+
					"\ntitle:"+ title+
					"\ncategory:"+ category+
					"\ndone:"+ done+
					"\nfavourite:"+ favourite+
					"\ndeadline:"+ deadline +
					"\ndescription:"+description+
					"\ndeleted:"+ deleted +"}";
			User user = singleton.getUser();
			String url = "https://api-todo-unb.herokuapp.com/tasks/"+ user.id;
			HttpResponse<JsonNode> res = Unirest.post(url).header("Content-Type","application/json").header("Authorization", "bearer " + user.getSessionToken()).body(getTask).asJson();
			JSONObject jsonTask = res.getBody().getObject();
			System.out.println(jsonTask);
			Tasks tasks = new Tasks(Integer.parseInt(jsonTask.getString("id")), jsonTask.getString("title"), jsonTask.getString("category"), Boolean.valueOf(jsonTask.getString("done")), Boolean.valueOf(jsonTask.getString("favourite")), jsonTask.getString("deadline"), jsonTask.getString("description"), Boolean.valueOf(jsonTask.getString("deleted")));
			return tasks;
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
		Object taskToDelete = lookForTask(id, singleton.getListTasks());
		try{
			User user = singleton.getUser();
			String url = "https://api-todo-unb.herokuapp.com/tasks/"+ user.id;
			HttpResponse<JsonNode> deleteTask = Unirest.delete(url).header("Content-Type","application/json").header("Authorization", "bearer " + user.getSessionToken()).body(taskToDelete).asJson();
			JSONObject jsonTasktoDelete = deleteTask.getBody().getObject();
			System.out.println(jsonTasktoDelete);
			Tasks taskDeleted = new Tasks(Integer.parseInt(jsonTasktoDelete.getString("id")), jsonTasktoDelete.getString("title"), jsonTasktoDelete.getString("category"), Boolean.valueOf(jsonTasktoDelete.getString("done")), Boolean.valueOf(jsonTasktoDelete.getString("favourite")), jsonTasktoDelete.getString("deadline"), jsonTasktoDelete.getString("description"), Boolean.valueOf(jsonTasktoDelete.getString("deleted")));
			return taskDeleted;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public Tasks update(Object taskChanges, Singleton singleton) {
		try{
			String getUpdate = "{id:"+ id+
					"\ntitle:"+ title+
					"\ncategory:"+ category+
					"\ndone:"+ done+
					"\nfavourite:"+ favourite+
					"\ndeadline:"+ deadline +
					"\ndescription:"+description+
					"\ndeleted:"+ deleted +"}";
			User user = singleton.getUser();
			String url = "https://api-todo-unb.herokuapp.com/tasks/"+ user.id;
			HttpResponse<JsonNode> updateTask = Unirest.put(url).header("Content-Type","application/json").header("Authorization", "bearer " + user.getSessionToken()).body(taskChanges).asJson();
			JSONObject jsonTasktoUpdate = updateTask.getBody().getObject();
			System.out.println(jsonTasktoUpdate);
			Tasks taskUpdated = new Tasks(Integer.parseInt(jsonTasktoUpdate.getString("id")), jsonTasktoUpdate.getString("title"), jsonTasktoUpdate.getString("category"), Boolean.valueOf(jsonTasktoUpdate.getString("done")), Boolean.valueOf(jsonTasktoUpdate.getString("favourite")), jsonTasktoUpdate.getString("deadline"), jsonTasktoUpdate.getString("description"), Boolean.valueOf(jsonTasktoUpdate.getString("deleted")));
			return taskUpdated;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
