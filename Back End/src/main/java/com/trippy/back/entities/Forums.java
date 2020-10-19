package com.trippy.back.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity()
public class Forums {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    String forumUser;

    @Column
    String forumTitle;

    @Column
    String forumPostDate;

    @Column
    String forumPost;

    public Forums(){}

    public Forums(int id, String forumUser, String forumTitle, String forumPostDate, String forumPost){
        this.id = id;
        this.forumUser = forumUser;
        this.forumTitle = forumTitle;
        this.forumPostDate = forumPostDate;
        this.forumPost = forumPost;
    }

    public int getId(){ return id; }

    public String getForumUser(){ return forumUser; }

    public String getForumTitle(){ return forumTitle; }

    public String getForumPostDate(){ return forumPostDate; }

    public String getForumPost(){ return forumPost; }

    public void setId(int id){ this.id = id; }

    public void setForumUser(String forumUser){ this.forumUser = forumUser; }

    public void setForumTitle(String forumTitle){ this.forumTitle = forumTitle; }

    public void setForumPostDate(String forumPostDate){ this.forumPostDate = forumPostDate; }

    public void setForumPost(String forumPost){ this.forumPost = forumPost; }

    @Override
    public String toString(){
        return "Forums{" + "id=" + id +
                            ", username" + forumUser + '\'' +
                            ", title" + forumTitle +  '\'' +
                            ", postDate" + forumPostDate + '\'' +
                            ", post" + forumPost + '\'' +
                            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Forums forums = (Forums) o;
        return Objects.equals(id, forums.id)
                && Objects.equals(forumUser, forums.forumUser)
                && Objects.equals(forumTitle, forums.forumTitle)
                && Objects.equals(forumPostDate, forums.forumPostDate)
                && Objects.equals(forumPost, forums.forumPost);
    }
}
