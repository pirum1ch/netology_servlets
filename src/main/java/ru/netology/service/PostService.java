package ru.netology.service;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.repository.PostRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class PostService {

    // 2 - потому что мы в конструкторе репозитория в мапе создаем 2 записи с id 1 и 2
    private final AtomicLong counter = new AtomicLong(2);

    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<Post> all() {
        return repository.all();
    }

    public Post getById(long id) {
        return repository.getById(id).orElseThrow(NotFoundException::new);
    }

    public Post save(Post post) {
        Post updatePost;
        // Проверяем ID поста и если он не равнео 0 (те апдейт записи) то ищем в мапе объект с таким же ID
        // и далее в нем меняем Context и записываем в мапу gmailpoportaизменения. Если ID равено 0 - то просто записываем новое значение с инкрементом Counter на единицу
        if (post.getId() != 0) {
            try {
                updatePost = getById(post.getId());
            } catch (NotFoundException nfe) {
                return new Post(-1, "Поста с ID " + post.getId() + " нет. Попрообуйте другой ID");
            }
            updatePost.setContent(post.getContent());
            post = updatePost;
        } else {
            post.setId(counter.incrementAndGet());
        }
        return repository.save(post);
    }

    public void removeById(long id) {
        repository.removeById(id);
    }
}

