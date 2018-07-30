package com.example.demo.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRespository extends JpaRepository<User,Long> {
    User findByUserName(String userName);
    User findById(Long id);
    List<User> findAll();
    Page<User> findByUserName(String userName, Pageable pageable);

    /*
    自定义查询语句sql在SQL的查询方法上面使用@Query注解，如涉及到删除和修改在需要加上@Modifying.
        也可以根据需要添加 @Transactional 对事物的支持，查询超时的设置等
    注意语句中选择"select * "改成"select u from User u",param要用?1,?2表示位置
    */
    @Modifying
    @Query("update User u set u.userName=?1 where u.id=?2")
    int modifyByIdAndUserName(String userName,Long id);

    @Transactional
    @Modifying
    @Query("delete from User where id=?1")
    void deleteByUserId(Long id);

    @Transactional(timeout=10)
    @Query("select u from User u where u.id>?1 and u.id<?2")
    List<User> findByIdRange(Long idstart,Long idend);

    /*
    多表查询：
    多表查询在spring data jpa中有两种实现方式，第一种是利用hibernate的级联查询来实现，第二种是创建一个结果集的接口来接收连表查询后的结果，这里主要第二种方式
    创建结果集接口：
    public interface HotelSummary {

        City getCity();

        String getName();

        Double getAverageRating();

        default Integer getAverageRatingRounded() {
            return getAverageRating() == null ? null : (int) Math.round(getAverageRating());
        }

    }
    定义：
    @Query("select h.name as name, avg(r.rating) as averageRating "
            - "from Hotel h left outer join h.reviews r  group by h")
    Page<HotelSummary> findByCity(Pageable pageable);
    使用：
    Page<HotelSummary> hotels = this.hotelRepository.findByCity(new PageRequest(0, 10, Direction.ASC, "name"));
    for(HotelSummary summay:hotels){
            System.out.println("Name" +summay.getName());
        }


    多数据源：
    实体类声明@Entity 关系型数据库支持类型、声明@Document 为mongodb支持类型，不同的数据源
     interface PersonRepository extends Repository<Person, Long> {
     …
    }

    @Entity
    public class Person {
      …
    }

    interface UserRepository extends Repository<User, Long> {
     …
    }

    @Document
    public class User {
      …
    }
     */
}
