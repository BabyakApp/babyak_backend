package Babyak.babyak_backend.repository.chatroom;

import Babyak.babyak_backend.domain.chatroom.ChatRoom;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository @Primary
public class JpaChatroomRepository implements ChatroomRepository {

    private final EntityManager em;

    public JpaChatroomRepository(EntityManager em){
        this.em = em;
    }

//    @Override
//    public Map<Integer, Timestamp> userLastvisited() {
//        Timestamp ts = new Timestamp(99L);
//        HashMap<Integer, Timestamp> map = new HashMap<>();
//        map.put(9, ts);
//        return map;
//    }

//    @Override
//    public List<Integer> memberlist() {
//        return null;
//    }

    @Override
    public List<ChatRoom> findAll() {
        return em.createQuery("select m from ChatRoom m", ChatRoom.class)
                .getResultList();
    }

    @Override
    public List<ChatRoom> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<ChatRoom> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<ChatRoom> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(ChatRoom entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends ChatRoom> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends ChatRoom> S save(S entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public <S extends ChatRoom> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ChatRoom> findById(Long id) {
        ChatRoom ch = em.find(ChatRoom.class, id);
        return Optional.ofNullable(ch);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends ChatRoom> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends ChatRoom> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<ChatRoom> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public ChatRoom getOne(Long aLong) {
        return null;
    }

    @Override
    public ChatRoom getById(Long aLong) {
        return null;
    }

    @Override
    public ChatRoom getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends ChatRoom> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends ChatRoom> List<S> findAll(Example<S> example) {
        return (List<S>) em.createQuery("select ch from ChatRoom ch", ChatRoom.class).getResultList();
    }

    @Override
    public <S extends ChatRoom> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends ChatRoom> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends ChatRoom> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends ChatRoom> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends ChatRoom, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

}
