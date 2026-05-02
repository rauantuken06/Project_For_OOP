package kbtu.university.patterns;

import java.util.ArrayList;
import java.util.List;

public class NewsPublisher {

    private List<NewsSubscriber> subs = new ArrayList<>();

    public void addSubscriber(NewsSubscriber s) {
        subs.add(s);
    }

    public void notifySubs(String news) {
        for (NewsSubscriber s : subs) {
            s.update(news);
        }
    }
}