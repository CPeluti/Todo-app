package app.models;

import app.controllers.UserInstance;
import app.interfaces.Storable;
import kong.unirest.Unirest;

import java.util.*;


public class Tasks implements Storable {
	private int id;
	private String title;//conteudo
	private int category;//categoria
	private  boolean done = false;//completada
	private  boolean favourite = false;// favorito
	private String deadline;//data formato da data vai ser dd/mm/aaaa ou dd/MM/yyyy HH:mm:ss
	private String description;// descricao
	private boolean deleted = false;//tarefa deletada?

	public int getId() { return id; }

	public String getTitle() {
		return title;
	}

	public  int getCategory() {
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

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public void setFavourite(boolean favourite) {
		this.favourite = favourite;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Tasks(int id, String title, int category, boolean done, boolean favourite, String deadline, String description, boolean deleted) {
		this.id = id;
		this.title = title;
		this.category = category;
		this.done = done;
		this.favourite = favourite;
		this.deadline = deadline;
		this.description = description;
		this.deleted = deleted;
	}
	public static void create(Tasks task, UserInstance userInstance) {
	    User user = userInstance.getUser();
	    System.out.println(user.getSessionToken());
	    Map<String, String> header = new HashMap<>();
	    header.put("Content-Type","application/json");
	    header.put("Authorization", "bearer " + user.getSessionToken());

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
	    Unirest.post("https://api-todo-unb.herokuapp.com/tasks/{userId}").routeParam("userId",user.getId()).headers(header).body(getTask).asJson();
	}

	public static void delete(int id, UserInstance userInstance){
		User user = userInstance.getUser();
		String url = "https://api-todo-unb.herokuapp.com/tasks/" + user.getId();
		String dataString = "{\"taskId\":\""+id+"\"}";

		Unirest.delete(url)
				.header("Content-Type","application/json")
				.header("Authorization", "bearer " + user.getSessionToken())
				.body(dataString)
				.asJson();
	}

	public static void update(Tasks taskChanges, UserInstance userInstance) {
		User user = userInstance.getUser();
		String url = "https://api-todo-unb.herokuapp.com/tasks/" + user.getId();
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
		Unirest.put(url)
				.header("Content-Type","application/json")
				.header("Authorization", "bearer " + user.getSessionToken())
				.body(getTask)
				.asJson();
	}
}
