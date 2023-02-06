

import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;

public class DeepLTranslator {
    private static final String API_URL = "https://api-free.deepl.com/v2/translate";
    private static final String API_KEY = "71a62c6b-e251-4a1a-adcb-1745ac70f9f0:fx";

    public enum TargetLanguages {
        German("de"),
        English("en"),
        French("fr"),
        Spanish("es"),
        Portuguese("pt"),
        Italian("it"),
        Dutch("nl"),
        Polish("pl"),
        VarLanguage("xx");

        private String code;

        TargetLanguages(String code) {
            this.code = code;
        }

        public void setCode(String code){
            this.code = code;
        }
        public String getCode() {
            return code;
        }
    }

    public static class RequestBodyJson {
        String[] text;
        String target_lang;

        public RequestBodyJson(String[] text, String target_lang) {
            this.text = text;
            this.target_lang = target_lang;
        }
    }


    public Translation translateME(Object toTranslate, TargetLanguages language) throws IOException {
        String responseString;
        Translation done;
        Gson gson = new Gson();

        if (toTranslate instanceof String) {
            String[] trans = new String[1];
            trans[0] = (String) toTranslate;
            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");

            //creating a request object
            Request request = new Request.Builder()
                    .url(API_URL)
                    .post(RequestBody.create(mediaType, new Gson().toJson(new RequestBodyJson(trans, language.getCode()))))
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "DeepL-Auth-Key " + API_KEY)
                    .build();

            Response response = client.newCall(request).execute();
            done = gson.fromJson(response.body().string(),Translation.class);

            responseString = response.body().string();
            System.out.println(responseString);
            return done;

        } else if (toTranslate instanceof String[]) {
            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");

            //creating a request object
            Request request = new Request.Builder()
                    .url(API_URL)
                    .post(RequestBody.create(mediaType, new Gson().toJson(new RequestBodyJson((String[]) toTranslate, language.getCode()))))
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "DeepL-Auth-Key " + API_KEY)
                    .build();

            Response response = client.newCall(request).execute();
            done = gson.fromJson(response.body().string(),Translation.class);
            responseString = response.body().string();
            System.out.println(responseString);
            return done;

        } else {
            throw new IOException("The passed parameter is not a String or String Array!");
        }

    }


}