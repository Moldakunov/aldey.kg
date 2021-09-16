package kg.aldey;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kg.aldey.config.globalvariable.GlobalVar;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import static kg.aldey.config.globalvariable.GlobalVar.*;
import static kg.aldey.config.globalvariable.GlobalVar.SITEMAP;

public class Tes {
  public static void main(String[] args) {
        URL url;
        try {
          String response;
          StringBuilder builder = new StringBuilder();
          Gson gson = new GsonBuilder().create();
          url = new URL("http://212.2.230.77:8080/v1/shoppingCart/buyShoppingCartFast?productId="+128267+
                  "&userId=-1&qty=1&storeId="+GlobalVar.storeId+"&phone="+121212);
          HttpURLConnection connection = (HttpURLConnection) url.openConnection();
          BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
          while ((response = reader.readLine()) != null) {
            builder.append(response);
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
    }



  // получаем от сервера уже подготовленный под наши требования сайтмэп
  // и создаем (в случае, если его еще генерили)
  // или перезаписываем его новыми данными
  public static void generateSitemap() throws IOException {
    FileOutputStream fos;
    CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

    HttpGet request =
        new HttpGet(mainURL + "getSiteMap?baseUrl=https://www.emin.kg/product/&storeId=" + storeId);

    CloseableHttpResponse response = closeableHttpClient.execute(request);
    HttpEntity entity = response.getEntity();

    // Перезаписываем уже существующий файл, т.к. он уже устарел или создаем новый файл
    // флаг false означает перезапись файла
    fos = new FileOutputStream(SITEMAP, false);
    fos.write(EntityUtils.toByteArray(entity));
    fos.flush();
    fos.close();
  }
}
