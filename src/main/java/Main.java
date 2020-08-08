import java.util.ArrayList;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.io.*;
import java.lang.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main extends ListenerAdapter {
    public int Adam = 0;
    private Object ArrayIndexOutOfBoundsException;

    public static void main(String[] args) throws LoginException {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        String token = "Enter your token";

        builder.setToken(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.addEventListener(new Main());
        builder.buildAsync();
    }

    public String getRandomLine(String name) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(name));
        ArrayList<String> nickname = new ArrayList<String>();
        String line = br.readLine();
        while(line!= null) {
            nickname.add(line);

            line = br.readLine();
        }
        br.close();
        return nickname.get((int) Math.floor(Math.random()* nickname.size())); //get random name
    };
    public void onMessageReceived(MessageReceivedEvent event){
        System.out.println("Recieved MEssage from" +
                event.getAuthor().getName()+ ": " +
                event.getMessage().getContentDisplay());
        if(event.getMessage().getContentRaw().equals("!ping")){
            event.getChannel().sendMessage("Pong!").queue();
        }
        else if (event.getMessage().getContentRaw().equals("!avatar")) {
            // Send the user's avatar URL
            System.out.println("WHAT IS IT");
            event.getChannel().sendMessage(event.getAuthor().getAvatarUrl()).queue();
        }
        if (event.getAuthor().getName().split(" ")[0].equals("AdamDZ")) {
            // Send the user's avatar URL
            try {
                event.getChannel().sendMessage(getRandomLine("Adam.txt")).queue();

            } catch (IOException e) {
                e.printStackTrace();
                event.getChannel().sendMessage("NO").queue();
            }
            System.out.println(event.getAuthor().getName().split(" ")[0]);

        }
        else if (event.getMessage().getContentRaw().split(" ")[0].equals("*add")) {
            // Send the user's avatar URL
            try {
                FileWriter fileWritter = new FileWriter("Adam.txt",true);
                BufferedWriter bw = new BufferedWriter(fileWritter);
                try {
                    bw.write("\n" + event.getMessage().getContentRaw().substring(5));
                    System.out.println("Successfully wrote to the file.");
                }
                catch (Exception e) {

                }
                bw.close();
                fileWritter.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        else if((event.getMessage().getContentRaw().toLowerCase().indexOf("adam") != -1) && !event.getAuthor().getName().split(" ")[0].equals("Adam")) {
            ++Adam;
        }
        if(Adam == 3){
            Adam = 0;
            try {
                event.getChannel().sendMessage(getRandomLine("AdamR.txt")).queue();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    };

    /*
    public void onMessageReceived(MessageReceivedEvent event){
        System.out.println("Recieved MEssage from" +
                event.getAuthor().getName()+ ": " +
                event.getMessage().getContentDisplay());
        // If the message is "what is my avatar"

    };
else if (event.getAuthor().getName().split(" ")[0].equals("Tim")) {
            // Send the user's avatar URL
            try {
                event.getChannel().sendMessage(getRandomLine("Adam.txt")).queue();

            } catch (IOException e) {
                e.printStackTrace();
                event.getChannel().sendMessage("NO").queue();
            }
            System.out.println(event.getAuthor().getName().split(" ")[0]);

        }
     */
}
