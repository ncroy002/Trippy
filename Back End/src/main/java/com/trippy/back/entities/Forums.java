package com.trippy.back.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Forums {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    String forum_user;

    @Column
    String forum_title;

    @Column
    String forum_post_date;

    @Column
    String forum_post;

    public Forums(){}

    public Forums(int id, String forum_user, String forum_title, String forum_post_date, String forum_post){
        this.id = id;
        this.forum_user = forum_user;
        this.forum_title = forum_title;
        this.forum_post_date = forum_post_date;
        this.forum_post = forum_post;
    }

    public int getId(){ return id; }

    public String getForumUser(){ return forum_user; }

    public String getForumTitle(){ return forum_title; }

    public String getForumPostDate(){ return forum_post_date; }

    public String getForumPost(){ return forum_post; }

    public void setId(int id){ this.id = id; }

    public void setForumUser(String forum_user){ this.forum_user = forum_user; }

    public void setForumTitle(String forum_title){ this.forum_title = forum_title; }

    public void setForumPostDate(String forum_post_date){ this.forum_post_date = forum_post_date; }

    public void setForumPost(String forum_post){ this.forum_post = forum_post; }

    @Override
    public String toString(){
        return "Forums{" + "id=" + id +
                            ", username" + forum_user + '\'' +
                            ", title" + forum_title +  '\'' +
                            ", postDate" + forum_post_date + '\'' +
                            ", post" + forum_post + '\'' +
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
                && Objects.equals(forum_user, forums.forum_user)
                && Objects.equals(forum_title, forums.forum_title)
                && Objects.equals(forum_post_date, forums.forum_post_date)
                && Objects.equals(forum_post, forums.forum_post);
    }
}
