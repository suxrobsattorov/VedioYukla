package ok.suxrob;

import com.google.gson.Gson;
import ok.suxrob.Instagram.DTO;
import ok.suxrob.Linkedin.LinkedinDTO;
import ok.suxrob.TikTok.TikTokDTO;
import ok.suxrob.YouTube.YouTubeDTO;
import okhttp3.*;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;

public class WelcomeController {

    public SendMessage instagramVedio(Update update, String linc) throws IOException {
        SendMessage sendMessage = new SendMessage();

        String[] d = linc.split("/");
        String s = "";

        for (int i = 0; i < d.length; i++) {
            if (d[i].startsWith("C")) {
                s = d[i];
            }
        }

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://instagram-media-downloader.p.rapidapi.com/tgbots/ig/load/post.php?url=https%3A%2F%2F" +
                        "www.instagram.com%2Fp%2F" + s + "%2F%3Futm_medium%3Dcopy_link")
                .get()
                .addHeader("x-rapidapi-host", "instagram-media-downloader.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "978175f32fmshd0828de0c3f6991p1e9ab9jsn4f0b1d0203d0")
                .build();

        Response response = client.newCall(request).execute();

        String json = response.body().string();
        Gson gson = new Gson();
        DTO dto = gson.fromJson(json, DTO.class);


        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setParseMode("html");
        sendMessage.setText("<a href = \"" + dto.getVideo() + "\"> link </a>");
        return sendMessage;
    }

    public SendMessage youTubeMusic(Update update, String linc) throws IOException {
        SendMessage sendMessage = new SendMessage();

        String[] d = linc.split("/");

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://youtube-video-info.p.rapidapi.com/video_formats?video=" + d[3])
                .get()
                .addHeader("x-rapidapi-host", "youtube-video-info.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "9f1d8301f9msh5e30ce7d725c289p16cb97jsn4432ee57589e")
                .build();

        Response response = client.newCall(request).execute();

        String json = response.body().string();
        Gson gson = new Gson();
        YouTubeDTO dto = gson.fromJson(json, YouTubeDTO.class);


        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setParseMode("html");
        sendMessage.setText("<a href = \"" + dto.getAllFormats()[4].getLink() + "\">" + dto.getVideoTitle() + "</a>");
        return sendMessage;
    }

    public SendMessage youTubeVideo(Update update, String linc) throws IOException {
        SendMessage sendMessage = new SendMessage();

        String[] d = linc.split("/");

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://youtube-video-info.p.rapidapi.com/video_formats?video=" + d[3])
                .get()
                .addHeader("x-rapidapi-host", "youtube-video-info.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "9f1d8301f9msh5e30ce7d725c289p16cb97jsn4432ee57589e")
                .build();

        Response response = client.newCall(request).execute();

        String json = response.body().string();
        System.out.println(json);
        Gson gson = new Gson();
        YouTubeDTO dto = gson.fromJson(json, YouTubeDTO.class);

        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setParseMode("html");
        sendMessage.setText("<a href = \"" + dto.getAllFormats()[5].getLink() + "\"> " + dto.getVideoTitle() + " </a>");
        return sendMessage;
    }


    public SendMessage tiktok(Update update, String linc) throws IOException {
        SendMessage sendMessage = new SendMessage();

        String[] d = linc.split("/");

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://tiktok-downloader-download-tiktok-videos-without-watermark.p.rapidapi.com/index?url=" +
                        "https%3A%2F%2Fvm.tiktok.com%2F" + d[3] + "%2F")
                .get()
                .addHeader("url", "https://vm.tiktok.com/ZSeuhWj1k/")
                .addHeader("x-rapidapi-host", "tiktok-downloader-download-tiktok-videos-without-watermark.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "9f1d8301f9msh5e30ce7d725c289p16cb97jsn4432ee57589e")
                .build();

        Response response = client.newCall(request).execute();

        String json = response.body().string();
        Gson gson = new Gson();
        TikTokDTO dto = gson.fromJson(json, TikTokDTO.class);


        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setParseMode("html");
        sendMessage.setText("<a href = \"" + dto.getVideo()[0] + "\n" + dto.getMusic()[0] + "\"> video </a>");
        return sendMessage;
    }


    public SendMessage linkedin(Update update, String linc) throws IOException {
        SendMessage sendMessage = new SendMessage();

        String[] d = linc.split("/");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("url", "https://www.linkedin.com/posts/" + d[4] + "utm_source=linkedin_share&utm_medium=member_desktop_web")
                .build();

        Request request = new Request.Builder()
                .url("https://linkedin-video-downloader.p.rapidapi.com/")
                .post(body)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .addHeader("X-RapidAPI-Host", "linkedin-video-downloader.p.rapidapi.com")
                .addHeader("X-RapidAPI-Key", "978175f32fmshd0828de0c3f6991p1e9ab9jsn4f0b1d0203d0")
                .build();

        Response response = client.newCall(request).execute();

        String json = response.body().string();
        Gson gson = new Gson();
        LinkedinDTO dto = gson.fromJson(json, LinkedinDTO.class);


        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setParseMode("html");
        sendMessage.setText("<a href = \"" + dto.getResponse().getLinks().get(0).getUrl() + "\n" + "\"> video </a>");
        return sendMessage;
    }
































   /* public SendMessage start(Update update, Integer index, String username) throws IOException {
        SendMessage sendMessage = new SendMessage();

        OkHttpClient client = new OkHttpClient();
        String[] s = username.split("@");
        Request request = new Request.Builder()
                .url("https://instagram130.p.rapidapi.com/account-info?username=" + s[1])
                .get()
                .addHeader("x-rapidapi-host", "instagram130.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "9f1d8301f9msh5e30ce7d725c289p16cb97jsn4432ee57589e")
                .build();

        Response response = client.newCall(request).execute();
        String json = response.body().string();

        Gson gson = new Gson();
        DTO dto=gson.fromJson(json,DTO.class);

        String path = dto.getEdge_owner_to_timeline_media().getEdges().get(index).getNode().getDisplay_url();// + ".jpg";

        File file = new File(path);
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setParseMode("html");
        sendMessage.setText("<a href = \"" + path + "\"> link </a>");
        return sendMessage;
    }*/
}
