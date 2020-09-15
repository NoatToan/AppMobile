package com.example.rssfeed;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity {
    private ArrayList<Food> listFood;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        listView = findViewById(R.id.listViewFood);
        FetchFeedTask fetch = new FetchFeedTask(getIntent().getStringExtra("url"));
        fetch.execute();
    }

    private class FetchFeedTask extends AsyncTask<Void, Void, Boolean> {
        private String urlLink;

        public FetchFeedTask(String urlLink) {
            this.urlLink = urlLink;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            if (TextUtils.isEmpty(urlLink))
                return false;
            try {
                if (!urlLink.startsWith("http://") && !urlLink.startsWith("https://"))
                    urlLink = "http://" + urlLink;
                URL url = new URL(urlLink);

                InputStream inputStream = url.openConnection().getInputStream();
                listFood = parseFeed(inputStream);
                return true;
            } catch (IOException e) {
                Log.e("background", "Error", e);
            } catch (XmlPullParserException e) {
                Log.e("background", "Error", e);
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {
                listView.setAdapter(new FoodAdapter(FoodActivity.this, listFood,getIntent().getStringExtra("cate")));
            }
        }

        private ArrayList<Food> parseFeed(InputStream is) throws XmlPullParserException, IOException {
            String title = "";
            String link = "";
            String description = "";
            String img = "";
            String pubDate = "";
            boolean isItem = false;
            ArrayList<Food> listFood = new ArrayList<Food>();

            try {
                XmlPullParser parser = Xml.newPullParser();
                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                parser.setInput(is, null);

                int evenType = parser.getEventType();
                while (evenType != XmlPullParser.END_DOCUMENT) {
                    if (evenType == XmlPullParser.START_TAG) {
                        if (parser.getName().equalsIgnoreCase("item")) {
                            isItem = true;
                        } else if (parser.getName().equalsIgnoreCase("title")) {
                            if (isItem) {
                                title = parser.nextText();
                            }
                        } else if (parser.getName().equalsIgnoreCase("link")) {
                            if (isItem) {
                                link = parser.nextText();
                            }
                        } else if (parser.getName().equalsIgnoreCase("pubDate")) {
                            if (isItem) {
                                pubDate = parser.nextText();
                                pubDate = pubDateString(pubDate);
                            }
                        } else if (parser.getName().equalsIgnoreCase("description")) {
                            if (isItem) {
                                description = parser.nextText();
                                description = plainDescription(description);
                            }
                        } else if (parser.getName().equalsIgnoreCase("content:encoded")) {
                            if (isItem) {
                                String text = "";
                                img = parser.nextText();
                                int start = img.indexOf("<img");
                                if (start != -1) {
                                    int end = img.indexOf("/>");
                                    text = img.substring(start, end);
                                    start = text.indexOf("src=");
                                    end = text.indexOf(".jpg");
                                    if (end == -1) {
                                        end = text.indexOf(".png");
                                    }
                                    text = text.substring(start + 5, end + 4);

                                }
                                img = text;
                                Log.d("img", "nd:" + text);
                            }
                        }
                    } else if (evenType == parser.END_TAG && parser.getName().equalsIgnoreCase("item")) {
                        Food food = new Food(title, description, link, img,pubDate);
                        listFood.add(food);
                        title = "";
                        description = "";
                        link = "";
                        img = "";
                        isItem = false;
                    }
                    evenType = parser.next();
                }
                return listFood;
            } finally {
                is.close();
            }
        }
    }

    private String pubDateString(String pubDate) {
        int end = pubDate.indexOf("+");
        if(end != -1) {
            pubDate = pubDate.substring(0, end);
        }
        return pubDate;
    }

    private String plainDescription(String htmlString) {
        String noHtmlString = Html.fromHtml(htmlString).toString();
        return noHtmlString;
    }
}
