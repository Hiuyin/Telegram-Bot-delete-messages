import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;


public class Bot extends TelegramLongPollingBot {


    int CountMessages = -1;
    long taskLimit;
    ArrayList messageIdArray = new ArrayList();
    int numberMessages = 100;

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage send = new SendMessage();
        switch(update.getMessage().getText()){
            case "/help":
                send.setChatId(update.getMessage().getChatId().toString())
                        .setText("Bot feito para delete de mensagens após um número N de mensagens");
                try {
                    sendMessage(send);

                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "/setnumbermessages":
                setNumberMessages(update.getMessage().getChatId().toString());
            default:
                if (!(CountMessages() < numberMessages)) {
                    if (update.hasMessage() && update.getMessage().hasText()) {
                        arrayMessageDelete(update.getMessage().getChatId().toString());
                        CountMessages = 0;
                        setMessageIdArray(update.getMessage().getMessageId());
                    }
                } else {
                    setMessageIdArray(update.getMessage().getMessageId());
                }

        }


    }

    @Override
    public String getBotUsername() {
        return "BoomBot";
    }

    @Override
    public String getBotToken() {
        return "340047831:AAF7lflenkNU1KU3J3lFCdl9oD92takfNaM";
    }

    public void initCountMessages() {
        this.CountMessages = 0;
    }

    public void initTimeLimit(long limit) {
        this.taskLimit = limit;
    }

    public int CountMessages() {
        int Counter = CountMessages;

        CountMessages = Counter + 1;
        return CountMessages;
    }

    public void arrayMessageDelete(String ChatID) {
        for (int i = 0; i < numberMessages; i++) {
            try

            {
                DeleteMessage delete = new DeleteMessage()
                        .setChatId(ChatID)
                        .setMessageId( Integer.parseInt(messageIdArray.get(i).toString()));
                deleteMessage(delete);
            } catch (
                    TelegramApiException e)

            {
                e.printStackTrace();
            }

            initCountMessages();
        }
    }
    public void setMessageIdArray (int MessageID){
         messageIdArray.add(MessageID);
    }
    public void setNumberMessages(String chatID){
        SendMessage s = new SendMessage();
        s.setChatId(chatID).setText("Digite o valor para definir o número de mensagens que o bot irá receber antes de apaga-las");
        try {
            sendMessage(s);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public void recuperaValor(Update value){
        numberMessages = Integer.parseInt(value.getMessage().getText().toString());
    }

}





