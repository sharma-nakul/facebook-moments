package Facebook;

import FacebookUser.UPost;
import com.restfb.Connection;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.exception.FacebookGraphException;
import com.restfb.types.Post;
import com.restfb.types.User;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Class calls functions to fetch User's FacebookDesign Highlights
 * Created by Nakul Sharma on 20-04-2015.
 */
public class FacebookDesign {

  /*  private FacebookClient fbClient;
    FacebookDesign(FacebookClient fbClient){
        this.fbClient=fbClient;
    }
*/
    protected TreeMap<String, ArrayList<UPost>> getAllPost(FacebookClient fbClient) {
        TreeMap<String, ArrayList<UPost>> posts = new TreeMap<>();
        ArrayList<UPost> monthPost = new ArrayList<>();
        Date oneYearAgo = new Date(System.currentTimeMillis() - 1000L * 60L * 60L * 24L * 365L);
        String userId, postMonth = "WrongMonth", postYear;
        SimpleDateFormat month = new SimpleDateFormat("MM");
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] alphabeticMonth = dfs.getMonths();
        Date date = new Date();
        int flag = 0;
        try {
            User me = fbClient.fetchObject("me", com.restfb.types.User.class, Parameter.with("fields", "id"));
            userId = me.getId();
            Date currentDate = dateFormat.parse(dateFormat.format(date));
            Connection<Post> userPost = fbClient.fetchConnection("me/posts", Post.class, Parameter.with("fields", "id,message,description,status_type,type, story, created_time"), Parameter.with("until", "yesterday"), Parameter.with("since", oneYearAgo));
            do {
                for (Post p : userPost.getData()) {
                    int numericMonth = Integer.parseInt(month.format(p.getCreatedTime()));
                    if (numericMonth >= 1 && numericMonth <= 12) {
                        postMonth = alphabeticMonth[numericMonth - 1];
                    }
                    postYear = year.format(p.getCreatedTime());
                    // System.out.println("Current Month: " + postMonth + " & Year: " + postYear + " & Flag: " + flag);
                    if (!currentDate.equals(dateFormat.parse(postYear + "-" + numericMonth))) {
                        Collections.sort(monthPost);
                        posts.put(dateFormat.format(currentDate), monthPost);
                        currentDate = dateFormat.parse(postYear + "-" + numericMonth);
                        flag = 1;
                        // System.out.println("Date: " + dateFormat.format(currentDate) + " Flag: " + flag);
                    } else {
                        flag = 0;
                    }
                    // System.out.println("Current Month: " + postMonth + " & Year: " + postYear + " & Flag: " + flag);
                    switch (flag) {
                        case 0:
                            Post count = fbClient.fetchObject(p.getId(), Post.class, Parameter.with("fields", "likes.summary(true),comments.summary(true)"));
                            UPost post = new UPost(userId, p.getId(), p.getMessage(), postMonth, p.getStatusType(), count.getLikesCount(), count.getCommentsCount());
                            post.setStory(p.getStory());
                            post.setType(p.getType());
                            post.setDescription(p.getDescription());
                            post.setPostYear(postYear);
                            monthPost.add(post);
                            break;
                        case 1:
                            monthPost = new ArrayList<>();
                            Post count1 = fbClient.fetchObject(p.getId(), Post.class, Parameter.with("fields", "likes.summary(true),comments.summary(true)"));
                            UPost post1 = new UPost(userId, p.getId(), p.getMessage(), postMonth, p.getStatusType(), count1.getLikesCount(), count1.getCommentsCount());
                            post1.setStory(p.getStory());
                            post1.setType(p.getType());
                            post1.setDescription(p.getDescription());
                            post1.setPostYear(postYear);
                            monthPost.add(post1);
                            break;
                    }
                }
                userPost = fbClient.fetchConnectionPage(userPost.getNextPageUrl(), Post.class);
            } while (userPost.hasNext());

        } catch (FacebookGraphException e) {
            System.out.println("Error: " + e.getErrorCode() + "\nError Message: " + e.getErrorMessage());
            System.out.println("Error Type: " + e.getErrorType() + "\nHttps Status Code" + e.getHttpStatusCode());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return posts;
    }

    public TreeMap<String, ArrayList<UPost>> getHighlights(FacebookClient fbClient) {
        TreeMap<String, ArrayList<UPost>> highlights = new TreeMap<>();
        for (Map.Entry<String, ArrayList<UPost>> entry : getAllPost(fbClient).entrySet()) {
            String key = entry.getKey();
            ArrayList<UPost> value = entry.getValue();
            ArrayList<UPost> topPost = new ArrayList<>();
            Iterator it = value.iterator();
            int flag = 0, count = 0;
            while (flag == 0) {
                if (it.hasNext()) {
                    if (count < 5) {
                        topPost.add((UPost) it.next());
                        count++;
                        flag = 0;
                    } else {
                        flag = 1;
                        count = 1;
                    }
                } else
                    flag = 1;
            }
            highlights.put(key, topPost);
        }
        return highlights;
    }

}

