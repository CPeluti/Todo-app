
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date; 
import java.time.LocalDateTime;
import java.time.ZoneOffset;   

public class Tasks {
	public String title;//antigo conteudo
	public String category;//antiga categoria
	public boolean done = false;//antiga completada
	public boolean favourite = false;//antigo favorito
	public String deadline;//antiga data formato da data vai ser dd/mm/aaaa ou dd/MM/yyyy HH:mm:ss
	public String description;// antiga descricao
	
	public Tasks(String title, String category, boolean done, boolean favourite, String deadline, String description) {
		this.title = title;
		this.category = category;
		this.done = done;
		this.favourite = favourite;
		this.deadline = deadline;
		this.description = description;
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

}
