package FacebookUser;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Class holds Facebook data
 * Created by Nakul Sharma on 18-04-2015.
 */
public class UPost implements Comparable<UPost> {

    private String userId;
    private String postId;

    private String postMessage = null;   // message field in Fb JSON
    private String postMonth; // created_time field in Fb JSON
    private String postYear;
    private String statusType;
    private String story = null;
    private String type = null;
    private String description = null;
    private long likesCount; // likes --> data --> id in Fb JSON
    private long commentCount; // comments field in Fb JSON
    private long rating;

    public UPost(String userId, String postId, String postMessage, String postMonth, String statusType, long likesCount, long commentCount) {
        this.setUserId(userId);
        this.setPostId(postId);
        this.setPostMessage(postMessage);
        this.setPostMonth(postMonth);
        this.setLikesCount(likesCount);
        this.setCommentCount(commentCount);
        this.setStatusType(statusType);
        this.setRating((likesCount * 5) + (commentCount * 10));
    }

    @JsonIgnore
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

//    @JsonIgnore
    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }


    public String getPostMessage() {
        return postMessage;
    }

    public void setPostMessage(String postMessage) {
        this.postMessage = postMessage;
    }

    @JsonIgnore
    public String getPostMonth() {
        return postMonth;
    }

    public void setPostMonth(String postMonth) {
        this.postMonth = postMonth;
    }

    @JsonIgnore
    public long getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(long likesCount) {
        this.likesCount = likesCount;
    }

    @JsonIgnore
    public long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(long commentCount) {
        this.commentCount = commentCount;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

//    @JsonIgnore
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

  //      @JsonIgnore
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonIgnore
    public String getPostYear() {
        return postYear;
    }

    public void setPostYear(String postYear) {
        this.postYear = postYear;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    public String getDate() {
        return this.postMonth + "-" + this.postYear;
    }

    @Override
    public int compareTo(UPost other) {
        long r1 = this.rating;
        long r2 = other.rating;
        if (r1 < r2)
            return 1;
        else if (r1 > r2)
            return -1;
        else
            return 0;
    }

}
