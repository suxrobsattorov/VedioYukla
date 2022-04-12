package ok.suxrob;

import ok.suxrob.WelcomeController;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MyBot extends TelegramLongPollingBot {
    private WelcomeController welcomeController = new WelcomeController();


    public void onUpdateReceived(Update update) {
        User user = update.getMessage().getFrom();
        sendToAdmin(update.getMessage());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        String text = update.getMessage().getText();
        if (update.hasMessage()) {
            if (text.equals("/start")) {
                try {
                    updatephoto(user, new File("E:/JAVA_darslari/JAVA/Amaliy/test/src/main/resources/instagram-tiktok-youtube.jpg"),
                            "#instagram\n#tiktok\n#youtube");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                sendMessage.setText("Quyidagilardan olingan linkni vedioga aylantirib beramiz!");
                sendMessage.setReplyMarkup(menu());
                sendMsg(sendMessage);
            } else if (text.equals("📽️ Instagram")) {
                try {
                    updatephoto(user, new File("E:/JAVA_darslari/JAVA/Amaliy/test/src/main/resources/Instagram-4.jpg"),
                            "Instagram linkini tashlang!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Repository.message.put(update.getMessage().getChatId().toString(), text);
                try {
                    sendMsg(welcomeController.instagramVedio(update, text));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (text.equals("📽️ Tik Tok")) {
                try {
                    updatephoto(user, new File("E:/JAVA_darslari/JAVA/Amaliy/test/src/main/resources/share_img.png"),
                            "Tik Tok linkini tashlang!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Repository.message.put(update.getMessage().getChatId().toString(), text);
                try {
                    sendMsg(welcomeController.tiktok(update, text));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (text.equals("📽️ Linkedin")) {
                try {
                    updatephoto(user, new File("E:/JAVA_darslari/JAVA/Amaliy/test/src/main/resources/linkedin.jpeg"),
                            "Linkedin linkini tashlang!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Repository.message.put(update.getMessage().getChatId().toString(), text);
                try {
                    sendMsg(welcomeController.linkedin(update, text));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if (text.equals("📽️ You Tube")) {
                try {
                    updatephoto(user, new File("E:/JAVA_darslari/JAVA/Amaliy/test/src/main/resources/YouTube-Logo.jpg"),
                            "Tou Tube linkini tashlang!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Repository.message.put(update.getMessage().getChatId().toString(), text);
            } else if (text.startsWith("http")) {
                if (Repository.message.get(update.getMessage().getChatId().toString()).equals("📽️ Instagram")) {
                    try {
                        sendMsg(welcomeController.instagramVedio(update, text));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (Repository.message.get(update.getMessage().getChatId().toString()).equals("📽️ Tik Tok")) {
                    try {
                        sendMsg(welcomeController.tiktok(update, text));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (Repository.message.get(update.getMessage().getChatId().toString()).equals("📽️ Linkedin")) {
                    try {
                        sendMsg(welcomeController.linkedin(update, text));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if (Repository.message.get(update.getMessage().getChatId().toString()).equals("📽️ You Tube")) {
                    try {
                        sendMsg(welcomeController.youTubeVideo(update, text));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    sendMessage.setText("Mavjud bo'lmagan message!");
                    sendMsg(sendMessage);
                }
            }
        } else {
            sendMessage.setText("Mavjud bo'lmagan message!");
            sendMsg(sendMessage);
        }

        System.out.println(text);
    }

    public void sendToAdmin(Message message) {
        SendMessage sendMessage = new SendMessage();
        String mess = message.getChatId() + "\t\t" + message.getFrom().getFirstName() + "\t\t" + message.getFrom().getUserName() + "\t\t" + message.getText();
        sendMessage.setChatId("501270547");
        sendMessage.setText(mess);
        sendMsg(sendMessage);
    }

    public ReplyKeyboardMarkup menu() {
        List<KeyboardRow> keyboardRowList = ReplayKeyboardUtil.rowlist(
                ReplayKeyboardUtil.row(ReplayKeyboardUtil.button("📽️ Instagram")),
                ReplayKeyboardUtil.row(ReplayKeyboardUtil.button("📽️ Tik Tok")),
                ReplayKeyboardUtil.row(ReplayKeyboardUtil.button("📽️ Linkedin")),
                ReplayKeyboardUtil.row(ReplayKeyboardUtil.button("📽️ You Tube"))
        );
        ReplyKeyboardMarkup markup = ReplayKeyboardUtil.keyboard(keyboardRowList);
        markup.setOneTimeKeyboard(true);
        markup.setResizeKeyboard(true);
        markup.setSelective(true);
        return markup;
    }

    public void sendMsg(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(SendPhoto sendPhoto) {
        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void updatephoto(User user, File file, String caption) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(user.getId().toString());
        InputFile inputFile = new InputFile(file);
        sendPhoto.setPhoto(inputFile);
        sendPhoto.setCaption(caption);
        sendMsg(sendPhoto);
    }


    public String getBotUsername() {
        return "t.me/suxrobinstagramdownload_bot";
    }

    public String getBotToken() {
        return "5103826439:AAECpJUNFj12zbFgFGPwzxzJpUiVRGwiJF8";
    }
}

/*

    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        if (update.hasMessage()) {
            String text = update.getMessage().getText();
            int count = 0;
            char[] s = text.toCharArray();
            for (int i = 0; i < s.length; i++) {
                if (Character.isDigit(s[i])) {
                    count++;
                }
            }
            if (text.equals("/start")) {
                sendMessage.setText("Username kiriting");
                sendMsg(sendMessage);
            } else if (text.startsWith("@")) {
                Repository.username.put(update.getMessage().getChatId().toString(), text);
                sendMessage.setText("indexni kiriting");
                sendMsg(sendMessage);
            } else {
                if (count == s.length) {
                    Integer i = Integer.parseInt(text);
                    String matn = Repository.username.get(update.getMessage().getChatId().toString());
                    try {
                        sendMsg(welcomeController.start(update, i, matn));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            System.out.println(text);
        }
    }*/
