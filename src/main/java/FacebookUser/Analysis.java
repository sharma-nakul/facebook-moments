package FacebookUser;

/**
 * Class to hold post's ratings
 * Created by Nakul Sharma on 20-04-2015.
 */
public class Analysis {

    //private long likeRating;
    //private long commentRating;
    private long rating;
    private String postMonth;
    private String postYear;
    private String message;
    private String story;
    private String postId;

    public Analysis(String postId, long likeRating, long commentRating, String postMonth, String postYear, String message, String story) {
        //  this.commentRating=commentRating;
        // this.likeRating=likeRating;
        this.postMonth = postMonth;
        this.postYear = postYear;
        this.setRating(likeRating + commentRating);
        this.message=message;
        this.story=story;
        this.postId=postId;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    public String getPostMonth() {
        return postMonth;
    }

    public String getPostYear() {
        return postYear;
    }

    public String getMessage() {
        return message;
    }

    public String getStory() {
        return story;
    }

    public String getPostId() {
        return postId;
    }
}
