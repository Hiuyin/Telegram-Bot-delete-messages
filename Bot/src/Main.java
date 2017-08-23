import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public  class Main {
    public static void main(String[] args){
       // Vetores v = new Vetores(0);
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try{
            botsApi.registerBot(new Bot());
        }catch (TelegramApiException e ){
            e.printStackTrace();
        }
    }
}
