package com.trippy.back.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity()
public class Forums {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    String username;

    @Column
    String title;

    @Column
    String postDate;

    @Column
    String post;

    public Forums(){}

    public Forums(int id, String username, String title, String postDate, String post){
        this.id = id;
        this.username = username;
        this.title = title;
        this.postDate = postDate;
        this.post = post;
    }

    public int getId(){ return id; }

    public String getUsername(){ return username; }

    public String title(){ return title; }

    public String postDate(){ return postDate; }

    public String getPost(){ return post; }

    public void setId(int id){ this.id = id; }

    public void setUsername(String username){ this.username = username; }

    public void setTitle(String title){ this.title = title; }

    public void setPostDate(String postDate){ this.postDate = postDate; }

    public void setPost(String post){ this.post = post; }

    @Override
    public String toString(){
        return "Forums{" + "id=" + id +
                            ", username" + username + '\'' +
                            ", title" + title +  '\'' +
                            ", postDate" + postDate + '\'' +
                            ", post" + post + '\'' +
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
        return Objects.equals(id, forums.id) && Objects.equals(post, forums.post);
    }
}
