package ru.netology.repository;

import ru.netology.model.Post;
import ru.netology.service.PostService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class PostRepository {

    private Map<Long, Post> map;

    public PostRepository() {
        //немного наполним базу
        Post post1 = new Post(1, "Post1");
        Post post2 = new Post(2, "Post2");

        //использую вариант мапы для многопоточности. Крайне быстро работает на чтение
        map = new ConcurrentHashMap();
        map.put(post1.getId(), post1);
        map.put(post2.getId(), post2);
    }

    public List<Post> all() {
        return map.entrySet().stream().map(x -> x.getValue()).toList();
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable((Post) map.get(id));
    }

    public Post save(Post post) {
        map.put(post.getId(), post);
        return post;
    }

    public void removeById(long id) {
        map.remove(id);
    }
}
